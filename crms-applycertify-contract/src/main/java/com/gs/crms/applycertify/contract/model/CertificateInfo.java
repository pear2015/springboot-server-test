package com.gs.crms.applycertify.contract.model;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 申请基本信息
 *
 * @author zhufengjie
 * @version 1.0
 * @created 22 -八月-2017 14:24:33
 */
public class CertificateInfo {
    /**
     * 主键，证书号
     */
    private long certificateId;
    // 申请Id
    @Size(max = 36, message = "applyId max length is 36")
    private String applyId;
    // 证书状态
    @Size(max = 256, message = "certificateStatus max length is 256")
    private String certificateStatus;
    // 失效时间
    private Date invalidTime;
    // 创建人
    @Size(max = 128, message = "createPersonName max length is 128")
    private String createPersonName;
    // 修改时间
    private Date modifyTime;
    // 修改人
    @Size(max = 128, message = "modifyPersonName max length is 128")
    private String modifyPersonName;
    // 验证号
    @Size(max = 128, message = "validateCode max length is 128")
    private String validateCode;
    // 打印时间
    private Date printTime;
    @Size(max = 2000, message = "attachmentId max length is 2000")
    // 证书文件（mongodb中文件id）
    private String attachmentId;
    // 证书类型
    @Size(max = 255, message = "certificateType max length is 255")
    private String certificateType;
    /**
     * 是否有效
     */
    private  boolean  isValid;

    /**
     * Get
     *
     * @return
     */
    public long getCertificateId() {
        return certificateId;
    }

    /**
     * Set
     *
     * @param certificateId
     */
    public void setCertificateId(long certificateId) {
        this.certificateId = certificateId;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    /**
     * Get
     *
     * @return
     */
    public String getCertificateStatus() {
        return certificateStatus;
    }

    /**
     * Set
     */
    public void setCertificateStatus(String certificateStatus) {
        this.certificateStatus = certificateStatus;
    }

    /**
     * Get
     *
     * @return
     */
    public Date getInvalidTime() {
        return invalidTime;
    }

    /**
     * Set
     */
    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    /**
     * Get
     *
     * @return
     */
    public String getCreatePersonName() {
        return createPersonName;
    }

    /**
     * Set
     */
    public void setCreatePersonName(String createPersonName) {
        this.createPersonName = createPersonName;
    }

    /**
     * Get
     *
     * @return
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * Set
     *
     * @return
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * Get
     *
     * @return
     */
    public String getModifyPersonName() {
        return modifyPersonName;
    }

    /**
     * Set
     *
     * @return
     */
    public void setModifyPersonName(String modifyPersonName) {
        this.modifyPersonName = modifyPersonName;
    }

    /**
     * Get
     *
     * @return
     */
    public String getValidateCode() {
        return validateCode;
    }

    /**
     * Set
     *
     * @return
     */
    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    /**
     * Get
     *
     * @return
     */
    public Date getPrintTime() {
        return printTime;
    }

    /**
     * Set
     *
     * @return
     */
    public void setPrintTime(Date printTime) {
        this.printTime = printTime;
    }

    /**
     * Get
     *
     * @return
     */
    public String getAttachmentId() {
        return attachmentId;
    }

    /**
     * Set
     *
     * @return
     */
    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    /**
     * Get
     *
     * @return
     */
    public String getCertificateType() {
        return certificateType;
    }

    /**
     * Set
     *
     * @return
     */
    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}