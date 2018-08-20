package com.gs.crms.crimenotice.contract.model;

/**
 * Created by zhangqiang on 2017/11/11.
 * 待合并罪犯关联
 */
public class WaitMergingCriminalRelation {
    /**
     * Id
     */
    private String id;
    /**
     * 公告Id
     */
    private String noticeId;
    /**
     * 等待分派罪犯Id列表
     */
    private String waitCriminalIds;
    /**
     * 目标罪犯Id
     */
    private String targetCriminalId;

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

    public String getWaitCriminalIds() {
        return waitCriminalIds;
    }

    public void setWaitCriminalIds(String waitCriminalIds) {
        this.waitCriminalIds = waitCriminalIds;
    }

    public String getTargetCriminalId() {
        return targetCriminalId;
    }

    public void setTargetCriminalId(String targetCriminalId) {
        this.targetCriminalId = targetCriminalId;
    }
}
