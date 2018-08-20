package com.gs.crms.statisticsanalysis.contract.service;

import com.gs.crms.statisticsanalysis.contract.model.ReportParamModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;

import java.util.List;

/**
 * Created by zhufengjie on 2017/9/19.
 */
public interface BussinessDealStatisticsanalysisService {
    /**
     * 通过时间获取业务处理统计数
     *
     * @param reportParamModel the report param model
     * @return buss deal statistics by date
     */
    public List<ReportRtnModel> getBussDealStatisticsByDate(ReportParamModel reportParamModel);

    /**
     * 业务处理统计
     * 按照申请类型和采集点统计
     *
     * @param reportParamModel the report param model
     * @return 申请类型 buss deal statistics by apply type and center code
     */
    List<ReportRtnModel> getBussDealStatisticsByApplyTypeAndCenterCode(ReportParamModel reportParamModel);

    /**
     * 根据申请类型+ 申请目的统计
     *
     * @param reportParamModel the report param model
     * @return buss deal statistics by type and purpose
     */
    List<ReportRtnModel> getBussDealStatisticsByTypeAndPurpose(ReportParamModel reportParamModel);

    /**
     * 根据所在地（采集点）+ 申请目的统计
     *
     * @param reportParamModel the report param model
     * @return buss deal statistics by center code and purpose
     */
    List<ReportRtnModel> getBussDealStatisticsByCenterCodeAndPurpose(ReportParamModel reportParamModel);

    /**
     * 站点下的平均办理时长统计分析
     * 办理结果
     *
     * @param reportParamModel the report param model
     * @return buss deal statistics avg by center id
     */
    List<ReportRtnModel> getBussDealStatisticsAvgByCenterId(ReportParamModel reportParamModel);

    /**
     * 站点下的平均办理时长统计分析
     * 审核
     *
     * @param reportParamModel the report param model
     * @return buss deal statistics by analysis audit id
     */
    List<ReportRtnModel> getBussDealStatisticsByAnalysisAuditId(ReportParamModel reportParamModel);


}
