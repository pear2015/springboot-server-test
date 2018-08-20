package com.gs.crms.statisticsanalysis.service.serviceimpl;

import com.gs.crms.applycertify.service.repository.ApplyInfoRepository;
import com.gs.crms.common.utils.TimeUtil;
import com.gs.crms.crimenotice.service.repository.NoticeRepository;
import com.gs.crms.statisticsanalysis.contract.model.*;
import com.gs.crms.statisticsanalysis.contract.service.BussinessCollectStatisticsAnalysisService;
import com.gs.crms.statisticsanalysis.service.util.ResultToModelUtil;
import com.gs.crms.statisticsanalysis.service.util.StatisticAndAnalysisStorageUtil;
import com.gs.crms.statisticsanalysis.service.util.TimeFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by chenwei on 2018/1/3.
 */
@Transactional
@Service
public class BussinessCollectStatisticsAnalysisServiceImpl implements BussinessCollectStatisticsAnalysisService {
    @Autowired
    private ApplyInfoRepository applyInfoRepository;
    @Autowired
    private NoticeRepository noticeRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private ResultToModelUtil resultToModelUtil = new ResultToModelUtil();
    private TimeFormatUtil timeFormatUtil = new TimeFormatUtil();
    @Value("${search.lastDays}")
    private int lastDays;//搜索年限
    /**
     * 获取本年业务量（个人申请，政府申请，公告录入）
     *
     * @return buss for year
     */
    @Override
    public ReportBussModel getBussForYear() {
        TimeModel timeModel = timeFormatUtil.getTimeZoneByDateType("0");
        return getStatisticsData(timeModel);
    }

    /**
     * 获取本月业务量
     *
     * @return the buss ford month
     */
    @Override
    public ReportBussModel getBussFordMonth() {
        TimeModel timeModel = timeFormatUtil.getTimeZoneByDateType("1");
        return getStatisticsData(timeModel);
    }

    /**
     * 获取本周业务量
     *
     * @return the buss ford week
     */
    @Override
    public ReportBussModel getBussFordWeek() {
        TimeModel timeModel = timeFormatUtil.getTimeZoneByDateType("6");
        return getStatisticsData(timeModel);
    }

    /**
     * 获取当天业务量（个人申请，政府申请， 公告录入）
     *
     * @return buss for day
     */
    @Override
    public ReportBussModel getBussForDay() {
        TimeModel timeModel = timeFormatUtil.getTimeZoneByDateType("2");
        return getStatisticsData(timeModel);
    }


    /**
     * 获取统计数据
     *
     * @param timeModel
     * @return
     */
    private ReportBussModel getStatisticsData(TimeModel timeModel) {
        ReportBussModel bussModel = new ReportBussModel();
        if (timeModel != null) {
            // 个人申请
            int personApplyFinished = applyInfoRepository.countFinishPersonApplyByDate(timeModel.getStartDate(), timeModel.getEndDate());
            int personApplyUnFinished = applyInfoRepository.countUnFinishPersonApplyByDate(timeModel.getStartDate(), timeModel.getEndDate());
            int personApplyRefused = applyInfoRepository.countRefusePersonApplyByDate(timeModel.getStartDate(), timeModel.getEndDate());
            //政府申请
            int govApplyFinished = applyInfoRepository.countFinishGovApplyByDate(timeModel.getStartDate(), timeModel.getEndDate());
            int govApplyUnFinished = applyInfoRepository.countUnFinishGovApplyByDate(timeModel.getStartDate(), timeModel.getEndDate());
            int govApplyRefused = applyInfoRepository.countRefuseGovApplyByDate(timeModel.getStartDate(), timeModel.getEndDate());
            //犯罪公告
            int noticeFinished = noticeRepository.countFinishNoticeByDate(timeModel.getStartDate(), timeModel.getEndDate());
            int noticeUnFinished = noticeRepository.countUnFinishNoticeByDate(timeModel.getStartDate(), timeModel.getEndDate());
            int noticeRefused = noticeRepository.countRefuseNoticeByDate(timeModel.getStartDate(), timeModel.getEndDate());

            bussModel.setFinished(new int[]{personApplyFinished, govApplyFinished, noticeFinished});
            bussModel.setUnFinished(new int[]{personApplyUnFinished, govApplyUnFinished, noticeUnFinished});
            bussModel.setRefused(new int[]{personApplyRefused, govApplyRefused, noticeRefused});

        }
        return bussModel;
    }

