package com.gs.crms.statisticsanalysis.contract.service;

import com.gs.crms.statisticsanalysis.contract.model.ReportApplyModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportBussModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportReturnModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;

import java.util.List;

/**
 * Created by chenwei on 2018/1/3.
 */
public interface BussinessCollectStatisticsAnalysisService {
    /**
     * 获取本年业务量（个人申请，政府申请，公告录入）
     *
     * @return buss for year
     */
    ReportBussModel getBussForYear();

    /**
     * 获取本月业务量
     *
     * @return the buss ford month
     */
    ReportBussModel getBussFordMonth();

    /**
     * 获取本周业务量
     *
     * @return the buss ford week
     */
    ReportBussModel getBussFordWeek();

    /**
     * 获取当天业务量（个人申请，政府申请， 公告录入）
     *
     * @return buss for day
     */
    ReportBussModel getBussForDay();

    /**
     * 本周、本月、本年的公告数据统计
     * @return
     */
    ReportReturnModel getBussFoNotice();

    /**
     * 今日、今年的申请数据统计
     * @return
     */
    ReportReturnModel getBussForApply();

    /**
     * 获取最近10天的个人申请的数据
     * @return
     */
    List<ReportRtnModel> getBussForRecentApply();

    /**
     * 本周的申请业务量
     * @return
     */
    List<ReportRtnModel> getBussCollectForApplyWeek();

    /**
     * 本月的申请业务量
      * @return
     */

    List<ReportRtnModel> getBussCollectForApplyMonth();

    /**
     * 本年的业务申请量
     * @return
     */
    List<ReportRtnModel> getBussCollectForApplyYear();
    /**
     * 获取未完成业务列表的 未分析的(已提交+待分析) 审核中 待打印
     * @return
     */
    ReportApplyModel getBussDealStatistics(String enteringPersonId);
}
