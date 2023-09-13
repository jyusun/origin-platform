package com.jyusun.origin.base.mail.model.context.protocol;

import com.jyusun.origin.base.mail.common.enums.ProtocolEnum;
import com.jyusun.origin.core.common.util.SpringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

/**
 * 作用描述：
 *
 * @author jyusun at 2023/5/2 19:13
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class ProtocolFactory implements InitializingBean {

    private static final Map<ProtocolEnum, AbstractProtocol> PROTOCOL_MAP = new EnumMap<>(ProtocolEnum.class);

    public AbstractProtocol getHandler(ProtocolEnum protocol) {
        return PROTOCOL_MAP.get(protocol);
    }

    /**
     * 协议上下文初始化
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        SpringUtil.getContext()
            .getBeansOfType(AbstractProtocol.class)
            .values()
            .forEach(handler -> PROTOCOL_MAP.put(handler.getProtocol(), handler));
    }

}
