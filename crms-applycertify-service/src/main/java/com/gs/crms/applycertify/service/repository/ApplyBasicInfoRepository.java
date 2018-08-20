package com.gs.crms.applycertify.service.repository;

import com.gs.crms.applycertify.service.entity.ApplyBasicInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 申请个人基本信息Repository
 * Created by zhangqiang on 2017/8/22.
 */
@Repository
public interface ApplyBasicInfoRepository extends JpaRepository<ApplyBasicInfoEntity, Long>, JpaSpecificationExecutor {
    /**
     * 通过ID查询人员
     *
     * @param applyBasicID
     * @return
     */
    ApplyBasicInfoEntity getApplyBasicInfoEntityByApplyBasicId(String applyBasicID);

    /**
     * get by name
     *
     * @param firstName
     * @param lastName
     * @return
     */
    List<ApplyBasicInfoEntity> getAllByFirstNameAndLastName(String firstName, String lastName);

    /**
     * 通过身份证查询人员
     *
     * @param certificateNumber
     * @return
     */
    List<ApplyBasicInfoEntity> findAllByCertificateTypeAndCertificateNumber(String certificateType, String certificateNumber);
}
