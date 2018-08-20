package com.gs.crms.crimenotice.service.repository.extend;

import com.gs.crms.crimenotice.service.entity.NoticeEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by zhengyali on 2017/11/9.
 */
@FunctionalInterface
public interface NoticeRepositoryExtend {
    List<NoticeEntity> findAllByCrimePersonIdAndTime(String crimePersonId, Date startDate, Date endDate);
}
