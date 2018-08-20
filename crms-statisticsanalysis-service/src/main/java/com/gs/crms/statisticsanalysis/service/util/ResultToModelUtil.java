package com.gs.crms.statisticsanalysis.service.util;

import com.gs.crms.statisticsanalysis.contract.model.ReportParamModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;
import com.gs.crms.statisticsanalysis.contract.model.TimeModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhufengjie on 2017/9/25.
 */
public class ResultToModelUtil {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private ReportDataUtil reportDataUtil = new ReportDataUtil();

    /**
     * 按日期查询获取到统计
     * 日期 +1个参数 x轴可能显示时间
     *
     * @param entityManager
     * @param dateType
     * @param proceName
     * @param parameter1    parameter1 日期标记
     *                      0 存储函数 按 年查询
     *                      1 存储函数 按 月查询
     *                      2 存储函数 按 天查询
     * @param parameter2    参数
     * @return
     */
    public List<ReportRtnModel> getResultListByDate(EntityManager entityManager, String dateType, String proceName, TimeModel timeModel, int parameter1, String parameter2, boolean isShowTimeX) {
        List<ReportRtnModel> personalApplyStatistics = new ArrayList<>();
        try {
            //注册存储过程参数
            StoredProcedureQuery storedProcedureQuery = exChangeStoredProcedureNoParameter(entityManager, timeModel.getStartDate(), timeModel.getEndDate(), proceName);
            storedProcedureQuery=exChangeLastParameter(storedProcedureQuery,parameter1,  parameter2);
            boolean flag = storedProcedureQuery.execute();
            if (flag) {
                List<Object[]> cursorRecord = storedProcedureQuery.getResultList();
                personalApplyStatistics = getReportRtnModel(dateType, cursorRecord, isShowTimeX);
            } else {
                logger.error("Remote Server Exception:getResultList_ByDate", proceName);
            }
            return personalApplyStatistics;
        } catch (Exception e) {
            logger.error("Remote Server Exception:getResultList_ByDate" + proceName, e);
            return personalApplyStatistics;
        }
    }

