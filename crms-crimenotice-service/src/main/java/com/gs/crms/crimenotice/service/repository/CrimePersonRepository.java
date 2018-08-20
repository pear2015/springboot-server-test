package com.gs.crms.crimenotice.service.repository;

import com.gs.crms.crimenotice.service.entity.CrimePersonInfoEntity;
import com.gs.crms.crimenotice.service.repository.extend.CrimePersonRepositoryExtend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Created by tanjie on 2017/7/28.
 */
public interface CrimePersonRepository extends JpaRepository<CrimePersonInfoEntity, Long>, JpaSpecificationExecutor, CrimePersonRepositoryExtend {
    /**
     * get crimePersonInfo
     *
     * @param certificateId
     * @param firstName
     * @param lastName
     * @return
     */
    List<CrimePersonInfoEntity> getByCertificateNumberLikeAndFirstNameLikeAndLastNameLike(String certificateId, String firstName, String lastName);

    /**
     * 通过姓名或者身份证查询可能犯罪
     *
     * @param certificateType
     * @param certificateNumber
     * @return
     */
    @Query("select c from CrimePersonInfoEntity c where c.certificateType=?1 and c.certificateNumber=?2  and c.isActive='1' order by  c.enteringTime desc ")
    List<CrimePersonInfoEntity> findAllPossibleCrimePersonInfo(String certificateType, String certificateNumber);

    /**
     * get crime person by id
     *
     * @param crimePersonId
     * @return
     */
    CrimePersonInfoEntity getByCrimePersonId(String crimePersonId);

    /**
     * 通过证件号码查询犯罪人信息
     *
     * @param certificateNumber
     * @return
     */
    CrimePersonInfoEntity findByCertificateNumber(String certificateNumber);

    /**
     * 通过姓名查询犯罪人信息
     *
     * @param firstName
     * @param lastName
     * @return
     */
    @Query("select c from CrimePersonInfoEntity c where c.firstName=?1 and c.lastName=?2 and c.sexId=?3 and c.isActive='1' order by  c.enteringTime desc ")
    List<CrimePersonInfoEntity> findAllByFirstNameAndLastNameAndSexId(String firstName, String lastName,String sexId);

    /**
     * 根据犯罪人Id查询犯罪人信息
     *
     * @param crimePersonId
     * @return
     */
    CrimePersonInfoEntity findOneByCrimePersonId(String crimePersonId);

    @Modifying
    @Query("update CrimePersonInfoEntity a set a.isMerging=:im where a.crimePersonId in :ids")
    void updateCrimePersonIsMergingStatus(@Param(value = "im") String im, @Param(value = "ids") List<String> ids);
}
