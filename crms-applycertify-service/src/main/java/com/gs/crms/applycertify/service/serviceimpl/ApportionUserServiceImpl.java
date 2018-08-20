package com.gs.crms.applycertify.service.serviceimpl;

import com.gs.crms.applycertify.contract.model.WaitApportion;
import com.gs.crms.applycertify.contract.service.ApportionUserService;
import com.gs.crms.applycertify.service.datamappers.WaitApportionMapper;
import com.gs.crms.applycertify.service.entity.ApplyAndAnalystRelationEntity;
import com.gs.crms.applycertify.service.entity.WaitApportionEntity;
import com.gs.crms.applycertify.service.repository.ApplyAndAnalystRelationRepository;
import com.gs.crms.applycertify.service.repository.WaitApportionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangqiang on 2017/9/28.
 * 分派服务需要的服务实现
 */
@Service
@Transactional
public class ApportionUserServiceImpl implements ApportionUserService {

    @Autowired
    private ApplyAndAnalystRelationRepository applyAndAnalystRelationRepository;

    @Autowired
    private WaitApportionRepository waitApportionRepository;

    @Autowired
    private WaitApportionMapper waitApportionMapper;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 返回没有新分析任务的分析员ID列表
     *
     * @param analystIds
     * @return
     */
    @Override
    public List<String> findNoAnalysisTaskAnalystList(List<String> analystIds) {

        List<String> result = new ArrayList<>();

        if (!analystIds.isEmpty()) {
            analystIds.forEach(analystId -> {
                List<ApplyAndAnalystRelationEntity> list = applyAndAnalystRelationRepository.findAllByAnalystIdAndAnalysisResultFail(analystId, "0");
                if (list.isEmpty()) {
                    result.add(analystId);
                }
            });
        }
        return result;
    }

    /**
     * 获取到待分派申请队列
     *
     * @return
     */
    @Override
    public List<WaitApportion> getAllWaitApportion() {
        List<WaitApportionEntity> waitApportionEntities = waitApportionRepository.getAllWaitApportion();
        return waitApportionMapper.entitiestoModels(waitApportionEntities);
    }


    /**
     * 根据任务id删除待派发表中的申请
     *
     * @param ids
     * @return
     */
    @Override
    public Boolean deleteApportion(List<String> ids) {
        try {
            waitApportionRepository.deleteByIds(ids);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("delete apportion error:", e);
            return false;
        }
        return true;
    }

    /**
     * 根据业务类型查询等待分派列表
     * @return
     */
    @Override
    public List<WaitApportion> getAllWaitApportionByWaitReasonType(String waitReasonType) {
        List<WaitApportionEntity> waitApportionEntityList=waitApportionRepository.findAllByWaitReasonType(waitReasonType);
        return waitApportionMapper.entitiestoModels(waitApportionEntityList);
    }
}
