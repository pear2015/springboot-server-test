package com.gs.crms.statisticsanalysis.contract.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zhengyali on 2018/1/23.
 */
public class ReportReturnModel {
    @ApiModelProperty(value = "当天")
    private int currentDay;
    @ApiModelProperty(value = "本周")
    private int currentWeek;
    @ApiModelProperty(value = "本月")
    private int currentMonth;
    @ApiModelProperty(value = "本年")
    private int currentYear;

    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }

    public int getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(int currentWeek) {
        this.currentWeek = currentWeek;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }
}
