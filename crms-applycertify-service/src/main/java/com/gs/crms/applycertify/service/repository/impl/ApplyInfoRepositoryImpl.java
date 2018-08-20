package com.gs.crms.applycertify.service.repository.impl;


import com.gs.crms.applycertify.service.entity.ApplyBasicInfoEntity;
import com.gs.crms.applycertify.service.entity.ApplyInfoEntity;
import com.gs.crms.applycertify.service.repository.extend.ApplyInfoRepositoryExtend;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangqiang on 2017/8/22.
 */
public class ApplyInfoRepositoryImpl implements ApplyInfoRepositoryExtend {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    /*
    SQL语句
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 通过回执单号或身份证查询申请进度
     *
     * @param deliveryReceiptNumbr
     * @param certificateNumber
     * @return
     */
    @Override
    public List<ApplyInfoEntity> getApplyBasicInfoSchedule(String deliveryReceiptNumbr, String certificateNumber) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = cb.createQuery(ApplyInfoEntity.class);
        List<Predicate> predicateList = new ArrayList<>();
        Root<ApplyInfoEntity> root = criteriaQuery.from(ApplyInfoEntity.class);
        if (StringUtils.isNotEmpty(deliveryReceiptNumbr) && StringUtils.isEmpty(certificateNumber)) {
            Predicate predicate = cb.equal(root.get("deliveryReceiptNumbr"), deliveryReceiptNumbr);
            predicateList.add(predicate);
        }
        if (StringUtils.isNotEmpty(certificateNumber) && StringUtils.isEmpty(deliveryReceiptNumbr)) {
            Predicate predicate = cb.equal(root.get("applyBasicInfoEntity").get("certificateNumber"), certificateNumber);
            predicateList.add(predicate);
        } else {
            Predicate predicate1 = cb.equal(root.get("deliveryReceiptNumbr"), deliveryReceiptNumbr);
            Predicate predicate2 = cb.equal(root.get("applyBasicInfoEntity").get("certificateNumber"), certificateNumber);
            predicateList.add(cb.or(predicate1, predicate2));
        }
        criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()])).orderBy(cb.desc(root.get("applyTime").as(Date.class)));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * 根据姓名+性别查询
     * @param firstName
     * @param lastName
     * @param sexId
     * @return
     */
    @Override
    public List<ApplyBasicInfoEntity> getBaseInfoByNameAndSexId(String firstName, String lastName, String sexId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = cb.createQuery(ApplyBasicInfoEntity.class);
        List<Predicate> predicateList = new ArrayList<>();
        Root<ApplyBasicInfoEntity> root = criteriaQuery.from(ApplyBasicInfoEntity.class);
        Predicate predicateOne = cb.equal(root.get("firstName"), firstName);
        Predicate predicateTwo = cb.equal(root.get("lastName"), lastName);
        predicateList.add(cb.and(predicateOne, predicateTwo));
        if (StringUtils.isNotEmpty(sexId)) {
            Predicate predicateSex = cb.equal(root.get("sexId"), sexId);
            predicateList.add(cb.and(predicateSex));
        }
        criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()])).orderBy(cb.desc(root.get("createTime").as(Date.class)));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
