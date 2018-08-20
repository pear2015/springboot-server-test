package com.gs.crms.statisticsanalysis.service.serviceimpl;

import com.gs.crms.statisticsanalysis.contract.model.ReportCrimeParamModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;
import com.gs.crms.statisticsanalysis.contract.model.TimeModel;
import com.gs.crms.statisticsanalysis.contract.service.CrimeInfoStatisticsAnalysisService;
import com.gs.crms.statisticsanalysis.service.util.ResultToModelUtil;
import com.gs.crms.statisticsanalysis.service.util.StatisticAndAnalysisStorageUtil;
import com.gs.crms.statisticsanalysis.service.util.TimeFormatUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by zhangqiang 2017/11/24
 * 犯罪信息统计
 */
@Transactional
@Service
public class CrimeInfoStatisticsAnalysisServiceImpl implements CrimeInfoStatisticsAnalysisService {

    @PersistenceContext
    private EntityManager entityManager;
    private ResultToModelUtil resultToModelUtil = new ResultToModelUtil();
    private TimeFormatUtil timeFormatUtil = new TimeFormatUtil();

    /**
     * 犯罪区域和犯罪类型
     *
     * @param reportCrimeParamModel
     * @return
     */
    @Override
    public List<ReportRtnModel> getCrimeInfoStatisticsCrimeRegionMain(ReportCrimeParamModel reportCrimeParamModel) {
        String storageFunctionName = StatisticAndAnalysisStorageUtil.CRIME_REGION_TYPE;
        TimeModel timeModel = timeFormatUtil.getTimeZoneCrimeExChange(reportCrimeParamModel);
        return resultToModelUtil.getResultList(entityManager, storageFunctionName, timeModel, reportCrimeParamModel.getCrimeRegionName(), reportCrimeParamModel.getCrimeTypeId());
    }

    /**
     * 犯罪类型和犯罪时间
     *
     * @param reportCrimeParamModel
     * @return
     */
    @Override
    public List<ReportRtnModel> getCrimeInfoStatisticsCrimeTypeMain(ReportCrimeParamModel reportCrimeParamModel) {
        String storageFunctionName = StatisticAndAnalysisStorageUtil.CRIME_TYPE_TIME;
        TimeModel timeModel = timeFormatUtil.getTimeZoneCrimeExChange(reportCrimeParamModel);
        return resultToModelUtil.getResultListByDateAndTwoParam(entityManager, reportCrimeParamModel.getDateType(), storageFunctionName, timeModel.getStartDate(),
                timeModel.getEndDate(), reportCrimeParamModel.getCrimeTypeId(),reportCrimeParamModel.getDateType());
    }

    /**
     * 犯罪区域和犯罪时间
     *
     * @param reportCrimeParamModel
     * @return
     */
    @Override
    public List<ReportRtnModel> getCrimeInfoStatisticsCrimeTimeMain(ReportCrimeParamModel reportCrimeParamModel) {
        // 默认统计当天全国各省犯罪数量
        String storageFunctionName = StatisticAndAnalysisStorageUtil.CRIME_REGION_TIME;
        TimeModel timeModel = timeFormatUtil.getTimeZoneCrimeExChange(reportCrimeParamModel);
        return resultToModelUtil.getResultListByDateAndTwoParam(entityManager, reportCrimeParamModel.getDateType(),
                storageFunctionName, timeModel.getStartDate(), timeModel.getEndDate(),
                reportCrimeParamModel.getCrimeRegionName(),reportCrimeParamModel.getDateType());
    }

}
