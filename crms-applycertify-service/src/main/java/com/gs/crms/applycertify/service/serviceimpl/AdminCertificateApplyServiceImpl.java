package com.gs.crms.applycertify.service.serviceimpl;

import com.gs.crms.applycertify.contract.model.*;
import com.gs.crms.applycertify.contract.model.admin.ApplyInfoParam;
import com.gs.crms.applycertify.contract.model.admin.CertificateParamModel;
import com.gs.crms.applycertify.contract.service.AdminCertificateApplyService;
import com.gs.crms.applycertify.contract.service.CertificateApplyService;
import com.gs.crms.applycertify.service.datamappers.*;
import com.gs.crms.applycertify.service.entity.*;
import com.gs.crms.applycertify.service.repository.*;

import com.gs.crms.common.enums.AttachmentEntity;
import com.gs.crms.common.model.Error;
import com.gs.crms.common.repository.AttachmentRepository;
import com.gs.crms.common.model.ReturnBase;

import com.gs.crms.common.utils.TimeUtil;
import com.gs.crms.common.utils.VariableUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * Created by zhangqiang on 2017/9/28.
 * 管理员查询申请相关服务实现
 */
@Transactional
@Service
public class AdminCertificateApplyServiceImpl implements AdminCertificateApplyService {

    @Autowired
    private ApplyInfoRepository applyInfoRepository;

    @Autowired
    private ApplyBasicInfoRepository applyBasicInfoRepository;

    @Autowired
    private ApplyInfoMapper applyInfoMapper;

    @Autowired
    private ApplyBasicMapper applyBasicMapper;

    @Autowired
    private AttachmentInfoMapper attachmentInfoMapper;
    @Autowired
    private AttachmentRepository attchmentInfoRepository;
    @Autowired
    private CertificateInfoRepository certificateInfoRepository;
    @Autowired
    private CertificateInfoMapper certificateInfoMapper;
    @Autowired
    private WaitApportionRepository waitApportionRepository;
    @Autowired
    private ApplyAndAnalystRelationRepository applyAndAnalystRelationRepository;
    @Autowired
    private CertificateApplyService certificateApplyService;
    @Value("${search.isUnCompleteList}")
    private String isUnCompleteList;//未完成查询的申请状态
    @Value("${search.isValidDays}")
    private int isValidDays;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 从申请人姓，名，状态，申请目的，申请时间,申请类型 去查询申请
     * 优先级列表查询使用
     * isPriority
     * true  按照有优先级列表查询
     * false 查询历史申请信息列表
     *
     * @param applyInfoParam
     * @return
     */
    @Override
    public ReturnBase<List<CertificateApplyInfo>> getCertificateApplyInfo(String orgPersonId, ApplyInfoParam applyInfoParam, boolean isPriority) {
        ReturnBase<List<CertificateApplyInfo>> returnBase = new ReturnBase<>();
        try {
            PageRequest pageRequest = new PageRequest(applyInfoParam.getPages(), applyInfoParam.getPageSize());
            //管理员获取到所有申请信息
            ReturnBase<List<ApplyInfo>>  applyInfoEntityPage = certificateApplyService.getApplyInfoPage(applyInfoParam, pageRequest, orgPersonId, isPriority);
            returnBase = getResturnBase(applyInfoEntityPage);
        } catch (Exception e) {
            returnBase.setError(new Error(500, "Server Inner Exception"));
            returnBase.setSuccess(false);
            logger.error("Exception:Admin Search Application List Call Exception" + e);
        }


        return returnBase;
    }


