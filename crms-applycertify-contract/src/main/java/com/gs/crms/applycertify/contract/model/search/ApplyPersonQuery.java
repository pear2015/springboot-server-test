package com.gs.crms.applycertify.contract.model.search;

import com.gs.crms.common.model.SortOrder;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by zhangqiang on 2017/10/23.
 */
public class ApplyPersonQuery {
    /**
     * 名
     */
    @Size(max = 128, message = "firstName max length is 128")
    public String firstName;
    /**
     * 姓
     */
    @Size(max = 128, message = "firstName max length is 128")
    public String lastName;
    /**
     * 证件号码
     */
    @Size(max = 128, message = "certificateId max length is 128")
    public String certificateId;
    /**
     * 证件类型
     */
    @Size(max = 36, message = "certificateType max length is 36")
    public String certificateType;
    /**
     * 性别
     */
    @Size(max = 36, message = "sexId max length is 36")
    public String sexId;

    /**
     * 页码
     */
    public int pages;
    /**
     * 每页大小
     */
    public int pageSize;
    /**
     * 排序规则
     */
    public List<SortOrder> sortOrderList;
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
        if(StringUtils.isEmpty(getCertificateId())||StringUtils.isEmpty(getCertificateType())){
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

    /**
     * Get
     *
     * @return
     */
    public String getCertificateId() {
        return certificateId;
    }

    /**
     * Set
     */
    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
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
        if (pageSize == 0) {
            return 10;
        } else {
            return pageSize;
        }
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

    public String getSexId() {
        return sexId;
    }

    public void setSexId(String sexId) {
        this.sexId = sexId;
    }
}
