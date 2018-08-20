package com.gs.crms.applycertify.service.entity;

import com.gs.crms.applycertify.service.entity.dictionary.ApplyTypeEntity;
import com.gs.crms.common.enums.CertificateTypeEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 证书表
 * Created by zhangqiang on 2017/8/25.
 */
@Entity
@Table(name = "b_certificate_info", schema = "crms_crime", indexes = {@Index(name = "apply_idIndex", columnList = "apply_id")})
@SequenceGenerator(name = "ud", sequenceName = "crms_crime.b_certificate_info_id_seq", schema = "crms_crime", initialValue = 2000000000, allocationSize = 1)
public class CertificateInfoEntity {
    /*
    主键,证书号
     */
    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ud")
    private long certificateId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ApplyInfoEntity applyInfoEntity;
    // 申请Id
    @Column(name = "apply_id", nullable = false, length = 36)
    private String applyId;
    // 证书状态
    @Column(name = "certificate_status", length = 256)
    private String certificateStatus;
    // 失效时间
    @Column(name = "invalid_time")
    private Date invalidTime;
    // 创建人姓名
    @Column(name = "create_person_name", length = 128)
    private String createPersonName;
    // 创建人ID
    @Column(name = "create_person_id", length = 36)
    private String createPersonId;
    //  创建时间
    @Column(name = "create_time")
    private Date createTime;
    // 修改时间
    @Column(name = "modify_time")
    private Date modifyTime;
    // 修改人
    @Column(name = "modify_person_name", length = 128)
    private String modifyPersonName;
    // 验证号
    @Column(name = "validate_code", length = 128)
    private String validateCode;
    // 打印时间
    @Column(name = "print_time")
    private Date printTime;
    // 证书文件（mongodb中文件id）
    @Column(name = "attachment_id", length = 2000)
    private String attachmentId;
    // 数据发生变更时间
    @Column(name = "change_time")
    private Date changeTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certificate_type", referencedColumnName = "id", insertable = false, updatable = false)
    private ApplyTypeEntity applyTypeEntity;
    // 证书类型
    @Column(name = "certificate_type", length = 36)
    private String certificateType;

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

    /**
     * Get
     *
     * @return
     */
    public ApplyInfoEntity getApplyInfoEntity() {
        return applyInfoEntity;
    }

    /**
     * Set
     *
     * @param applyInfoEntity
     */
    public void setApplyInfoEntity(ApplyInfoEntity applyInfoEntity) {
        this.applyInfoEntity = applyInfoEntity;
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
     *
     * @param certificateStatus
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
     *
     * @param invalidTime
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
     *
     * @param createPersonName
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
     * @param modifyTime
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
     * @param modifyPersonName
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
     * @param validateCode
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
     * @param printTime
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
     * @param attachmentId
     */
    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    /**
     * Get
     *
     * @return
     */
    public ApplyTypeEntity getApplyTypeEntity() {
        return applyTypeEntity;
    }

    /**
     * Set
     *
     * @param applyTypeEntity
     */
    public void setApplyTypeEntity(ApplyTypeEntity applyTypeEntity) {
        this.applyTypeEntity = applyTypeEntity;
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
     * @param certificateType
     */
    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    /**
     * Get
     *
     * @return
     */
    public String getCreatePersonId() {
        return createPersonId;
    }

    /**
     * Set
     *
     * @param createPersonId
     */
    public void setCreatePersonId(String createPersonId) {
        this.createPersonId = createPersonId;
    }

    /**
     * Get
     *
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Set
     *
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * Get
     *
     * @return
     */
    public Date getChangeTime() {
        return changeTime;
    }

    /**
     * Set
     *
     * @param changeTime
     */
    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }
}
