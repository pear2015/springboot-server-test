//package com.gs.crms.webapi.controller;
//
//
//import com.gs.crms.crimenotice.contract.model.*;
//import com.gs.crms.crimenotice.contract.service.CrimeService;
//import org.junit.Assert;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//
//import static org.mockito.Matchers.anyObject;
//
//import static org.mockito.Matchers.anyString;
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.reset;
//
///**
// * Created by: xuqicai
// * Date:2017/8/15
// * Project_Name:crms-server
// * Description:
// */
//public class CrimeControllerTest {
//
//    @Mock
//    private CrimeService crimeService;
//
//    @InjectMocks
//    private CrimeController crimeController;
//    @BeforeMethod
//    public void beforeMethod() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @AfterMethod
//    public void afterMethod() {
//        reset(crimeService);
//    }
//
//    @Test
//    public void testSaveCrimeInfo() throws Exception {
//        boolean result = true;
//        CrimeAndNoticeExtendInfo crimeAndNoticeExtendInfo = new CrimeAndNoticeExtendInfo();
//
//        when(crimeService.addCrimeAndNoticeInfo(anyObject(),anyObject(),anyObject(),anyObject())).thenReturn(result);
//
//        ResponseEntity responseEntity = crimeController.saveCrimeInfo(crimeAndNoticeExtendInfo);
//        Assert.assertNotNull(responseEntity);
//    }
//    @Test
//    public void testSaveCrimeInfo_Null() throws Exception {
//        boolean result = true;
//
//        when(crimeService.addCrimeAndNoticeInfo(anyObject(),anyObject(),anyObject(),anyObject())).thenReturn(result);
//
//        ResponseEntity responseEntity = crimeController.saveCrimeInfo(null);
//        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @Test
//    public void testSearchCrimeInfo() throws Exception {
//        Date enteringBeginTime = new Date();
//        Date enteringEndTime = new Date();
//        List<CrimeAndNoticeExtendInfo> result = new ArrayList<>();
//
//        when(crimeService.getCrimeAndNoticeExtendInfo(anyObject(),anyObject(),anyObject(),anyObject())).thenReturn(result);
//
//        ResponseEntity responseEntity = crimeController.searchCrimeInfo("1","",enteringBeginTime,enteringEndTime);
//        Assert.assertNotNull(responseEntity);
//        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
//    }
//
//    @Test
//    public void testGetGroupListInfo() throws Exception {
//        List<GroupInfo> groupInfoList = new ArrayList<>();
//
//        when(crimeService.getGroupInfo()).thenReturn(groupInfoList);
//
//        ResponseEntity responseEntity = crimeController.getGroupListInfo();
//        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
//    }
//
//    @Test
//    public void testGetCourtListInfo() throws Exception {
//        List<CourtInfo> courtInfoList = new ArrayList<>();
//
//        when(crimeService.getCourtList()).thenReturn(courtInfoList);
//
//        ResponseEntity responseEntity = crimeController.getCourtListInfo();
//        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
//    }
//
//    @Test
//    public void testGetNoticeInfoByGroup() throws Exception {
//        List<NoticeInfo> noticeInfoList = new ArrayList<>();
//
//        when(crimeService.getNoticeListInfoByGroup(anyObject())).thenReturn(noticeInfoList);
//
//        ResponseEntity responseEntity = crimeController.getNoticeInfoByGroup("1");
//        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
//    }
//    @Test
//    public void testGetNoticeInfoByGroup_Null() throws Exception {
//        List<NoticeInfo> noticeInfoList = new ArrayList<>();
//
//        when(crimeService.getNoticeListInfoByGroup(anyObject())).thenReturn(noticeInfoList);
//
//        ResponseEntity responseEntity = crimeController.getNoticeInfoByGroup(null);
//        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @Test
//    public void testSearchCrimePersonInfo() throws Exception {
//        List<CrimePersonInfo> crimePersonInfoList = new ArrayList<>();
//
//        when(crimeService.getCrimePersonListInfoByFirstNameAndLastNameOrCertificateNumber(anyObject(),anyObject(),anyString())).thenReturn(crimePersonInfoList);
//
//        ResponseEntity responseEntity =crimeController.searchCrimePersonInfo("1","1","123456");
//        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
//    }
//    @Test
//    public void testGetNoticeInfoListByIDAndName() throws Exception{
//        List<CrimeAndNoticeExtendInfo> list = new ArrayList<>();
//
//        when(crimeService.getCrimeAndNoticeExtendInfo(anyObject(),anyObject(),anyString())).thenReturn(list);
//
//        ResponseEntity<List<CrimeAndNoticeExtendInfo>> responseEntity = crimeController.getNoticeInfoListByIDAndName("1","1","1");
//        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
//        //情况二
//        ResponseEntity<List<CrimeAndNoticeExtendInfo>> responseEntity1 = crimeController.getNoticeInfoListByIDAndName("","","");
//        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    /**
//     * 通过申请ID匹配犯罪公告列表
//     * @throws Exception
//     */
//    @Test
//    public void testGetCrimeAndNoticeExtendInfoByApplyID()throws Exception{
//        List<CrimeAndNoticeExtendInfo> list = new ArrayList<>();
//        String applyID= "1";
//        when(crimeService.getCrimeAndNoticeExtendInfoByApplyID(applyID)).thenReturn(list);
//        ResponseEntity<List<CrimeAndNoticeExtendInfo>>  responseEntity= crimeController.getCrimeAndNoticeExtendInfoByApplyID(applyID);
//        Assert.assertNotNull(responseEntity);
//        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
//
//        applyID = "";
//        when(crimeService.getCrimeAndNoticeExtendInfoByApplyID(applyID)).thenReturn(list);
//        ResponseEntity<List<CrimeAndNoticeExtendInfo>>  responseEntity_Error= crimeController.getCrimeAndNoticeExtendInfoByApplyID(applyID);
//        Assert.assertNotNull(responseEntity_Error);
//        Assert.assertEquals(responseEntity_Error.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}