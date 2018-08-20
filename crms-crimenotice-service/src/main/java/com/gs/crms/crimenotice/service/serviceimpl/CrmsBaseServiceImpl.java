package com.gs.crms.crimenotice.service.serviceimpl;

import com.gs.crms.crimenotice.contract.model.CourtInfo;
import com.gs.crms.crimenotice.contract.model.CrimeTypeInfo;
import com.gs.crms.crimenotice.contract.service.CrmsBaseService;
import com.gs.crms.crimenotice.service.datamappers.CourtMapper;
import com.gs.crms.crimenotice.service.datamappers.CrimeTypeMapper;
import com.gs.crms.crimenotice.service.entity.dictionary.CourtEntity;
import com.gs.crms.crimenotice.service.entity.dictionary.CrimeTypeEntity;
import com.gs.crms.crimenotice.service.repository.CourtRepository;
import com.gs.crms.crimenotice.service.repository.CrimeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by zhengyali on 2017/10/30.
 */
@Service
@Transactional
public class CrmsBaseServiceImpl implements CrmsBaseService {
    @Autowired
    private CourtRepository courtRepository;
    @Autowired
    private CourtMapper courtMapper;
    @Autowired
    private CrimeTypeRepository crimeTypeRepository;
    @Autowired
    private CrimeTypeMapper crimeTypeMapper;

    /**
     * 法院列表
     *
     * @return
     */
    @Override
    public List<CourtInfo> getCourtList() {
        List<CourtEntity> courtEntities = courtRepository.getCourtEntityList();
        return courtMapper.entitiestoModels(courtEntities);
    }

    /**
     * 获取犯罪类型列表
     *
     * @return
     */
    @Override
    public List<CrimeTypeInfo> getCrimeTypeInfoList() {
        List<CrimeTypeEntity> crimeTypeEntityList = crimeTypeRepository.findAllByOrderByIdAsc();
        return crimeTypeMapper.entitiestoModels(crimeTypeEntityList);
    }

}
