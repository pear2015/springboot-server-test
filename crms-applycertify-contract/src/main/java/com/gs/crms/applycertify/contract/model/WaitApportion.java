package com.gs.crms.applycertify.contract.model;

import java.util.Date;

/**
 * 等待派发队列
 * Created by zhangqiang on 2017/8/22.
 */
public class WaitApportion {
    /*
     主键ID
     */
    private String apportionId;

    /*
    ID
     */
    private String applyInfoId;

    /**
     * 創建時間
     */
    private Date createTime;

    /**
     * 权限
     */
    private String applyPriority;

    /**
     * 等待激活的原因类型1.分析员2.审核员
     */
    private String waitReasonType;
    /**
     * 工作流流程ID
     */
    private String processId;
    /**
     * 业务类型
     * 1 个人申请业务
     * 2 政府申请业务
     * 3 公告业务
     */
    private  String  businessType;

    public String getApportionId() {
        return apportionId;
    }

    public void setApportionId(String apportionId) {
        this.apportionId = apportionId;
    }

    public String getApplyInfoId() {
        return applyInfoId;
    }

    public void setApplyInfoId(String applyInfoId) {
        this.applyInfoId = applyInfoId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getApplyPriority() {
        return applyPriority;
    }

    public void setApplyPriority(String applyPriority) {
        this.applyPriority = applyPriority;
    }

    public String getWaitReasonType() {
        return waitReasonType;
    }

    public void setWaitReasonType(String waitReasonType) {
        this.waitReasonType = waitReasonType;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}
