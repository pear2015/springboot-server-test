package com.gs.crms.statisticsanalysis.service.util;

import com.gs.crms.statisticsanalysis.contract.model.ReportCrimeParamModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportParamModel;
import com.gs.crms.statisticsanalysis.contract.model.TimeModel;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取到传给存储过程的时间格式
 * Created by zhufengjie on 2017/9/25.
 */
public class TimeFormatUtil {
    /**
     * 申请业务日期转化
     *
     * @param reportParamModel
     * @return
     */
    public TimeModel getTimeZoneReportExChange(ReportParamModel reportParamModel) {
        return getTimeZone(reportParamModel.getDateType(), reportParamModel.getStartDate(), reportParamModel.getEndDate());
    }

    /**
     * 犯罪公告和犯罪信息日期转化
     *
     * @param reportParamModel
     * @return
     */
    public TimeModel getTimeZoneCrimeExChange(ReportCrimeParamModel reportParamModel) {
        return getTimeZone(reportParamModel.getDateType(), reportParamModel.getStartDate(), reportParamModel.getEndDate());
    }

    /**
     * 根据日期类型获取时间模型
     *
     * @param dateType 日期类型
     * @return 日期模型（开始时间，结束时间）
     */
    public TimeModel getTimeZoneByDateType(String dateType) {
        return getTimeZone(dateType, null, null);
    }
    /**
     * 新需求
     * 当年 取今年 例：2017年
     * 当月 例：11月
     * 当天 例 2017-11-23 00:00:00
     * 0 当年： 按月统计
     * 1 当月 ：按天统计
     * 2 当天：就统计当天
     * 3 自定义年 按年统计
     * 4 自定义月 按月统计
     * 5 自定义天 按天统计
     *  dimension 维度
     */
    /**
     * 获取到时间区间
     *
     * @param dateType
     * @param reportStartDate
     * @param reportEndDate
     * @return
     */
    public TimeModel getTimeZone(String dateType, Date reportStartDate, Date reportEndDate) {
        TimeModel timeModel = new TimeModel();
        switch (dateType) {
            case "0"://当年
                timeModel = getCurrentYear();
                break;
            case "1"://当月
                timeModel = getCurrentMonth();
                break;
            case "2"://当天
                timeModel = getCurrentDay();
                break;
            case "6"://本周
                timeModel = getCurrentWeek();
                break;
            case "3"://时间点：自定义年
            case "4"://时间点：自定义月
            case "5"://时间点：自定义日
                timeModel = getTimeModelByDefined(reportStartDate, reportEndDate);
                break;
            default:
                break;
        }
        return timeModel;
    }


    /**
     * 获取当年 2017-01-01 00:00:00 -当前日期
     *
     * @return
     */
    private static TimeModel getCurrentYear() {
        TimeModel timeModel = new TimeModel();
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        Date dateStart = getYearFirst(currentYear);
        Date dateEnd = getYearLast(currentYear);
        timeModel.setStartDate(new Timestamp(dateStart.getTime()));
        timeModel.setEndDate(new Timestamp(dateEnd.getTime()));
        return timeModel;
    }

    /**
     * 获取当年第一天
     *
     * @param currentYear
     * @return
     */
    private static Date getYearFirst(int currentYear) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, currentYear);
        return calendar.getTime();
    }

    /**
     * 获取当年最后一天
     *
     * @param currentYear
     * @return
     */
    private static Date getYearLast(int currentYear) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, currentYear);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        return getMaxTime(calendar);
    }

    /**
     * 获取当月最后一天
     *
     * @param
     * @return
     */
    private Date getMonthLast() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return getMaxTime(calendar);
    }

    /**
     * 获取当月 2017-11-01 00:00:00 -当前日期
     *
     * @return
     */
    private TimeModel getCurrentMonth() {
        TimeModel timeModel = new TimeModel();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 0);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dateStart = calendar.getTime();
        timeModel.setStartDate(new Timestamp(dateStart.getTime()));
        //获取截止时间
        Date dateEnd = getMonthLast();
        timeModel.setEndDate(new Timestamp(dateEnd.getTime()));
        return timeModel;
    }

    /**
     * 获取本周时间模型
     *
     * @return
     */
    private TimeModel getCurrentWeek() {
        TimeModel timeModel = new TimeModel();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int dayofweek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        calendar.add(Calendar.DATE, 2 - dayofweek);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        timeModel.setStartDate(getMinTime(calendar));
        //获取截止时间
        Date dateEnd = new Date();
        calendar.setTime(dateEnd);
        timeModel.setEndDate(getMaxTime(calendar));
        return timeModel;
    }


    /**
     * 获取当天 2017-11-23 00:00:00 -当前日期
     *
     * @return
     */
    private TimeModel getCurrentDay() {
        TimeModel timeModel = new TimeModel();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 0);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dateStart = calendar.getTime();
        //获取截止时间
        Date dateEnd = new Date();
        timeModel.setStartDate(new Timestamp(dateStart.getTime()));
        calendar.setTime(dateEnd);
        timeModel.setEndDate(getMaxTime(calendar));
        return timeModel;
    }

    /**
     * 按自定义年获取到时间区间
     *
     * @param reportStartDate
     * @param reportEndDate
     * @return
     */
    private TimeModel getTimeModelByDefined(Date reportStartDate, Date reportEndDate) {
        TimeModel timeModel = new TimeModel();
        //起始日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(reportStartDate != null ? reportStartDate : new Date());
        timeModel.setStartDate(getMinTime(calendar));
        //结束日期
        calendar.setTime(reportEndDate != null ? reportEndDate : new Date());
        timeModel.setEndDate(getMaxTime(calendar));
        return timeModel;
    }

    /**
     * 获取到时间区间中最小值
     *
     * @param calendar
     * @return
     */
    public static Timestamp getMinTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return getMinTimeForDay(calendar);
    }

    /**
     * 获取一天最开始时间
     *
     * @param calendar
     * @return
     */
    public static Timestamp getMinTimeForDay(Calendar calendar) {
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     * 获取到时间区间中最大值
     *
     * @param calendar
     * @return
     */
    public static Timestamp getMaxTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        return getMaxTimeForDay(calendar);
    }

    /**
     * 获取一天最大时间
     *
     * @param calendar
     * @return
     */
    private static Timestamp getMaxTimeForDay(Calendar calendar) {
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     * 存储函数 日期标记处理
     * dateFlag
     * 0 存储函数 按 年查询
     * 1 存储函数 按 月查询
     * 2 存储函数 按 天查询
     *
     * @param dateType
     */
    public int resolveDateFlag(String dateType) {
        int dateFlag = 2;
        switch (dateType) {
            case "0": // 当年  自定义月 都是按月统计
            case "4":
                dateFlag = 1;
                break;
            case "1":// 当月 当日  定义日 本周 都是按日 统计
            case "2":
            case "5":
            case "6":
                dateFlag = 2;
                break;
            case "3"://自定义年
                dateFlag = 0;
                break;
            default:
                break;
        }
        return dateFlag;
    }
}
