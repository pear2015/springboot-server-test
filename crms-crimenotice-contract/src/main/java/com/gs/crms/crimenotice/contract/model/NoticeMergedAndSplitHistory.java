package com.gs.crms.crimenotice.contract.model;

import java.util.Date;

/**
 * Created by zhangqiang on 2017/11/11.
 * 公告合并拆分历史记录
 */
public class NoticeMergedAndSplitHistory {
    /**
     * Id
     */
    private String id;
    /**
     * 公告Id
     */
    private String noticeId;
    /**
     * 合并罪犯id集合
     */
    private String mergedCriminalIds;
    /**
     * 拆分罪犯id集合
     */
    private String splitCriminalIds;
    /**
     * 操作员Id
     */
    private String operatorId;
    /**
     * 审核员Id
     */
    private String auditorId;
    /**
     * 操作时间
     */
    private Date operatorTime;
    /**
     * 目标罪犯
     */
    private String objectCriminalId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getMergedCriminalIds() {
        return mergedCriminalIds;
    }

    public void setMergedCriminalIds(String mergedCriminalIds) {
        this.mergedCriminalIds = mergedCriminalIds;
    }

    public String getSplitCriminalIds() {
        return splitCriminalIds;
    }

    public void setSplitCriminalIds(String splitCriminalIds) {
        this.splitCriminalIds = splitCriminalIds;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getObjectCriminalId() {
        return objectCriminalId;
    }

    public void setObjectCriminalId(String objectCriminalId) {
        this.objectCriminalId = objectCriminalId;
    }
}
