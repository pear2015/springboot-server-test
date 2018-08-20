package com.gs.crms.systemmanager.contract.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by zhangqiang on 2017/11/2.
 * 用户登陆登出日志
 */
public class LoginLog {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 登陆时间
     */
    @ApiModelProperty(value = "登陆时间")
    private Date loginTime;

    /**
     * 登出时间
     */
    @ApiModelProperty(value = "登出时间")
    private Date loginOutTime;

    /**
     * 时间差 单位：分钟（登录时间登出时间）
     */
    @ApiModelProperty(value = "时间差")
    private int timeSpan;

    /**
     * 人员ID
     */
    @ApiModelProperty(value = "人员ID")
    @Size(max = 128, message = "userId max length 128")
    private String userId;

    /**
     * 中心编码
     */
    @ApiModelProperty(value = "中心编码")
    @Size(max = 128, message = "centerCode max length 128")
    private String centerCode;
    @ApiModelProperty(value = "采集点名称")
    @Size(max = 255, message = "centerName max length 255")
    private String centerName;

    /**
     * Get
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get
     *
     * @return
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * Set
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * Get
     *
     * @return
     */
    public Date getLoginOutTime() {
        return loginOutTime;
    }

    /**
     * Set
     */
    public void setLoginOutTime(Date loginOutTime) {
        this.loginOutTime = loginOutTime;
    }

    /**
     * Get
     *
     * @return
     */
    public int getTimeSpan() {
        return timeSpan;
    }

    /**
     * Set
     *
     * @param timeSpan
     */
    public void setTimeSpan(int timeSpan) {
        this.timeSpan = timeSpan;
    }

    /**
     * Get
     *
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Get
     *
     * @return
     */
    public String getCenterCode() {
        return centerCode;
    }

    /**
     * Set
     */
    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }
}
