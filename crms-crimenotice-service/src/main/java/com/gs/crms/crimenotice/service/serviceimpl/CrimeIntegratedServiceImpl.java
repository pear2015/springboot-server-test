package com.gs.crms.crimenotice.service.serviceimpl;

import com.gs.crms.common.model.Error;
import com.gs.crms.common.model.ReturnBase;
import com.gs.crms.common.utils.TimeUtil;
import com.gs.crms.crimenotice.contract.model.search.CrimeAndNoticeExtendInfo;
import com.gs.crms.crimenotice.contract.model.CrimeIntegrated.*;
import com.gs.crms.crimenotice.contract.model.CrimePersonInfo;
import com.gs.crms.crimenotice.contract.service.CrimeIntegratedService;
import com.gs.crms.crimenotice.contract.service.CrimeService;
import com.gs.crms.crimenotice.service.datamappers.CrimePersonMapper;
import com.gs.crms.crimenotice.service.entity.CrimePersonInfoEntity;
import com.gs.crms.crimenotice.service.entity.NoticeEntity;
import com.gs.crms.crimenotice.service.repository.CrimePersonRepository;
import com.gs.crms.crimenotice.service.repository.NoticeRepository;

import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangqiang on 2017/10/17.
 */
@Service
@Transactional
public class CrimeIntegratedServiceImpl implements CrimeIntegratedService {

    @Autowired
    private CrimePersonRepository crimePersonRepository;
    @Autowired
    private CrimePersonMapper crimePersonMapper;

    @Autowired
    private CrimeService crimeService;

    @Autowired
    private NoticeRepository noticeRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 犯罪信息查询
     *
     * @param crimePersonQuery
     * @return
     */
    @Override
    public ReturnBase<List<CrimeAndNoticeExtendInfo>> findAllCrimeInfoByPersonalInfo(CrimePersonQuery crimePersonQuery) {
        ReturnBase<List<CrimeAndNoticeExtendInfo>> returnBase = new ReturnBase<>();
        try {
            Page<CrimePersonInfoEntity> page = findAllByCrimePersonQueryPage(crimePersonQuery);
            List<CrimePersonInfoEntity> entities = new ArrayList<>();

            if (page != null) {
                entities = page.getContent();

            }

            List<String> crimePersonIds = new ArrayList<>();

            if (!entities.isEmpty()) {

                for (CrimePersonInfoEntity crimePersonInfoEntity : entities) {
                    crimePersonIds.add(crimePersonInfoEntity.getCrimePersonId());
                }

                List<String> noticeIds = findAllNoticeByCrimePersonId(crimePersonIds);
                List<CrimeAndNoticeExtendInfo> list = crimeService.getNoticeDetailByNoticeIds(noticeIds);

                if (!list.isEmpty()) {
                    returnBase.setData(list);
                    returnBase.setTotalCount(page != null ? page.getTotalElements() : 0);
                    returnBase.setTotalPage(page != null ? page.getTotalPages() : 0);
                    returnBase.setSuccess(true);
                }
            }
        } catch (Exception e) {
            logger.error("findAllCrimeInfoByPersonalInfo：" + e);
            returnBase.setSuccess(false);
            returnBase.setError(new Error(500, "Server Inner Exception"));
        }

        return returnBase;
    }


    /**
     * 通过犯罪人ID查询公告ID列表
     *
     * @param crimePersonIds
     * @return
     */
    private List<String> findAllNoticeByCrimePersonId(List<String> crimePersonIds) {
        List<String> noticeIds = new ArrayList<>();

        if (!crimePersonIds.isEmpty()) {
            for (String personId : crimePersonIds) {
                NoticeEntity noticeEntity = noticeRepository.getByCrimePersonId(personId);
                if (noticeEntity != null) {
                    noticeIds.add(noticeEntity.getNoticeId());
                }
            }
        }
        return noticeIds;
    }

