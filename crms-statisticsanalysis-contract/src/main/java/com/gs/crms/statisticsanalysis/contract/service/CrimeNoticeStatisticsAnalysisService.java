package com.gs.crms.statisticsanalysis.contract.service;

import com.gs.crms.statisticsanalysis.contract.model.ReportCrimeParamModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;

import java.util.List;

/**
 * Created by wangzuzhi on 2017/10/31.
 * 犯罪公告统计
 */
@FunctionalInterface
public interface CrimeNoticeStatisticsAnalysisService {
    /**
     * 通过时间获取各个法院的公告发布数量
     *
     * @param reportCrimeParamModel the statistics param model
     * @return the crime notice statistics by date
     */
     List<ReportRtnModel> getCrimeNoticeStatisticsByDate(ReportCrimeParamModel reportCrimeParamModel);
}
