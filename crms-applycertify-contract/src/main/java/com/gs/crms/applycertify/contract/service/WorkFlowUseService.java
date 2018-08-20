package com.gs.crms.applycertify.contract.service;

import com.gs.crms.applycertify.contract.model.ApplyAndAnalystRelation;
import com.gs.crms.applycertify.contract.model.WaitApportion;

/**
 * Created by zhangqiang on 2017/9/28.
 */
public interface WorkFlowUseService {
    /**
     * 保存申请分析/审核任务和用户关联表
     * @param applyAndAnalystRelation
     * @return
     */
    boolean saveApplyAnalystAuditor(ApplyAndAnalystRelation applyAndAnalystRelation);

    /**
     * 保存等待激活表接口
     * @param waitApportion
     * @return
     */
    boolean saveWaitApportion(WaitApportion waitApportion);
}
