package com.gs.crms.statisticsanalysis.contract.service;

import com.gs.crms.statisticsanalysis.contract.model.ReportParamModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;

import java.util.List;

/**
 * Created by wangzuzhi on 2017/11/16.
 * 系统使用情况统计
 */
@FunctionalInterface
public interface SysUsageStatisticsanalysisService {
    /**
     * 通过时间和站点获取系统使用情况
     *
     * @param reportParamModel the statistics param model
     * @return the sys usage statistic by date
     */
     List<ReportRtnModel> getSysUsageStatisticByDate(ReportParamModel reportParamModel);
}
