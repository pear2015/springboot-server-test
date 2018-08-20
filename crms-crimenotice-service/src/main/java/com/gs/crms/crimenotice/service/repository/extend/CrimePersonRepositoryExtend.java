package com.gs.crms.crimenotice.service.repository.extend;

import com.gs.crms.crimenotice.service.entity.CrimePersonInfoEntity;

import java.util.List;

/**
 * Created by zhengyali on 2017/11/3.
 */
@FunctionalInterface
public interface CrimePersonRepositoryExtend {
    /**
     * 根据条件
     * @param certificateType
     * @param certificateNumber
     * @param firstName
     * @param lastName
     * @return
     */
    List<CrimePersonInfoEntity> findAllBySearch(String certificateType, String certificateNumber, String firstName, String lastName);
}
