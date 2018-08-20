package com.gs.crms.crimenotice.service.entity;

import com.gs.crms.crimenotice.service.entity.dictionary.CrimeTypeEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 犯罪记录基本信息
 * Created by tanjie on 2017/7/10.
 */
@Entity
@Table(name = "b_crime_info", schema = "crms_crime")
public class CrimeEntity extends BaseEntity {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String crimeId;

    /**
     * 犯罪描述
     */
    @Column(name = "crime_description", length = 2000)
    private String crimeDescription;

    /**
     * 犯罪区域id
     */
    @Column(name = "crime_region_id", length = 128)
    private String crimeRegionId;
    /**
     * 犯罪区域名称
     */
    @Column(name = "crime_region_name", length = 256)
    private String crimeRegionName;

    /**
     * 简要案情 描述
     */
    @Column(name = "description", length = 2000)
    private String description;

    /**
     * 判决罪名
     */
    @Column(name = "crime_name", length = 256)
    private String crimeName;
    /**
     * 外键关联判决类型id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crime_type", insertable = false, updatable = false)
    private CrimeTypeEntity crimeTypeEntity;
    /**
     * 判决类型
     */
    @Column(name = "crime_type", length = 36)
    private String crimeType;
    /**
     * 判决依据
     */
    @Column(name = "crime_result", length = 2000)
    private String crimeResult;
    /**
     * 法院判决时间
     */
    @Column(name = "crime_judge_time")
    private Date crimeJudgeTime;

    /**
     * 删除标志
     */
    @Column(name = "isactive")
    private String isActive;
    /**
     * 犯罪时间
     */
    @Column(name = "crime_time")
    private Date crimeTime;

    /**
     * 诉讼号
     */
    @Column(name = "litigation_number", length = 256)
    private String litigationNumber;

    /**
     * Get
     *
     * @return
     */
    public String getLitigationNumber() {
        return litigationNumber;
    }

    /**
     * Set
     *
     * @param litigationNumber
     */
    public void setLitigationNumber(String litigationNumber) {
        this.litigationNumber = litigationNumber;
    }

    /**
     * Get
     *
     * @return
     */
    public Date getCrimeTime() {
        return crimeTime;
    }

    /**
     * Set
     *
     * @param crimeTime
     */
    public void setCrimeTime(Date crimeTime) {
        this.crimeTime = crimeTime;
    }

    /**
     * Get
     *
     * @return
     */
    public String getIsActive() {
        return isActive;
    }

    /**
     * Set
     *
     * @param isActive
     */
    public void setIsActive(String isActive) {
        this.isActive = isActive;
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

    public String getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType;
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

    public CrimeTypeEntity getCrimeTypeEntity() {
        return crimeTypeEntity;
    }

    public void setCrimeTypeEntity(CrimeTypeEntity crimeTypeEntity) {
        this.crimeTypeEntity = crimeTypeEntity;
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
}
