package com.gs.crms.crimenotice.service.serviceimpl;

import com.gs.crms.applycertify.contract.model.ApplyAndAnalystRelation;
import com.gs.crms.applycertify.contract.model.ApplyBasicInfo;
import com.gs.crms.applycertify.contract.service.CertificateApplyService;
import com.gs.crms.common.model.AttachmentInfo;
import com.gs.crms.common.model.Error;
import com.gs.crms.common.model.ReturnBase;
import com.gs.crms.common.repository.AttachmentRepository;
import com.gs.crms.common.utils.PageUtil;
import com.gs.crms.common.utils.TimeUtil;
import com.gs.crms.common.utils.VariableUtil;
import com.gs.crms.crimenotice.contract.model.CrimeInfo;
import com.gs.crms.crimenotice.contract.model.CrimePersonInfo;
import com.gs.crms.crimenotice.contract.model.CrimeRecordPrint;
import com.gs.crms.crimenotice.contract.model.NoticeInfo;
import com.gs.crms.crimenotice.contract.model.search.CrimePersonSearchInfo;
import com.gs.crms.crimenotice.contract.model.search.CrimeSearchInfo;
import com.gs.crms.crimenotice.contract.service.CrimeSearchService;
import com.gs.crms.crimenotice.contract.service.CrimeService;
import com.gs.crms.crimenotice.service.datamappers.*;
import com.gs.crms.crimenotice.service.entity.CrimePersonInfoEntity;
import com.gs.crms.crimenotice.service.entity.MergedCriminalEntity;
import com.gs.crms.crimenotice.service.entity.NoticeEntity;
import com.gs.crms.crimenotice.service.repository.*;
import com.gsafety.springboot.common.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * Created by zhengyali on 2017/10/31.
 * 犯罪公告相关的搜索
 */
