package com.gs.crms.statisticsanalysis.contract.model;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by zhufengjie on 2017/9/19.
 */
public class ReportParamModel {
    @ApiModelProperty(value = "日期类型:当年[0]:按月[1]:当日[2];:自定义年[3];:自定义月[4]:自定义日:[5] ")
    private String dateType;
    @ApiModelProperty(value = "自定义日期开始时间")
    private Date startDate;
    @ApiModelProperty(value = "自定义日期结束时间")
    private Date endDate;
    @ApiModelProperty(value = "采集点Id")
    private String centerCode;
    @ApiModelProperty(value = "职业")
    private String profession;
    @ApiModelProperty(value = "办理用途")
    private String applyPurposeId;
    @ApiModelProperty(value = "办理结果")
    private String applyResultId;
    @ApiModelProperty(value = "审核结果")
    private String auditResultId;
    @ApiModelProperty(value = "申请类型")
    private String applyTypeId;
    @ApiModelProperty(value = "政府类型")
    private String govermentInfo;
    @ApiModelProperty(value = "平均办理时长开始点")
    private int  startMinute;
    @ApiModelProperty(value = "平均办理时长开始点")
    private int  endMinute;
    @ApiModelProperty(value = "时间点: 年:[yyyy];月:[yyyy-MM];日:[yyyy-MM-dd]")
    private Date timePoint;
    public enum DateType{
        year("0"),month("1"), day("2"),yearDIY("3"),monthDIY("4"),dayDIY("5"), NoData("6");
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
            return "6";
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

    public String getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getApplyPurposeId() {
        return applyPurposeId;
    }

    public void setApplyPurposeId(String applyPurposeId) {
        this.applyPurposeId = applyPurposeId;
    }

    public String getApplyResultId() {
        return applyResultId;
    }

    public void setApplyResultId(String applyResultId) {
        this.applyResultId = applyResultId;
    }

    public String getApplyTypeId() {
        return applyTypeId;
    }

    public void setApplyTypeId(String applyTypeId) {
        this.applyTypeId = applyTypeId;
    }

    public String getGovermentInfo() {
        return govermentInfo;
    }

    public void setGovermentInfo(String govermentInfo) {
        this.govermentInfo = govermentInfo;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    public String getAuditResultId() {
        return auditResultId;
    }

    public void setAuditResultId(String auditResultId) {
        this.auditResultId = auditResultId;
    }
}
