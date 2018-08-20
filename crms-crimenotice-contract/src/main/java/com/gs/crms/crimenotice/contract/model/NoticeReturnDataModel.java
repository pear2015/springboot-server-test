package com.gs.crms.crimenotice.contract.model;
import java.util.Date;

/**
 * Created by zhengyali on 2018/3/14.
 */
public class NoticeReturnDataModel {
    /**
     * 公告id
     */
    private String noticeId;

    /**
     * 公告号
     */
    private String noticeNumber;

    /**
     * 公告发布时间
     */
    private Date noticeCreateTime;

    /**
     * 拒绝录入原因
     */
    private String rejectEnterReason;

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
    private String auditPersonId;

    /**
     * 审核员姓名
     */
    private String auditPersonName;
    /**
     * 审核结果ID  1 同意录入  2 拒绝录入
     */
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
    private String auditDescription;
    /**
     * 录入人ID
     */
    private String enterPersonId;
    /**
     * 录入时间
     */
    private Date enteringTime;
    /**
     * 录入人员姓名
     */
    private String enteringPersonName;
    /**
     * 归档员录入意见
     */
    private String noticeInputStatus;
    /**
     * 归档员录入意见名称
     */
    private String noticeInputStatusName;

    /**
     * 工作流Id
     */
    private String workFlowId;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
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

    public String getRejectEnterReason() {
        return rejectEnterReason;
    }

    public void setRejectEnterReason(String rejectEnterReason) {
        this.rejectEnterReason = rejectEnterReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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

    public String getAuditResultName() {
        return auditResultName;
    }

    public void setAuditResultName(String auditResultName) {
        this.auditResultName = auditResultName;
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

    public String getNoticeInputStatusName() {
        return noticeInputStatusName;
    }

    public void setNoticeInputStatusName(String noticeInputStatusName) {
        this.noticeInputStatusName = noticeInputStatusName;
    }

    public String getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(String workFlowId) {
        this.workFlowId = workFlowId;
    }
}
