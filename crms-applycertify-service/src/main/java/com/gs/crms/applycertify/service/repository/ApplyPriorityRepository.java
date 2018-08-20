package com.gs.crms.applycertify.service.repository;

import com.gs.crms.applycertify.service.entity.dictionary.ApplyPriorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangqiang on 2017/10/12.
 */
@Repository
public interface ApplyPriorityRepository extends JpaRepository<ApplyPriorityEntity, Long> {
    /**
     * 获取申请优先级列表
     * @return
     */
    @Query("select a from ApplyPriorityEntity a order by  a.applyPriorityId ASC ")
    List<ApplyPriorityEntity> getApplyPriorityEntityList();
}
