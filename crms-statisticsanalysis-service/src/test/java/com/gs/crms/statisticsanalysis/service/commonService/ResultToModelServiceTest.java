package com.gs.crms.statisticsanalysis.service.commonService;

import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;
import com.gs.crms.statisticsanalysis.contract.model.TimeModel;
import com.gs.crms.statisticsanalysis.service.common.ResultToModelService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

/**
 * Created by wangzuzhi on 2017/11/13.
 */
public class ResultToModelServiceTest {

    @Mock
    EntityManager entityManager;

    @Mock
    StoredProcedureQuery storedProcedureQuery;

    @InjectMocks
    ResultToModelService resultToModelService;

    @BeforeMethod
    private void beforeMethod() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    private void afterMethod() {
        reset();
    }

    @Test
    public void testGetResultList_ByDateIsYEAR() throws Exception {
        TimeModel timeModel = new TimeModel();
        timeModel.setStartDate(Timestamp.valueOf("2017-01-01 00:00:00.0"));
        timeModel.setEndDate(Timestamp.valueOf("2017-12-31 23:59:59.999"));
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        ReportRtnModel reportRtnModel = new ReportRtnModel();
        reportRtnModel.setCoord_X("2017");
        reportRtnModel.setTypeName("11");
        reportRtnModel.setCount("10");
        reportRtnModels.add(reportRtnModel);
        int dateType = 0;
        String proce_name = "1";
        List<String> params = new ArrayList<>();
        params.add("1");
        params.add("2");
        List<Object[]> cursorRecord = new ArrayList<>();
        Object[] obj = {"1","2","2017.01.01","4"};
        cursorRecord.add(obj);
        when(entityManager.createStoredProcedureQuery(proce_name)).thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.execute()).thenReturn(true);
        when(storedProcedureQuery.getResultList()).thenReturn(cursorRecord);
        List<ReportRtnModel> result = resultToModelService.getResultListByDate(entityManager,dateType,proce_name,timeModel,params);
        assertEquals(result.size(), 1);
    }

    @Test
    public void testGetResultList_ByDateIsMONTH() throws Exception {
        TimeModel timeModel = new TimeModel();
        timeModel.setStartDate(Timestamp.valueOf("2017-01-01 00:00:00.0"));
        timeModel.setEndDate(Timestamp.valueOf("2017-12-31 23:59:59.999"));
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        ReportRtnModel reportRtnModel = new ReportRtnModel();
        reportRtnModel.setCoord_X("2017");
        reportRtnModel.setTypeName("11");
        reportRtnModel.setCount("10");
        reportRtnModels.add(reportRtnModel);
        int dateType = 1;
        String proce_name = "1";
        List<String> params = new ArrayList<>();
        params.add("1");
        params.add("2");
        List<Object[]> cursorRecord = new ArrayList<>();
        Object[] obj = {"1","2","2017.01.01","2017.01.01","5"};
        cursorRecord.add(obj);
        when(entityManager.createStoredProcedureQuery(proce_name)).thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.execute()).thenReturn(true);
        when(storedProcedureQuery.getResultList()).thenReturn(cursorRecord);
        List<ReportRtnModel> result = resultToModelService.getResultListByDate(entityManager,dateType,proce_name,timeModel,params);
        assertEquals(result.size(), 1);
    }
}