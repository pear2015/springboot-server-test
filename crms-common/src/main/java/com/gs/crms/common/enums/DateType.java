package com.gs.crms.common.enums;

/**
 * Created by wangzuzhi on 2017/11/2.
 */
public enum DateType {

    /**
     * 按年
     */
    YEAR(0),

    /**
     * 按月
     */
    MONTH(1),

    /**
     * 按日
     */
    DAY(2),

    /**
     * 按自定义年
     */
    YEARDIY(3),

    /**
     * 按自定义月
     */
    MONTHDIY(4),

    /**
     * 按自定义日
     */
    DAYDIY(5);

    private int paramType;

    DateType(int paramType) {
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
