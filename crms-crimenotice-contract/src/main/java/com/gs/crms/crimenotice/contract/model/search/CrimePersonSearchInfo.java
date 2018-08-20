package com.gs.crms.crimenotice.contract.model.search;

import com.gs.crms.common.model.SortOrder;
import com.gs.crms.common.utils.JavaBeanValidationUtil;
import com.gs.crms.common.utils.PageUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by zhengyali on 2017/10/30.
 */
public class CrimePersonSearchInfo {
    /**
     * 名
     */
    @Size(max = 128)
    private String firstName;
    /**
     * 姓
     */
    @Size(max = 128)
    private String lastName;
    /**
     * 证件号码
     */
    @Size(max = 128)
    private String certificateNumber;
    /**
     * 证件类型
     */
    @Size(max = 36)
    private String certificateType;
    /**
     * 性别
     */
    @Size(max = 36)
    private String sexId;

    /**
     * 页码
     */
    private int pages;
    /**
     * 每页大小
     */
    private int pageSize;
    /**
     * 排序规则
     */
    private List<SortOrder> sortOrderList;

    /**
     * 数据回填时验证数据
     * @return
     */
    public  boolean   validateData(){
        if(validateCertificateData()||validateNameAndSexData()){
            return  true;
        }
        return false;
    }

    /**
     * 验证证件编号是否为空
     * @return
     */
    public   boolean   validateCertificateData(){
        if(StringUtils.isEmpty(getCertificateNumber())||StringUtils.isEmpty(getCertificateType())){
          return  false;
        }
        return true;
    }

    /**
     * 验证姓名和性别是否为空
     * @return
     */
    public  boolean   validateNameAndSexData(){
        if(StringUtils.isEmpty(getFirstName())||StringUtils.isEmpty(getLastName())||StringUtils.isEmpty(getSexId())){
            return  false;
        }
        return true;
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
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
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
     */
    public void setPages(int pages) {
        this.pages = pages;
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
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Get
     *
     * @return
     */
    public List<SortOrder> getSortOrderList() {
        return sortOrderList;
    }

    /**
     * Set
     */
    public void setSortOrderList(List<SortOrder> sortOrderList) {
        this.sortOrderList = sortOrderList;
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
    public String getSexId() {
        return sexId;
    }

    /**
     * Set
     *
     * @param sexId
     */
    public void setSexId(String sexId) {
        this.sexId = sexId;
    }
}
