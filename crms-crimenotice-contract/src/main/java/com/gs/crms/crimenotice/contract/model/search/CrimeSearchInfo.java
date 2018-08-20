package com.gs.crms.crimenotice.contract.model.search;

import com.gs.crms.common.model.RoleType;
import com.gs.crms.common.model.SortOrder;
import com.gs.crms.common.utils.PageUtil;
import com.gs.crms.common.utils.VariableUtil;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.beans.Transient;
import java.util.Date;
import java.util.List;

/**
 * 查询时间模型
 * Created by zhufengjie on 2017/9/20.
 */

public class CrimeSearchInfo{
    /**
     * 公告编号
     */
    @Size(max=128)
    private String noticeNumber;
    /**
     * 法院
     */
    @Size(max=36)
    private String courtId;
    /**
     * 公告发布开始时间
     */
    private Date startNoticeCreateTime;

    /**
     *  公告发布结束时间
     */
    private Date endNoticeCreateTime;
    /**
     * 证件编号
     */
    @Size(max=128)
    private  String certificateNumber;
    /**
     * 角色
     */
   private String RoleType;

    /**
     * 页码
     */
    private int pages;
    /**
     * 每页页码
     */
    private int pageSize;
    /**
     * 录入人Id
     */
    @Size(max=36)
    private String enterPersonId;
    /**
     * 录入人名称
     */
    @Size(max=128)
    private String enterPersonName;
    /**
     * 审核员Id
     */
    @Size(max=36)
    private String auditPersonId;
    /**
     * 公告状态查询
     */
    @Size(max=36)
    private  String noticeStatus;
    /**
     * 排序规则
     */
    private List<SortOrder> sortOrders;

    public String getNoticeNumber() {
        return noticeNumber;
    }

    public void setNoticeNumber(String noticeNumber) {
        this.noticeNumber = noticeNumber;
    }

    public String getCourtId() {
        return courtId;
    }

    public void setCourtId(String courtId) {
        this.courtId = courtId;
    }


    public List<SortOrder> getSortOrders() {
        return sortOrders;
    }

    public void setSortOrders(List<SortOrder> sortOrders) {
        this.sortOrders = sortOrders;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageSize() {
        return pageSize==0?PageUtil.PAGESIAE:pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getRoleType() {
        return RoleType;
    }
    public void setRoleType(String roleType) {
        RoleType = roleType;
    }

    public String getEnterPersonId() {
        return enterPersonId;
    }

    /**
     * 是否按照录入人查询
     * @return
     */
    public  boolean  isSearchByEnterPersonId(){
         return    StringUtils.isNoneBlank(getRoleType())&& StringUtils.isNoneBlank(getEnterPersonId()) && VariableUtil.FILER.equals(getRoleType());
    }

    public void setEnterPersonId(String enterPersonId) {
        this.enterPersonId = enterPersonId;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }


    public Date getStartNoticeCreateTime() {
        return startNoticeCreateTime;
    }

    public void setStartNoticeCreateTime(Date startNoticeCreateTime) {
        this.startNoticeCreateTime = startNoticeCreateTime;
    }

    public Date getEndNoticeCreateTime() {
        return endNoticeCreateTime;
    }

    public void setEndNoticeCreateTime(Date endNoticeCreateTime) {
        this.endNoticeCreateTime = endNoticeCreateTime;
    }

    public String getAuditPersonId() {
        return auditPersonId;
    }

    public void setAuditPersonId(String auditPersonId) {
        this.auditPersonId = auditPersonId;
    }

    public String getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public String getEnterPersonName() {
        return enterPersonName;
    }

    public void setEnterPersonName(String enterPersonName) {
        this.enterPersonName = enterPersonName;
    }
}
