package com.gs.crms.crimenotice.service.repository;

import com.gs.crms.crimenotice.service.entity.dictionary.CourtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tanjie on 2017/8/10.
 */
@Repository
public interface CourtRepository extends JpaRepository<CourtEntity,Long>{

    /**
     * Gets court entity list.
     *
     * @return the court entity list
     */
    @Query("select a from CourtEntity a order by a.courtId asc ")
    List<CourtEntity> getCourtEntityList();
}
