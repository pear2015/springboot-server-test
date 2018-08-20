package com.gs.crms.applycertify.contract.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 申请人员基本信息
 *
 * @author zhangqiang
 * @version 1.0
 * @created 22 -八月-2017 14:22:13
 */
public class ApplyBasicInfo {
    private String applyBasicId;

    @Max(200)
    private int age;

    @Size(max = 36, message = "career max length is 128")
    private String careerId;

    private String careerName;

    @Size(max = 128, message = "certificateNumber max length is 128")
    private String certificateNumber;

    @Size(max = 36, message = "certificateType max length is 36")
    private String certificateType;

    @Size(max = 255, message = "certificateValidity max length is 255")
    private String certificateValidity;

    @Size(max = 128, message = "cityName max length is 128")
    private String cityName;

    @Size(max = 128, message = "communityName max length is 128")
    private String communityName;

    @Size(max = 20, message = "contractPhone max length is 20")
    private String contractPhone;

    @Size(max = 256, message = "countryName max length is 256")
    private String countryName;

    @Size(max = 2000, message = "description max length is 2000")
    private String description;

    @Size(max = 2000, message = "detailAddress max length is 2000")
    private String detailAddress;

    @Size(max = 128, message = "fatherLastName max length is 128")
    private String fatherLastName;

    @Size(max = 128, message = "fatherFirstName max length is 128")
    private String fatherFirstName;

    @Size(max = 256, message = "fatherNameSoundex max length is 256")
    private String fatherNameSoundex;

    @Size(max = 36, message = "marriageID max length is 36")
    private String marriageId;

    @Size(max = 128, message = "motherLastName max length is 128")
    private String motherLastName;

    @Size(max = 128, message = "motherFirstName max length is 128")
    private String motherFirstName;

    @Size(max = 256, message = "motherNameSoundex max length is 256")
    private String motherNameSoundex;

    @Size(max = 128, message = "lastName max length is 128")
    private String lastName;

    @Size(max = 128, message = "firstName max length is 128")
    private String firstName;

    @Size(max = 128, message = "nation max length is 128")
    private String nation;

    @Size(max = 2000, message = "otherFeature max length is 2000")
    private String otherFeature;

    @Size(max = 128, message = "profession max length is 128")
    private String profession;

    @Size(max = 128, message = "provinceName max length is 36")
    private String provinceName;

    @Size(max = 36, message = "sexId max length is 36")
    private String sexId;

    private Date dateOfBirth;

    @Size(max = 128, message = "countryOfCitizenship max length is 128")
    private String countryOfCitizenship;

    @Size(max = 2000, message = "credentialsIssuePlace max length is 2000")
    private String credentialsIssuePlace;

    private Date credentialsIssueDate;

    private String sexName;
    private String marriageName;
    private String certificateName;
    private byte[] registrationPhoto;

    @Size(max = 256, message = "livingCountryName max length is 36")
    private String livingCountryName;
    @Size(max = 256, message = "livingProvinceName max length is 36")
    private String livingProvinceName;
    @Size(max = 256, message = "livingCityName max length is 36")
    private String livingCityName;
    @Size(max = 256, message = "livingCommunityName max length is 36")
    private String livingCommunityName;
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

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(int age) {
        this.age = age;
    }



    /**
     * Gets certificate number.
     *
     * @return the certificate number
     */
    public String getCertificateNumber() {
        return certificateNumber;
    }

    /**
     * Sets certificate number.
     *
     * @param certificateNumber the certificate number
     */
    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    /**
     * Gets certificate type.
     *
     * @return the certificate type
     */
    public String getCertificateType() {
        return certificateType;
    }

    /**
     * Sets certificate type.
     *
     * @param certificateType the certificate type
     */
    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    /**
     * Gets certificate validity.
     *
     * @return the certificate validity
     */
    public String getCertificateValidity() {
        return certificateValidity;
    }

    /**
     * Sets certificate validity.
     *
     * @param certificateValidity the certificate validity
     */
    public void setCertificateValidity(String certificateValidity) {
        this.certificateValidity = certificateValidity;
    }

    /**
     * Gets city name.
     *
     * @return the city name
     */
    public String getCityName() {
        return cityName;
    }

    public String getApplyBasicId() {
        return applyBasicId;
    }

    public void setApplyBasicId(String applyBasicId) {
        this.applyBasicId = applyBasicId;
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

    public String getContractPhone() {
        return contractPhone;
    }

    public void setContractPhone(String contractPhone) {
        this.contractPhone = contractPhone;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
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

    public String getMarriageId() {
        return marriageId;
    }

    public void setMarriageId(String marriageId) {
        this.marriageId = marriageId;
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getSexId() {
        return sexId;
    }

    public void setSexId(String sexId) {
        this.sexId = sexId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountryOfCitizenship() {
        return countryOfCitizenship;
    }

    public void setCountryOfCitizenship(String countryOfCitizenship) {
        this.countryOfCitizenship = countryOfCitizenship;
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

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public byte[] getRegistrationPhoto() {
        return registrationPhoto;
    }

    public void setRegistrationPhoto(byte[] registrationPhoto) {
        this.registrationPhoto = registrationPhoto;
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