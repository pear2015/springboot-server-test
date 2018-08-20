package com.gs.crms.common.enums;

/**
 * Created by zhengyali on 2017/12/26.
 */
public enum MarriageType {
    /**
     * 未婚
     */
    UNMARRIED(0),
    /**
     * 已婚
     */
    MARRIED(1),
    /**
     * 离异
     */
    DIVORCED(2),
    /**
     *丧偶
     */
    WIDOWER(3),
    /**
     * 未知
     */
    UNKNOW(4);
    private int paramType;

    MarriageType(int paramType) {
        this.paramType = paramType;
    }

    /**
     * Gets param type.
     *
     * @return the param type
     */
    public int getParamType() {
        return this.paramType;
    }
}
