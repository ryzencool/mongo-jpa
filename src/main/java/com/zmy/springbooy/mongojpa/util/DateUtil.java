package com.zmy.springbooy.mongojpa.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 *
 * Created by ztex on 2017/9/30.
 */
public class DateUtil {

    /**
     * 获取当前时间的格式化结果
     *
     * @param format
     * @return
     * @example formatCurrent('dd/MM/yy')
     */
    public static String formatCurrentTime(String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime currentDate = LocalDateTime.now();
        String currentDateFormatted = currentDate.format(dateTimeFormatter);
        return currentDateFormatted;
    }

    /**
     * 格式化时间 年月日时分秒
     * @param localDateTime
     * @param format
     * @return
     */
    public static String formatDateTime(LocalDateTime localDateTime, String format) {
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 格式化日期 年月日
     * @param localDate
     * @param format
     * @return
     */
    public static String formatDate(LocalDate localDate, String format) {
        return localDate.format(DateTimeFormatter.ofPattern(format));
    }


    /**
     * 根据给定格式字符串格式化为LocalDate对象
     *
     * @param originDate 初始的时间
     * @return
     */
    public static LocalDateTime parseDateTime(String originDate, String format) {
        return LocalDateTime.parse(originDate, DateTimeFormatter.ofPattern(format));
    }

    public static LocalDate parseDate(String originDate, String format) {
        return LocalDate.parse(originDate, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 获取两个日期间隔的天数
     * @param startDate
     * @param endDate
     * @param format
     * @return
     */
    public static int getIntervalDay(String startDate, String endDate, String format) {
        LocalDate start = parseDate(startDate, format);
        LocalDate end = parseDate(endDate, format);
        return Period.between(start, end).getDays();

    }

    /**
     * 获取两个日期间隔的月数
     * @param startDate
     * @param endDate
     * @param format
     * @return
     */
    public static int getIntervalMonth(String startDate, String endDate, String format) {
        LocalDate start = parseDate(startDate, format);
        LocalDate end = parseDate(endDate, format);
        return Period.between(start, end).getMonths();
    }

    /**
     * 获取两个日期之间间隔的年份
     * @param startDate
     * @param endDate
     * @param format
     * @return
     */
    public static int getIntervalYear(String startDate, String endDate, String format) {
        LocalDate start = parseDate(startDate, format);
        LocalDate end = parseDate(endDate, format);
        return Period.between(start, end).getYears();
    }

}
