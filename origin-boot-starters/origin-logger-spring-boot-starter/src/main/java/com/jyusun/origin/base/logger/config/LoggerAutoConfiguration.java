package com.jyusun.origin.base.logger.config;

import com.jyusun.origin.base.logger.api.LoggerOutput;
import com.jyusun.origin.base.logger.aspect.SystemLoggerAspect;
import com.jyusun.origin.base.logger.config.props.SystemLoggerProperties;
import com.jyusun.origin.base.logger.listener.LoggerListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志工具自动配置
 *
 * @author jyusun at 2019/1/2 20:07
 * @since 1.0.0
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(SystemLoggerProperties.class)
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "origin-system.logger", name = "enabled", matchIfMissing = true)
public class LoggerAutoConfiguration {

    /**
     * 请求日志切面
     * @return {@link SystemLoggerAspect}
     */
    @Bean
    public SystemLoggerAspect sysLoggerAspect() {
        log.info("===  System Logger Aspect Init...");
        return new SystemLoggerAspect();
    }

    @Bean
    public LoggerListener loggerListener(LoggerOutput loggerOutput) {
        log.info("===  Logger Listener Init...");
        return new LoggerListener(loggerOutput);
    }

}
