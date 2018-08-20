//package com.gs.crms.webapi.controller;
//
//import com.gs.crms.crimenotice.contract.model.monogdb.MongoFile;
//import com.gs.crms.crimenotice.contract.service.MongoService;
//
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.multipart.MultipartFile;
//
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Matchers.any;
//import static org.mockito.Matchers.anyString;
//import static org.mockito.Mockito.reset;
//import static org.mockito.Mockito.when;
//
///**
// * Created by zhufengjie on 2017/9/8.
// */
//public class MongoDBControllerTest {
//    @Mock
//    private MongoService mongoService;
//    @Mock
//    private MultipartFile multipartFile;
//    @Mock
//    private MultipartFile multipartFile1;
//    @InjectMocks
//    private  MongoDBController mongoDBController;
//    @BeforeMethod
//    public void beforeMethod() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @AfterMethod
//    public void afterMethod() {
//        reset(mongoService);
//        reset(multipartFile);
//    }
//
//    /**
//     * 保存单个文件
//     * @throws Exception
//     */
//    @Test
//    public void testStoreSingleFile()throws Exception{
//        MongoFile mongoFile = new MongoFile();
//        String fileId = "1";
//        when(mongoService.storeFile(any())).thenReturn(fileId);
//        ResponseEntity<String> responseEntity = mongoDBController.storeSingleFile(multipartFile);
//        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//        //情况二
//        when(mongoService.storeFile(any())).thenReturn(null);
//        ResponseEntity<String> responseEntity1 = mongoDBController.storeSingleFile(multipartFile);
//        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
//        //情况三
//        when(mongoService.storeFile(any())).thenThrow(IOException.class);
//        ResponseEntity<String> responseEntity2 = mongoDBController.storeSingleFile(multipartFile);
//        Assert.assertEquals(responseEntity2.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    /**
//     *  通过文件id获取文件
//     * @throws Exception
//     */
//    @Test
//    public void testGetSingleFile()throws Exception{
//        byte[] result = null;
//        String id = "";
//        when(mongoService.find(anyString())).thenReturn(result);
//        ResponseEntity<byte[]> responseEntity = mongoDBController.getSingleFile(id);
//        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.BAD_REQUEST);
//        String id1 = "1";
//        when(mongoService.find(anyString())).thenReturn(result);
//        ResponseEntity<byte[]> responseEntity1 = mongoDBController.getSingleFile(id1);
//        Assert.assertNotNull(responseEntity1);
//        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
//    }
//
//    /**
//     * 保存多个文件
//     * @throws Exception
//     */
//    @Test
//    public void testStoreFiles()throws Exception{
//        List<MultipartFile> multipartFiles = new ArrayList<>();
//        multipartFiles.add(multipartFile);
//        multipartFiles.add(multipartFile1);
//        String fileId = "1";
//        StringBuilder stringBuilder = new StringBuilder();
//        when(mongoService.storeFile(any())).thenReturn(fileId);
//        ResponseEntity<Boolean> responseEntity = mongoDBController.storeFiles(multipartFiles);
//        Assert.assertNotNull(responseEntity);
//        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
//        //情况二
//        when(mongoService.storeFile(any())).thenReturn("");
//        ResponseEntity<Boolean> responseEntity1 = mongoDBController.storeFiles(multipartFiles);
//        Assert.assertNotNull(responseEntity1);
//        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
//        //情况三
//        when(mongoService.storeFile(any())).thenThrow(IOException.class);
//        ResponseEntity<Boolean> responseEntity2 = mongoDBController.storeFiles(multipartFiles);
//        Assert.assertNotNull(responseEntity2);
//        Assert.assertEquals(responseEntity2.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
