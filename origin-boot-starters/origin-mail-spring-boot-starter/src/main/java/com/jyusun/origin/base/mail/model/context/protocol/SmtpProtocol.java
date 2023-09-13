package com.jyusun.origin.base.mail.model.context.protocol;

import com.jyusun.origin.base.mail.common.enums.ProtocolEnum;
import com.jyusun.origin.base.mail.model.context.props.AccountProperties;
import com.jyusun.origin.base.mail.model.context.props.ServerProperties;

import java.util.Map;
import java.util.Properties;

/**
 * 作用描述：SMTP 协议属性构建
 *
 * @author jyusun at 2023/5/2 19:08
 * @since 1.0.0
 */
public class SmtpProtocol extends AbstractProtocol {

    /**
     * @return
     */
    @Override
    public ProtocolEnum getProtocol() {
        return ProtocolEnum.IMAP;
    }

    /**
     * @param accountProperties
     * @param serverProperties
     * @param extProperties
     * @return
     */
    @Override
    public Properties initProps(AccountProperties accountProperties, ServerProperties serverProperties,
            Map<String, String> extProperties) {
        return null;
    }

}