@Service
@Transactional
public class CrimeSearchServiceImpl implements CrimeSearchService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CrimePersonRepository crimePersonRepository;

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private CrimePersonMapper crimePersonMapper;

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private CertificateApplyService certificateApplyService;
    @Autowired
    private CrimeService crimeService;

    @Autowired
    private CrimeMapper crimeMapper;
    @Autowired
    private CrimeRepository crimeRepository;

    @Autowired
    private MergedCriminalRepsitory mergedCriminalRepsitory;

    @Value("${citizen.certificateType}")
    private String certificateType;//证书类型

    @Value("${citizen.isByCrime}")
    private boolean isByCrime;//查询模式
    @Value("${search.noticeStatusList}")
    private String noticeStatusList;//公告的状态列表

    /**
     * 公告录入人员回填 数据列表
     * 先从犯罪库查询数据 若没有数据再从人口库查询数据
     *
     * @param crimePersonSearchInfo
     * @return
     */
    @Override
    public ReturnBase<List<CrimePersonInfo>> getCrimePersonInfoBySearch(CrimePersonSearchInfo crimePersonSearchInfo, String backFillCriminalId) {
        ReturnBase<List<CrimePersonInfo>> returnBase = new ReturnBase<>();
        Page<CrimePersonInfoEntity> page = getCrimePersonPageList(crimePersonSearchInfo, backFillCriminalId);
        try {
            if (page != null) {
                List<CrimePersonInfo> list = crimePersonMapper.entitiestoModels(page.getContent());
                returnBase.setSuccess(true);
                returnBase.setData(list);
                returnBase.setTotalPage(page.getTotalPages());
                returnBase.setTotalCount(page.getTotalElements());
                if (list.isEmpty()) {
                    // 调用人口库查询人员数据。
                }
            }
        } catch (Exception e) {
            logger.error("getCrimePersonInfoBySearch error", e);
            returnBase.setSuccess(false);
            returnBase.setError(new Error(500, VariableUtil.SERVER_INNER_EXCEPTION));
        }
        return returnBase;
    }

    /***
     * 根据查询条件查询犯罪人员的基本信息
     * @param crimePersonSearchInfo
     * @return
     */
    private Page<CrimePersonInfoEntity> getCrimePersonPageList(CrimePersonSearchInfo crimePersonSearchInfo, String backFillCriminalId) {
        PageRequest pageRequest = new PageRequest(crimePersonSearchInfo.getPages(), crimePersonSearchInfo.getPageSize());
        Specification<CrimePersonInfoEntity> specification = (Root<CrimePersonInfoEntity> entityRoot, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
          List<Predicate> predicates =getCrimePersonPageListPredicate(crimePersonSearchInfo, backFillCriminalId,entityRoot,cb);
            Predicate[] predicate = new Predicate[predicates.size()];
            return query.where(predicates.toArray(predicate)).orderBy(cb.desc(entityRoot.get("enteringTime").as(Date.class))).getRestriction();
        };
        return crimePersonRepository.findAll(specification, pageRequest);
    }

    /**
     * 根据查询条件查询犯罪人员的基本信息
     * @param crimePersonSearchInfo
     * @param backFillCriminalId
     * @param entityRoot
     * @param cb
     * @return
     */
    private  List<Predicate>getCrimePersonPageListPredicate(CrimePersonSearchInfo crimePersonSearchInfo,String backFillCriminalId,Root<CrimePersonInfoEntity> entityRoot, CriteriaBuilder cb){
        List<Predicate> predicates = new ArrayList<>();
        Predicate predicateOne = cb.equal(entityRoot.get("firstName"), crimePersonSearchInfo.getFirstName());
        Predicate predicateTwo = cb.equal(entityRoot.get("lastName"), crimePersonSearchInfo.getLastName());
        if (StringUtils.isBlank(crimePersonSearchInfo.getCertificateNumber()) || StringUtils.isBlank(crimePersonSearchInfo.getCertificateType())) {
            predicates.add(cb.and(predicateOne, predicateTwo));
        } else {
            Predicate predicateThree = cb.equal(entityRoot.get("certificateType"), crimePersonSearchInfo.getCertificateType());
            Predicate predicateFour = cb.equal(entityRoot.get(VariableUtil.CERTIFICATENUMBER), crimePersonSearchInfo.getCertificateNumber());
            predicates.add(cb.or(cb.and(predicateOne, predicateTwo), cb.and(predicateThree, predicateFour)));
        }
        if (StringUtils.isNotBlank(backFillCriminalId)) {
            // 排除回填外的罪犯。
            predicates.add(cb.notEqual(entityRoot.get("crimePersonId"), backFillCriminalId));
        }
        if (StringUtils.isNotBlank(crimePersonSearchInfo.getSexId())) {
            predicates.add(cb.equal(entityRoot.get("sexId"), crimePersonSearchInfo.getSexId()));
        }
        Predicate predicateFive = cb.equal(entityRoot.get("isActive"), "1");
        predicates.add(predicateFive);
        return predicates;
    }

    /**
     * 查询历史公告列表
     *
     * @param crimeSearchInfo
     * @return
     */
    @Override
    public ReturnBase<List<NoticeInfo>> findNoticeListByHistory(CrimeSearchInfo crimeSearchInfo) {
        Page<NoticeEntity> page = getNoticePageHistory(crimeSearchInfo);
        return noticePageChange(page);
    }

    /**
     * 查询公告的历史记录
     *
     * @param crimeSearchModel
     * @return
     */
    private Page<NoticeEntity> getNoticePageHistory(CrimeSearchInfo crimeSearchModel) {
        PageRequest pageRequest = PageUtil.initPage(crimeSearchModel.getPages(), crimeSearchModel.getPageSize());
        Specification<NoticeEntity> specification = (Root<NoticeEntity> entityRoot, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = getNoticePageHistoryExChange(crimeSearchModel, entityRoot, cb);
            Predicate[] predicate = new Predicate[predicates.size()];
            return query.where(predicates.toArray(predicate)).orderBy(cb.desc(entityRoot.get(VariableUtil.PRIORITY).as(String.class)), cb.desc(entityRoot.get(VariableUtil.NOTICECREATETIME).as(Date.class))).getRestriction();
        };
        return noticeRepository.findAll(specification, pageRequest);
    }

    /**
     * 查询公告历史记录Predicate 转化
     *
     * @param crimeSearchModel
     * @param entityRoot
     * @param cb
     * @return
     */
    private List<Predicate> getNoticePageHistoryExChange(CrimeSearchInfo crimeSearchModel, Root<NoticeEntity> entityRoot, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isBlank(crimeSearchModel.getCourtId())) {
            predicates.add(cb.equal(entityRoot.get(VariableUtil.COURTID), crimeSearchModel.getCourtId()));
        }
        if (!StringUtils.isBlank(crimeSearchModel.getNoticeNumber())) {
            predicates.add(cb.like(entityRoot.get(VariableUtil.NOTICENUMBER), "%" + crimeSearchModel.getNoticeNumber() + "%"));
        }
        if (!StringUtils.isBlank(crimeSearchModel.getCertificateNumber())) {
            predicates.add(cb.like(entityRoot.get(VariableUtil.CRIME_PERSON_INFO_ENTITY).get(VariableUtil.CERTIFICATENUMBER), "%" + crimeSearchModel.getCertificateNumber() + "%"));
        }
        if (crimeSearchModel.isSearchByEnterPersonId()) {
            predicates.add(cb.equal(entityRoot.get(VariableUtil.ENTERPERSONID), crimeSearchModel.getEnterPersonId()));
        }
        if (!StringUtils.isBlank(crimeSearchModel.getEnterPersonName())) {
            predicates.add(cb.like(entityRoot.get("enteringPersonName"), "%" + crimeSearchModel.getEnterPersonName() + "%"));
        }
        if (StringUtils.isNoneBlank(crimeSearchModel.getNoticeStatus())) {
            predicates.add(cb.equal(entityRoot.get(VariableUtil.STATUS), crimeSearchModel.getNoticeStatus()));
        } else {
            String[] statusList = noticeStatusList.split(",");
            CriteriaBuilder.In<String> in = cb.in(entityRoot.get(VariableUtil.STATUS));
            for (String id : statusList) {
                in.value(id);
            }
            predicates.add(in);
        }
        if (crimeSearchModel.getStartNoticeCreateTime() != null) {
            predicates.add(cb.greaterThanOrEqualTo(entityRoot.get(VariableUtil.NOTICECREATETIME), crimeSearchModel.getStartNoticeCreateTime()));
        }
        if (crimeSearchModel.getEndNoticeCreateTime() != null) {
            predicates.add(cb.lessThanOrEqualTo(entityRoot.get(VariableUtil.NOTICECREATETIME), crimeSearchModel.getEndNoticeCreateTime()));
        }
        return predicates;
    }

    /**
     * 对查询后的分页列表进行处理
     *
     * @param page
     * @return
     */
    private ReturnBase<List<NoticeInfo>> noticePageChange(Page<NoticeEntity> page) {
        ReturnBase<List<NoticeInfo>> returnBase = new ReturnBase<>();
        try {
            if (page != null) {
                List<NoticeInfo> list = noticeMapper.entitiestoModels(page.getContent());
                returnBase.setSuccess(true);
                returnBase.setData(list);
                returnBase.setTotalPage(page.getTotalPages());
                returnBase.setTotalCount(page.getTotalElements());
            }
        } catch (Exception e) {
            logger.error("noticePageChange error", e);
            returnBase.setSuccess(false);
            returnBase.setError(new Error(500, "Server Inner Exception"));
        }
        return returnBase;
    }

    /**
     * 获取当前待审核的列表
     *
     * @param pages
     * @param pageSize
     * @param auditorId
     * @return
     */
    @Override
    public ReturnBase<List<NoticeInfo>> getAuditorTaskList(int pages, int pageSize, String auditorId, String businessType) {
        return getReplyOrAuditorNoticeList(pages, pageSize, "", auditorId, businessType);
    }


    /**
     * 获取申请任务列表
     *
     * @param applyAndAnalystRelationList
     * @return
     */
    private ReturnBase<List<NoticeInfo>> getApplicationTaskList(ReturnBase<List<ApplyAndAnalystRelation>> applyAndAnalystRelationList) {
        ReturnBase<List<NoticeInfo>> returnBase = new ReturnBase<>();
        List<NoticeInfo> list = new ArrayList<>();

        if (applyAndAnalystRelationList != null) {
            List<ApplyAndAnalystRelation> relationEntities = applyAndAnalystRelationList.getData();
            if (!relationEntities.isEmpty()) {
                relationEntities.forEach(i -> {
                    NoticeEntity noticeEntity = noticeRepository.findOneByNoticeId(i.getApplyInfoId());
                    if (noticeEntity != null) {
                        NoticeInfo noticeInfo=noticeMapper.entityToModel(noticeEntity);
                        noticeInfo.setApportionTime(i.getCreateTime());
                        list.add(noticeInfo);
                    }
                });
            }
            returnBase.setData(list);
            returnBase.setSuccess(true);
            returnBase.setTotalPage(applyAndAnalystRelationList.getTotalPage());
            returnBase.setTotalCount(applyAndAnalystRelationList.getTotalCount());
        }
        return returnBase;
    }

    /**
     * 查询犯罪嫌疑人
     *
     * @param certificateType
     * @param certificateNumber
     * @param firstName
     * @param lastName
     * @return
     */
    @Override
    public List<CrimePersonInfo> findPersonalCrimeInfoList(String certificateType, String certificateNumber, String firstName, String lastName) {
        List<CrimePersonInfoEntity> crimePersonInfoEntityList = crimePersonRepository.findAllBySearch(certificateType, certificateNumber, firstName, lastName);
        return crimePersonMapper.entitiestoModels(crimePersonInfoEntityList);
    }

    /**
     * 获取待分析列表
     *
     * @param pages
     * @param pageSize
     * @param filerId
     * @param businessType
     * @return
     */
    @Override
    public ReturnBase<List<NoticeInfo>> getAnalystTaskList(int pages, int pageSize, String filerId, String businessType) {
        return getReplyOrAuditorNoticeList(pages, pageSize, filerId, "", businessType);
    }

    /**
     * 获取已审核列表
     *
     * @param crimeSearchInfo
     * @return
     */
    @Override
    public ReturnBase<List<NoticeInfo>> getHasAuditorTaskList(CrimeSearchInfo crimeSearchInfo) {
        Page<NoticeEntity> page = getHasAuditNoticePage(crimeSearchInfo);
        return noticePageChange(page);
    }

    /**
     * 查询归档员在审核中的公告
     *
     * @param crimeSearchInfo
     * @return
     */
    @Override
    public ReturnBase<List<NoticeInfo>> findNoticeListByWaitAudit(CrimeSearchInfo crimeSearchInfo) {
        Page<NoticeEntity> page = getWaitAuditNoticePage(crimeSearchInfo);
        return noticePageChange(page);
    }

    /**
     * 查询可编辑的优先级列表
     *
     * @param crimeSearchInfo
     * @return
     */
    @Override
    public ReturnBase<List<NoticeInfo>> findNoticeListByPriority(CrimeSearchInfo crimeSearchInfo) {
        Page<NoticeEntity> page = getEditPriorityNoticePage(crimeSearchInfo);
        return noticePageChange(page);
    }

    /**
     * 根据犯罪嫌疑人Id查询犯罪嫌疑人 公告和犯罪信息
     * 用于打印犯罪记录等
     * 如果这个罪犯被合并完成了，那么打印目标罪犯的公告；
     * 没有打印自己的公告
     *
     * @param crimePersonId
     * @return
     */
    @Override
    public List<CrimeRecordPrint> findCriminalAndNoticeByCrimePersonId(String crimePersonId) {
        CrimePersonInfoEntity crimePersonInfoEntity = crimePersonRepository.findOneByCrimePersonId(crimePersonId);
        List<CrimeRecordPrint> list = new ArrayList<>();
        String criminalId = "";
        if (crimePersonInfoEntity == null) {
            return list;
        }
        //获取50年内的公告
        Date endDate = new Date();
        Date startDate = TimeUtil.getLateLyDate(50);
        if (StringUtils.equals("0", crimePersonInfoEntity.getIsActive())) {
            MergedCriminalEntity mergedCriminalEntity = mergedCriminalRepsitory.findByCriminalId(crimePersonInfoEntity.getCrimePersonId());
            if (mergedCriminalEntity != null) {
                criminalId = mergedCriminalEntity.getObjectCriminalId();
            }
        } else {
            criminalId = crimePersonInfoEntity.getCrimePersonId();
        }
        List<NoticeEntity> noticeEntityList = noticeRepository.findAllByCrimePersonIdAndTime(criminalId, startDate, endDate);
        if (!CollectionUtils.isEmpty(noticeEntityList)) {
            noticeEntityList.stream().forEach(noticeEntity -> {
                CrimeRecordPrint crimeRecordPrint = new CrimeRecordPrint();
                if (noticeEntity.getCrimeEntity() != null) {
                    crimeRecordPrint.setNoticeInfo(noticeMapper.entityToModel(noticeEntity));
                    crimeRecordPrint.setCrimeInfo(crimeMapper.entityToModel(noticeEntity.getCrimeEntity()));
                    crimeRecordPrint.setAttachmentInfo(crimeService.findAttachmentByIds(noticeEntity.getAttchmentId()));
                }
                list.add(crimeRecordPrint);
            });
        }
        return list;
    }
    /**
     * 查询可编辑的优先级列表
     *
     * @param crimeSearchInfo
     * @return
     */
    private Page<NoticeEntity> getEditPriorityNoticePage(CrimeSearchInfo crimeSearchInfo) {
        PageRequest pageRequest = PageUtil.initPage(crimeSearchInfo.getPages(), crimeSearchInfo.getPageSize());
        Specification<NoticeEntity> specification = (Root<NoticeEntity> entityRoot, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = priorityPredicatesExChange(crimeSearchInfo, entityRoot, cb);
            Predicate[] predicate = new Predicate[predicates.size()];
            return query.where(predicates.toArray(predicate)).orderBy(cb.desc(entityRoot.get(VariableUtil.PRIORITY).as(String.class)), cb.asc(entityRoot.get(VariableUtil.NOTICECREATETIME).as(Date.class))).getRestriction();
        };
        return noticeRepository.findAll(specification, pageRequest);
    }

    /**
     * 优先级 Predicate
     * @param crimeSearchInfo
     * @param entityRoot
     * @param cb
     * @return
     */
    private List<Predicate> priorityPredicatesExChange(CrimeSearchInfo crimeSearchInfo, Root<NoticeEntity> entityRoot, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isBlank(crimeSearchInfo.getCourtId())) {
            predicates.add(cb.equal(entityRoot.get(VariableUtil.COURTID), crimeSearchInfo.getCourtId()));
        }
        if (!StringUtils.isBlank(crimeSearchInfo.getNoticeNumber())) {
            predicates.add(cb.like(entityRoot.get(VariableUtil.NOTICENUMBER), "%" + crimeSearchInfo.getNoticeNumber() + "%"));
        }
        if (!StringUtils.isBlank(crimeSearchInfo.getCertificateNumber())) {
            predicates.add(cb.like(entityRoot.get(VariableUtil.CRIME_PERSON_INFO_ENTITY).get(VariableUtil.CERTIFICATENUMBER), "%" + crimeSearchInfo.getCertificateNumber() + "%"));
        }
        if (crimeSearchInfo.isSearchByEnterPersonId()) {
            predicates.add(cb.equal(entityRoot.get(VariableUtil.ENTERPERSONID), crimeSearchInfo.getEnterPersonId()));
        }

        if (!StringUtils.isBlank(crimeSearchInfo.getEnterPersonName())) {
            predicates.add(cb.like(entityRoot.get("enterPersonName"), "%" + crimeSearchInfo.getEnterPersonName() + "%"));
        }
        //查询可编辑优先级的数据
        Predicate predicateOne = cb.equal(entityRoot.get(VariableUtil.STATUS), "1");
        Predicate predicateTwo = cb.equal(entityRoot.get(VariableUtil.STATUS), "2");
        predicates.add(cb.or(predicateOne, predicateTwo));

        if (crimeSearchInfo.getStartNoticeCreateTime() != null) {
            predicates.add(cb.greaterThanOrEqualTo(entityRoot.get(VariableUtil.NOTICECREATETIME), crimeSearchInfo.getStartNoticeCreateTime()));
        }
        if (crimeSearchInfo.getEndNoticeCreateTime() != null) {
            predicates.add(cb.lessThanOrEqualTo(entityRoot.get(VariableUtil.NOTICECREATETIME), crimeSearchInfo.getEndNoticeCreateTime()));
        }

        return predicates;
    }

    /**
     * 获取归档员审核中的列表
     *
     * @param crimeSearchInfo
     * @return
     */
    private Page<NoticeEntity> getWaitAuditNoticePage(CrimeSearchInfo crimeSearchInfo) {
        PageRequest pageRequest = new PageRequest(crimeSearchInfo.getPages(), crimeSearchInfo.getPageSize());
        Specification<NoticeEntity> specification = (Root<NoticeEntity> entityRoot, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = noticePredicatesExChange(crimeSearchInfo, entityRoot, cb);
            Predicate[] predicate = new Predicate[predicates.size()];
            return query.where(predicates.toArray(predicate)).orderBy(cb.desc(entityRoot.get("priority").as(String.class)), cb.asc(entityRoot.get(VariableUtil.NOTICECREATETIME).as(Date.class))).getRestriction();
        };
        return noticeRepository.findAll(specification, pageRequest);

    }

    /**
     * 查询审核中的公告数据转化
     *
     * @param crimeSearchInfo
     * @param entityRoot
     * @param cb
     * @return
     */
    private List<Predicate> noticePredicatesExChange(CrimeSearchInfo crimeSearchInfo, Root<NoticeEntity> entityRoot, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isBlank(crimeSearchInfo.getCourtId())) {
            predicates.add(cb.equal(entityRoot.get(VariableUtil.COURTID), crimeSearchInfo.getCourtId()));
        }
        if (!StringUtils.isBlank(crimeSearchInfo.getNoticeNumber())) {
            predicates.add(cb.like(entityRoot.get("noticeNumber"), "%" + crimeSearchInfo.getNoticeNumber() + "%"));
        }
        if (!StringUtils.isBlank(crimeSearchInfo.getCertificateNumber())) {
            predicates.add(cb.equal(entityRoot.get(VariableUtil.CRIME_PERSON_INFO_ENTITY).get(VariableUtil.CERTIFICATENUMBER), crimeSearchInfo.getCertificateNumber()));
        }
        if (crimeSearchInfo.isSearchByEnterPersonId()) {
            predicates.add(cb.equal(entityRoot.get("enterPersonId"), crimeSearchInfo.getEnterPersonId()));
        }
        //查询审核中的公告
        predicates.add(cb.equal(entityRoot.get("status"), "1"));

        if (crimeSearchInfo.getStartNoticeCreateTime() != null) {
            predicates.add(cb.greaterThanOrEqualTo(entityRoot.get(VariableUtil.NOTICECREATETIME), crimeSearchInfo.getStartNoticeCreateTime()));
        }
        if (crimeSearchInfo.getEndNoticeCreateTime() != null) {
            predicates.add(cb.lessThanOrEqualTo(entityRoot.get(VariableUtil.NOTICECREATETIME), crimeSearchInfo.getEndNoticeCreateTime()));
        }
        return predicates;
    }

    /**
     * 查询已审核列表
     *
     * @param crimeSearchInfo
     * @return
     */
    private Page<NoticeEntity> getHasAuditNoticePage(CrimeSearchInfo crimeSearchInfo) {
        PageRequest pageRequest = new PageRequest(crimeSearchInfo.getPages(), crimeSearchInfo.getPageSize());
        Specification<NoticeEntity> specification = (Root<NoticeEntity> entityRoot, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = auditPredicatesExChange(crimeSearchInfo, entityRoot, cb);
            Predicate[] predicate = new Predicate[predicates.size()];
            return query.where(predicates.toArray(predicate)).orderBy(cb.desc(entityRoot.get(VariableUtil.PRIORITY).as(String.class)), cb.desc(entityRoot.get(VariableUtil.NOTICECREATETIME).as(Date.class))).getRestriction();
        };
        return noticeRepository.findAll(specification, pageRequest);
    }

    private List<Predicate> auditPredicatesExChange(CrimeSearchInfo crimeSearchInfo, Root<NoticeEntity> entityRoot, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isBlank(crimeSearchInfo.getAuditPersonId())) {
            predicates.add(cb.equal(entityRoot.get("auditPersonId"), crimeSearchInfo.getAuditPersonId()));
        }

        if (!StringUtils.isBlank(crimeSearchInfo.getCourtId())) {
            predicates.add(cb.equal(entityRoot.get(VariableUtil.COURTID), crimeSearchInfo.getCourtId()));
        }
        if (!StringUtils.isBlank(crimeSearchInfo.getNoticeNumber())) {
            predicates.add(cb.like(entityRoot.get(VariableUtil.NOTICENUMBER), "%" + crimeSearchInfo.getNoticeNumber() + "%"));
        }
        if (!StringUtils.isBlank(crimeSearchInfo.getCertificateNumber())) {
            predicates.add(cb.like(entityRoot.get(VariableUtil.CRIME_PERSON_INFO_ENTITY).get(VariableUtil.CERTIFICATENUMBER), "%" + crimeSearchInfo.getCertificateNumber() + "%"));
        }
        if (crimeSearchInfo.isSearchByEnterPersonId()) {
            predicates.add(cb.equal(entityRoot.get("enterPersonId"), crimeSearchInfo.getEnterPersonId()));
        }
        if (!StringUtils.isBlank(crimeSearchInfo.getEnterPersonName())) {
            predicates.add(cb.like(entityRoot.get("enteringPersonName"), "%" + crimeSearchInfo.getEnterPersonName() + "%"));
        }
        if (StringUtils.isNoneBlank(crimeSearchInfo.getNoticeStatus())) {
            predicates.add(cb.equal(entityRoot.get(VariableUtil.STATUS), crimeSearchInfo.getNoticeStatus()));
        } else {
            Predicate predicateOne = cb.equal(entityRoot.get(VariableUtil.STATUS), "2");
            Predicate predicateTwo = cb.equal(entityRoot.get(VariableUtil.STATUS), "3");
            Predicate predicateThree = cb.equal(entityRoot.get(VariableUtil.STATUS), "4");
            predicates.add(cb.or(predicateOne, predicateTwo, predicateThree));
        }
        if (crimeSearchInfo.getStartNoticeCreateTime() != null) {
            predicates.add(cb.greaterThanOrEqualTo(entityRoot.get(VariableUtil.NOTICECREATETIME), crimeSearchInfo.getStartNoticeCreateTime()));
        }
        if (crimeSearchInfo.getEndNoticeCreateTime() != null) {
            predicates.add(cb.lessThanOrEqualTo(entityRoot.get(VariableUtil.NOTICECREATETIME), crimeSearchInfo.getEndNoticeCreateTime()));
        }
        return predicates;
    }

    /**
     * 获取待审核或待分析公告列表
     *
     * @param pages
     * @param pageSize
     * @param filerId
     * @param auditorId
     * @param businessType
     * @return
     */
    private ReturnBase<List<NoticeInfo>> getReplyOrAuditorNoticeList(int pages, int pageSize, String filerId, String auditorId, String businessType) {
        ReturnBase<List<NoticeInfo>> returnBase = new ReturnBase<>();
        try {

            ReturnBase<List<ApplyAndAnalystRelation>> page= certificateApplyService.findReplyAnalysisApplicationCommon(pages, pageSize, filerId, auditorId, businessType);
            returnBase = this.getApplicationTaskList(page);
        } catch (Exception e) {
            logger.error("Exception:Get Reply Application List Call Exception" + e);
            returnBase.setSuccess(false);
            returnBase.setError(new Error(500, "Server Inner Exception"));
        }
        return returnBase;
    }

    /**
     * 根据犯罪人id查询犯罪人信息
     *
     * @param crimeId
     * @return
     */
    @Override
    public CrimePersonInfo findOneByCrimeId(String crimeId) {
        CrimePersonInfoEntity crimePersonInfoEntity = crimePersonRepository.getByCrimePersonId(crimeId);
        return crimePersonMapper.entityToModel(crimePersonInfoEntity);
    }

    /**
     * 根据身份证号查询犯罪人信息
     *
     * @param certificateNumber
     * @return
     */
    @Override
    public List<CrimePersonInfo> getApplyPersonInfoByCerNumber(String certificateNumber) {
        return crimePersonMapper.entitiestoModels(crimePersonRepository.findAllPossibleCrimePersonInfo(certificateType, certificateNumber));
    }

    /**
     * 根据身份证号查询犯罪信息列表
     *
     * @param certificateNumber
     * @return
     */
    @Override
    public List<CrimeInfo> getCrimeInfoByCerNumber(String certificateNumber) {
        return crimeMapper.entitiestoModels(crimeRepository.findAllCrimeByCertificateNumber(certificateType, certificateNumber));
    }

    /**
     * 根据身份证号查询犯罪信息
     *
     * @param crimeId
     * @return
     */
    @Override
    public CrimeInfo getCrimeInfoDetailByCrimeId(String crimeId) {
        return crimeMapper.entityToModel(crimeRepository.findOneByCrimeId(crimeId));
    }

    /**
     * 根据证件编号查询犯罪信息
     *
     * @param crimePersonSearchInfo
     * @return
     */
    @Override
    public List<CrimePersonInfo> getCrimePersonInfoByCertNumberOrName(CrimePersonSearchInfo crimePersonSearchInfo) {
        if (crimePersonSearchInfo.validateCertificateData()) {
            return getCrimePersonInfoByCertificateId(crimePersonSearchInfo, isByCrime);
        } else {
            if (!crimePersonSearchInfo.validateNameAndSexData()) {
                return new ArrayList<>();
            }
            return getCrimePersonInfoByUserNameAndSexId(crimePersonSearchInfo.getFirstName(), crimePersonSearchInfo.getLastName(), crimePersonSearchInfo.getSexId(), isByCrime);
        }
    }

    /**
     * 根据犯罪人id获取犯罪人信息
     * @param crimePersonId
     * @return
     */
    @Override
    public CrimePersonInfo getCriminalByCrimePersonId(String crimePersonId) {
        return crimePersonMapper.entityToModel(crimePersonRepository.findOneByCrimePersonId(crimePersonId));
    }

    /**
     * 通过查询姓名和性别查询
     *
     * @param firstName
     * @param lastName
     * @param sexId
     * @param isByCrime 是否按照犯罪库查询  true 按照犯罪库查询  false 按照人口库查询
     * @return
     */
    private List<CrimePersonInfo> getCrimePersonInfoByUserNameAndSexId(String firstName, String lastName, String sexId, boolean isByCrime) {
        //优先通过犯罪库查询
        if (isByCrime) {
            return crimePersonMapper.entitiestoModels(crimePersonRepository.findAllByFirstNameAndLastNameAndSexId(firstName, lastName, sexId));
        } else {
            List<ApplyBasicInfo> list = certificateApplyService.getCitizenInfoByFullName(firstName, lastName, sexId);
            List<CrimePersonInfo> crimePersonInfoList = getCrimePersonInfoByJsonExchange(list);
            if (CollectionUtils.isEmpty(crimePersonInfoList)) {
                crimePersonInfoList = crimePersonMapper.entitiestoModels(crimePersonRepository.findAllByFirstNameAndLastNameAndSexId(firstName, lastName, sexId));
            }
            return crimePersonInfoList;
        }

    }

    /**
     * 通过证件类型来查询
     *
     * @param crimePersonSearchInfo
     * @param isByCrime             是否按照犯罪库查询  true 按照犯罪库查询  false 按照人口库查询
     * @return
     */
    private List<CrimePersonInfo> getCrimePersonInfoByCertificateId(CrimePersonSearchInfo crimePersonSearchInfo, boolean isByCrime) {
        //优先通过犯罪库查询
        if (isByCrime) {
            List<CrimePersonInfo> crimePersonInfoList = crimePersonMapper.entitiestoModels(crimePersonRepository.findAllPossibleCrimePersonInfo(crimePersonSearchInfo.getCertificateType(), crimePersonSearchInfo.getCertificateNumber()));
            if (crimePersonSearchInfo.validateNameAndSexData()) {
                crimePersonInfoList = crimePersonMapper.entitiestoModels(crimePersonRepository.findAllByFirstNameAndLastNameAndSexId(crimePersonSearchInfo.getFirstName(), crimePersonSearchInfo.getLastName(), crimePersonSearchInfo.getSexId()));
            }
            return crimePersonInfoList;
        } else {
            if (certificateType.equals(crimePersonSearchInfo.getCertificateType())) {
                List<ApplyBasicInfo> list = certificateApplyService.getCitizenInfoByID(crimePersonSearchInfo.getCertificateNumber());
                List<CrimePersonInfo> crimePersonInfoList = getCrimePersonInfoByJsonExchange(list);
                if (CollectionUtils.isEmpty(crimePersonInfoList)) {
                    crimePersonInfoList = getBasePersonByCertificateIdByCitizenInfo(crimePersonSearchInfo);
                }
                return crimePersonInfoList;
            } else {
                return getBasePersonByCertificateIdByCitizenInfo(crimePersonSearchInfo);
            }

        }

    }

    /**
     * 人口库根据证件类型查询不到时
     * 1、查询犯罪库 按照证件类型查
     * 2、查询人口库 按照姓名+性别查询
     * 3、2中查询不到时 按照姓名+性别 查询犯罪库
     *
     * @return
     */
    private List<CrimePersonInfo> getBasePersonByCertificateIdByCitizenInfo(CrimePersonSearchInfo applyPersonQuery) {
        List<CrimePersonInfo> crimePersonInfoList = crimePersonMapper.entitiestoModels(crimePersonRepository.findAllPossibleCrimePersonInfo(applyPersonQuery.getCertificateType(), applyPersonQuery.getCertificateNumber()));
        if (CollectionUtils.isEmpty(crimePersonInfoList) && !applyPersonQuery.validateNameAndSexData()) {
            return new ArrayList<>();
        }
        if (CollectionUtils.isEmpty(crimePersonInfoList)) {
            List<ApplyBasicInfo> list = certificateApplyService.getCitizenInfoByFullName(applyPersonQuery.getFirstName(), applyPersonQuery.getLastName(), applyPersonQuery.getSexId());
            crimePersonInfoList = getCrimePersonInfoByJsonExchange(list);
        }
        if (CollectionUtils.isEmpty(crimePersonInfoList)) {
            crimePersonInfoList = crimePersonMapper.entitiestoModels(crimePersonRepository.findAllByFirstNameAndLastNameAndSexId(applyPersonQuery.getFirstName(), applyPersonQuery.getLastName(), applyPersonQuery.getSexId()));
        }
        return crimePersonInfoList;
    }

    /**
     * 将查询到的申请人的信息转化为犯罪人的信息模型
     *
     * @param list
     * @return
     */
    public List<CrimePersonInfo> getCrimePersonInfoByJsonExchange(List<ApplyBasicInfo> list) {
        String json = JsonUtil.toJson(list);
        try {
            if (CollectionUtils.isEmpty(list)) {
                return new ArrayList<>();
            }
            return JsonUtil.decodeToList(json, CrimePersonInfo.class);
        } catch (Exception e) {
            logger.error("getCrimePersonInfoByJsonExchange error", e);
            return new ArrayList<>();
        }
    }
}
