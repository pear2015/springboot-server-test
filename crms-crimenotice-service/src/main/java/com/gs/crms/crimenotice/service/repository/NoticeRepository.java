package com.gs.crms.crimenotice.service.repository;

import com.gs.crms.crimenotice.service.entity.NoticeEntity;
import com.gs.crms.crimenotice.service.repository.extend.NoticeRepositoryExtend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

/**
 * 公告信息Repository
 * Created by tanjie on 2017/7/10.
 */
@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long>, JpaSpecificationExecutor, NoticeRepositoryExtend {
    /**
     * 根据公告编码进行查重
     *
     * @param noticeNumber
     * @return
     */
    @Query(value = "select count (A) from NoticeEntity A  where A.noticeNumber=?1 and A.status not in ('4','5')")
    int countNoticeByNoticeNumber(String noticeNumber);

    /**
     * get crimeNoticeId
     *
     * @param crimePersonId
     * @return
     */
    NoticeEntity getByCrimePersonId(String crimePersonId);

    /**
     * get notice by noticeID
     *
     * @param noticeId
     * @return
     */
    NoticeEntity getByNoticeId(String noticeId);

    List<NoticeEntity> findAllByCrimePersonId(String crimePersonId);

    NoticeEntity findOneByNoticeId(String noticeId);

    /**
     * 判断是否为当前公告
     *
     * @param noticeNumber
     * @param noticeId
     * @return
     */
    @Query(value = "select count (A) from NoticeEntity A  where A.noticeNumber=?1 and A.noticeId<>?2 and A.status not in ('4','5')")
    int countNoticeByNoticeNumberAndNoticeId(String noticeNumber, String noticeId);

    /**
     * 批量更新公告和罪犯的关联关系
     *
     * @param crimePersonId
     * @param id
     */
    @Modifying
    @Query("update NoticeEntity a set a.crimePersonId=:cid where a.noticeId in :ids")
    void updateNoticeCrimePersonId(@Param(value = "cid") String crimePersonId, @Param(value = "ids") List<String> id);

    /**
     * 统计已完成公告量
     * @param beginTime 开始时间
     * @param endTIme  结束时间
     * @return  统计数
     */
    @Query("select  count(1) from NoticeEntity a  where a.status='3' and a.enteringTime between ?1 and ?2")
    int countFinishNoticeByDate(Date beginTime,Date endTIme);

    /**
     * 统计未完成公告量
     * @param beginTime  开始时间
     * @param endTIme  结束时间
     * @return
     */
    @Query("select  count(1) from NoticeEntity a  where a.status in('1','2') and a.enteringTime between ?1 and ?2")
    int countUnFinishNoticeByDate(Date beginTime,Date endTIme);

    /**
     * 统计已拒绝公告量
     * @param beginTime  开始时间
     * @param endTIme   结束时间
     * @return 统计数
     */
    @Query("select  count(1) from NoticeEntity a  where a.status='4'  and a.enteringTime between ?1 and ?2")
    int countRefuseNoticeByDate(Date beginTime,Date endTIme);
    /**
     * 统计公告量
     * @param beginTime 开始时间
     * @param endTIme  结束时间
     * @return  统计数
     */
    @Query("select  count(1) from NoticeEntity a  where a.enteringTime between ?1 and ?2")
    int countNoticeByDate(Date beginTime,Date endTIme);
}
