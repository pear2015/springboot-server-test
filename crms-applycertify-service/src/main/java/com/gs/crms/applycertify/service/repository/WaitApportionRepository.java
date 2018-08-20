package com.gs.crms.applycertify.service.repository;


import com.gs.crms.applycertify.service.entity.WaitApportionEntity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tanjie on 2017/8/8.
 */
@Repository
public interface WaitApportionRepository extends CrudRepository<WaitApportionEntity, Long>, PagingAndSortingRepository<WaitApportionEntity, Long> {

    /**
     * @return 所有待分派队列数据
     */
    @Query("select c from WaitApportionEntity c order by c.applyPriority desc, c.createTime asc ")
    List<WaitApportionEntity> getAllWaitApportion();

    /**
     * 通过id集合删除通知
     *
     * @param ids 编号集合
     * @param ids
     * @throws Exception
     */
    @Transactional
    @Modifying
    @Query("delete from WaitApportionEntity c where c.applyInfoId in :ids")
    void deleteByIds(@Param("ids") List<String> ids);

    /**
     * 根据申请ID查询等待分派
     *
     * @param applyId
     * @return
     */
    WaitApportionEntity findByApplyInfoId(String applyId);

    /**
     * 等待分派的原因
     *
     * @param waitReasonType
     * @return
     */
    /**
     * @return 所有待分派队列数据
     */
    @Query("select c from WaitApportionEntity c  where c.waitReasonType=?1 order by c.applyPriority desc, c.createTime asc ")
    List<WaitApportionEntity> findAllByWaitReasonType(String waitReasonType);

    /**
     * 更新公告申请的优先级
     * @param noticeId
     * @param priority
     */
    @Modifying
    @Query(value = "update WaitApportionEntity set applyPriority=?2 where applyInfoId=?1 and businessType=?3")
    void updatePriorityByNoticeId(String noticeId, String priority,String businessType);

    /**
     * 流程启动失败 删除等待
     * @param noticeId
     * @param businessType
     */
    @Modifying
    @Query(value = "delete  from WaitApportionEntity where applyInfoId=?1 and businessType=?2")
    void deleteByApplyInfoId(String noticeId,String businessType );
}
