//package com.gs.crms.webapi.controller;
//
//import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;
//import com.gs.crms.statisticsanalysis.contract.model.StatisticsParamModel;
//import com.gs.crms.statisticsanalysis.contract.service.CrimeInfoStatisticsAnalysisService;
//import com.gs.crms.statisticsanalysis.contract.service.CrimeNoticeStatisticsAnalysisService;
//import com.gs.crms.statisticsanalysis.contract.service.SysUsageStatisticsanalysisService;
//import com.gs.glink.common.utils.JsonUtil;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.testng.Assert.assertEquals;
//import static org.mockito.Mockito.reset;
//import static org.mockito.Mockito.when;
//
///**
// * Created by wangzuzhi on 2017/11/14.
// */
//public class CrimeStatisticsanalysisControllerTest {
//
//    @Mock
//    CrimeInfoStatisticsAnalysisService crimeInfoStatisticsAnalysisService;
//
//    @Mock
//    CrimeNoticeStatisticsAnalysisService crimeNoticeStatisticsAnalysisService;
//
//    @Mock
//    SysUsageStatisticsanalysisService sysUsageStatisticsanalysisService;
//
//    @InjectMocks
//    CrimeStatisticsanalysisController crimeStatisticsanalysisController;
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
//    public void testGetCrimeNoticeStatisticsByDate() throws Exception {
//        String queryInfo = "{\"dateType\": 0}";
//        StatisticsParamModel statisticsParamModel = JsonUtil.fromJson(queryInfo, StatisticsParamModel.class);
//        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
//        ReportRtnModel reportRtnModel = new ReportRtnModel();
//        reportRtnModel.setCoord_X("2017");
//        reportRtnModel.setTypeName("1");
//        reportRtnModel.setCount("20");
//        reportRtnModels.add(reportRtnModel);
//        when(crimeNoticeStatisticsAnalysisService.getCrimeNoticeStatisticsByDate(statisticsParamModel)).thenReturn(reportRtnModels);
//        ResponseEntity<List<ReportRtnModel>> responseEntity = crimeStatisticsanalysisController.getCrimeNoticeStatisticsByDate(queryInfo);
//        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//    }
//
//    @Test
//    public void testGetCrimeNoticeStatisticsByDateIsNull() throws Exception {
//        String queryInfo = "";
//        StatisticsParamModel statisticsParamModel = null;
//        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
//        ReportRtnModel reportRtnModel = new ReportRtnModel();
//        reportRtnModel.setCoord_X("2017");
//        reportRtnModel.setTypeName("1");
//        reportRtnModel.setCount("20");
//        reportRtnModels.add(reportRtnModel);
//        when(crimeNoticeStatisticsAnalysisService.getCrimeNoticeStatisticsByDate(statisticsParamModel)).thenReturn(reportRtnModels);
//        ResponseEntity<List<ReportRtnModel>> responseEntity = crimeStatisticsanalysisController.getCrimeNoticeStatisticsByDate(queryInfo);
//        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @Test
//    public void testGetCrimeInfoStatisticsByRegionDate() throws Exception {
//        String queryInfo = "{\"dateType\": 0}";
//        StatisticsParamModel statisticsParamModel = JsonUtil.fromJson(queryInfo, StatisticsParamModel.class);
//        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
//        ReportRtnModel reportRtnModel = new ReportRtnModel();
//        reportRtnModel.setCoord_X("2017");
//        reportRtnModel.setTypeName("1");
//        reportRtnModel.setCount("20");
//        reportRtnModels.add(reportRtnModel);
//        when(crimeInfoStatisticsAnalysisService.getCrimeInfoStatisticsByRegionDate(statisticsParamModel)).thenReturn(reportRtnModels);
//        ResponseEntity<List<ReportRtnModel>> responseEntity = crimeStatisticsanalysisController.getCrimeInfoStatisticsByRegionDate(queryInfo);
//        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//    }
//
//    @Test
//    public void testGetCrimeInfoStatisticsByRegionDateIsNull() throws Exception {
//        String queryInfo = "";
//        StatisticsParamModel statisticsParamModel = null;
//        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
//        ReportRtnModel reportRtnModel = new ReportRtnModel();
//        reportRtnModel.setCoord_X("2017");
//        reportRtnModel.setTypeName("1");
//        reportRtnModel.setCount("20");
//        reportRtnModels.add(reportRtnModel);
//        when(crimeInfoStatisticsAnalysisService.getCrimeInfoStatisticsByRegionDate(statisticsParamModel)).thenReturn(reportRtnModels);
//        ResponseEntity<List<ReportRtnModel>> responseEntity = crimeStatisticsanalysisController.getCrimeInfoStatisticsByRegionDate(queryInfo);
//        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @Test
//    public void testGetCrimeInfoStatisticsByCrimeTypeDate() throws Exception {
//        String queryInfo = "{\"dateType\": 0}";
//        StatisticsParamModel statisticsParamModel = JsonUtil.fromJson(queryInfo, StatisticsParamModel.class);
//        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
//        ReportRtnModel reportRtnModel = new ReportRtnModel();
//        reportRtnModel.setCoord_X("2017");
//        reportRtnModel.setTypeName("1");
//        reportRtnModel.setCount("20");
//        reportRtnModels.add(reportRtnModel);
//        when(crimeInfoStatisticsAnalysisService.getCrimeInfoStatisticsByCrimeTypeDate(statisticsParamModel)).thenReturn(reportRtnModels);
//        ResponseEntity<List<ReportRtnModel>> responseEntity = crimeStatisticsanalysisController.getCrimeInfoStatisticsByCrimeTypeDate(queryInfo);
//        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//    }
//
//    @Test
//    public void testGetCrimeInfoStatisticsByCrimeTypeDateIsNull() throws Exception {
//        String queryInfo = "";
//        StatisticsParamModel statisticsParamModel = null;
//        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
//        ReportRtnModel reportRtnModel = new ReportRtnModel();
//        reportRtnModel.setCoord_X("2017");
//        reportRtnModel.setTypeName("1");
//        reportRtnModel.setCount("20");
//        reportRtnModels.add(reportRtnModel);
//        when(crimeInfoStatisticsAnalysisService.getCrimeInfoStatisticsByCrimeTypeDate(statisticsParamModel)).thenReturn(reportRtnModels);
//        ResponseEntity<List<ReportRtnModel>> responseEntity = crimeStatisticsanalysisController.getCrimeInfoStatisticsByCrimeTypeDate(queryInfo);
//        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @Test
//    public void testGetSysUsageStatisticByDate() throws Exception {
//        String queryInfo = "{\"dateType\": 0}";
//        StatisticsParamModel statisticsParamModel = JsonUtil.fromJson(queryInfo, StatisticsParamModel.class);
//        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
//        ReportRtnModel reportRtnModel = new ReportRtnModel();
//        reportRtnModel.setCoord_X("2017");
//        reportRtnModel.setTypeName("1");
//        reportRtnModel.setCount("20");
//        reportRtnModels.add(reportRtnModel);
//        when(sysUsageStatisticsanalysisService.getSysUsageStatisticByDate(statisticsParamModel)).thenReturn(reportRtnModels);
//        ResponseEntity<List<ReportRtnModel>> responseEntity = crimeStatisticsanalysisController.getSysUsageStatisticByDate(queryInfo);
//        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//    }
//
//    @Test
//    public void testGetSysUsageStatisticByDateIsNull() throws Exception {
//        String queryInfo = "";
//        StatisticsParamModel statisticsParamModel = null;
//        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
//        ReportRtnModel reportRtnModel = new ReportRtnModel();
//        reportRtnModel.setCoord_X("2017");
//        reportRtnModel.setTypeName("1");
//        reportRtnModel.setCount("20");
//        reportRtnModels.add(reportRtnModel);
//        when(sysUsageStatisticsanalysisService.getSysUsageStatisticByDate(statisticsParamModel)).thenReturn(reportRtnModels);
//        ResponseEntity<List<ReportRtnModel>> responseEntity = crimeStatisticsanalysisController.getSysUsageStatisticByDate(queryInfo);
//        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//}