package com.gs.crms.applycertify.service.repository.common;

import com.gs.crms.common.enums.MarriageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhengyali on 2018/3/6.
 * 婚姻
 */
@Repository
public interface MarriageRepository extends JpaRepository<MarriageEntity,Long> {
    /**
     * 获取所有的婚姻 默认升序
     * @return
     */
    @Query("select a from MarriageEntity a order by  a.marriageId asc ")
    List<MarriageEntity> findAllByMarriageIdAsc();
}
