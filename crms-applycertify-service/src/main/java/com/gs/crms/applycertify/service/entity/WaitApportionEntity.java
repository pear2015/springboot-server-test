package com.gs.crms.applycertify.service.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tanjie on 2017/8/8.
 */
@Entity
@Table(name = "b_wait_apportion", schema = "crms_crime")
public class WaitApportionEntity {
    /*
      主键ID
      */
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String apportionId;

    /**
     * 申请ID  (此处做修改 关联业务id)
     */
    @Column(name = "apply_info_id", length = 36)
    private String applyInfoId;

    /**
     * 創建時間
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 权限
     */
    @Column(name = "priority", length = 36)
    private String applyPriority;
    /**
     * 等待激活的原因类型1.分析员2.审核员
     */
    @Column(name = "wait_reason_type", length = 36)
    private String waitReasonType;
    /**
     * 业务类型
     * 1 个人申请业务
     * 2 政府申请业务
     * 3 公告业务
     */
    @Column(name="business_type")
    private  String  businessType;
    /**
     * 工作流流程ID
     */
    @Column(name = "process_id", length = 256)
    private String processId;

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
