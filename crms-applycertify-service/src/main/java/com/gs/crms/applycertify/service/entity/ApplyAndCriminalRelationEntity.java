package com.gs.crms.applycertify.service.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 申请和罪犯关联
 * Created by tanjie on 2017/8/9.
 */
@Entity
@Table(name = "r_apply_and_criminal", schema = "crms_crime")
public class ApplyAndCriminalRelationEntity {

    /*
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String relationId;

    /*
     * 外键关联ID
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_info_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ApplyInfoEntity applyInfoEntity;

    /*
    ID
     */
    @Column(name = "apply_info_id", length = 36)
    private String applyInfoId;

    /*
     * 罪犯ID 不强制外键关联
     */
    @Column(name = "criminal_id", length = 2000)
    private String criminalId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;


    /**
     * Get
     *
     * @return
     */
    public String getRelationId() {
        return relationId;
    }

    /**
     * Set
     *
     * @param relationId
     */
    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    /**
     * Get
     *
     * @return
     */
    public ApplyInfoEntity getApplyInfoEntity() {
        return applyInfoEntity;
    }

    /**
     * Set
     *
     * @param applyInfoEntity
     */
    public void setApplyInfoEntity(ApplyInfoEntity applyInfoEntity) {
        this.applyInfoEntity = applyInfoEntity;
    }

    /**
     * Get
     *
     * @return
     */
    public String getApplyInfoId() {
        return applyInfoId;
    }

    /**
     * Set
     *
     * @param applyInfoId
     */
    public void setApplyInfoId(String applyInfoId) {
        this.applyInfoId = applyInfoId;
    }

    /**
     * Get
     *
     * @return
     */
    public String getCriminalId() {
        return criminalId;
    }

    /**
     * Set
     *
     * @param criminalId
     */
    public void setCriminalId(String criminalId) {
        this.criminalId = criminalId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
