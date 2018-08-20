package com.gs.crms.applycertify.contract.model;

import javax.validation.constraints.Size;

/**
 * 申请和公告关联
 * Created by zhangqiang on 2017/8/22.
 */
public class ApplyAndCriminalRelation {
    /**
     * 申请ID
     */
    @Size(max = 36, message = "applyInfoId max length is 128")
    private String applyInfoId;
    /**
     * 罪犯ID
     */
    @Size(max = 2000, message = "applyInfoId max length is 2000")
    private String criminalId;

    /**
     * Get
     *
     * @return
     */
    public String getApplyInfoId() {
        return applyInfoId;
    }

    /**
     * Set
     *
     * @param applyInfoId
     */
    public void setApplyInfoId(String applyInfoId) {
        this.applyInfoId = applyInfoId;
    }

    /**
     * Get
     *
     * @return
     */
    public String getCriminalId() {
        return criminalId;
    }

    /**
     * Set
     *
     * @param criminalId
     */
    public void setCriminalId(String criminalId) {
        this.criminalId = criminalId;
    }
}
