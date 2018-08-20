package com.gs.crms.crimenotice.contract.model.CrimeIntegrated;

import com.gs.crms.common.model.SortOrder;
import com.gs.crms.common.utils.PageUtil;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangqiang on 2017/10/17.
 * 犯罪信息查询Model
 * 根据个人信息
 */
public class CrimePersonQuery {
    // 名
    @Size(max = 128)
    private String firstName;

    // 姓
    @Size(max = 128)
    private String lastName;

    // 父亲的名
    @Size(max = 128)
    private String fatherFirstName;

    // 父亲的姓
    @Size(max = 128)
    private String fatherLastName;

    // 母亲的名
    @Size(max = 128)
    private String motherFirstName;

    // 母亲的姓
    @Size(max = 128)
    private String motherLastName;

    // 出生日期
    private Date birthDate;

    // 证件号码
    @Size(max = 128)
    private String certificateNumber;

    // 单页数据量
    private int pageSize;

    // 页数索引
    private int pages;

    // 排序规则
    private List<SortOrder> sortOrder;

    /**
     * get
     *
     * @return
     */
    public int getPageSize() {
        return pageSize==0?PageUtil.PAGESIAE:pageSize;
    }

    /**
     * set
     *
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

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

    /**
     * Get
     *
     * @return
     */
    public String getFatherFirstName() {
        return fatherFirstName;
    }

    /**
     * Set
     *
     * @param fatherFirstName
     */
    public void setFatherFirstName(String fatherFirstName) {
        this.fatherFirstName = fatherFirstName;
    }

    /**
     * Get
     *
     * @return
     */
    public String getFatherLastName() {
        return fatherLastName;
    }

    /**
     * Set
     *
     * @param fatherLastName
     */
    public void setFatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
    }

    /**
     * Get
     *
     * @return
     */
    public String getMotherFirstName() {
        return motherFirstName;
    }

    /**
     * Set
     *
     * @param motherFirstName
     */
    public void setMotherFirstName(String motherFirstName) {
        this.motherFirstName = motherFirstName;
    }

    /**
     * Get
     *
     * @return
     */
    public String getMotherLastName() {
        return motherLastName;
    }

    /**
     * Set
     *
     * @param motherLastName
     */
    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    /**
     * Get
     *
     * @return
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Set
     *
     * @param birthDate
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Get
     *
     * @return
     */
    public String getCertificateNumber() {
        return certificateNumber;
    }

    /**
     * Set
     *
     * @param certificateNumber
     */
    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }
}

