//package com.gs.crms.webapi.controller;
//
//import com.gs.crms.systemmanager.contract.model.AppUserInfo;
//import com.gs.crms.systemmanager.contract.service.UserService;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.reset;
//import static org.mockito.Mockito.when;
//
///**
// * Created by xuzhen on 2017/3/17.
// */
//public class AppUserControllerTest {
//
//    @Mock
//    UserService userService;
//    @InjectMocks
//    AppUserController appUserController;
//
//    @BeforeMethod
//    public void beforeMethod() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @AfterMethod
//    public void afterMethod() {
//    reset(userService);
//    }
//
//    /**
//     * 添加用户信息
//     */
//    @Test
//    public void testCreate() {
//        AppUserInfo appUserInfo = new AppUserInfo();
//       when(userService.save(any(AppUserInfo.class))).thenReturn(appUserInfo);
//       ResponseEntity<AppUserInfo> responseEntity = appUserController.create("");
//       Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//    }
//
//    /**
//     * 查询所有用户信息
//     */
//    @Test
//    public void testFindAll() {
//        ResponseEntity<List<AppUserInfo>> responseEntity = appUserController.findAll();
//        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
//    }
//}