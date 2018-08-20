package com.gs.crms.applycertify.service.entity.dictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zhangqiang on 2017/10/12.
 */
@Entity
@Table(name = "d_apply_priority", schema = "crms_crime")
public class ApplyPriorityEntity {
    /*
    * 主键ID
    */
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String applyPriorityId;

    /*
    优先级名称
     */
    @Column(name = "name", length = 128, nullable = false)
    private String name;

    /*
    描述
     */
    @Column(name = "description", length = 2000)
    private String description;

    /**
     * Get
     *
     * @return
     */
    public String getApplyPriorityId() {
        return applyPriorityId;
    }

    /**
     * Set
     *
     * @param applyPriorityId
     */
    public void setApplyPriorityId(String applyPriorityId) {
        this.applyPriorityId = applyPriorityId;
    }

    /**
     * Get
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