    /**
     * 管理员修改申请优先级
     * 此处做修改 之前只是修改了申请中的优先级和等待分派优先级
     * 此处还要修改分派后关系表的优先级
     *
     * @param applyId
     * @param priority
     * @param modifyPersonName
     * @return
     */
    @Override
    public Boolean updateApplyPriority(String applyId, String priority, String modifyPersonName) {
        //查询数据库申请记录
        try {
            ApplyInfoEntity applyInfoEntity = applyInfoRepository.getByApplyId(applyId);
            WaitApportionEntity waitApportionEntity = waitApportionRepository.findByApplyInfoId(applyId);
            if (applyInfoEntity != null) {
                //修改申请表中的数据
                applyInfoEntity.setModifyPersonName(modifyPersonName);
                applyInfoEntity.setPriority(priority);
                applyInfoEntity.setModifyTime(new Date());
                applyInfoEntity.setChangeTime(new Date());
                applyInfoRepository.save(applyInfoEntity);
                //修改待分派表中的数据
                if (waitApportionEntity != null) {
                    waitApportionEntity.setApplyPriority(priority);
                    waitApportionRepository.save(waitApportionEntity);
                }
                applyAndAnalystRelationRepository.updatePriorityByApplyId(applyId, priority);
                return true;
            }
            return false;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:update Application priority  Information Call Exception" + e);
            return false;
        }
    }

    /**
     * 获取证书信息
     *
     * @param certificateParamModel
     * @return
     */
    @Override
    public ReturnBase<List<CertificateExtendInfo>> getCertificate(String orgPersonId, CertificateParamModel certificateParamModel) {
        ReturnBase<List<CertificateExtendInfo>> returnBase = new ReturnBase<>();
        try {
            //根据证书打印时间降序
            Sort sort = new Sort(Sort.Direction.DESC, VariableUtil.PRINT_TIME);
            PageRequest pageRequest = new PageRequest(certificateParamModel.getPages(), certificateParamModel.getPageSize(), sort);
            //根据申请时间获取申请时间段
            Page<CertificateInfoEntity> certificateInfoEntityPage = getCertificatePage(certificateParamModel, pageRequest, orgPersonId);
            if (certificateInfoEntityPage != null) {
                List<CertificateInfoEntity> certificateInfoEntities = certificateInfoEntityPage.getContent();
                List<CertificateExtendInfo> certificateExtendInfos = getCertificateExtendInfo(certificateInfoEntities);
                returnBase.setData(certificateExtendInfos);
                returnBase.setTotalPage(certificateInfoEntityPage.getTotalPages());
                returnBase.setTotalCount(certificateInfoEntityPage.getTotalElements());
                returnBase.setSuccess(true);
            }
        } catch (Exception e) {
            returnBase.setSuccess(false);
            returnBase.setError(new Error(500, "Server Inner Exception"));
            logger.error("Exception:Get Credentials List Call Exception" + e);
        }

        return returnBase;
    }

    /**
     * 获取证书分页对象
     *
     * @param certificateParamModel
     * @param pageRequest
     * @return
     */
    private Page<CertificateInfoEntity> getCertificatePage(CertificateParamModel certificateParamModel, PageRequest pageRequest, String orgPersonId) {
        Specification<CertificateInfoEntity> specification = (Root<CertificateInfoEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = certificatePredicatesExChange(certificateParamModel, root, cb, orgPersonId);
            Predicate[] predicate = new Predicate[predicates.size()];
            return query.where(predicates.toArray(predicate)).getRestriction();
        };
        return certificateInfoRepository.findAll(specification, pageRequest);
    }