    /**
     * 处理最后两个参数
     * @param storedProcedureQuery
     * @param parameter1
     * @param parameter2
     * @return
     */
    private StoredProcedureQuery exChangeLastParameter(StoredProcedureQuery storedProcedureQuery, int parameter1, String parameter2) {
        storedProcedureQuery.registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN);
        storedProcedureQuery.setParameter(5, parameter1);
        storedProcedureQuery.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
        if (StringUtils.isNotEmpty(parameter2)) {
            storedProcedureQuery.setParameter(6, parameter2);
        } else {
            storedProcedureQuery.setParameter(6, "");
        }
        return storedProcedureQuery;
    }

    /**
     * 按日期查询获取到统计
     * 日期+1个参数 x轴不显示时间
     *
     * @param entityManager
     * @param proceName
     * @param timeModel     parameter1 日期标记
     *                      0 存储函数 按 年查询
     *                      1 存储函数 按 月查询
     *                      2 存储函数 按 天查询
     * @param parameter1    日期标记
     * @param parameter2    参数
     * @return
     */
    public List<ReportRtnModel> getResultListNoShowDate(EntityManager entityManager, String proceName, TimeModel timeModel, int parameter1, String parameter2) {
        List<ReportRtnModel> personalApplyStatistics = new ArrayList<>();
        try {
            //注册存储过程参数
            StoredProcedureQuery storedProcedureQuery = exChangeStoredProcedureNoParameter(entityManager, timeModel.getStartDate(), timeModel.getEndDate(), proceName);
            storedProcedureQuery=exChangeLastParameter(storedProcedureQuery,parameter1,  parameter2);
            return resolveResult(storedProcedureQuery, proceName);
        } catch (Exception e) {
            logger.error("Remote Server Exception:getResultListNoShowDate" + proceName, e);
            return personalApplyStatistics;
        }
    }

    /**
     * 获取到结果集
     * 两个参数（不包含时间）
     * x轴不显示时间
     *
     * @param entityManager
     * @param proceName     存储函数名称
     * @param timeModel     时间段
     * @param
     */
    public List<ReportRtnModel> getResultList(EntityManager entityManager, String proceName, TimeModel timeModel, String parameter1, String parameter2) {
        List<ReportRtnModel> personalApplyStatistics = new ArrayList<>();
        try {
            //注册存储过程参数
            StoredProcedureQuery storedProcedureQuery = exChangeStoredProcedure(entityManager, timeModel.getStartDate(), timeModel.getEndDate(), proceName, parameter1, parameter2);
            return resolveResult(storedProcedureQuery, proceName);
        } catch (Exception e) {
            logger.error("Remote Server Exception:getResultList" + proceName, e);
            return personalApplyStatistics;
        }
    }

    /**
     * 按日期查询获取到统计
     * 日期 +2个参数显示时间
     *
     * @param entityManager
     * @param dateType
     * @param proceName
     * @return
     */
    public List<ReportRtnModel> getResultListByDateAndTwoParam(EntityManager entityManager, String dateType, String proceName, Timestamp startDate, Timestamp endDate, String parameter, String param) {
        List<ReportRtnModel> personalApplyStatistics = new ArrayList<>();
        try {
            //注册存储过程参数
            StoredProcedureQuery storedProcedureQuery = exChangeStoredProcedureNoParameter(entityManager, startDate, endDate, proceName);
            //注册存储过程参数
            storedProcedureQuery.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
            //注入参数
            storedProcedureQuery.setParameter(4, parameter);
            if (StringUtils.isNotEmpty(parameter)) {
                storedProcedureQuery.setParameter(4, parameter);
            } else {
                storedProcedureQuery.setParameter(4, "");
            }
            if (StringUtils.isNotEmpty(param)) {
                storedProcedureQuery.setParameter(5, param);
            } else {
                storedProcedureQuery.setParameter(5, "");
            }
            boolean flag = storedProcedureQuery.execute();
            if (flag) {
                List<Object[]> cursorRecord = storedProcedureQuery.getResultList();
                personalApplyStatistics = getReportRtnModel(dateType, cursorRecord, true);
            } else {
                logger.error("Remote Server Exception:getResultListByDateAndTwoParam", proceName);
            }
            return personalApplyStatistics;
        } catch (Exception e) {
            logger.error("Remote Server Exception:getResultListByDateAndTwoParam" + proceName, e);
            return personalApplyStatistics;
        }
    }

    /**
     * 获取到结果集
     * 分析 待修改
     *
     * @param entityManager
     * @param proceName     存储函数名称
     * @param timeModel     时间段
     * @param parameter1    申请结果 或 审核结果
     * @param parameter2    申请类型
     */
    public List<ReportRtnModel> getAnalystResultList(EntityManager entityManager, String proceName, TimeModel timeModel, String parameter1, String parameter2, int parameter3, int parameter4) {
        List<ReportRtnModel> personalApplyStatistics = new ArrayList<>();
        try {
            //注册存储过程参数
            StoredProcedureQuery storedProcedureQuery = exChangeStoredProcedure(entityManager, timeModel.getStartDate(), timeModel.getEndDate(), proceName, parameter1, parameter2);
            storedProcedureQuery.registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN);
            storedProcedureQuery.setParameter(6, parameter3);
            storedProcedureQuery.registerStoredProcedureParameter(7, Integer.class, ParameterMode.IN);
            storedProcedureQuery.setParameter(7, parameter4);
            return resolveResult(storedProcedureQuery, proceName);
        } catch (Exception e) {
            logger.error("Remote Server Exception:getAnalystResultList" + proceName, e);
            return personalApplyStatistics;
        }
    }

    /**
     * 处理结果
     *
     * @param storedProcedureQuery
     * @param proceName
     * @return
     */
    private List<ReportRtnModel> resolveResult(StoredProcedureQuery storedProcedureQuery, String proceName) {
        List<ReportRtnModel> personalApplyStatistics = new ArrayList<>();
        boolean flag = storedProcedureQuery.execute();
        try {
            if (flag) {
                List<Object[]> cursorRecord = storedProcedureQuery.getResultList();
                personalApplyStatistics = reportDataUtil.toResultModel(cursorRecord);
            } else {
                logger.error("Remote Server Exception:this StoredProcedure  has Exception", proceName);
            }
            return personalApplyStatistics;
        } catch (Exception e) {
            logger.error("Remote Server Exception:this StoredProcedure has Exception" + proceName, e);
            return personalApplyStatistics;
        }
    }
    /**
     * 处理结果
     *
     * @param storedProcedureQuery
     * @param proceName
     * @return
     */
    private List<ReportRtnModel> resolveOneResult(StoredProcedureQuery storedProcedureQuery, String proceName,boolean isIncludeTime) {
        List<ReportRtnModel> personalApplyStatistics = new ArrayList<>();
        boolean flag = storedProcedureQuery.execute();
        try {
            if (flag) {
                List<Object[]> cursorRecord = storedProcedureQuery.getResultList();
                personalApplyStatistics = reportDataUtil.toResultOneModel(cursorRecord,isIncludeTime);
            } else {
                logger.error("Remote Server Exception:this StoredProcedure  has Exception", proceName);
            }
            return personalApplyStatistics;
        } catch (Exception e) {
            logger.error("Remote Server Exception:this StoredProcedure has Exception" + proceName, e);
            return personalApplyStatistics;
        }
    }
    /**
     * 两个维度（不包含时间）
     *
     * @param entityManager
     * @param startDate
     * @param endDate
     * @param proceName
     * @param parameter1
     * @param parameter2
     * @return
     */
    private StoredProcedureQuery exChangeStoredProcedure(EntityManager entityManager, Timestamp startDate, Timestamp endDate, String proceName, String parameter1, String parameter2) {
        StoredProcedureQuery storedProcedureQuery = exChangeStoredProcedureNoParameter(entityManager, startDate, endDate, proceName);
        if (StringUtils.isNotEmpty(parameter1)) {
            storedProcedureQuery.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
            storedProcedureQuery.setParameter(4, parameter1);
        } else {
            storedProcedureQuery.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
            storedProcedureQuery.setParameter(4, "");
        }
        if (StringUtils.isNotEmpty(parameter2)) {
            storedProcedureQuery.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
            storedProcedureQuery.setParameter(5, parameter2);
        } else {
            storedProcedureQuery.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
            storedProcedureQuery.setParameter(5, "");
        }
        return storedProcedureQuery;
    }

    /**
     * 不含参数
     *
     * @param entityManager
     * @param startDate
     * @param endDate
     * @param proceName
     * @return
     */
    private StoredProcedureQuery exChangeStoredProcedureNoParameter(EntityManager entityManager, Timestamp startDate, Timestamp endDate, String proceName) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(proceName);
        //注册存储过程参数
        storedProcedureQuery.registerStoredProcedureParameter(1, Object.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(3, Timestamp.class, ParameterMode.IN);
        //注入参数
        storedProcedureQuery.setParameter(2, startDate);
        storedProcedureQuery.setParameter(3, endDate);
        return storedProcedureQuery;
    }

    /**
     *
     * @param entityManager
     * @param proceName
     * @param timeModel
     * @return
     */
    public List<ReportRtnModel> getApplyResultList(EntityManager entityManager, String proceName, TimeModel timeModel,boolean isIncludeTime) {
        List<ReportRtnModel> personalApplyStatistics = new ArrayList<>();
        try {
            //注册存储过程参数
            StoredProcedureQuery storedProcedureQuery = exChangeStoredProcedureNoParameter(entityManager, timeModel.getStartDate(), timeModel.getEndDate(), proceName);
            return resolveOneResult(storedProcedureQuery, proceName,isIncludeTime);
        } catch (Exception e) {
            logger.error("Remote Server Exception:getApplyResultList" + proceName, e);
            return personalApplyStatistics;
        }
    }

    /**
     * 获取到数据库返回的数据对象
     *
     * @param dateType
     * @param cursorRecord
     * @return
     */
    public List<ReportRtnModel> getReportRtnModel(String dateType, List<Object[]> cursorRecord, boolean isShowTimeX) {
        if (ReportParamModel.DateType.day.getNum().equals(dateType) || ReportParamModel.DateType.dayDIY.getNum().equals(dateType) || ReportParamModel.DateType.NoData.getNum().equals(dateType)) {
            return reportDataUtil.toResultModelForDate(cursorRecord, isShowTimeX);
        } else {
            return reportDataUtil.toResultModelYearOrMonthForDate(cursorRecord, isShowTimeX);
        }
    }
}
