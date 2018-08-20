package com.gs.crms.webapi.controller;

import com.gs.crms.applycertify.contract.model.ApplyBasicInfo;
import com.gs.crms.applycertify.contract.model.ApplyInfo;
import com.gs.crms.applycertify.contract.model.CertificateApplyInfo;
import com.gs.crms.applycertify.contract.service.CertificateApplyService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

/**
 * Created by PEAR on 2018/1/13.
 */
public class CertificateApplyControllerTest {

    @Mock
    private CertificateApplyService certificateApplyService;

    @InjectMocks
    private CertificateApplyController certificateApplyController;

    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void afterMethod() {
        reset(certificateApplyService);
    }

    /**
     * @throws Exception
     */
    @Test
    public void testSaveCertificateApplyInfo() throws Exception {
        //1
        CertificateApplyInfo certificateApplyInfo = new CertificateApplyInfo();
        certificateApplyInfo.setApplyBasicInfo(null);
        when(certificateApplyService.saveApplyInfo(certificateApplyInfo)).thenReturn(null);
        ResponseEntity<ApplyInfo> responseEntity = certificateApplyController.saveApplyInfo(certificateApplyInfo);
        Assert.assertNotNull(responseEntity.getStatusCode());
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        //2
        certificateApplyInfo.setApplyBasicInfo(new ApplyBasicInfo());
        certificateApplyInfo.setApplyInfo(new ApplyInfo());
        when(certificateApplyService.saveApplyInfo(certificateApplyInfo)).thenReturn(null);
        ResponseEntity<ApplyInfo> responseEntity1 = certificateApplyController.saveApplyInfo(certificateApplyInfo);
        Assert.assertNotNull(responseEntity1.getStatusCode());
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        //3
        ApplyInfo applyInfo = new ApplyInfo();
        when(certificateApplyService.saveApplyInfo(certificateApplyInfo)).thenReturn(applyInfo);
        ResponseEntity<ApplyInfo> responseEntity2 = certificateApplyController.saveApplyInfo(certificateApplyInfo);
        Assert.assertNotNull(responseEntity2.getStatusCode());
        Assert.assertEquals(responseEntity2.getStatusCode(), HttpStatus.OK);
    }

    /**
     * 待验证
     *
     * @throws Exception
     */
    @Test
    public void testUpdateCertificateApplyInfo() throws Exception {
        //1
        CertificateApplyInfo certificateApplyInfo = new CertificateApplyInfo();
        certificateApplyInfo.setApplyBasicInfo(null);
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        when(certificateApplyService.submitAnalysisResult(certificateApplyInfo)).thenReturn(map);
        ResponseEntity<Map<String, Object>> responseEntity = certificateApplyController.updateCertificateApplyInfo(certificateApplyInfo);
        Assert.assertNotNull(responseEntity.getStatusCode());
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        //2
        certificateApplyInfo.setApplyBasicInfo(new ApplyBasicInfo());
        certificateApplyInfo.setApplyInfo(new ApplyInfo());
        when(certificateApplyService.submitAnalysisResult(certificateApplyInfo)).thenReturn(map);
        ResponseEntity<Map<String, Object>> responseEntity1 = certificateApplyController.updateCertificateApplyInfo(certificateApplyInfo);
        Assert.assertNotNull(responseEntity1.getStatusCode());
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        //3
        Map<String, Object> map1 = new HashMap<>();
        map1.put("success", true);
        when(certificateApplyService.submitAnalysisResult(certificateApplyInfo)).thenReturn(map1);
        ResponseEntity<Map<String, Object>> responseEntity2 = certificateApplyController.updateCertificateApplyInfo(certificateApplyInfo);
        Assert.assertNotNull(responseEntity2.getStatusCode());
        Assert.assertEquals(responseEntity2.getStatusCode(), HttpStatus.OK);
    }

    /**
     * 待验证
     *
     * @throws Exception
     */
    @Test
    public void testApplyAuditSubmit() throws Exception {
        //1
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        CertificateApplyInfo certificateApplyInfo = new CertificateApplyInfo();
        certificateApplyInfo.setApplyBasicInfo(null);
        when(certificateApplyService.applyAuditSubmit(certificateApplyInfo)).thenReturn(map);
        ResponseEntity<Map<String,Object>> responseEntity = certificateApplyController.applyAuditSubmit(certificateApplyInfo);
        Assert.assertNotNull(responseEntity.getStatusCode());
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        //2
        certificateApplyInfo.setApplyBasicInfo(new ApplyBasicInfo());
        certificateApplyInfo.setApplyInfo(new ApplyInfo());
        when(certificateApplyService.applyAuditSubmit(certificateApplyInfo)).thenReturn(map);
        ResponseEntity<Map<String,Object>> responseEntity1 = certificateApplyController.applyAuditSubmit(certificateApplyInfo);
        Assert.assertNotNull(responseEntity1.getStatusCode());
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        //3
        Map<String, Object> map1 = new HashMap<>();
        map1.put("success", true);
        when(certificateApplyService.applyAuditSubmit(certificateApplyInfo)).thenReturn(map1);
        ResponseEntity<Map<String,Object>> responseEntity2 = certificateApplyController.applyAuditSubmit(certificateApplyInfo);
        Assert.assertNotNull(responseEntity2.getStatusCode());
        Assert.assertEquals(responseEntity2.getStatusCode(), HttpStatus.OK);
    }

}
