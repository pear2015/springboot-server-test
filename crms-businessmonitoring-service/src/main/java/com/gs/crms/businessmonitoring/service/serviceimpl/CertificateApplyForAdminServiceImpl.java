package com.gs.crms.businessmonitoring.service.serviceimpl;

import com.gs.crms.applycertify.contract.model.CertificateApplyInfo;
import com.gs.crms.applycertify.contract.model.CertificateExtendInfo;
import com.gs.crms.applycertify.contract.model.CertificateInfo;

import com.gs.crms.applycertify.contract.model.admin.ApplyInfoParam;
import com.gs.crms.applycertify.contract.model.admin.CertificateParamModel;
import com.gs.crms.applycertify.contract.service.AdminCertificateApplyService;

import com.gs.crms.businessmonitoring.contract.service.CertificateApplyForAdminService;
import com.gs.crms.common.model.ReturnBase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhangqiang on 2017/9/28.
 * 管理员查询申请相关服务实现
 */
@Service
public class CertificateApplyForAdminServiceImpl implements CertificateApplyForAdminService {
    @Autowired
    private AdminCertificateApplyService adminCertificateApplyService;
    Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 查询申请：分页
     *
     * @param applyInfoParam
     * @return
     */
    @Override
    public ReturnBase<List<CertificateApplyInfo>> getCertificateApplyInfo(String orgPersonId, ApplyInfoParam applyInfoParam,boolean isPriority) {
        return adminCertificateApplyService.getCertificateApplyInfo(orgPersonId, applyInfoParam,isPriority);
    }

    /**
     * 管理员修改申请优先级
     *
     * @param applyID
     * @param priority
     * @param modifyPersonName
     * @return
     */
    @Override
    public Boolean updateApplyPriority(String applyID, String priority, String modifyPersonName) {
        return adminCertificateApplyService.updateApplyPriority(applyID, priority, modifyPersonName);
    }

    /**
     * 获取证书信息:分页
     *
     * @param certificateParamModel
     * @return
     */
    @Override
    public ReturnBase<List<CertificateExtendInfo>> getCertificate(String orgPersonId, CertificateParamModel certificateParamModel) {
        return adminCertificateApplyService.getCertificate(orgPersonId, certificateParamModel);
    }

    /**
     * 添加证书
     *
     * @param certificateInfo
     * @return
     */
    @Override
    public CertificateInfo addCertificateInfo(CertificateInfo certificateInfo) {
        return adminCertificateApplyService.addCertificateInfo(certificateInfo);
    }

    /**
     * 通过申请ID获取证书
     *
     * @param applyInfoId
     * @return
     */
    @Override
    public CertificateInfo getCertificateByApplyInfoId(String applyInfoId) {
        return adminCertificateApplyService.getCertificateByApplyInfoId(applyInfoId);
    }

    /**
     * 更新证书信息（打印后）
     *
     * @param certificateInfo
     * @return
     */
    @Override
    public boolean updateCertificateInfo(CertificateInfo certificateInfo) {
        return adminCertificateApplyService.updateCertificateInfo(certificateInfo);
    }
}
