package com.gs.crms.applycertify.service.repository;

import com.gs.crms.applycertify.service.entity.history.ApplyBussinessHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangqiang on 2017/9/25.
 * 申请历史操作记录Repository
 */
@Repository
public interface ApplyBussinessHistoryRepository extends JpaRepository<ApplyBussinessHistoryEntity, Long> {
    /**
     * 通过申请ID获取该申请的操作历史
     * @return
     */
    List<ApplyBussinessHistoryEntity> findAllByApplyIdOrderByOperatorTimeDesc(String applyId);
}
