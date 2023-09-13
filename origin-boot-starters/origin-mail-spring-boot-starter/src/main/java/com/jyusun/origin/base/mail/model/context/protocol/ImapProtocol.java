package com.jyusun.origin.base.mail.model.context.protocol;

import com.jyusun.origin.base.mail.common.constant.MailPropConstant;
import com.jyusun.origin.base.mail.common.enums.ProtocolEnum;
import com.jyusun.origin.base.mail.model.context.props.AccountProperties;
import com.jyusun.origin.base.mail.model.context.props.ServerProperties;

import java.util.Map;
import java.util.Properties;

/**
 * 作用描述：IMAP协议属性构建
 *
 * @author jyusun at 2023/5/2 19:08
 * @since 1.0.0
 */
public class ImapProtocol extends AbstractProtocol {

    /**
     * @return
     */
    @Override
    public ProtocolEnum getProtocol() {
        return ProtocolEnum.IMAP;
    }

    /**
     * @param context
     * @return
     */
    @Override
    public Properties initProps(AccountProperties accountProperties, ServerProperties serverProperties,
            Map<String, String> extProperties) {
        String protocol = this.getProtocol().getProtocol();

        Properties properties = new Properties();
        properties.setProperty(MailPropConstant.PROTOCOL_STORE, protocol);
        properties.setProperty(MailPropConstant.IMAP_HOST, serverProperties.getHost());
        properties.setProperty(MailPropConstant.IMAP_PORT, String.valueOf(serverProperties.getPort()));
        if (false) {
            properties.setProperty(MailPropConstant.IMAP_SSL_AUTH, MailPropConstant.IMAP_SSL_AUTH_VALUE);
            properties.setProperty(MailPropConstant.IMAP_SOCKET_FACTORY_CLASS,
                    MailPropConstant.IMAP_SOCKET_FACTORY_CLASS_VALUE);
            properties.setProperty(MailPropConstant.IMAP_SOCKET_FACTORY_PORT,
                    String.valueOf(serverProperties.getPort()));
            properties.setProperty(MailPropConstant.IMAP_SOCKET_FACTORY_FALLBACK,
                    MailPropConstant.IMAP_SOCKET_FACTORY_FALLBACK_VALUE);
            properties.setProperty(MailPropConstant.IMAP_PORT, String.valueOf(serverProperties.getPort()));
        }
        return properties;
    }

}
