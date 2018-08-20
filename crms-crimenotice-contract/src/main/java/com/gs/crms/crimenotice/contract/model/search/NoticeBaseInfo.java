package com.gs.crms.crimenotice.contract.model.search;

/**
 * Created by zhengyali on 2017/10/30.
 */
public class NoticeBaseInfo {
    /**
     * 公告id
     * */
    private String noticeId;
    /**
     * 公告号
     */
    private String noticeNumber;

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
}
