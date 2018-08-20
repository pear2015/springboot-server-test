package com.gs.crms.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhufengjie on 2017/10/20.
 */
public class TimeUtil {
    private static Logger logger = LoggerFactory.getLogger(TimeUtil.class);

    /**
     * 构造
     */
    private TimeUtil() {

    }

    /**
     * 获取一天最开始时间
     *
     * @param calendar
     * @return
     */
    public Timestamp getMinTimeForDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     * 获取一天最大时间
     *
     * @param calendar
     * @return
     */
    public Timestamp getMaxTimeForDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     * 获取一天的最小时间
     */
    public static Date getMinDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一天的最小时间
     */
    public static Date getMaxDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 最近year年
     */
    public static Date getLateLyDate(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -year);
        return calendar.getTime();
    }

    /**
     * 最近days
     * @param days
     * @return
     */
    public static Date getLateLyDayDate(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -days);
        return calendar.getTime();
    }

    /**
     * 有效时间
     * @param days
     * @return
     */
    public static Date getInValidDate(Date date,int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date==null?new Date():date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
    /**
     * 日期转化
     *
     * @param time
     * @return
     */
    public static Date getDateByTime(String time) {
        if(StringUtils.isEmpty(time)){
            return  null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(format.format(Long.parseLong(time)));
        } catch (ParseException e) {
            logger.error("data resolve has ParseException", e);
            return null;
        }
    }

    /**
     * 日期转为String
     * @param time
     * @return
     */
    public static String  getDateFormatString(String time) {
        if(StringUtils.isEmpty(time)){
            return  null;
        }
        try {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(Long.parseLong(time));
        }catch (Exception e){
            logger.error("data resolve has ParseException", e);
            return null;
        }
    }
    /**
     * 根据出生日期计算年龄
     */
    public  static  int getAge(Date birth ,Date currentDate){
        if(birth==null){
            return 0;
        }
        if(birth.getTime()>currentDate.getTime()){
            return  0;
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(birth);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(currentDate);
        int result=calendar2.get(Calendar.YEAR)-calendar1.get(Calendar.YEAR);
        int month=calendar2.get(Calendar.MONTH)-calendar1.get(Calendar.MONTH);
        return getAgeByMonth(Math.abs(result*12+month));
    }

    /**
     * 根据总月份获取年
     * @param monthAll
     * @return
     */
    public static  int getAgeByMonth (int monthAll) {
        if(monthAll<=0){
            return  0;
        }
       return (int)Math.floor((double) monthAll/12);
    }
}
