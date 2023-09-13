package com.jyusun.origin.base.security.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Jwt 属性配置
 *
 * @author jyusun at 2022-08-01 17:58:42
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "origin-system.jwt")
public class JwtTokenProperties {

    /**
     * isEnabled：true or false
     */
    private Boolean enabled = true;

    /**
     * header
     */
    private String header;

    /**
     * 有效期1天(单位:s)
     */
    private Long expire;

    /**
     * 秘钥
     */
    private String secret;

    /**
     * 签发者
     */
    private String issuer;

    public JwtTokenProperties() {
        this.init();
    }

    private void init() {
        this.enabled = true;
        this.expire = 5184000L;
        this.secret = "";
        this.issuer = "jyusun";
    }

}
