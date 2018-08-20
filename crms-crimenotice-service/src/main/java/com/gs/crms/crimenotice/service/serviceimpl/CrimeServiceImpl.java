package com.gs.crms.crimenotice.service.serviceimpl;

import com.gs.crms.applycertify.contract.model.ApplyBussinessHistory;
import com.gs.crms.applycertify.contract.service.CertificateApplyService;
import com.gs.crms.applycertify.service.entity.ApplyAndAnalystRelationEntity;
import com.gs.crms.applycertify.service.repository.ApplyAndAnalystRelationRepository;
import com.gs.crms.applycertify.service.repository.WaitApportionRepository;
import com.gs.crms.common.utils.VariableUtil;
import com.gs.crms.crimenotice.service.datamappers.AttachmentMapper;
import com.gs.crms.common.enums.AttachmentEntity;
import com.gs.crms.common.model.AttachmentInfo;
import com.gs.crms.common.model.Error;
import com.gs.crms.common.model.ReturnBase;
import com.gs.crms.common.repository.AttachmentRepository;
import com.gs.crms.common.utils.StringAndListExchangeUtil;
import com.gs.crms.common.utils.TimeUtil;
import com.gs.crms.crimenotice.contract.model.*;
import com.gs.crms.crimenotice.contract.model.search.*;
import com.gs.crms.crimenotice.contract.service.CrimeService;
import com.gs.crms.crimenotice.service.datamappers.*;
import com.gs.crms.crimenotice.service.entity.*;
import com.gs.crms.crimenotice.service.repository.*;

import com.gsafety.springboot.common.utils.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * Created by tanjie on 2017/7/25.
 * 犯罪相关实现
 */
