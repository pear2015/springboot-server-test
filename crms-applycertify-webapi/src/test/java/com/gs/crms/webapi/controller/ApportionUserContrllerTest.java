package com.gs.crms.webapi.controller;

import com.gs.crms.applycertify.contract.model.WaitApportion;
import com.gs.crms.applycertify.contract.service.ApportionUserService;
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
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

/**
 * Created by PEAR on 2018/1/13.
 */
public class ApportionUserContrllerTest {
    @Mock
    private ApportionUserService apportionUserService;
    @InjectMocks
    private ApportionUserContrller apportionUserContrller;

    /**
     * 在测试方法运行前进行初始化Mock类（根据接口代理生成，但调用该方法时并不是真的方法）
     */
    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void afterMethod() {
        reset(apportionUserService);
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testFindNoAnalysisTaskAnalystList() throws Exception {
        //1
        List<String> ids = new ArrayList<>();
        List<String> list = new ArrayList<>();
        when(apportionUserService.findNoAnalysisTaskAnalystList(ids)).thenReturn(list);
        ResponseEntity<List<String>> responseEntity = apportionUserContrller.findNoAnalysisTaskAnalystList(ids);
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        //2
        ids.add("1");
        when(apportionUserService.findNoAnalysisTaskAnalystList(ids)).thenReturn(list);
        ResponseEntity<List<String>> responseEntity1 = apportionUserContrller.findNoAnalysisTaskAnalystList(ids);
        Assert.assertNotNull(responseEntity1);
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetAllWaitApportion() throws Exception {
        //1
        List<WaitApportion> list = new ArrayList<>();
        when(apportionUserService.getAllWaitApportionByWaitReasonType(anyString())).thenReturn(list);
        ResponseEntity<List<WaitApportion>> responseEntity = apportionUserContrller.getAllWaitApportion("");
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        //2
        when(apportionUserService.getAllWaitApportionByWaitReasonType("1")).thenReturn(list);
        ResponseEntity<List<WaitApportion>> responseEntity1 = apportionUserContrller.getAllWaitApportion("1");
        Assert.assertNotNull(responseEntity1);
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testDeleteApportion() throws Exception {
        //1
        List<String> ids = new ArrayList<>();
        boolean result = false;
        when(apportionUserService.deleteApportion(ids)).thenReturn(result);
        ResponseEntity<Boolean> responseEntity = apportionUserContrller.deleteApportion(ids);
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        //2
        ids.add("1");
        when(apportionUserService.deleteApportion(ids)).thenReturn(true);
        ResponseEntity<Boolean> responseEntity1 = apportionUserContrller.deleteApportion(ids);
        Assert.assertNotNull(responseEntity1);
        Assert.assertTrue(responseEntity1.hasBody());
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.OK);
    }
}
