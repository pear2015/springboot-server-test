package com.gs.crms.statisticsanalysis.contract.service;

import com.gs.crms.statisticsanalysis.contract.model.ReportParamModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;

import java.util.List;

/**
 * Created by zhufengjie on 2017/9/19.
 */
public interface GovApplyStatisticsanalysisService {
    /**
     * 通过时间获取政府申请统计数
     * @param reportParamModel
     * @return
     */
   public List<ReportRtnModel> getGovApplyStatisticsByTime(ReportParamModel reportParamModel);

    /**
     * 通过申请目的获取政府申请统计数
     * @param reportParamModel
     * @return
     */
   public List<ReportRtnModel> getGovApplyStatisticsByOrg(ReportParamModel reportParamModel);


    /**
     * 按照政府类型和办理用途统计
     * @param reportParamModel
     * @return
     */
    List<ReportRtnModel> getGovApplyStatisticsByOrgAndPurpose(ReportParamModel reportParamModel);
    /**
     * 按照政府类型和办理结果统计
     * @param reportParamModel
     * @return
     */
    List<ReportRtnModel> getGovApplyStatisticsByOrgAndResult(ReportParamModel reportParamModel);

    /**
     * 按照办理用途和办理结果
     * @param reportParamModel
     * @return
     */
    List<ReportRtnModel> getGovApplyStatisticsByPurposeAndResult(ReportParamModel reportParamModel);
}
