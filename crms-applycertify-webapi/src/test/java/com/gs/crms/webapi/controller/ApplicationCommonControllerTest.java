package com.gs.crms.webapi.controller;

import com.gs.crms.applycertify.contract.service.CommonApplyService;
import com.gs.crms.common.enums.ApplyType;
import org.testng.annotations.Test;
import com.gs.crms.applycertify.contract.model.ApplyPriority;
import com.gs.crms.applycertify.contract.model.ApplyPurpose;
import com.gs.crms.applycertify.contract.model.CertificateType;
import com.gs.crms.applycertify.service.serviceimpl.CertificateApplyServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

/**
 * Created by zhengyali on 2018/1/16.
 */
public class ApplicationCommonControllerTest {
    @Mock
    private CommonApplyService commonApplyService;
    @InjectMocks
    private ApplicationCommonController apportionCommonController;

    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void afterMethod() {
        reset(commonApplyService);
    }

    @Test
    public void testGetCertificateTypeList() throws Exception {
        List<CertificateType> list = new ArrayList<>();
        when(commonApplyService.getCertificateTypeList()).thenReturn(list);
        ApplyType applyType=null;
        ResponseEntity<List<CertificateType>> responseEntity = apportionCommonController.getCertificateTypeList(applyType);
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetApplyPurposeList() throws Exception {
        List<ApplyPurpose> list = new ArrayList<>();
        when(commonApplyService.getApplyPurposeList()).thenReturn(list);
        ResponseEntity<List<ApplyPurpose>> responseEntity = apportionCommonController.getApplyPurposeList();
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetApplyPriority() throws Exception {
        List<ApplyPriority> list = new ArrayList<>();
        when(commonApplyService.getAllApplyPriority()).thenReturn(list);
        ResponseEntity<List<ApplyPriority>> responseEntity = apportionCommonController.getApplyPriority();
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

}