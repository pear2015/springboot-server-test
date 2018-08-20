package com.gs.crms.statisticsanalysis.service.util;

import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengyali on 2017/11/24.
 */
public class ReportDataUtil {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String LOGINFO = "resultSet is empty!";

    /**
     * 结果集转为模型对象
     *
     * @param cursorRecord
     * @return
     */
    public List<ReportRtnModel> toResultModel(List<Object[]> cursorRecord) {
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (!cursorRecord.isEmpty()) {
            for (Object[] obj : cursorRecord) {
                ReportRtnModel reportRtnModel = new ReportRtnModel();
                reportRtnModel.setCoord_X(obj[0].toString());
                reportRtnModel.setCoord_Y(obj[1].toString());
                reportRtnModel.setCount(obj[2].toString());
                reportRtnModels.add(reportRtnModel);
            }
        } else {
            logger.info(LOGINFO);
        }
        return reportRtnModels;
    }
    /**
     * 结果集转为模型对象
     *
     * @param cursorRecord
     * @return
     */
    public List<ReportRtnModel> toResultOneModel(List<Object[]> cursorRecord,boolean isIncludeTime) {
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (!cursorRecord.isEmpty()) {
            for (Object[] obj : cursorRecord) {
                ReportRtnModel reportRtnModel = new ReportRtnModel();
                if(isIncludeTime){
                    reportRtnModel.setCoord_X(StringUtils.substringAfterLast(obj[0].toString(),"-"));
                }else{
                    reportRtnModel.setCoord_X(obj[0].toString());
                }
                reportRtnModel.setCount(obj[1].toString());
                reportRtnModels.add(reportRtnModel);
            }
        } else {
            logger.info(LOGINFO);
        }
        return reportRtnModels;
    }

    /**
     * 两个维度的数据处理 一般情况
     *
     * @param cursorRecord
     * @return
     */
    public List<ReportRtnModel> toResultModelForDate(List<Object[]> cursorRecord, boolean isShowTimeX) {
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (!cursorRecord.isEmpty()) {
            for (Object[] obj : cursorRecord) {
                reportRtnModels.add(reportRtnModelExChange(obj, isShowTimeX));
            }
        } else {
            logger.info(LOGINFO);
        }
        return reportRtnModels;
    }

    /**
     * 统计数据处理
     *
     * @param obj
     * @param isShowTimeX
     * @return
     */
    private ReportRtnModel reportRtnModelExChange(Object[] obj, boolean isShowTimeX) {
        ReportRtnModel reportRtnModel = new ReportRtnModel();
        if (obj[0] != null) {
            reportRtnModel.setCoord_Y(obj[0].toString());
        }
        if (obj[1] != null) {
            reportRtnModel.setCoord_X(obj[1].toString());
        }
        if (obj.length > 2 && obj[2] != null) {
            reportRtnModel.setCount(obj[2].toString());
        }
        if (obj.length > 3 && obj[3] != null) {
            reportRtnModel.setTypeName(obj[3].toString());
        }
        // 若x轴显示时间
        if (isShowTimeX) {
            String temp = reportRtnModel.getCoord_Y();
            reportRtnModel.setCoord_Y(reportRtnModel.getCoord_X());
            reportRtnModel.setCoord_X(temp);
        }
        return reportRtnModel;
    }

    /**
     * 维度中包含自定义年 或年 或月 统计的数据处理
     *
     * @param cursorRecord
     * @return
     */
    public List<ReportRtnModel> toResultModelYearOrMonthForDate(List<Object[]> cursorRecord, boolean isShowTimeX) {
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (!cursorRecord.isEmpty()) {
            for (Object[] obj : cursorRecord) {
                reportRtnModels.add(yearOrMonthModelExChange(obj, isShowTimeX));
            }
        } else {
            logger.info(LOGINFO);
        }
        return reportRtnModels;
    }

    /**
     * 统计数据处理
     *
     * @param obj
     * @param isShowTimeX
     * @return
     */
    private ReportRtnModel yearOrMonthModelExChange(Object[] obj, boolean isShowTimeX) {
        ReportRtnModel reportRtnModel = new ReportRtnModel();
        String year = obj[0].toString();
        int index = year.lastIndexOf('.');
        if (index != -1) {
            year = year.substring(0, index);
        }
        reportRtnModel.setCoord_Y(year);
        if (obj[1] != null) {
            reportRtnModel.setCoord_X(obj[1].toString());
        }
        if (obj.length > 2 && obj[2] != null) {
            reportRtnModel.setCount(obj[2].toString());
        }
        // 若x轴显示时间
        if (isShowTimeX) {
            String temp = reportRtnModel.getCoord_Y();
            reportRtnModel.setCoord_Y(reportRtnModel.getCoord_X());
            reportRtnModel.setCoord_X(temp);
        }
        return reportRtnModel;
    }

}
