package com.gs.crms.applycertify.contract.model.admin;

import com.gs.crms.common.model.SortOrder;
import com.gs.crms.common.utils.PageUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by zhufengjie on 2017/10/18.
 */
public class ApplyInfoParam {
    // 证件号码
    private String certificateNum;
    // 名
    private String firstName;
    // 姓
    private String lastName;
    // 回执单号
    private String deliveryReceiptNumbr;
    // 申请状态
    private String applyStatus;
    // 申请开始时间
    private Date applyStartTime;
    // 申请时间
    private Date applyEndTime;
    // 申请目的
    private String applyPurpose;
    // 申请类型
    private String applyType;
    // 单页数据量
    private int pageSize;
    // 页数索引
    private int pages;
    // 排序规则
    private List<SortOrder> sortOrder;
    // 审核人
    private String auditPersonId;

    /**
     * Get
     *
     * @return
     */
    public String getCertificateNum() {
        return certificateNum;
    }

    /**
     * Set
     *
     * @param certificateNum
     */
    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    /**
     * Get
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDeliveryReceiptNumbr() {
        return deliveryReceiptNumbr;
    }

    public void setDeliveryReceiptNumbr(String deliveryReceiptNumbr) {
        this.deliveryReceiptNumbr = deliveryReceiptNumbr;
    }

    /**
     * Get
     *
     * @return
     */
    public String getApplyStatus() {
        return applyStatus;
    }

    /**
     * Set
     *
     * @param applyStatus
     */
    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Date getApplyStartTime() {
        return applyStartTime;
    }

    public void setApplyStartTime(Date applyStartTime) {
        this.applyStartTime = applyStartTime;
    }

    public Date getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(Date applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    /**
     * Get
     *
     * @return
     */
    public String getApplyPurpose() {
        return applyPurpose;
    }

    /**
     * Set
     *
     * @param applyPurpose
     */
    public void setApplyPurpose(String applyPurpose) {
        this.applyPurpose = applyPurpose;
    }

    /**
     * Get
     *
     * @return
     */
    public String getApplyType() {
        return applyType;
    }

    /**
     * Set
     *
     * @param applyType
     */
    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    /**
     * Get
     *
     * @return
     */
    public int getPageSize() {
        return pageSize==0? PageUtil.PAGESIAE:pageSize;
    }

    /**
     * Set
     *
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Get
     *
     * @return
     */
    public int getPages() {
        return pages;
    }

    /**
     * Set
     *
     * @param pages
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * Get
     *
     * @return
     */
    public List<SortOrder> getSortOrder() {
        return sortOrder;
    }

    /**
     * Set
     *
     * @param sortOrder
     */
    public void setSortOrder(List<SortOrder> sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * Get
     *
     * @return
     */
    public String getAuditPersonId() {
        return auditPersonId;
    }

    /**
     * Set
     *
     * @param auditPersonId
     */
    public void setAuditPersonId(String auditPersonId) {
        this.auditPersonId = auditPersonId;
    }
}
