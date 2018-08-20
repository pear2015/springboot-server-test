package com.gs.crms.webapi.controller;

import com.gs.crms.applycertify.contract.model.ApplyAndAnalystRelation;
import com.gs.crms.applycertify.contract.model.WaitApportion;
import com.gs.crms.applycertify.contract.service.WorkFlowUseService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

/**
 * Created by zhengyali on 2018/1/17.
 */
public class WorkFlowUserControllerTest {
    @Mock
    private WorkFlowUseService workFlowUseService;
    @InjectMocks
    private WorkFlowUserController workFlowUserController;

    /**
     * 在测试方法运行前进行初始化Mock类（根据接口代理生成，但调用该方法时并不是真的方法）
     */
    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void afterMethod() {
        reset(workFlowUseService);
    }

    @Test
    public void testSaveWaitApportion() throws Exception {
        //1
        when(workFlowUseService.saveWaitApportion(null)).thenReturn(false);
        ResponseEntity<Boolean> responseEntity = workFlowUserController.saveWaitApportion(null);
        Assert.assertNotNull(responseEntity.getStatusCode());
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        //2
        WaitApportion waitApportion=new WaitApportion();
        when(workFlowUseService.saveWaitApportion(waitApportion)).thenReturn(false);
        ResponseEntity<Boolean> responseEntity1 = workFlowUserController.saveWaitApportion(waitApportion);
        Assert.assertNotNull(responseEntity1.getStatusCode());
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        //3
        when(workFlowUseService.saveWaitApportion(waitApportion)).thenReturn(true);
        ResponseEntity<Boolean> responseEntity2 = workFlowUserController.saveWaitApportion(waitApportion);
        Assert.assertNotNull(responseEntity2.getStatusCode());
        Assert.assertEquals(responseEntity2.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testSaveApplyAnlyastAuditor() throws Exception {
        //1
        when(workFlowUseService.saveApplyAnalystAuditor(null)).thenReturn(false);
        ResponseEntity<Boolean> responseEntity = workFlowUserController.saveApplyAnlyastAuditor(null);
        Assert.assertNotNull(responseEntity.getStatusCode());
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        //2
        ApplyAndAnalystRelation applyAndAnalystRelation=new ApplyAndAnalystRelation();
        when(workFlowUseService.saveApplyAnalystAuditor(applyAndAnalystRelation)).thenReturn(false);
        ResponseEntity<Boolean> responseEntity1 = workFlowUserController.saveApplyAnlyastAuditor(applyAndAnalystRelation);
        Assert.assertNotNull(responseEntity1.getStatusCode());
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        //3
        when(workFlowUseService.saveApplyAnalystAuditor(applyAndAnalystRelation)).thenReturn(true);
        ResponseEntity<Boolean> responseEntity2 = workFlowUserController.saveApplyAnlyastAuditor(applyAndAnalystRelation);
        Assert.assertNotNull(responseEntity2.getStatusCode());
        Assert.assertEquals(responseEntity2.getStatusCode(), HttpStatus.OK);
    }

}