package com.jyusun.origin.base.launch.web.config;

import com.jyusun.origin.core.common.util.DateUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;

/**
 * @author jyusun
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * 不需要拦截地址
     */
    public static final String[] EXCLUDE_URLS = { "/login", "/logout", "/refresh" };

    /**
     * 日期格式化
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setTimeFormatter(DateTimeFormatter.ofPattern(DateUtil.PATTERN_HH_MM_SS));
        registrar.setDateFormatter(DateTimeFormatter.ofPattern(DateUtil.PATTERN_YYYY_MM_DD));
        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern(DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
        registrar.registerFormatters(registry);
    }

    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    // registry.addInterceptor(getHeaderInterceptor())
    // .addPathPatterns("/**")
    // .excludePathPatterns(EXCLUDE_URLS)
    // .order(-10);
    // }

    // /**
    // * 自定义请求头拦截器
    // */
    // public HttpHeaderInterceptor getHeaderInterceptor() {
    // return new HeaderInterceptor();
    // }

}
