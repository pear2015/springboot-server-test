package com.gs.crms.common.model;

/**
 * Created by zhengyali on 2017/9/28.
 */
public enum RoleType {
    /**
     * 操作员
     */
    OPERATOR(0),

    /**
     * 分析员
     */
    ANALYST(1),
    /**
     * 审核员
     */
   AUDITOR(2),

    /**
     * 日志审计
     */
    LOGAUDITOR(3),
    /**
     * 采集点办理员
     */
    POINTOPERATOR(4),

    /**
     * 国家中心审核员
     */
    COUNTRYAUDITOR(5),

    /**
     * 公证处办理员
     */
    NOTARYOPERATOR(6),

    /**
     * 公证处审核员
     */
    NOTARYAUDITOR (7),

    /**
     * 管理员
     */
    CRIMESM(8),
    /**
     * 归档员
     */
    FILER(8),
    /**
     * 业务监控
     */
    BUSINESSMONITOR(9);


    private int paramType;

    RoleType(int  paramType) {

        this.paramType = paramType;
    }

    /**
     * Get param type int.
     *
     * @return the int
     */
    public int getParamType() {
        return this.paramType;
    }

}
