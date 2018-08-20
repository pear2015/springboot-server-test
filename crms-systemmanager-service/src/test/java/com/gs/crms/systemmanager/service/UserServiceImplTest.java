//package com.gs.crms.systemmanager.service;
//
//
//import com.gs.crms.systemmanager.contract.model.AppUserInfo;
//import com.gs.crms.systemmanager.service.datamappers.UserMapper;
//import com.gs.crms.systemmanager.service.entity.AppUserEntity;
//import com.gs.crms.systemmanager.service.repository.UserRepository;
//import com.gs.crms.systemmanager.service.serviceimpl.UserServiceImpl;
//import org.springframework.util.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.when;
//
//import static org.junit.Assert.*;
//
///**
// * Created by tanjie on 2017/3/14.
// */
//public class UserServiceImplTest {
//
//    @Mock
//    private UserMapper userMapper;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserServiceImpl userServiceimpl;
//
//    @BeforeMethod
//    private void beforeMethod() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    /**
//     * Test save.
//     */
//    @Test
//    public void testSave(){
//        AppUserInfo userInfo = new AppUserInfo();
//        userInfo.setUserId(1);
//        userInfo.setUsername("test");
//        AppUserEntity userEntity = new AppUserEntity();
//        when(userMapper.modelToEntity(userInfo)).thenReturn(userEntity);
//        when(userMapper.entityToModel(userRepository.save(userEntity))).thenReturn(userInfo);
//        AppUserInfo result = userServiceimpl.save(userInfo);
//        Assert.notNull(result);
//    }
//
//    /**
//     * Test find all.
//     */
//    @Test
//    public void testFindAll(){
//       List<AppUserEntity> appUserEntities = new ArrayList<>();
//        appUserEntities.add(new AppUserEntity());
//        List<AppUserInfo> userInfoList= new ArrayList<>();
//        AppUserInfo userInfo = new AppUserInfo();
//        when(userRepository.findAll()).thenReturn(appUserEntities);
//        when(userMapper.entityToModel(any())).thenReturn(userInfo);
//        userInfoList = userServiceimpl.findAll();
//        assertNotNull(userInfoList);
//    }
//}