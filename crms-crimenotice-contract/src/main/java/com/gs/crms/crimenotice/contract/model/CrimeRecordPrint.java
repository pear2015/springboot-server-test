package com.gs.crms.crimenotice.contract.model;

import com.gs.crms.common.model.AttachmentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangqiang on 2017/11/11.
 * 犯罪公告打印对象
 */
public class CrimeRecordPrint {
    /**
     * 公告信息
     */
    private NoticeInfo noticeInfo;
    /**
     * 犯罪信息
     */
    private CrimeInfo crimeInfo;
    /**
     * 添加附件
     */
    private List<AttachmentInfo> attachmentInfo=new ArrayList<>();

    public NoticeInfo getNoticeInfo() {
        return noticeInfo;
    }

    public void setNoticeInfo(NoticeInfo noticeInfo) {
        this.noticeInfo = noticeInfo;
    }

    public CrimeInfo getCrimeInfo() {
        return crimeInfo;
    }

    public void setCrimeInfo(CrimeInfo crimeInfo) {
        this.crimeInfo = crimeInfo;
    }

    public List<AttachmentInfo> getAttachmentInfo() {
        return attachmentInfo;
    }

    public void setAttachmentInfo(List<AttachmentInfo> attachmentInfo) {
        this.attachmentInfo = attachmentInfo;
    }
}
