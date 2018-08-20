package com.gs.crms.applycertify.service.serviceimpl;

import com.gs.crms.applycertify.contract.model.*;
import com.gs.crms.applycertify.contract.model.admin.ApplyInfoParam;
import com.gs.crms.applycertify.contract.model.search.ApplyPersonQuery;
import com.gs.crms.applycertify.contract.service.CertificateApplyService;
import com.gs.crms.applycertify.service.datamappers.*;
import com.gs.crms.applycertify.service.entity.*;
import com.gs.crms.applycertify.service.entity.history.ApplyBussinessHistoryEntity;
import com.gs.crms.applycertify.service.repository.*;
import com.gs.crms.common.enums.AttachmentEntity;
import com.gs.crms.common.model.Error;
import com.gs.crms.common.model.ReturnBase;
import com.gs.crms.common.repository.AttachmentRepository;
import com.gs.crms.common.utils.*;
import com.gs.crms.common.model.AttachmentInfo;
import com.gs.crms.crimenotice.contract.model.CrimePersonInfo;
import com.gs.crms.crimenotice.contract.model.CrimeRecordPrint;
import com.gs.crms.crimenotice.contract.service.CrimeSearchService;

import com.gsafety.springboot.common.utils.HttpClientUtil;

import com.gsafety.springboot.common.utils.JsonUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
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
 * 申请业务Service实现
 * Created by zhangqiang on 2017/8/22.
 */
@Service
@Transactional
public class CertificateApplyServiceImpl implements CertificateApplyService {

    @Autowired
    private ApplyInfoRepository applyInfoRepository;

    @Autowired
    private ApplyBasicInfoRepository applyBasicInfoRepository;

    @Autowired
    private ApplyAndNoticeRelationRepository applyAndNoticeRelationRepository;

    @Autowired
    private ApplyAndNoticeRelationMapper applyAndNoticeRelationMapper;

    @Autowired
    private ApplyInfoMapper applyInfoMapper;

    @Autowired
    private ApplyBasicMapper applyBasicMapper;

    @Autowired
    private AttachmentInfoMapper attachmentInfoMapper;

    @Autowired
    private ApplyBussinessHistoryMapper applyBussinessHistoryMapper;

    @Autowired
    private AttachmentRepository attchmentInfoRepository;

    @Autowired
    private CrimeSearchService crimeSearchService;

    @Autowired
    private ApplyAndAnalystRelationRepository applyAndAnalystRelationRepository;
    @Autowired
    private ApplyAndAnalystRelationMapper applyAndAnalystRelationMapper;

    @Autowired
    private ApplyBussinessHistoryRepository applyBussinessHistoryRepository;


    @Autowired
    private WaitApportionRepository waitApportionRepository;
    @Autowired
    private ApplyReturnDataModelMapper applyReturnDataModelMapper;


    @Autowired
    private HttpClientUtil httpClientUtil;

    @Value("${peopleLiberary.baseUrl}")
    private String peopleLiberaryUrl;

    @Value("${workflow.baseUrl}")
    private String workFlowUrl;//工作流服务地址

    @Value("${process.personalApply}")
    private String personProcessKey;//个人申请流程Key

    @Value("${process.governmentApply}")
    private String governmentProcessKey;//政府申请流程key

    /**
     * 是否启用接口
     **/
    @Value("${citizen.enable}")
    private boolean citizenEnable;

    /**
     * 根据身份证号查询URL
     **/
    @Value("${citizen.byId_url}")
    private String citizenById;
    /**
     * 根据名称查询URL
     **/
    @Value("${citizen.byName_url}")
    private String citizenByName;

    /**
     * 共享交换系统分配的用户名
     **/
    @Value("${citizen.exc_user}")
    private String excUser = "sec007";
    @Value("${citizen.certificateType}")

    private String certificateType;//证书类型
    @Value("${citizen.certificateTypeName}")

    private String certificateTypeName;//证书类型名称
    @Value("${citizen.isByCrime}")

    private boolean isByCrime;//查询模式
    @Value("${search.isCompleteList}")
    private String isCompleteList;//完成查询的申请状态

