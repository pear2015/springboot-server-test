package com.gs.crms.crimenotice.service.entity;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by zhangqiang on 2017/10/30.
 * 实体基类
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class BaseEntity {

    /**
     * 录入人ID
     */
    @Column(name = "entering_person_id", length = 128)
    private String enterPersonId;
    /**
     * 录入时间
     */
    @Column(name = "entering_time")
    private Date enteringTime;
    /**
     * 录入人员姓名
     */
    @Column(name = "entering_person_name", length = 128)
    private String enteringPersonName;
    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;
    /**
     * 修改人员姓名
     */
    @Column(name = "modify_person_name", length = 128)
    private String modifyPersonName;
    /**
     * 修改人员Id
     */
    @Column(name = "modify_person_id", length = 128)
    private String modifyPersonId;

    /**
     * 删除人Id
     */
    @Column(name = "delete_person_id", length = 128)
    private String deletePersonId;

    /**
     * 删除人姓名
     */
    @Column(name = "delete_person_name", length = 128)
    private String deletePersonName;

    /**
     * 删除时间
     */
    @Column(name = "delete_time")
    private Date deleteTime;
    /**
     * 数据发生变更时间
     */
    @Column(name = "change_time")
    private Date changeTime;

    /**
     * Get
     *
     * @return
     */
    public String getEnterPersonId() {
        return enterPersonId;
    }

    /**
     * Set
     */
    public void setEnterPersonId(String enterPersonId) {
        this.enterPersonId = enterPersonId;
    }

    /**
     * Get
     *
     * @return
     */
    public Date getEnteringTime() {
        return enteringTime;
    }

    /**
     * Set
     */
    public void setEnteringTime(Date enteringTime) {
        this.enteringTime = enteringTime;
    }

    /**
     * Get
     *
     * @return
     */
    public String getEnteringPersonName() {
        return enteringPersonName;
    }

    /**
     * Set
     */
    public void setEnteringPersonName(String enteringPersonName) {
        this.enteringPersonName = enteringPersonName;
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
     */
    public void setModifyPersonName(String modifyPersonName) {
        this.modifyPersonName = modifyPersonName;
    }

    /**
     * Get
     *
     * @return
     */
    public String getModifyPersonId() {
        return modifyPersonId;
    }

    /**
     * Set
     */
    public void setModifyPersonId(String modifyPersonId) {
        this.modifyPersonId = modifyPersonId;
    }

    /**
     * Get
     *
     * @return
     */
    public String getDeletePersonId() {
        return deletePersonId;
    }

    /**
     * Set
     */
    public void setDeletePersonId(String deletePersonId) {
        this.deletePersonId = deletePersonId;
    }

    /**
     * Get
     *
     * @return
     */
    public String getDeletePersonName() {
        return deletePersonName;
    }

    /**
     * Set
     */
    public void setDeletePersonName(String deletePersonName) {
        this.deletePersonName = deletePersonName;
    }

    /**
     * Get
     *
     * @return
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * Set
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
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
