package com.gs.crms.crimenotice.service.repository;

import com.gs.crms.crimenotice.service.entity.dictionary.CrimeTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhengyali on 2017/10/30.
 * 犯罪类型 Repository
 */
@Repository
public interface CrimeTypeRepository extends JpaRepository<CrimeTypeEntity,Long> {

    List<CrimeTypeEntity> findAllByOrderByIdAsc();
}
