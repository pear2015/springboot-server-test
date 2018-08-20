package com.gs.crms.applycertify.service.repository.common;
import com.gs.crms.common.enums.SexEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhengyali on 2018/3/7.
 */
@Repository
public interface SexRepository extends JpaRepository<SexEntity,Long> {
    /**
     * 获取所有的婚姻 默认升序
     * @return
     */
    @Query("select a from SexEntity a order by  a.sexId asc ")
    List<SexEntity> findAllBySexIdAsc();
}
