package com.gs.crms.applycertify.contract.service;

import com.gs.crms.applycertify.contract.model.*;

import com.gs.crms.applycertify.contract.model.admin.ApplyInfoParam;
import com.gs.crms.applycertify.contract.model.admin.CertificateParamModel;
import com.gs.crms.common.model.ReturnBase;

import java.util.List;

/**
 * Created by zhangqiang on 2017/9/28.
 * 管理员查询申请相关服务
 */
public interface AdminCertificateApplyService {

    /**
     * 从申请人姓名，状态（待审，已发等）申请目的（持枪证，结婚证）,申请时间,申请类型 等多个角度去查询
     *
     * @return
     */
    ReturnBase<List<CertificateApplyInfo>> getCertificateApplyInfo(String orgPersonId, ApplyInfoParam applyInfoParam,boolean isPriority);

    /**
     * 管理员修改申请优先级
     *
     * @param applyID
     * @param priority
     * @param modifyPersonName
     * @return
     */
    Boolean updateApplyPriority(String applyID, String priority, String modifyPersonName);

    /**
     * 获取证书信息
     *
     * @param certificateParamModel
     * @return
     */
    ReturnBase<List<CertificateExtendInfo>> getCertificate(String orgPersonId, CertificateParamModel certificateParamModel);

    /**
     * 添加证书
     * (打印前)
     * @param certificateInfo
     * @return
     */
    CertificateInfo addCertificateInfo(CertificateInfo certificateInfo);

    /**
     * 通过申请ID查询证书
     *
     * @return
     */
    CertificateInfo getCertificateByApplyInfoId(String applyInfoId);

    /**
     * 更新证书信息（打印后）
     * @param certificateInfo
     * @return
     */
    boolean updateCertificateInfo(CertificateInfo certificateInfo);
}