    @Value("${search.isUnCompleteList}")
    private String isUnCompleteList;//未完成查询的申请状态

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 保存申请（个人申请，政府申请）
     * 根据申请类型保存不同申请
     * 2017-11-3 修改为  判断系统是否可能存在该犯罪人
     * 流程
     * 1、设置申请id（applyId） 和 申请中犯罪记录的字段
     * a 设置申请id
     * b 根据证件和姓名 查询是否存在犯罪人
     * 若存在：
     * 1、将犯罪记录设为有犯罪(crimeRecord=1)
     * 2、将犯罪的申请状态设为已提交(applyStatusId=0)
     * 不存在
     * 1、将犯罪记录设为无犯罪(crimeRecord=0)
     * 2、申请状态设为待打印(applyStatusId=3)
     * 3、将申请结果设为无犯罪记录(ApplyResultId=1)
     * 4、将申请结果时间改为当前时间
     * 2、保存数据  保存申请信息和申请基本信息
     * a、applyInfo(申请表)
     * b  applyBaseInfo(申请基本表)
     * c  AttachmentInfo(附件表)
     * d  ApplyBussinessHistory(操作历史)
     * 3、 数据保存成功后并且存在犯罪记录才可调用工作流
     * 工作流调用失败事务回滚
     * * 2、保存数据  保存申请信息和申请基本信息
     * a、applyInfo(申请)  applyBaseInfo(申请人基本信息)  AttachmentInfo(附件)  操作历史
     * 3、2 操作成功后并且存在犯罪记录才可调用工作流
     * 工作流调用失败事务回滚
     *
     * @param certificateApplyInfo
     * @return
     */
    //region 1 申请保存
    @Override
    public ApplyInfo saveApplyInfo(CertificateApplyInfo certificateApplyInfo) {
        ApplyInfo applyInfo = certificateApplyInfo.getApplyInfo();
        ApplyBasicInfo applyBasicInfo = certificateApplyInfo.getApplyBasicInfo();
        applyInfo.setApplyId(UUID.randomUUID().toString());
        // 通过身份证或者姓名查询到犯罪嫌疑人
        List<CrimePersonInfo> list = findApplyPersonIsHasCrimeRecord(applyBasicInfo);
        if (!list.isEmpty()) {
            applyInfo.setCrimeRecord(1);
            //有犯罪记录，将申请状态设置为0：已提交
            applyInfo.setApplyStatusId(StringUtils.equals("2",applyInfo.getApplyTypeId())?"2":"0");
        } else {
            // 申请结果为无犯罪记录
            applyInfo.setCrimeRecord(0);
            applyInfo.setApplyStatusId("3");
            applyInfo.setApplyResultId("1");
            applyInfo.setApplyResultTime(new Date());
        }
        // 1保存申请的基本信息
        boolean saveSuccess = saveApplyInfoRelation(applyInfo, applyBasicInfo, certificateApplyInfo.getAttachmentInfoList(), certificateApplyInfo.getApplyAndCriminalRelation());
        // 3调用工作流
        if (saveSuccess && applyInfo.getCrimeRecord() == 1) {
            saveSuccess = callWorkFlowServer(applyInfo);
        }
        if (saveSuccess) {
            return applyInfo;
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    /**
     * 保存申请相关的基本信息
     * 1、保存申请人基本信息
     * 2、保存申请附件信息
     * 3、保存申请信息
     * 4、保存犯罪人和申请的关联关系
     * 个人申请 不保存
     * 政府申请 只有客户端勾选后才保存
     * 5、保存操作历史
     *
     * @param applyInfo          申请信息
     * @param applyBasicInfo     申请人信息
     * @param attachmentInfoList 附件
     * @return
     */

    private boolean saveApplyInfoRelation(ApplyInfo applyInfo, ApplyBasicInfo applyBasicInfo, List<AttachmentInfo> attachmentInfoList, ApplyAndCriminalRelation applyAndCriminalRelation) {
        try {
            boolean result = true;
            // 1、保存申请人基本信息
            String applyBasicInfoId = addApplyBasicInfo(applyBasicInfo);
            // 2、保存申请附件信息
            List<String> attachmentIDList = addApplyAttachment(attachmentInfoList);
            applyInfo.setApplyBasicId(applyBasicInfoId);
            applyInfo.setAttchmentId(StringAndListExchangeUtil.changeIdListToString(attachmentIDList));
            // 3.保存申请
            ApplyInfoEntity applyInfoEntity = addApplyInfo(applyInfo);
            if (applyInfoEntity == null) {
                return false;
            }
            applyInfo.setApplyId(applyInfoEntity.getApplyId());
            // 4.保存犯罪人和申请的关联关系(政府申请调用)
            if (StringUtils.equals("2", applyInfo.getApplyTypeId()) && applyAndCriminalRelation != null && StringUtils.isNoneBlank(applyAndCriminalRelation.getCriminalId())) {
                result = relationGovernmentApplyAndNotice(applyInfo.getApplyId(), applyAndCriminalRelation);
            }
            return result ? saveApplyBusinessHistory(applyInfo) : result;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:save ApplyInfo and applyBaseInfo and attachmentInfoList  Call Exception" + e);
            return false;
        }
    }

    /**
     * (申请录入)保存操作历史
     *
     * @param applyInfo
     * @return
     */
    private boolean saveApplyBusinessHistory(ApplyInfo applyInfo) {
        ApplyBussinessHistory applyBussinessHistory = new ApplyBussinessHistory();
        applyBussinessHistory.setApplyId(applyInfo.getApplyId());
        applyBussinessHistory.setOperatorId(applyInfo.getEnteringPersonId());
        applyBussinessHistory.setOperatorName(applyInfo.getEnteringPersonName());
        // 如果是政府申请，需要保存分析结果等信息
        if (StringUtils.equals("2", applyInfo.getApplyTypeId())) {
            applyBussinessHistory.setOperatorResult(applyInfo.getAnalysisResultId());
            applyBussinessHistory.setOperatorDescription(applyInfo.getAnalysisDescription());
        }
        applyBussinessHistory.setOperatorTime(new Date());
        applyBussinessHistory.setOperatorTypeId("1");
        return saveApplyBussinessOperatorHistory(applyBussinessHistory);
    }

    /**
     * 3 调用工作流
     * a 调用成功
     * 更新申请字段中的workFlowId
     * 判断当前任务是否已经分派出去 分派出去的话更新审核状态为待分析
     * 若保存申请失败
     * 删除等待分派表和分派关联表
     * b 调用成功
     * 返回false
     *
     * @param applyInfo
     * @return
     */
    private boolean callWorkFlowServer(ApplyInfo applyInfo) {
        // 调用工作流
        String workFlowId = startProcessByKey(applyInfo.getApplyId(), applyInfo.getApplyTypeId(), applyInfo.getEnteringPersonId(), applyInfo.getPriority());
        if (StringUtils.isBlank(workFlowId)) {
            return false;
        } else {
            ApplyInfoEntity applyInfoEntity = applyInfoMapper.modelToEntity(applyInfo);
            List<ApplyAndAnalystRelationEntity>list=applyAndAnalystRelationRepository.findAllByApplyInfoId( applyInfo.getApplyId());
            if(!CollectionUtils.isEmpty(list)){
                applyInfoEntity.setApplyStatusId(StringUtils.equals("1",applyInfo.getApplyTypeId())?"1":"2");
            }
            // 更新申请表将工作流ID插入到申请表中
            applyInfoEntity.setWorkflowId(workFlowId);
            try {
                applyInfoRepository.save(applyInfoEntity);
            } catch (Exception e) {
                logger.error("Remote Server Exception:start workflow has error", e);
                deleteUserRelationAndWaitApportion(applyInfoEntity.getApplyId());
                return false;
            }
        }
        return true;
    }

    /**
     * 删除关联关系和等待分派
     *
     * @param applyInfoId
     */
    private void deleteUserRelationAndWaitApportion(String applyInfoId) {
        try {
            if (!StringUtils.isBlank(applyInfoId)) {
                ApplyAndAnalystRelationEntity applyAndAnalystRelationEntity = applyAndAnalystRelationRepository.findByApplyInfoId(applyInfoId);
                if (applyAndAnalystRelationEntity != null) {
                    applyAndAnalystRelationRepository.delete(applyAndAnalystRelationEntity);
                }
                WaitApportionEntity waitApportionEntity = waitApportionRepository.findByApplyInfoId(applyInfoId);
                if (waitApportionEntity != null) {
                    waitApportionRepository.delete(waitApportionEntity);
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:Delete The Relation Between Operator Call Exception" + e);
        }
    }

    /**
     * 政府申请关联犯罪嫌疑人
     */
    private boolean relationGovernmentApplyAndNotice(String applyInfoId, ApplyAndCriminalRelation applyAndCriminalRelation) {
        try {
            //政府申请，关联手动勾选后的公告
            if (applyAndCriminalRelation != null) {
                ApplyAndCriminalRelationEntity entity = new ApplyAndCriminalRelationEntity();
                entity.setRelationId(UUID.randomUUID().toString());
                entity.setApplyInfoId(applyInfoId);
                entity.setCriminalId(applyAndCriminalRelation.getCriminalId());
                applyAndNoticeRelationRepository.save(entity);
            }
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception: save relationGovernmentApply when input government Call Exception" + e);
            return false;
        }

    }

    /**
     * 1、保存申请人基本信息
     * a.如果犯罪数据库存在此人，说明是从申请库回填的人，执行更新操作；
     * b.如果犯罪数据库不存在此人，说明是手动填写的人或者是从人口库获取的人，需要根据身份证判断是否有此人；
     *
     * @param applyBasicInfo the apply basic info
     * @return string
     */
    private  String addApplyBasicInfo(ApplyBasicInfo applyBasicInfo) {
        try {
            ApplyBasicInfoEntity applyBasicInfoEntity = applyBasicMapper.modelToEntity(applyBasicInfo);
            applyBasicInfoEntity.setApplyBasicId(UUID.randomUUID().toString());
            applyBasicInfoEntity.setCreateTime(new Date());
            // 数据发生变更时间
            applyBasicInfoEntity.setChangeTime(new Date());
            applyBasicInfoRepository.save(applyBasicInfoEntity);
            return applyBasicInfoEntity.getApplyBasicId();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:addApplyBasicInfo call exception" + e);
            return null;
        }
    }


    /**
     * 3.保存申请基本信息
     *
     * @param applyInfo the apply info
     * @return string
     */
    private  ApplyInfoEntity addApplyInfo(ApplyInfo applyInfo) {
        applyInfo.setApplyTime(new Date());
        applyInfo.setChangeTime(new Date());
        ApplyInfoEntity applyInfoEntity = applyInfoMapper.modelToEntity(applyInfo);
        try {
            applyInfoRepository.save(applyInfoEntity);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:Save Apply Personal Information Call Exception" + e);
        }
        return applyInfoEntity;
    }

    /**
     * 2.保存申请关联附件信息(多条附件信息)
     *
     * @param attachmentInfo
     */
    private List<String> addApplyAttachment(List<AttachmentInfo> attachmentInfo) {
        List<String> attachmentIdList = new ArrayList<>();
        try {

            if (attachmentInfo != null && !attachmentInfo.isEmpty()) {
                for (AttachmentInfo attachment : attachmentInfo) {
                    AttachmentEntity entity = new AttachmentEntity();
                    entity.setAttachmentId(UUID.randomUUID().toString());
                    entity.setAttachmenttypeId(attachment.getAttachmenttypeId());
                    entity.setFileFormatType(attachment.getFileFormatType());
                    entity.setFileName(attachment.getFileName());
                    entity.setFilePath(attachment.getFilePath());
                    entity.setCreateTime(new Date());
                    attachmentIdList.add(entity.getAttachmentId());
                    attchmentInfoRepository.save(entity);
                }
            }

        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception: Save Apply Attachment Call Exception" + e);
        }

        return attachmentIdList;
    }
    //endregion
    //region 2 提交分析结果

    /**
     * 分析席：提交分析结果，更新申请信息
     * 流程
     * 1、保存申请信息
     * 更新申请中的状态 设为审核中(applyStatusId=2)
     * 更新分析时间、分析人、变化时间、分析名称 分析结果、分析描述、修改人、修改时间
     * 保存操作历史
     * 2、删除分派关联关系 更新申请与犯罪关联关系
     * a 删除分派关联关系
     * 首次分析：直接删除
     * 重新分析：先删除分派关联关系 然后重新分配给审核员
     * 3、调用工作流
     * 调用失败 事务回滚
     *
     * @param certificateApplyInfo
     * @return
     */
    @Override
    public Map<String,Object> submitAnalysisResult(CertificateApplyInfo certificateApplyInfo) {
        Map<String,Object>map=new HashMap<>();
        ApplyInfo applyInfo = certificateApplyInfo.getApplyInfo();
        ApplyInfoEntity applyInfoEntity = applyInfoRepository.getByApplyId(applyInfo.getApplyId());
        if (applyInfoEntity == null) {
            map.put(VariableUtil.SUCCESS,false);
            return map;
        }
        applyInfo.setWorkflowId(applyInfoEntity.getWorkflowId());
        //1保存申请信息
        boolean result = saveApplyWhenAnalyst(applyInfo);
        //2 删除关联关系和更新公告
        if (result && deleteApplyAndAnalystRelationData(applyInfo, StringUtils.isNotBlank(applyInfo.getAuditResultId())) && updateApplyInfoAndNoticesRelations(applyInfo.getApplyId(), certificateApplyInfo.getApplyAndCriminalRelation())) {
            //3 调用工作流
            result = cellWorkflowServerWhenAnalyst(applyInfo,StringUtils.isNotBlank(applyInfo.getAuditResultId()));
        }
        if (!result) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        map.put(VariableUtil.SUCCESS, result);
        map.put("data", result ? applyReturnDataModelMapper.entityToModel(applyInfoEntity) : null);
        return map;
    }


    /**
     * 保存申请分析结果
     *
     * @param applyInfo
     * @return
     */
    private boolean saveApplyWhenAnalyst(ApplyInfo applyInfo) {
        try {
            ApplyInfoEntity applyInfoEntity = applyInfoRepository.getByApplyId(applyInfo.getApplyId());
            if (applyInfoEntity == null) {
                return false;
            }
            //1.更新申请中的分析结果等信息
            applyInfoEntity.setAnalysisDescription(applyInfo.getAnalysisDescription());
            applyInfoEntity.setAnalysisPersonId(applyInfo.getAnalysisPersonId());
            applyInfoEntity.setAnalysisPersonName(applyInfo.getAnalysisPersonName());
            applyInfoEntity.setAnalysisResultId(applyInfo.getAnalysisResultId());
            applyInfoEntity.setModifyPersonName(applyInfo.getAnalysisPersonName());
            applyInfoEntity.setModifyTime(new Date());
            applyInfoEntity.setAnalysisTime(new Date());
            applyInfoEntity.setApplyStatusId("2");
            // 数据发生变更时间
            applyInfoEntity.setChangeTime(new Date());
            applyInfoRepository.save(applyInfoEntity);
            return saveApplyBusHistoryWhenAnalyst(applyInfo);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Remote Server Exception:save application analyst result", e);
            return false;
        }
    }

    /**
     * 分析时 保存操作历史
     *
     * @param applyInfo
     * @return
     */
    private boolean saveApplyBusHistoryWhenAnalyst(ApplyInfo applyInfo) {
        try {
            ApplyBussinessHistory applyBussinessHistory = new ApplyBussinessHistory();
            applyBussinessHistory.setApplyId(applyInfo.getApplyId());
            applyBussinessHistory.setOperatorId(applyInfo.getAnalysisPersonId());
            applyBussinessHistory.setOperatorTime(new Date());
            applyBussinessHistory.setOperatorTypeId("2");//2代表分析操作
            applyBussinessHistory.setOperatorResult(applyInfo.getAnalysisResultId());//分析结果
            applyBussinessHistory.setOperatorName(applyInfo.getAnalysisPersonName());//录入人
            applyBussinessHistory.setOperatorDescription(applyInfo.getAnalysisDescription());//分析描述
            return saveApplyBussinessOperatorHistory(applyBussinessHistory);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Remote Server Exception:save application analyst result", e);
            return false;
        }

    }

    /**
     * 调用工作流
     */
    private boolean cellWorkflowServerWhenAnalyst(ApplyInfo applyInfo,boolean isReplyAnalysis) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put(VariableUtil.PRIORITY, applyInfo.getPriority());
            map.put("isReply", isReplyAnalysis);
            return completeWorkflowTask(applyInfo.getApplyId(),applyInfo.getAnalysisPersonId(), applyInfo.getWorkflowId(), map);
        } catch (Exception e) {
            // 访问工作流服务出现异常，手动回滚事务。
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Remote Server Exception:use workflow when submit analyst result has error", e);
            return false;
        }
    }


    /**
     * b 分析席和审核席完成任务时，删除对应的任务关联关系
     * b、删除对应的关联关系
     * 不是重新分析 只删除对应的关联关系
     * 是重新分析 除对应的关联关系和保存新的关联关系
     *
     * @param applyInfo
     * @param isReplyAnalysis
     * @return
     */
    private boolean deleteApplyAndAnalystRelationData(ApplyInfo applyInfo, boolean isReplyAnalysis) {
        try {
            applyAndAnalystRelationRepository.deleteByApplyInfoIdAndAnalystId(applyInfo.getApplyId(), applyInfo.getAnalysisPersonId());
            if (isReplyAnalysis) {
                return saveApplyAndAnalystRelationEntity(applyInfo,isReplyAnalysis);
            }
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:Delete Application Analyst And Auditor Call Exception" + e);
            return false;
        }
    }

    /**
     * 重新分析 保存分派关联关系
     *
     * @param applyInfo
     * @return
     */
    private boolean saveApplyAndAnalystRelationEntity(ApplyInfo applyInfo,boolean isReplyAnalysis) {
        try {
            ApplyAndAnalystRelationEntity applyAndAnalystRelationEntityNew = new ApplyAndAnalystRelationEntity();
            applyAndAnalystRelationEntityNew.setRelationId(UUID.randomUUID().toString());
            applyAndAnalystRelationEntityNew.setCreateTime(new Date());
            applyAndAnalystRelationEntityNew.setApplyInfoId(applyInfo.getApplyId());
            applyAndAnalystRelationEntityNew.setPriority(applyInfo.getPriority());
            applyAndAnalystRelationEntityNew.setAnalysisResultFail(isReplyAnalysis?"1":"0");
            applyAndAnalystRelationEntityNew.setAuditorId(applyInfo.getAuditPersonId());
            if (StringUtils.equals("1", applyInfo.getApplyTypeId())) {
                applyAndAnalystRelationEntityNew.setBusinessType("1");
            } else if (StringUtils.equals("2", applyInfo.getApplyTypeId())) {
                applyAndAnalystRelationEntityNew.setBusinessType("2");
            }
            applyAndAnalystRelationRepository.save(applyAndAnalystRelationEntityNew);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:save ApplyAndAnalyst Relation   Call Exception" + e);
            return false;
        }

    }


    /**
     * 更新申请和公告关联关系
     * applyId 要取applyInfo中的applyId 模型applyAndCriminalRelation中的applyId有可能为空 造成无犯罪记录 没有删除犯罪嫌疑人
     * 此处做修改不管之前是否添加犯罪人的关联关系  先删除 然后添加新的犯罪嫌疑人
     *
     * @param applyAndCriminalRelation
     */
    private boolean updateApplyInfoAndNoticesRelations(String applyId, ApplyAndCriminalRelation applyAndCriminalRelation) {
        try {
            if (StringUtils.isNotEmpty(applyId)) {
                applyAndNoticeRelationRepository.deleteApplyAndCriminalRelationByapplyInfoId(applyId);
            }
            if (applyAndCriminalRelation != null && StringUtils.isNotEmpty(applyAndCriminalRelation.getCriminalId())) {
                return saveApplyAndCriminalRelationEntity(applyAndCriminalRelation);
            }
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:Delete Application Analyst And Auditor Call Exception" + e);
            return false;
        }
    }


    /**
     * 保存申请中犯罪的关联关系
     *
     * @param applyAndCriminalRelation
     * @return
     */
    private boolean saveApplyAndCriminalRelationEntity(ApplyAndCriminalRelation applyAndCriminalRelation) {
        try {
            ApplyAndCriminalRelationEntity applyAndCriminalRelationEntityNew = new ApplyAndCriminalRelationEntity();
            applyAndCriminalRelationEntityNew.setRelationId(UUID.randomUUID().toString());
            applyAndCriminalRelationEntityNew.setApplyInfoId(applyAndCriminalRelation.getApplyInfoId());
            applyAndCriminalRelationEntityNew.setCriminalId(applyAndCriminalRelation.getCriminalId());
            applyAndCriminalRelationEntityNew.setCreateTime(new Date());
            applyAndNoticeRelationRepository.save(applyAndCriminalRelationEntityNew);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:save ApplyAndCriminal Relation  Call Exception" + e);
            return false;
        }

    }
    //endregion

    /**
     * 通过姓名和身份证ID模糊匹配人口库人员列表
     * 模拟数据，后面需要接入人口库接口
     * 1.首先从犯罪库查询是否有此人
     * 2.没有在从人口库查询此人
     *
     * @param applyPersonQuery
     * @return
     */
    @Override
    public ReturnBase<List<ApplyBasicInfo>> getApplyPersonInfo(ApplyPersonQuery applyPersonQuery) {
        ReturnBase<List<ApplyBasicInfo>> returnBase = new ReturnBase<>();

        Page<ApplyBasicInfoEntity> page = getApplyPersonInfoPage(applyPersonQuery);

        try {
            if (page != null) {
                List<ApplyBasicInfo> list = applyBasicMapper.entitiestoModels(page.getContent());
                returnBase.setSuccess(true);
                returnBase.setData(list);
                returnBase.setTotalPage(page.getTotalPages());
                returnBase.setTotalCount(page.getTotalElements());
            }
        } catch (Exception e) {
            logger.error("Remote Server Exception:getApplyPersonInfo", e);
            returnBase.setSuccess(false);
            returnBase.setError(new Error(500, VariableUtil.SERVER_INNER_EXCEPTION));
        }
        return returnBase;
    }

    /**
     * 通过姓名和身份证ID模糊匹配人口库人员列表Page
     *
     * @param applyPersonQuery
     * @return
     */
    private Page<ApplyBasicInfoEntity> getApplyPersonInfoPage(ApplyPersonQuery applyPersonQuery) {
        PageRequest pageRequest = new PageRequest(applyPersonQuery.getPages(), applyPersonQuery.getPageSize());
        Specification<ApplyBasicInfoEntity> specification = (Root<ApplyBasicInfoEntity> entityRoot, CriteriaQuery<?>
                query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            Predicate predicateOne = cb.equal(entityRoot.get(VariableUtil.FIRSTNAME), applyPersonQuery.getFirstName());
            Predicate predicateTwo = cb.equal(entityRoot.get(VariableUtil.LASTNAME), applyPersonQuery.getLastName());
            if (StringUtils.isBlank(applyPersonQuery.getCertificateType()) || StringUtils.isBlank(applyPersonQuery.getCertificateId())) {
                predicates.add(cb.and(predicateOne, predicateTwo));
            } else {
                Predicate predicateThree = cb.equal(entityRoot.get("certificateType"), applyPersonQuery.getCertificateType());
                Predicate predicateFour = cb.equal(entityRoot.get(VariableUtil.CERTIFICATENUMBER), applyPersonQuery.getCertificateId());
                predicates.add(cb.or(cb.and(predicateOne, predicateTwo), cb.and(predicateThree, predicateFour)));
            }
            if (StringUtils.isNoneBlank(applyPersonQuery.getSexId())) {
                Predicate predicateSex = cb.equal(entityRoot.get("sexId"), applyPersonQuery.getSexId());
                predicates.add(cb.and(predicateSex));
            }
            Predicate[] predicate = new Predicate[predicates.size()];
            return query.where(predicates.toArray(predicate)).getRestriction();
        };
        return applyBasicInfoRepository.findAll(specification, pageRequest);
    }



    /**
     * 通过申请ID获取申请信息
     *
     * @param id
     * @return
     */
    @Override
    public CertificateApplyInfo getApplyInfo(String id) {
        CertificateApplyInfo certificateApplyInfo = new CertificateApplyInfo();

        if (!StringUtils.isBlank(id)) {
            //1.获取申请信息
            ApplyInfoEntity applyInfoEntity = applyInfoRepository.getByApplyId(id);
            ApplyInfo applyInfo = applyInfoMapper.entityToModel(applyInfoEntity);
            certificateApplyInfo.setApplyInfo(applyInfo);
            //2.获取申请本人信息
            if (applyInfoEntity != null) {
                ApplyBasicInfoEntity applyBasicInfoEntity = applyBasicInfoRepository.getApplyBasicInfoEntityByApplyBasicId(applyInfoEntity.getApplyBasicId());
                ApplyBasicInfo applyBasicInfo = applyBasicMapper.entityToModel(applyBasicInfoEntity);
                certificateApplyInfo.setApplyBasicInfo(applyBasicInfo);
                //3.获取附件信息
                String attachmentId = applyInfoEntity.getAttchmentId();

                if (!StringUtils.isBlank(attachmentId)) {
                    List<String> attachmentIds = StringAndListExchangeUtil.changeStringToList(attachmentId);
                    List<AttachmentInfo> attachmentInfos = attachmentInfoMapper.entitiestoModels(attchmentInfoRepository.findAll(attachmentIds));

                    certificateApplyInfo.setAttachmentInfoList(attachmentInfos);
                }
                //查询与改申请相关的公告
                ApplyAndCriminalRelationEntity applyAndCriminalRelationEntity = applyAndNoticeRelationRepository.getByApplyInfoId(applyInfo.getApplyId());
                ApplyAndCriminalRelation applyAndCriminalRelation = applyAndNoticeRelationMapper.entityToModel(applyAndCriminalRelationEntity);
                certificateApplyInfo.setApplyAndCriminalRelation(applyAndCriminalRelation);
                //查询犯罪嫌疑人
                if (applyAndCriminalRelationEntity != null && StringUtils.isNoneBlank(applyAndCriminalRelationEntity.getCriminalId())) {
                    certificateApplyInfo.setCrimePersonInfo(crimeSearchService.findOneByCrimeId(applyAndCriminalRelationEntity.getCriminalId()));
                }
            }
        }
        return certificateApplyInfo;
    }

    /**
     * 通过分析员ID获取分析任务
     * 包括申请详细
     *
     * @param analystId
     * @return
     */
    @Override
    public CertificateApplyInfo getAnalysisTaskByAnalystId(String analystId) {
        CertificateApplyInfo certificateApplyInfo = new CertificateApplyInfo();
        try {
            ApplyAndAnalystRelationEntity entity = this.findByAnalystIdAndAnalysisResultFailGetOne(analystId, "0");

            if (entity != null) {
                ApplyInfoEntity applyInfoEntity = applyInfoRepository.getByApplyId(entity.getApplyInfoId());
                ApplyBasicInfoEntity applyBasicInfoEntity = applyBasicInfoRepository.getApplyBasicInfoEntityByApplyBasicId(applyInfoEntity.getApplyBasicId());
                if (StringUtils.isNotBlank(applyInfoEntity.getAttchmentId())) {
                    List<String> idList = StringAndListExchangeUtil.changeStringToList(applyInfoEntity.getAttchmentId());
                    List<AttachmentInfo> attachmentInfoList = attachmentInfoMapper.entitiestoModels(attchmentInfoRepository.findAll(idList));
                    certificateApplyInfo.setAttachmentInfoList(attachmentInfoList);
                }

                certificateApplyInfo.setApplyInfo(applyInfoMapper.entityToModel(applyInfoEntity));
                certificateApplyInfo.setApplyBasicInfo(applyBasicMapper.entityToModel(applyBasicInfoEntity));
            } else {
                return certificateApplyInfo;
            }
        } catch (Exception e) {
            logger.error("Exception:Get Application Should Analysis Call Exception:" + e);
        }
        return certificateApplyInfo;
    }

    /**
     * 审核席位:提交审核结果，更新申请信息
     * 1、数据保存 (更新审核结果保存和保存操作历史)
     * a 更新审核结果保存
     * 更新审核
     * 和保存操作历史)
     * 2、保存成功后 调用公告流(调用失败 进行事务回滚)
     *
     * @param certificateApplyInfo
     * @return
     */
    @Override
    public Map<String, Object> applyAuditSubmit(CertificateApplyInfo certificateApplyInfo) {
        Map<String, Object> map = new HashMap<>();
        ApplyInfo applyInfo = certificateApplyInfo.getApplyInfo();
        ApplyInfoEntity applyInfoEntity = applyInfoRepository.getByApplyId(applyInfo.getApplyId());
        if (applyInfoEntity == null) {
            map.put(VariableUtil.SUCCESS, false);
            return map;
        }
        boolean auditResult = StringUtils.equals("1", applyInfo.getAuditResultId()); //审核是否通过
        applyInfo = resolvebusinessByAuditorResult(applyInfo, auditResult);
        applyInfo.setWorkflowId(applyInfoEntity.getWorkflowId());
        //1 保存基本信息
        boolean result = saveApplyResultWhenAudit(applyInfo, applyInfoEntity, auditResult);
        // 2 保存通过后调用工作流
        if (result && !cellWorkServerWhenAudit(applyInfo, auditResult)) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result=false;
        }
        map.put(VariableUtil.SUCCESS, result);
        map.put("data", result ? applyReturnDataModelMapper.entityToModel(applyInfoEntity) : null);
        return map;
    }

    /**
     * 流程
     * 1、数据更新
     * 数据基本信息(审核人,审核结果,审核时间,拒绝原因,变化时间 修改人 修改时间,审核描述)
     * 申请状态
     * 1.1、通过拒绝时： 申请状态 改为驳回(applyStatusId=5)申请结果改为驳回(applyResultId=3) 更新完成时间
     * 1.2、通过无犯罪记录时： 申请状态 改为待打印(applyStatusId=3)申请结果改为无犯罪记录(applyResultId=1)
     * 1.3、通过有犯罪记录时： 申请状态 改为待打印(applyStatusId=3)申请结果改为有犯罪记录(applyResultId=2)
     * 1.4 审核不通过时 申请状态 改为分析中 (applyStatusId=1)
     * 2、删除审核任务关联记录(根据申请id)
     * 3、判断审核是否通过：
     * 3.1 审核通过 删除审核员的当前申请的分派关系
     * 3.2 不通过  删除审核员的当前申请的分派关系 重新分给审核员
     * 4、保存操作历史
     *
     * @param applyInfo
     * @param applyInfoEntity
     * @return
     */
    private boolean saveApplyResultWhenAudit(ApplyInfo applyInfo, ApplyInfoEntity applyInfoEntity, boolean auditResult) {
        boolean result = true;
        try {
            //更新审核
            applyInfoEntity.setAuditDescription(applyInfo.getAuditDescription());
            applyInfoEntity.setAuditPersonId(applyInfo.getAuditPersonId());
            applyInfoEntity.setAuditPersonName(applyInfo.getAuditPersonName());
            applyInfoEntity.setAuditResultId(applyInfo.getAuditResultId());
            applyInfoEntity.setAuditTime(applyInfo.getAuditTime());
            applyInfoEntity.setApplyStatusId(applyInfo.getApplyStatusId());
            applyInfoEntity.setAuditRejectReason(applyInfo.getAuditRejectReason());
            applyInfoEntity.setApplyResultId(applyInfo.getApplyResultId());
            applyInfoEntity.setApplyCompleteTime(applyInfo.getApplyCompleteTime());
            applyInfoEntity.setApplyResultTime(new Date());
            // 数据发生变更时间
            applyInfoEntity.setChangeTime(new Date());
            applyInfoEntity.setModifyTime(new Date());
            applyInfoEntity.setModifyPersonName(applyInfo.getAuditPersonName());
            applyInfoRepository.save(applyInfoEntity);
            // 删除审核任务关联记录(根据申请id)
            applyAndAnalystRelationRepository.deleteByApplyInfoIdAndAuditorId(applyInfo.getApplyId(), applyInfo.getAuditPersonId());
            //审核不通过 将当前的任务重新分给分析员
            if (!auditResult) {
                result = apportionToAnalyst(applyInfo);
            }
            // 数据保存成功或重新分给分析员保存成功 保存操作历史
            if (result) {
                return saveBusHistoryWhenAudit(applyInfo);
            }
            return result;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:save Apply audit Result    Call Exception" + e);
            return false;
        }
    }

    /***
     * 根据审核结果 处理相关业务
     */
    private ApplyInfo resolvebusinessByAuditorResult(ApplyInfo applyInfo, boolean auditResult) {
        if (auditResult) {
            //通过
            if (StringUtils.equals("3", applyInfo.getAnalysisResultId())) {
                // 拒绝分析，申请状态为驳回，申请结果为驳回
                applyInfo.setApplyStatusId("5");//驳回
                applyInfo.setApplyResultId("3");
                applyInfo.setApplyCompleteTime(new Date());
            } else if (StringUtils.equals("1", applyInfo.getAnalysisResultId())) {
                // 分析结果为有犯罪，申请状态为待打印，结果为有犯罪
                applyInfo.setApplyStatusId("3");//待打印,有犯罪记录
                applyInfo.setApplyResultId("2");
            } else if (StringUtils.equals("2", applyInfo.getAnalysisResultId())) {
                // 分析结果为有犯罪，申请状态为待打印，结果为无犯罪
                applyInfo.setApplyStatusId("3");//待打印,无犯罪记录
                applyInfo.setApplyResultId("1");
            }
            applyInfo.setApplyResultTime(new Date());
        } else {
            //不通过
            applyInfo.setApplyStatusId("1");//分析中
        }
        return applyInfo;
    }

    /**
     * 审核不通过 将申请重新指给分析员
     */
    private boolean apportionToAnalyst(ApplyInfo applyInfo) {
        try {
            //将分析重新打回给分析员
            ApplyAndAnalystRelationEntity applyAndAnalystRelationEntity = new ApplyAndAnalystRelationEntity();
            applyAndAnalystRelationEntity.setRelationId(UUID.randomUUID().toString());
            applyAndAnalystRelationEntity.setAnalystId(applyInfo.getAnalysisPersonId());
            applyAndAnalystRelationEntity.setApplyInfoId(applyInfo.getApplyId());
            applyAndAnalystRelationEntity.setAnalysisResultFail("1");//代表重新此条申请需要分析
            applyAndAnalystRelationEntity.setCreateTime(new Date());
            applyAndAnalystRelationEntity.setPriority(applyInfo.getPriority());
            if (StringUtils.equals("1", applyInfo.getApplyTypeId())) {
                applyAndAnalystRelationEntity.setBusinessType("1");
            } else if (StringUtils.equals("2", applyInfo.getApplyTypeId())) {
                applyAndAnalystRelationEntity.setBusinessType("2");
            }
            applyAndAnalystRelationRepository.save(applyAndAnalystRelationEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:save reply analyst apportion  Call Exception" + e);
            return false;
        }

    }

    /**
     * 审核通过保存操作历史
     */
    private boolean saveBusHistoryWhenAudit(ApplyInfo applyInfo) {
        //4.保存操作历史
        ApplyBussinessHistory applyBussinessHistory = new ApplyBussinessHistory();
        applyBussinessHistory.setApplyId(applyInfo.getApplyId());
        applyBussinessHistory.setOperatorId(applyInfo.getAuditPersonId());
        applyBussinessHistory.setOperatorName(applyInfo.getAuditPersonName());
        applyBussinessHistory.setOperatorTime(new Date());
        applyBussinessHistory.setOperatorTypeId("3");//3代表审核操作
        applyBussinessHistory.setOperatorResult(applyInfo.getAuditResultId());//审核结果
        applyBussinessHistory.setOperatorDescription(applyInfo.getAuditDescription());//审核描述
        return saveApplyBussinessOperatorHistory(applyBussinessHistory);
    }

    /**
     * 审核通过后 调用工作流
     * 只有审核通过才传入申请状态
     */
    private boolean cellWorkServerWhenAudit(ApplyInfo applyInfo, boolean auditResult) {
        try {
            // 6.完成审核任务，调用工作流接口
            Map<String, Object> variables = new HashMap<>();
            variables.put("auditresult", auditResult);
            if (auditResult) {
                variables.put("applyStatus", applyInfo.getApplyStatusId());
            }
            variables.put("isReply", !auditResult);
            variables.put(VariableUtil.PRIORITY, applyInfo.getPriority());
            return completeWorkflowTask(applyInfo.getApplyId(),applyInfo.getAuditPersonId(), applyInfo.getWorkflowId(), variables);

        } catch (Exception e) {
            logger.error("Remote Server Exception:use workflow when submit audit result has error", e);
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
    private boolean completeWorkflowTask(String businessKey,String assignee, String workFlowId, Map<String, Object> variables) {

        String url = workFlowUrl + "/task/complete/"+ businessKey+"/" +assignee + "/" + workFlowId;
        try {
            return httpClientUtil.httpPost(url, variables, boolean.class, false);
        } catch (Exception e) {
            logger.error("Remote Server Exception:Complete Workflow Task Call Exception" + e);
            return false;
        }

    }

    /**
     * 根据姓名和身份证ID查找犯罪人员基本信息
     * 返回可能的犯罪公告
     * 待修改
     *
     * @param applyBasicInfo
     * @return
     */
    private List<CrimePersonInfo> findApplyPersonIsHasCrimeRecord(ApplyBasicInfo applyBasicInfo) {
        List<CrimePersonInfo> list = new ArrayList<>();

        try {
            list = crimeSearchService.findPersonalCrimeInfoList(applyBasicInfo.getCertificateType(), applyBasicInfo.getCertificateNumber(), applyBasicInfo.getFirstName(), applyBasicInfo.getLastName());
            return list;
        } catch (Exception e) {
            logger.error("Exception: Find Personal Crime Record Call Exception" + e);
            return list;
        }
    }

    /**
     * 启动工作流流程，
     * 前提：申请人员有犯罪记录
     *
     * @param applyType
     * @return
     */
    private String startProcessByKey(String applyId, String applyType, String operatorId, String priority) {
        String workFlowID = "";
        Map<String, Object> map = new HashMap<>();
        map.put("operator", operatorId);
        map.put(VariableUtil.PRIORITY, StringUtils.isNoneBlank(priority) ? priority : "0");
        try {
            if ("1".equals(applyType)) {
                //启动个人申请流程
                String url = workFlowUrl + "/process/start?processKey=" + personProcessKey + "&bussinessKey=" + applyId;
                workFlowID = httpClientUtil.httpPost(url, map, String.class, false);
            } else if ("2".equals(applyType)) {
                //启动政府申请流程
                String url = workFlowUrl + "/process/start?processKey=" + governmentProcessKey + "&bussinessKey=" + applyId;
                workFlowID = httpClientUtil.httpPost(url, map, String.class, false);
            }
        } catch (Exception e) {
            logger.error("Remote Server Exception:Start WorkFlow Call Exception" + e);
        }

        return workFlowID;
    }



    /**
     * 保存申请业务操作历史
     *
     * @return
     */
    @Override
    public   boolean saveApplyBussinessOperatorHistory(ApplyBussinessHistory applyBussinessHistory) {
        try {
            ApplyBussinessHistoryEntity applyBussinessHistoryEntity = applyBussinessHistoryMapper.modelToEntity(applyBussinessHistory);
            applyBussinessHistoryEntity.setId(UUID.randomUUID().toString());
            applyBussinessHistoryRepository.save(applyBussinessHistoryEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:Save Operator History Call Exception" + e);
            return false;
        }
    }

    /**
     * 根据工作人员Id查询已完成和未完成
     *
     * @param pages
     * @param pageSize
     * @param operatorId
     * @param isComplete 查询未完成还是已完成
     * @return
     */
    private Page<ApplyInfoEntity> findApplyInfoPage(String operatorId, int pages, int pageSize, Boolean isComplete, Date startTime, Date endTime, String[] applyStatusList) {
        PageRequest pageRequest = new PageRequest(pages, pageSize);
        Specification<ApplyInfoEntity> specification = (Root<ApplyInfoEntity> entityRoot, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = applyInfoPredicatesExChange(operatorId, startTime, endTime, applyStatusList, entityRoot, cb);
            Predicate[] predicate = new Predicate[predicates.size()];
            if (isComplete) {
                return query.where(predicates.toArray(predicate)).orderBy(cb.desc(entityRoot.get(VariableUtil.PRIORITY).as(String.class)), cb.desc(entityRoot.get(VariableUtil.APPLY_COMPLETE_TIME).as(Date.class))).getRestriction();
            } else {
                return query.where(predicates.toArray(predicate)).orderBy(cb.desc(entityRoot.get(VariableUtil.PRIORITY).as(String.class)), cb.asc(entityRoot.get(VariableUtil.APPLY_TIME).as(Date.class))).getRestriction();
            }
        };
        return applyInfoRepository.findAll(specification, pageRequest);
    }

    /**
     * 未完成和已完成 Predicate转化
     *
     * @param operatorId
     * @param startTime
     * @param endTime
     * @param applyStatusList
     * @param root
     * @param cb
     * @return
     */
    private List<Predicate> applyInfoPredicatesExChange(String operatorId, Date startTime, Date endTime, String[] applyStatusList, Root<ApplyInfoEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isBlank(operatorId)) {
            predicates.add(cb.equal(root.get("enteringPersonId"), operatorId));
        }
        CriteriaBuilder.In<String> in = cb.in(root.get(VariableUtil.APPLY_STATUSID));
        for (String id1 : applyStatusList) {
            in.value(id1);
        }
        predicates.add(in);
        if (startTime != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(VariableUtil.APPLY_COMPLETE_TIME), startTime));
        }
        if (endTime != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get(VariableUtil.APPLY_COMPLETE_TIME), endTime));
        }
        return predicates;
    }

