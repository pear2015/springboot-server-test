package com.gs.crms.crimenotice.service.entity.dictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zhangqiang on 2017/10/30.
 * 公告状态
 */
@Entity
@Table(name = "d_notice_status", schema = "crms_crime")
public class NoticeStatusEntity {

    /**
     * ID
     */
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    /**
     * 名称
     */
    @Column(name = "name", length = 128)
    private String name;

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
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
