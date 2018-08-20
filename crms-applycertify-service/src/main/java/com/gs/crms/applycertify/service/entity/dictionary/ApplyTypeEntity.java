package com.gs.crms.applycertify.service.entity.dictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tanjie on 2017/8/8.
 * 申请类型（个人申请和政府申请）
 */
@Entity
@Table(name = "d_apply_type", schema = "crms_crime")
public class ApplyTypeEntity {
    /*
  主键ID
   */
    @Id
    @Column(name = "id", nullable = false,length = 36)
    private String applyTypeId;

    /*
    申请类型名称
     */
    @Column(name = "name",length = 128,nullable = false)
    private String name;

    /*
    描述
     */
    @Column(name = "description",length = 2000)
    private  String description;

    public String getApplyTypeId() {
        return applyTypeId;
    }

    public void setApplyTypeId(String applyTypeId) {
        this.applyTypeId = applyTypeId;
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
