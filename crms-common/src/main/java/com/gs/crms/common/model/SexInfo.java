package com.gs.crms.common.model;

/**
 * Created by zhengyali on 2018/3/7.
 */
public class SexInfo {
    /*
   主键ID
    */
    private String sexId;

    /*
    名称
     */
    private String name;

    /*
    描述
     */
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
