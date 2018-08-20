package com.gs.crms.crimenotice.contract.model.search;

import com.gs.crms.common.model.AttachmentInfo;
import com.gs.crms.crimenotice.contract.model.CrimeInfo;
import com.gs.crms.crimenotice.contract.model.NoticeInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengyali on 2017/10/30.
 * 公告详情
 */
public class CrimeAndNoticeListInfo {
    private CrimeInfo crimeInfo;
    private NoticeInfo noticeInfo;
    private List<AttachmentInfo> attachmentInfo=new ArrayList<>();
    private List<NoticeInfo> noticeInfoList = new ArrayList<>();

    public CrimeInfo getCrimeInfo() {
        return crimeInfo;
    }

    public void setCrimeInfo(CrimeInfo crimeInfo) {
        this.crimeInfo = crimeInfo;
    }

    public NoticeInfo getNoticeInfo() {
        return noticeInfo;
    }

    public void setNoticeInfo(NoticeInfo noticeInfo) {
        this.noticeInfo = noticeInfo;
    }

    public List<AttachmentInfo> getAttachmentInfo() {
        return attachmentInfo;
    }

    public void setAttachmentInfo(List<AttachmentInfo> attachmentInfo) {
        this.attachmentInfo = attachmentInfo;
    }

    public List<NoticeInfo> getNoticeInfoList() {
        return noticeInfoList;
    }

    public void setNoticeInfoList(List<NoticeInfo> noticeInfoList) {
        this.noticeInfoList = noticeInfoList;
    }
}
