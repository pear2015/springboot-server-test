package com.gs.crms.crimenotice.contract.service;

import com.gs.crms.common.model.ReturnBase;
import com.gs.crms.crimenotice.contract.model.CrimeInfo;
import com.gs.crms.crimenotice.contract.model.CrimePersonInfo;
import com.gs.crms.crimenotice.contract.model.CrimeRecordPrint;
import com.gs.crms.crimenotice.contract.model.NoticeInfo;
import com.gs.crms.crimenotice.contract.model.search.CrimePersonSearchInfo;
import com.gs.crms.crimenotice.contract.model.search.CrimeSearchInfo;

import java.util.List;

/**
 * Created by zhengyali on 2017/10/31.
 */
public interface CrimeSearchService {
    /**
     * 犯罪公告人员回填
     *
     * @param crimePersonSearchInfo
     * @return
     */
    ReturnBase<List<CrimePersonInfo>> getCrimePersonInfoBySearch(CrimePersonSearchInfo crimePersonSearchInfo,String backFillCriminalId);

    /**
     * 查询公告信息列表
     *
     * @param crimeSearchInfo
     * @return
     */
    ReturnBase<List<NoticeInfo>> findNoticeListByHistory(CrimeSearchInfo crimeSearchInfo);

    /**
     * 审核员获取待审核列表
     *
     * @param pages
     * @param pageSize
     * @param auditorId
     * @return
     */
    ReturnBase<List<NoticeInfo>> getAuditorTaskList(int pages, int pageSize, String auditorId, String businessType);

    /**
     * 查询犯罪嫌疑人
     *
     * @param certificateType
     * @param certificateNumber
     * @param firstName
     * @param lastName
     * @return
     */
    List<CrimePersonInfo> findPersonalCrimeInfoList(String certificateType, String certificateNumber, String firstName, String lastName);

    /**
     * 归档员待分析列表
     *
     * @param pages
     * @param pageSize
     * @param filterId
     * @param businessType
     * @return
     */
    ReturnBase<List<NoticeInfo>> getAnalystTaskList(int pages, int pageSize, String filterId, String businessType);

    /**
     * 获取已审核的公告
     *
     * @param crimeSearchInfo
     * @return
     */
    ReturnBase<List<NoticeInfo>> getHasAuditorTaskList(CrimeSearchInfo crimeSearchInfo);

    /**
     * 查询归档员待审核的公告
     *
     * @param crimeSearchInfo
     * @return
     */
    ReturnBase<List<NoticeInfo>> findNoticeListByWaitAudit(CrimeSearchInfo crimeSearchInfo);

    /**
     * 查询可编辑的优先级列表
     *
     * @param crimeSearchInfo
     * @return
     */
    ReturnBase<List<NoticeInfo>> findNoticeListByPriority(CrimeSearchInfo crimeSearchInfo);

    /**
     * 根据 犯罪嫌疑人查询犯罪人和公告信息
     *
     * @return
     */
    List<CrimeRecordPrint> findCriminalAndNoticeByCrimePersonId(String crimePersonId);
    /**
     * 根据犯罪人Id查询犯罪人信息 （申请业务调用）
     */
    CrimePersonInfo findOneByCrimeId(String crimeId);

    /**
     * 外部接口通过身份证号查询
     * @param certificateNumber
     * @return
     */
    List<CrimePersonInfo> getApplyPersonInfoByCerNumber(String  certificateNumber);

    /**
     * 根据身份证号查询犯罪信息列表
     * @param certificateNumber
     * @return
     */
    List<CrimeInfo> getCrimeInfoByCerNumber(String certificateNumber);

    /**
     * 根据犯罪信息Id查询犯罪信息
     * @param crimeId
     * @return
     */
    CrimeInfo getCrimeInfoDetailByCrimeId(String crimeId);

    /**
     * 根据身份证号或姓名查询人口库信息或犯罪库数据
     * @param crimePersonSearchInfo
     * @return
     */
    List<CrimePersonInfo> getCrimePersonInfoByCertNumberOrName(CrimePersonSearchInfo crimePersonSearchInfo);

    /**
     * 根据犯罪人Id 获取犯罪人
     * @param crimePersonId
     * @return
     */
    CrimePersonInfo getCriminalByCrimePersonId(String crimePersonId);
}
