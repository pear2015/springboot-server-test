package com.gs.crms.crimenotice.service.entity.dictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zhangqiang on 2017/11/6.
 * 公告优先级
 */
@Entity
@Table(name = "d_notice_priority", schema = "crms_crime")
public class NoticePriorityEntity {

    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String noticePriorityId;

    @Column(name = "name", length = 128)
    private String name;

    public String getNoticePriorityId() {
        return noticePriorityId;
    }

    public void setNoticePriorityId(String noticePriorityId) {
        this.noticePriorityId = noticePriorityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
