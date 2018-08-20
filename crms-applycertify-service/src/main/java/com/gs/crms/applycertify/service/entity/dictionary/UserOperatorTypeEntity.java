package com.gs.crms.applycertify.service.entity.dictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zhangqiang on 2017/9/25.
 * 用户操作类型
 */
@Entity
@Table(name = "d_user_operator_type", schema = "crms_crime")
public class UserOperatorTypeEntity {

    /*
     主键ID
      */
    @Id
    @Column(name = "id", nullable = false,length = 36)
    private String operatorTypeId;

    /*
    操作类型名称
     */
    @Column(name = "name",length = 128,nullable = false)
    private String name;

    /*
    描述
     */
    @Column(name = "description",length = 2000)
    private  String description;

    /**
     * Get
     * @return
     */
    public String getOperatorTypeId() {
        return operatorTypeId;
    }

    /**
     * Set
     * @param operatorTypeId
     */
    public void setOperatorTypeId(String operatorTypeId) {
        this.operatorTypeId = operatorTypeId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
