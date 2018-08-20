package com.gs.crms.common.enums;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 性别
 * Created by Administrator on 2017/3/3.
 */
@Entity
@Table(name = "d_gender", schema = "crms_crime")
public class SexEntity {
    /*
    主键ID
     */
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String sexId;

    /*
    名称
     */
    @Column(name = "name", length = 128, nullable = false)
    private String name;

    /*
    描述
     */
    @Column(name = "description", length = 2000)
    private String description;


    public String getSexId() {
        return sexId;
    }

    public void setSexId(String sexId) {
        this.sexId = sexId;
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