    /**
     * 查询工作人员未办理业务
     *
     * @param userId
     * @return
     */
    @Override
    public ReturnBase<List<ApplyInfo>> getUnCompleteApplicationByUserId(String userId, int pages, int pageSize,String applyStatus) {
        ReturnBase<List<ApplyInfo>> returnBase = new ReturnBase<>();
        try {
            String[] applyStatusList = isUnCompleteList.split(",");
            if(!StringUtils.isEmpty(applyStatus)){
                 applyStatusList=applyStatus.split(",");
            }
            Page<ApplyInfoEntity> page = findApplyInfoPage(userId, pages, pageSize, false, null, null, applyStatusList);
            returnBase = getApplicationIsCompletePage(page);
        } catch (Exception e) {
            logger.error("Exception:CertificateApplyServiceImpl getUnCompleteApplicationByUserId Call Exception" + e);
            returnBase.setSuccess(false);
            returnBase.setError(new Error(500, VariableUtil.SERVER_INNER_EXCEPTION));
        }
        return returnBase;
    }

    /**
     * 查询审核员已审核的申请
     *
     * @param applyInfoParam
     * @return
     */
    @Override
    public ReturnBase<List<ApplyInfo>> getPersonalApplicationHasTask(ApplyInfoParam applyInfoParam) {
        ReturnBase<List<ApplyInfo>> returnBase = new ReturnBase<>();
        try {
            Page<ApplyInfoEntity> page = findHasAuditApply(applyInfoParam);
            returnBase = getApplicationIsCompletePage(page);
        } catch (Exception e) {
            logger.error("Exception:CertificateApplyServiceImpl getPersonalApplicationHasTask Call Exception" + e);
            returnBase.setSuccess(false);
            returnBase.setError(new Error(500, VariableUtil.SERVER_INNER_EXCEPTION));
        }
        return returnBase;

    }

