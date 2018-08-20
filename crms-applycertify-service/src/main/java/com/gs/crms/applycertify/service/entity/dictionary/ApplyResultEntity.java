package com.gs.crms.applycertify.service.entity.dictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zhangqiang on 2017/9/25.
 * 申请结果
 */
@Entity
@Table(name = "d_apply_result", schema = "crms_crime")
public class ApplyResultEntity {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String applyResultId;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "name", length = 128)
    private String name;

    /**
     * Get
     *
     * @return
     */
    public String getApplyResultId() {
        return applyResultId;
    }

    /**
     * Set
     *
     * @param applyResultId
     */
    public void setApplyResultId(String applyResultId) {
        this.applyResultId = applyResultId;
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
}
