package com.gs.crms.crimenotice.contract.model;

/**
 * Created by zhangqiang on 2017/11/11.
 * 以合并罪犯记录表，可用于拆分
 */
public class MergedCriminal {
    /**
     * Id
     */
    private String id;
    /**
     * 公告Id
     */
    private String noticeId;
    /**
     * 已合并罪犯Id
     */
    private String criminalId;
    /**
     * 已合并罪犯的公告Id列表
     */
    private String criminalNoticeIds;
    /**
     * 目标罪犯ID
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

    public String getCriminalId() {
        return criminalId;
    }

    public void setCriminalId(String criminalId) {
        this.criminalId = criminalId;
    }

    public String getCriminalNoticeIds() {
        return criminalNoticeIds;
    }

    public void setCriminalNoticeIds(String criminalNoticeIds) {
        this.criminalNoticeIds = criminalNoticeIds;
    }

    public String getObjectCriminalId() {
        return objectCriminalId;
    }

    public void setObjectCriminalId(String objectCriminalId) {
        this.objectCriminalId = objectCriminalId;
    }
}
