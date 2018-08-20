package com.gs.crms.applycertify.service.entity;


import com.gs.crms.common.enums.CareerEntity;
import com.gs.crms.common.enums.CertificateTypeEntity;
import com.gs.crms.common.enums.MarriageEntity;
import com.gs.crms.common.enums.SexEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tanjie on 2017/8/8.
 */
@Entity
@Table(name = "b_apply_person_info", schema = "crms_crime", indexes = {@Index(name = "first_nameIndex", columnList = "first_name"), @Index(name = "last_nameIndex", columnList = "last_name"), @Index(name = "certificate_numberIndex", columnList = "certificate_number")})
public class ApplyBasicInfoEntity {
    /*
    主键ID
    */
    @Id
    @Column(name = "id", length = 36)
    private String applyBasicId;

    /*
    外键关联性别ID
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender", referencedColumnName = "id", insertable = false, updatable = false)
    private SexEntity sexEntity;

    /*
    性别ID
     */
    @Column(name = "gender", length = 36)
    private String sexId;

    /*
   外键关联婚姻ID
    */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marriage_id", referencedColumnName = "id", insertable = false, updatable = false)
    private MarriageEntity marriageEntity;

    /*
    婚姻ID
     */
    @Column(name = "marriage_id", length = 36)
    private String marriageId;

    /*
    名
     */
    @Column(name = "last_name", length = 128)
    private String lastName;
    /*
    姓
     */
    @Column(name = "first_name", length = 128)
    private String firstName;

    /**
     * 证件颁发地
     */
    @Column(name = "credentials_issue_place", length = 2000)
    private String credentialsIssuePlace;

    /**
     * 证件颁发日期
     */
    @Column(name = "credentials_issue_date")
    private Date credentialsIssueDate;
    /*
    证件号码
     */
    @Column(name = "certificate_number", length = 128)
    private String certificateNumber;

    /*
    外键证件类型ID
    */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certificate_type", referencedColumnName = "id", insertable = false, updatable = false)
    private CertificateTypeEntity certificateTypeEntity;

    /*
    证件类型
     */
    @Column(name = "certificate_type", length = 36)
    private String certificateType;

    /*
    证件有效期
     */
    @Column(name = "certificate_validity")
    private String certificateValidity;

    /*
   年龄
    */
    @Column(name = "age")
    private int age;
    /**
     * 出生日期
     */
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    /*
    职业
     */
    @Column(name = "profession", length = 128)
    private String profession;

    /*
   联系电话
    */
    @Column(name = "contact_phone", length = 20)
    private String contractPhone;

    /*
    名族
     */
    @Column(name = "nation", length = 128)
    private String nation;
    /*
          外键关联学历ID
           */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_id", insertable = false, updatable = false)
    private CareerEntity careerEntity;
    /*
    学历
     */
    @Column(name = "career_id", length = 128)
    private String careerId;

    /*
    其他特征
     */
    @Column(name = "other_feature", length = 2000)
    private String otherFeature;

    /*
    母亲名
     */
    @Column(name = "mother_last_name", length = 128)
    private String motherLastName;
    /*
    母亲姓
     */
    @Column(name = "mother_first_name", length = 128)
    private String motherFirstName;

    /*
  母亲姓名发音
   */
    @Column(name = "mother_name_soundex", length = 256)
    private String motherNameSoundex;

    /*
    父亲名
     */
    @Column(name = "father_last_name", length = 128)
    private String fatherLastName;
    /*
    父亲姓
     */
    @Column(name = "father_first_name", length = 128)
    private String fatherFirstName;

    /*
    父亲姓名发音
     */
    @Column(name = "father_name_soundex", length = 256)
    private String fatherNameSoundex;
    /*
    省名称
    */
    @Column(name = "province_name", length = 128)
    private String provinceName;
    /*
    市名称
    */
    @Column(name = "city_name", length = 128)
    private String cityName;
    /*
     社区名称
      */
    @Column(name = "community_name", length = 128)
    private String communityName;
    /*
    详细地址
    */
    @Column(name = "detail_address", length = 2000)
    private String detailAddress;

    /**
     * 居住国家
     */
    @Column(name = "living_country_name", length = 256)
    private String livingCountryName;

    /**
     * 居住省
     */
    @Column(name = "living_province_name", length = 256)
    private String livingProvinceName;

    /**
     * 居住城市
     */
    @Column(name = "living_city_name", length = 256)
    private String livingCityName;

    /**
     * 居住社区
     */
    @Column(name = "living_community_name", length = 256)
    private String livingCommunityName;


    /*
     描述
    */
    @Column(name = "description", length = 2000)
    private String description;

    /**
     * 国籍：本国/外国/侨胞
     */
    @Column(name = "country_of_citizenship", length = 128)
    private String countryOfCitizenship;

    /**
     * 登记照片
     */
    @Column(name = "registration_photo")
    private byte[] registrationPhoto;

