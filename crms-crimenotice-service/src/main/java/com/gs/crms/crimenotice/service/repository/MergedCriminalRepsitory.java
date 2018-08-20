package com.gs.crms.crimenotice.service.repository;

import com.gs.crms.crimenotice.service.entity.MergedCriminalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zhangqiang on 2017/11/11.
 */
@Repository
public interface MergedCriminalRepsitory extends JpaRepository<MergedCriminalEntity, Long> {
    /**
     * 通过罪犯ID获取已合并记录
     *
     * @param criminalId
     * @return
     */
    MergedCriminalEntity findByCriminalId(String criminalId);
}
