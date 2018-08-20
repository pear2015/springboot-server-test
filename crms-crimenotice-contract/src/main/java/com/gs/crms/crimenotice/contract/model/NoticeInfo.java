package com.gs.crms.crimenotice.contract.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.beans.Transient;
import java.util.Date;

/**
 * Created by tanjie on 2017/7/28.
 */
public class NoticeInfo {
    /**
     * 公告id
     */
    private String noticeId;
    /**
     * 犯罪人Id
     */
    @Size(max=36)
    private String crimePersonId;
    /**
     * 姓
     */
    private String firstName;
    /**
     * 名
     */
    private String lastName;

    /**
     * 犯罪信息id
     */
    @Size(max=36)
    private String crimeId;
    /***
     *法院id
     */
    @NotNull
    @Size(max=36)
    private String courtId;
    /***
     *法院名称
     */
    private String courtName;
    /**
     * 公告号
     */
    @NotNull
    @Size(max=128)
    private String noticeNumber;
    /**
     * 公告发布时间
     */
    private Date noticeCreateTime;

    /**
     * 公告描述
     */
    @Size(max=2000)
    private String noticeDescription;
    /**
     * 备注
     */
    @Size(max=2000)
    private String note;
    /**
     * 附件ID集合以,分隔 如：aaa,bbb,ccc
     */
    @Size(max=2000)
    private String attchmentId;

    /**
     * 拒绝录入原因
     */
    @Size(max=2000)
    private String rejectEnterReason;
    /**
     * 优先级
     */
    @NotNull
    @Size(max=36)
    private String priority;

    /**
     * 优先级名称
     */
    private String priorityName;

    /**
     * 删除标志（0代表保存、1代表删除）
     */
    @Size(max=36)
    private String isActive;

    /**
     * 公告状态
     */
    private String status;
    /**
     * 公告状态
     */
    private String statusName;

    /**
     * 审核员ID
     */
    @Size(max=36)
    private String auditPersonId;

    /**
     * 审核员姓名
     */
    @Size(max=128)
    private String auditPersonName;
    /**
     * 审核结果ID  1 同意录入  2 拒绝录入
     */
    @Size(max=36)
    private String auditResultId;
    /**
     * 审核结果
     */

    private String auditResultName;

    /**
     * 审核时间
     */
    private Date auditTime;
    /**
     * 审核描述
     */
    @Size(max=2000)
    private String auditDescription;
    /**
     * 录入人ID
     */
    @Size(max=36)
    private String enterPersonId;
    /**
     * 录入时间
     */
    private Date enteringTime;
    /**
     * 录入人员姓名
     */
    @Size(max=128)
    private String enteringPersonName;
    /**
     * 归档员录入意见
     */
    @NotNull
    @Size(max=36)
    private String noticeInputStatus;
    /**
     * 归档员录入意见名称
     */
    private String noticeInputStatusName;
    /**
     * 修改人员姓名
     */
    @Size(max=128)
    private String modifyPersonName;
    /**
     * 修改人员Id
     */
    @Size(max=36)
    private String modifyPersonId;

    /**
     * 删除人Id
     */
    private String deletePersonId;

    /**
     * 删除人姓名
     */
    private String deletePersonName;
    /**
     * 工作流Id
     */
    private String workFlowId;
    /**
     * 证件编号
     */
    private String certificateNumber;
    /**
     * 分派时间
     */
    private Date apportionTime;

    /**
     * 提交审核时 数据检验
     *
     * @return
     */
    public Boolean checkDataOnSubmitAudit() {
        if (getNoticeId() == null || getAuditPersonId() == null || getAuditResultId() == null || getAuditDescription() == null) {
            return false;
        }
        return true;
    }

    /**
     * 编辑优先级时，验证
     *
     * @return
     */
    public Boolean checkDataOnEditPriority() {
        if (getNoticeId() == null || getPriority() == null) {
            return false;
        }
        return true;
    }

