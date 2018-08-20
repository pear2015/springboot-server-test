package com.gs.crms.common.enums;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zhangqiang on 2017/9/25.
 * 证件类型
 */
@Entity
@Table(name = "d_certificate_type", schema = "crms_crime")
public class CertificateTypeEntity {
    /*
   主键ID
    */
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String certificateTypeId;

    /*
    名称
     */
    @Column(name = "name", length = 128, nullable = false)
    private String name;
    /*
  * 种类
    */
    @Column(name = "type", length = 128)
    private String type;
    /**
     * 个人申请是否启用
     */
    @Column(name = "person_is_valid", length = 36)
    private String personIsValid;
    /**
     * 政府申请是否启用
     */
    @Column(name = "government_is_valid", length = 36)
    private String governmentIsValid;
    /**
     * 公告是否启用
     */
    @Column(name = "notice_is_valid", length = 36)
    private String noticeIsValid;

    /**
     * Get
     *
     * @return
     */
    public String getCertificateTypeId() {
        return certificateTypeId;
    }

    /**
     * Set
     *
     * @param certificateTypeId
     */
    public void setCertificateTypeId(String certificateTypeId) {
        this.certificateTypeId = certificateTypeId;
    }

    /**
     * Get
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Set
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    public String getPersonIsValid() {
        return personIsValid;
    }

    public void setPersonIsValid(String personIsValid) {
        this.personIsValid = personIsValid;
    }

    public String getGovernmentIsValid() {
        return governmentIsValid;
    }

    public void setGovernmentIsValid(String governmentIsValid) {
        this.governmentIsValid = governmentIsValid;
    }

    public String getNoticeIsValid() {
        return noticeIsValid;
    }

    public void setNoticeIsValid(String noticeIsValid) {
        this.noticeIsValid = noticeIsValid;
    }
}
