package com.gs.crms.applycertify.service.serviceimpl;

import com.gs.crms.applycertify.contract.model.ApplyAndAnalystRelation;
import com.gs.crms.applycertify.contract.model.WaitApportion;
import com.gs.crms.applycertify.contract.service.WorkFlowUseService;
import com.gs.crms.applycertify.service.datamappers.ApplyAndAnalystRelationMapper;
import com.gs.crms.applycertify.service.datamappers.WaitApportionMapper;
import com.gs.crms.applycertify.service.entity.ApplyAndAnalystRelationEntity;
import com.gs.crms.applycertify.service.entity.ApplyInfoEntity;
import com.gs.crms.applycertify.service.entity.WaitApportionEntity;
import com.gs.crms.applycertify.service.repository.ApplyAndAnalystRelationRepository;
import com.gs.crms.applycertify.service.repository.ApplyInfoRepository;
import com.gs.crms.applycertify.service.repository.WaitApportionRepository;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.UUID;

/**
 * Created by zhangqiang on 2017/9/28.
 */
@Service
@Transactional
public class WorkFlowUseServiceImpl implements WorkFlowUseService {

    @Autowired
    private ApplyAndAnalystRelationRepository applyAndAnalystRelationRepository;

    @Autowired
    private ApplyAndAnalystRelationMapper applyAndAnalystRelationMapper;

    @Autowired
    private WaitApportionRepository waitApportionRepository;

    @Autowired
    private WaitApportionMapper waitApportionMapper;

    @Autowired
    private ApplyInfoRepository applyInfoRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 保存申请分析/审核任务和用户关联表
     * 如果是申请的业务类型。保存后需要更新对应申请的状态：分析中，审核中；
     *
     * @param applyAndAnalystRelation
     * @return
     */
    @Override
    public boolean saveApplyAnalystAuditor(ApplyAndAnalystRelation applyAndAnalystRelation) {
        try {
            ApplyAndAnalystRelationEntity applyAndAnalystRelationEntity = applyAndAnalystRelationMapper.modelToEntity(applyAndAnalystRelation);

            applyAndAnalystRelationEntity.setRelationId(UUID.randomUUID().toString());
            applyAndAnalystRelationEntity.setCreateTime(new Date());
            applyAndAnalystRelationEntity.setPriority(StringUtils.isEmpty(applyAndAnalystRelationEntity.getPriority())?"0":applyAndAnalystRelationEntity.getPriority());
            applyAndAnalystRelationRepository.save(applyAndAnalystRelationEntity);
            //如果是申请的业务类型。保存后需要更新对应申请的状态：分析中，审核中；
            if (!StringUtils.equals("3", applyAndAnalystRelation.getBusinessType())) {
                ApplyInfoEntity applyInfoEntity = applyInfoRepository.getByApplyId(applyAndAnalystRelationEntity.getApplyInfoId());
                if(applyInfoEntity != null&&StringUtils.isNotBlank(applyAndAnalystRelation.getAnalystId())){
                    applyInfoEntity.setApplyStatusId("1");
                    applyInfoRepository.save(applyInfoEntity);
                }else if(applyInfoEntity != null&&StringUtils.isNotBlank(applyAndAnalystRelation.getAuditorId())){
                    applyInfoEntity.setApplyStatusId("2");
                    applyInfoRepository.save(applyInfoEntity);
                }
            }
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("WorkFlowUseServiceImpl:saveApplyAnalystAuditor called exception:" + e);
            return false;
        }
    }

    /**
     * 保存等待激活表接口
     *
     * @param waitApportion
     * @return
     */
    @Override
    public boolean saveWaitApportion(WaitApportion waitApportion) {
        try {
            WaitApportionEntity waitApportionEntity = waitApportionMapper.modelToEntity(waitApportion);
            waitApportionEntity.setApportionId(UUID.randomUUID().toString());
            waitApportionEntity.setCreateTime(new Date());
            waitApportionRepository.save(waitApportionEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("WorkFlowUseServiceImpl:saveWaitApportion called exception:" + e);
            return false;
        }
    }
}
