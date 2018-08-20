package com.gs.crms.statisticsanalysis.contract.model;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
/**
 * Created by zhengyali on 2017/11/21.
 */
public class ReportCrimeParamModel {
    @ApiModelProperty(value = "日期类型:[0]:按年;[1]:按月;[2]当天;[3]:自定义年;[4]:自定义月;[5]:自定义日;[6]本周")
    private String dateType;
    @ApiModelProperty(value = "自定义日期开始时间")
    private Date startDate;
    @ApiModelProperty(value = "自定义日期结束时间")
    private Date endDate;
    @ApiModelProperty(value = "采集点Id")
    private String fo;
    @ApiModelProperty(value = "法院Id")
    private String courtId;
    @ApiModelProperty(value = "犯罪类型")
    private String crimeTypeId;
    @ApiModelProperty(value = "犯罪区域Id")
    private String crimeRegionId;
    @ApiModelProperty(value = "犯罪区域名称")
    private String crimeRegionName;
    @ApiModelProperty(value = "时间点: 年:[yyyy];月:[yyyy-MM];日:[yyyy-MM-dd]")
    private Date timePoint;
    public enum DateType{
        year("0"),month("1"), day("2"),yearDIY("3"),monthDIY("4"),dayDIY("5");
        private String num;
        private  DateType(String num){
            this.num = num;
        }
        public String getNum() {
            return num;
        }
    }

    public Date getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(Date timePoint) {
        this.timePoint = timePoint;
    }
    public String getDateType() {
        if(StringUtils.isEmpty(dateType)){
            return "2";
        }else{
            return  dateType;
        }
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getFo() {
        return fo;
    }

    public void setFo(String fo) {
        this.fo = fo;
    }

    public String getCourtId() {
        return courtId;
    }

    public void setCourtId(String courtId) {
        this.courtId = courtId;
    }

    public String getCrimeTypeId() {
        return crimeTypeId;
    }

    public void setCrimeTypeId(String crimeTypeId) {
        this.crimeTypeId = crimeTypeId;
    }

    public String getCrimeRegionId() {
        return crimeRegionId;
    }

    public void setCrimeRegionId(String crimeRegionId) {
        this.crimeRegionId = crimeRegionId;
    }

    public String getCrimeRegionName() {
        return crimeRegionName;
    }

    public void setCrimeRegionName(String crimeRegionName) {
        this.crimeRegionName = crimeRegionName;
    }
}
