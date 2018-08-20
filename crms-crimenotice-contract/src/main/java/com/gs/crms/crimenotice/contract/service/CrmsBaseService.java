package com.gs.crms.crimenotice.contract.service;

import com.gs.crms.crimenotice.contract.model.CourtInfo;
import com.gs.crms.crimenotice.contract.model.CrimeTypeInfo;

import java.util.List;

/**
 * Created by zhengyali on 2017/10/30.
 */
public interface CrmsBaseService {
    /**
     * 获取法院信息
     *
     * @return
     */
    List<CourtInfo> getCourtList();

    /**
     * 犯罪类型列表
     * @return
     */
    List<CrimeTypeInfo> getCrimeTypeInfoList();

}
