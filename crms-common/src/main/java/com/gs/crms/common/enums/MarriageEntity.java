package com.gs.crms.common.enums;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 婚姻
 * Created by tanjie on 2017/7/10.
 */
@Entity
@Table(name = "d_marriage", schema = "crms_crime")
public class MarriageEntity {
    /*
    主键ID
     */
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String marriageId;

    /*
    名称
     */
    @Column(name = "name", length = 128, nullable = false)
    private String name;

    /*
    描述
     */
    @Column(name = "description", length = 2000)
    private String description;

    public String getMarriageId() {
        return marriageId;
    }

    public void setMarriageId(String marriageId) {
        this.marriageId = marriageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
