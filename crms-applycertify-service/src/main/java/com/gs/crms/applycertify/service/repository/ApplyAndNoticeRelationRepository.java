package com.gs.crms.applycertify.service.repository;

import com.gs.crms.applycertify.service.entity.ApplyAndCriminalRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 申请和公告关联Repository
 * Created by zhangqiang on 2017/8/22.
 */
@Repository
public interface ApplyAndNoticeRelationRepository extends JpaRepository<ApplyAndCriminalRelationEntity, Long> {
    /**
     * get NoticeId by applyId
     *
     * @param applyId
     * @return
     */
    ApplyAndCriminalRelationEntity getByApplyInfoId(String applyId);

    /**
     * get NoticeId by applyId
     *
     * @param applyId
     * @return
     */
    List<ApplyAndCriminalRelationEntity> getAllByApplyInfoId(String applyId);

    /**
     * 根据申请Id 删除犯罪关联关系
     * @param applyInfoId
     */
    @Modifying
    @Query(value = "delete  from ApplyAndCriminalRelationEntity where applyInfoId =?1")
    void deleteApplyAndCriminalRelationByapplyInfoId(String applyInfoId);
}
