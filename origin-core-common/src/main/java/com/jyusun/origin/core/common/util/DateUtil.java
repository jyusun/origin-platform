package com.jyusun.origin.core.common.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * 时间处理工具
 * <p>
 * 作用描述：Date、LocalDate、LocalDateTime 格式处理
 *
 * @author jyusun
 * @date 2021/7/5 22:26
 * @since 1.0.0
 */
@UtilityClass
public class DateUtil {

    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String PATTERN_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";

    public static final String PATTERN_HH_MM_SS = "HH:mm:ss";

    public static final String PATTERN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String PATTERN_YYYYMMDDHHMM = "yyyyMMddHHmm";

    public static final String PATTERN_YYYYMMDD = "yyyyMMdd";

    public static final String PATTERN_YYYYMM = "yyyyMM";

    public static final String PATTERN_YYYY = "yyyy";

    public static final String PATTERN_MM = "MM";

    public static final String PATTERN_DD = "dd";

    public static final String PATTERN_HHMMSS = "HHmmss";

    public static final String PATTERN_HHMM = "HHmm";

    /**
     * LocalDateTime To String
     * @param dateTime {@link LocalDateTime} 转换时间
     * @param format {@code String } 转换格式
     * @return {@code String} 时间
     */
    public static String toStr(LocalDateTime dateTime, String format) {
        return Objects.nonNull(dateTime) ? dateTime.format(DateTimeFormatter.ofPattern(format)) : StringUtil.EMPTY;
    }

    /**
     * LocalDate To String
     * @param date {@link LocalDate} 转换时间
     * @param format {@code String } 转换格式
     * @return {@code String} 时间
     */
    public static String toStr(LocalDate date, String format) {
        return Objects.nonNull(date) ? date.format(DateTimeFormatter.ofPattern(format)) : StringUtil.EMPTY;
    }

    /**
     * LocalTime To String
     * @param time {@link LocalTime} 转换时间
     * @param format {@code String } 转换格式
     * @return {@code LocalTime} 字符串时间
     */
    public static String toStr(LocalTime time, String format) {
        return time.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 当前日期 LocalDateTime To String
     * @param format {@code String } 转换格式
     * @return {@code String} 字符串日期时间
     */
    public static String thisDateTimeToStr(String format) {
        return toStr(LocalDateTime.now(), format);
    }

    /**
     * 当前日期 LocalDate To String
     * @param format {@code String } 转换格式
     * @return {@code String} 字符串日期
     */
    public static String thisDateToStr(String format) {
        return toStr(LocalDate.now(), format);
    }

    /**
     * 获取当前日期
     * @return {@code String} 格式为yyyyMMdd字符串日期
     */
    public static String thisDateToStrYmd() {
        return toStr(LocalDate.now(), PATTERN_YYYYMMDD);
    }

    /**
     * 当前时间 LocalTime To String
     * @param format {@code String } 转换格式
     * @return {@code String} 字符串时间
     */
    public static String thisTimeToStr(String format) {
        return toStr(LocalTime.now(), format);
    }

    /**
     * String to LocalDateTime
     * @param dateTime {@code String} 转换时间
     * @param format {@code String } 转换格式
     * @return
     */
    public static LocalDateTime toLocalDateTime(String dateTime, String format) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(format));
    }

    /**
     * Date to LocalDateTime
     * @param date {@code Date} 时间
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * String to LocalDate
     * @param date {@code String} 转换时间
     * @param format {@code String } 转换格式
     * @return {@link LocalDate}
     */
    public static LocalDate toLocalDate(String date, String format) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
    }

    /**
     * String to LocalTime
     * @param time {@code String} 转换时间
     * @param format {@code String } 转换格式
     * @return {@link LocalTime}
     */
    public static LocalTime toLocalTime(String time, String format) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(format));
    }

    /**
     * LocalDateTime to Date
     * @param dateTime {@code LocalDateTime} 转换时间
     * @return {@link Date}
     */
    public static Date toDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 字符串日期格式转换
     * @param date {@code String} 字符串日期
     * @param thisFormat {@code String} 当前日期格式
     * @param toFormat {@code String} 转换后格式
     * @return {@code String} 转换后的时间格式
     */
    public static String strDateConvertFormat(String date, String thisFormat, String toFormat) {
        return StringUtils.isBlank(date) ? StringUtil.EMPTY
                : toLocalDate(date, thisFormat).format(DateTimeFormatter.ofPattern(toFormat));
    }

    /**
     * 当前时间戳
     * @return {@code Long} 时间戳
     */
    public long timeStamp() {
        return System.currentTimeMillis();
    }

    /**
     * dateTime的时间戳
     * @return {@code Long} 时间戳
     */
    public long toTimeStamp(LocalDateTime dateTime) {
        return dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * timeMillis 时间戳 zoneId 时区
     * @return {@code Long} 时间
     */
    public LocalDateTime toDateTime(Long timeMillis, ZoneId zoneId) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeMillis), zoneId);
    }

}