    /**
     * 提交分析时 数据检验
     *
     * @return
     */
    @Transient
    public Boolean checkDataOnSubmitAnalyst() {
        if (getNoticeId() == null || getNoticeInputStatus() == null) {
            return false;
        }
        return true;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getCrimePersonId() {
        return crimePersonId;
    }

    public void setCrimePersonId(String crimePersonId) {
        this.crimePersonId = crimePersonId;
    }

    public String getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(String crimeId) {
        this.crimeId = crimeId;
    }

    public String getCourtId() {
        return courtId;
    }

    public void setCourtId(String courtId) {
        this.courtId = courtId;
    }

    public String getNoticeNumber() {
        return noticeNumber;
    }

    public void setNoticeNumber(String noticeNumber) {
        this.noticeNumber = noticeNumber;
    }

    public Date getNoticeCreateTime() {
        return noticeCreateTime;
    }

    public void setNoticeCreateTime(Date noticeCreateTime) {
        this.noticeCreateTime = noticeCreateTime;
    }

    public String getNoticeDescription() {
        return noticeDescription;
    }

    public void setNoticeDescription(String noticeDescription) {
        this.noticeDescription = noticeDescription;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAttchmentId() {
        return attchmentId;
    }

    public void setAttchmentId(String attchmentId) {
        this.attchmentId = attchmentId;
    }

    public String getRejectEnterReason() {
        return rejectEnterReason;
    }

    public void setRejectEnterReason(String rejectEnterReason) {
        this.rejectEnterReason = rejectEnterReason;
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

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuditPersonId() {
        return auditPersonId;
    }

    public void setAuditPersonId(String auditPersonId) {
        this.auditPersonId = auditPersonId;
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

    public String getAuditDescription() {
        return auditDescription;
    }

    public void setAuditDescription(String auditDescription) {
        this.auditDescription = auditDescription;
    }

    public String getEnterPersonId() {
        return enterPersonId;
    }

    public void setEnterPersonId(String enterPersonId) {
        this.enterPersonId = enterPersonId;
    }

    public Date getEnteringTime() {
        return enteringTime;
    }

    public void setEnteringTime(Date enteringTime) {
        this.enteringTime = enteringTime;
    }

    public String getEnteringPersonName() {
        return enteringPersonName;
    }

    public void setEnteringPersonName(String enteringPersonName) {
        this.enteringPersonName = enteringPersonName;
    }

    public String getNoticeInputStatus() {
        return noticeInputStatus;
    }

    public void setNoticeInputStatus(String noticeInputStatus) {
        this.noticeInputStatus = noticeInputStatus;
    }

    public String getModifyPersonName() {
        return modifyPersonName;
    }

    public void setModifyPersonName(String modifyPersonName) {
        this.modifyPersonName = modifyPersonName;
    }

    public String getModifyPersonId() {
        return modifyPersonId;
    }

    public void setModifyPersonId(String modifyPersonId) {
        this.modifyPersonId = modifyPersonId;
    }

    public String getDeletePersonId() {
        return deletePersonId;
    }

    public void setDeletePersonId(String deletePersonId) {
        this.deletePersonId = deletePersonId;
    }

    public String getDeletePersonName() {
        return deletePersonName;
    }

    public void setDeletePersonName(String deletePersonName) {
        this.deletePersonName = deletePersonName;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(String workFlowId) {
        this.workFlowId = workFlowId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getNoticeInputStatusName() {
        return noticeInputStatusName;
    }

    public void setNoticeInputStatusName(String noticeInputStatusName) {
        this.noticeInputStatusName = noticeInputStatusName;
    }

    public String getAuditResultName() {
        return auditResultName;
    }

    public void setAuditResultName(String auditResultName) {
        this.auditResultName = auditResultName;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public Date getApportionTime() {
        return apportionTime;
    }

    public void setApportionTime(Date apportionTime) {
        this.apportionTime = apportionTime;
    }
}
