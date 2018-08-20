package com.gs.crms.applycertify.service.repository;

import com.gs.crms.applycertify.service.entity.CertificateInfoEntity;
import com.gsafety.springboot.common.pagerepository.QueryMetaDataRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * 证书申请Repository
 * Created by zhufengjie on 2017/9/28.
 */
@Repository
public interface CertificateInfoRepository extends CrudRepository<CertificateInfoEntity, Long>, QueryMetaDataRepository<CertificateInfoEntity, Long>, JpaSpecificationExecutor {
    /**
     * 通过申请ID获取证书
     *
     * @param applyId
     * @return
     */
    CertificateInfoEntity findByApplyId(String applyId);

    /**
     * 通过证书编号获取证书
     *
     * @param certificateId
     * @return
     */
    CertificateInfoEntity findByCertificateId(Long certificateId);
}