    /**
     * 根据申请Id 查询犯罪嫌疑人 公告列表  犯罪信息列表
     *
     * @param applyId
     * @return
     */
    @Override
    public List<CrimeRecordPrint> findNoticeByApplyId(String applyId) {
        List<CrimeRecordPrint> list = new ArrayList<>();
        ApplyAndCriminalRelationEntity entity = applyAndNoticeRelationRepository.getByApplyInfoId(applyId);
        if (entity == null || entity.getCriminalId() == null) {
            return list;
        } else {
            list = crimeSearchService.findCriminalAndNoticeByCrimePersonId(entity.getCriminalId());
        }
        return list;
    }

    /**
     * 查询已审核完成的列表
     * 排序规则: 优先级降序 申请时间降序
     *
     * @return
     */
    private Page<ApplyInfoEntity> findHasAuditApply(ApplyInfoParam applyInfoParam) {
        PageRequest pageRequest = new PageRequest(applyInfoParam.getPages(), applyInfoParam.getPageSize());
        Specification<ApplyInfoEntity> specification = (Root<ApplyInfoEntity> entityRoot, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = auditPredicatesExChange(applyInfoParam, entityRoot, cb);
            Predicate[] predicate = new Predicate[predicates.size()];
            return query.where(predicates.toArray(predicate)).orderBy(cb.desc(entityRoot.get(VariableUtil.PRIORITY).as(String.class)), cb.desc(entityRoot.get(VariableUtil.APPLY_TIME).as(Date.class))).getRestriction();
        };
        return applyInfoRepository.findAll(specification, pageRequest);
    }

