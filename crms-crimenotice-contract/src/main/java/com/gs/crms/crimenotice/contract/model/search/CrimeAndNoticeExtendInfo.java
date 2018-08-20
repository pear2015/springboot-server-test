package com.gs.crms.crimenotice.contract.model.search;


import com.gs.crms.common.utils.JavaBeanValidationUtil;
import com.gs.crms.common.model.AttachmentInfo;
import com.gs.crms.crimenotice.contract.model.CrimeInfo;
import com.gs.crms.crimenotice.contract.model.CrimePersonInfo;
import com.gs.crms.crimenotice.contract.model.NoticeInfo;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanjie on 2017/7/28.
 */
public class CrimeAndNoticeExtendInfo {
    private CrimeInfo crimeInfo;
    private CrimePersonInfo crimePersonInfo;
    private NoticeInfo noticeInfo;
    private List<AttachmentInfo> attachmentInfo= new ArrayList<>();
    /**
     * 等待合并罪犯ID列表
     */
    private List<String> waitCriminalIds= new ArrayList<>();

    /**
     * 公告更新时 数据检验
     *
     * @return
     */
    @Transient
    public Boolean checkDataOnUpdate() {
        if (validateDataOnAdd()) {
            if (getCrimeInfo().getCrimeId() == null || getCrimePersonInfo().getCrimePersonId() == null || getNoticeInfo().getNoticeId() == null) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 数据新增保存时验证数据是否非空 长度是否正确
     *
     * @return
     */
    public boolean validateDataOnAdd() {
        if (!checkDataIsNotNull()) {
            return false;
        }
        if (checkDataIsNotNull()) {
            return JavaBeanValidationUtil.validate(getCrimeInfo()) && JavaBeanValidationUtil.validate(getCrimePersonInfo()) && JavaBeanValidationUtil.validate(getNoticeInfo());
        }
        return false;
    }

    /**
     * 保存时数据检验数据是否是非空
     *
     * @return
     */
    public Boolean checkDataIsNotNull() {
        if (getCrimeInfo() == null || getCrimePersonInfo() == null || getNoticeInfo() == null) {
            return false;
        }
        return true;
    }

    /**
     * Gets crime info.
     *
     * @return the crime info
     */
    public CrimeInfo getCrimeInfo() {
        return crimeInfo;
    }

    /**
     * Sets crime info.
     *
     * @param crimeInfo the crime info
     */
    public void setCrimeInfo(CrimeInfo crimeInfo) {
        this.crimeInfo = crimeInfo;
    }

    /**
     * Gets crime person info.
     *
     * @return the crime person info
     */
    public CrimePersonInfo getCrimePersonInfo() {
        return crimePersonInfo;
    }

    /**
     * Sets crime person info.
     *
     * @param crimePersonInfo the crime person info
     */
    public void setCrimePersonInfo(CrimePersonInfo crimePersonInfo) {
        this.crimePersonInfo = crimePersonInfo;
    }

    /**
     * Gets notice info.
     *
     * @return the notice info
     */
    public NoticeInfo getNoticeInfo() {
        return noticeInfo;
    }

    /**
     * Sets notice info.
     *
     * @param noticeInfo the notice info
     */
    public void setNoticeInfo(NoticeInfo noticeInfo) {
        this.noticeInfo = noticeInfo;
    }

    /**
     * Gets attachment info.
     *
     * @return the attachment info
     */
    public List<AttachmentInfo> getAttachmentInfo() {
        return attachmentInfo;
    }

    /**
     * Sets attachment info.
     *
     * @param attachmentInfo the attachment info
     */
    public void setAttachmentInfo(List<AttachmentInfo> attachmentInfo) {
        this.attachmentInfo = attachmentInfo;
    }

    public List<String> getWaitCriminalIds() {
        return waitCriminalIds;
    }

    public void setWaitCriminalIds(List<String> waitCriminalIds) {
        this.waitCriminalIds = waitCriminalIds;
    }
}