    /**
     * 查询犯罪人信息,带分页
     * 根据传入的查询对象指生成动态查询
     * 默认查询有效的犯罪人
     *
     * @param crimePersonQuery
     * @return
     */
    private Page<CrimePersonInfoEntity> findAllByCrimePersonQueryPage(CrimePersonQuery crimePersonQuery) {
        PageRequest   pageRequest = new PageRequest(crimePersonQuery.getPages(), crimePersonQuery.getPageSize());
        Specification<CrimePersonInfoEntity> specification = (Root<CrimePersonInfoEntity> entityRoot, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates=predicatesExChange(crimePersonQuery , entityRoot,  cb);
            Predicate[] predicate = new Predicate[predicates.size()];
            return query.where(predicates.toArray(predicate)).orderBy(cb.desc(entityRoot.get("enteringTime").as(Date.class))).getRestriction();
        };
        return crimePersonRepository.findAll(specification, pageRequest);
    }

    /**
     * 查询数据转化
     * @param crimePersonQuery
     * @param entityRoot
     * @param cb
     * @return
     */
    private   List<Predicate> predicatesExChange(CrimePersonQuery crimePersonQuery ,Root<CrimePersonInfoEntity> entityRoot, CriteriaBuilder cb){
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isBlank(crimePersonQuery.getFirstName())) {
            predicates.add(cb.like(entityRoot.get("firstName"), "%" + crimePersonQuery.getFirstName() + "%"));
        }
        if (!StringUtils.isBlank(crimePersonQuery.getLastName())) {
            predicates.add(cb.like(entityRoot.get("lastName"), "%" + crimePersonQuery.getLastName() + "%"));
        }
        if (!StringUtils.isBlank(crimePersonQuery.getCertificateNumber())) {
            predicates.add(cb.like(entityRoot.get("certificateNumber"), "%" + crimePersonQuery.getCertificateNumber() + "%"));
        }
        if (!StringUtils.isBlank(crimePersonQuery.getFatherFirstName())) {
            predicates.add(cb.like(entityRoot.get("fatherFirstName"), "%" + crimePersonQuery.getFatherFirstName() + "%"));
        }
        if (!StringUtils.isBlank(crimePersonQuery.getFatherLastName())) {
            predicates.add(cb.like(entityRoot.get("fatherLastName"), "%" + crimePersonQuery.getFatherLastName() + "%"));
        }
        if (!StringUtils.isBlank(crimePersonQuery.getMotherFirstName())) {
            predicates.add(cb.like(entityRoot.get("motherFirstName"), "%" + crimePersonQuery.getMotherFirstName() + "%"));
        }
        if (!StringUtils.isBlank(crimePersonQuery.getMotherLastName())) {
            predicates.add(cb.like(entityRoot.get("motherLastName"), "%" + crimePersonQuery.getMotherLastName() + "%"));
        }
        predicates.add(cb.equal(entityRoot.get("isActive"), "1"));
        if (null != crimePersonQuery.getBirthDate()) {
            try {
                predicates.add(cb.greaterThanOrEqualTo(entityRoot.get("birthDate"), TimeUtil.getMinDate(crimePersonQuery.getBirthDate())));
                predicates.add(cb.lessThanOrEqualTo(entityRoot.get("birthDate"), TimeUtil.getMaxDate(crimePersonQuery.getBirthDate())));
            } catch (Exception e) {
                logger.error("findAllCrimeInfoByPersonalInfo error", e);
            }
        }

        return predicates;
    }


    /**
     * 根据条件查询犯罪信息列表
     *
     * @param crimePersonQuery
     * @return
     */
    @Override
    public ReturnBase<List<CrimePersonInfo>> findAllCrimePersonInfoBySearch(CrimePersonQuery crimePersonQuery) {
        ReturnBase<List<CrimePersonInfo>> returnBase = new ReturnBase<>();
        Page<CrimePersonInfoEntity> page = findAllByCrimePersonQueryPage(crimePersonQuery);
        try {
            if (page != null) {
                List<CrimePersonInfo> list = crimePersonMapper.entitiestoModels(page.getContent());
                returnBase.setSuccess(true);
                returnBase.setData(list);
                returnBase.setTotalPage(page.getTotalPages());
                returnBase.setTotalCount(page.getTotalElements());
            }
        } catch (Exception e) {
            logger.error("findAllCrimePersonInfoBySearch error", e);
            returnBase.setSuccess(false);
            returnBase.setError(new Error(500, "Server Inner Exception"));
        }
        return returnBase;
    }
}
