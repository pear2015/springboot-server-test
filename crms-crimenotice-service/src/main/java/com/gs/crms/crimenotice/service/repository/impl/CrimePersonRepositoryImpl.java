package com.gs.crms.crimenotice.service.repository.impl;

import com.gs.crms.crimenotice.service.entity.CrimePersonInfoEntity;
import com.gs.crms.crimenotice.service.repository.extend.CrimePersonRepositoryExtend;
import org.apache.commons.lang3.StringUtils;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengyali on 2017/11/3.
 */
public class CrimePersonRepositoryImpl implements CrimePersonRepositoryExtend {
    @PersistenceContext
    private EntityManager entityManager;

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
    public List<CrimePersonInfoEntity> findAllBySearch(String certificateType, String certificateNumber, String firstName, String lastName) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = cb.createQuery(CrimePersonInfoEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<CrimePersonInfoEntity> root = criteriaQuery.from(CrimePersonInfoEntity.class);
        Predicate predicate = cb.equal(root.get("firstName"), firstName);
        Predicate predicate1 = cb.equal(root.get("lastName"), lastName);
        if (StringUtils.isBlank(certificateNumber) || StringUtils.isBlank(certificateType)) {
            predicates.add(cb.and(predicate, predicate1));
        } else {
            Predicate predicate2 = cb.equal(root.get("certificateType"), certificateType);
            Predicate predicate3 = cb.equal(root.get("certificateNumber"), certificateNumber);
            predicates.add(cb.or(cb.and(predicate, predicate1), cb.and(predicate2, predicate3)));
        }
        Predicate predicateFive = cb.equal(root.get("isActive"), "1");
        predicates.add(predicateFive);
        Predicate[] predicateAll = new Predicate[predicates.size()];
        return entityManager.createQuery(criteriaQuery.where(predicates.toArray(predicateAll))).getResultList();
    }
}
