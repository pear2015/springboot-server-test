package com.gs.crms.crimenotice.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by zhangqiang on 2017/11/11.
 * 以合并罪犯记录表，可用于拆分
 */
@Entity
@Table(name = "b_merged_criminal", schema = "crms_crime")
public class MergedCriminalEntity {

    /**
     * Id
     */
    @Id
    @Column(name = "id", length = 36, nullable = false)
    private String id;
    /**
     * 公告Id
     */
    @Column(name = "notice_id", length = 36)
    private String noticeId;
    /**
     * 已合并罪犯Id
     */
    @Column(name = "criminal_id", length = 36)
    private String criminalId;
    /**
     * 已合并罪犯的公告Id列表
     */
    @Column(name = "crime_notice_ids", length = 2000)
    private String criminalNoticeIds;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date CreateTime;
    /**
     * 目标罪犯ID
     */
    @Column(name = "object_criminal_id")
    private String objectCriminalId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getCriminalId() {
        return criminalId;
    }

    public void setCriminalId(String criminalId) {
        this.criminalId = criminalId;
    }

    public String getCriminalNoticeIds() {
        return criminalNoticeIds;
    }

    public void setCriminalNoticeIds(String criminalNoticeIds) {
        this.criminalNoticeIds = criminalNoticeIds;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public String getObjectCriminalId() {
        return objectCriminalId;
    }

    public void setObjectCriminalId(String objectCriminalId) {
        this.objectCriminalId = objectCriminalId;
    }
}