    /**
     * 证书查询 Predicate转化
     *
     * @param certificateParamModel
     * @param root
     * @param cb
     * @param orgPersonId
     * @return
     */
    private List<Predicate> certificatePredicatesExChange(CertificateParamModel certificateParamModel, Root<CertificateInfoEntity> root, CriteriaBuilder cb, String orgPersonId) {
        List<Predicate> predicates = certificateAndName(certificateParamModel.getCertificateNum(), certificateParamModel.getFirstName(), certificateParamModel.getLastName(), root, cb);
        //申请信息的字段
        if (StringUtils.isNotBlank(certificateParamModel.getAuthCode())) {//验证码
            predicates.add(cb.like(root.get("validateCode"), "%" + certificateParamModel.getAuthCode() + "%"));
        }
        if (StringUtils.isNotBlank(certificateParamModel.getDeliveryReceiptNumbr())) {//回执编号
            predicates.add(cb.like(root.get(VariableUtil.APPLY_INFO_ENTITY).get("deliveryReceiptNumbr"), "%" + certificateParamModel.getDeliveryReceiptNumbr() + "%"));
        }
        if (StringUtils.isNotBlank(certificateParamModel.getGovermentInfo())) {//政府部门
            predicates.add(cb.like(root.get(VariableUtil.APPLY_INFO_ENTITY).get("govermentInfo"), "%" + certificateParamModel.getGovermentInfo() + "%"));
        }
        predicates.add(cb.isNotNull(root.get(VariableUtil.PRINT_TIME)));// 证书打印时间非空
        predicates.add(cb.isNotNull(root.get(VariableUtil.APPLY_INFO_ENTITY).get("applyResultId")));
        //证书信息的字段
        if (StringUtils.isNotBlank(certificateParamModel.getCertificateId())) {//证书编号
            predicates.add(cb.equal(root.get("certificateId").as(Long.class), certificateParamModel.getCertificateId()));
        }
        if (StringUtils.isNotBlank(certificateParamModel.getCertificateType())) {//证书类型
            predicates.add(cb.equal(root.get("certificateType"), certificateParamModel.getCertificateType()));
        }
        if (StringUtils.isNotBlank(certificateParamModel.getCertificateStatus())) {//证书状态
            predicates.add(cb.equal(root.get("certificateStatus"), certificateParamModel.getCertificateStatus()));
        }
        if (certificateParamModel.getPrintStartTime() != null) {//起始时间
            predicates.add(cb.greaterThanOrEqualTo(root.get(VariableUtil.PRINT_TIME), certificateParamModel.getPrintStartTime()));
        }
        if (certificateParamModel.getPrintEndTime() != null) {//截止时间
            predicates.add(cb.lessThanOrEqualTo(root.get(VariableUtil.PRINT_TIME), certificateParamModel.getPrintEndTime()));
        }
        if (!StringUtils.equals("monkey", orgPersonId)) {
            predicates.add(cb.equal(root.get("createPersonName"), orgPersonId));
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
    private List<Predicate> certificateAndName(String certificateNum, String firstName, String lastName, Root<CertificateInfoEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotBlank(certificateNum)) {//身份证
            predicates.add(cb.like(root.get(VariableUtil.APPLY_INFO_ENTITY).get(VariableUtil.APPLY_BASE_INFO_ENTITY).get(VariableUtil.CERTIFICATENUMBER), "%" + certificateNum + "%"));
        } else {
            if (StringUtils.isNotBlank(firstName)) {//名
                predicates.add(cb.like(root.get(VariableUtil.APPLY_INFO_ENTITY).get(VariableUtil.APPLY_BASE_INFO_ENTITY).get("firstName"), "%" + firstName + "%"));
            }
            if (StringUtils.isNotBlank(lastName)) {//姓
                predicates.add(cb.like(root.get(VariableUtil.APPLY_INFO_ENTITY).get(VariableUtil.APPLY_BASE_INFO_ENTITY).get("lastName"), "%" + lastName + "%"));
            }
        }
        return predicates;
    }

    /**
     * 根据证书对象获取证书，申请，基本信息，附件对象
     *
     * @param certificateInfoEntities
     * @return
     */
    private List<CertificateExtendInfo> getCertificateExtendInfo(List<CertificateInfoEntity> certificateInfoEntities) {
        List<CertificateExtendInfo> certificateExtendInfos = new ArrayList<>();
        if (certificateInfoEntities != null && !certificateInfoEntities.isEmpty()) {
            for (CertificateInfoEntity certificateInfoEntity : certificateInfoEntities) {
                CertificateExtendInfo certificateExtendInfo = new CertificateExtendInfo();
                //获取证书
                CertificateInfo certificateInfo=certificateInfoMapper.entityToModel(certificateInfoEntity);
                certificateInfo.setPrintTime(certificateInfo.getPrintTime()==null?new Date():certificateInfo.getPrintTime());
                certificateInfo.setInvalidTime(certificateInfo.getInvalidTime()==null?TimeUtil.getInValidDate(certificateInfo.getPrintTime(),isValidDays):certificateInfo.getInvalidTime());
                certificateInfo.setValid(certificateInfo.getInvalidTime().getTime()>new Date().getTime());
                certificateExtendInfo.setCertificateInfo(certificateInfo);
                //获取申请
                certificateExtendInfo.setApplyInfo(applyInfoMapper.entityToModel(certificateInfoEntity.getApplyInfoEntity()));
                //获取申请人基本信息
                certificateExtendInfo.setApplyBasicInfo(applyBasicMapper.entityToModel(certificateInfoEntity.getApplyInfoEntity().getApplyBasicInfoEntity()));
                certificateExtendInfos.add(certificateExtendInfo);
            }
        } else {
            logger.info("AdminCertificateApplyServiceImpl---->getCertificateExtendInfo is null");
        }
        return certificateExtendInfos;
    }


    /**
     * 保存证书
     * 申请ID，证书类型，创建人，验证码
     * 此处做修改
     * 先判断当前是否已经存在证书
     * 存在  返回当前的证书
     * 不存在  更新证书
     *
     * @param certificateInfo
     * @return
     */
    private CertificateInfo saveCertificateInfo(CertificateInfo certificateInfo) {
        try {
            CertificateInfoEntity  certificateInfoOld=  certificateInfoRepository.findByApplyId(certificateInfo.getApplyId());
            if(certificateInfoOld!=null){
                return  certificateInfoMapper.entityToModel(certificateInfoOld);
            }
            ApplyInfoEntity applyInfoEntity = applyInfoRepository.getByApplyId(certificateInfo.getApplyId());
            if (applyInfoEntity != null) {
                applyInfoEntity.setApplyStatusId("3");
                applyInfoRepository.save(applyInfoEntity);
            }
            certificateInfo.setCertificateId(0);
            CertificateInfoEntity certificateInfoEntity = certificateInfoMapper.modelToEntity(certificateInfo);
            certificateInfoEntity.setCreateTime(new Date());
            certificateInfoEntity.setChangeTime(new Date());
            certificateInfoRepository.save(certificateInfoEntity);
            certificateInfo.setCertificateId(certificateInfoEntity.getCertificateId());
            return certificateInfo;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:saveCertificateInfo Call Exception" + e);
            return null;
        }


    }

    /**
     * 通过申请ID获取证书
     *
     * @param applyInfoId
     * @return
     */
    @Override
    public CertificateInfo getCertificateByApplyInfoId(String applyInfoId) {
        CertificateInfo certificateInfo=certificateInfoMapper.entityToModel(certificateInfoRepository.findByApplyId(applyInfoId));
        if(certificateInfo!=null){
            certificateInfo.setPrintTime(certificateInfo.getPrintTime()==null?new Date():certificateInfo.getPrintTime());
            certificateInfo.setInvalidTime(TimeUtil.getInValidDate(certificateInfo.getPrintTime(),isValidDays));
            certificateInfo.setValid(certificateInfo.getInvalidTime().getTime()>new Date().getTime());
        }
         return certificateInfo;
    }

    /**
     * 添加证书
     *
     * @param certificateInfo
     * @return
     */
    @Override
    public CertificateInfo addCertificateInfo(CertificateInfo certificateInfo) {
        return saveCertificateInfo(certificateInfo);
    }

    /**
     * 更新证书
     *
     * @param certificateInfo
     * @return certificateInfoEntity 不为空
     * printTime 不为空 重新打印
     * 为空 第一次打印 更新状态 和申请完成时间
     */
    @Override
    public boolean updateCertificateInfo(CertificateInfo certificateInfo) {
        boolean isSuccess = true;
        try {
            CertificateInfoEntity certificateInfoEntity = certificateInfoRepository.findByApplyId(certificateInfo.getApplyId());
            if (certificateInfoEntity != null) {
                ApplyInfoEntity applyInfoEntity = applyInfoRepository.getByApplyId(certificateInfoEntity.getApplyId());
                if(applyInfoEntity==null){
                    return false;
                }
                certificateInfoEntity.setModifyPersonName(certificateInfoEntity.getCreatePersonId());
                certificateInfoEntity.setModifyTime(new Date());
                applyInfoEntity.setApplyStatusId("4");
                applyInfoEntity.setApplyCompleteTime(applyInfoEntity.getApplyCompleteTime()!=null?applyInfoEntity.getApplyCompleteTime():new Date());
                certificateInfoEntity.setAttachmentId(StringUtils.isNotEmpty(certificateInfo.getAttachmentId())?certificateInfo.getAttachmentId():certificateInfoEntity.getAttachmentId());
                certificateInfoEntity.setChangeTime(new Date());
                certificateInfoEntity.setPrintTime(certificateInfo.getPrintTime()==null?new Date():certificateInfo.getPrintTime());
                certificateInfo.setInvalidTime(certificateInfo.getInvalidTime()==null?TimeUtil.getInValidDate(certificateInfoEntity.getPrintTime(),isValidDays):certificateInfoEntity.getInvalidTime());
                // 如果个人申请证书
                if (StringUtils.equals("1", certificateInfoEntity.getCertificateType())) {
                    Calendar ca = Calendar.getInstance();
                    ca.add(Calendar.DATE, 90);
                    Date invalidTime = ca.getTime();
                    certificateInfoEntity.setInvalidTime(invalidTime);
                }
                certificateInfoRepository.save(certificateInfoEntity);
                isSuccess = true;
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Update certificate cause Exception" + e);
            isSuccess = false;
        }
        return isSuccess;
    }

    /**
     * 分页查询，返回分页对象
     *
     * @param applyInfoEntityPage
     * @return
     */
    private ReturnBase<List<CertificateApplyInfo>> getResturnBase(  ReturnBase<List<ApplyInfo>> applyInfoEntityPage) {
        ReturnBase<List<CertificateApplyInfo>> returnBase = new ReturnBase<>();
        if (applyInfoEntityPage != null&&applyInfoEntityPage.getData()!=null) {
            List<ApplyInfo> applyInfoEntities = applyInfoEntityPage.getData();
            List<CertificateApplyInfo> certificateApplyInfos = getCertificateApplyInfo(applyInfoEntities);
            returnBase.setData(certificateApplyInfos);
            returnBase.setTotalPage(applyInfoEntityPage.getTotalPage());
            returnBase.setTotalCount(applyInfoEntityPage.getTotalCount());
            returnBase.setSuccess(true);
        }
        return returnBase;
    }

    /**
     * 根据申请获取申请详情
     *
     * @param applyInfoEntities
     * @return
     */
    private List<CertificateApplyInfo> getCertificateApplyInfo(List<ApplyInfo> applyInfoEntities) {
        List<CertificateApplyInfo> list = new ArrayList<>();
        //获取到申请的公告关联
        for (ApplyInfo applyInfo : applyInfoEntities) {
            CertificateApplyInfo certificateApplyInfo = new CertificateApplyInfo();
            //获取附件
            String attachementIDs = applyInfo.getAttchmentId();
            if (StringUtils.isNotBlank(attachementIDs)) {
                String[] attachementIDArr = attachementIDs.split(",");
                List<AttachmentEntity> attachmentInfoEntities = attchmentInfoRepository.getAllByAttachmentId(Arrays.asList(attachementIDArr));
                certificateApplyInfo.setAttachmentInfoList(attachmentInfoMapper.entitiestoModels(attachmentInfoEntities));
            }
            //获取基本信息
            ApplyBasicInfoEntity applyBasicInfoEntity = applyBasicInfoRepository.getApplyBasicInfoEntityByApplyBasicId(applyInfo.getApplyBasicId());
            certificateApplyInfo.setApplyBasicInfo(applyBasicMapper.entityToModel(applyBasicInfoEntity));
            //获取基本信息
            certificateApplyInfo.setApplyInfo(applyInfo);
            list.add(certificateApplyInfo);
        }
        return list;
    }
}