    /**
     * 查询已审核完成的列表Predicate
     *
     * @param applyInfoParam
     * @param entityRoot
     * @param cb
     * @return
     */
    private List<Predicate> auditPredicatesExChange(ApplyInfoParam applyInfoParam, Root<ApplyInfoEntity> entityRoot, CriteriaBuilder cb) {
        List<Predicate> predicates = predicatesExChangeCommon(applyInfoParam, entityRoot, cb);
        if (StringUtils.isNotBlank(applyInfoParam.getApplyStatus())) {//申请状态
            predicates.add(cb.equal(entityRoot.get(VariableUtil.APPLY_STATUSID), applyInfoParam.getApplyStatus()));
        } else {
            CriteriaBuilder.In<String> in = cb.in(entityRoot.get(VariableUtil.APPLY_STATUSID));
            //已审核
            in.value("1");
            in.value("3");
            in.value("4");
            in.value("5");
            predicates.add(in);
        }
        if (!StringUtils.isBlank(applyInfoParam.getAuditPersonId())) {
            predicates.add(cb.equal(entityRoot.get("auditPersonId"), applyInfoParam.getAuditPersonId()));
        }
        return predicates;
    }

    /**
     * 查询 抽取公共
     *
     * @param applyInfoParam
     * @param root
     * @param cb
     * @return
     */
    private List<Predicate> predicatesExChangeCommon(ApplyInfoParam applyInfoParam, Root<ApplyInfoEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = applyCertificateAndName(applyInfoParam.getCertificateNum(), applyInfoParam.getFirstName(), applyInfoParam.getLastName(), root, cb);
        if (StringUtils.isNotBlank(applyInfoParam.getDeliveryReceiptNumbr())) {//回执单号
            predicates.add(cb.like(root.get("deliveryReceiptNumbr"), "%" + applyInfoParam.getDeliveryReceiptNumbr() + "%"));
        }
        if (StringUtils.isNotBlank(applyInfoParam.getApplyPurpose())) {//申请目的
            predicates.add(cb.equal(root.get("applyPurposeId"), applyInfoParam.getApplyPurpose()));
        }
        if (StringUtils.isNotBlank(applyInfoParam.getApplyType())) {//申请类型
            predicates.add(cb.equal(root.get("applyTypeId"), applyInfoParam.getApplyType()));
        }
        if (applyInfoParam.getApplyStartTime() != null) {//起始时间
            predicates.add(cb.greaterThanOrEqualTo(root.get("applyTime"), applyInfoParam.getApplyStartTime()));
        }
        if (applyInfoParam.getApplyEndTime() != null) {//截止时间
            predicates.add(cb.lessThanOrEqualTo(root.get("applyTime"), applyInfoParam.getApplyEndTime()));
        }
        return predicates;
    }

