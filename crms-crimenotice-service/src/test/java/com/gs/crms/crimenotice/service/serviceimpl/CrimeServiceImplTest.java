//package com.gs.crms.crimenotice.service.serviceimpl;
//
//import com.gs.crms.applycertify.contract.service.CertificateApplyService;
//import com.gs.crms.crimenotice.contract.model.*;
//import com.gs.crms.crimenotice.contract.model.search.CrimeAndNoticeExtendInfo;
//import com.gs.crms.crimenotice.service.datamappers.*;
//
//import com.gs.crms.common.enums.AttachmentEntity;
//import com.gs.crms.crimenotice.service.entity.CrimeEntity;
//import com.gs.crms.crimenotice.service.entity.CrimePersonInfoEntity;
//import com.gs.crms.crimenotice.service.entity.NoticeEntity;
//import com.gs.crms.crimenotice.service.entity.dictionary.CourtEntity;
//import com.gs.crms.crimenotice.service.entity.dictionary.GroupEntity;
//import com.gs.crms.crimenotice.service.repository.*;
//import org.junit.Assert;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.mockito.Matchers.*;
//import static org.mockito.Mockito.reset;
//import static org.mockito.Mockito.when;
//
///**
// * Created by: xuqicai
// * Date:2017/8/16
// * Project_Name:crms-server
// * Description:
// */
//public class CrimeServiceImplTest {
//
//    @Mock
//    private CrimeRepository crimeRepository;
//
//    @Mock
//    private CrimePersonRepository crimePersonRepository;
//
//    @Mock
//    private AttchmentRepository attchmentRepository;
//
//    @Mock
//    private NoticeMapper noticeMapper;
//
//    @Mock
//    private CrimeMapper crimeMapper;
//
//    @Mock
//    private CrimePersonMapper crimePersonMapper;
//
//    @Mock
//    private AttachmentMapper attachmentMapper;
//
//    @Mock
//    private GroupRepository groupRepository;
//
//    @Mock
//    private GroupMapper groupMapper;
//
//    @Mock
//    private NoticeRepository noticeRepository;
//
//    @Mock
//    private CourtRepository courtRepository;
//
//    @Mock
//    private CourtMapper courtMapper;
//    @Mock
//    private CertificateApplyService certificateApplyService;
//
//    @InjectMocks
//    private CrimeServiceImpl crimeService;
//
//
//    @BeforeMethod
//    public void beforeMethod() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @AfterMethod
//    public void afterMethod() {
//        reset(crimeRepository);
//        reset(crimePersonRepository);
//        reset(attchmentRepository);
//        reset(noticeMapper);
//        reset(crimeMapper);
//        reset(crimePersonMapper);
//        reset(attachmentMapper);
//        reset(groupRepository);
//        reset(groupMapper);
//        reset(noticeRepository);
//        reset(courtRepository);
//        reset(courtMapper);
//        reset(certificateApplyService);
//    }
//
//    @Test
//    public void testAddCrimeAndNoticeInfo() throws Exception {
//        // 1:
//        CrimeInfo crimeInfo = new CrimeInfo();
//        CrimeEntity crimeEntity = new CrimeEntity();
//        when(crimeRepository.save(any(CrimeEntity.class))).thenReturn(crimeEntity);
//        // 2:
//        CrimePersonInfo crimePersonInfo = new CrimePersonInfo();
//        CrimePersonInfoEntity crimePersonInfoEntity = new CrimePersonInfoEntity();
//        when(crimePersonRepository.save(any(CrimePersonInfoEntity.class))).thenReturn(crimePersonInfoEntity);
//
//        List<AttachmentInfo> attachmentInfos = new ArrayList<>();
//        attachmentInfos.add(new AttachmentInfo());
//        attachmentInfos.add(new AttachmentInfo());
//        AttachmentEntity attachmentEntity = new AttachmentEntity();
//
//        when(attchmentRepository.save(any(AttachmentEntity.class))).thenReturn(attachmentEntity);
//        NoticeEntity noticeEntity = new NoticeEntity();
//        NoticeInfo noticeInfo = new NoticeInfo();
//        when(noticeRepository.save(any(NoticeEntity.class))).thenReturn(noticeEntity);
//        boolean result = crimeService.addCrimeAndNoticeInfo(crimeInfo,crimePersonInfo,noticeInfo,attachmentInfos);
//        Assert.assertEquals(result,true);
//    }
//
//    /**
//     *根据姓名模糊匹配或者证件号码查询犯罪人员基本信息
//     * @throws Exception
//     */
//    @Test
//    public void testGetCrimePersonListInfoByFirstNameAndLastNameOrCertificateNumber() throws Exception {
//        List<CrimePersonInfo> crimePersonInfoList =new ArrayList<>();
//        List<CrimePersonInfoEntity> crimePersonInfoEntities = new ArrayList<>();
//        when(crimeRepository.getCrimePersonListInfoByFirstNameAndLastNameOrCertificateNumber(anyString(),anyString(),anyString())).thenReturn(crimePersonInfoEntities);
//        when(crimePersonMapper.entitiestoModels(crimePersonInfoEntities)).thenReturn(crimePersonInfoList);
//        crimePersonInfoList = crimeService.getCrimePersonListInfoByFirstNameAndLastNameOrCertificateNumber("xuqicai","123456","");
//        Assert.assertNotNull(crimePersonInfoList);
//    }
//
//    /**
//     *获取组相关信息
//     * @throws Exception
//     */
//    @Test
//    public void testGetGroupInfo() throws Exception {
//        List<GroupEntity> groupEntities = new ArrayList<>();
//        List<GroupInfo> groupInfoList = new ArrayList<>();
//        when(groupRepository.findAll()).thenReturn(groupEntities);
//        when(groupMapper.entitiestoModels(groupEntities)).thenReturn(groupInfoList);
//        groupInfoList = crimeService.getGroupInfo();
//        Assert.assertNotNull(groupInfoList);
//    }
//
//    /**
//     * 根据公告组ID查询同组的公告信息
//     * @throws Exception
//     */
//    @Test
//    public void testGetNoticeListInfoByGroup() throws Exception {
//        List<NoticeEntity> noticeEntities = new ArrayList<>();
//        List<NoticeInfo> noticeInfoList = new ArrayList<>();
//        when(crimeRepository.getNoticeListInfoByGroupId(anyObject())).thenReturn(noticeEntities);
//        when(noticeMapper.entitiestoModels(noticeEntities)).thenReturn(noticeInfoList);
//        noticeInfoList = crimeService.getNoticeListInfoByGroup("1");
//        Assert.assertNotNull(noticeInfoList);
//    }
//
//    /**
//     * 公告查询以及查询公告相关的犯罪信息
//     * @throws Exception
//     */
//    @Test
//    public void testGetCrimeAndNoticeExtendInfo() throws Exception {
//        Date enteringBeginTime = new Date();
//        Date enteringEndTime =  new Date();
//
//        List<NoticeEntity> noticeEntities = new ArrayList<>();
//        NoticeEntity noticeEntity = new NoticeEntity();
//        noticeEntities.add(noticeEntity);
//        noticeEntity.setAttchmentID("");
//        NoticeInfo noticeInfo = new NoticeInfo();
//
//        CrimeInfo crimeInfo = new CrimeInfo();
//        CrimePersonInfo crimePersonInfo = new CrimePersonInfo();
//        List<AttachmentEntity> attachmentEntities = new ArrayList<>();
//        List<AttachmentInfo> attachmentInfos = new ArrayList<>();
//        List<CrimeAndNoticeExtendInfo> noticeInfoList = new ArrayList<>();
//        when(crimeRepository.getNoticeListInfo(anyObject(),anyObject(),anyObject(),anyObject())).thenReturn(noticeEntities);
//        when(noticeMapper.entityToModel(any())).thenReturn(noticeInfo);
//        when(crimeMapper.entityToModel(any())).thenReturn(crimeInfo);
//        when(crimePersonMapper.entityToModel(any())).thenReturn(crimePersonInfo);
//        when(crimeRepository.getAttachmentListInfo(anyString())).thenReturn(attachmentEntities);
//        when(attachmentMapper.entitiestoModels(anyList())).thenReturn(attachmentInfos);
//
//        noticeInfoList  = crimeService.getCrimeAndNoticeExtendInfo("1","",enteringBeginTime,enteringEndTime);
//        Assert.assertNotNull(noticeInfoList);
//     }
//
//    /**
//     * 获取法院信息
//     * @throws Exception
//     */
//    @Test
//    public void testGetCourtList() throws Exception {
//        List<CourtEntity> courtEntities = new ArrayList<>();
//        List<CourtInfo> courtInfoList = new ArrayList<>();
//        when(courtRepository.getCourtEntityList()).thenReturn(courtEntities);
//        when(courtMapper.entitiestoModels(courtEntities)).thenReturn(courtInfoList);
//        courtInfoList = crimeService.getCourtList();
//        Assert.assertNotNull(courtInfoList);
//    }
//
//    /**
//     * 通过身份证和姓名查询到犯罪公告和犯罪信息
//     * @throws Exception
//     */
//    @Test
//    public void testGetCrimeAndNoticeExtendInfoByCertificateIdAndFirstNameAndLastName()throws Exception{
//        List<CrimeAndNoticeExtendInfo> crimeAndNoticeExtendInfoList = new ArrayList<>();
//        List<CrimePersonInfoEntity> crimePersonInfoEntities = new ArrayList<>();
//        CrimePersonInfoEntity crimePersonInfoEntity =  new CrimePersonInfoEntity();
//        crimePersonInfoEntity.setCrimePersonID("");
//        crimePersonInfoEntities.add(crimePersonInfoEntity);
//        CrimeInfo crimeInfo = new CrimeInfo();
//        List<AttachmentInfo> attachmentInfos = new ArrayList<>();
//        when(crimePersonRepository.getByCertificateNumberLikeAndFirstNameLikeAndLastNameLike(anyString(),anyString(),anyString())).thenReturn(crimePersonInfoEntities);
//        NoticeInfo noticeInfo = new NoticeInfo();
//        NoticeEntity noticeEntity = new NoticeEntity();
//        noticeEntity.setCrimeID("11");
//        noticeEntity.setAttchmentID("2,1");
//        when(noticeRepository.getByCrimePersonID(anyString())).thenReturn(noticeEntity);
//        when(noticeMapper.entityToModel(noticeEntity)).thenReturn(noticeInfo);
//        when(crimeMapper.entityToModel(crimeRepository.getByCrimeID(anyString()))).thenReturn(crimeInfo);
//        when(attachmentMapper.entitiestoModels(attchmentRepository.findAll(anyList()))).thenReturn(attachmentInfos);
//
//        crimeAndNoticeExtendInfoList = crimeService.getCrimeAndNoticeExtendInfo(anyString(),anyString(),anyString());
//        Assert.assertNotNull(crimeAndNoticeExtendInfoList);
//    }
//
//    /**
//     *  通过申请ID匹配犯罪公告列表
//     * @throws Exception
//     */
//    @Test
//    public void testGetCrimeAndNoticeExtendInfoByApplyID()throws Exception{
//        String noticeStr = "";
//        String noticeStr1 = "1,2";
//        NoticeEntity notice = new NoticeEntity();
//        notice.setAttchmentID("11");
//        NoticeInfo noticeInfo = new NoticeInfo();
//        CrimeEntity crimeEntity = new CrimeEntity();
//        CrimeInfo crimeInfo = new CrimeInfo();
//        List<AttachmentEntity> attachmentEntities = new ArrayList<>();
//        List<AttachmentInfo> attachmentInfos = new ArrayList<>();
//        List<CrimePersonInfoEntity> crimePersonInfoEntities = new ArrayList<>();
//        CrimePersonInfoEntity crimePersonInfoEntity = new CrimePersonInfoEntity();
//        crimePersonInfoEntities.add(crimePersonInfoEntity);
//
//        CrimePersonInfo crimePersonInfo = new CrimePersonInfo();
//            //notice为空
//            when(certificateApplyService.getNoticeIDsByApplyID(any())).thenReturn(noticeStr);
//            when(noticeRepository.getByNoticeID(anyString())).thenReturn(notice);
//            when(crimeRepository.getByCrimeID(anyString())).thenReturn(crimeEntity);
//            when(crimePersonRepository.getByCrimePersonID(any())).thenReturn(crimePersonInfoEntity);
//            when(attchmentRepository.findAll(anyList())).thenReturn(attachmentEntities);
//            when(noticeMapper.entityToModel(any())).thenReturn(noticeInfo);
//            when(crimeMapper.entityToModel(any())).thenReturn(crimeInfo);
//            when(crimePersonMapper.entityToModel(any())).thenReturn(crimePersonInfo);
//            when(attachmentMapper.entitiestoModels(anyList())).thenReturn(attachmentInfos);
//            Assert.assertEquals(crimeService.getCrimeAndNoticeExtendInfoByApplyID(""), null);
//            //notice不为空
//            when(certificateApplyService.getNoticeIDsByApplyID(any())).thenReturn(noticeStr1);
//            when(noticeRepository.getByNoticeID(anyString())).thenReturn(notice);
//            when(crimeRepository.getByCrimeID(any())).thenReturn(crimeEntity);
//            when(crimePersonRepository.getByCrimePersonID(anyString())).thenReturn(crimePersonInfoEntity);
//            when(attchmentRepository.findAll(anyList())).thenReturn(attachmentEntities);
//            when(noticeMapper.entityToModel(any())).thenReturn(noticeInfo);
//            when(crimeMapper.entityToModel(any())).thenReturn(crimeInfo);
//            when(crimePersonMapper.entityToModel(any())).thenReturn(crimePersonInfo);
//            when(attachmentMapper.entitiestoModels(anyList())).thenReturn(attachmentInfos);
//            Assert.assertNotNull(crimeService.getCrimeAndNoticeExtendInfoByApplyID(""));
//
//    }
//}