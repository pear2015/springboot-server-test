package com.gs.crms.statisticsanalysis.service.serviceimpl;

import com.gs.crms.statisticsanalysis.contract.model.ReportParamModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;
import com.gs.crms.statisticsanalysis.contract.model.TimeModel;
import com.gs.crms.statisticsanalysis.contract.service.SysUsageStatisticsanalysisService;
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
 * Created by wangzuzhi on 2017/11/16.
 */
@Transactional
@Service
public class SysUsageStatisticsanalysisServiceImpl implements SysUsageStatisticsanalysisService {

    @PersistenceContext
    private EntityManager entityManager;
    private ResultToModelUtil resultToModelUtil = new ResultToModelUtil();
    private TimeFormatUtil timeFormatUtil = new TimeFormatUtil();
    @Value("${statisticsanalysis.year}")
    private int year;

    /**
     * 按照年月日统计系统的使用情况
     * @param reportParamModel the statistics param model
     * @return
     */
    @Override
    public List<ReportRtnModel> getSysUsageStatisticByDate(ReportParamModel reportParamModel) {
        TimeModel timeModel = timeFormatUtil.getTimeZoneReportExChange(reportParamModel);
        return resultToModelUtil.getResultListByDate(entityManager,reportParamModel.getDateType(),StatisticAndAnalysisStorageUtil.PROCE_SYSUSAGE_SPAN,timeModel,timeFormatUtil.resolveDateFlag(reportParamModel.getDateType()),"",false);
    }

}
