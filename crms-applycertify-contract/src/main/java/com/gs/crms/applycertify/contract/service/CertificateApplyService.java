package com.gs.crms.applycertify.contract.service;

import com.gs.crms.applycertify.contract.model.*;
import com.gs.crms.applycertify.contract.model.admin.ApplyInfoParam;
import com.gs.crms.applycertify.contract.model.search.ApplyPersonQuery;
import com.gs.crms.common.model.ReturnBase;
import com.gs.crms.crimenotice.contract.model.CrimeRecordPrint;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangqiang on 2017/8/22.
 */
public interface CertificateApplyService {
    /**
     * 保存申请基本信息
     *
     * @param certificateApplyInfo
     * @return
     */
    ApplyInfo saveApplyInfo(CertificateApplyInfo certificateApplyInfo);

    /**
     * 修改申请的基本信息,公告关联
     *
     * @param certificateApplyInfo
     * @return
     */
    Map<String,Object> submitAnalysisResult(CertificateApplyInfo certificateApplyInfo);

    /**
     * 根据姓名和身份证ID模糊匹配人口库中的人员基本信息
     *
     * @param applyPersonQuery
     * @return
     */
    ReturnBase<List<ApplyBasicInfo>> getApplyPersonInfo(ApplyPersonQuery applyPersonQuery);
    /**
     * 获取申请信息
     *
     * @param id
     * @return
     */
    CertificateApplyInfo getApplyInfo(String id);

    /**
     * 通过分析员ID获取分析任务
     *
     * @param analystId
     * @return
     */
    CertificateApplyInfo getAnalysisTaskByAnalystId(String analystId);

    /**
     * 审核席位更新申请信息
     *
     * @param certificateApplyInfo
     * @return
     */
    Map<String, Object>  applyAuditSubmit(CertificateApplyInfo certificateApplyInfo);

    /**
     * 查询用户当天申请业务办理量
     *
     * @param userId
     * @return
     */
    ReturnBase<List<ApplyInfo>> getPersonalDayHandleApplication(String userId, int pages, int pageSize);

    /**
     * 查询分析员需要重新分析的申请
     *
     * @param pages
     * @param pageSize
     * @param analystId
     * @return
     */
    ReturnBase<List<ApplyInfo>> getPersonalApplicationTask(int pages, int pageSize, String analystId, String auditorId);

    /**
     * 根据申请id查询历史操作记录
     *
     * @param id
     * @return
     */
    List<ApplyBussinessHistory> getApplyOperatorHistoryById(String id);



    /**
     * 查询用户未完成的申请
     *
     * @param
     * @return
     */
    ReturnBase<List<ApplyInfo>> getUnCompleteApplicationByUserId(String userId, int pages, int pageSize,String applyStatus);

    /**
     * 查询审核员已审核的申请
     *
     * @param applyInfoParam
     * @return
     */
    ReturnBase<List<ApplyInfo>> getPersonalApplicationHasTask(ApplyInfoParam applyInfoParam);

    /**
     * 根据申请id获取公告信息
     *
     * @param applyId
     * @return
     */
    List<CrimeRecordPrint> findNoticeByApplyId(String applyId);

    /**
     * 根据公民身份证号精确查询公民基本信息
     * @param certificateNumber
     * @return
     */
    List<ApplyBasicInfo> getCitizenInfoByID(String certificateNumber);

    /**
     * 根据姓名模糊查询公民基本信息
     * @param firstName
     * @param lastName
     * @return
     */
    List<ApplyBasicInfo> getCitizenInfoByFullName(String firstName, String lastName, String sex);

    /**
     * 根据证件编号和回执单号查询申请办理进度
     * @param deliveryReceiptNumbr
     * @param certificateNumber
     * @return
     */
    List<Map<String, Object>> getPersonalApplicationListBySearch(String deliveryReceiptNumbr, String certificateNumber);

    /**
     * 申请业务 人员回填修改
     * date： 2017-12-26 16:27
     *
     * @param applyPersonQuery
     * @return
     */
    List<ApplyBasicInfo> getApplyPersonInfoByQuery(ApplyPersonQuery applyPersonQuery);

    /**
     * 可编辑优先级查询
     * @param applyInfoParam
     * @param pageRequest
     * @param orgPersonId
     * @param isPriority
     * @return
     */
    ReturnBase<List<ApplyInfo>> getApplyInfoPage(ApplyInfoParam applyInfoParam, PageRequest pageRequest, String orgPersonId, boolean isPriority);

    /**
     * 保存操作历史
     * @param applyBussinessHistory
     * @return
     */
    boolean saveApplyBussinessOperatorHistory(ApplyBussinessHistory applyBussinessHistory);

    /**
     *获取分析员或者审核员分派关联列表
     * @param pages
     * @param pageSize
     * @param analystId
     * @param auditorId
     * @param businessType
     * @return
     */
    ReturnBase<List<ApplyAndAnalystRelation>> findReplyAnalysisApplicationCommon(int pages, int pageSize, String analystId, String auditorId, String businessType);
}
