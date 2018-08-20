package com.gs.crms.statisticsanalysis.service.serviceimpl;

import com.gs.crms.statisticsanalysis.contract.model.ReportParamModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;
import com.gs.crms.statisticsanalysis.contract.model.TimeModel;
import com.gs.crms.statisticsanalysis.contract.service.PersonalApplyStatisticsanalysisService;
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
public class PersonalApplyStatisticsanalysisServiceImpl implements PersonalApplyStatisticsanalysisService {
    @PersistenceContext
    private EntityManager entityManager;
    private ResultToModelUtil resultToModelUtil = new ResultToModelUtil();
    private TimeFormatUtil timeFormatUtil = new TimeFormatUtil();
    @Value("${statisticsanalysis.year}")
    private int year;
    //region 1 按照年月日统计
    /**
     * 通过时间获取个人申请统计数
     *
     * @param reportParamModel
     * @return
     */
    @Override
    public List<ReportRtnModel> getPersonalApplyStatisticsByTime(ReportParamModel reportParamModel) {
        TimeModel timeModel = timeFormatUtil.getTimeZoneReportExChange(reportParamModel);
        return resultToModelUtil.getResultListByDate(entityManager, reportParamModel.getDateType(),StatisticAndAnalysisStorageUtil.APPLY_PERSON, timeModel,timeFormatUtil.resolveDateFlag(reportParamModel.getDateType()), reportParamModel.getCenterCode(),false);

    }

    //endregion
    //region 2 根据职业或办理用途 统计
    /**
     * 根据职业或办理用途 统计业务数量
     * 按 年份/月份/天或自定义时间
     * @param reportParamModel
     * @return
     */
    @Override
    public List<ReportRtnModel> getPersonalApplyStatisticsByProfessionAndPurpose(ReportParamModel reportParamModel) {
        TimeModel timeModel = timeFormatUtil.getTimeZoneReportExChange(reportParamModel);
        return resultToModelUtil.getResultList(entityManager, StatisticAndAnalysisStorageUtil.APPLY_PROFESSION_PURPOSE, timeModel, reportParamModel.getProfession(),reportParamModel.getApplyPurposeId());

    }
//endregion
    //region 3 按照职业和办理结果统计
    /**
     * 按照 职业和办理结果统计
     * @param reportParamModel
     * @return
     */
    @Override
    public List<ReportRtnModel> getPersonalApplyStatisticsByProfessionAndResult(ReportParamModel reportParamModel) {
        TimeModel timeModel = timeFormatUtil.getTimeZoneReportExChange(reportParamModel);
        return resultToModelUtil.getResultList(entityManager, StatisticAndAnalysisStorageUtil.APPLY_PROFESSION_RESULT, timeModel, reportParamModel.getProfession(),reportParamModel.getApplyResultId());

    }
    //endregion
    //region 4 按照办理用途和办理结果统计
    /**
     * 根据办理用途和办理结果 统计业务数量
     * @param reportParamModel
     * @return
     */
    @Override
    public List<ReportRtnModel> getPersonalApplyStatisticsByPurposeAndResult(ReportParamModel reportParamModel) {
        TimeModel timeModel = timeFormatUtil.getTimeZoneReportExChange(reportParamModel);
        return resultToModelUtil.getResultList(entityManager, StatisticAndAnalysisStorageUtil.APPLY_PURPOSE_RESULT, timeModel, reportParamModel.getApplyPurposeId(),reportParamModel.getApplyResultId());

    }
//endregion
}
