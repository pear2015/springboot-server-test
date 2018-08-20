package com.gs.crms.crimenotice.service.repository.impl;

import com.gs.crms.common.utils.VariableUtil;
import com.gs.crms.crimenotice.service.entity.NoticeEntity;
import com.gs.crms.crimenotice.service.repository.extend.NoticeRepositoryExtend;
import com.gsafety.springboot.common.entityrepository.QueryEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhengyali on 2017/11/9.
 */
public class NoticeRepositoryImpl implements NoticeRepositoryExtend {
    /*
   SQL语句
    */
    @PersistenceContext
    private EntityManager entityManager;
    /*
    SQL语句
     */
    @Autowired
    private QueryEntityFactory queryEntityFactory;

    @Override
    public List<NoticeEntity> findAllByCrimePersonIdAndTime(String crimePersonId, Date startDate, Date endDate) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = cb.createQuery(NoticeEntity.class);
        Root<NoticeEntity> root = criteriaQuery.from(NoticeEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("crimePersonId"), crimePersonId));
        predicates.add(cb.equal(root.get("isActive"), "1"));
        predicates.add(cb.greaterThanOrEqualTo(root.get(VariableUtil.NOTICECREATETIME), startDate));
        predicates.add(cb.lessThanOrEqualTo(root.get(VariableUtil.NOTICECREATETIME), endDate));
        Predicate[] predicate = new Predicate[predicates.size()];
        return entityManager.createQuery(criteriaQuery.where(predicates.toArray(predicate)).orderBy(cb.desc(root.get("noticeCreateTime").as(Date.class)))).getResultList();
    }
}
