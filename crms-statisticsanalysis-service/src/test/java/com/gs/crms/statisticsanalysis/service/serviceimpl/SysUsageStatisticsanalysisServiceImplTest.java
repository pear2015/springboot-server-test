//package com.gs.crms.statisticsanalysis.service.serviceimpl;
//
//import com.gs.crms.common.enums.DateType;
//import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;
//import com.gs.crms.statisticsanalysis.contract.model.StatisticsParamModel;
//import com.gs.crms.statisticsanalysis.contract.model.TimeModel;
//import com.gs.crms.statisticsanalysis.service.commonService.ResultToModelService;
//import com.gs.crms.statisticsanalysis.service.commonService.TimeFormatService;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import javax.persistence.EntityManager;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.reset;
//import static org.mockito.Mockito.when;
//import static org.testng.Assert.*;
//
///**
// * Created by wangzuzhi on 2017/11/16.
// */
//public class SysUsageStatisticsanalysisServiceImplTest {
//
//    @Mock
//    EntityManager entityManager;
//
//    @Mock
//    TimeFormatService timeFormatService;
//
//    @Mock
//    ResultToModelService resultToModelService;
//
//    @InjectMocks
//    SysUsageStatisticsanalysisServiceImpl sysUsageStatisticsanalysisService;
//
//    @BeforeMethod
//    private void beforeMethod() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @AfterMethod
//    private void afterMethod() {
//        reset();
//    }
//
//    @Test
//    public void testGetSysUsageStatisticByDateIsYEAR() throws Exception {
//        TimeModel timeModel = new TimeModel();
//        timeModel.setStartDate(Timestamp.valueOf("2017-01-01 00:00:00.0"));
//        timeModel.setEndDate(Timestamp.valueOf("2017-12-31 23:59:59.999"));
//        List<ReportRtnModel> crimeInfoStatistics = new ArrayList<>();
//        ReportRtnModel reportRtnModel = new ReportRtnModel();
//        reportRtnModel.setCount("10");
//        reportRtnModel.setTypeName("1");
//        reportRtnModel.setCoord_X("2017");
//        crimeInfoStatistics.add(reportRtnModel);
//        StatisticsParamModel statisticsParamModel = new StatisticsParamModel();
//        statisticsParamModel.setDateType(0);
//        statisticsParamModel.setDateEnums(DateType.YEAR);
//        when(timeFormatService.getTimeZone(statisticsParamModel)).thenReturn(timeModel);
//        when(resultToModelService.getResultList_ByDate(entityManager,statisticsParamModel.getDateType(),"11",timeModel,statisticsParamModel.getCenterCodes())).thenReturn(crimeInfoStatistics);
//        List<ReportRtnModel> result = sysUsageStatisticsanalysisService.getSysUsageStatisticByDate(statisticsParamModel);
//        assertEquals(result.size(), 0);
//    }
//
//    @Test
//    public void testGetSysUsageStatisticByDateIsMONTH() throws Exception {
//        TimeModel timeModel = new TimeModel();
//        timeModel.setStartDate(Timestamp.valueOf("2017-01-01 00:00:00.0"));
//        timeModel.setEndDate(Timestamp.valueOf("2017-12-31 23:59:59.999"));
//        List<ReportRtnModel> crimeInfoStatistics = new ArrayList<>();
//        ReportRtnModel reportRtnModel = new ReportRtnModel();
//        reportRtnModel.setCount("10");
//        reportRtnModel.setTypeName("1");
//        reportRtnModel.setCoord_X("2017");
//        crimeInfoStatistics.add(reportRtnModel);
//        StatisticsParamModel statisticsParamModel = new StatisticsParamModel();
//        statisticsParamModel.setDateType(1);
//        statisticsParamModel.setDateEnums(DateType.MONTH);
//        when(timeFormatService.getTimeZone(statisticsParamModel)).thenReturn(timeModel);
//        when(resultToModelService.getResultList_ByDate(entityManager,statisticsParamModel.getDateType(),"11",timeModel,statisticsParamModel.getCenterCodes())).thenReturn(crimeInfoStatistics);
//        List<ReportRtnModel> result = sysUsageStatisticsanalysisService.getSysUsageStatisticByDate(statisticsParamModel);
//        assertEquals(result.size(), 0);
//    }
//
//    @Test
//    public void testGetSysUsageStatisticByDateIsDAY() throws Exception {
//        TimeModel timeModel = new TimeModel();
//        timeModel.setStartDate(Timestamp.valueOf("2017-01-01 00:00:00.0"));
//        timeModel.setEndDate(Timestamp.valueOf("2017-12-31 23:59:59.999"));
//        List<ReportRtnModel> crimeInfoStatistics = new ArrayList<>();
//        ReportRtnModel reportRtnModel = new ReportRtnModel();
//        reportRtnModel.setCount("10");
//        reportRtnModel.setTypeName("1");
//        reportRtnModel.setCoord_X("2017");
//        crimeInfoStatistics.add(reportRtnModel);
//        StatisticsParamModel statisticsParamModel = new StatisticsParamModel();
//        statisticsParamModel.setDateType(2);
//        statisticsParamModel.setDateEnums(DateType.DAY);
//        when(timeFormatService.getTimeZone(statisticsParamModel)).thenReturn(timeModel);
//        when(resultToModelService.getResultList_ByDate(entityManager,statisticsParamModel.getDateType(),"11",timeModel,statisticsParamModel.getCenterCodes())).thenReturn(crimeInfoStatistics);
//        List<ReportRtnModel> result = sysUsageStatisticsanalysisService.getSysUsageStatisticByDate(statisticsParamModel);
//        assertEquals(result.size(), 0);
//    }
//
//    @Test
//    public void testGetSysUsageStatisticByDateIsYEARDIY() throws Exception {
//        TimeModel timeModel = new TimeModel();
//        timeModel.setStartDate(Timestamp.valueOf("2017-01-01 00:00:00.0"));
//        timeModel.setEndDate(Timestamp.valueOf("2017-12-31 23:59:59.999"));
//        List<ReportRtnModel> crimeInfoStatistics = new ArrayList<>();
//        ReportRtnModel reportRtnModel = new ReportRtnModel();
//        reportRtnModel.setCount("10");
//        reportRtnModel.setTypeName("1");
//        reportRtnModel.setCoord_X("2017");
//        crimeInfoStatistics.add(reportRtnModel);
//        StatisticsParamModel statisticsParamModel = new StatisticsParamModel();
//        statisticsParamModel.setDateType(3);
//        statisticsParamModel.setDateEnums(DateType.YEARDIY);
//        when(timeFormatService.getTimeZone(statisticsParamModel)).thenReturn(timeModel);
//        when(resultToModelService.getResultList_ByDate(entityManager,statisticsParamModel.getDateType(),"11",timeModel,statisticsParamModel.getCenterCodes())).thenReturn(crimeInfoStatistics);
//        List<ReportRtnModel> result = sysUsageStatisticsanalysisService.getSysUsageStatisticByDate(statisticsParamModel);
//        assertEquals(result.size(), 0);
//    }
//
//    @Test
//    public void testGetSysUsageStatisticByDateIsMONTHDIY() throws Exception {
//        TimeModel timeModel = new TimeModel();
//        timeModel.setStartDate(Timestamp.valueOf("2017-01-01 00:00:00.0"));
//        timeModel.setEndDate(Timestamp.valueOf("2017-12-31 23:59:59.999"));
//        List<ReportRtnModel> crimeInfoStatistics = new ArrayList<>();
//        ReportRtnModel reportRtnModel = new ReportRtnModel();
//        reportRtnModel.setCount("10");
//        reportRtnModel.setTypeName("1");
//        reportRtnModel.setCoord_X("2017");
//        crimeInfoStatistics.add(reportRtnModel);
//        StatisticsParamModel statisticsParamModel = new StatisticsParamModel();
//        statisticsParamModel.setDateType(4);
//        statisticsParamModel.setDateEnums(DateType.MONTHDIY);
//        when(timeFormatService.getTimeZone(statisticsParamModel)).thenReturn(timeModel);
//        when(resultToModelService.getResultList_ByDate(entityManager,statisticsParamModel.getDateType(),"11",timeModel,statisticsParamModel.getCenterCodes())).thenReturn(crimeInfoStatistics);
//        List<ReportRtnModel> result = sysUsageStatisticsanalysisService.getSysUsageStatisticByDate(statisticsParamModel);
//        assertEquals(result.size(), 0);
//    }
//
//    @Test
//    public void testGetSysUsageStatisticByDateIsDAYDIY() throws Exception {
//        TimeModel timeModel = new TimeModel();
//        timeModel.setStartDate(Timestamp.valueOf("2017-01-01 00:00:00.0"));
//        timeModel.setEndDate(Timestamp.valueOf("2017-12-31 23:59:59.999"));
//        List<ReportRtnModel> crimeInfoStatistics = new ArrayList<>();
//        ReportRtnModel reportRtnModel = new ReportRtnModel();
//        reportRtnModel.setCount("10");
//        reportRtnModel.setTypeName("1");
//        reportRtnModel.setCoord_X("2017");
//        crimeInfoStatistics.add(reportRtnModel);
//        StatisticsParamModel statisticsParamModel = new StatisticsParamModel();
//        statisticsParamModel.setDateType(5);
//        statisticsParamModel.setDateEnums(DateType.DAYDIY);
//        when(timeFormatService.getTimeZone(statisticsParamModel)).thenReturn(timeModel);
//        when(resultToModelService.getResultList_ByDate(entityManager,statisticsParamModel.getDateType(),"11",timeModel,statisticsParamModel.getCenterCodes())).thenReturn(crimeInfoStatistics);
//        List<ReportRtnModel> result = sysUsageStatisticsanalysisService.getSysUsageStatisticByDate(statisticsParamModel);
//        assertEquals(result.size(), 0);
//    }
//
//}