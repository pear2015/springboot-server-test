package com.gs.crms.applycertify.service.serviceimpl;

import com.gs.crms.applycertify.contract.model.ApplyPriority;
import com.gs.crms.applycertify.contract.model.ApplyPurpose;
import com.gs.crms.applycertify.contract.model.CertificateType;
import com.gs.crms.applycertify.contract.service.CommonApplyService;
import com.gs.crms.applycertify.service.datamappers.*;
import com.gs.crms.applycertify.service.entity.dictionary.ApplyPurposeEntity;
import com.gs.crms.applycertify.service.repository.ApplyPriorityRepository;
import com.gs.crms.applycertify.service.repository.ApplyPurposeRepository;
import com.gs.crms.applycertify.service.repository.CertifcateTypeRepository;
import com.gs.crms.applycertify.service.repository.common.CareerRepository;
import com.gs.crms.common.enums.ApplyType;
import com.gs.crms.common.enums.CertificateTypeEntity;
import com.gs.crms.common.model.CareerInfo;
import com.gs.crms.common.model.MarriageInfo;
import com.gs.crms.common.model.SexInfo;
import com.gs.crms.applycertify.service.repository.common.MarriageRepository;
import com.gs.crms.applycertify.service.repository.common.SexRepository;
import com.gs.crms.common.utils.VariableUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengyali on 2018/3/7.
 * 查询数据字典
 */
@Service
@Transactional
public class CommonApplyServiceImpl implements CommonApplyService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ApplyPurposeRepository applyPurposeRepository;
    @Autowired
    private ApplyPurposeMapper applyPurposeMapper;
    @Autowired
    private CertifcateTypeRepository certifcateTypeRepository;
    @Autowired
    private CertificateTypeMapper certificateTypeMapper;
    @Autowired
    private ApplyPriorityRepository applyPriorityRepository;
    @Autowired
    private ApplyPriorityMapper applyPriorityMapper;
    @Autowired
    private CareerRepository careerRepository;
    @Autowired
    private CareerMapper careerMapper;
    @Autowired
    private MarriageRepository marriageRepository;
    @Autowired
    private MarriageMapper marriageMapper;
    @Autowired
    private SexRepository sexRepository;
    @Autowired
    private SexMapper sexMapper;

    /**
     * 获取申请目的列表
     *
     * @return
     */
    @Override
    public List<ApplyPurpose> getApplyPurposeList() {
        List<ApplyPurpose> applyPurposes = new ArrayList<>();
        try {
            List<ApplyPurposeEntity> list = applyPurposeRepository.getApplyPurposeEntityList();
            applyPurposes = applyPurposeMapper.entitiestoModels(list);
        } catch (Exception e) {
            logger.error("Exception:Get Apply Purpose Call Exception" + e);
        }
        return applyPurposes;

    }

    /**
     * 获取证件类型
     *
     * @return
     */
    @Override
    public List<CertificateType> getCertificateTypeList() {
        List<CertificateType> certificateTypes = new ArrayList<>();
        try {
            List<CertificateTypeEntity> list = certifcateTypeRepository.getCertificateTypeEntityList();
            certificateTypes = certificateTypeMapper.entitiestoModels(list);
        } catch (Exception e) {
            logger.error("Exception:Get Certificate Type Call Exception" + e);
        }
        return certificateTypes;
    }

    /**
     * 获取申请优先级
     *
     * @return
     */
    @Override
    public List<ApplyPriority> getAllApplyPriority() {
        return applyPriorityMapper.entitiestoModels(applyPriorityRepository.getApplyPriorityEntityList());
    }

    /**
     * 获取职业列表
     *
     * @return
     */
    @Override
    public List<CareerInfo> getAllCareerInfo() {
        return careerMapper.entitiestoModels(careerRepository.findAllByCareerIdAsc());
    }

    /**
     * 获取婚姻状态列表
     *
     * @return
     */
    @Override
    public List<MarriageInfo> getMarriage() {
        return marriageMapper.entitiestoModels(marriageRepository.findAllByMarriageIdAsc());
    }

    /**
     * 获取性别状态列表
     *
     * @return
     */
    @Override
    public List<SexInfo> getSexInfo() {
        return sexMapper.entitiestoModels(sexRepository.findAllBySexIdAsc());
    }

    /**
     * 获取指定的证件类型
     *
     * @param applyType
     * @return
     */
    @Override
    public List<CertificateType> getCertificateTypeListByType(ApplyType applyType) {
        if (applyType == null) {
            return certificateTypeMapper.entitiestoModels(certifcateTypeRepository.getCertificateTypeEntityList());
        } else if (applyType == ApplyType.PERSON) {
            return certificateTypeMapper.entitiestoModels(certifcateTypeRepository.getCertificateTypeListByPersonIsValid(VariableUtil.ISVALID));
        } else if (applyType == ApplyType.GOVENMENT) {
            return certificateTypeMapper.entitiestoModels(certifcateTypeRepository.getCertificateTypeListByGovernmentIsValid(VariableUtil.ISVALID));
        } else {
            return certificateTypeMapper.entitiestoModels(certifcateTypeRepository.getCertificateTypeListByNoticeIsValid(VariableUtil.ISVALID));
        }

    }
}
