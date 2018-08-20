package com.gs.crms.statisticsanalysis.service.serviceimpl;

import com.gs.crms.statisticsanalysis.contract.model.ReportCrimeParamModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;
import com.gs.crms.statisticsanalysis.contract.model.TimeModel;
import com.gs.crms.statisticsanalysis.contract.service.CrimeNoticeStatisticsAnalysisService;
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
 * Created by wangzuzhi on 2017/11/1.
 */
@Transactional
@Service
public class CrimeNoticeStatisticsAnalysisServiceImpl implements CrimeNoticeStatisticsAnalysisService {

    @PersistenceContext
    private EntityManager entityManager;
    private ResultToModelUtil resultToModelUtil = new ResultToModelUtil();
    private TimeFormatUtil timeFormatUtil = new TimeFormatUtil();
    @Value("${statisticsanalysis.year}")
    private int year;

    /**
     * 按年 月 日统计 全国各个法院的犯罪公告
     * @param reportParamModel
     * * 按年 月 日 法院 统计
     * @return
     */
    @Override
    public List<ReportRtnModel> getCrimeNoticeStatisticsByDate(ReportCrimeParamModel reportParamModel) {
        TimeModel timeModel = timeFormatUtil.getTimeZoneCrimeExChange(reportParamModel);
        boolean isShowTimeX=false;
        if(StringUtils.isNotEmpty(reportParamModel.getCourtId())){
            isShowTimeX=true;
        }
        return resultToModelUtil.getResultListByDate(entityManager, reportParamModel.getDateType(), StatisticAndAnalysisStorageUtil.CRIME_NOTICE, timeModel,timeFormatUtil.resolveDateFlag(reportParamModel.getDateType()),reportParamModel.getCourtId(),isShowTimeX);

    }
}