    /**
     * 获取完成或者未完成的Page
     *
     * @param page
     * @return
     */
    private ReturnBase<List<ApplyInfo>> getApplicationIsCompletePage(Page<ApplyInfoEntity> page) {
        ReturnBase<List<ApplyInfo>> returnBase = new ReturnBase<>();
        if (page != null) {
            List<ApplyInfo> list = applyInfoMapper.entitiestoModels(page.getContent());
            returnBase.setSuccess(true);
            returnBase.setData(list);
            returnBase.setTotalPage(page.getTotalPages());
            returnBase.setTotalCount(page.getTotalElements());
        }
        return returnBase;
    }

    /**
     * 获取用户当天已办理的申请业务
     *
     * @param userId
     * @return
     */
    @Override
    public ReturnBase<List<ApplyInfo>> getPersonalDayHandleApplication(String userId, int pages, int pageSize) {
        ReturnBase<List<ApplyInfo>> returnBase = new ReturnBase<>();
        try {
            Calendar calendar = Calendar.getInstance();

            calendar.setTime(new Date());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            Date startTime = calendar.getTime();

            calendar.add(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.SECOND, -1);

            Date endTime = calendar.getTime();
            String[] applyStatusList = isCompleteList.split(",");
            Page<ApplyInfoEntity> page = findApplyInfoPage(userId, pages, pageSize, true, startTime, endTime, applyStatusList);

            returnBase = getApplicationIsCompletePage(page);
        } catch (Exception e) {
            logger.error("Exception:Get Day Handle Application Call Exception" + e);
            returnBase.setSuccess(false);
            returnBase.setError(new Error(500, VariableUtil.SERVER_INNER_EXCEPTION));
        }
        return returnBase;
    }

    /**
     * 查询分析员需要重新分析的申请
     * 查询审核员需要审核的申请
     *
     * @param pages
     * @param pageSize
     * @param analystId
     * @return
     */
    @Override
    public ReturnBase<List<ApplyInfo>> getPersonalApplicationTask(int pages, int pageSize, String analystId, String auditorId) {
        ReturnBase<List<ApplyInfo>> returnBase = new ReturnBase<>();

        try {
            Page<ApplyAndAnalystRelationEntity> page = findReplyAnalysisApplication(pages, pageSize, analystId, auditorId, "1");
            returnBase = getApplicationTaskList(page);
        } catch (Exception e) {
            logger.error("Exception:Get Reply Application List Call Exception" + e);
            returnBase.setSuccess(false);
            returnBase.setError(new Error(500, VariableUtil.SERVER_INNER_EXCEPTION));
        }

        return returnBase;
    }

    /**
     * 获取申请任务列表
     *
     * @param page
     * @return
     */
    private ReturnBase<List<ApplyInfo>> getApplicationTaskList(Page<ApplyAndAnalystRelationEntity> page) {
        ReturnBase<List<ApplyInfo>> returnBase = new ReturnBase<>();
        List<ApplyInfo> list = new ArrayList<>();

        if (page != null) {
            List<ApplyAndAnalystRelationEntity> relationEntities = page.getContent();

            if (!relationEntities.isEmpty()) {
                relationEntities.forEach(i -> {
                    ApplyInfoEntity applyInfoEntity = applyInfoRepository.getByApplyId(i.getApplyInfoId());
                    if (applyInfoEntity != null) {
                        ApplyInfo applyInfo = applyInfoMapper.entityToModel(applyInfoEntity);
                        applyInfo.setApportionTime(i.getCreateTime());
                        list.add(applyInfo);
                    }
                });
            }

            returnBase.setData(list);
            returnBase.setSuccess(true);
            returnBase.setTotalPage(page.getTotalPages());
            returnBase.setTotalCount(page.getTotalElements());
        }

        return returnBase;
    }


    /**
     * 重新分析申请列表
     * 查询审核员需要审核的申请
     * 个人审核列表和政府审核列表 合在一起 businessType 为 1和2
     * 公告待审核列表  businessType为3
     * 排序规则 优先级降序 申请时间升序
     *
     * @param pages
     * @param pageSize
     * @param analystId
     * @param businessType
     * @return
     */
    private Page<ApplyAndAnalystRelationEntity> findReplyAnalysisApplication(int pages, int pageSize, String analystId, String auditorId, String businessType) {
        PageRequest pageRequest = new PageRequest(pages, pageSize);
        Specification<ApplyAndAnalystRelationEntity> specification = (Root<ApplyAndAnalystRelationEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = replyAnalysisPredicatesExChange(analystId, auditorId, businessType, root, cb);
            Predicate[] predicate = new Predicate[predicates.size()];
            return query.where(predicates.toArray(predicate)).orderBy(cb.desc(root.get(VariableUtil.PRIORITY).as(String.class)), cb.asc(root.get("createTime").as(Date.class))).getRestriction();
        };
        return applyAndAnalystRelationRepository.findAll(specification, pageRequest);
    }

    /**
     * @param pages
     * @param pageSize
     * @param analystId
     * @param auditorId
     * @param businessType
     * @return
     */
    @Override
    public ReturnBase<List<ApplyAndAnalystRelation>> findReplyAnalysisApplicationCommon(int pages, int pageSize, String analystId, String auditorId, String businessType) {
        ReturnBase<List<ApplyAndAnalystRelation>>returnBase=new ReturnBase<>();
        try {
            Page<ApplyAndAnalystRelationEntity> page = findReplyAnalysisApplication( pages,  pageSize,  analystId,  auditorId,  businessType);
            returnBase = getApplicationTaskListCommon(page);
        } catch (Exception e) {
            logger.error("Exception:Get Reply Application List Call Exception" + e);
            returnBase.setSuccess(false);
            returnBase.setError(new Error(500, VariableUtil.SERVER_INNER_EXCEPTION));
        }
        return  returnBase;
    }
    /**
     * 获取申请任务列表
     *
     * @param page
     * @return
     */
    private ReturnBase<List<ApplyAndAnalystRelation>> getApplicationTaskListCommon(Page<ApplyAndAnalystRelationEntity> page) {
        ReturnBase<List<ApplyAndAnalystRelation>> returnBase = new ReturnBase<>();
        if (page != null) {
            List<ApplyAndAnalystRelationEntity> relationEntities = page.getContent();
            returnBase.setData(applyAndAnalystRelationMapper.entitiestoModels(relationEntities));
            returnBase.setSuccess(true);
            returnBase.setTotalPage(page.getTotalPages());
            returnBase.setTotalCount(page.getTotalElements());
        }

        return returnBase;
    }

