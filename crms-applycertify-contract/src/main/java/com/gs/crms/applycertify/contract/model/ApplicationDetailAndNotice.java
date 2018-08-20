package com.gs.crms.applycertify.contract.model;

import com.gs.crms.crimenotice.contract.model.search.CrimeAndNoticeExtendInfo;

import java.util.List;

/**
 * Created by zhangqiang on 2017/9/28.
 * 申请详情和公告详情关联
 */
public class ApplicationDetailAndNotice {
    /**
     * 申请详情
     */
    private CertificateApplyInfo certificateApplyInfo;
    /**
     * 公告详情
     */
    private List<CrimeAndNoticeExtendInfo> crimeAndNoticeExtendInfoList;

    /**
     * Get
     *
     * @return
     */
    public CertificateApplyInfo getCertificateApplyInfo() {
        return certificateApplyInfo;
    }

    /**
     * Set
     *
     * @param certificateApplyInfo
     */
    public void setCertificateApplyInfo(CertificateApplyInfo certificateApplyInfo) {
        this.certificateApplyInfo = certificateApplyInfo;
    }

    /**
     * Get
     *
     * @return
     */
    public List<CrimeAndNoticeExtendInfo> getCrimeAndNoticeExtendInfoList() {
        return crimeAndNoticeExtendInfoList;
    }

    /**
     * Set
     *
     * @param crimeAndNoticeExtendInfoList
     */
    public void setCrimeAndNoticeExtendInfoList(List<CrimeAndNoticeExtendInfo> crimeAndNoticeExtendInfoList) {
        this.crimeAndNoticeExtendInfoList = crimeAndNoticeExtendInfoList;
    }
}
