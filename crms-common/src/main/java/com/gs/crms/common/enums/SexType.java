package com.gs.crms.common.enums;

/**
 * Created by zhengyali on 2017/12/26.
 */
public enum SexType {
    /**
     * 男
     */
    MALE(0),
    /**
     * 女
     */
    FEMALE(1);
    private int paramType;

    SexType(int paramType) {
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
