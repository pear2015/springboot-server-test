package com.gs.crms.common.enums;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zhengyali on 2018/3/6.
 */
@Entity
@Table(name = "d_career", schema = "crms_crime")
public class CareerEntity {
    /*
 主键ID
  */
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String careerId;

    /*
    名称
     */
    @Column(name = "name", length = 128, nullable = false)
    private String name;

    public String getCareerId() {
        return careerId;
    }

    public void setCareerId(String careerId) {
        this.careerId = careerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
