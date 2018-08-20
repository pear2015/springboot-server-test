package com.gs.crms.crimenotice.contract.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by tanjie on 2017/7/28.
 */
public class CrimePersonInfo {
    /**
     * 主键ID
     */
    @Size(max=36)
    private String crimePersonId;
    /**
     * 性别ID
     */
    @NotNull
    @Size(max=36)
    private String sexId;
    /**
     * 性别名称
     */
    private String sexName;
    /**
     * 婚姻
     */
    @Size(max=36)
    private String marriageId;

    /**
     * 婚姻名称
     */
    private String marriageName;

    /**
     * 姓
     */
    @NotNull
    @Size(max=128)
    private String firstName;
    /**
     * 名
     */
    @NotNull
    @Size(max=128)
    private String lastName;
    /**
     * 证件号码
     */
    @Size(max=128)
    private String certificateNumber;

    /**
     * 证件类型
     */
    @NotNull
    @Size(max=36)
    private String certificateType;
    /**
     * 证件类型名称
     */
    private String certificateName;

    /**
     * 证件有效期
     */
    @Size(max=128)
    private String certificateValidity;

    /**
     * 证件颁发地
     */
    @Size(max=2000)
    public String credentialsIssuePlace;
    /**
     * 证件颁发日期
     */
    public Date credentialsIssueDate;

    /**
     * 年龄
     */
    @Max(200)
    private int age;

    /**
     * 身高
     */
    private double height;

    /**
     * 鼻型
     */
    @Size(max = 128)
    private String noseType;

    /**
     * 职业
     */
    @Size(max = 256)
    private String profession;

    /**
     * 联系电话
     */
    @Size(max = 20)
    private String contractPhone;

    /**
     * 眼睛颜色
     */
    @Size(max = 128)
    private String eyeColor;

    /**
     * 肤色
     */
    @Size(max = 128)
    private String skinColor;

    /**
     * 头发颜色
     */
    @Size(max = 128)
    private String hairColor;

    /**
     * 名族
     */
    @Size(max = 128)
    private String nation;

    /**
     * 学历
     */
    @Size(max = 36, message = "career max length is 128")
    private String careerId;

    private String careerName;

    /**
     * 其他特征
     */
    @Size(max = 2000)
    private String otherFeature;

    /**
     * 母亲名
     */
    @Size(max = 128)
    private String motherLastName;
    /**
     * 母亲姓
     */
    @Size(max = 128)
    private String motherFirstName;
    /**
     * 母亲姓名发音
     */
    @Size(max = 256)
    private String motherNameSoundex;

    /**
     * 父亲名
     */
    @Size(max = 128)
    private String fatherLastName;
    /**
     * 父亲姓
     */
    @Size(max = 128)
    private String fatherFirstName;

    /**
     * 父亲姓名发音
     */
    @Size(max = 256)
    private String fatherNameSoundex;
    /**
     * 出生国家名称
     */
    @Size(max = 128)
    private String countryName;

    /**
     * 出生省名称
     */
    @Size(max = 128)
    private String provinceName;
    /**
     * 出生市名称
     */
    @Size(max = 128)
    private String cityName;
    /**
     * 出生社区名称
     */
    @Size(max = 128)
    private String communityName;
    /**
     * 出生详细地址
     */
    @Size(max = 2000)
    private String detailAddress;
    /**
     * 描述
     */
    @Size(max = 2000)
    private String description;

    /**
     * 登记照片
     */

    private byte[] registrationPhoto;

    /**
     * 出生日期
     */

    private Date dateOfBirth;

    /**
     * 居住国家
     */
    @Size(max = 128)
    private String livingCountryName;
    /**
     * 居住省
     */
    @Size(max = 128)
    private String livingProvinceName;
    /**
     * 居住城市
     */
    @Size(max = 128)
    private String livingCityName;
    /**
     * 居住社区
     */
    @Size(max = 128)
    private String livingCommunityName;
    /**
     * 删除标志
     */
    @Size(max = 36)
    private String isActive;
    /**
     * 录入人ID
     */
    @Size(max = 36)
    private String enterPersonId;
    /**
     * 录入时间
     */
    private Date enteringTime;
    /**
     * 录入人员姓名
     */
    @Size(max = 128)
    private String enteringPersonName;
    /**
     * 回填罪犯ID
     */
    @Size(max = 36)
    private String backFillCriminalId;
    /**
     * 个人档案编号
     */
    @Size(max = 128)
    private String archivesCode;
    /**
     * 正在被合并
     */
    @Size(max = 36)
    private String isMerging;
    /**
     * 父亲身份证号码
     */
    @Size(max = 128, message = "fatherCertificateNumber max length is 128")
    private String fatherCertificateNumber;
    /**
     * 母亲身份证号码
     */
    @Size(max = 128, message = "motherCertificateNumber max length is 128")
    private String motherCertificateNumber;

    public String getIsMerging() {
        return isMerging;
    }

    public void setIsMerging(String isMerging) {
        this.isMerging = isMerging;
    }

    public String getCrimePersonId() {
        return crimePersonId;
    }

    public void setCrimePersonId(String crimePersonId) {
        this.crimePersonId = crimePersonId;
    }


    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }


    public String getMarriageName() {
        return marriageName;
    }

    public void setMarriageName(String marriageName) {
        this.marriageName = marriageName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getCertificateValidity() {
        return certificateValidity;
    }

    public void setCertificateValidity(String certificateValidity) {
        this.certificateValidity = certificateValidity;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getNoseType() {
        return noseType;
    }

    public void setNoseType(String noseType) {
        this.noseType = noseType;
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

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
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

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getRegistrationPhoto() {
        return registrationPhoto;
    }

    public void setRegistrationPhoto(byte[] registrationPhoto) {
        this.registrationPhoto = registrationPhoto;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getEnterPersonId() {
        return enterPersonId;
    }

    public void setEnterPersonId(String enterPersonId) {
        this.enterPersonId = enterPersonId;
    }

    public Date getEnteringTime() {
        return enteringTime;
    }

    public void setEnteringTime(Date enteringTime) {
        this.enteringTime = enteringTime;
    }

    public String getEnteringPersonName() {
        return enteringPersonName;
    }

    public void setEnteringPersonName(String enteringPersonName) {
        this.enteringPersonName = enteringPersonName;
    }

    public String getSexId() {
        return sexId;
    }

    public void setSexId(String sexId) {
        this.sexId = sexId;
    }

    public String getMarriageId() {
        return marriageId;
    }

    public void setMarriageId(String marriageId) {
        this.marriageId = marriageId;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getArchivesCode() {
        return archivesCode;
    }

    public void setArchivesCode(String archivesCode) {
        this.archivesCode = archivesCode;
    }

    public String getBackFillCriminalId() {
        return backFillCriminalId;
    }

    public void setBackFillCriminalId(String backFillCriminalId) {
        this.backFillCriminalId = backFillCriminalId;
    }

    public String getFatherCertificateNumber() {
        return fatherCertificateNumber;
    }

    public void setFatherCertificateNumber(String fatherCertificateNumber) {
        this.fatherCertificateNumber = fatherCertificateNumber;
    }

    public String getMotherCertificateNumber() {
        return motherCertificateNumber;
    }

    public void setMotherCertificateNumber(String motherCertificateNumber) {
        this.motherCertificateNumber = motherCertificateNumber;
    }

    public String getCareerId() {
        return careerId;
    }

    public void setCareerId(String careerId) {
        this.careerId = careerId;
    }

    public String getCareerName() {
        return careerName;
    }

    public void setCareerName(String careerName) {
        this.careerName = careerName;
    }
}