    //  创建时间
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "change_time")
    private Date changeTime;
    /**
     * 母亲身份证号码
     */
    @Column(name = "mother_certificate_number", length = 128)
    private String motherCertificateNumber;
    /**
     * 父亲身份证号码
     */
    @Column(name = "father_certificate_number", length = 128)
    private String fatherCertificateNumber;

    public String getApplyBasicId() {
        return applyBasicId;
    }

    public void setApplyBasicId(String applyBasicId) {
        this.applyBasicId = applyBasicId;
    }

    public SexEntity getSexEntity() {
        return sexEntity;
    }

    public void setSexEntity(SexEntity sexEntity) {
        this.sexEntity = sexEntity;
    }

    public String getSexId() {
        return sexId;
    }

    public void setSexId(String sexId) {
        this.sexId = sexId;
    }

    public MarriageEntity getMarriageEntity() {
        return marriageEntity;
    }

    public void setMarriageEntity(MarriageEntity marriageEntity) {
        this.marriageEntity = marriageEntity;
    }

    public String getMarriageId() {
        return marriageId;
    }

    public void setMarriageId(String marriageId) {
        this.marriageId = marriageId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCredentialsIssuePlace() {
        return credentialsIssuePlace;
    }

    public void setCredentialsIssuePlace(String credentialsIssuePlace) {
        this.credentialsIssuePlace = credentialsIssuePlace;
    }

    public Date getCredentialsIssueDate() {
        return credentialsIssueDate;
    }

    public void setCredentialsIssueDate(Date credentialsIssueDate) {
        this.credentialsIssueDate = credentialsIssueDate;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public CertificateTypeEntity getCertificateTypeEntity() {
        return certificateTypeEntity;
    }

    public void setCertificateTypeEntity(CertificateTypeEntity certificateTypeEntity) {
        this.certificateTypeEntity = certificateTypeEntity;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateValidity() {
        return certificateValidity;
    }

    public void setCertificateValidity(String certificateValidity) {
        this.certificateValidity = certificateValidity;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getContractPhone() {
        return contractPhone;
    }

    public void setContractPhone(String contractPhone) {
        this.contractPhone = contractPhone;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCareerId() {
        return careerId;
    }

    public void setCareerId(String careerId) {
        this.careerId = careerId;
    }

    public String getOtherFeature() {
        return otherFeature;
    }

    public void setOtherFeature(String otherFeature) {
        this.otherFeature = otherFeature;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public String getMotherFirstName() {
        return motherFirstName;
    }

    public void setMotherFirstName(String motherFirstName) {
        this.motherFirstName = motherFirstName;
    }

    public String getMotherNameSoundex() {
        return motherNameSoundex;
    }

    public void setMotherNameSoundex(String motherNameSoundex) {
        this.motherNameSoundex = motherNameSoundex;
    }

    public String getFatherLastName() {
        return fatherLastName;
    }

    public void setFatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
    }

    public String getFatherFirstName() {
        return fatherFirstName;
    }

    public void setFatherFirstName(String fatherFirstName) {
        this.fatherFirstName = fatherFirstName;
    }

    public String getFatherNameSoundex() {
        return fatherNameSoundex;
    }

    public void setFatherNameSoundex(String fatherNameSoundex) {
        this.fatherNameSoundex = fatherNameSoundex;
    }
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getLivingCountryName() {
        return livingCountryName;
    }

    public void setLivingCountryName(String livingCountryName) {
        this.livingCountryName = livingCountryName;
    }

    public String getLivingProvinceName() {
        return livingProvinceName;
    }

    public void setLivingProvinceName(String livingProvinceName) {
        this.livingProvinceName = livingProvinceName;
    }

    public String getLivingCityName() {
        return livingCityName;
    }

    public void setLivingCityName(String livingCityName) {
        this.livingCityName = livingCityName;
    }

    public String getLivingCommunityName() {
        return livingCommunityName;
    }

    public void setLivingCommunityName(String livingCommunityName) {
        this.livingCommunityName = livingCommunityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountryOfCitizenship() {
        return countryOfCitizenship;
    }

    public void setCountryOfCitizenship(String countryOfCitizenship) {
        this.countryOfCitizenship = countryOfCitizenship;
    }

    public byte[] getRegistrationPhoto() {
        return registrationPhoto;
    }

    public void setRegistrationPhoto(byte[] registrationPhoto) {
        this.registrationPhoto = registrationPhoto;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getMotherCertificateNumber() {
        return motherCertificateNumber;
    }

    public void setMotherCertificateNumber(String motherCertificateNumber) {
        this.motherCertificateNumber = motherCertificateNumber;
    }

    public String getFatherCertificateNumber() {
        return fatherCertificateNumber;
    }

    public void setFatherCertificateNumber(String fatherCertificateNumber) {
        this.fatherCertificateNumber = fatherCertificateNumber;
    }

    public CareerEntity getCareerEntity() {
        return careerEntity;
    }

    public void setCareerEntity(CareerEntity careerEntity) {
        this.careerEntity = careerEntity;
    }
}
