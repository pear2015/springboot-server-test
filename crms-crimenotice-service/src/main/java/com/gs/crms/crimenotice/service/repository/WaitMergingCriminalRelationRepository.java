package com.gs.crms.crimenotice.service.repository;

import com.gs.crms.crimenotice.service.entity.WaitMergingCriminalRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by zhangqiang on 2017/11/11.
 */
@Repository
public interface WaitMergingCriminalRelationRepository extends JpaRepository<WaitMergingCriminalRelationEntity, Long> {

    WaitMergingCriminalRelationEntity findByNoticeId(String noticeId);
   @Modifying
   @Query(value = "delete  from WaitMergingCriminalRelationEntity  A where A.noticeId=?1")
    void deleteByNoticeId(String noticeId);
}
