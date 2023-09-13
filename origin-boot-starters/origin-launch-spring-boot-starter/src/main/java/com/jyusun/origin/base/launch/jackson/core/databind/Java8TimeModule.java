package com.jyusun.origin.base.launch.jackson.core.databind;

import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.jyusun.origin.core.common.util.DateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 前后端交互时间格式化
 *
 * @author jyusun at 2021-10-27 18:01:41
 * @since 1.0.0
 */
public class Java8TimeModule extends SimpleModule {

    /**
     * JDK8时间格式默认配置
     */
    public Java8TimeModule() {
        super(PackageVersion.VERSION);
        this.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS)));
        this.addSerializer(LocalDate.class,
                new LocalDateSerializer(DateTimeFormatter.ofPattern(DateUtil.PATTERN_YYYY_MM_DD)));
        this.addSerializer(LocalTime.class,
                new LocalTimeSerializer(DateTimeFormatter.ofPattern(DateUtil.PATTERN_HH_MM_SS)));
        this.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS)));
        this.addDeserializer(LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ofPattern(DateUtil.PATTERN_YYYY_MM_DD)));
        this.addDeserializer(LocalTime.class,
                new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DateUtil.PATTERN_HH_MM_SS)));
    }

}