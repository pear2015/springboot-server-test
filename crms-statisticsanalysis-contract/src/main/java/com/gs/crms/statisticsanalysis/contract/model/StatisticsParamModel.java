package com.gs.crms.statisticsanalysis.contract.model;

import com.gs.crms.common.enums.DateType;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by wangzuzhi on 2017/11/2.
 */
public class StatisticsParamModel {
    @ApiModelProperty(value = "日期类型:[0]:按年;[1]:按月;[2]:自定义年;[3]:自定义月;[4]:自定义日")
    private int dateType;
    @ApiModelProperty(value = "时间枚举类型")
    private DateType dateEnums;
    @ApiModelProperty(value = "自定义日期开始时间")
    private Date startDate;
    @ApiModelProperty(value = "自定义日期结束时间")
    private Date endDate;
    @ApiModelProperty(value = "时间点: 年:[yyyy];月:[yyyy-MM];日:[yyyy-MM-dd]")
    private Date timePoint;
    @ApiModelProperty(value = "法院")
    private List<String> courts;
    @ApiModelProperty(value = "犯罪类型")
    private List<String> crimeTypes;
    @ApiModelProperty(value = "中心编码")
    private List<String> centerCodes;

    /**
     * Gets date type.
     *
     * @return the date type
     */
    public int getDateType() {
        return dateType;
    }

    /**
     * Sets date type.
     *
     * @param dateType the date type
     */
    public void setDateType(int dateType) {
        this.dateType = dateType;
    }

    /**
     * Gets date enums.
     *
     * @return the date enums
     */
    public DateType getDateEnums() {
        DateType result = DateType.DAYDIY;
        for (DateType dt :
                DateType.values()) {
            if (dt.getParamType() == this.dateType) {
                result = dt;
                break;
            }
        }
        return result;
    }

    /**
     * Sets date enums.
     *
     * @param dateEnums the date enums
     */
    public void setDateEnums(DateType dateEnums) {
        this.dateEnums = dateEnums;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets time point.
     *
     * @return the time point
     */
    public Date getTimePoint() {
        return timePoint;
    }

    /**
     * Sets time point.
     *
     * @param timePoint the time point
     */
    public void setTimePoint(Date timePoint) {
        this.timePoint = timePoint;
    }

    /**
     * Gets courts.
     *
     * @return the courts
     */
    public List<String> getCourts() {
        return courts;
    }

    /**
     * Sets courts.
     *
     * @param courts the courts
     */
    public void setCourts(List<String> courts) {
        this.courts = courts;
    }

    /**
     * Gets crime types.
     *
     * @return the crime types
     */
    public List<String> getCrimeTypes() {
        return crimeTypes;
    }

    /**
     * Sets crime types.
     *
     * @param crimeTypes the crime types
     */
    public void setCrimeTypes(List<String> crimeTypes) {
        this.crimeTypes = crimeTypes;
    }

    /**
     * Gets center codes.
     *
     * @return the center codes
     */
    public List<String> getCenterCodes() {
        return centerCodes;
    }

    /**
     * Sets center codes.
     *
     * @param centerCodes the center codes
     */
    public void setCenterCodes(List<String> centerCodes) {
        this.centerCodes = centerCodes;
    }
}
