package com.jyusun.origin.base.mail.model.context.protocol;

import com.jyusun.origin.base.mail.common.enums.ProtocolEnum;
import com.jyusun.origin.base.mail.model.context.props.AccountProperties;
import com.jyusun.origin.base.mail.model.context.props.ServerProperties;

import java.util.Map;
import java.util.Properties;

/**
 * 作用描述：
 *
 * @author jyusun at 2023/5/2 19:05
 * @since 1.0.0
 */
public abstract class AbstractProtocol {

    public abstract ProtocolEnum getProtocol();

    public abstract Properties initProps(AccountProperties accountProperties, ServerProperties serverProperties,
            Map<String, String> extProperties);

}
