package com.gs.crms.applycertify.contract.model;

/**
 * Created by zhangqiang on 2017/9/25.
 * 证件类型
 */
public class CertificateType {
    /**
     * 证件ID
     */
    private String certificateTypeId;

    /**
     * 证件名称
     */
    private String name;

    /**
     * 证件种类0身份证、1，其他 3.无 2.护照等等
     */
    private String type;

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
}