@Service
@Transactional
public class CrimeServiceImpl implements CrimeService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CrimeRepository crimeRepository;

    @Autowired
    private CrimePersonRepository crimePersonRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private CrimeMapper crimeMapper;

    @Autowired
    private CrimePersonMapper crimePersonMapper;

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Autowired
    private NoticeMergedAndSplitHistoryMapper noticeMergedAndSplitHistoryMapper;

    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private WaitApportionRepository waitApportionRepository;
    @Autowired
    private NoticeMergedAndSplitHistoryRepository noticeMergedAndSplitHistoryRepository;
    @Autowired
    private WaitMergingCriminalRelationRepository waitMergingCriminalRelationRepository;
    @Autowired
    private MergedCriminalRepsitory mergedCriminalRepsitory;
    @Autowired
    private CertificateApplyService certificateApplyService;
    @Autowired
    private HttpClientUtil httpClientUtil;
    @Autowired
    private ApplyAndAnalystRelationRepository applyAndAnalystRelationRepository;
    @Autowired
    private NoticeReturnDataModelMapper noticeReturnDataModelMapper;

    @Value("${workflow.baseUrl}")
    private String workFlowUrl;//工作流服务地址

    @Value("${search.searchYear}")
    private int searchYear;//搜索年限
    
    @Value("${process.noticeProcess}")
    private String noticeProcess;//个人申请流程Key


    /**
     * 公告编号查重
     *
     * @param noticeNumber
     * @return
     */
    @Override
    public boolean getNoticeNumberUseStatus(String noticeNumber, String noticeId) {
        int count = noticeRepository.countNoticeByNoticeNumber(noticeNumber);
        if (StringUtils.isNoneBlank(noticeId) && count > 0) {
            int countCurrent = noticeRepository.countNoticeByNoticeNumberAndNoticeId(noticeNumber, noticeId);
            return countCurrent > 0;
        } else {
            return count > 0;
        }
    }
    //region 1 公告保存

    /**
     * 流程
     * 1、保存数据(公告、犯罪信息、犯罪人信息、待合并)
     * 1.1 犯罪相关主表数据
     * a 保存犯罪基本信息
     * b 保存犯罪人员基本信息
     * c 保存公告关联附件信息
     * d 保存公告信息
     * 1.2 保存待合并数据
     * 2、数据保存成功 调用工作流
     *
     * @return boolean
     */
    @Override
    public Map<String, Object> addCrimeAndNoticeInfo(CrimeAndNoticeExtendInfo crimeAndNoticeExtendInfo) {
        Map<String, Object> map = new HashMap<>();
        List<AttachmentInfo> attachmentInfo = crimeAndNoticeExtendInfo.getAttachmentInfo();
        NoticeEntity noticeEntity = addNoticeInfoExchange(crimeAndNoticeExtendInfo.getNoticeInfo());
        // 1 判断公告基础数据和待合并
        boolean saveSuccess = saveNoticeCommonValidateData(noticeEntity, addCrimeInfoExchange(crimeAndNoticeExtendInfo.getCrimeInfo()), addCrimePersonInfoExchange(crimeAndNoticeExtendInfo.getCrimePersonInfo()), addNoticeAttachment(attachmentInfo), crimeAndNoticeExtendInfo.getWaitCriminalIds());
        // 2 数据保存成功 调用工作流启动服务失败后进行事务回滚
        if (saveSuccess && !cellWorkFlowServerDataResolve(noticeEntity)) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            saveSuccess = false;
        }
        map.put(VariableUtil.SUCCESS, saveSuccess);
        map.put(VariableUtil.DATA, saveSuccess ? noticeReturnDataModelMapper.entityToModel(noticeEntity) : null);
        return map;
    }

    /***
     *  犯罪数据(公告、犯罪信息、犯罪人信息、待合并) 保存结果
     *  String backFillCriminalId, String noticeId, String crimePersonId, List<String> waitCriminalIds
     * @param noticeEntity
     * @return
     */
    //region 1.1 保存犯罪基本信息
    private boolean saveNoticeCommonValidateData(NoticeEntity noticeEntity, CrimeEntity crimeEntity, CrimePersonInfoEntity crimePersonEntity, List<String> attachmentIdList, List<String> waitCriminalId) {
        return settingNoticeRelationData(noticeEntity, crimeEntity, crimePersonEntity, attachmentIdList) && saveWaitCriminalData(noticeEntity.getNoticeId(), crimePersonEntity.getCrimePersonId(), waitCriminalId);
    }

    /**
     * 流程：
     * 1、设置相关(公告、犯罪信息、犯罪人信息)的数据
     * 2、保存相关数据(公告、犯罪信息、犯罪人信息)
     *
     * @param crimeEntity
     * @param crimePersonEntity
     * @param noticeEntity
     * @param attachmentIdList
     * @return
     */
    private boolean settingNoticeRelationData(NoticeEntity noticeEntity, CrimeEntity crimeEntity, CrimePersonInfoEntity crimePersonEntity, List<String> attachmentIdList) {
        // 数据创建时 默认数据设置
        crimeEntity.setIsActive("0");
        crimePersonEntity.setIsActive("0");
        crimePersonEntity.setIsMerging("0");
        noticeEntity.setIsActive("0");
        noticeEntity.setStatus("1");
        crimeEntity.setCrimeId(UUID.randomUUID().toString());
        crimePersonEntity.setCrimePersonId(UUID.randomUUID().toString());
        noticeEntity.setNoticeId(UUID.randomUUID().toString());
        noticeEntity.setAttchmentId(CollectionUtils.isEmpty(attachmentIdList) ? null : StringAndListExchangeUtil.changeIdListToString(attachmentIdList));
        noticeEntity.setCrimeId(crimeEntity.getCrimeId());
        noticeEntity.setCrimePersonId(crimePersonEntity.getCrimePersonId());
        noticeEntity.setCrimeId(crimeEntity.getCrimeId());
        return saveNoticeRelationData(crimeEntity, crimePersonEntity, noticeEntity);
    }

    /**
     * 保存公告相关的数据
     *
     * @param crimeEntity
     * @param crimePersonEntity
     * @param noticeEntity
     * @return
     */
    private boolean saveNoticeRelationData(CrimeEntity crimeEntity, CrimePersonInfoEntity crimePersonEntity, NoticeEntity noticeEntity) {
        try {
            crimeRepository.save(crimeEntity);
            crimePersonRepository.save(crimePersonEntity);
            noticeRepository.save(noticeEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:Save notice relation data Call Exception" + e);
            return false;
        }
    }

    /**
     * 保存公告对应的等待分派的数据
     */
    private boolean saveWaitCriminalData(String noticeId, String crimePersonId, List<String> waitCriminalIds) {
        // 6.如果勾选了待合并罪犯，保存合并罪犯关系
        if (!CollectionUtils.isEmpty(waitCriminalIds)) {
            return saveWaitCriminalData(waitCriminalIds, noticeId, crimePersonId);
        }
        return true;
    }
    //endregion

    /**
     * 启动工作流
     * 1、调用成功 保存操作历史
     * 2、不成功  删除等待分派和分派的数据
     *
     * @param noticeEntity
     * @return
     */
    private boolean cellWorkFlowServerDataResolve(NoticeEntity noticeEntity) {
        boolean result = cellWorkFlowServerWhenAddNotice(noticeEntity);
        try {
            if (!result) {
                waitApportionRepository.deleteByApplyInfoId(noticeEntity.getNoticeId(), "3");
                //删除等待分析列表
                applyAndAnalystRelationRepository.deleteByApplyInfoId(noticeEntity.getNoticeId());
            }
            return result;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:delete waitApportion or applyAndAnalyst Relation  when cell workFlow server Call Exception" + e);
            return false;
        }
    }

    /**
     * 后调用工作流成功后 更新申请中的字段workFlowId
     *
     * @param noticeEntity
     * @return
     */
    private boolean cellWorkFlowServerWhenAddNotice(NoticeEntity noticeEntity) {
        try {
            String workFlowId = startProcessByKey(noticeEntity.getNoticeId(), noticeEntity.getEnterPersonId(), noticeEntity.getPriority());
            if (StringUtils.isBlank(workFlowId)) {
                return false;
            } else {
                noticeEntity.setWorkFlowId(workFlowId);
                noticeRepository.save(noticeEntity);
                return saveApplyBusinessHistory(noticeEntity);
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:Cell workflow server when add notice Call Exception" + e);
            return false;
        }
    }

    /**
     * 2保存操作历史
     *
     * @param noticeEntity
     * @return
     */
    private boolean saveApplyBusinessHistory(NoticeEntity noticeEntity) {
        ApplyBussinessHistory applyBussinessHistory = new ApplyBussinessHistory();
        applyBussinessHistory.setApplyId(noticeEntity.getNoticeId());
        applyBussinessHistory.setOperatorId(noticeEntity.getEnterPersonId());//录入人
        applyBussinessHistory.setOperatorTime(new Date());
        applyBussinessHistory.setOperatorTypeId("4");//4代表录入
        applyBussinessHistory.setOperatorName(noticeEntity.getEnteringPersonName());//录入人名称
        applyBussinessHistory.setOperatorResult(noticeEntity.getNoticeInputStatus());//录入结果
        applyBussinessHistory.setOperatorDescription(noticeEntity.getRejectEnterReason());//录入意见
        return certificateApplyService.saveApplyBussinessOperatorHistory(applyBussinessHistory);
    }

    /**
     * 保存待合并列表
     *
     * @param waitCriminalIds
     * @param noticeId
     * @param crimePersonId
     * @return
     */
    private boolean saveWaitCriminalData(List<String> waitCriminalIds, String noticeId, String crimePersonId) {
        try {
            // 6.如果勾选了待合并罪犯，保存合并罪犯关系
            if (!CollectionUtils.isEmpty(waitCriminalIds)) {
                String waitCriminalIdsStr = StringAndListExchangeUtil.changeIdListToString(waitCriminalIds);
                //保存待合并列表 理论上应该把回填的罪犯也加入到待合并列表  前端做了勾选 这里不做处理
                saveWaitMergingCriminalRelation(noticeId, waitCriminalIdsStr, crimePersonId);
                // 7. 将待合并罪犯的状态设置为正在合并isMerging=1
                this.changeCriminalIsMergingStatus("1", waitCriminalIdsStr);
            }
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:Save wait Criminal  Call Exception" + e);
            return false;
        }
    }
    //endregion
    // region 2 重新分析

    /**
     * 重新分析
     * 1、数据保存
     * a：保存犯罪基本信息
     * b: 保存犯罪人员基本信息
     * c: 保存公告关联附件信息
     * d：保存公告信息
     * 2、处理分派 、待合并 的数据
     * 分派：删除旧的分派  重新将任务指给审核员
     * 待合并：更新待合并的数据
     * 3、调用工作流
     *
     * @return boolean
     */
    @Override
    public Map<String, Object> updateCrimeAndNoticeInfo(CrimeAndNoticeExtendInfo crimeAndNoticeExtendInfo) {
        Map<String, Object> map = new HashMap<>();
        CrimeInfo crimeInfo = crimeAndNoticeExtendInfo.getCrimeInfo();
        CrimePersonInfo crimePersonInfo = crimeAndNoticeExtendInfo.getCrimePersonInfo();
        NoticeInfo noticeInfo = crimeAndNoticeExtendInfo.getNoticeInfo();
        List<AttachmentInfo> attachmentInfo = crimeAndNoticeExtendInfo.getAttachmentInfo();
        boolean result = true;
        if (replySaveNoticeCommonValidateData(noticeInfo, crimeInfo, crimePersonInfo, addNoticeAttachment(attachmentInfo), crimeAndNoticeExtendInfo.getWaitCriminalIds())) {
            result = updateApplyAndAnalystRelation(noticeInfo) && saveApplyBusinessHistoryWhenReply(noticeInfo);
        }
        // 数据处理成功后调用工作流失败 进行事务回滚
        if (result && !cellWorkFlowServerWhenReply(noticeInfo)) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result = false;
        }
        map.put(VariableUtil.SUCCESS, result);
        map.put(VariableUtil.DATA, result ? noticeReturnDataModelMapper.modelToModel(noticeInfo) : null);
        return map;
    }

    /**
     * 数据检测
     * 1、保存公告
     * 2 保存公告成功 处理数据
     *
     * @param noticeInfo
     * @param crimeInfo
     * @param crimePersonInfo
     * @return
     */
    private boolean replySaveNoticeCommonValidateData(NoticeInfo noticeInfo, CrimeInfo crimeInfo, CrimePersonInfo crimePersonInfo, List<String> attachmentIdList, List<String> waitCriminalIds) {
        CrimeEntity crimeEntityOld = crimeRepository.getByCrimeId(crimeInfo.getCrimeId());
        CrimePersonInfoEntity crimePersonInfoEntityOld = crimePersonRepository.getByCrimePersonId(crimePersonInfo.getCrimePersonId());
        NoticeEntity noticeEntityOld = noticeRepository.getByNoticeId(noticeInfo.getNoticeId());
        if (crimeEntityOld == null || crimePersonInfoEntityOld == null || noticeEntityOld == null) {
            return false;
        }
        noticeInfo.setWorkFlowId(noticeEntityOld.getWorkFlowId());
        noticeInfo.setAttchmentId(CollectionUtils.isEmpty(attachmentIdList) ? null : StringAndListExchangeUtil.changeIdListToString(attachmentIdList));
        return saveNoticeRelationData(addCrimeInfoExchange(crimeInfo), addCrimePersonInfoExchange(crimePersonInfo), addNoticeInfoExchange(noticeInfo)) && resolveWaitCriminalWhenReply(noticeInfo.getNoticeId(), waitCriminalIds, crimePersonInfo.getCrimePersonId());
    }

    /**
     * 重新分析时应当删除该分析员的分析任务 而不是业务下的所有分派关系
     * 删除旧分派保存新分派
     */
    private boolean updateApplyAndAnalystRelation(NoticeInfo noticeInfo) {
        try {
            applyAndAnalystRelationRepository.deleteByApplyInfoIdAndAnalystId(noticeInfo.getNoticeId(), noticeInfo.getEnterPersonId());
            return addApplyAndAnalystRelation(noticeInfo);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:delete ApplyAndAnalystRelation hen reply submit notice   Call Exception" + e);
            return false;
        }
    }

    /**
     * 重新分给审核员
     *
     * @return
     */
    private boolean addApplyAndAnalystRelation(NoticeInfo noticeInfo) {
        try {
            ApplyAndAnalystRelationEntity applyAndAnalystRelationEntityNew = new ApplyAndAnalystRelationEntity();
            applyAndAnalystRelationEntityNew.setRelationId(UUID.randomUUID().toString());
            applyAndAnalystRelationEntityNew.setCreateTime(new Date());
            applyAndAnalystRelationEntityNew.setApplyInfoId(noticeInfo.getNoticeId());
            applyAndAnalystRelationEntityNew.setAuditorId(noticeInfo.getAuditPersonId());
            applyAndAnalystRelationEntityNew.setPriority(noticeInfo.getPriority());
            applyAndAnalystRelationEntityNew.setBusinessType("3");
            applyAndAnalystRelationRepository.save(applyAndAnalystRelationEntityNew);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:add  ApplyAndAnalystRelation to auditor when reply submit notice  Call Exception" + e);
            return false;
        }
    }

    /**
     * 重新分析公告提交保存操作历史
     */
    private boolean saveApplyBusinessHistoryWhenReply(NoticeInfo noticeInfo) {
        ApplyBussinessHistory applyBussinessHistory = new ApplyBussinessHistory();
        applyBussinessHistory.setApplyId(noticeInfo.getNoticeId());
        applyBussinessHistory.setOperatorId(noticeInfo.getEnterPersonId());
        applyBussinessHistory.setOperatorTime(new Date());
        applyBussinessHistory.setOperatorTypeId("5");//4代表分析
        applyBussinessHistory.setOperatorName(noticeInfo.getAuditPersonName());
        applyBussinessHistory.setOperatorResult(noticeInfo.getNoticeInputStatus());//录入结果
        applyBussinessHistory.setOperatorDescription(noticeInfo.getRejectEnterReason());//录入意见
        return certificateApplyService.saveApplyBussinessOperatorHistory(applyBussinessHistory);
    }

    /**
     * 保存公告成功后调用工作流
     *
     * @param noticeInfo
     * @return
     */
    private boolean cellWorkFlowServerWhenReply(NoticeInfo noticeInfo) {
        try {
            Map<String, Object> variables = new HashMap<>();
            variables.put(VariableUtil.PRIORITY, noticeInfo.getPriority());
            variables.put("isReply", true);
            return completeWorkflowTask(noticeInfo.getNoticeId(), noticeInfo.getEnterPersonId(), noticeInfo.getWorkFlowId(), variables);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:Cell workflow server when add notice Call Exception" + e);
            return false;
        }
    }

    /**
     * 1:保存犯罪基本信息
     *
     * @param crimeInfo
     * @return
     */
    private CrimeEntity addCrimeInfoExchange(CrimeInfo crimeInfo) {
        try {
            CrimeEntity crimeEntity = crimeMapper.modelToEntity(crimeInfo);
            crimeEntity.setIsActive("0");
            crimeEntity.setEnteringTime(new Date());
            // 记录数据发生变更时间
            crimeEntity.setChangeTime(new Date());
            return crimeEntity;
        } catch (Exception e) {
            logger.error("addCrimeInfoExchange has error!", e);
            return null;
        }
    }

    /**
     * 2:保存犯罪人员基本信息(犯罪人员的Id为空进行保存  犯罪人员的id不为空进行更新)
     *
     * @param crimePersonInfo
     * @return
     */
    private CrimePersonInfoEntity addCrimePersonInfoExchange(CrimePersonInfo crimePersonInfo) {
        try {
            CrimePersonInfoEntity crimePersonInfoEntity = crimePersonMapper.modelToEntity(crimePersonInfo);
            crimePersonInfoEntity.setEnteringTime(new Date());
            // 记录数据发生变更时间
            crimePersonInfoEntity.setChangeTime(new Date());
            return crimePersonInfoEntity;
        } catch (Exception e) {
            logger.info("save crimePerson has error!", e);
            return null;
        }
    }

    /**
     * 3：保存公告关联附件信息(多条附件信息)
     *
     * @param attachmentInfo
     */
    private List<String> addNoticeAttachment(List<AttachmentInfo> attachmentInfo) {
        List<String> attchmentIdList = new ArrayList<>();
        if (CollectionUtils.isEmpty(attachmentInfo)) {
            return attchmentIdList;
        }
        try {
            for (AttachmentInfo item : attachmentInfo) {
                if (StringUtils.isEmpty(item.getAttachmentId())) {
                    AttachmentEntity attachmentEntity = new AttachmentEntity();
                    item.setAttachmentId(UUID.randomUUID().toString());
                    attachmentEntity.setAttachmentId(item.getAttachmentId());
                    attachmentEntity.setAttachmenttypeId(item.getAttachmenttypeId());
                    attachmentEntity.setFileFormatType(item.getFileFormatType());
                    attachmentEntity.setFileName(item.getFileName());
                    attachmentEntity.setFilePath(item.getFilePath());
                    attachmentEntity.setCreateTime(new Date());
                    attchmentIdList.add(attachmentEntity.getAttachmentId());
                    attachmentRepository.save(attachmentEntity);
                }
                attchmentIdList.add(item.getAttachmentId());
            }
            return attchmentIdList;
        } catch (Exception e) {
            logger.info("addNoticeAttachment has error!", e);
            return attchmentIdList;
        }
    }

    /**
     * 4：保存公告信息
     *
     * @param noticeInfo
     * @return
     */
    private NoticeEntity addNoticeInfoExchange(NoticeInfo noticeInfo) {
        try {
            noticeInfo.setEnteringTime(new Date());
            NoticeEntity noticeEntity = noticeMapper.modelToEntity(noticeInfo);
            noticeEntity.setEnteringTime(new Date());
            noticeEntity.setNoticeCreateTime(noticeInfo.getNoticeCreateTime() == null ? new Date() : noticeInfo.getNoticeCreateTime());
            noticeEntity.setStatus("1");
            noticeEntity.setIsActive("0");
            // 记录数据发生变更时间
            noticeEntity.setChangeTime(new Date());
            return noticeEntity;
        } catch (Exception e) {
            logger.info("save notice has error!", e);
            return null;
        }

    }


    /**
     * 获取公告详情
     *
     * @param noticeId
     * @return
     */
    @Override
    public CrimeAndNoticeExtendInfo findCrimeAndNoticeByNoticeId(String noticeId) {
        CrimeAndNoticeExtendInfo crimeAndNoticeExtendInfo = new CrimeAndNoticeExtendInfo();
        NoticeEntity noticeEntity = noticeRepository.findOneByNoticeId(noticeId);
        crimeAndNoticeExtendInfo.setNoticeInfo(noticeMapper.entityToModel(noticeEntity));
        if (noticeEntity != null) {
            crimeAndNoticeExtendInfo.setCrimePersonInfo(crimePersonMapper.entityToModel(noticeEntity.getCrimePersonInfoEntity()));
            crimeAndNoticeExtendInfo.setCrimeInfo(crimeMapper.entityToModel(noticeEntity.getCrimeEntity()));
        }
        if (noticeEntity != null && noticeEntity.getAttchmentId() != null) {
            List<AttachmentEntity> attachmentEntities = crimeRepository.getAttachmentListInfo(noticeEntity.getAttchmentId());
            crimeAndNoticeExtendInfo.setAttachmentInfo(attachmentMapper.entitiestoModels(attachmentEntities));
        }
        return crimeAndNoticeExtendInfo;
    }

    /**
     * 根据犯罪人员列表
     * 如果这个罪犯有合并操作，并且合并完成了，应该查询合并完成后目标罪犯下的公告
     *
     * @param crimePersonId
     * @return
     */
    @Override
    public CrimeAndNoticeListInfo getNoticeListByCrimePersonId(String crimePersonId) {
        CrimeAndNoticeListInfo crimeAndNoticeListInfo = new CrimeAndNoticeListInfo();
        //此处做修改 获取当前近50年的公告
        Date endDate = new Date();
        Date startDate = TimeUtil.getLateLyDate(searchYear);
        List<NoticeEntity> list = noticeRepository.findAllByCrimePersonIdAndTime(crimePersonId, startDate, endDate);
        NoticeEntity noticeEntity = getOneNoticeEntity(list);
        if (noticeEntity == null) {
            return crimeAndNoticeListInfo;
        }
        crimeAndNoticeListInfo.setNoticeInfo(noticeMapper.entityToModel(noticeEntity));
        crimeAndNoticeListInfo.setCrimeInfo(crimeMapper.entityToModel(noticeEntity.getCrimeEntity()));
        crimeAndNoticeListInfo.setNoticeInfoList(noticeMapper.entitiestoModels(list));
        if (StringUtils.isNoneBlank(noticeEntity.getAttchmentId())) {
            List<AttachmentEntity> attachmentEntities = crimeRepository.getAttachmentListInfo(noticeEntity.getAttchmentId());
            crimeAndNoticeListInfo.setAttachmentInfo(attachmentMapper.entitiestoModels(attachmentEntities));
        }
        return crimeAndNoticeListInfo;
    }
    //endregion
    // region 3 提交审核

    /**
     * 提交审核
     * 1、数据赋值
     * 2、保存审核数据
     * 3、调用工作流完成任务
     *
     * @param noticeInfo
     * @return
     */
    @Override
    public Map<String, Object> submitAudit(NoticeInfo noticeInfo) {
        boolean saveSuccess = true;
        Map<String, Object> map = new HashMap<>();
        NoticeEntity noticeEntity = noticeRepository.findOneByNoticeId(noticeInfo.getNoticeId());
        if (noticeEntity == null) {
            saveSuccess = false;
        }
        noticeEntity = exchangeAuditData(noticeInfo, noticeEntity);
        if (saveSuccess && deleteApplyAndAnalystRelationByNoticeId(noticeInfo.getNoticeId(), noticeInfo.getAuditPersonId()) && saveApplyBussinessHistoryWhenAudit(noticeEntity)) {
            if (StringUtils.equals("1", noticeInfo.getAuditResultId())) {
                // 审核通过
                saveSuccess = this.auditPassHandle(noticeEntity);
            } else if (StringUtils.equals("2", noticeInfo.getAuditResultId())) {
                // 审核不通过
                saveSuccess = this.auditNoPassHandle(noticeEntity);
            }
        }
        //保存审核数据成功后 调用工作流完成任务失败进行事务回滚
        if (saveSuccess && !cellWorkFlowServerWhenAudit(noticeEntity)) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            saveSuccess = false;
        }
        map.put(VariableUtil.SUCCESS, saveSuccess);
        map.put(VariableUtil.DATA, saveSuccess ? noticeReturnDataModelMapper.entityToModel(noticeEntity) : null);
        return map;
    }

    /**
     * 审核调用工作流
     *
     * @param noticeEntity
     * @return
     */
    private boolean cellWorkFlowServerWhenAudit(NoticeEntity noticeEntity) {
        try {
            Map<String, Object> variables = new HashMap<>();
            variables.put("priority", noticeEntity.getPriority());
            variables.put("auditresult", noticeEntity.getAuditResultId().equals(VariableUtil.AUDITOR_RESULT_PASS_ID) ? true : false);
            variables.put("isReply", noticeEntity.getAuditResultId().equals(VariableUtil.AUDITOR_RESULT_PASS_ID) ? false : true);
            return completeWorkflowTask(noticeEntity.getNoticeId(), noticeEntity.getAuditPersonId(), noticeEntity.getWorkFlowId(), variables);
        } catch (Exception e) {
            logger.error("Exception:submitAudit call Exception:" + e);
            // 完成工作流任务出现异常，手动回滚事务
            return false;
        }
    }