    /**
     * 获取公告本周 本月 本年的公告量
     * @return
     */
    @Override
    public ReportReturnModel getBussFoNotice() {
        ReportReturnModel reportReturnModel=new ReportReturnModel();
        TimeModel currentWeek = timeFormatUtil.getTimeZoneByDateType("6");
        TimeModel currentMonth = timeFormatUtil.getTimeZoneByDateType("1");
        TimeModel currentYear = timeFormatUtil.getTimeZoneByDateType("0");
        reportReturnModel.setCurrentWeek(getNoticeDataByTime(currentWeek));
        reportReturnModel.setCurrentMonth(getNoticeDataByTime(currentMonth));
        reportReturnModel.setCurrentYear(getNoticeDataByTime(currentYear));
        return reportReturnModel;
    }

    /**
     * 获取申请的业务量
     *
     * @return
     */
    private int getApplyDataByTime(TimeModel timeModel) {
        if (timeModel == null) {
            return 0;
        }
        return applyInfoRepository.countApplyByDate(timeModel.getStartDate(), timeModel.getEndDate());
    }
    /**
     * 获取公告的申请量
     *
     * @return
     */
    private int getNoticeDataByTime(TimeModel timeModel) {
        if (timeModel == null) {
            return 0;
        }
        return noticeRepository.countNoticeByDate(timeModel.getStartDate(), timeModel.getEndDate());
    }
    /**
     * 获取申请当天 当年的业务量
     * @return
     */
    @Override
    public ReportReturnModel getBussForApply() {
        ReportReturnModel reportReturnModel=new ReportReturnModel();
        TimeModel currentYear = timeFormatUtil.getTimeZoneByDateType("0");
        TimeModel currentDay = timeFormatUtil.getTimeZoneByDateType("2");
        reportReturnModel.setCurrentYear(getApplyDataByTime(currentYear));
        reportReturnModel.setCurrentDay(getApplyDataByTime(currentDay));
        return reportReturnModel;
    }

    /**
     * 获取最近10天的业务总量
     * @return
     */
    @Override
    public List<ReportRtnModel> getBussForRecentApply() {
        TimeModel timeModel=new TimeModel();
        Calendar calendar = Calendar.getInstance();
        timeModel.setEndDate(TimeFormatUtil.getMaxTime(calendar));
        Date startDate = TimeUtil.getLateLyDayDate(lastDays);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(startDate);
        timeModel.setStartDate(TimeFormatUtil.getMinTime(calendar1));
        return resultToModelUtil.getApplyResultList(entityManager, StatisticAndAnalysisStorageUtil.APPLY_PERSON_ALL, timeModel,false);
    }

    /**
     * 本周的业务申请量
     * @return
     */
    @Override
    public List<ReportRtnModel> getBussCollectForApplyWeek() {
        TimeModel currentWeek = timeFormatUtil.getTimeZoneByDateType("6");
        if(currentWeek==null){
            return  new ArrayList<>();
        }
        return getBussForApplyByTime(currentWeek);
    }

    /**
     * 本月的申请量
     * @return
     */
    @Override
    public List<ReportRtnModel> getBussCollectForApplyMonth() {
        TimeModel currentMonth = timeFormatUtil.getTimeZoneByDateType("1");
        if(currentMonth==null){
            return  new ArrayList<>();
        }
        return getBussForApplyByTime(currentMonth);
    }

    /**
     * 本年的申请量
     * @return
     */
    @Override
    public List<ReportRtnModel> getBussCollectForApplyYear() {
        TimeModel currentYear = timeFormatUtil.getTimeZoneByDateType("0");
        if(currentYear==null){
            return  new ArrayList<>();
        }
        return getBussForApplyByTime(currentYear);
    }
    /**
     * 获取业务申请量
     * @param timeModel
     * @return
     */
    private List<ReportRtnModel> getBussForApplyByTime( TimeModel timeModel) {
        return resultToModelUtil.getApplyResultList(entityManager, StatisticAndAnalysisStorageUtil.APPLY_ALL_TIME, timeModel,false);
    }

    /**
     *
     * @return
     */
    @Override
    public ReportApplyModel getBussDealStatistics(String enteringPersonId) {
        ReportApplyModel reportApplyModel=new ReportApplyModel();
        reportApplyModel.setUnAnalyst(applyInfoRepository.countUnAnalystApply(enteringPersonId));
        reportApplyModel.setUnAudit(applyInfoRepository.countUnAuditorApply(enteringPersonId));
        reportApplyModel.setUnPrint(applyInfoRepository.countUnPrintApply(enteringPersonId));
        return reportApplyModel;
    }
}
