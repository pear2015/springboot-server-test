package com.gs.crms.applycertify.contract.service;

import com.gs.crms.applycertify.contract.model.ApplyPriority;
import com.gs.crms.applycertify.contract.model.ApplyPurpose;
import com.gs.crms.applycertify.contract.model.CertificateType;
import com.gs.crms.common.enums.ApplyType;
import com.gs.crms.common.model.CareerInfo;
import com.gs.crms.common.model.MarriageInfo;
import com.gs.crms.common.model.SexInfo;

import java.util.List;

/**
 * Created by zhengyali on 2018/3/7.
 * 查询基础数据
 */
public interface CommonApplyService {
    /**
     * 获取申请目的列表
     *
     * @return
     */
    List<ApplyPurpose> getApplyPurposeList();

    /**
     * 获取证件类型
     *
     * @return
     */
    List<CertificateType> getCertificateTypeList();

    /**
     * 获取所有申请优先级
     *
     * @return
     */
    List<ApplyPriority> getAllApplyPriority();

    /**
     * 获取所有职业
     * @return
     */
    List<CareerInfo> getAllCareerInfo();

    /**
     * 获取所有的婚姻状态
     * @return
     */

    List<MarriageInfo> getMarriage();

    /**
     * 获取性别
     * @return
     */
    List<SexInfo> getSexInfo();

    /**
     * 根据申请类型获取指定的证件类型
     * @param applyType
     * @return
     */
    List<CertificateType> getCertificateTypeListByType(ApplyType applyType);
}
