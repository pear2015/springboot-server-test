package com.gs.crms.statisticsanalysis.contract.model;

import java.sql.Timestamp;

/**
 * Created by zhufengjie on 2017/9/19.
 */
public class TimeModel {
    private Timestamp startDate;
    private Timestamp endDate;

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
}
