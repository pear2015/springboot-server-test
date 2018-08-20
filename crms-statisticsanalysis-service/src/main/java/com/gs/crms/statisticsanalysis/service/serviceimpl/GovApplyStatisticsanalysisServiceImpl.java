package com.gs.crms.statisticsanalysis.service.serviceimpl;
import com.gs.crms.statisticsanalysis.contract.model.ReportParamModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;
import com.gs.crms.statisticsanalysis.contract.model.TimeModel;
import com.gs.crms.statisticsanalysis.contract.service.GovApplyStatisticsanalysisService;
import com.gs.crms.statisticsanalysis.service.util.ResultToModelUtil;
import com.gs.crms.statisticsanalysis.service.util.StatisticAndAnalysisStorageUtil;
import com.gs.crms.statisticsanalysis.service.util.TimeFormatUtil;
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
public class GovApplyStatisticsanalysisServiceImpl implements GovApplyStatisticsanalysisService {
    @PersistenceContext
    private EntityManager entityManager;
    private ResultToModelUtil resultToModelUtil = new ResultToModelUtil();
    private TimeFormatUtil timeFormatUtil = new TimeFormatUtil();
    @Value("${statisticsanalysis.year}")
    private int year;
    //region 1 按照年月日统计
    /**
     * 通过时间获取政府申请统计数
     *
     *
     * @param reportParamModel
     * @return
     */
    @Override
    public List<ReportRtnModel> getGovApplyStatisticsByTime(ReportParamModel reportParamModel) {
        TimeModel timeModel = timeFormatUtil.getTimeZoneReportExChange(reportParamModel);
        return resultToModelUtil.getResultListByDate(entityManager, reportParamModel.getDateType(), StatisticAndAnalysisStorageUtil.APPLY_GOV, timeModel,timeFormatUtil.resolveDateFlag(reportParamModel.getDateType()),"",true);

    }

    //endregion
    //region 2 政府类型+时间

    /**
     * 通过机构获取政府申请统计数
     *
     * @param reportParamModel
     * @return
     */
    @Override
    public List<ReportRtnModel> getGovApplyStatisticsByOrg(ReportParamModel reportParamModel) {
        TimeModel timeModel = timeFormatUtil.getTimeZoneReportExChange(reportParamModel);
        return resultToModelUtil.getResultListByDate(entityManager, reportParamModel.getDateType(),  StatisticAndAnalysisStorageUtil.APPLY_GOV_ORG, timeModel,timeFormatUtil.resolveDateFlag(reportParamModel.getDateType()),reportParamModel.getGovermentInfo(),true);

    }
    //endregion
    //region 3 政府类型+办理用途
    /**
     * 根据
     * 办理用途 为X轴
     *
     * @param reportParamModel
     * @return
     */
    @Override
    public List<ReportRtnModel> getGovApplyStatisticsByOrgAndPurpose(ReportParamModel reportParamModel) {
        TimeModel timeModel = timeFormatUtil.getTimeZoneReportExChange(reportParamModel);
        return resultToModelUtil.getResultList(entityManager, StatisticAndAnalysisStorageUtil.APPLY_GOV_ORG_PURPOSE, timeModel,reportParamModel.getApplyPurposeId(),reportParamModel.getGovermentInfo() );

    }
    //endregion
    //region 4 政府类型+办理结果

    /**
     * 根据 政府名称  办理结果 统计
     *
     *  政府名称 作为X轴
     * @param reportParamModel
     * @return
     */
    @Override
    public List<ReportRtnModel> getGovApplyStatisticsByOrgAndResult(ReportParamModel reportParamModel) {
        TimeModel timeModel = timeFormatUtil.getTimeZoneReportExChange(reportParamModel);
        return resultToModelUtil.getResultList(entityManager, StatisticAndAnalysisStorageUtil.APPLY_GOV_ORG_RESULT, timeModel, reportParamModel.getGovermentInfo(),reportParamModel.getApplyResultId());

    }
   //endregion
    //region 5 办理用途 +办理结果
    /**
     * 办理用途 +办理结果
     * @param reportParamModel
     * @return
     */
    @Override
    public List<ReportRtnModel> getGovApplyStatisticsByPurposeAndResult(ReportParamModel reportParamModel) {
        TimeModel timeModel = timeFormatUtil.getTimeZoneReportExChange(reportParamModel);
        return resultToModelUtil.getResultList(entityManager, StatisticAndAnalysisStorageUtil.APPLY_GOV_PURPOSE_RESULT, timeModel,reportParamModel.getApplyPurposeId(),reportParamModel.getApplyResultId() );
    }
    //endregion
}
