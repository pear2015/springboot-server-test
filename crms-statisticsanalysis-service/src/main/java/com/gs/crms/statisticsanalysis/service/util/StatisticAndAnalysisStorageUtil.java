package com.gs.crms.statisticsanalysis.service.util;

/**
 * Created by zhengyali on 2017/11/20.
 * 所有的存储函数
 */
public class StatisticAndAnalysisStorageUtil {
    /**
     * 业务办理情况统计
         * 维度1 按年月日统计
         * 维度2 申请类型和采集点（所在区域）
         * 维度3 申请类型和申请目的统计
         * 维度4 所在地（采集点）和申请目的
     * 统计本周、本月、本年的业务量
     */
    public static final String APPLY_ALL = "\"crms_crime\".\"apply_all\"";
    public static final String APPLY_ALL_TYPE_CENTER = "\"crms_crime\".\"apply_all_type_center\"";
    public static final String APPLY_ALL_TYPE_PURPOSE = "\"crms_crime\".\"apply_all_type_purpose\"";
    public static final String APPLY_ALL_CENTER_PURPOSE = "\"crms_crime\".\"apply_all_center_purpose\"";
    public static final String APPLY_ALL_TIME = "\"crms_crime\".\"apply_all_time\"";
    /**
     * 业务办理情况分析  单个业务的办理时长(或者单个站点下的业务办理情况)
     * 办理结果
     * 审核结果(指的是申请成功还是失败)
     */
    public static final String APPLTY_RESULT_AVG_TIME = "\"crms_crime\".\"apply_center_result_avg_time\"";
    public static final String APPLTY_PASS_AVG_TIME = "\"crms_crime\".\"apply_center_pass_avg_time\"";

    /**
     * 个人申请业务统计分析
     * 维度1 按时间和采集点统计
     * 维度2 按照职业和办理用途
     * 维度3 职业和办理结果
     * 维度4 办理用途+办理结果
     */
    public static final String APPLY_PERSON = "\"crms_crime\".\"apply_personal_center\"";
    public static final String APPLY_PROFESSION_PURPOSE = "\"crms_crime\".\"apply_profession_purpose\"";
    public static final String APPLY_PROFESSION_RESULT = "\"crms_crime\".\"apply_profession_result\"";
    public static final String APPLY_PURPOSE_RESULT = "\"crms_crime\".\"apply_personal_purpose_result\"";
    public static final String APPLY_PERSON_ALL = "\"crms_crime\".\"apply_personal_all\"";

    /**
     * 政府申请统计和分析
     * 维度1 按照时间统计
     * 按天统计
     * 按月统计
     * 按天统计
     * 按维度政府名称+时间 统计
     * 维度2 政府类型+时间段
     * 维度3 政府类型 +办理用
     * 维度4 政府类型 +办理结果
     * 维度5 办理用途+办理结果
     */
    public static final String APPLY_GOV = "\"crms_crime\".\"apply_govt\"";
    public static final String APPLY_GOV_ORG = "\"crms_crime\".\"apply_govt_org\"";
    public static final String APPLY_GOV_ORG_PURPOSE = "\"crms_crime\".\"apply_govt_org_purpose\"";
    public static final String APPLY_GOV_ORG_RESULT = "\"crms_crime\".\"apply_govt_org_result\"";
    public static final String APPLY_GOV_PURPOSE_RESULT = "\"crms_crime\".\"apply_govt_purpose_result\"";
    /**
     * 犯罪公告统计
     * 按发布法院 发布时间统计
     */
    public static final String CRIME_NOTICE = "\"crms_crime\".\"crime_notice_court\"";
    /**
     * 系统使用情况统计
     */
    public static final String PROCE_SYSUSAGE_SPAN = "\"crms_crime\".\"center_time_span\"";

    /**
     * 犯罪信息统计
     * 维度1 犯罪类型和犯罪区域
     * 维度2 犯罪类型和犯罪时间
     * 维度3 犯罪区域和犯罪时间
     */
    public static final String CRIME_REGION_TYPE = "\"crms_crime\".\"crime_region_type\"";
    public static final String CRIME_TYPE_TIME = "\"crms_crime\".\"crime_type_time\"";
    public static final String CRIME_REGION_TIME = "\"crms_crime\".\"crime_region_time\"";

    private StatisticAndAnalysisStorageUtil() {
    }
}