    /**
     * 重新分析查询转化
     *
     * @param analystId
     * @param auditorId
     * @param businessType
     * @param root
     * @param cb
     * @return
     */
    private List<Predicate> replyAnalysisPredicatesExChange(String analystId, String auditorId, String businessType, Root<ApplyAndAnalystRelationEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isBlank(analystId)) {
            predicates.add(cb.equal(root.get("analystId"), analystId));
            predicates.add(cb.equal(root.get("analysisResultFail"), "1"));
        }
        if (!StringUtils.isBlank(auditorId)) {
            predicates.add(cb.equal(root.get("auditorId"), auditorId));
        }
        if (!StringUtils.isBlank(businessType)) {
            if ("1".equals(businessType) || "2".equals(businessType)) {
                Predicate predicate1 = cb.equal(root.get(VariableUtil.BUSINESS_TYPE), "1");
                Predicate predicate2 = cb.equal(root.get(VariableUtil.BUSINESS_TYPE), "2");
                predicates.add(cb.or(predicate1, predicate2));
            } else if ("3".equals(businessType)) {
                predicates.add(cb.equal(root.get(VariableUtil.BUSINESS_TYPE), businessType));
            }
        }
        return predicates;
    }

    /**
     * 根据申请id查询历史操作记录
     *
     * @param id
     * @return
     */
    @Override

    public List<ApplyBussinessHistory> getApplyOperatorHistoryById(String id) {
        return applyBussinessHistoryMapper.entitiestoModels(applyBussinessHistoryRepository.findAllByApplyIdOrderByOperatorTimeDesc(id));
    }



    /**
     * 按时间获取一条最早待分析的个人申请
     *
     * @param analystId          分析员id
     * @param analysisResultFail 申请状态
     * @return
     */
    private ApplyAndAnalystRelationEntity findByAnalystIdAndAnalysisResultFailGetOne(String analystId, String analysisResultFail) {
        List<ApplyAndAnalystRelationEntity> applyAndAnalystRelationEntityList = applyAndAnalystRelationRepository.findByAnalystIdAndAnalysisResultFail(analystId, analysisResultFail, "1");
        return applyAndAnalystRelationEntityList.isEmpty() ? null : applyAndAnalystRelationEntityList.get(0);

    }

    /**
     * 根据身份证号查询申请人的基本信息
     *
     * @param certificateNumber
     * @return
     */
    @Override
    public List<ApplyBasicInfo> getCitizenInfoByID(String certificateNumber) {
        List<ApplyBasicInfo> result = new ArrayList<>();
        if (StringUtils.isEmpty(certificateNumber)) {
            return result;
        }
        JSONObject params = new JSONObject();
        params.put(VariableUtil.CARD_ID, certificateNumber);

        result = postRequest(citizenById, params);
        return result;
    }

    @Override
    public List<ApplyBasicInfo> getCitizenInfoByFullName(String firstName, String lastName, String sex) {
        List<ApplyBasicInfo> result = new ArrayList<>();
        if (StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName) || StringUtils.isEmpty(sex)) {
            return result;
        }
        JSONObject params = new JSONObject();
        params.put("firstnamePrefix", firstName);
        params.put("lastnamePrefix", lastName);
        params.put("sex", sex);

        result = postRequest(citizenByName, params);
        return result;
    }

    /**
     * 根据 证件编号和回执单号查询申请进度
     *
     * @param deliveryReceiptNumbr
     * @param certificateNumber
     * @return
     */
    @Override
    public List<Map<String, Object>> getPersonalApplicationListBySearch(String deliveryReceiptNumbr, String certificateNumber) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<ApplyInfoEntity> applyInfoEntityList = applyInfoRepository.getApplyBasicInfoSchedule(deliveryReceiptNumbr, certificateNumber);
        if (!CollectionUtils.isEmpty(applyInfoEntityList)) {
            for (ApplyInfoEntity applyInfoEntity : applyInfoEntityList) {
                Map<String, Object> map = new HashMap<>();
                map.put("deliveryReceiptNumbr", applyInfoEntity.getDeliveryReceiptNumbr());
                map.put("certificateNumber", applyInfoEntity.getApplyBasicInfoEntity() != null ? applyInfoEntity.getApplyBasicInfoEntity().getCertificateNumber() : "");
                map.put("schedule", applyInfoEntity.getApplyStatusEntity() != null ? applyInfoEntity.getApplyStatusEntity().getApplyStatusId() : "");
                map.put("firstName", applyInfoEntity.getApplyBasicInfoEntity() != null ? applyInfoEntity.getApplyBasicInfoEntity().getFirstName() : "");
                map.put("lastName", applyInfoEntity.getApplyBasicInfoEntity() != null ? applyInfoEntity.getApplyBasicInfoEntity().getLastName() : "");
                mapList.add(map);
            }
        }
        return mapList;
    }

    /**
     * 申请回填接口修改
     * 1、从人口库查询
     * a、证件类型不为空
     * 1  证件类型为身份证      优先按照证件类型+证件编号查人口库    查询不到查人口（按照姓名+性别） 若人口库查不到再查发犯罪库
     * 2  证件类型不是身份证    查犯罪库 按照证件类型查询  查询不到查人口（按照姓名+性别） 若人口库查不到再查发犯罪库
     * b、证件类型为空
     * 1、 查人口（按照姓名+性别）
     * 2、人口查询为空, 再查不到再查犯罪库
     * 2、从犯罪库查询
     * a 证件不为空  优先按照证件类型+证件编号查  查询不到按照姓名+性别查
     * b 证件为空    按照姓名+性别查
     *
     * @param applyPersonQuery
     * @return
     */
    @Override
    public List<ApplyBasicInfo> getApplyPersonInfoByQuery(ApplyPersonQuery applyPersonQuery) {
        //从人口库查询
        if (!applyPersonQuery.validateCertificateData()) {
            if (!applyPersonQuery.validateNameAndSexData()) {
                return new ArrayList<>();
            }
            return getApplyBaseInfoByUserNameAndSexId(applyPersonQuery.getFirstName(), applyPersonQuery.getLastName(), applyPersonQuery.getSexId(), isByCrime);
        } else {
            return getApplyBaseInfoByCertificateId(applyPersonQuery, isByCrime);
        }
    }

    /**
     * 通过查询姓名和性别查询
     *
     * @param firstName
     * @param lastName
     * @param sexId
     * @param isByCrime 是否按照犯罪库查询  true 按照犯罪库查询  false 按照人口库查询
     * @return
     */
    private List<ApplyBasicInfo> getApplyBaseInfoByUserNameAndSexId(String firstName, String lastName, String sexId, boolean isByCrime) {
        //优先通过犯罪库查询
        if (isByCrime) {
            return applyBasicMapper.entitiestoModels(applyInfoRepository.getBaseInfoByNameAndSexId(firstName, lastName, sexId));
        } else {
            List<ApplyBasicInfo> applyBasicInfoList = getCitizenInfoByFullName(firstName, lastName, sexId);
            if (CollectionUtils.isEmpty(applyBasicInfoList)) {
                applyBasicInfoList = applyBasicMapper.entitiestoModels(applyInfoRepository.getBaseInfoByNameAndSexId(firstName, lastName, sexId));
            }
            return applyBasicInfoList;
        }
    }

    /**
     * 通过证件类型来查询
     *
     * @param applyPersonQuery
     * @param isByCrime        是否按照犯罪库查询  true 按照犯罪库查询  false 按照人口库查询
     * @return
     */
    private List<ApplyBasicInfo> getApplyBaseInfoByCertificateId(ApplyPersonQuery applyPersonQuery, boolean isByCrime) {
        //优先通过犯罪库查询
        if (isByCrime) {
            List<ApplyBasicInfo> applyBasicInfoList = applyBasicMapper.entitiestoModels(applyBasicInfoRepository.findAllByCertificateTypeAndCertificateNumber(applyPersonQuery.getCertificateType(), applyPersonQuery.getCertificateId()));
            if (CollectionUtils.isEmpty(applyBasicInfoList) && applyPersonQuery.validateNameAndSexData()) {
                applyBasicInfoList = applyBasicMapper.entitiestoModels(applyInfoRepository.getBaseInfoByNameAndSexId(applyPersonQuery.getFirstName(), applyPersonQuery.getLastName(), applyPersonQuery.getSexId()));
            }
            return applyBasicInfoList;
        } else {
            if (certificateType.equals(applyPersonQuery.getCertificateType())) {
                List<ApplyBasicInfo> applyBasicInfoList = getCitizenInfoByID(applyPersonQuery.getCertificateId());
                if (CollectionUtils.isEmpty(applyBasicInfoList)) {
                    applyBasicInfoList = getBasePersonByCertificateIdByCitizenInfo(applyPersonQuery);
                }
                return applyBasicInfoList;
            } else {
                return getBasePersonByCertificateIdByCitizenInfo(applyPersonQuery);
            }
        }

    }

    /**
     * 人口库根据证件类型查询不到时
     * 1、查询犯罪库 按照证件类型查
     * 2、查询人口库 按照姓名+性别查询
     * 3、2中查询不到时 按照姓名+性别 查询犯罪库
     *
     * @return
     */
    private List<ApplyBasicInfo> getBasePersonByCertificateIdByCitizenInfo(ApplyPersonQuery applyPersonQuery) {
        List<ApplyBasicInfo> applyBasicInfoList = applyBasicMapper.entitiestoModels(applyBasicInfoRepository.findAllByCertificateTypeAndCertificateNumber(applyPersonQuery.getCertificateType(), applyPersonQuery.getCertificateId()));
        if (CollectionUtils.isEmpty(applyBasicInfoList) && !applyPersonQuery.validateNameAndSexData()) {
            return new ArrayList<>();
        }
        if (CollectionUtils.isEmpty(applyBasicInfoList)) {
            applyBasicInfoList = getCitizenInfoByFullName(applyPersonQuery.getFirstName(), applyPersonQuery.getLastName(), applyPersonQuery.getSexId());
        }
        if (CollectionUtils.isEmpty(applyBasicInfoList)) {
            applyBasicInfoList = applyBasicMapper.entitiestoModels(applyInfoRepository.getBaseInfoByNameAndSexId(applyPersonQuery.getFirstName(), applyPersonQuery.getLastName(), applyPersonQuery.getSexId()));
        }
        return applyBasicInfoList;
    }

    /**
     * 发送POST请求
     *
     * @param url
     * @param params
     * @return
     */
    private List<ApplyBasicInfo> postRequest(String url, JSONObject params) {
        List<ApplyBasicInfo> result = new ArrayList<>();
        try {
            // 如果未启用接口，则直接返回null
            if (!this.citizenEnable) {
                return result;
            }
            // 1.生成随机密钥（用于加密请求的参数）
            String randomCipher = RandomStringUtils.randomAlphanumeric(32);
            // 2.使用RSA公钥加密随机密钥（32字节对称加密密钥+10位时间戳）
            String excCipher = CipherUtil.encryptByRSA(randomCipher + System.currentTimeMillis() / 1000,
                    CipherUtil.getPublicKey());
            // 3.使用随机密钥加密请求的参数
            String encryptParams = CipherUtil.encryptByCipher(randomCipher, params.toString());

            // 4.构建HttpClient
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("ExcUser", excUser);
            httpPost.setHeader("ExcCipher", excCipher);
            StringEntity entity = new StringEntity(encryptParams);
            httpPost.setEntity(entity);

            // 5.发送POST请求
            HttpResponse response = httpClient.execute(httpPost);

            // 6.请求成功，处理Response响应
            if (response.getStatusLine().getStatusCode() == 200) {
                // 1.返回的加密的私钥
                String responseCipher = response.getHeaders("Exccipher")[0].getValue();
                // 2.通过RSA公钥解密私钥
                String dcipher = CipherUtil.decryptByRSA(responseCipher, CipherUtil.getPublicKey());
                dcipher = StringUtils.left(dcipher, 32);
                // 3.通过私钥解密返回结果
                String resultStr = CipherUtil.decryptByCipher(dcipher, EntityUtils.toString(response.getEntity()));
                if (!StringUtils.isEmpty(resultStr)) {
                    result = this.convertApplyBasicInfo(resultStr);
                    return result;
                }
            }
            logger.info("Get citizenInfo  server status is" + response.getStatusLine().getStatusCode());
        } catch (Exception e) {
            logger.error("Post Requset [{}] fail. Exception: {} ", url, e);
        }
        return result;
    }
    /**
     * 获取申请分页对象 优先级列表查询
     * isPriority
     * true 优先级降序 申请时间升序
     * false 优先级降序 申请时间降序
     *
     * @param applyInfoParam
     * @param pageRequest
     * @return
     */
    @Override
    public  ReturnBase<List<ApplyInfo>> getApplyInfoPage(ApplyInfoParam applyInfoParam, PageRequest pageRequest, String orgPersonId, boolean isPriority) {
        Specification<ApplyInfoEntity> specification = (Root<ApplyInfoEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = applyInfoPredicatesExChange(applyInfoParam, root, cb, orgPersonId, isPriority);
            Predicate[] predicate = new Predicate[predicates.size()];
            if (isPriority) {
                return query.where(predicates.toArray(predicate)).orderBy(cb.desc(root.get(VariableUtil.PRIORITY).as(String.class)), cb.asc(root.get(VariableUtil.APPLY_TIME).as(Date.class))).getRestriction();
            } else {
                return query.where(predicates.toArray(predicate)).orderBy(cb.desc(root.get(VariableUtil.PRIORITY).as(String.class)), cb.desc(root.get(VariableUtil.APPLY_TIME).as(Date.class))).getRestriction();
            }
        };
        return getApplicationIsCompletePage(applyInfoRepository.findAll(specification, pageRequest));
    }
    /**
     * 申请列表查询
     * 此处做修改  去掉根据录入人 查询可编辑优先级的申请列表
     *
     * @param applyInfoParam
     * @param root
     * @param cb
     * @param orgPersonId
     * @return
     */
    private List<Predicate> applyInfoPredicatesExChange(ApplyInfoParam applyInfoParam, Root<ApplyInfoEntity> root, CriteriaBuilder cb, String orgPersonId, boolean isPriority) {
        //申请基本信息的字段
        List<Predicate> predicates=predicatesExChangeCommon(applyInfoParam,root,cb);
        if (isPriority) {
            CriteriaBuilder.In<String> in = cb.in(root.get(VariableUtil.APPLY_STATUSID));
            for (String id1 : isUnCompleteList.split(",")) {
                in.value(id1);
            }
            predicates.add(in);
        }
        //获取可以调整优先级的申请列表,此处monkey指的是管理员，系统内部使用,证明申请管理，将不设条件
        if (StringUtils.isNotBlank(orgPersonId) && !StringUtils.equals("monkey", orgPersonId)) {
            predicates.add(cb.equal(root.get("enteringPersonId"), orgPersonId));
        }
        return predicates;
    }
    /**
     * 证件和姓名查询Predicate
     *
     * @param certificateNum
     * @param firstName
     * @param lastName
     * @param root
     * @param cb
     * @return
     */
    private List<Predicate> applyCertificateAndName(String certificateNum, String firstName, String lastName, Root<ApplyInfoEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotBlank(certificateNum)) {//身份证
            predicates.add(cb.like(root.get(VariableUtil.APPLY_BASE_INFO_ENTITY).get(VariableUtil.CERTIFICATENUMBER), "%" + certificateNum + "%"));
        } else {
            if (StringUtils.isNotBlank(firstName)) {//名
                predicates.add(cb.like(root.get(VariableUtil.APPLY_BASE_INFO_ENTITY).get(VariableUtil.FIRSTNAME), "%" + firstName + "%"));
            }
            if (StringUtils.isNotBlank(lastName)) {//姓
                predicates.add(cb.like(root.get(VariableUtil.APPLY_BASE_INFO_ENTITY).get(VariableUtil.LASTNAME), "%" + lastName + "%"));
            }
        }
        return predicates;
    }

    /**
     * 将返回的json结构转换为实体集合
     * 人口库国籍和犯罪库不同 暂不获取
     *
     * @param result
     * @return
     */
    private List<ApplyBasicInfo> convertApplyBasicInfo(String result) {
        List<ApplyBasicInfo> resultList = new ArrayList<>();
        try {
            List<LinkedHashMap> list = JsonUtil.decodeToList(result, LinkedHashMap.class);
            for (int i = 0; i < list.size(); i++) {
                LinkedHashMap item = list.get(i);
                // 处理个人信息Json
                ApplyBasicInfo abi = resolvePersonalNotNullJson(item);
                // 处理其他信息Json
                if (abi != null) {
                    abi = resolveFatherAndOtherJson(abi, item);
                }
                // 处理地区相关的Json
                if (abi != null) {
                    abi = resolveCityAndCommunityJson(abi, item);
                }
                resultList.add(abi);
            }
        } catch (Exception e) {
            logger.error("Convert Result [{}] fail. Exception: {} ", e);
        }
        return resultList;
    }

    /**
     * 处理基本信息
     *
     * @param item
     * @return
     */
    private ApplyBasicInfo resolvePersonalNotNullJson(LinkedHashMap item) {
        try {
            ApplyBasicInfo abi = new ApplyBasicInfo();
            abi.setCertificateNumber(item.get(VariableUtil.CARD_ID) != null ? item.get("CARD_ID").toString() : "");
            abi.setFirstName(item.get("FIRST_NAME") != null ? item.get("FIRST_NAME").toString() : "");
            abi.setLastName(item.get("LAST_NAME") != null ? item.get("LAST_NAME").toString() : "");
            abi.setSexId(item.get(VariableUtil.SEX_ID) != null ? SexUtil.getCrimeSexId(item.get(VariableUtil.SEX_ID).toString()) : "");
            return resolvePersonalCommonJson(abi, item);
        } catch (Exception e) {
            logger.error("resolve personal has fail. Exception: {} ", e);
            return null;
        }
    }

    private ApplyBasicInfo resolvePersonalCommonJson(ApplyBasicInfo abi, LinkedHashMap item) {
        try {
            abi.setCertificateValidity(item.get("CERTIFICATE_VALIDITY") != null ? item.get("CERTIFICATE_VALIDITY").toString() : "");
            abi.setCredentialsIssuePlace(item.get("CREDENTIALS_ISSUE_PLACE") != null ? item.get("CREDENTIALS_ISSUE_PLACE").toString() : "");
            abi.setRegistrationPhoto(Base64Util.base64StringToByteFun(item.get("BIOMETRIC_IMAGE") != null ? item.get("BIOMETRIC_IMAGE").toString() : ""));
            if (item.containsKey("DOB") && item.get("DOB") != null) {
                abi.setDateOfBirth(TimeUtil.getDateByTime(item.get("DOB").toString()));
                abi.setAge(TimeUtil.getAge(abi.getDateOfBirth(), new Date()));
            }
            return resolvePersonalOtherJson(abi, item);
        } catch (Exception e) {
            logger.error("resolve personal Common  message has fail. Exception: {} ", e);
            return null;
        }
    }

    /**
     * 处理个人相关的Json数据
     *
     * @param abi
     * @param item
     * @return
     */
    private ApplyBasicInfo resolvePersonalOtherJson(ApplyBasicInfo abi, LinkedHashMap item) {
        try {
            abi.setCertificateType(item.get(VariableUtil.CARD_ID) != null ? certificateType : "");
            abi.setCertificateName(item.get(VariableUtil.CARD_ID) != null ? certificateTypeName : "");
            abi.setSexName(item.get(VariableUtil.SEX_ID) != null ? SexUtil.getNameByValue(abi.getSexId()) : "");
            abi.setMarriageId(item.get(VariableUtil.MARRIAGE_ID) != null ? MarriageUtil.getCrimeMarriageId(item.get(VariableUtil.MARRIAGE_ID).toString()) : "");
            abi.setMarriageName(item.get(VariableUtil.MARRIAGE_ID) != null ? MarriageUtil.getMarriageName(abi.getMarriageId()) : "");
            return abi;
        } catch (Exception e) {
            logger.error("resolve personal other message has fail. Exception: {} ", e);
            return null;
        }
    }

    /**
     * 处理 父母相关的json
     *
     * @param abi
     * @param item
     * @return
     */
    private ApplyBasicInfo resolveFatherAndOtherJson(ApplyBasicInfo abi, LinkedHashMap item) {
        try {
            abi.setFatherFirstName(item.get("FATHER_FIRST_NAME") != null ? item.get("FATHER_FIRST_NAME").toString() : "");
            abi.setFatherLastName(item.get("FATHER_LAST_NAME") != null ? item.get("FATHER_LAST_NAME").toString() : "");
            abi.setMotherFirstName(item.get("MOTHER_FIRST_NAME") != null ? item.get("MOTHER_FIRST_NAME").toString() : "");
            abi.setMotherLastName(item.get("MOTHER_LAST_NAME") != null ? item.get("MOTHER_LAST_NAME").toString() : "");
            abi.setProfession(item.get("PROFESSION") != null ? item.get("PROFESSION").toString() : "");
            return resolveOtherJson(abi, item);
        } catch (Exception e) {
            logger.error("Convert FatherAndOther Json  [{}] fail. Exception: {} ", e);
            return null;
        }
    }

    private ApplyBasicInfo resolveOtherJson(ApplyBasicInfo abi, LinkedHashMap item) {
        try {
            abi.setContractPhone(item.get("CONTRACT_PHONE") != null ? item.get("CONTRACT_PHONE").toString() : "");
            abi.setDetailAddress(item.get("DETAIL_ADDRESS") != null ? item.get("DETAIL_ADDRESS").toString() : "");
            if (item.containsKey(VariableUtil.CREDENTIALS_ISSUE_DATE) && item.get(VariableUtil.CREDENTIALS_ISSUE_DATE) != null) {
                abi.setCredentialsIssueDate(TimeUtil.getDateByTime(item.get(VariableUtil.CREDENTIALS_ISSUE_DATE).toString()));
            }
            if (item.containsKey(VariableUtil.CREDENTIALS_EXPIRED_DATE) && item.get(VariableUtil.CREDENTIALS_EXPIRED_DATE) != null) {
                abi.setCertificateValidity(TimeUtil.getDateFormatString(item.get(VariableUtil.CREDENTIALS_EXPIRED_DATE).toString()));
            }
            return abi;
        } catch (Exception e) {
            logger.error("Convert  other Result [{}] fail. Exception: {} ", e);
            return null;
        }
    }

    /**
     * 处理城市和市区的Json数据
     */
    private ApplyBasicInfo resolveCityAndCommunityJson(ApplyBasicInfo abi, LinkedHashMap item) {
        try {
            abi.setCountryName(item.get("COUNTRY_NAME") != null ? HelpUtil.firstCharToUpperCase(item.get("COUNTRY_NAME").toString()) : "");
            abi.setProvinceName(item.get("PROVINCE_NAME") != null ? HelpUtil.firstCharToUpperCase(item.get("PROVINCE_NAME").toString()) : "");
            abi.setCommunityName(item.get("COMMUNITY_NAME") != null ? HelpUtil.firstCharToUpperCase(item.get("COMMUNITY_NAME").toString()) : "");
            abi.setCityName(item.get("CITY_NAME") != null ? HelpUtil.firstCharToUpperCase(item.get("CITY_NAME").toString()) : "");
            return resolveLivingCityAndCommunityJson(abi, item);
        } catch (Exception e) {
            logger.error("Convert  Result  city And Community has fail. Exception: {} ", e);
            return null;
        }
    }

    /**
     * 处理居住地的Json数据
     *
     * @param abi
     * @param item
     * @return
     */
    private ApplyBasicInfo resolveLivingCityAndCommunityJson(ApplyBasicInfo abi, LinkedHashMap item) {
        try {
            abi.setLivingCountryName(item.get("LIVING_COUNTRY_NAME") != null ? HelpUtil.firstCharToUpperCase(item.get("LIVING_COUNTRY_NAME").toString()) : "");
            abi.setLivingProvinceName(item.get("LIVING_PROVINCE_NAME") != null ? HelpUtil.firstCharToUpperCase(item.get("LIVING_PROVINCE_NAME").toString()) : "");
            abi.setLivingCityName(item.get("LIVING_CITY_NAME") != null ? HelpUtil.firstCharToUpperCase(item.get("LIVING_CITY_NAME").toString()) : "");
            abi.setLivingCommunityName(item.get("LIVING_COMMUNITY_NAME") != null ? HelpUtil.firstCharToUpperCase(item.get("LIVING_COMMUNITY_NAME").toString()) : "");
            return abi;
        } catch (Exception e) {
            logger.error("Convert Result Living  city And Community has fail. Exception: {} ", e);
            return null;
        }
    }
}
