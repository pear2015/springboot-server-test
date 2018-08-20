package com.gs.crms.applycertify.contract.model;
import com.gs.crms.common.model.AttachmentInfo;


/**
 * 申请基本信息
 *
 * @author zhufengjie
 * @version 1.0
 * @created 22 -八月-2017 14:24:33
 */
public class CertificateExtendInfo {
    private CertificateInfo certificateInfo;
    private AttachmentInfo attachmentInfo;
    private ApplyInfo applyInfo;
    private ApplyBasicInfo applyBasicInfo;

    public ApplyBasicInfo getApplyBasicInfo() {
        return applyBasicInfo;
    }

    public void setApplyBasicInfo(ApplyBasicInfo applyBasicInfo) {
        this.applyBasicInfo = applyBasicInfo;
    }

    public ApplyInfo getApplyInfo() {
        return applyInfo;
    }

    public void setApplyInfo(ApplyInfo applyInfo) {
        this.applyInfo = applyInfo;
    }

    public CertificateInfo getCertificateInfo() {
        return certificateInfo;
    }

    public void setCertificateInfo(CertificateInfo certificateInfo) {
        this.certificateInfo = certificateInfo;
    }

    public AttachmentInfo getAttachmentInfo() {
        return attachmentInfo;
    }

    public void setAttachmentInfo(AttachmentInfo attachmentInfo) {
        this.attachmentInfo = attachmentInfo;
    }
}