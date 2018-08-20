package com.gs.crms.applycertify.service.repository;

import com.gs.crms.common.enums.CertificateTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangqiang on 2017/9/25.
 * 证件类型Repository
 */
@Repository
public interface CertifcateTypeRepository extends JpaRepository<CertificateTypeEntity, Long> {

    /**
     * Gets apply certificate entity list.
     * 获取证件类型列表
     *
     * @return the apply certificate entity list
     */
    @Query("select a from CertificateTypeEntity a ORDER BY a.certificateTypeId  ASC ")
    List<CertificateTypeEntity> getCertificateTypeEntityList();

    /**
     * 个人申请的证件列表
     * @param isValid
     * @return
     */
    @Query("select a from CertificateTypeEntity a  where a.personIsValid=?1 ORDER BY a.certificateTypeId  ASC ")
    List<CertificateTypeEntity> getCertificateTypeListByPersonIsValid(String isValid);

    /**
     * 政府申请
     * @param isValid
     * @return
     */
    @Query("select a from CertificateTypeEntity a  where a.governmentIsValid=?1 ORDER BY a.certificateTypeId  ASC ")
    List<CertificateTypeEntity> getCertificateTypeListByGovernmentIsValid(String isValid);

    /**
     * 公告的证件列表
     * @param isValid
     * @return
     */
    @Query("select a from CertificateTypeEntity a  where a.noticeIsValid=?1 ORDER BY a.certificateTypeId  ASC ")
    List<CertificateTypeEntity> getCertificateTypeListByNoticeIsValid(String isValid);
}
