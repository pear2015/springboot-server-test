package com.gs.crms.statisticsanalysis.contract.model;

import io.swagger.annotations.ApiModelProperty;


/**
 * Created by chenwei on 2018/1/3.
 * 专题图数据模型 (专题图特用)
 */
public class ReportBussModel {
    @ApiModelProperty(value = "已完成业务")
    private int[] finished;
    @ApiModelProperty(value = "未完成业务")
    private int[] unFinished;
    @ApiModelProperty(value = "已拒绝业务")
    private int[] refused;

    /**
     * Get finished int [ ].
     *
     * @return the int [ ]
     */
    public int[] getFinished() {
        return finished;
    }

    /**
     * Sets finished.
     *
     * @param finished the finished
     */
    public void setFinished(int[] finished) {
        this.finished = finished;
    }

    /**
     * Get un finished int [ ].
     *
     * @return the int [ ]
     */
    public int[] getUnFinished() {
        return unFinished;
    }

    /**
     * Sets un finished.
     *
     * @param unFinished the un finished
     */
    public void setUnFinished(int[] unFinished) {
        this.unFinished = unFinished;
    }

    /**
     * Get refused int [ ].
     *
     * @return the int [ ]
     */
    public int[] getRefused() {
        return refused;
    }

    /**
     * Sets refused.
     *
     * @param refused the refused
     */
    public void setRefused(int[] refused) {
        this.refused = refused;
    }
}
