package com.jyusun.origin.base.wecom.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 企业微信
 *
 * @author jyusun
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "origin-system.wecom")
public class WecomProperties {

    /**
     * 端点（访问域名）
     */
    private String endpoint;
    /**
     * 企业微信ID
     */
    private String corpid;
    /**
     * 企业微信授权
     */
    private String corpsecret;


}
