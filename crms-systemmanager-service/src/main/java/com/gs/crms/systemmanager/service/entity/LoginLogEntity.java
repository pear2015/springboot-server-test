package com.gs.crms.systemmanager.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by zhangqiang on 2017/11/2.
 * 用户登陆登出日志
 */
@Entity
@Table(name = "h_login_log", schema = "crms_crime")
public class LoginLogEntity {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    /**
     * 登陆时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 登出时间
     */
    @Column(name = "login_out_time")
    private Date loginOutTime;

    /**
     * 时间差 单位：分钟（登录时间登出时间）
     */
    @Column(name = "time_span")
    private int timeSpan;

    /**
     * 人员ID
     */
    @Column(name = "user_id", length = 128)
    private String userId;

    /**
     * 中心编码
     */
    @Column(name = "center_code", length = 128)
    private String centerCode;
    /**
     * 中心名称
     */
    @Column(name = "center_name", length = 128)
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

    /**
     * Get
     *
     * @return
     */
    public String getCenterName() {
        return centerName;
    }

    /**
     * Set
     *
     * @param centerName
     */
    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }
}
