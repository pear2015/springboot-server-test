package com.gs.crms.applycertify.service.entity;


import com.gs.crms.applycertify.service.entity.dictionary.*;
import com.gs.crms.common.enums.CertificateTypeEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tanjie on 2017/8/8.
 */
@Entity
@Table(name = "b_apply_info", schema = "crms_crime", indexes = {@Index(name = "apply_basic_idIndex", columnList = "apply_basic_id"), @Index(name = "delivery_receipt_numbrIndex", columnList = "delivery_receipt_numbr")})
public class ApplyInfoEntity {
    /*
   主键ID
   */
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String applyId;

    /*
    外键关联ID
     */
    @JoinColumn(name = "apply_type_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ApplyTypeEntity applyTypeEntity;

    /*
    ID
     */
    @Column(name = "apply_type_id", length = 36)
    private String applyTypeId;

    /*
   外键关联ID
    */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_status_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ApplyStatusEntity applyStatusEntity;

    /*
    ID
     */
    @Column(name = "apply_status_id", length = 36)
    private String applyStatusId;


    /*
     申请目的外键关联ID
    */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_purpose_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ApplyPurposeEntity applyPurposeEntity;

    /*
    ID
     */
    @Column(name = "apply_purpose_id", length = 36)
    private String applyPurposeId;


    /*
   外键关联ID
    */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_basic_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ApplyBasicInfoEntity applyBasicInfoEntity;

    /*
    ID
     */
    @Column(name = "apply_basic_id", length = 36)
    private String applyBasicId;

    /**
     * 申请时间
     */
    @Column(name = "apply_time")
    private Date applyTime;

    /**
     * 申请中心名称
     */
    @Column(name = "apply_center_name", length = 256)
    private String applyCenterName;

    /**
     * 申请中心名称Id
     */
    @Column(name = "apply_center_id", length = 128)
    private String applyCenterId;
//    /**
//     * 采集点所在省
//     */
//    @Column(name = "apply_center_province", length = 128)
//    private String applyCenterProvince;


    /**
     * 申请描述
     */
    @Column(name = "apply_description", length = 2000)
    private String applyDescription;

    /**
     * 申请修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 修改人
     */
    @Column(name = "modify_person_name", length = 256)
    private String modifyPersonName;

    /**
     * 修改描述
     */
    @Column(name = "modify_description", length = 2000)
    private String modifyDescription;

    /**
     * 分析时间
     */
    @Column(name = "analysis_time")
    private Date analysisTime;

    /**
     * 分析人姓名
     */
    @Column(name = "analysis_person_name", length = 128)
    private String analysisPersonName;

    /*
     * 分析结果外键关联ID
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analysis_result_id", referencedColumnName = "id", insertable = false, updatable = false)
    private AnalysisResultEntity analysisResultEntity;
    /**
     * 分析结果
     */
    @Column(name = "analysis_result_id", length = 36)
    private String analysisResultId;

    /**
     * 分析描述  此处只有拒绝分析时才     */
    @Column(name = "analysis_description", length = 2000)
    private String analysisDescription;

    /**
     * 审核时间
     */
    @Column(name = "audit_time")
    private Date auditTime;

    /**
     * 审核人姓名
     */
    @Column(name = "audit_person_name")
    private String auditPersonName;

    /*
     * 分析结果外键关联ID
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "audit_result_id", referencedColumnName = "id", insertable = false, updatable = false)
    private AuditResultEntity auditResultEntity;
    /**
     * 审核结果
     */
    @Column(name = "audit_result_id", length = 36)
    private String auditResultId;

    /**
     * 审核描述
     */
    @Column(name = "audit_description", length = 2000)
    private String auditDescription;

    /*
    省名称
    */
    @Column(name = "province_name", length = 128)
    private String provinceName;
    /*
    市名称
    */
    @Column(name = "city_name", length = 128)
    private String cityName;
    /*
     社区名称
      */
    @Column(name = "community_name", length = 128)
    private String communityName;

    /**
     * 备注
     */
    @Column(name = "note", length = 2000)
    private String note;

    /**
     * 回执单号
     */
    @Column(name = "delivery_receipt_numbr", length = 128)
    private String deliveryReceiptNumbr;

    /**
     * 政府申请（流程号）
     */
    @Column(name = "goverment_process", length = 128)
    private String govermentProcess;

    /**
     * 政府申请（部门信息）
     */
    @Column(name = "goverment_info", length = 128)
    private String govermentInfo;

    /*
    附件ID集合以,分隔 如：aaa,bbb,ccc
   */
    @Column(name = "attachment_id", length = 2000)
    private String attchmentId;

    /**
     * 是否有犯罪记录（0：无 1：有）
     */
    @Column(name = "crime_record")
    private int crimeRecord;

    /**
     * 申请人类型：本人/代理人
     */
    @Column(name = "apply_person_type", length = 128)
    private String applyPersonType;
    /**
     * 代理人证件号码
     */
    @Column(name = "agent_id_number", length = 128)
    private String agentIdNumber;

    /*
   *外键证件类型ID
   */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id_type", referencedColumnName = "id", insertable = false, updatable = false)
    private CertificateTypeEntity certificateTypeEntity;

    /**
     * 代理人证件类型
     */
    @Column(name = "agent_id_type", length = 36)
    private String agentIdType;

    /**
     * 申请其他目的原因
     */
    @Column(name = "other_purpose_reason", length = 2000)
    private String otherPurposeReason;

    /**
     * 工作流流程号
     */
    @Column(name = "workflow_id", length = 128)
    private String workflowId;

    /**
     * 审核拒绝原因
     */
    @Column(name = "audit_reject_reson", length = 2000)
    private String auditRejectReason;

    /**
     * 录入人员ID
     */
    @Column(name = "entering_person_name", length = 128)
    private String enteringPersonName;

    /*
    * 申请结果实体
    */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_result_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ApplyResultEntity applyResultEntity;

    /*
    * 申请结果外键关联ID
    */
    @Column(name = "apply_result_id", length = 36)
    private String applyResultId;

    /*
    * 优先级实体
    */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority", referencedColumnName = "id", insertable = false, updatable = false)
    private ApplyPriorityEntity applyPriorityEntity;

    /**
     * 优先级
     */
    @Column(name = "priority", length = 36)
    private String priority;
    /**
     * 录入人ID
     */
    @Column(name = "entering_person_id", length = 36)
    private String enteringPersonId;
    /**
     * 分析员ID
     */
    @Column(name = "analysis_person_id", length = 36)
    private String analysisPersonId;
    /**
     * 审核员ID
     */
    @Column(name = "audit_person_id", length = 36)
    private String auditPersonId;

    /**
     * 申请结果时间
     */
    @Column(name = "apply_result_time")
    private Date applyResultTime;
    /**
     * 申请完成时间
     */
    @Column(name = "apply_complete_time")
    private Date applyCompleteTime;
    /**
     * 数据发生变更时间
     */
    @Column(name = "change_time")
    private Date changeTime;

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public ApplyTypeEntity getApplyTypeEntity() {
        return applyTypeEntity;
    }

    public void setApplyTypeEntity(ApplyTypeEntity applyTypeEntity) {
        this.applyTypeEntity = applyTypeEntity;
    }

    public String getApplyTypeId() {
        return applyTypeId;
    }

    public void setApplyTypeId(String applyTypeId) {
        this.applyTypeId = applyTypeId;
    }

    public ApplyStatusEntity getApplyStatusEntity() {
        return applyStatusEntity;
    }

    public void setApplyStatusEntity(ApplyStatusEntity applyStatusEntity) {
        this.applyStatusEntity = applyStatusEntity;
    }

    public ApplyPurposeEntity getApplyPurposeEntity() {
        return applyPurposeEntity;
    }

    public void setApplyPurposeEntity(ApplyPurposeEntity applyPurposeEntity) {
        this.applyPurposeEntity = applyPurposeEntity;
    }

    public String getApplyPurposeId() {
        return applyPurposeId;
    }

    public void setApplyPurposeId(String applyPurposeId) {
        this.applyPurposeId = applyPurposeId;
    }

    public ApplyBasicInfoEntity getApplyBasicInfoEntity() {
        return applyBasicInfoEntity;
    }

    public void setApplyBasicInfoEntity(ApplyBasicInfoEntity applyBasicInfoEntity) {
        this.applyBasicInfoEntity = applyBasicInfoEntity;
    }

    public String getApplyBasicId() {
        return applyBasicId;
    }

    public void setApplyBasicId(String applyBasicId) {
        this.applyBasicId = applyBasicId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyCenterName() {
        return applyCenterName;
    }

    public void setApplyCenterName(String applyCenterName) {
        this.applyCenterName = applyCenterName;
    }

    public String getApplyCenterId() {
        return applyCenterId;
    }

    public void setApplyCenterId(String applyCenterId) {
        this.applyCenterId = applyCenterId;
    }

    public String getApplyDescription() {
        return applyDescription;
    }

    public void setApplyDescription(String applyDescription) {
        this.applyDescription = applyDescription;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyPersonName() {
        return modifyPersonName;
    }

    public void setModifyPersonName(String modifyPersonName) {
        this.modifyPersonName = modifyPersonName;
    }

    public String getModifyDescription() {
        return modifyDescription;
    }

    public void setModifyDescription(String modifyDescription) {
        this.modifyDescription = modifyDescription;
    }

    public Date getAnalysisTime() {
        return analysisTime;
    }

    public void setAnalysisTime(Date analysisTime) {
        this.analysisTime = analysisTime;
    }

    public String getAnalysisPersonName() {
        return analysisPersonName;
    }

    public void setAnalysisPersonName(String analysisPersonName) {
        this.analysisPersonName = analysisPersonName;
    }

    public AnalysisResultEntity getAnalysisResultEntity() {
        return analysisResultEntity;
    }

    public void setAnalysisResultEntity(AnalysisResultEntity analysisResultEntity) {
        this.analysisResultEntity = analysisResultEntity;
    }

    public String getAnalysisResultId() {
        return analysisResultId;
    }

    public void setAnalysisResultId(String analysisResultId) {
        this.analysisResultId = analysisResultId;
    }

    public String getAnalysisDescription() {
        return analysisDescription;
    }

    public void setAnalysisDescription(String analysisDescription) {
        this.analysisDescription = analysisDescription;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditPersonName() {
        return auditPersonName;
    }

    public void setAuditPersonName(String auditPersonName) {
        this.auditPersonName = auditPersonName;
    }

    public AuditResultEntity getAuditResultEntity() {
        return auditResultEntity;
    }

    public void setAuditResultEntity(AuditResultEntity auditResultEntity) {
        this.auditResultEntity = auditResultEntity;
    }

    public String getAuditResultId() {
        return auditResultId;
    }

    public void setAuditResultId(String auditResultId) {
        this.auditResultId = auditResultId;
    }

    public String getAuditDescription() {
        return auditDescription;
    }

    public void setAuditDescription(String auditDescription) {
        this.auditDescription = auditDescription;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDeliveryReceiptNumbr() {
        return deliveryReceiptNumbr;
    }

    public void setDeliveryReceiptNumbr(String deliveryReceiptNumbr) {
        this.deliveryReceiptNumbr = deliveryReceiptNumbr;
    }

    public String getGovermentProcess() {
        return govermentProcess;
    }

    public void setGovermentProcess(String govermentProcess) {
        this.govermentProcess = govermentProcess;
    }

    public String getGovermentInfo() {
        return govermentInfo;
    }

    public void setGovermentInfo(String govermentInfo) {
        this.govermentInfo = govermentInfo;
    }
    public int getCrimeRecord() {
        return crimeRecord;
    }

    public void setCrimeRecord(int crimeRecord) {
        this.crimeRecord = crimeRecord;
    }

    public String getApplyPersonType() {
        return applyPersonType;
    }

    public void setApplyPersonType(String applyPersonType) {
        this.applyPersonType = applyPersonType;
    }

    public String getAgentIdNumber() {
        return agentIdNumber;
    }

    public void setAgentIdNumber(String agentIdNumber) {
        this.agentIdNumber = agentIdNumber;
    }

    public CertificateTypeEntity getCertificateTypeEntity() {
        return certificateTypeEntity;
    }

    public void setCertificateTypeEntity(CertificateTypeEntity certificateTypeEntity) {
        this.certificateTypeEntity = certificateTypeEntity;
    }

    public String getAgentIdType() {
        return agentIdType;
    }

    public void setAgentIdType(String agentIdType) {
        this.agentIdType = agentIdType;
    }

    public String getOtherPurposeReason() {
        return otherPurposeReason;
    }

    public void setOtherPurposeReason(String otherPurposeReason) {
        this.otherPurposeReason = otherPurposeReason;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getAuditRejectReason() {
        return auditRejectReason;
    }

    public void setAuditRejectReason(String auditRejectReason) {
        this.auditRejectReason = auditRejectReason;
    }

    public String getEnteringPersonName() {
        return enteringPersonName;
    }

    public void setEnteringPersonName(String enteringPersonName) {
        this.enteringPersonName = enteringPersonName;
    }

    public ApplyResultEntity getApplyResultEntity() {
        return applyResultEntity;
    }

    public void setApplyResultEntity(ApplyResultEntity applyResultEntity) {
        this.applyResultEntity = applyResultEntity;
    }

    public String getApplyResultId() {
        return applyResultId;
    }

    public void setApplyResultId(String applyResultId) {
        this.applyResultId = applyResultId;
    }

    public ApplyPriorityEntity getApplyPriorityEntity() {
        return applyPriorityEntity;
    }

    public void setApplyPriorityEntity(ApplyPriorityEntity applyPriorityEntity) {
        this.applyPriorityEntity = applyPriorityEntity;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getEnteringPersonId() {
        return enteringPersonId;
    }

    public void setEnteringPersonId(String enteringPersonId) {
        this.enteringPersonId = enteringPersonId;
    }

    public String getAnalysisPersonId() {
        return analysisPersonId;
    }

    public void setAnalysisPersonId(String analysisPersonId) {
        this.analysisPersonId = analysisPersonId;
    }

    public String getAuditPersonId() {
        return auditPersonId;
    }

    public void setAuditPersonId(String auditPersonId) {
        this.auditPersonId = auditPersonId;
    }

    public Date getApplyResultTime() {
        return applyResultTime;
    }

    public void setApplyResultTime(Date applyResultTime) {
        this.applyResultTime = applyResultTime;
    }

    public Date getApplyCompleteTime() {
        return applyCompleteTime;
    }

    public void setApplyCompleteTime(Date applyCompleteTime) {
        this.applyCompleteTime = applyCompleteTime;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getApplyStatusId() {
        return applyStatusId;
    }

    public void setApplyStatusId(String applyStatusId) {
        this.applyStatusId = applyStatusId;
    }

    public String getAttchmentId() {
        return attchmentId;
    }

    public void setAttchmentId(String attchmentId) {
        this.attchmentId = attchmentId;
    }

//    public String getApplyCenterProvince() {
//        return applyCenterProvince;
//    }
//
//    public void setApplyCenterProvince(String applyCenterProvince) {
//        this.applyCenterProvince = applyCenterProvince;
//    }
}
