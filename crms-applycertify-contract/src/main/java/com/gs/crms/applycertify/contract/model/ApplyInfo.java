package com.gs.crms.applycertify.contract.model;


import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 申请基本信息
 *
 * @author zhangqiang
 * @version 1.0
 * @created 22 -八月-2017 14:24:33
 */
public class ApplyInfo {
    private String applyId;
    @Size(max = 2000, message = "analysisDescription max length is 2000")
    private String analysisDescription;

    @Size(max = 128, message = "analysisPersonName max length is 128")
    private String analysisPersonName;

    @Size(max = 36, message = "analysisResultId max length is 36")
    private String analysisResultId;

    private Date analysisTime;
    private String applyBasicId;

    @Size(max = 256, message = "applyCenterName max length is 256")
    private String applyCenterName;

    @Size(max = 2000, message = "applyDescription max length is 2000")
    private String applyDescription;

    @Size(max = 36, message = "applyPurposeId max length is 36")
    private String applyPurposeId;

    @Size(max = 36, message = "applyStatusId max length is 36")
    private String applyStatusId;

    private Date applyTime;

    @Size(max = 36, message = "applyTypeId max length is 36")
    private String applyTypeId;

    @Size(max = 2000, message = "attchmentId max length is 2000")
    private String attchmentId;

    @Size(max = 2000, message = "auditDescription max length is 2000")
    private String auditDescription;

    @Size(max = 255, message = "auditPersonName max length is 255")
    private String auditPersonName;

    @Size(max = 36, message = "auditResultId max length is 36")
    private String auditResultId;

    private Date auditTime;

    @Size(max = 128, message = "deliveryReceiptNumbr max length is 128")
    private String deliveryReceiptNumbr;

    @Size(max = 128, message = "cityName max length is 128")
    private String cityName;

    @Size(max = 128, message = "communityName max length is 128")
    private String communityName;

    private int crimeRecord;

    @Size(max = 128, message = "govermentInfo max length is 128")
    private String govermentInfo;

    @Size(max = 128, message = "govermentProcess max length is 128")
    private String govermentProcess;

    @Size(max = 2000, message = "modifyDescription max length is 2000")
    private String modifyDescription;

    @Size(max = 256, message = "modifyPersonName max length is 256")
    private String modifyPersonName;

    private Date modifyTime;

    @Size(max = 2000, message = "note max length is 2000")
    private String note;

    @Size(max = 128, message = "provinceName max length is 128")
    private String provinceName;

    @Size(max = 128, message = "applyPersonType max length is 128")
    private String applyPersonType;

    @Size(max = 128, message = "agentIdNumber max length is 128")
    private String agentIdNumber;

    @Size(max = 36, message = "agentIdType max length is 36")
    private String agentIdType;

    private String agentIdTypeName;

    @Size(max = 2000, message = "otherPurposeReason max length is 2000")
    private String otherPurposeReason;

    @Size(max = 2000, message = "auditRejectReason max length is 2000")
    private String auditRejectReason;

    private String applyPurposeName;
    private String applyStatusName;
    private String applyTypeName;

    @Size(max = 128, message = "enteringPersonName max length is 128")
    private String enteringPersonName;

    private String analysisResultName;
    private String auditResultName;
    private String applyResultId;
    private String applyResultName;
    private Date changeTime;

    @Size(max = 36, message = "priority max length is 36")
    private String priority;

    private String priorityName;

    @Size(max = 128, message = "applyCenterId max length is 128")
    private String applyCenterId;
    @Size(max = 128, message = "applyCenterProvince max length is 128")
    private String applyCenterProvince;

    @Size(max = 36, message = "enteringPersonId max length is 36")
    private String enteringPersonId;

    @Size(max = 36, message = "analysisPersonId max length is 36")
    private String analysisPersonId;

    @Size(max = 36, message = "auditPersonId max length is 36")
    private String auditPersonId;

    private Date credentialsPrintTime;
    private Date applyResultTime;
    private Date applyCompleteTime;

    @Size(max = 128, message = "certificateNumber max length is 128")
    private String certificateNumber;

    @Size(max = 36, message = "certificateType max length is 36")
    private String certificateType;

    @Size(max = 128, message = "lastName max length is 128")
    private String lastName;

    @Size(max = 128, message = "firstName max length is 128")
    private String firstName;

    private String certificateName;
    /**
     * 分派时间
     */
    private Date apportionTime;

    /**
     * 工作流流程号
     */
    private String workflowId;

    public Date getApplyCompleteTime() {
        return applyCompleteTime;
    }

