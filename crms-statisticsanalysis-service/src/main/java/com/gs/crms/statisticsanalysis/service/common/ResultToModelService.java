package com.gs.crms.statisticsanalysis.service.common;

import com.gs.crms.common.enums.DateType;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;
import com.gs.crms.statisticsanalysis.contract.model.TimeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzuzhi on 2017/11/2.
 */
public class ResultToModelService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final  String LOGINFO = "resultSet is empty!";

    /**
     * 按日期查询获取到统计
     *
     * @param entityManager
     * @param dateType
     * @param proceName
     * @param timeModel
     * @param params
     * @return
     */
    public List<ReportRtnModel> getResultListByDate(EntityManager entityManager, int dateType, String proceName, TimeModel timeModel, List<String> params) {
        List<ReportRtnModel> personalApplyStatistics = new ArrayList<>();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(proceName);
        //注册存储过程参数
        storedProcedureQuery.registerStoredProcedureParameter(1, Object.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(3, Timestamp.class, ParameterMode.IN);
        if (params != null) {
            storedProcedureQuery.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        }
        //注入参数
        storedProcedureQuery.setParameter(2, timeModel.getStartDate());
        storedProcedureQuery.setParameter(3, timeModel.getEndDate());
        if (params != null) {
            storedProcedureQuery.setParameter(4, String.join(",", params));
        }
        boolean flag = storedProcedureQuery.execute();
        if (flag) {
            List<Object[]> cursorRecord = storedProcedureQuery.getResultList();
            personalApplyStatistics = getReportRtnModel(dateType, cursorRecord);
        } else {
            logger.error("execute PROCE_TIME failed");
        }
        return personalApplyStatistics;
    }

    /**
     * 获取到数据库返回的数据对象
     *
     * @param dateType
     * @param cursorRecord
     * @return
     */
    public List<ReportRtnModel> getReportRtnModel(int dateType, List<Object[]> cursorRecord) {
        if (DateType.YEAR.getParamType() == dateType || DateType.YEARDIY.getParamType() == dateType || DateType.DAY.getParamType() == dateType || DateType.DAYDIY.getParamType() == dateType) {
            return toResultModelOnlyTwoForDate(cursorRecord);
        } else {
            return toResultModelForDate(cursorRecord);
        }
    }

    /**
     * 日期查询中结果集只返回三列值的方法
     *
     * @param cursorRecord
     * @return
     */
    public List<ReportRtnModel> toResultModelForDate(List<Object[]> cursorRecord) {
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (!cursorRecord.isEmpty()) {
            for (Object[] obj : cursorRecord) {
                reportRtnModels.add(toResultModelForDateExChange(obj));
            }
        } else {
            logger.info(LOGINFO);
        }
        return reportRtnModels;
    }

    private ReportRtnModel toResultModelForDateExChange(Object[] obj) {
        ReportRtnModel reportRtnModel = new ReportRtnModel();
        String year = "";
        if (obj[2] != null) {
            year = obj[2].toString();
            int yearIndex = year.lastIndexOf('.');
            if (yearIndex != -1)
                year = year.substring(0, yearIndex);
        }
        String month = "";
        if (obj[3] != null) {
            month = obj[3].toString();
            int monthIndex = month.lastIndexOf('.');
            if (monthIndex != -1)
                month = month.substring(0, monthIndex);
        }
        reportRtnModel.setCoord_X(year + "-" + month);
        if (obj[1] != null)
            reportRtnModel.setTypeName(obj[1].toString());
        if (obj[4] != null)
            reportRtnModel.setCount(obj[4].toString());
        return reportRtnModel;
    }

    /**
     * 日期查询中结果集只返回两列值的方法
     *
     * @param cursorRecord
     * @return
     */
    public List<ReportRtnModel> toResultModelOnlyTwoForDate(List<Object[]> cursorRecord) {
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (!cursorRecord.isEmpty()) {
            for (Object[] obj : cursorRecord) {
                reportRtnModels.add(modelOnlyTwoForDateChange(obj));
            }
        } else {
            logger.info(LOGINFO);
        }
        return reportRtnModels;
    }

    private ReportRtnModel modelOnlyTwoForDateChange(Object[] obj) {
        ReportRtnModel reportRtnModel = new ReportRtnModel();
        if (obj.length > 2 && obj[2] != null) {
            String year = obj[2].toString();
            int index = year.lastIndexOf('.');
            if (index != -1)
                year = year.substring(0, index);
            reportRtnModel.setCoord_X(year);
        }
        if (obj.length > 1 && obj[1] != null) {
            reportRtnModel.setTypeName(obj[1].toString());
        }
        if (obj.length > 3 && obj[3] != null) {
            reportRtnModel.setCount(obj[3].toString());
        }
        return reportRtnModel;
    }
}
