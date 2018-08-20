package com.gs.crms.crimenotice.contract.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by tanjie on 2017/7/25.
 * 犯罪相关DTO
 */
public class CrimeInfo {
    /**
     * 主键ID
     */
    private String crimeId;

    /**
     * 犯罪描述
     */
    @Length(max=2000)
    @NotNull
    private String crimeDescription;
    /**
     * 犯罪区域id
     */
    @Length(max=128)
    private String crimeRegionId;

    /**
     * 犯罪区域名称
     */
    @Length(max=256)
    private String crimeRegionName;

    /**
     * 简要案情 描述
     */
    @Length(max=2000)
    private String description;

    /**
     * 判决罪名
     */
    @Length(max=256)
    private String crimeName;

    /**
     * 犯罪类型类型id
     */
    @Length(max=36)
    private String crimeTypeId;
    /**
     * 判决类型名称
     */
    private String crimeTypeName;
    /**
     * 判决依据
     */
    @Length(max=2000)
    private String crimeResult;
    /**
     * 法院判决时间
     */
    private Date crimeJudgeTime;

    /**
     * 删除标志
     */
    private String isActive;
    /**
     * 录入人id
     */
    @Length(max=36)
    private String enterPersonId;
    /**
     * 录入时间
     */
    private Date enteringTime;
    /**
     * 录入人名称
     */
    @Length(max=128)
    private String enteringPersonName;

    /**
     * 犯罪时间
     */
    @NotNull
    private Date crimeTime;
    /**
     * 诉讼号
     */
    @Length(max=256)
    private String litigationNumber;

    public String getLitigationNumber() {
        return litigationNumber;
    }

    public void setLitigationNumber(String litigationNumber) {
        this.litigationNumber = litigationNumber;
    }

    public Date getCrimeTime() {
        return crimeTime;
    }

    public void setCrimeTime(Date crimeTime) {
        this.crimeTime = crimeTime;
    }

    public String getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(String crimeId) {
        this.crimeId = crimeId;
    }

    public String getCrimeDescription() {
        return crimeDescription;
    }

    public void setCrimeDescription(String crimeDescription) {
        this.crimeDescription = crimeDescription;
    }

    public String getCrimeRegionId() {
        return crimeRegionId;
    }

    public void setCrimeRegionId(String crimeRegionId) {
        this.crimeRegionId = crimeRegionId;
    }

    public String getCrimeRegionName() {
        return crimeRegionName;
    }

    public void setCrimeRegionName(String crimeRegionName) {
        this.crimeRegionName = crimeRegionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCrimeName() {
        return crimeName;
    }

    public void setCrimeName(String crimeName) {
        this.crimeName = crimeName;
    }

    public String getCrimeTypeId() {
        return crimeTypeId;
    }

    public void setCrimeTypeId(String crimeTypeId) {
        this.crimeTypeId = crimeTypeId;
    }

    public String getCrimeTypeName() {
        return crimeTypeName;
    }

    public void setCrimeTypeName(String crimeTypeName) {
        this.crimeTypeName = crimeTypeName;
    }

    public String getCrimeResult() {
        return crimeResult;
    }

    public void setCrimeResult(String crimeResult) {
        this.crimeResult = crimeResult;
    }

    public Date getCrimeJudgeTime() {
        return crimeJudgeTime;
    }

    public void setCrimeJudgeTime(Date crimeJudgeTime) {
        this.crimeJudgeTime = crimeJudgeTime;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getEnterPersonId() {
        return enterPersonId;
    }

    public void setEnterPersonId(String enterPersonId) {
        this.enterPersonId = enterPersonId;
    }

    public Date getEnteringTime() {
        return enteringTime;
    }

    public void setEnteringTime(Date enteringTime) {
        this.enteringTime = enteringTime;
    }

    public String getEnteringPersonName() {
        return enteringPersonName;
    }

    public void setEnteringPersonName(String enteringPersonName) {
        this.enteringPersonName = enteringPersonName;
    }
}
