package com.gs.crms.crimenotice.service.repository;

import com.gs.crms.crimenotice.service.entity.NoticeMergedAndSplitHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangqiang on 2017/11/12.
 */
@Repository
public interface NoticeMergedAndSplitHistoryRepository extends JpaRepository<NoticeMergedAndSplitHistoryEntity, Long> {
    /**
     * 通过公告Id获取合并拆分历史
     *
     * @param noticeId
     * @return
     */
    List<NoticeMergedAndSplitHistoryEntity> findAllByNoticeId(String noticeId);
    
}
