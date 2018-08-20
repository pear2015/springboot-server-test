package com.gs.crms.common.model;


/**
 * Created by zhengyali on 2018/3/6.
 */
public class CareerInfo {
    /*
主键ID
 */
    private String careerId;
    /*
    名称
     */
    private String name;

    public String getCareerId() {
        return careerId;
    }

    public void setCareerId(String careerId) {
        this.careerId = careerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
