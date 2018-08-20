package com.gs.crms.applycertify.service.entity.history;

import com.gs.crms.applycertify.service.entity.dictionary.UserOperatorTypeEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhangqiang on 2017/9/25.
 * 无犯罪申请业务历史记录实体
 */
@Entity
@Table(name = "h_apply_business", schema = "crms_crime")
public class ApplyBussinessHistoryEntity {
    /*
    * 主键ID
    */
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;
    /**
     * 申请ID
     */
    @Column(name = "apply_id", length = 36)
    private String applyId;
    /**
     * 人员ID
     */
    @Column(name = "operator_id", length = 128)
    private String operatorId;
    /**
     * 人员名称
     */
    @Column(name = "operator_name", length = 128)
    private String operatorName;

    /*
    * 操作类型
    */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_type_id", insertable = false, updatable = false)
    private UserOperatorTypeEntity userOperatorTypeEntity;

    /**
     * 操作类型
     */
    @Column(name = "operator_type_id", length = 36)
    private String operatorTypeId;
    /**
     * 操作结果
     */
    @Column(name = "operator_result", length = 36)
    private String operatorResult;
    /**
     * 操作描述
     */
    @Column(name = "operator_description", length = 2000)
    private String operatorDescription;
    /**
     * 操作时间
     */
    @Column(name = "operator_time")
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

    /**
     * Get
     *
     * @return
     */
    public UserOperatorTypeEntity getUserOperatorTypeEntity() {
        return userOperatorTypeEntity;
    }

    /**
     * Set
     *
     * @param userOperatorTypeEntity
     */
    public void setUserOperatorTypeEntity(UserOperatorTypeEntity userOperatorTypeEntity) {
        this.userOperatorTypeEntity = userOperatorTypeEntity;
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

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
