package com.gs.crms.crimenotice.contract.model;

/**
 * Created by zhengyali on 2017/10/31.
 */
public class NoticeApportion {
    private  String noticeId;
    private  String auditorId;
    private  String status;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
