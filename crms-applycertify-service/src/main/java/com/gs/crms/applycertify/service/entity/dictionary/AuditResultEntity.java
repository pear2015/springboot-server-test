package com.gs.crms.applycertify.service.entity.dictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zhangqiang on 2017/9/4.
 */
@Entity
@Table(name = "d_audit_result", schema = "crms_crime")
public class AuditResultEntity {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String auditResultId;

    /*
     * 审核结果名称
     */
    @Column(name = "name", length = 128, nullable = false)
    private String auditResultName;

    public String getAuditResultId() {
        return auditResultId;
    }

    public void setAuditResultId(String auditResultId) {
        this.auditResultId = auditResultId;
    }

    /**
     * Get
     *
     * @return
     */
    public String getAuditResultName() {
        return auditResultName;
    }

    /**
     * Set
     *
     * @param auditResultName
     */
    public void setAuditResultName(String auditResultName) {
        this.auditResultName = auditResultName;
    }

}