//endregion

    /**
     * 审核时数据赋值
     *
     * @param noticeInfo
     * @param noticeEntity
     * @return
     */
    private NoticeEntity exchangeAuditData(NoticeInfo noticeInfo, NoticeEntity noticeEntity) {
        // 记录数据发生变更的时间
        noticeEntity.setChangeTime(new Date());
        noticeEntity.setAuditPersonId(noticeInfo.getAuditPersonId());
        noticeEntity.setAuditPersonName(noticeInfo.getAuditPersonName());
        noticeEntity.setAuditTime(new Date());
        noticeEntity.setAuditDescription(noticeInfo.getAuditDescription());
        noticeEntity.setAuditResultId(noticeInfo.getAuditResultId());
        return noticeEntity;
    }

    /**
     * 保存操作历史 审核时
     *
     * @param noticeEntity
     * @return
     */
    private boolean saveApplyBussinessHistoryWhenAudit(NoticeEntity noticeEntity) {
        //4.保存操作历史
        ApplyBussinessHistory applyBussinessHistory = new ApplyBussinessHistory();
        applyBussinessHistory.setApplyId(noticeEntity.getNoticeId());
        applyBussinessHistory.setOperatorId(noticeEntity.getAuditPersonId());
        applyBussinessHistory.setOperatorTime(new Date());
        applyBussinessHistory.setOperatorTypeId("6");//3代表审核操作
        applyBussinessHistory.setOperatorResult(noticeEntity.getAuditResultId());//审核结果
        applyBussinessHistory.setOperatorName(noticeEntity.getAuditPersonName());
        applyBussinessHistory.setOperatorDescription(noticeEntity.getAuditDescription());//审核描述
        return certificateApplyService.saveApplyBussinessOperatorHistory(applyBussinessHistory);
    }

    /**
     * 提交审核的时候删除依赖关系
     * 此处做修改 若直接删除该业务下的所有业务分派关系,可能会把工作流分派的任务删除掉
     */
    private boolean deleteApplyAndAnalystRelationByNoticeId(String noticeId, String auditorId) {
        try {
            //删除依赖关系
            applyAndAnalystRelationRepository.deleteByApplyInfoIdAndAuditorId(noticeId, auditorId);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:delete  ApplyAndAnalystRelation by noticeId call Exception:" + e);
            return false;
        }
    }


    /**
     * 审核通过处理
     * 同意录入：
     * 1.将公告和犯罪，罪犯设为有效
     * 2.如果有合并操作,
     * a.将公告的罪犯改为新罪犯,同时设置原有罪犯列表失效，
     * b.同时删除待合并记录
     * 3.保存已合并罪犯和公告记录表
     * 4.记录合并拆分历史
     * 拒绝录入：
     * 1.如果有待合并，删除带合并列表
     * 2.同时将合并中的罪犯恢复isMerging=0;
     */
    public boolean auditPassHandle(NoticeEntity noticeEntity) {
        boolean isSuccess = true;
        try {
            if (noticeEntity != null) {
                WaitMergingCriminalRelationEntity waitMergingCriminalRelationEntity = findWaitMergeCriminalIdsByNoticeId(noticeEntity.getNoticeId());
                if ("1".equals(noticeEntity.getNoticeInputStatus())) {
                    return validateDataIsActiveWhenAudit(noticeEntity, waitMergingCriminalRelationEntity);
                } else {
                    // 审核通过  拒绝录入
                    noticeEntity.setStatus("4");
                    noticeRepository.save(noticeEntity);
                    isSuccess = resolveWaitMergingCriminalWhenAuditNoPass(waitMergingCriminalRelationEntity);
                }
            }
            return isSuccess;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:auditPassHandle call Exception:" + e);
            return false;
        }
    }

    /**
     * 审核时判断检测数据保存是否正确
     *
     * @param noticeEntity
     * @param waitMergingCriminalRelationEntity
     * @return
     */
    private boolean validateDataIsActiveWhenAudit(NoticeEntity noticeEntity, WaitMergingCriminalRelationEntity waitMergingCriminalRelationEntity) {
        if (setDataIsActiveWhenAudit(noticeEntity)) {
            if (waitMergingCriminalRelationEntity != null) {
                return resolveWaitMergingCriminalWhenAuditPass(noticeEntity, waitMergingCriminalRelationEntity);
            }
            return true;
        }
        return false;
    }

    /**
     * 审核通过 将公告 犯罪 犯罪人的数据设置为有效
     */
    private boolean setDataIsActiveWhenAudit(NoticeEntity noticeEntity) {
        CrimeEntity crimeEntity = noticeEntity.getCrimeEntity();
        CrimePersonInfoEntity crimePersonInfoEntity = noticeEntity.getCrimePersonInfoEntity();
        if (crimeEntity == null || crimePersonInfoEntity == null) {
            return false;
        }
        noticeEntity.setStatus("3");
        noticeEntity.setIsActive("1");
        crimeEntity.setChangeTime(new Date());
        crimeEntity.setIsActive("1");
        crimePersonInfoEntity.setIsActive("1");
        crimePersonInfoEntity.setChangeTime(new Date());
        return saveNoticeRelationData(crimeEntity, crimePersonInfoEntity, noticeEntity);
    }

    /**
     * 审核通过
     * 1. 删除待合并记录
     * 2、将公告列表中原有罪犯指定为新的罪犯
     * 3、保存已合并罪犯和公告记录表
     * 4.记录合并拆分历史
     * 5.同时将合并中的罪犯恢复isMerging=0;
     *
     * @return
     */
    private boolean resolveWaitMergingCriminalWhenAuditPass(NoticeEntity noticeEntity, WaitMergingCriminalRelationEntity waitMergingCriminalRelationEntity) {
        try {
            // 查询待合并列表
            List<String> criminalIds = StringAndListExchangeUtil.changeStringToList(waitMergingCriminalRelationEntity.getWaitCriminalIds());
            // 1删除待合并记录
            waitMergingCriminalRelationRepository.deleteByNoticeId(noticeEntity.getNoticeId());
            // a.通过罪犯ID获取公告ID集合
            if (!CollectionUtils.isEmpty(criminalIds)) {
                return saveDataWhenWaitCriminalIdsNotNull(noticeEntity, waitMergingCriminalRelationEntity, criminalIds);
            }
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:resolveWaitMergingCriminalWhenAuditPass call Exception:" + e);
            return false;
        }
    }

    /**
     * 待合并列表不为空时 记录合并拆分历史
     *
     * @param noticeEntity
     * @param waitMergingCriminalRelationEntity
     * @param criminalIds
     * @return
     */
    private boolean saveDataWhenWaitCriminalIdsNotNull(NoticeEntity noticeEntity, WaitMergingCriminalRelationEntity waitMergingCriminalRelationEntity, List<String> criminalIds) {
        List<Map<String, String>> crimeAndNotice = new ArrayList<>();
        for (String str : criminalIds) {
            Map<String, String> map = new HashMap<>();
            String noticeIds = getNoticeIdsByCriminalId(str);
            map.put(VariableUtil.CRIMINAL_NOTICEIDS, noticeIds);
            map.put(VariableUtil.CRIMINALID, str);
            crimeAndNotice.add(map);
        }
        // 3 将公告列表中原有罪犯指定为新的罪犯  4保存已合并罪犯和公告记录表
        if (!CollectionUtils.isEmpty(crimeAndNotice) && updateNoticeOwnerAndIsActiveCriminal(crimeAndNotice, noticeEntity.getCrimePersonId()) && saveMergedCriminal(noticeEntity.getNoticeId(), crimeAndNotice, noticeEntity.getCrimePersonId())) {
            return this.saveNoticeMergedAndSplitHistory(noticeEntity, waitMergingCriminalRelationEntity);// 5.记录合并拆分历史
        }
        return false;
    }

    /**
     * 保存合并拆分历史
     *
     * @return
     */
    private boolean saveNoticeMergedAndSplitHistory(NoticeEntity noticeEntity, WaitMergingCriminalRelationEntity waitMergingCriminalRelationEntity) {
        try {
            NoticeMergedAndSplitHistory noticeMergedAndSplitHistory = new NoticeMergedAndSplitHistory();
            noticeMergedAndSplitHistory.setAuditorId(noticeEntity.getAuditPersonId());
            noticeMergedAndSplitHistory.setMergedCriminalIds(waitMergingCriminalRelationEntity.getWaitCriminalIds());
            noticeMergedAndSplitHistory.setOperatorId(noticeEntity.getEnterPersonId());
            noticeMergedAndSplitHistory.setNoticeId(noticeEntity.getNoticeId());
            noticeMergedAndSplitHistory.setObjectCriminalId(waitMergingCriminalRelationEntity.getTargetCriminalId());
            noticeMergedAndSplitHistory.setOperatorTime(new Date());
            return this.saveNoticeMergeAndSplitHistory(noticeMergedAndSplitHistory);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:save notice mergedAnd splitHistory call Exception:" + e);
            return false;
        }
    }


    /**
     * 拒绝录入
     * 1.如果有待合并，删除带合并列表
     * 2.同时将合并中的罪犯恢复isMerging=0;
     * * @return
     */
    private boolean resolveWaitMergingCriminalWhenAuditNoPass(WaitMergingCriminalRelationEntity waitMergingCriminalRelationEntity) {
        try {
            if (waitMergingCriminalRelationEntity != null) {
                changeCriminalIsMergingStatus("0", waitMergingCriminalRelationEntity.getWaitCriminalIds());
            }
            //如果有待合并，删除待合并列表
            if (waitMergingCriminalRelationEntity != null) {
                waitMergingCriminalRelationRepository.delete(waitMergingCriminalRelationEntity);
            }
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("resolve wait merging has error when audit not Pass", e);
            return false;
        }

    }

    /**
     * 重新分析时 处理待合并
     * 更新待合并列表
     * 1 获取等待合并列表
     * 2.删除旧的等待合并列表
     * a 将旧的等待合并的数据列表isMerging=0(1代表正在合并 0合并完成或取消合并)
     * b 删除旧的待合并列表
     * 3.判断存在合并数据列表
     * a 存在 保存待合并列表
     * b 不存在 不做处理
     */
    private boolean resolveWaitCriminalWhenReply(String noticeId, List<String> waitCriminalIds, String crimePersonId) {
        try {
            // 1 获取等待合并列表
            WaitMergingCriminalRelationEntity waitMergingCriminalRelationEntity = waitMergingCriminalRelationRepository.findByNoticeId(noticeId);
            // 2 删除旧的待合并列表
            if (waitMergingCriminalRelationEntity != null) {
                // 将旧的等待合并列表中的罪犯设为合并取消
                this.changeCriminalIsMergingStatus("0", waitMergingCriminalRelationEntity.getWaitCriminalIds());
                //删除待合并列表
                waitMergingCriminalRelationRepository.deleteByNoticeId(noticeId);
            }
            //保存待合并数据
            return saveWaitCriminalData(waitCriminalIds, noticeId, crimePersonId);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:resolve wiatCriminal when reply notice  call Exception:" + e);
            return false;
        }
    }


    /**
     * 审核不通过处理
     * 1、更新公告状态设为重新分析(status=2)
     * 2、重新把公告分给归档员
     *
     * @param noticeEntity
     * @return
     */
    private boolean auditNoPassHandle(NoticeEntity noticeEntity) {
        try {
            //审核不通过驳回，归档员重新录入
            noticeEntity.setStatus("2");
            // 重新保存此条公告和归档员关联关系
            ApplyAndAnalystRelationEntity applyAndAnalystRelationEntity = new ApplyAndAnalystRelationEntity();
            applyAndAnalystRelationEntity.setRelationId(UUID.randomUUID().toString());
            applyAndAnalystRelationEntity.setAnalystId(noticeEntity.getEnterPersonId());
            applyAndAnalystRelationEntity.setApplyInfoId(noticeEntity.getNoticeId());
            applyAndAnalystRelationEntity.setAnalysisResultFail("1");//代表重新此条公告需要分析
            applyAndAnalystRelationEntity.setPriority(noticeEntity.getPriority());
            applyAndAnalystRelationEntity.setCreateTime(new Date());
            applyAndAnalystRelationEntity.setBusinessType("3");
            applyAndAnalystRelationRepository.save(applyAndAnalystRelationEntity);
            noticeRepository.save(noticeEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:auditNoPassHandle call Exception:" + e);
            return false;
        }
    }

    /**
     * 完成工作流任务
     *
     * @param assignee
     * @param workFlowId
     * @return
     */
    private boolean completeWorkflowTask(String businessKey, String assignee, String workFlowId, Map<String, Object> variables) {

        String url = workFlowUrl + "/task/complete/" + businessKey + "/" + assignee + "/" + workFlowId;
        boolean result = false;
        try {
            result = httpClientUtil.httpPost(url, variables, boolean.class, false);
        } catch (Exception e) {
            logger.error("Remote Server Exception:Complete Workflow Task Call Exception" + e);
        }
        return result;
    }

    /**
     * 获取最新的一条公告
     */
    private NoticeEntity getOneNoticeEntity(List<NoticeEntity> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new NoticeEntity();
        } else {
            return list.get(0);
        }
    }

    /**
     * 根据公告ID列表查询公告详情
     *
     * @param noticeIds
     * @return
     */
    @Override
    public List<CrimeAndNoticeExtendInfo> getNoticeDetailByNoticeIds(List<String> noticeIds) {
        List<CrimeAndNoticeExtendInfo> list = new ArrayList<>();
        if (!noticeIds.isEmpty()) {
            noticeIds.forEach(id -> list.add(getCrimeAndNoticeExtendInfo(noticeRepository.getByNoticeId(id))));
        }
        return list;
    }

    /**
     * 通过公告关联的附件ID获取附件列表
     *
     * @param ids
     * @return
     */
    @Override
    public List<AttachmentInfo> findAttachmentByIds(String ids) {
        List<String> idList = changeStringArrayToList(ids);
        if (!CollectionUtils.isEmpty(idList)) {
            return attachmentMapper.entitiestoModels(attachmentRepository.getAllByAttachmentId(idList));
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 根据公告Id列表获取公告列表分页
     *
     * @param ids
     * @return
     */
    @Override
    public ReturnBase<List<CrimeAndNoticeExtendInfo>> getNoticeByNoticeIdList(List<String> ids, int pages, int pageSize) {
        ReturnBase<List<CrimeAndNoticeExtendInfo>> returnBase = new ReturnBase<>();
        PageRequest pageRequest = new PageRequest(pages, pageSize);
        Page<NoticeEntity> page = getNoticeByNoticeIdListPage(pageRequest, ids);
        if (page != null) {
            return geNoticeIdListExchange(page);
        }
        return returnBase;
    }

    /**
     * 查询
     *
     * @param pageRequest
     * @param ids
     * @return
     */
    private Page<NoticeEntity> getNoticeByNoticeIdListPage(PageRequest pageRequest, List<String> ids) {
        Specification<NoticeEntity> specification = (Root<NoticeEntity> entityRoot, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = predicatesExChange(ids, entityRoot, cb);
            Predicate[] predicate = new Predicate[predicates.size()];
            return cb.and(predicates.toArray(predicate));
        };
        return noticeRepository.findAll(specification, pageRequest);
    }

    /**
     * 转化查询列表
     *
     * @param page
     * @return
     */
    private ReturnBase<List<CrimeAndNoticeExtendInfo>> geNoticeIdListExchange(Page<NoticeEntity> page) {
        ReturnBase<List<CrimeAndNoticeExtendInfo>> returnBase = new ReturnBase<>();
        try {
            List<NoticeEntity> noticeEntityList = page.getContent();
            returnBase.setData(getCrimeAndNoticeExtendInfoList(noticeEntityList));
            returnBase.setSuccess(true);
            returnBase.setTotalCount(page.getTotalElements());
            returnBase.setTotalPage(page.getTotalPages());
            return returnBase;
        } catch (Exception e) {
            logger.error("Exception: Find Notice By Notice's Id List Call Exception" + e);
            returnBase.setSuccess(false);
            returnBase.setError(new Error(500, VariableUtil.SERVER_INNER_EXCEPTION));
            return returnBase;
        }
    }

    /**
     * 根据 公告列表信息 获取犯罪信息和犯罪人 附件 对象列表
     *
     * @param noticeEntityList
     * @return
     */
    private List<CrimeAndNoticeExtendInfo> getCrimeAndNoticeExtendInfoList(List<NoticeEntity> noticeEntityList) {
        List<CrimeAndNoticeExtendInfo> list = new ArrayList<>();
        if (!noticeEntityList.isEmpty()) {
            for (NoticeEntity noticeEntity : noticeEntityList) {
                list.add(getCrimeAndNoticeExtendInfo(noticeEntity));
            }
        }
        return list;
    }

    /**
     * 根据 公告列表信息 获取犯罪信息和犯罪人 附件 单个对象
     *
     * @param noticeEntity
     * @return
     */
    private CrimeAndNoticeExtendInfo getCrimeAndNoticeExtendInfo(NoticeEntity noticeEntity) {
        CrimeAndNoticeExtendInfo crimeAndNoticeExtendInfo = new CrimeAndNoticeExtendInfo();
        if (StringUtils.isEmpty(noticeEntity.getCrimeId()) || StringUtils.isEmpty(noticeEntity.getCrimePersonId())) {
            return crimeAndNoticeExtendInfo;
        }
        crimeAndNoticeExtendInfo.setNoticeInfo(noticeMapper.entityToModel(noticeEntity));
        crimeAndNoticeExtendInfo.setCrimeInfo(crimeMapper.entityToModel(crimeRepository.getByCrimeId(noticeEntity.getCrimeId())));
        crimeAndNoticeExtendInfo.setCrimePersonInfo(crimePersonMapper.entityToModel(crimePersonRepository.getByCrimePersonId(noticeEntity.getCrimePersonId())));
        String attachmentId = noticeEntity.getAttchmentId();
        crimeAndNoticeExtendInfo.setAttachmentInfo(findAttachmentByIds(attachmentId));
        return crimeAndNoticeExtendInfo;
    }

    private List<Predicate> predicatesExChange(List<String> ids, Root<NoticeEntity> entityRoot, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder.In<String> in = cb.in(entityRoot.get("noticeId"));
        if (!ids.isEmpty()) {
            for (String id : ids) {
                in.value(id);
            }
        }
        predicates.add(in);
        return predicates;
    }

    /**
     * 将以逗号分隔的字符串转为list
     *
     * @param id
     * @return
     */
    private List<String> changeStringArrayToList(String id) {
        List<String> idList = new ArrayList<>();
        if (!StringUtils.isBlank(id)) {
            String[] idArray = id.split(",");
            idList = Arrays.asList(idArray);
        }
        return idList;
    }

    /**
     * 分派后数据保存
     * 保存指定的分析员
     *
     * @param noticeApportion
     * @return
     */
    @Override
    public boolean saveNoticeApportion(NoticeApportion noticeApportion) {
        try {
            NoticeEntity noticeEntity = noticeRepository.findOneByNoticeId(noticeApportion.getNoticeId());
            if (noticeEntity == null) {
                return false;
            }
            noticeEntity.setAuditPersonId(noticeApportion.getAuditorId());
            noticeRepository.save(noticeEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:save notice Apportion call Exception:" + e);
            return false;
        }
    }

    /**
     * 公告编辑优先级
     *
     * @param noticeInfo
     * @return
     */
    @Override
    public boolean editPriority(NoticeInfo noticeInfo) {
        try {
            NoticeEntity noticeEntity = noticeRepository.findOneByNoticeId(noticeInfo.getNoticeId());
            if (noticeEntity == null) {
                return false;
            }
            noticeEntity.setPriority(noticeInfo.getPriority());
            // 记录数据发生变化时间
            noticeEntity.setChangeTime(new Date());
            noticeRepository.save(noticeEntity);
            // 更新等待分派的优先级
            waitApportionRepository.updatePriorityByNoticeId(noticeInfo.getNoticeId(), noticeInfo.getPriority(), "3");
            //更新分派关系之后的优先级
            applyAndAnalystRelationRepository.updatePriorityByApplyId(noticeInfo.getNoticeId(), noticeInfo.getPriority());
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:Edit Priority  has error! call Exception:" + e);
            return false;
        }

    }

    /**
     * 启动工作流流程，
     * 前提：申请人员有犯罪记录
     *
     * @param
     * @return
     */
    private String startProcessByKey(String applyId, String operatorId, String priority) {
        String workFlowId = "";
        Map<String, Object> variables = new HashMap<>();
        variables.put("operator", operatorId);
        variables.put("priority", priority);
        try {
            String url = workFlowUrl + "/process/start?processKey=" + noticeProcess + "&bussinessKey=" + applyId;
            workFlowId = httpClientUtil.httpPost(url, variables, String.class, false);
        } catch (Exception e) {
            logger.error("Remote Server Exception:Start WorkFlow Call Exception" + e);
        }
        return workFlowId;
    }

    /**
     * 根据ID获取公告
     *
     * @param id
     * @return
     */
    @Override
    public NoticeInfo getNoticeById(String id) {
        return noticeMapper.entityToModel(noticeRepository.getByNoticeId(id));
    }

    /**
     * 根据公告id获取犯罪审核列表
     * 1、公告信息
     * 2、当前罪犯
     * 3 犯罪信息
     * 3、附件信息
     * 4、待合并数据集合
     *
     * @param noticeId
     * @return
     */
    @Override
    public CrimeAndNoticeExtendInfo findAuditDetailByNoticeId(String noticeId) {
        CrimeAndNoticeExtendInfo crimeAndNoticeExtendInfo = new CrimeAndNoticeExtendInfo();
        NoticeEntity noticeEntity = noticeRepository.findOneByNoticeId(noticeId);
        if (noticeEntity == null) {
            return crimeAndNoticeExtendInfo;
        }
        crimeAndNoticeExtendInfo = getCrimeAndNoticeExtendInfo(noticeEntity);
        //待合并罪犯集合
        WaitMergingCriminalRelationEntity waitMergingCriminalRelationEntity = waitMergingCriminalRelationRepository.findByNoticeId(noticeEntity.getNoticeId());
        if (waitMergingCriminalRelationEntity != null && StringUtils.isNoneBlank(waitMergingCriminalRelationEntity.getWaitCriminalIds())) {
            List<String> waitMergingCriminalList = Arrays.asList(waitMergingCriminalRelationEntity.getWaitCriminalIds().split(","));
            crimeAndNoticeExtendInfo.setWaitCriminalIds(waitMergingCriminalList);
        }
        return crimeAndNoticeExtendInfo;
    }

    /**
     * 保存待合并罪犯关系
     *
     * @param noticeId
     * @param waitCriminalIds
     * @param targetCriminalId
     * @return
     */
    private boolean saveWaitMergingCriminalRelation(String noticeId, String waitCriminalIds, String targetCriminalId) {
        try {
            WaitMergingCriminalRelationEntity waitMergingCriminalRelationEntity = new WaitMergingCriminalRelationEntity();
            waitMergingCriminalRelationEntity.setId(UUID.randomUUID().toString());
            waitMergingCriminalRelationEntity.setNoticeId(noticeId);
            waitMergingCriminalRelationEntity.setWaitCriminalIds(waitCriminalIds);
            waitMergingCriminalRelationEntity.setCreateTime(new Date());
            waitMergingCriminalRelationEntity.setTargetCriminalId(targetCriminalId);
            waitMergingCriminalRelationRepository.save(waitMergingCriminalRelationEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:saveWaitMergingCriminalRelation call Exception:" + e);
            return false;
        }
    }

    /**
     * 保存已合并罪犯关联关系
     *
     * @param noticeId
     * @param criminalAndNoticeIds
     * @return
     */
    private boolean saveMergedCriminal(String noticeId, List<Map<String, String>> criminalAndNoticeIds, String objectCriminalId) {
        try {
            if (!CollectionUtils.isEmpty(criminalAndNoticeIds)) {
                for (Map<String, String> map : criminalAndNoticeIds) {
                    MergedCriminalEntity mergedCriminalEntity = new MergedCriminalEntity();
                    mergedCriminalEntity.setId(UUID.randomUUID().toString());
                    mergedCriminalEntity.setNoticeId(noticeId);
                    mergedCriminalEntity.setCriminalId(map.get("criminalId"));
                    mergedCriminalEntity.setCreateTime(new Date());
                    mergedCriminalEntity.setCriminalNoticeIds(map.get("criminalNoticeIds"));
                    mergedCriminalEntity.setObjectCriminalId(objectCriminalId);
                    mergedCriminalRepsitory.save(mergedCriminalEntity);
                }
            }
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:saveMergedCriminal call Exception:" + e);
            return false;
        }
    }

    /**
     * 通过罪犯ID获取公告ID集合
     *
     * @param criminalId
     * @return
     */
    private String getNoticeIdsByCriminalId(String criminalId) {
        String noticeIds = "";

        if (StringUtils.isNotBlank(criminalId)) {
            List<NoticeEntity> list = noticeRepository.findAllByCrimePersonId(criminalId);
            List<String> ids = new ArrayList<>();
            if (!list.isEmpty()) {
                for (NoticeEntity noticeEntity : list) {
                    ids.add(noticeEntity.getNoticeId());
                }
                noticeIds = StringAndListExchangeUtil.changeIdListToString(ids);
            }
        }
        return noticeIds;
    }

    /**
     * 批量更新公告和罪犯的关联关系，同时将罪犯设置为不可用
     *
     * @param crimeAndNotice
     * @return
     */
    private boolean updateNoticeOwnerAndIsActiveCriminal(List<Map<String, String>> crimeAndNotice, String targetCriminalId) {
        try {
            for (Map<String, String> map : crimeAndNotice) {
                List<String> noticeIds = StringAndListExchangeUtil.changeStringToList(map.get("criminalNoticeIds"));
                String criminalId = map.get("criminalId");
                CrimePersonInfoEntity crimePersonInfoEntity = crimePersonRepository.getByCrimePersonId(criminalId);
                if (crimePersonInfoEntity != null) {
                    crimePersonInfoEntity.setIsActive("0");
                    crimePersonInfoEntity.setIsMerging("0");
                    crimePersonRepository.save(crimePersonInfoEntity);
                }
                // 批量更新公告的所属罪犯()
                if (!CollectionUtils.isEmpty(noticeIds)) {
                    noticeRepository.updateNoticeCrimePersonId(targetCriminalId, noticeIds);
                }
            }
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:updateNoticeOwnerAndIsActiveCriminal call Exception:" + e);
            return false;
        }
    }

    /**
     * 保存合并和拆分历史
     *
     * @param noticeMergedAndSplitHistory
     * @return
     */
    private boolean saveNoticeMergeAndSplitHistory(NoticeMergedAndSplitHistory noticeMergedAndSplitHistory) {
        try {
            NoticeMergedAndSplitHistoryEntity noticeMergedAndSplitHistoryEntity = noticeMergedAndSplitHistoryMapper.modelToEntity(noticeMergedAndSplitHistory);
            noticeMergedAndSplitHistoryEntity.setId(UUID.randomUUID().toString());
            noticeMergedAndSplitHistoryRepository.save(noticeMergedAndSplitHistoryEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:saveNoticeMergeAndSplitHistory call Exception:" + e);
            return false;
        }
    }

    /**
     * 根据公告Id获取待合并罪犯数据
     *
     * @param noticeId * @return
     */
    private WaitMergingCriminalRelationEntity findWaitMergeCriminalIdsByNoticeId(String noticeId) {
        return waitMergingCriminalRelationRepository.findByNoticeId(noticeId);
    }

    /**
     * 批量更新罪犯的待合并状态
     *
     * @param isMerging
     * @param waitCriminalIds
     * @return
     */
    private boolean changeCriminalIsMergingStatus(String isMerging, String waitCriminalIds) {
        try {
            if (StringUtils.isNotBlank(waitCriminalIds)) {
                crimePersonRepository.updateCrimePersonIsMergingStatus(isMerging, StringAndListExchangeUtil.changeStringToList(waitCriminalIds));
            }
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:changeCriminalIsMergingStatus call Exception:" + e);
            return false;
        }
    }
}
