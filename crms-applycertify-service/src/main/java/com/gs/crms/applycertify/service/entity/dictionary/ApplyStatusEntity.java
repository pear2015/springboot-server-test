package com.gs.crms.applycertify.service.entity.dictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tanjie on 2017/8/8.
 */
@Entity
@Table(name = "d_apply_status", schema = "crms_crime")
public class ApplyStatusEntity {
    /*
  主键ID
   */
    @Id
    @Column(name = "id", nullable = false,length = 36)
    private String applyStatusId;

    /*
    申请状态名称
     */
    @Column(name = "name",length = 128,nullable = false)
    private String name;

    /*
    描述
     */
    @Column(name = "description",length = 2000)
    private  String description;

    public String getApplyStatusId() {
        return applyStatusId;
    }

    public void setApplyStatusId(String applyStatusId) {
        this.applyStatusId = applyStatusId;
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
