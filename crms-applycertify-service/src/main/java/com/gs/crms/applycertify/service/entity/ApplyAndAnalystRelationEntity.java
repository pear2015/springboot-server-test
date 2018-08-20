package com.gs.crms.applycertify.service.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 分析任务和用户关联表
 * Created by zhangqiang on 2017/8/31.
 */
@Entity
@Table(name = "r_apply_analyst_auditor", schema = "crms_crime")
public class ApplyAndAnalystRelationEntity {
    /*
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String relationId;

    /*
    ID
     */
    @Column(name = "apply_info_id", length = 36)
    private String applyInfoId;

    /*
    公告ID 不强制外键关联
     */
    @Column(name = "analyst_id", length = 36)
    private String analystId;

    /**
     * 创建时间
     */
    @Column(name = "auditor_id", length = 36)
    private String auditorId;
    /**
     * 分析任务是新建还是驳回0新建1驳回
     */
    @Column(name = "analysis_result_fail", length = 36)
    private String analysisResultFail;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 业务类型
     */
    @Column(name = "business_type", length = 36)
    private String businessType;
    /**
     * 优先级
     */
    @Column(name = "priority", length = 36)
    private String priority;

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
    public String getAnalystId() {
        return analystId;
    }

    /**
     * Set
     *
     * @param analystId
     */
    public void setAnalystId(String analystId) {
        this.analystId = analystId;
    }

    /**
     * Get
     *
     * @return
     */
    public String getAuditorId() {
        return auditorId;
    }

    /**
     * Set
     *
     * @param auditorId
     */
    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId;
    }

    /**
     * Get
     *
     * @return
     */
    public String getAnalysisResultFail() {
        return analysisResultFail;
    }

    /**
     * Set
     *
     * @param analysisResultFail
     */
    public void setAnalysisResultFail(String analysisResultFail) {
        this.analysisResultFail = analysisResultFail;
    }

    /**
     * Get
     *
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Set
     *
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * Get
     *
     * @return
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * Set
     *
     * @param businessType
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    /**
     * Get
     *
     * @return
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Set
     *
     * @param priority
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }
}
