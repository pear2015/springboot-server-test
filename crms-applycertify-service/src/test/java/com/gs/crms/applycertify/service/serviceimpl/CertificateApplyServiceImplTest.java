//package com.gs.crms.applycertify.service.serviceimpl;
//
//import com.gs.crms.applycertify.contract.model.*;
//import com.gs.crms.applycertify.service.datamappers.*;
//import com.gs.crms.applycertify.service.entity.*;
//import com.gs.crms.applycertify.service.entity.dictionary.ApplyPurposeEntity;
//import com.gs.crms.applycertify.service.repository.*;
//
//
//import com.gs.crms.crimenotice.contract.model.AttachmentInfo;
//import com.gs.crms.crimenotice.contract.service.CrimeService;
//
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Matchers.any;
//import static org.mockito.Matchers.anyString;
//import static org.mockito.Mockito.*;
//
///**
// * Created by zhufengjie on 2017/8/30.
// */
//public class CertificateApplyServiceImplTest {
//    @Mock
//    private ApplyInfoRepository applyInfoRepository;
//
//    @Mock
//    private ApplyBasicInfoRepository applyBasicInfoRepository;
//
//    @Mock
//    private ApplyAndNoticeRelationRepository applyAndNoticeRelationRepository;
//
//    @Mock
//    private WaitApportionRepository waitApportionRepository;
//
//    @Mock
//    private ApplyPurposeRepository applyPurposeRepository;
//
//    @Mock
//    private ApplyInfoMapper applyInfoMapper;
//
//    @Mock
//    private ApplyBasicMapper applyBasicMapper;
//
//    @Mock
//    private ApplyAndNoticeRelationMapper applyAndNoticeRelationMapper;
//
//    @Mock
//    private WaitApportionMapper waitApportionMapper;
//
//    @Mock
//    private AttachmentInfoMapper attachmentInfoMapper;
//
//    @Mock
//    private ApplyPurposeMapper applyPurposeMapper;
//
//    @Mock
//    private AttchmentInfoRepository attchmentInfoRepository;
//    @Mock
//    private ApplyAndAnalystRelationRepository applyAndAnalystRelationRepository;
//
//    @Mock
//    private CrimeService crimeService;
//    @Mock
//    private ApplyAndAnalystRelationMapper applyAndAnalystRelationMapper;
//    @InjectMocks
//    private CertificateApplyServiceImpl certificateApplyService;
//
//    @BeforeMethod
//    public void beforeMethod() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @AfterMethod
//    public void afterMethod() {
//        reset(applyInfoRepository);
//        reset(applyBasicInfoRepository);
//        reset(applyAndNoticeRelationRepository);
//        reset(waitApportionRepository);
//        reset(applyPurposeRepository);
//        reset(applyInfoMapper);
//        reset(applyBasicMapper);
//        reset(applyAndNoticeRelationMapper);
//        reset(waitApportionMapper);
//        reset(attachmentInfoMapper);
//        reset(applyPurposeMapper);
//        reset(attchmentInfoRepository);
//        reset(attachmentInfoMapper);
//        reset(applyAndAnalystRelationRepository);
//        reset(applyAndAnalystRelationMapper);
//    }
//
//    /**
//     * 保存申请
//     *
//     * @throws Exception
//     */
////    @Test
////    public void testSaveCertificateApplyInfo() throws Exception {
////        ApplyBasicInfoEntity applyBasicInfoEntity = new ApplyBasicInfoEntity();
////        ApplyBasicInfo applyBasicInfo = new ApplyBasicInfo();
////
////        List<AttachmentEntity> attachmentInfoEntities = new ArrayList<>();
////        attachmentInfoEntities.add(new AttachmentEntity());
////        List<AttachmentInfo> attachmentInfos = new ArrayList<>();
////        AttachmentInfo attachmentInfo = new AttachmentInfo();
////        attachmentInfo.setAttachmentID("");
////        AttachmentInfo attachmentInfo1 = new AttachmentInfo();
////        attachmentInfo1.setAttachmentID("");
////        attachmentInfos.add(attachmentInfo);
////        attachmentInfos.add(attachmentInfo1);
////        AttachmentEntity attachmentInfoEntity = new AttachmentEntity();
////        ApplyAndCriminalRelation applyAndNoticeRelation = new ApplyAndCriminalRelation();
////        applyAndNoticeRelation.setNoticeID("1");
////        ApplyAndCriminalRelationEntity applyAndNoticeRelationEntity = new ApplyAndCriminalRelationEntity();
////        List<ApplyAndCriminalRelation> applyAndNoticeRelationList = new ArrayList<>();
////        ApplyAndCriminalRelation applyAndNoticeRelation1 = new ApplyAndCriminalRelation();
////        applyAndNoticeRelation1.setNoticeID("2");
////        applyAndNoticeRelationList.add(applyAndNoticeRelation);
////        applyAndNoticeRelationList.add(applyAndNoticeRelation1);
////        ApplyInfo applyInfo = new ApplyInfo();
////        applyInfo.setApplyTypeID("1");
////        applyInfo.setAnalysisResultID("2");
////        ApplyInfoEntity applyInfoEntity = new ApplyInfoEntity();
////        WaitApportionEntity apportionEntity = new WaitApportionEntity();
////        List<CrimePersonInfo> crimePersonInfos = new ArrayList<>();
////        //保存基本信息
////        when(applyBasicMapper.modelToEntity(any())).thenReturn(applyBasicInfoEntity);
////        when(applyBasicInfoRepository.save(any(ApplyBasicInfoEntity.class))).thenReturn(applyBasicInfoEntity);
////        //保存申请附件信息
////        when(attchmentInfoRepository.save(any(AttachmentEntity.class))).thenReturn(attachmentInfoEntity);
////        // (1)、查找是否有犯罪记录,有犯罪记录根据类型启动流程，没有不启动;
////        when(crimeService.getCrimePersonListInfoByFirstNameAndLastNameOrCertificateNumber(any(),any(),any())).thenReturn(crimePersonInfos);
////        when(applyInfoMapper.modelToEntity(any())).thenReturn(applyInfoEntity);
////
////        when(applyInfoRepository.save(any(ApplyInfoEntity.class))).thenReturn(applyInfoEntity);
////        when(waitApportionRepository.save(any(WaitApportionEntity.class))).thenReturn(apportionEntity);
////
////        when(applyAndNoticeRelationMapper.modelToEntity(any())).thenReturn(applyAndNoticeRelationEntity);
////        when(applyAndNoticeRelationRepository.save(any(ApplyAndCriminalRelationEntity.class))).thenReturn(applyAndNoticeRelationEntity);
////
////        Assert.assertNotNull(certificateApplyService.saveCertificateApplyInfo(applyInfo,applyBasicInfo,attachmentInfos,applyAndNoticeRelationList));
////        List<CrimePersonInfo> crimePersonInfos1 = new ArrayList<>();
////        crimePersonInfos1.add(new CrimePersonInfo());
////        when(crimeService.getCrimePersonListInfoByFirstNameAndLastNameOrCertificateNumber(any(),any(),any())).thenReturn(crimePersonInfos1);
////        when(applyInfoMapper.modelToEntity(any())).thenReturn(applyInfoEntity);
////
////        when(applyInfoRepository.save(any(ApplyInfoEntity.class))).thenReturn(applyInfoEntity);
////        when(waitApportionRepository.save(any(WaitApportionEntity.class))).thenReturn(apportionEntity);
////        when(applyAndNoticeRelationMapper.modelToEntity(any())).thenReturn(applyAndNoticeRelationEntity);
////        when(applyAndNoticeRelationRepository.save(applyAndNoticeRelationEntity)).thenReturn(applyAndNoticeRelationEntity);
////        List<ApplyAndCriminalRelation> applyAndNoticeRelations1 = new ArrayList<>();
////        Assert.assertEquals(certificateApplyService.saveCertificateApplyInfo(applyInfo,applyBasicInfo,attachmentInfos,applyAndNoticeRelations1),applyInfo);
////        Assert.assertEquals(certificateApplyService.saveCertificateApplyInfo(applyInfo,applyBasicInfo,null,applyAndNoticeRelations1),applyInfo);
////
////        applyInfo.setApplyTypeID("2");
////        Assert.assertNotNull(certificateApplyService.saveCertificateApplyInfo(applyInfo,applyBasicInfo,attachmentInfos,applyAndNoticeRelationList));
////
////    }
//
//    /**
//     * 更新分析申请信息，公告关联
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testUpdateCertificateApplyInfo() throws Exception {
//        CertificateApplyInfo certificateApplyInfo =  new CertificateApplyInfo();
//        ApplyInfo applyInfo = new ApplyInfo();
//        applyInfo.setApplyID("11");
//        certificateApplyInfo.setApplyInfo(applyInfo);
//        ApplyInfoEntity applyInfoEntity = new ApplyInfoEntity();
//        applyInfoEntity.setApplyID("");
//        ApplyAndCriminalRelationEntity applyAndNoticeRelationEntity = new ApplyAndCriminalRelationEntity();
//        List<ApplyAndAnalystRelationEntity> applyAndAnalystRelationEntities = new ArrayList<>();
//        applyAndAnalystRelationEntities.add(new ApplyAndAnalystRelationEntity());
//        ApplyAndCriminalRelation applyAndNoticeRelation = new ApplyAndCriminalRelation();
//        List<ApplyAndCriminalRelation> applyAndNoticeRelations = new ArrayList<>();
//        applyAndNoticeRelations.add(applyAndNoticeRelation);
//        applyAndNoticeRelations.add(new ApplyAndCriminalRelation());
//        certificateApplyInfo.setApplyAndCriminalRelationList(applyAndNoticeRelations);
//        when(applyInfoRepository.getByApplyID(anyString())).thenReturn(applyInfoEntity);
//        when(applyInfoRepository.save(any(ApplyInfoEntity.class))).thenReturn(applyInfoEntity);
//        when(applyAndAnalystRelationRepository.findAllByApplyInfoId(anyString())).thenReturn(applyAndAnalystRelationEntities);
//        when(applyAndNoticeRelationRepository.getByApplyInfoID(anyString())).thenReturn(applyAndNoticeRelationEntity);
//        when(applyAndNoticeRelationRepository.save(any(ApplyAndCriminalRelationEntity.class))).thenReturn(applyAndNoticeRelationEntity);
//        //情况一
//        Assert.assertEquals(certificateApplyService.updateCertificateApplyInfo(certificateApplyInfo), true);
//        //情况二
//        when(applyAndNoticeRelationRepository.getByApplyInfoID(anyString())).thenReturn(null);
//        Assert.assertEquals(certificateApplyService.updateCertificateApplyInfo(certificateApplyInfo), true);
//        //情况四
//        when(applyAndNoticeRelationRepository.getByApplyInfoID(anyString())).thenReturn(applyAndNoticeRelationEntity);
//        List<ApplyAndCriminalRelation> applyAndNoticeRelations1 = new ArrayList<>();
//        certificateApplyInfo.setApplyAndCriminalRelationList(applyAndNoticeRelations1);
//        doNothing().when(applyAndNoticeRelationRepository).delete(any(ApplyAndCriminalRelationEntity.class));
//        Assert.assertEquals(certificateApplyService.updateCertificateApplyInfo(certificateApplyInfo), true);
//        //情况三
//        when(applyInfoRepository.getByApplyID(anyString())).thenReturn(null);
//        Assert.assertEquals(certificateApplyService.updateCertificateApplyInfo(certificateApplyInfo), true);
//    }
//    /**
//     * 更新审核申请信息，公告关联
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testUpdateCertificateApplyInfoForAudit() throws Exception {
//        CertificateApplyInfo certificateApplyInfo =  new CertificateApplyInfo();
//        ApplyInfo applyInfo = new ApplyInfo();
//        applyInfo.setApplyID("");
//        applyInfo.setAuditResultID("2");
//        certificateApplyInfo.setApplyInfo(applyInfo);
//        ApplyInfoEntity applyInfoEntity = new ApplyInfoEntity();
//        ApplyAndAnalystRelationEntity applyAndAnalystRelationEntity = new ApplyAndAnalystRelationEntity();
//        when(applyInfoRepository.getByApplyID(anyString())).thenReturn(applyInfoEntity);
//        when(applyInfoRepository.save(any(ApplyInfoEntity.class))).thenReturn(applyInfoEntity);
//        when(applyAndAnalystRelationRepository.save(any(ApplyAndAnalystRelationEntity.class))).thenReturn(applyAndAnalystRelationEntity);
//
//        Assert.assertEquals(certificateApplyService.updateCertificateApplyInfoForAudit(certificateApplyInfo), true);
//        when(applyInfoRepository.getByApplyID(anyString())).thenReturn(null);
//        Assert.assertEquals(certificateApplyService.updateCertificateApplyInfoForAudit(certificateApplyInfo), true);
//    }
//
//    /**
//     * 通过姓名和身份证ID模糊匹配人口库人员列表
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testGetApplyPersonInfo() throws Exception {
//        List<ApplyBasicInfo> applyBasicInfos = certificateApplyService.getApplyPersonInfo("", "","");
//        Assert.assertNotNull(applyBasicInfos);
//        //抛异常情况
//        List<ApplyBasicInfo> applyBasicInfos1 = certificateApplyService.getApplyPersonInfo("", "","");
//    }
//
//    /**
//     * 获取申请目的列表
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testGetApplyPurposeList() throws Exception {
//        List<ApplyPurposeEntity> applyPurposeEntities = new ArrayList<>();
//        List<ApplyPurpose> applyPurposes = new ArrayList<>();
//
//        when(applyPurposeRepository.getApplyPurposeEntityList()).thenReturn(applyPurposeEntities);
//        when(applyPurposeMapper.entitiestoModels(applyPurposeEntities)).thenReturn(applyPurposes);
//        List<ApplyPurpose> applyPurposeList = certificateApplyService.getApplyPurposeList();
//
//        Assert.assertNotNull(applyPurposeList);
//    }
//
//    /**
//     * 获取申请信息
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testGetApplyInfo() throws Exception {
//        ApplyInfo applyInfo = new ApplyInfo();
//        ApplyInfoEntity applyInfoEntity = new ApplyInfoEntity();
//        applyInfoEntity.setAttchmentID("11");
//        ApplyBasicInfo applyBasicInfo = new ApplyBasicInfo();
//        ApplyAndCriminalRelationEntity applyAndNoticeRelationEntity = new ApplyAndCriminalRelationEntity();
//        applyAndNoticeRelationEntity.setNoticeID("11");
//        List<String> list = new ArrayList<>();
//        List<AttachmentInfo> attachmentInfos = new ArrayList<>();
//
//        when(applyInfoRepository.getByApplyID(anyString())).thenReturn(applyInfoEntity);
//        when(applyInfoMapper.entityToModel(applyInfoEntity)).thenReturn(applyInfo);
//        when(applyBasicMapper.entityToModel(applyInfoEntity.getApplyBasicInfoEntity())).thenReturn(applyBasicInfo);
//        when(attachmentInfoMapper.entitiestoModels(attchmentInfoRepository.findAll(list))).thenReturn(attachmentInfos);
//        when(applyAndNoticeRelationRepository.getByApplyInfoID(anyString())).thenReturn(applyAndNoticeRelationEntity);
//        CertificateApplyInfo certificateApplyInfo = certificateApplyService.getApplyInfo("");
//
//        Assert.assertNotNull(certificateApplyInfo);
//
//        applyInfoEntity.setAttchmentID(null);
//        when(applyInfoRepository.getByApplyID(anyString())).thenReturn(applyInfoEntity);
//        when(applyInfoMapper.entityToModel(applyInfoEntity)).thenReturn(applyInfo);
//        when(applyBasicMapper.entityToModel(applyInfoEntity.getApplyBasicInfoEntity())).thenReturn(applyBasicInfo);
//        when(attachmentInfoMapper.entitiestoModels(attchmentInfoRepository.findAll(list))).thenReturn(attachmentInfos);
//        when(applyAndNoticeRelationRepository.getByApplyInfoID(anyString())).thenReturn(applyAndNoticeRelationEntity);
//        CertificateApplyInfo certificateApplyInfo1 = certificateApplyService.getApplyInfo("");
//        Assert.assertNotNull(certificateApplyInfo1);
//
//    }
//
//    /**
//     * 保存申请人基本信息
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testAddApplyBasicInfo() throws Exception {
//        ApplyBasicInfoEntity applyBasicInfoEntity = new ApplyBasicInfoEntity();
//        ApplyBasicInfo applyBasicInfo = new ApplyBasicInfo();
//
//        when(applyBasicMapper.modelToEntity(applyBasicInfo)).thenReturn(applyBasicInfoEntity);
//        when(applyBasicInfoRepository.save(applyBasicInfoEntity)).thenReturn(applyBasicInfoEntity);
//        String applyBasicID = certificateApplyService.addApplyBasicInfo(applyBasicInfo);
//        Assert.assertNotNull(applyBasicID);
//    }
//
//    /**
//     * 保存申请基本信息
//     */
////    @Test
////    public void testAddApplyInfo() {
////        ApplyInfo applyInfo = new ApplyInfo();
////        ApplyInfoEntity applyInfoEntity = new ApplyInfoEntity();
////        when(applyInfoMapper.modelToEntity(applyInfo)).thenReturn(applyInfoEntity);
////        when(applyInfoRepository.save(applyInfoEntity)).thenReturn(applyInfoEntity);
////        String applyID = certificateApplyService.addApplyInfo(applyInfo, anyString());
////        Assert.assertNotNull(applyID);
////    }
//    /**
//     * 根据申请ID获取到关联的公告ID
//     *
//     */
////    @Test
////    public void testGetNoticeIdByApplyID()throws Exception{
////        //情况1
////        ApplyAndCriminalRelationEntity applyAndNoticeRelationEntity = new ApplyAndCriminalRelationEntity();
////        when(applyAndNoticeRelationRepository.getByApplyInfoID(anyString())).thenReturn(applyAndNoticeRelationEntity);
////        String noticeID = certificateApplyService.getNoticeIDsByApplyID(anyString());
////        Assert.assertEquals(applyAndNoticeRelationEntity.getNoticeID(),noticeID);
////        //情况2
////        when(applyAndNoticeRelationRepository.getByApplyInfoID(anyString())).thenReturn(null);
////        String noticeID1 = certificateApplyService.getNoticeIDsByApplyID(anyString());
////        Assert.assertNotNull(noticeID1);
////
////    }
//
//    /**
//     * 获取待分派的任务队列数量
//     * @throws Exception
//     */
////    @Test
////    public void testGetWaitApportionCount() throws Exception{
////        Integer i = 0;
////        when(waitApportionRepository.getWaitApportionCount()).thenReturn(i);
////        i = certificateApplyService.getWaitApportionCount();
////        Assert.assertNotNull(i);
////    }
//
//    /**
//     * 按数量获取待分派数据
//     * @throws Exception
//     */
////    @Test
////    public void testGetWaitApportionByCount()throws Exception{
////        List<WaitApportion> waitApportions = new ArrayList<>();
////        List<WaitApportionEntity> waitApportionEntities = new ArrayList<>();
////        int count = 1;
////        Sort sort = new Sort("apportionID");
////        org.springframework.data.domain.Pageable page =new PageRequest(0,count,sort);
////        when(waitApportionRepository.fetchQueue(page)).thenReturn(waitApportionEntities);
////        when(waitApportionMapper.entitiestoModels(waitApportionEntities)).thenReturn(waitApportions);
////        waitApportions = certificateApplyService.getWaitApportionByCount(page);
////        Assert.assertNotNull(waitApportions);
////    }
//    /**
//     * 根据任务id删除待派发表中的申请
//     *
//     * @return
//     */
////    @Test
////    public void testDeleteApportion()throws Exception{
////        //情况一
////        List<String> ids = new ArrayList<>();
////        Mockito.doNothing().when(waitApportionRepository).deleteByIds(ids);
////        Boolean rtn = certificateApplyService.deleteApportion(ids);
////        Assert.assertTrue(rtn);
////        //异常情况
////        List<String> ids1 = new ArrayList<>();
////        Mockito.doThrow(RuntimeException.class).when(waitApportionRepository).deleteByIds(ids);
////        Boolean rtn1 = certificateApplyService.deleteApportion(ids1);
////        Assert.assertFalse(rtn1);
////    }
//
//    /**
//     * 分派任务给分析席位
//     * @throws Exception
//     */
////    @Test
////    public void testSaveHadApportionInfo()throws Exception{
////        List<HadApportionInfo> hadApportionInfos = new ArrayList<>();
////        List<HadApportionInfoEntity> hadApportionInfoEntities = new ArrayList<>();
////        HadApportionInfoEntity hadApportionInfoEntity = new HadApportionInfoEntity();
////        hadApportionInfoEntity.setId(UUID.randomUUID().toString());
////        hadApportionInfoEntity.setApplyInfoId("");
////        hadApportionInfoEntities.add(hadApportionInfoEntity);
////        when(hadApportionInfoMapper.modelstoEntities(anyList())).thenReturn(hadApportionInfoEntities);
////        when(hadApportionInfoRepository.save(anyList())).thenReturn(hadApportionInfoEntities);
////        Boolean rtn = certificateApplyService.saveHadApportionInfo(hadApportionInfos);
////        Assert.assertTrue(rtn);
////
////        //异常情况
////        hadApportionInfos.add(new HadApportionInfo());
////        when(hadApportionInfoMapper.modelstoEntities(anyList())).thenReturn(hadApportionInfoEntities);
////        when(hadApportionInfoRepository.save(anyList())).thenThrow(RuntimeException.class);
////        Boolean rtn1 = certificateApplyService.saveHadApportionInfo(hadApportionInfos);
////        Assert.assertFalse(rtn1);
////    }
//
//    /**
//     * 通过分析员ID获取分析任务
//     * @throws Exception
//     */
////    @Test
////    public void testGetAnalysisTaskByAnalystID()throws Exception{
////        //情况一
////        List<ApplyAndAnalystRelationEntity> list = new ArrayList<>();
////        when(applyAndAnalystRelationRepository.findAllByAnalystID(anyString())).thenReturn(list);
////        List<ApplyAndAnalystRelation> applyAndAnalystRelations = new ArrayList<>();
////        when(applyAndAnalystRelationMapper.entitiestoModels(anyList())).thenReturn(applyAndAnalystRelations);
////        ApplyAndAnalystRelation applyAndAnalystRelation = certificateApplyService.getAnalysisTaskByAnalystID("");
////        Assert.assertEquals(applyAndAnalystRelation,null);
////        //情况二
////        List<ApplyAndAnalystRelation> applyAndAnalystRelations1 = new ArrayList<>();
////        applyAndAnalystRelations1.add(new ApplyAndAnalystRelation());
////        when(applyAndAnalystRelationRepository.findAllByAnalystID(anyString())).thenReturn(list);
////        when(applyAndAnalystRelationMapper.entitiestoModels(anyList())).thenReturn(applyAndAnalystRelations1);
////        ApplyAndAnalystRelation applyAndAnalystRelation1 = certificateApplyService.getAnalysisTaskByAnalystID("");
////        Assert.assertNotNull(applyAndAnalystRelation1);
////    }
//
//    /**
//     * 根据申请状态获取申请信息
//     * @throws Exception
//     */
////    @Test
////    public void testGetWaitAuditList()throws Exception{
////        List<ApplyInfo> applyInfos = new ArrayList<>();
////        List<ApplyInfoEntity> applyInfoEntities = new ArrayList<>();
////        when(applyInfoRepository.getAllByApplyStatusID(anyString())).thenReturn(applyInfoEntities);
////        when(applyInfoMapper.entitiestoModels(any())).thenReturn(applyInfos);
////        Assert.assertNotNull(certificateApplyService.getWaitAuditList(""));
////    }
//
//    /**
//     * 获取到待分派申请队列
//     * @throws Exception
//     */
////    @Test
////    public void testGetAllQueueList()throws Exception{
////        List<WaitApportionEntity> waitApportionEntities = new ArrayList<>();
////        List<WaitApportion> list = new ArrayList<>();
////        when(waitApportionRepository.getAllQueueList()).thenReturn(waitApportionEntities);
////        when(waitApportionMapper.entitiestoModels(any())).thenReturn(list);
////        list = certificateApplyService.getAllQueueList();
////        Assert.assertNotNull(list);
////    }
//}
