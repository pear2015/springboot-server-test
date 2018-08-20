package com.gs.crms.applycertify.service.repository.common;

import com.gs.crms.common.enums.CareerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhengyali on 2018/3/6.
 * 职业
 */
@Repository
public interface CareerRepository extends  JpaRepository<CareerEntity,Long> {
    /**
     * 获取所有的学历 默认升序
     * @return
     */
    @Query("select a from CareerEntity a order by  a.careerId asc ")
    List<CareerEntity> findAllByCareerIdAsc();
}
