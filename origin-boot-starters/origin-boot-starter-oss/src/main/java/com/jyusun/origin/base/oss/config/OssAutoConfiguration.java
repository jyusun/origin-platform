package com.jyusun.origin.base.oss.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Oss 自动装配
 *
 * @author jyusun
 */
@Import({AliossConfiguration.class, QiniuConfiguration.class, LocalConfiguration.class})
@Configuration
@ConditionalOnProperty(prefix = "origin-system.oss", name = "enabled", matchIfMissing = true)
public class OssAutoConfiguration {
}


