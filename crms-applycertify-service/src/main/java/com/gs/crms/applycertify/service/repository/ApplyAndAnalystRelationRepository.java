package com.gs.crms.applycertify.service.repository;

import com.gs.crms.applycertify.service.entity.ApplyAndAnalystRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zhufengjie on 2017/9/4.
 */
public interface ApplyAndAnalystRelationRepository extends JpaRepository<ApplyAndAnalystRelationEntity, Long>, JpaSpecificationExecutor {
    /**
     * get Apply by analystId
     *
     * @param analystId
     * @return
     */
    List<ApplyAndAnalystRelationEntity> findAllByAnalystId(String analystId);
    /**
     * delete by applyInfoId
     *
     * @param applyInfoId
     */
    @Modifying
    @Query(value = "delete  from ApplyAndAnalystRelationEntity A where A.applyInfoId=?1")
    void deleteByApplyInfoId(String applyInfoId);
    /**
     * delete by applyInfoId
     *
     * @param applyInfoId
     */
    @Modifying
    @Query(value = "delete  from ApplyAndAnalystRelationEntity A where A.applyInfoId=?1 and A.analystId=?2")
    void deleteByApplyInfoIdAndAnalystId(String applyInfoId,String analystId);
    /**
     * delete by applyInfoId
     *
     * @param applyInfoId
     */
    @Modifying
    @Query(value = "delete  from ApplyAndAnalystRelationEntity A where A.applyInfoId=?1 and A.auditorId=?2")
    void deleteByApplyInfoIdAndAuditorId(String applyInfoId,String auditorId);
    /**
     * get Apply by applyInfoID
     *
     * @param applyInfoId
     * @return
     */
    List<ApplyAndAnalystRelationEntity> findAllByApplyInfoId(String applyInfoId);

    /**
     * 查询没有新分析任务的分析员
     *
     * @param analystId
     * @param analysisResultFail
     * @return
     */
    @Query("select  A from ApplyAndAnalystRelationEntity A where A.analystId=?1 and A.analysisResultFail=?2  and A.applyInfoId in (select  B.applyId from ApplyInfoEntity B )")
    List<ApplyAndAnalystRelationEntity> findAllByAnalystIdAndAnalysisResultFail(String analystId, String analysisResultFail);

    /**
     * 查询审核员需要审核的申请
     *
     * @return
     */
    List<ApplyAndAnalystRelationEntity> findAllByAuditorId(String auditorId);


    /**
     * 通过申请ID查询申请和人员关联记录
     *
     * @param applyInfoId
     * @return
     */
    ApplyAndAnalystRelationEntity findByApplyInfoId(String applyInfoId);

    /**
     * 通过分析员id获取需要分析的新申请
     *
     * @param analystId
     * @param analysisResultFail
     * @return
     */
    @Query(value = "select A from ApplyAndAnalystRelationEntity A where A.analystId=?1 and A.analysisResultFail=?2 and A.businessType=?3 and A.applyInfoId in (select  B.applyId from ApplyInfoEntity B ) order by A.createTime ASC ")
    List<ApplyAndAnalystRelationEntity> findByAnalystIdAndAnalysisResultFail(String analystId, String analysisResultFail, String businessType);

    /**
     * 更新优先级
     * @param applyId
     * @param priority
     */
    @Modifying
    @Query(value = "update ApplyAndAnalystRelationEntity set priority=?2 where applyInfoId=?1 ")
    void updatePriorityByApplyId(String applyId, String priority);
}
