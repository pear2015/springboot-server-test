package com.gs.crms.applycertify.contract.model.admin;

import com.gs.crms.common.model.SortOrder;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * 证书传入参数
 *
 * @author zhufengjie
 * @version 1.0
 * @created 22 -八月-2017 14:24:33
 */
public class CertificateParamModel {
    @Size(max = 64, message = "certificateId max length is 128")
    private String certificateId;//证书编号
    @Size(max = 128, message = "authCode max length is 128")
    private String authCode;//验证码
    @Size(max = 128, message = "firstName max length is 128")
    private String firstName;//名
    @Size(max = 128, message = "lastName max length is 128")
    private String lastName;//姓
    @Size(max = 128, message = "certificateNum max length is 128")
    private String certificateNum;//身份证
    @Size(max = 128, message = "deliveryReceiptNumbr max length is 128")
    private String deliveryReceiptNumbr;//回执单号
    @Size(max = 128, message = "govermentInfo max length is 128")
    private String govermentInfo;//政府名称
    @Size(max = 36, message = "certificateType max length is 36")
    private String certificateType;//证书类型
    @Size(max = 36, message = "certificateStatus max length is 36")
    private String certificateStatus;//申请状态
    private Date printStartTime;//打印开始时间
    private Date printEndTime;//证书结束时间
    // 单页数据量
    private int pageSize;

    // 页数索引
    private int pages;

    // 排序规则
    private List<SortOrder> sortOrder;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<SortOrder> getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(List<SortOrder> sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    public String getDeliveryReceiptNumbr() {
        return deliveryReceiptNumbr;
    }

    public void setDeliveryReceiptNumbr(String deliveryReceiptNumbr) {
        this.deliveryReceiptNumbr = deliveryReceiptNumbr;
    }

    public String getGovermentInfo() {
        return govermentInfo;
    }

    public void setGovermentInfo(String govermentInfo) {
        this.govermentInfo = govermentInfo;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateStatus() {
        return certificateStatus;
    }

    public void setCertificateStatus(String certificateStatus) {
        this.certificateStatus = certificateStatus;
    }

    public Date getPrintStartTime() {
        return printStartTime;
    }

    public void setPrintStartTime(Date printStartTime) {
        this.printStartTime = printStartTime;
    }

    public Date getPrintEndTime() {
        return printEndTime;
    }

    public void setPrintEndTime(Date printEndTime) {
        this.printEndTime = printEndTime;
    }
}