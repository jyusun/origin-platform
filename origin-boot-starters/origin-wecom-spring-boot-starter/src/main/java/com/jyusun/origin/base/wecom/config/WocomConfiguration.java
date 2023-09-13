package com.jyusun.origin.base.wecom.config;

import com.jyusun.origin.base.wecom.config.props.WecomProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 企业微信配置
 *
 * @author jyusun at 2022-4-12 21:06:22
 */
@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties({WecomProperties.class})
public class WocomConfiguration {

    private final WocomConfiguration wocomConfiguration;

}
