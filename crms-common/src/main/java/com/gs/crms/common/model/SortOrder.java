package com.gs.crms.common.model;

/**
 * Created by zhangqiang on 2017/10/18.
 */
public class SortOrder {
    /**
     * 升序降序0升序，1降序
     */
    private int ascDsc;
    /**
     * 属性
     */
    private String properties;

    /**
     * Get
     *
     * @return
     */
    public int getAscDsc() {
        return ascDsc;
    }

    /**
     * Set
     *
     * @param ascDsc
     */
    public void setAscDsc(int ascDsc) {
        this.ascDsc = ascDsc;
    }

    /**
     * Get
     *
     * @return
     */
    public String getProperties() {
        return properties;
    }

    /**
     * Set
     *
     * @param properties
     */
    public void setProperties(String properties) {
        this.properties = properties;
    }
}