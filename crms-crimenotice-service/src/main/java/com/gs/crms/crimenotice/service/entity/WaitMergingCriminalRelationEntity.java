package com.gs.crms.crimenotice.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by zhangqiang on 2017/11/11.\
 * 待合并罪犯关联表
 */
@Entity
@Table(name = "b_wait_merging_criminal", schema = "crms_crime")
public class WaitMergingCriminalRelationEntity {
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
     * 等待分派罪犯Id列表
     */
    @Column(name = "wait_criminal_ids", length = 2000)
    private String waitCriminalIds;
    /**
     * 目标罪犯Id
     */
    @Column(name = "target_criminal_id", length = 36)
    private String targetCriminalId;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date CreateTime;

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

    public String getWaitCriminalIds() {
        return waitCriminalIds;
    }

    public void setWaitCriminalIds(String waitCriminalIds) {
        this.waitCriminalIds = waitCriminalIds;
    }

    public String getTargetCriminalId() {
        return targetCriminalId;
    }

    public void setTargetCriminalId(String targetCriminalId) {
        this.targetCriminalId = targetCriminalId;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }
}
