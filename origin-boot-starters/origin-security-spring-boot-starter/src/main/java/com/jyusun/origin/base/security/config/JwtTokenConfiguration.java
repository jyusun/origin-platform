package com.jyusun.origin.base.security.config;

import com.jyusun.origin.base.security.config.props.JwtTokenProperties;
import com.jyusun.origin.base.security.handler.JwtInfoHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jwt Token 配置
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({ JwtTokenProperties.class })
public class JwtTokenConfiguration {

    @Bean
    public JwtInfoHandler jwtHelper(JwtTokenProperties jwtTokenProperties) {
        return new JwtInfoHandler(jwtTokenProperties);
    }

}
