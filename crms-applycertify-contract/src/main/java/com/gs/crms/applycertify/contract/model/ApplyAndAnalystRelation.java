package com.gs.crms.applycertify.contract.model;

import java.util.Date;

/**
 * 分析人物和分析员用户关联DTO
 * Created by zhangqiang on 2017/8/31.
 */
public class ApplyAndAnalystRelation {
    private String applyInfoId;
    private String analystId;
    /**
     * 创建时间
     */
    private String auditorId;
    /**
     * 分析任务是新建还是驳回0新建1驳回
     */
    private String analysisResultFail;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 业务类型
     * 1 申请业务
     * 2 公告业务
     */
    private String businessType;
    /**
     * 优先级
     */
    private String priority;

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
     * get
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
