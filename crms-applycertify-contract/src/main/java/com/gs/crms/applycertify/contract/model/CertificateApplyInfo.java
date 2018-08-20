package com.gs.crms.applycertify.contract.model;

import com.gs.crms.common.utils.JavaBeanValidationUtil;
import com.gs.crms.common.model.AttachmentInfo;
import com.gs.crms.crimenotice.contract.model.CrimePersonInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 申请信息和申请人信息，附件等
 * Created by zhangqiang on 2017/8/22.
 */
public class CertificateApplyInfo {

    private ApplyBasicInfo applyBasicInfo;
    private ApplyInfo applyInfo;
    private List<AttachmentInfo> attachmentInfoList=new ArrayList<>();
    private ApplyAndCriminalRelation applyAndCriminalRelation;
    private CrimePersonInfo crimePersonInfo;

    /**
     * Gets apply basic info.
     *
     * @return the apply basic info
     */
    public ApplyBasicInfo getApplyBasicInfo() {
        return applyBasicInfo;
    }

    /**
     * 验证写数据库是的DTO
     * 添加 验证申请信息和申请基本信息不可为空
     * @return
     */
    public boolean validateData() {
        boolean validateApplyInfo = true;
        boolean validateApplyBasicInfo = true;
        boolean validateApplyAndCriminalRelation = true;
        if(getApplyBasicInfo()==null||getApplyInfo()==null){
            return  false;
        }
        if (getApplyBasicInfo() != null) {
            validateApplyBasicInfo = JavaBeanValidationUtil.validate(getApplyBasicInfo());
        }
        if (getApplyInfo() != null) {
            validateApplyInfo = JavaBeanValidationUtil.validate(getApplyInfo());
        }
        if (getApplyAndCriminalRelation() != null) {
            validateApplyAndCriminalRelation = JavaBeanValidationUtil.validate(getApplyAndCriminalRelation());
        }
        return validateApplyInfo && validateApplyBasicInfo && validateApplyAndCriminalRelation;
    }

    /**
     * Sets apply basic info.
     *
     * @param applyBasicInfo the apply basic info
     */
    public void setApplyBasicInfo(ApplyBasicInfo applyBasicInfo) {
        this.applyBasicInfo = applyBasicInfo;
    }

    /**
     * Gets apply info.
     *
     * @return the apply info
     */
    public ApplyInfo getApplyInfo() {
        return applyInfo;
    }

    /**
     * Sets apply info.
     *
     * @param applyInfo the apply info
     */
    public void setApplyInfo(ApplyInfo applyInfo) {
        this.applyInfo = applyInfo;
    }

    /**
     * Gets attachment info list.
     *
     * @return the attachment info list
     */
    public List<AttachmentInfo> getAttachmentInfoList() {
        return attachmentInfoList;
    }

    /**
     * Sets attachment info list.
     *
     * @param attachmentInfoList the attachment info list
     */
    public void setAttachmentInfoList(List<AttachmentInfo> attachmentInfoList) {
        this.attachmentInfoList = attachmentInfoList;
    }

    /**
     * Get
     *
     * @return
     */
    public ApplyAndCriminalRelation getApplyAndCriminalRelation() {
        return applyAndCriminalRelation;
    }

    /**
     * Set
     *
     * @param applyAndCriminalRelation
     */
    public void setApplyAndCriminalRelation(ApplyAndCriminalRelation applyAndCriminalRelation) {
        this.applyAndCriminalRelation = applyAndCriminalRelation;
    }

    public CrimePersonInfo getCrimePersonInfo() {
        return crimePersonInfo;
    }

    public void setCrimePersonInfo(CrimePersonInfo crimePersonInfo) {
        this.crimePersonInfo = crimePersonInfo;
    }
}
