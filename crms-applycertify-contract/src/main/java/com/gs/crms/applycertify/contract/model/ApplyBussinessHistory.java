package com.gs.crms.applycertify.contract.model;

import java.util.Date;

/**
 * Created by zhangqiang on 2017/9/25.
 */
public class ApplyBussinessHistory {

    /*
   * 主键ID
   */
    private String id;
    /**
     * 申请ID
     */
    private String applyId;
    /**
     * 人员ID
     */
    private String operatorId;
    /**
     * 人员名称
     */
    private String operatorName;
    /**
     * 操作类型
     */
    private String operatorTypeId;

    /**
     * 操作类型名称
     */
    private String operatorTypeName;
    /**
     * 操作结果
     */
    private String operatorResult;
    /**
     * 操作描述
     */
    private String operatorDescription;
    /**
     * 操作时间
     */
    private Date operatorTime;

    /**
     * Get
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Set
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get
     *
     * @return
     */
    public String getApplyId() {
        return applyId;
    }

    /**
     * Set
     *
     * @param applyId
     */
    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    /**
     * Get
     *
     * @return
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     * Set
     *
     * @param operatorId
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * Get
     *
     * @return
     */
    public String getOperatorTypeId() {
        return operatorTypeId;
    }

    /**
     * Set
     *
     * @param operatorTypeId
     */
    public void setOperatorTypeId(String operatorTypeId) {
        this.operatorTypeId = operatorTypeId;
    }

    /**
     * Get
     * @return
     */
    public String getOperatorTypeName() {
        return operatorTypeName;
    }

    /**
     * Set
     * @param operatorTypeName
     */
    public void setOperatorTypeName(String operatorTypeName) {
        this.operatorTypeName = operatorTypeName;
    }

    /**
     * Get
     *
     * @return
     */
    public String getOperatorResult() {
        return operatorResult;
    }

    /**
     * Set
     *
     * @param operatorResult
     */
    public void setOperatorResult(String operatorResult) {
        this.operatorResult = operatorResult;
    }

    /**
     * Get
     *
     * @return
     */
    public String getOperatorDescription() {
        return operatorDescription;
    }

    /**
     * Set
     *
     * @param operatorDescription
     */
    public void setOperatorDescription(String operatorDescription) {
        this.operatorDescription = operatorDescription;
    }

    /**
     * Get
     *
     * @return
     */
    public Date getOperatorTime() {
        return operatorTime;
    }

    /**
     * Set
     *
     * @param operatorTime
     */
    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
