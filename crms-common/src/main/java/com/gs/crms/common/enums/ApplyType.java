package com.gs.crms.common.enums;

/**
 * Created by zhengyali on 2018/3/7.
 */
public enum ApplyType {
    /**
     * 个人申请
     */
    PERSON(0),
    /**
     * 政府申请
     */
    GOVENMENT(1),
    /**
     * 公告
     */
    NOTICE(2);
    private int paramType;

    ApplyType(int paramType) {
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
