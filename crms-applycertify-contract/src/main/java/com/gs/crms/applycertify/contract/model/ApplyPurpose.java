package com.gs.crms.applycertify.contract.model;

/**
 * 申请目的
 * Created by zhangqiang on 2017/8/23.
 */
public class ApplyPurpose {
    /**
     * 申请目的ID
     */
    private String applyPurposeId;
    /**
     * 申请目的名称
     */
    private String name;
    /**
     * 申请目的类型说明
     */
    private String description;

    public String getApplyPurposeId() {
        return applyPurposeId;
    }

    public void setApplyPurposeId(String applyPurposeId) {
        this.applyPurposeId = applyPurposeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
