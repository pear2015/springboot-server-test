package com.gs.crms.crimenotice.service.entity;

import com.gs.crms.applycertify.service.entity.dictionary.AuditResultEntity;
import com.gs.crms.crimenotice.service.entity.dictionary.CourtEntity;
import com.gs.crms.crimenotice.service.entity.dictionary.NoticeInputStatusEntity;
import com.gs.crms.crimenotice.service.entity.dictionary.NoticePriorityEntity;
import com.gs.crms.crimenotice.service.entity.dictionary.NoticeStatusEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 公告信息
 * Created by tanjie on 2017/7/10.
 */
@Entity
@Table(name = "b_notice_info", schema = "crms_crime",indexes = {@Index(name = "crime_Person_idIndex",columnList = "crime_Person_id"),@Index(name = "crime_idIndex",columnList = "crime_id")})
public class NoticeEntity extends BaseEntity {

    @Id
    @Column(name = "id", length = 36, nullable = false)
    private String noticeId;

    /*
     * 外键关联犯罪基本信息人员
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crime_Person_id", insertable = false, updatable = false)
    private CrimePersonInfoEntity crimePersonInfoEntity;
    /*
     * 犯罪人员基本ID
     */
    @Column(name = "crime_Person_id", nullable = false, length = 36)
    private String crimePersonId;

    /*
    外键关联犯罪
    */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crime_id", insertable = false, updatable = false)
    private CrimeEntity crimeEntity;

    /*
    犯罪ID
     */
    @Column(name = "crime_id", nullable = false, length = 36)
    private String crimeId;
    /*
    外键关联法院
    */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "court_id", insertable = false, updatable = false)
    private CourtEntity courtEntity;
    /*
    法院ID
     */
    @Column(name = "court_id", nullable = false, length = 36)
    private String courtId;

    /*
    公告号
    */
    @Column(name = "notice_number", nullable = false, length = 128)
    private String noticeNumber;

    /*
     * 公告的发布时间)
     */
    @Column(name = "notice_create_time", nullable = false)
    private Date noticeCreateTime;

    /*
    *公告描述
    */
    @Column(name = "notice_description", length = 2000)
    private String noticeDescription;
    /*
     备注
      */
    @Column(name = "note", length = 2000)
    private String note;
    /*
     附件ID集合以,分隔 如：aaa,bbb,ccc
      */
    @Column(name = "attachment_id", length = 2000)
    private String attchmentId;

    /**
     * 录入意见
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "input_status", insertable = false, updatable = false)
    private NoticeInputStatusEntity noticeInputStatusEntity;

    /**
     * 公告录入状态
     */
    @Column(name = "input_status", nullable = false, length = 36)
    private String noticeInputStatus;

    /**
     * 拒绝录入原因
     */
    @Column(name = "reject_enter_reason", length = 2000)
    private String rejectEnterReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority", insertable = false, updatable = false)
    private NoticePriorityEntity noticePriorityEntity;
    /**
     * 优先级
     */
    @Column(name = "priority", length = 36)
    private String priority;

    /**
     * 删除标志（0代表保存、1代表删除）
     */
    @Column(name = "isactive", length = 36)
    private String isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status", insertable = false, updatable = false)
    private NoticeStatusEntity noticeStatusEntity;

    /**
     * 公告状态
     * 1 已提交
     * 2 驳回
     * 3 已通过
     * 4 已拒绝
     * 5 失效
     */
    @Column(name = "status", length = 36)
    private String status;

    /**
     * 审核员ID
     */
    @Column(name = "audit_person_id", length = 36)
    private String auditPersonId;

    /**
     * 审核员姓名
     */
    @Column(name = "audit_person_name", length = 128)
    private String auditPersonName;

    /**
     * 审核结果ID  1审核通过 2 审核不通过
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "audit_result_id", insertable = false, updatable = false)
    private AuditResultEntity auditResultEntity;
    /**
     * 审核结果ID  1审核通过 2 审核不通过
     */
    @Column(name = "audit_result_id", length = 36)
    private String auditResultId;

    /**
     * 审核时间
     */
    @Column(name = "audit_time")
    private Date auditTime;
    /**
     * 审核描述
     */
    @Column(name = "audit_description", length = 2000)
    private String auditDescription;
    /**
     * 工作流
     */
    @Column(name = "work_flow_id", length = 128)
    private String workFlowId;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public CrimePersonInfoEntity getCrimePersonInfoEntity() {
        return crimePersonInfoEntity;
    }

    public void setCrimePersonInfoEntity(CrimePersonInfoEntity crimePersonInfoEntity) {
        this.crimePersonInfoEntity = crimePersonInfoEntity;
    }

    public String getCrimePersonId() {
        return crimePersonId;
    }

    public void setCrimePersonId(String crimePersonId) {
        this.crimePersonId = crimePersonId;
    }

    public CrimeEntity getCrimeEntity() {
        return crimeEntity;
    }

    public void setCrimeEntity(CrimeEntity crimeEntity) {
        this.crimeEntity = crimeEntity;
    }

    public String getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(String crimeId) {
        this.crimeId = crimeId;
    }

    public CourtEntity getCourtEntity() {
        return courtEntity;
    }

    public void setCourtEntity(CourtEntity courtEntity) {
        this.courtEntity = courtEntity;
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

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public NoticeStatusEntity getNoticeStatusEntity() {
        return noticeStatusEntity;
    }

    public void setNoticeStatusEntity(NoticeStatusEntity noticeStatusEntity) {
        this.noticeStatusEntity = noticeStatusEntity;
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

    public NoticeInputStatusEntity getNoticeInputStatusEntity() {
        return noticeInputStatusEntity;
    }

    public void setNoticeInputStatusEntity(NoticeInputStatusEntity noticeInputStatusEntity) {
        this.noticeInputStatusEntity = noticeInputStatusEntity;
    }

    public String getNoticeInputStatus() {
        return noticeInputStatus;
    }

    public void setNoticeInputStatus(String noticeInputStatus) {
        this.noticeInputStatus = noticeInputStatus;
    }

    public String getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(String workFlowId) {
        this.workFlowId = workFlowId;
    }

    public AuditResultEntity getAuditResultEntity() {
        return auditResultEntity;
    }

    public void setAuditResultEntity(AuditResultEntity auditResultEntity) {
        this.auditResultEntity = auditResultEntity;
    }

    public NoticePriorityEntity getNoticePriorityEntity() {
        return noticePriorityEntity;
    }

    public void setNoticePriorityEntity(NoticePriorityEntity noticePriorityEntity) {
        this.noticePriorityEntity = noticePriorityEntity;
    }
}