    public void setApplyCompleteTime(Date applyCompleteTime) {
        this.applyCompleteTime = applyCompleteTime;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getAnalysisDescription() {
        return analysisDescription;
    }

    public void setAnalysisDescription(String analysisDescription) {
        this.analysisDescription = analysisDescription;
    }

    public String getAnalysisPersonName() {
        return analysisPersonName;
    }

    public void setAnalysisPersonName(String analysisPersonName) {
        this.analysisPersonName = analysisPersonName;
    }

    public String getAnalysisResultId() {
        return analysisResultId;
    }

    public void setAnalysisResultId(String analysisResultId) {
        this.analysisResultId = analysisResultId;
    }

    public Date getAnalysisTime() {
        return analysisTime;
    }

    public void setAnalysisTime(Date analysisTime) {
        this.analysisTime = analysisTime;
    }

    public String getApplyBasicId() {
        return applyBasicId;
    }

    public void setApplyBasicId(String applyBasicId) {
        this.applyBasicId = applyBasicId;
    }

    public String getApplyCenterName() {
        return applyCenterName;
    }

    public void setApplyCenterName(String applyCenterName) {
        this.applyCenterName = applyCenterName;
    }

    public String getApplyDescription() {
        return applyDescription;
    }

    public void setApplyDescription(String applyDescription) {
        this.applyDescription = applyDescription;
    }

    public String getApplyPurposeId() {
        return applyPurposeId;
    }

    public void setApplyPurposeId(String applyPurposeId) {
        this.applyPurposeId = applyPurposeId;
    }

    public String getApplyStatusId() {
        return applyStatusId;
    }

    public void setApplyStatusId(String applyStatusId) {
        this.applyStatusId = applyStatusId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyTypeId() {
        return applyTypeId;
    }

    public void setApplyTypeId(String applyTypeId) {
        this.applyTypeId = applyTypeId;
    }

    public String getAttchmentId() {
        return attchmentId;
    }

    public void setAttchmentId(String attchmentId) {
        this.attchmentId = attchmentId;
    }

    public String getAuditDescription() {
        return auditDescription;
    }

    public void setAuditDescription(String auditDescription) {
        this.auditDescription = auditDescription;
    }

    public String getAuditPersonName() {
        return auditPersonName;
    }

    public void setAuditPersonName(String auditPersonName) {
        this.auditPersonName = auditPersonName;
    }

    public String getAuditResultId() {
        return auditResultId;
    }

    public void setAuditResultId(String auditResultId) {
        this.auditResultId = auditResultId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getDeliveryReceiptNumbr() {
        return deliveryReceiptNumbr;
    }

    public void setDeliveryReceiptNumbr(String deliveryReceiptNumbr) {
        this.deliveryReceiptNumbr = deliveryReceiptNumbr;
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

    public int getCrimeRecord() {
        return crimeRecord;
    }

    public void setCrimeRecord(int crimeRecord) {
        this.crimeRecord = crimeRecord;
    }

    public String getGovermentInfo() {
        return govermentInfo;
    }

    public void setGovermentInfo(String govermentInfo) {
        this.govermentInfo = govermentInfo;
    }

    public String getGovermentProcess() {
        return govermentProcess;
    }

    public void setGovermentProcess(String govermentProcess) {
        this.govermentProcess = govermentProcess;
    }

    public String getModifyDescription() {
        return modifyDescription;
    }

    public void setModifyDescription(String modifyDescription) {
        this.modifyDescription = modifyDescription;
    }

    public String getModifyPersonName() {
        return modifyPersonName;
    }

    public void setModifyPersonName(String modifyPersonName) {
        this.modifyPersonName = modifyPersonName;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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

    public String getAgentIdType() {
        return agentIdType;
    }

    public void setAgentIdType(String agentIdType) {
        this.agentIdType = agentIdType;
    }

    public String getAgentIdTypeName() {
        return agentIdTypeName;
    }

    public void setAgentIdTypeName(String agentIdTypeName) {
        this.agentIdTypeName = agentIdTypeName;
    }

    public String getOtherPurposeReason() {
        return otherPurposeReason;
    }

    public void setOtherPurposeReason(String otherPurposeReason) {
        this.otherPurposeReason = otherPurposeReason;
    }

    public String getAuditRejectReason() {
        return auditRejectReason;
    }

    public void setAuditRejectReason(String auditRejectReason) {
        this.auditRejectReason = auditRejectReason;
    }

    public String getApplyPurposeName() {
        return applyPurposeName;
    }

    public void setApplyPurposeName(String applyPurposeName) {
        this.applyPurposeName = applyPurposeName;
    }

    public String getApplyStatusName() {
        return applyStatusName;
    }

    public void setApplyStatusName(String applyStatusName) {
        this.applyStatusName = applyStatusName;
    }

    public String getApplyTypeName() {
        return applyTypeName;
    }

    public void setApplyTypeName(String applyTypeName) {
        this.applyTypeName = applyTypeName;
    }

    public String getEnteringPersonName() {
        return enteringPersonName;
    }

    public void setEnteringPersonName(String enteringPersonName) {
        this.enteringPersonName = enteringPersonName;
    }

    public String getAnalysisResultName() {
        return analysisResultName;
    }

    public void setAnalysisResultName(String analysisResultName) {
        this.analysisResultName = analysisResultName;
    }

    public String getAuditResultName() {
        return auditResultName;
    }

    public void setAuditResultName(String auditResultName) {
        this.auditResultName = auditResultName;
    }

    public String getApplyResultId() {
        return applyResultId;
    }

    public void setApplyResultId(String applyResultId) {
        this.applyResultId = applyResultId;
    }

    public String getApplyResultName() {
        return applyResultName;
    }

    public void setApplyResultName(String applyResultName) {
        this.applyResultName = applyResultName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }

    public String getApplyCenterId() {
        return applyCenterId;
    }

    public void setApplyCenterId(String applyCenterId) {
        this.applyCenterId = applyCenterId;
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

    public Date getCredentialsPrintTime() {
        return credentialsPrintTime;
    }

    public void setCredentialsPrintTime(Date credentialsPrintTime) {
        this.credentialsPrintTime = credentialsPrintTime;
    }

    public Date getApplyResultTime() {
        return applyResultTime;
    }

    public void setApplyResultTime(Date applyResultTime) {
        this.applyResultTime = applyResultTime;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public Date getApportionTime() {
        return apportionTime;
    }

    public void setApportionTime(Date apportionTime) {
        this.apportionTime = apportionTime;
    }

    public String getApplyCenterProvince() {
        return applyCenterProvince;
    }

    public void setApplyCenterProvince(String applyCenterProvince) {
        this.applyCenterProvince = applyCenterProvince;
    }
}