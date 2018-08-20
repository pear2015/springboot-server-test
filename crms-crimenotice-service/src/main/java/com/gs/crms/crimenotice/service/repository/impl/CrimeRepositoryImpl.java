package com.gs.crms.crimenotice.service.repository.impl;

import com.gs.crms.common.enums.AttachmentEntity;
import com.gs.crms.crimenotice.service.repository.extend.CrimeRepositoryExtend;

import com.gsafety.springboot.common.entityrepository.QueryEntityFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tanjie on 2017/7/28.
 */
public class CrimeRepositoryImpl implements CrimeRepositoryExtend {
    Logger logger = LoggerFactory.getLogger(this.getClass());
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

    /**
     * 通过附件ID集合获取每个公告的附件集合
     *
     * @param attachmentIdList
     * @return
     */
    @Override
    public List<AttachmentEntity> getAttachmentListInfo(String attachmentIdList) {
        List<String> attachmentStingIdList = Arrays.asList(attachmentIdList.split(","));
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(AttachmentEntity.class);
        Root<AttachmentEntity> root = criteriaQuery.from(AttachmentEntity.class);
        List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(root.get("attachmentId").in(attachmentStingIdList));
        criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
