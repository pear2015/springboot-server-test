package com.gs.crms.crimenotice.service.entity.dictionary;

import javax.persistence.*;
import java.util.Date;

/**
 * 法院
 * Created by tanjie on 2017/7/10.
 */
@Entity
@Table(name = "d_court", schema = "crms_crime")
public class CourtEntity {
    /*
   主键ID
   */
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String courtId;
    /*
    名称
     */
    @Column(name = "court_name", length = 128, nullable = false)
    private String name;

    /*
    地址
    */
    @Column(name = "court_address", length = 128)
    private String courtAddress;

    /*
    创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /*
   创建人员姓名
    */
    @Column(name = "create_person_name", length = 128)
    private String createPersonName;
    /*
   修改时间
    */
    @Column(name = "modify_time")
    private Date modifyTime;
    /*
   修改人姓名
    */
    @Column(name = "modify_person_name", length = 128)
    private String modifyPersonName;
    /*
   描述
    */
    @Column(name = "description", length = 2000)
    private String description;

    /*
    省ID 没有外键关联 不确定社区是属于省还是市还是社区
     */
    @Column(name = "province_id", length = 128)
    private String provinceId;

    /*
    市ID 没有外键关联 不确定社区是属于省还是市还是社区
     */
    @Column(name = "city_id", length = 128)
    private String cityId;

    /*
    社区ID 没有外键关联 不确定社区是属于省还是市还是社区
     */
    @Column(name = "community_id", length = 128)
    private String communityId;

    public String getCourtId() {
        return courtId;
    }

    public void setCourtId(String courtId) {
        this.courtId = courtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourtAddress() {
        return courtAddress;
    }

    public void setCourtAddress(String courtAddress) {
        this.courtAddress = courtAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatePersonName() {
        return createPersonName;
    }

    public void setCreatePersonName(String createPersonName) {
        this.createPersonName = createPersonName;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyPersonName() {
        return modifyPersonName;
    }

    public void setModifyPersonName(String modifyPersonName) {
        this.modifyPersonName = modifyPersonName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }
}
