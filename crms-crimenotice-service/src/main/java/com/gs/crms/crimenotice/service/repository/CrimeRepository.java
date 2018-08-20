package com.gs.crms.crimenotice.service.repository;

import com.gs.crms.crimenotice.service.entity.CrimeEntity;
import com.gs.crms.crimenotice.service.repository.extend.CrimeRepositoryExtend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
 * 犯罪信息Repository
 * Created by tanjie on 2017/7/10.
 */
@Repository
public interface CrimeRepository extends JpaRepository<CrimeEntity,Long> ,CrimeRepositoryExtend {
    /**
     * get crimeId
     * @param crimeId
     * @return
     */
    CrimeEntity getByCrimeId(String crimeId);

    /**
     * 根据身份证号查询犯罪信息
     * @param certificateNumber
     * @return
     */
    @Query(value = "select  c from CrimeEntity  c where c.crimeId in (select crimeId  from NoticeEntity  where crimePersonInfoEntity.certificateType=?1 and  crimePersonInfoEntity.certificateNumber=?2 and isActive='1') and c.isActive='1' order by  c.crimeTime desc ")
    List<CrimeEntity> findAllCrimeByCertificateNumber(String certificateType,String certificateNumber);

    CrimeEntity findOneByCrimeId(String crimeId);
}
