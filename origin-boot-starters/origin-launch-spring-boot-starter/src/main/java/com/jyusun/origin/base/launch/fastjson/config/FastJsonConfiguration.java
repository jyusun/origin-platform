package com.jyusun.origin.base.launch.fastjson.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import com.jyusun.origin.base.launch.fastjson.core.serializer.BooleanToIntegerSerializer;
import com.jyusun.origin.base.launch.fastjson.core.serializer.LocalDateSerializer;
import com.jyusun.origin.base.launch.fastjson.core.serializer.LocalDateTimeSerializer;
import com.jyusun.origin.base.launch.fastjson.core.serializer.LocalTimeSerializer;
import com.jyusun.origin.core.common.util.DateUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * FastJson 配置类
 *
 * @author jyusun
 * @since 1.0.0
 */
@Configuration
@ConditionalOnClass(JSON.class)
@ConditionalOnMissingBean(FastJsonHttpMessageConverter.class)
@ConditionalOnWebApplication
public class FastJsonConfiguration {

    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setFastJsonConfig(fastJsonConfig());
        converter.setSupportedMediaTypes(getSupportedMediaType());
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        return converter;
    }

    /**
     * fastJson的配置
     */
    private FastJsonConfig fastJsonConfig() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.DisableCircularReferenceDetect);
        fastJsonConfig.setDateFormat(DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS);
        fastJsonConfig.setCharset(StandardCharsets.UTF_8);
        // 自定义Boolean类型序列化配置
        SerializeConfig serializeConfig = fastJsonConfig.getSerializeConfig();
        serializeConfig.put(Boolean.class, new BooleanToIntegerSerializer());
        serializeConfig.put(LocalDateTime.class, new LocalDateTimeSerializer());
        serializeConfig.put(LocalDate.class, new LocalDateSerializer());
        serializeConfig.put(LocalTime.class, new LocalTimeSerializer());

        return fastJsonConfig;
    }

    /**
     * 支持的mediaType类型
     */
    private List<MediaType> getSupportedMediaType() {
        ArrayList<MediaType> mediaTypes = Lists.newArrayList();

        mediaTypes.add(MediaType.APPLICATION_JSON);
        mediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        mediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        mediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        mediaTypes.add(MediaType.APPLICATION_PDF);
        mediaTypes.add(MediaType.APPLICATION_RSS_XML);
        mediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        mediaTypes.add(MediaType.APPLICATION_XML);
        mediaTypes.add(MediaType.IMAGE_GIF);
        mediaTypes.add(MediaType.IMAGE_JPEG);
        mediaTypes.add(MediaType.IMAGE_PNG);
        mediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        mediaTypes.add(MediaType.TEXT_HTML);
        mediaTypes.add(MediaType.TEXT_MARKDOWN);
        mediaTypes.add(MediaType.TEXT_PLAIN);
        mediaTypes.add(MediaType.TEXT_XML);

        // 增加解析spring boot actuator结果的解析
        mediaTypes.add(MediaType.valueOf("application/vnd.spring-boot.actuator.v2+json"));

        return mediaTypes;
    }

}
