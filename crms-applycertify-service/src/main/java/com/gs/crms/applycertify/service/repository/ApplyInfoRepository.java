package com.gs.crms.applycertify.service.repository;

import com.gs.crms.applycertify.service.entity.ApplyInfoEntity;
import com.gs.crms.applycertify.service.repository.extend.ApplyInfoRepositoryExtend;
import com.gsafety.springboot.common.pagerepository.QueryMetaDataRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 申请基本信息Repository
 * Created by zhangqiang on 2017/8/22.
 */
@Repository
public interface ApplyInfoRepository extends CrudRepository<ApplyInfoEntity, Long>,QueryMetaDataRepository<ApplyInfoEntity,Long>,JpaSpecificationExecutor, ApplyInfoRepositoryExtend {
    /**
     * Gets by apply id.
     *
     * @param id the id
     * @return the by apply id
     */
    ApplyInfoEntity getByApplyId(String id);
    /**
     * 通过时间获取一天的办理业务量
     * @param enterName
     * @param sDate
     * @param eDate
     * @return
     */
    @Query(value = "SELECT a FROM ApplyInfoEntity a WHERE a.enteringPersonName=:enterName and a.applyTime BETWEEN :sDate AND :eDate")
    List<ApplyInfoEntity> getAllByEnteringPersonNameAndApplyTime(@Param("enterName") String enterName, @Param("sDate") Date sDate, @Param("eDate") Date eDate);

    /**
     * 统计已完成个人申请量
     * @param beginTime 开始时间
     * @param endTIme  结束时间
     * @return  统计数
     */
    @Query("select count(1) from ApplyInfoEntity a  where a.applyStatusId='4'  and a.applyTypeId='1' and a.applyTime between :beginTime and :endTIme")
    int countFinishPersonApplyByDate(@Param("beginTime")Date beginTime,@Param("endTIme")Date endTIme);

    /**
     * 统计未完成个人申请量
     * @param beginTime  开始时间
     * @param endTIme  结束时间
     * @return
     */
    @Query("select  count(1) from ApplyInfoEntity a  where a.applyStatusId not in ('4','5') and a.applyTypeId='1' and a.applyTime between ?1 and ?2")
    int countUnFinishPersonApplyByDate(Date beginTime,Date endTIme);

    /**
     * 统计已拒绝个人申请量
     * @param beginTime  开始时间
     * @param endTIme   结束时间
     * @return 统计数
     */
    @Query("select  count(1) from ApplyInfoEntity a  where a.applyResultId='3'  and a.applyTypeId='1' and a.applyTime between ?1 and ?2")
    int countRefusePersonApplyByDate(Date beginTime,Date endTIme);

    /**
     * 统计已完成政府申请量
     * @param beginTime 开始时间
     * @param endTIme  结束时间
     * @return  统计数
     */
    @Query("select  count(1) from ApplyInfoEntity a  where a.applyStatusId='4'  and a.applyTypeId='2' and a.applyTime between ?1 and ?2")
    int countFinishGovApplyByDate(Date beginTime,Date endTIme);

    /**
     * 统计未完成政府申请量
     * @param beginTime  开始时间
     * @param endTIme  结束时间
     * @return
     */
    @Query("select  count(1) from ApplyInfoEntity a  where a.applyStatusId not in ('4','5') and a.applyTypeId='2' and a.applyTime between ?1 and ?2")
    int countUnFinishGovApplyByDate(Date beginTime,Date endTIme);

    /**
     * 统计已拒绝政府申请量
     * @param beginTime  开始时间
     * @param endTIme   结束时间
     * @return 统计数
     */
    @Query("select  count(1) from ApplyInfoEntity a  where a.applyStatusId='3'  and a.applyTypeId='2' and a.applyTime between ?1 and ?2")
    int countRefuseGovApplyByDate(Date beginTime,Date endTIme);

    /**
     * 统计已完成个人申请量
     * @param beginTime 开始时间
     * @param endTIme  结束时间
     * @return  统计数
     */
    @Query("select count(1) from ApplyInfoEntity a  where   a.applyTime between :beginTime and :endTIme")
    int countApplyByDate(@Param("beginTime")Date beginTime,@Param("endTIme")Date endTIme);

    /**
     * 统计待分析
     * @return 统计数
     */
    @Query("select count(1) from ApplyInfoEntity a  where a.applyStatusId in ('0','1') and a.enteringPersonId=:enteringPersonId")
    int countUnAnalystApply(@Param("enteringPersonId")String enteringPersonId);
    /**
     * 统计待审核
     * @return 统计数
     */
    @Query("select count(1) from ApplyInfoEntity a  where a.applyStatusId='2' and a.enteringPersonId=:enteringPersonId")
    int countUnAuditorApply(@Param("enteringPersonId")String enteringPersonId);
    /**
     * 统计待打印
     * @return 统计数
     */
    @Query("select count(1) from ApplyInfoEntity a  where a.applyStatusId='3' and a.enteringPersonId=:enteringPersonId")
    int countUnPrintApply(@Param("enteringPersonId")String enteringPersonId);
}

