package com.gs.crms.statisticsanalysis.contract.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zhengyali on 2018/3/8.
 */
public class ReportApplyModel {
    @ApiModelProperty(value = "待分析")
    private long   unAnalyst;
    @ApiModelProperty(value = "待审核")
    private long unAudit;
    @ApiModelProperty(value = "待打印")
    private long unPrint;

    public long getUnAnalyst() {
        return unAnalyst;
    }

    public void setUnAnalyst(long unAnalyst) {
        this.unAnalyst = unAnalyst;
    }

    public long getUnAudit() {
        return unAudit;
    }

    public void setUnAudit(long unAudit) {
        this.unAudit = unAudit;
    }

    public long getUnPrint() {
        return unPrint;
    }

    public void setUnPrint(long unPrint) {
        this.unPrint = unPrint;
    }
}
