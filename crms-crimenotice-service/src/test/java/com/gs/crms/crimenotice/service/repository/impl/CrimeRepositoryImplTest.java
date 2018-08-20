//package com.gs.crms.crimenotice.service.repository.impl;
//
//import com.gs.crms.common.enums.AttachmentEntity;
//import com.gs.crms.crimenotice.service.entity.CrimePersonInfoEntity;
//import com.gs.crms.crimenotice.service.entity.NoticeEntity;
//import com.gsafety.springboot.common.entityrepository.QueryEntityFactory;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.*;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.reset;
//import static org.mockito.Mockito.when;
//import static org.testng.Assert.*;
//
///**
// * Created by zhufengjie on 2017/9/12.
// */
//public class CrimeRepositoryImplTest {
//    @Mock
//    private EntityManager entityManager;
//    @Mock
//    private QueryEntityFactory queryEntityFactory;
//    @Mock
//    private CriteriaBuilder criteriaBuilder;
//    @Mock
//    private CriteriaQuery criteriaQuery;
//    @Mock
//    private  Root<CrimePersonInfoEntity> root;
//    @Mock
//    private  Root<NoticeEntity> noticeEntityRoot;
//    @Mock
//    private TypedQuery typedQuery;
//    @Mock
//    private Path path;
//    @Mock
//    private Root<AttachmentEntity> attachmentEntityRoot;
//    @InjectMocks
//    private CrimeRepositoryImpl crimeRepository;
//
//    @BeforeMethod
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @AfterMethod
//    public void tearDown() throws Exception {
//        reset(entityManager);
//        reset(queryEntityFactory);
//        reset(criteriaBuilder);
//        reset(root);
//        reset(typedQuery);
//        reset(noticeEntityRoot);
//        reset(attachmentEntityRoot);
//    }
//
//    @Test
//    public void testGetNoticeListInfoByGroupId() throws Exception {
//        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//        when(criteriaBuilder.createQuery(any())).thenReturn(criteriaQuery);
//        when(criteriaQuery.from(NoticeEntity.class)).thenReturn(noticeEntityRoot);
//        when(entityManager.createQuery(any(CriteriaQuery.class))).thenReturn(typedQuery);
//        List<NoticeEntity> noticeEntities = crimeRepository.getNoticeListInfoByGroupId("");
//        Assert.assertNotNull(noticeEntities);
//    }
//
//    @Test
//    public void testGetCrimePersonListInfoByFirstNameAndLastNameOrCertificateNumber() throws Exception {
//         when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//         when(criteriaBuilder.createQuery(any())).thenReturn(criteriaQuery);
//         when(criteriaQuery.from(CrimePersonInfoEntity.class)).thenReturn(root);
//         when(entityManager.createQuery(any(CriteriaQuery.class))).thenReturn(typedQuery);
//         List<CrimePersonInfoEntity> crimePersonInfoEntities = crimeRepository.getCrimePersonListInfoByFirstNameAndLastNameOrCertificateNumber("1","1","1");
//         Assert.assertNotNull(crimePersonInfoEntities);
//         List<CrimePersonInfoEntity> crimePersonInfoEntities1 = crimeRepository.getCrimePersonListInfoByFirstNameAndLastNameOrCertificateNumber("","1","1");
//         Assert.assertNotNull(crimePersonInfoEntities1);
//         List<CrimePersonInfoEntity> crimePersonInfoEntities2 = crimeRepository.getCrimePersonListInfoByFirstNameAndLastNameOrCertificateNumber("","","1");
//         Assert.assertNotNull(crimePersonInfoEntities2);
//         List<CrimePersonInfoEntity> crimePersonInfoEntities3 = crimeRepository.getCrimePersonListInfoByFirstNameAndLastNameOrCertificateNumber("","","");
//         Assert.assertNotNull(crimePersonInfoEntities3);
//    }
//
//    @Test
//    public void testGetNoticeListInfo() throws Exception {
//        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//        when(criteriaBuilder.createQuery(any())).thenReturn(criteriaQuery);
//        when(criteriaQuery.from(NoticeEntity.class)).thenReturn(noticeEntityRoot);
//        when(entityManager.createQuery(any(CriteriaQuery.class))).thenReturn(typedQuery);
//        List<NoticeEntity> noticeEntities = crimeRepository.getNoticeListInfo("null","null",new Date(),new Date());
//        Assert.assertNotNull(noticeEntities);
//        List<NoticeEntity> noticeEntities1 = crimeRepository.getNoticeListInfo("","null",new Date(),new Date());
//        Assert.assertNotNull(noticeEntities1);
//        List<NoticeEntity> noticeEntities2 = crimeRepository.getNoticeListInfo("","",new Date(),new Date());
//        Assert.assertNotNull(noticeEntities2);
//        List<NoticeEntity> noticeEntities3 = crimeRepository.getNoticeListInfo("","",null,new Date());
//        Assert.assertNotNull(noticeEntities3);
//    }
//
//    @Test
//    public void testGetAttachmentListInfo() throws Exception {
//        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
//        when(criteriaBuilder.createQuery(any())).thenReturn(criteriaQuery);
//        when(criteriaQuery.from(AttachmentEntity.class)).thenReturn(attachmentEntityRoot);
//        when(attachmentEntityRoot.get("attachmentID")).thenReturn(path);
//        when(entityManager.createQuery(any(CriteriaQuery.class))).thenReturn(typedQuery);
//        List<AttachmentEntity> attachmentEntities = crimeRepository.getAttachmentListInfo("1,2");
//        Assert.assertNotNull(attachmentEntities);
//    }
//
//}