package com.gs.crms.crimenotice.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by zhangqiang on 2017/11/11.
 * 公告合并拆分历史记录表
 */
@Entity
@Table(name = "h_notice_merge_split", schema = "crms_crime")
public class NoticeMergedAndSplitHistoryEntity {
    /**
     * Id
     */
    @Id
    @Column(name = "id", length = 36, nullable = false)
    private String id;
    /**
     * 公告Id
     */
    @Column(name = "notice_id", length = 36)
    private String noticeId;
    /**
     * 合并罪犯id集合
     */
    @Column(name = "merged_criminal_ids", length = 2000)
    private String mergedCriminalIds;
    /**
     * 拆分罪犯id集合
     */
    @Column(name = "split_criminal_ids", length = 2000)
    private String splitCriminalIds;
    /**
     * 操作员Id
     */
    @Column(name = "operator_id", length = 36)
    private String operatorId;
    /**
     * 审核员Id
     */
    @Column(name = "auditor_id", length = 36)
    private String auditorId;
    /**
     * 操作时间
     */
    @Column(name = "operator_time")
    private Date operatorTime;
    /**
     * 目标罪犯ID
     */
    @Column(name = "object_criminal_id")
    private String objectCriminalId;

    /**
     * Get
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Set
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get
     *
     * @return
     */
    public String getNoticeId() {
        return noticeId;
    }

    /**
     * Set
     *
     * @param noticeId
     */
    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    /**
     * Get
     *
     * @return
     */
    public String getMergedCriminalIds() {
        return mergedCriminalIds;
    }

    /**
     * Set
     *
     * @param mergedCriminalIds
     */
    public void setMergedCriminalIds(String mergedCriminalIds) {
        this.mergedCriminalIds = mergedCriminalIds;
    }

    /**
     * Get
     *
     * @return
     */
    public String getSplitCriminalIds() {
        return splitCriminalIds;
    }

    /**
     * Set
     *
     * @param splitCriminalIds
     */
    public void setSplitCriminalIds(String splitCriminalIds) {
        this.splitCriminalIds = splitCriminalIds;
    }

    /**
     * Get
     *
     * @return
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     * Set
     *
     * @param operatorId
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * Get
     *
     * @return
     */
    public String getAuditorId() {
        return auditorId;
    }

    /**
     * Set
     */
    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId;
    }

    /**
     * Get
     *
     * @return
     */
    public Date getOperatorTime() {
        return operatorTime;
    }

    /**
     * Set
     */
    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    /**
     * Get
     *
     * @return
     */
    public String getObjectCriminalId() {
        return objectCriminalId;
    }

    /**
     * Set
     */
    public void setObjectCriminalId(String objectCriminalId) {
        this.objectCriminalId = objectCriminalId;
    }
}
