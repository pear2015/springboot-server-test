package com.gs.crms.crimenotice.contract.service;

import com.gs.crms.common.model.ReturnBase;
import com.gs.crms.crimenotice.contract.model.search.CrimeAndNoticeExtendInfo;
import com.gs.crms.crimenotice.contract.model.CrimeIntegrated.CrimePersonQuery;
import com.gs.crms.crimenotice.contract.model.CrimePersonInfo;

import java.util.List;

/**
 * Created by zhangqiang on 2017/10/17.
 */
public interface CrimeIntegratedService {
    /**
     * 根据人员基本信息查询犯罪信息
     *
     * @param crimePersonQuery
     * @return
     */
    ReturnBase<List<CrimeAndNoticeExtendInfo>> findAllCrimeInfoByPersonalInfo(CrimePersonQuery crimePersonQuery);

    /**
     * 查询犯罪信息列表
     * @param crimePersonQuery
     * @return
     */
    ReturnBase<List<CrimePersonInfo>> findAllCrimePersonInfoBySearch(CrimePersonQuery crimePersonQuery);
}
