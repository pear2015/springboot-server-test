package com.gs.crms.webapi.controller;

import com.gs.crms.applycertify.contract.model.ApplyBasicInfo;
import com.gs.crms.applycertify.contract.model.ApplyBussinessHistory;
import com.gs.crms.applycertify.contract.model.ApplyInfo;
import com.gs.crms.applycertify.contract.model.CertificateApplyInfo;
import com.gs.crms.applycertify.contract.model.admin.ApplyInfoParam;
import com.gs.crms.applycertify.contract.model.search.ApplyPersonQuery;
import com.gs.crms.applycertify.service.serviceimpl.CertificateApplyServiceImpl;
import com.gs.crms.common.model.ReturnBase;
import com.gs.crms.crimenotice.contract.model.CrimeRecordPrint;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

/**
 * Created by PEAR on 2018/1/13.
 */
public class ApplicationSearchControllerTest {
    @Mock
    private CertificateApplyServiceImpl certificateApplyService;
    @InjectMocks
    private ApplicationSearchController apportionSearchController;

    /**
     * 在测试方法运行前进行初始化Mock类（根据接口代理生成，但调用该方法时并不是真的方法）
     */
    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void afterMethod() {
        reset(certificateApplyService);
    }

    @Test
    public void testGetAnalysisTaskByAnalystID() throws Exception {
        String analystID = "11";
        CertificateApplyInfo certificateApplyInfo = new CertificateApplyInfo();
        when(certificateApplyService.getAnalysisTaskByAnalystId(anyString())).thenReturn(certificateApplyInfo);
        ResponseEntity<CertificateApplyInfo> responseEntity = apportionSearchController.getAnalysisTaskByAnalystId(analystID);
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        //情况二
        ResponseEntity<CertificateApplyInfo> responseEntity1 = apportionSearchController.getAnalysisTaskByAnalystId("");
        Assert.assertNotNull(responseEntity1);
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetApplyInfo() throws Exception {
        CertificateApplyInfo applyInfo = new CertificateApplyInfo();
        when(certificateApplyService.getApplyInfo(anyString())).thenReturn(applyInfo);
        ResponseEntity<CertificateApplyInfo> responseEntity = apportionSearchController.getApplyInfo("1");
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        //情况二
        ResponseEntity<CertificateApplyInfo> responseEntity1 = apportionSearchController.getApplyInfo("");
        Assert.assertNotNull(responseEntity1);
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetApplyPersonInfoByQuery() throws Exception {
        //1
        ApplyPersonQuery applyPersonQuery = new ApplyPersonQuery();
        List<ApplyBasicInfo> applyBasicInfoList = new ArrayList<>();
        when(certificateApplyService.getApplyPersonInfoByQuery(applyPersonQuery)).thenReturn(applyBasicInfoList);
        ResponseEntity<List<ApplyBasicInfo>> responseEntity = apportionSearchController.getApplyPersonInfoByQuery(applyPersonQuery);
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        //2
        applyPersonQuery.setFirstName("1");
        applyPersonQuery.setLastName("2");
        applyPersonQuery.setSexId("1");
        ResponseEntity<List<ApplyBasicInfo>> responseEntity1 = apportionSearchController.getApplyPersonInfoByQuery(applyPersonQuery);
        Assert.assertNotNull(responseEntity1);
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void testGetPersonalOneDayHandleApplication() throws Exception {
        //1
        ReturnBase<List<ApplyInfo>> list = new ReturnBase<>();
        when(certificateApplyService.getPersonalDayHandleApplication("", 0, 10)).thenReturn(list);
        ResponseEntity<ReturnBase<List<ApplyInfo>>> responseEntity = apportionSearchController.getPersonalOneDayHandleApplication("", 0, 10);
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        //2
        when(certificateApplyService.getPersonalDayHandleApplication("1", 0, 10)).thenReturn(list);
        ResponseEntity<ReturnBase<List<ApplyInfo>>> responseEntity1 = apportionSearchController.getPersonalOneDayHandleApplication("1", 0, 10);
        Assert.assertNotNull(responseEntity1);
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetUnCompleteApplicationByUserId() throws Exception {
        //1
        ReturnBase<List<ApplyInfo>> list = new ReturnBase<>();
        when(certificateApplyService.getUnCompleteApplicationByUserId("", 0, 10,"")).thenReturn(list);
        ResponseEntity<ReturnBase<List<ApplyInfo>>> responseEntity = apportionSearchController.getUnCompleteApplicationByUserId("", 0, 10,"");
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        //2
        when(certificateApplyService.getUnCompleteApplicationByUserId("1", 0, 10,"")).thenReturn(list);
        ResponseEntity<ReturnBase<List<ApplyInfo>>> responseEntity1 = apportionSearchController.getUnCompleteApplicationByUserId("1", 0, 10,"");
        Assert.assertNotNull(responseEntity1);
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetNeedReplyAnalysisApplication() throws Exception {
        //1
        ReturnBase<List<ApplyInfo>> list = new ReturnBase<>();
        when(certificateApplyService.getPersonalApplicationTask(0, 10, "", "")).thenReturn(list);
        ResponseEntity<ReturnBase<List<ApplyInfo>>> responseEntity = apportionSearchController.getNeedReplyAnalysisApplication(0, 10, "");
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        //2
        when(certificateApplyService.getPersonalApplicationTask(0, 10, "1", "")).thenReturn(list);
        ResponseEntity<ReturnBase<List<ApplyInfo>>> responseEntity1 = apportionSearchController.getNeedReplyAnalysisApplication(0, 10, "1");
        Assert.assertNotNull(responseEntity1);
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetNeedAuditApplicationById() throws Exception {
        //1
        ReturnBase<List<ApplyInfo>> list = new ReturnBase<>();
        when(certificateApplyService.getPersonalApplicationTask(0, 10, "", "")).thenReturn(list);
        ResponseEntity<ReturnBase<List<ApplyInfo>>> responseEntity = apportionSearchController.getNeedAuditApplicationById(0, 10, "");
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        //2
        when(certificateApplyService.getPersonalApplicationTask(0, 10, "1", "")).thenReturn(list);
        ResponseEntity<ReturnBase<List<ApplyInfo>>> responseEntity1 = apportionSearchController.getNeedAuditApplicationById(0, 10, "1");
        Assert.assertNotNull(responseEntity1);
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.OK);
    }

    /**
     * @throws Exception
     */
    @Test
    public void testGetHasAuditApplicationById() throws Exception {
        //1
        ApplyInfoParam applyInfoParam = new ApplyInfoParam();
        ReturnBase<List<ApplyInfo>> list = new ReturnBase<>();
        when(certificateApplyService.getPersonalApplicationHasTask(applyInfoParam)).thenReturn(list);
        ResponseEntity<ReturnBase<List<ApplyInfo>>> responseEntity = apportionSearchController.getHasAuditApplicationById(applyInfoParam);
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        //2
        applyInfoParam.setAuditPersonId("1");
        when(certificateApplyService.getPersonalApplicationHasTask(applyInfoParam)).thenReturn(list);
        ResponseEntity<ReturnBase<List<ApplyInfo>>> responseEntity1 = apportionSearchController.getHasAuditApplicationById(applyInfoParam);
        Assert.assertNotNull(responseEntity1);
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetApplyOperatorHistory() throws Exception {
        //1
        List<ApplyBussinessHistory> list = new ArrayList<>();
        when(certificateApplyService.getApplyOperatorHistoryById(anyString())).thenReturn(list);
        ResponseEntity<List<ApplyBussinessHistory>> responseEntity = apportionSearchController.getApplyOperatorHistory(anyString());
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        //2
        when(certificateApplyService.getApplyOperatorHistoryById("1")).thenReturn(list);
        ResponseEntity<List<ApplyBussinessHistory>> responseEntity1 = apportionSearchController.getApplyOperatorHistory("1");
        Assert.assertNotNull(responseEntity1);
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testFindNoticeByApplyId() throws Exception {
        //1
        List<CrimeRecordPrint> list = new ArrayList<>();
        when(certificateApplyService.findNoticeByApplyId(anyString())).thenReturn(list);
        ResponseEntity<List<CrimeRecordPrint>> responseEntity = apportionSearchController.findNoticeByApplyId(anyString());
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        //2
        when(certificateApplyService.findNoticeByApplyId("1")).thenReturn(list);
        ResponseEntity<List<CrimeRecordPrint>> responseEntity1 = apportionSearchController.findNoticeByApplyId("1");
        Assert.assertNotNull(responseEntity1);
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetCitizenInfoByID() throws Exception {
        //1
        List<ApplyBasicInfo> list = new ArrayList<>();
        when(certificateApplyService.getCitizenInfoByID(anyString())).thenReturn(list);
        ResponseEntity<List<ApplyBasicInfo>> responseEntity = apportionSearchController.getCitizenInfoByID(anyString());
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        //2
        when(certificateApplyService.getCitizenInfoByID("1")).thenReturn(list);
        ResponseEntity<List<ApplyBasicInfo>> responseEntity1 = apportionSearchController.getCitizenInfoByID("1");
        Assert.assertNotNull(responseEntity1);
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.OK);
    }
    @Test
    public void testGetCitizenInfoByName() throws Exception {
        //1
        List<ApplyBasicInfo> list = new ArrayList<>();
        when(certificateApplyService.getCitizenInfoByFullName(anyString(), anyString(),anyString())).thenReturn(list);
        ResponseEntity<List<ApplyBasicInfo>> responseEntity = apportionSearchController.getCitizenInfoByName(anyString(), anyString(),anyString());
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        //2
        when(certificateApplyService.getCitizenInfoByFullName("1", "1","1")).thenReturn(list);
        ResponseEntity<List<ApplyBasicInfo>> responseEntity1 = apportionSearchController.getCitizenInfoByName("1","1","1");
        Assert.assertNotNull(responseEntity1);
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.OK);
    }
    @Test
    public void testFindApplicationSchedule() throws Exception {
        //1
        List<Map<String, Object>>list=new ArrayList<>();
        when(certificateApplyService.getPersonalApplicationListBySearch(anyString(), anyString())).thenReturn(list);
        ResponseEntity<List<Map<String, Object>>>responseEntity = apportionSearchController.findApplicationSchedule(anyString(), anyString());
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        //2
        when(certificateApplyService.getPersonalApplicationListBySearch("1", "1")).thenReturn(list);
        ResponseEntity<List<Map<String, Object>>> responseEntity1 = apportionSearchController.findApplicationSchedule("1", "1");
        Assert.assertNotNull(responseEntity1);
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.OK);
    }
}
