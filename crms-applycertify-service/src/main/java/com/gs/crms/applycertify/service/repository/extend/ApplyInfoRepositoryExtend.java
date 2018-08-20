package com.gs.crms.applycertify.service.repository.extend;

import com.gs.crms.applycertify.service.entity.ApplyBasicInfoEntity;
import com.gs.crms.applycertify.service.entity.ApplyInfoEntity;

import java.util.List;

/**
 * 申请业务JPA
 * Created by zhangqiang on 2017/8/22.
 */
public interface ApplyInfoRepositoryExtend {
    /**
     * 通过姓名或身份证查询申请基本信息:证件优先
     * @param deliveryReceiptNumbr
     * @param certificateNumber
     * @return
     */
    List<ApplyInfoEntity> getApplyBasicInfoSchedule(String deliveryReceiptNumbr,String certificateNumber);

    List<ApplyBasicInfoEntity> getBaseInfoByNameAndSexId(String firstName, String lastName, String sexId);
}
