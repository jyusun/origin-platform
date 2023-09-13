package com.jyusun.origin.base.mail.model.context;

import com.google.common.collect.Maps;
import com.jyusun.origin.base.mail.model.context.props.AccountProperties;
import com.jyusun.origin.base.mail.model.context.props.ReceiveModeProperties;
import com.jyusun.origin.base.mail.model.context.props.ServerProperties;
import lombok.Getter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 作用描述：邮件配置管理
 *
 * @author jyusun at 2023/5/3 16:07
 * @since 1.0.0
 */

public class MailConfigDirector {

    private final String protocol;

    @Getter
    private MailConfigContext configContext;

    public MailConfigDirector(String protocol) {
        this.protocol = protocol;
    }

    public void construct(AccountProperties accountProps, ServerProperties serverProps,
            ReceiveModeProperties receiveModeProperties, Map<String, String> configExtProps, Charset defaultEncoding,
            Boolean sslAuth, Boolean testConnection) {
        this.configContext = MailConfigContext.builder()
            .protocol(this.protocol)
            .accountProps(accountProps)
            .serverProps(serverProps)
            .readModeProps(receiveModeProperties)
            .extProps(configExtProps)
            .defaultEncoding(defaultEncoding)
            .sslAuth(sslAuth)
            .testConnection(testConnection)
            .build();
    }

    public void construct(AccountProperties accountProps, ServerProperties serverProps,
            ReceiveModeProperties receiveModeProperties, Map<String, String> configExtProps) {
        this.construct(accountProps, serverProps, receiveModeProperties, configExtProps, StandardCharsets.UTF_8, false,
                false);
    }

    public void construct(AccountProperties accountProps, ServerProperties serverProps,
            ReceiveModeProperties receiveModeProperties) {
        this.construct(accountProps, serverProps, receiveModeProperties, Maps.newHashMap());
    }

}
