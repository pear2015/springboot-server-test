package com.gs.crms.statisticsanalysis.contract.service;

import com.gs.crms.statisticsanalysis.contract.model.ReportCrimeParamModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;

import java.util.List;

/**
 * The interface Crime info statistics analysis service.
 */
public interface CrimeInfoStatisticsAnalysisService {

    /**
     * 以犯罪区域为主的犯罪信息统计
     *
     * @param reportCrimeParamModel
     * @return
     */
    List<ReportRtnModel> getCrimeInfoStatisticsCrimeRegionMain(ReportCrimeParamModel reportCrimeParamModel);

    /**
     * 以犯罪类型为主的犯罪信息统计
     *
     * @param reportCrimeParamModel
     * @return
     */
    List<ReportRtnModel> getCrimeInfoStatisticsCrimeTypeMain(ReportCrimeParamModel reportCrimeParamModel);

    /**
     * 以犯罪时间为主的犯罪信息统计
     *
     * @param reportCrimeParamModel
     * @return
     */
    List<ReportRtnModel> getCrimeInfoStatisticsCrimeTimeMain(ReportCrimeParamModel reportCrimeParamModel);
}
