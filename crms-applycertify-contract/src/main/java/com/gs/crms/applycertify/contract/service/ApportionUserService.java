package com.gs.crms.applycertify.contract.service;

import com.gs.crms.applycertify.contract.model.WaitApportion;

import java.util.List;

/**
 * Created by zhangqiang on 2017/9/28.
 * 分派服务需要的服务
 */
public interface ApportionUserService {
    /**
     * 获取没有分析任务的分析员列表
     *
     * @param analystIds
     * @return
     */
    List<String> findNoAnalysisTaskAnalystList(List<String> analystIds);

    /**
     * 获取到待分派申请队列
     *
     * @return
     */
    List<WaitApportion> getAllWaitApportion();

    /**
     * 根据任务id删除待派发表中的申请
     *
     * @param ids
     * @return
     */
    Boolean deleteApportion(List<String> ids);

    /**
     * 根据业务类型获取等待分派列表
     * @return
     */
    List<WaitApportion> getAllWaitApportionByWaitReasonType(String waitReasonType);
}
