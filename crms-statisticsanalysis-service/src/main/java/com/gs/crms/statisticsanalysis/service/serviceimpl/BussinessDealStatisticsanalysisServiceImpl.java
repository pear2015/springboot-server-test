package com.gs.crms.statisticsanalysis.service.serviceimpl;

import com.gs.crms.statisticsanalysis.contract.model.ReportParamModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;
import com.gs.crms.statisticsanalysis.contract.model.TimeModel;
import com.gs.crms.statisticsanalysis.contract.service.BussinessDealStatisticsanalysisService;
import com.gs.crms.statisticsanalysis.service.util.ResultToModelUtil;
import com.gs.crms.statisticsanalysis.service.util.StatisticAndAnalysisStorageUtil;
import com.gs.crms.statisticsanalysis.service.util.TimeFormatUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by zhufengjie on 2017/9/19.
 */
@Transactional
@Service
public class BussinessDealStatisticsanalysisServiceImpl implements BussinessDealStatisticsanalysisService {
    @PersistenceContext
    private EntityManager entityManager;
    private ResultToModelUtil resultToModelUtil = new ResultToModelUtil();
    private TimeFormatUtil timeFormatUtil = new TimeFormatUtil();
    @Value("${statisticsanalysis.year}")
    private int year;
    @Value("${statisticsanalysis.auditList}")
    private String  auditList;
    @Value("${statisticsanalysis.typeList}")
    private String  typeList;
    @Value("${statisticsanalysis.resultList}")
    private String  resultList;
    //region 1 按照年月日统计

    /**
     * 通过时间获取业务办理统计数
     * * 按年份来统计 X Y 轴都不显示时间
     * @param reportParamModel
     * @return
     */
    @Override
    public List<ReportRtnModel> getBussDealStatisticsByDate(ReportParamModel reportParamModel) {
        TimeModel timeModel = timeFormatUtil.getTimeZoneReportExChange(reportParamModel);
        return resultToModelUtil.getResultListNoShowDate(entityManager, StatisticAndAnalysisStorageUtil.APPLY_ALL, timeModel, timeFormatUtil.resolveDateFlag(reportParamModel.getDateType()),reportParamModel.getCenterCode() );
    }

    //endregion
    //region 2 按照申请类型和采集点统计

    /**
     * 按照申请类型+所在地（采集点）统计
     *
     * @param reportParamModel
     * @return
     */
    @Override
    public List<ReportRtnModel> getBussDealStatisticsByApplyTypeAndCenterCode(ReportParamModel reportParamModel) {
        TimeModel timeModel = timeFormatUtil.getTimeZoneReportExChange(reportParamModel);
        return resultToModelUtil.getResultList(entityManager, StatisticAndAnalysisStorageUtil.APPLY_ALL_TYPE_CENTER, timeModel, reportParamModel.getApplyTypeId(),reportParamModel.getCenterCode());

    }
    //endregion

    //region 3 按照申请类型和申请目的统计

    /**
     * 按照申请类型+申请目的 统计
     *
     * @param reportParamModel
     * @return
     */
    @Override
    public List<ReportRtnModel> getBussDealStatisticsByTypeAndPurpose(ReportParamModel reportParamModel) {
        TimeModel timeModel = timeFormatUtil.getTimeZoneReportExChange(reportParamModel);
        return resultToModelUtil.getResultList(entityManager, StatisticAndAnalysisStorageUtil.APPLY_ALL_TYPE_PURPOSE, timeModel, reportParamModel.getApplyTypeId(),reportParamModel.getApplyPurposeId());
    }

    //endregion

    //region 4 按照所在地（采集点）和申请目的统计

    /**
     * 所在地（采集点）+申请目的 统计
     *
     * @param reportParamModel
     * @return
     */
    @Override
    public List<ReportRtnModel> getBussDealStatisticsByCenterCodeAndPurpose(ReportParamModel reportParamModel) {
        TimeModel timeModel = timeFormatUtil.getTimeZoneReportExChange(reportParamModel);
        return resultToModelUtil.getResultList(entityManager, StatisticAndAnalysisStorageUtil.APPLY_ALL_CENTER_PURPOSE, timeModel,reportParamModel.getCenterCode(),reportParamModel.getApplyPurposeId());
    }


    //endregion
    //region 5 站点下的平均办理时长段 统计

    /**
     * 站点下的平均办理时长
     * 办理结果
     *
     * @param reportParamModel
     * @return
     */
    @Override
    public List<ReportRtnModel> getBussDealStatisticsAvgByCenterId(ReportParamModel reportParamModel) {
        TimeModel timeModel = timeFormatUtil.getTimeZoneReportExChange(reportParamModel);
        if (StringUtils.isEmpty(reportParamModel.getApplyTypeId())){
            reportParamModel.setApplyTypeId(typeList);
        }
        if(StringUtils.isEmpty(reportParamModel.getApplyResultId())){
            reportParamModel.setApplyResultId(resultList);
        }
        return resultToModelUtil.getAnalystResultList(entityManager,StatisticAndAnalysisStorageUtil.APPLTY_RESULT_AVG_TIME, timeModel, reportParamModel.getApplyResultId(),reportParamModel.getApplyTypeId(),reportParamModel.getStartMinute(),reportParamModel.getEndMinute());
    }

    /**
     * 站点下的平均办理时长
     *  审核结果
     * @param reportParamModel
     * @return
     */
    @Override
    public List<ReportRtnModel> getBussDealStatisticsByAnalysisAuditId(ReportParamModel reportParamModel) {
        TimeModel timeModel = timeFormatUtil.getTimeZoneReportExChange(reportParamModel);
        if (StringUtils.isEmpty(reportParamModel.getApplyTypeId())){
            reportParamModel.setApplyTypeId(typeList);
        }
        return resultToModelUtil.getAnalystResultList(entityManager,StatisticAndAnalysisStorageUtil.APPLTY_PASS_AVG_TIME, timeModel, reportParamModel.getAuditResultId(),reportParamModel.getApplyTypeId(),reportParamModel.getStartMinute(),reportParamModel.getEndMinute());
    }

//endregion
}
