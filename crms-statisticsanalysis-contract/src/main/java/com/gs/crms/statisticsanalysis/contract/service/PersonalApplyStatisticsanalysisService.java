package com.gs.crms.statisticsanalysis.contract.service;

import com.gs.crms.statisticsanalysis.contract.model.ReportParamModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;

import java.util.List;

/**
 * Created by zhufengjie on 2017/9/19.
 */
public interface PersonalApplyStatisticsanalysisService {
    /**
     * 通过时间获取个人申请统计数，按年按月
     * @param reportParamModel
     * @return
     */
    List<ReportRtnModel> getPersonalApplyStatisticsByTime(ReportParamModel reportParamModel);
    /**
     *  根据职业或办理用途 统计业务数量
     * @param reportParamModel
     * @return
     */
    List<ReportRtnModel> getPersonalApplyStatisticsByProfessionAndPurpose(ReportParamModel reportParamModel);

    /**
     * 根据职业或办理结果 统计业务数量
     * @param reportParamModel
     * @return
     */
    List<ReportRtnModel> getPersonalApplyStatisticsByProfessionAndResult(ReportParamModel reportParamModel);

    /**
     * 根据职业或办理结果 统计业务数量
     * @param reportParamModel
     * @return
     */
    List<ReportRtnModel> getPersonalApplyStatisticsByPurposeAndResult(ReportParamModel reportParamModel);
}
