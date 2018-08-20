package com.gs.crms.applycertify.service.repository;

import com.gs.crms.applycertify.service.entity.dictionary.ApplyPurposeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 申请目的Repository
 * Created by zhangqiang on 2017/8/23.
 */
@Repository
public interface ApplyPurposeRepository extends JpaRepository<ApplyPurposeEntity, Long> {

    /**
     * Gets apply purpose entity list.
     * 获取申请目的列表
     * @return the apply purpose entity list
     */
    @Query("select a from ApplyPurposeEntity a order by  a.applyPurposeId asc ")
    List<ApplyPurposeEntity> getApplyPurposeEntityList();

}
