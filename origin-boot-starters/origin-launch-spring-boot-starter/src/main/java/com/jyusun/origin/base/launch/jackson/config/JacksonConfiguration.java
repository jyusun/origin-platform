package com.jyusun.origin.base.launch.jackson.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jyusun.origin.base.launch.jackson.core.databind.BooleanModule;
import com.jyusun.origin.base.launch.jackson.core.databind.Java8TimeModule;
import com.jyusun.origin.core.common.util.DateUtil;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * jackson 配置
 *
 * @author jyusun
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
// @Configuration(proxyBeanMethods = false)
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JacksonConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            builder.locale(Locale.CHINA);
            builder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
            builder.simpleDateFormat(DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS);
            // lang类型响应前端精度缺失问题
            builder.serializerByType(Long.class, ToStringSerializer.instance);
            // jdk8时间序列化
            builder.modules(new Java8TimeModule(),
                    // Boolean 值转换
                    new BooleanModule());
        };
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}