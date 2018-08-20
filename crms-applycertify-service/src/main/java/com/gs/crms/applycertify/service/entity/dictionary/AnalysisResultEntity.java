package com.gs.crms.applycertify.service.entity.dictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 分析结果Entity
 * Created by zhangqiang on 2017/9/4.
 */
@Entity
@Table(name = "d_analysis_result", schema = "crms_crime")
public class AnalysisResultEntity {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String analysisResultId;

    /*
    分析结果名称
     */
    @Column(name = "name", length = 128, nullable = false)
    private String analysisResultName;

    public String getAnalysisResultId() {
        return analysisResultId;
    }

    public void setAnalysisResultId(String analysisResultId) {
        this.analysisResultId = analysisResultId;
    }

    /**
     * Get
     *
     * @return
     */
    public String getAnalysisResultName() {
        return analysisResultName;
    }

    /**
     * Set
     *
     * @param analysisResultName
     */
    public void setAnalysisResultName(String analysisResultName) {
        this.analysisResultName = analysisResultName;
    }
}
