package com.jyusun.origin.base.logger.config.props;

import com.jyusun.origin.base.logger.common.enums.ActiveEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author sun
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "origin-system.logger")
public class SystemLoggerProperties {

    /**
     * isEnabled：true or false
     */
    private Boolean enabled = true;

    private ActiveEnum local;

    private ActiveEnum mq;

    private ActiveEnum rpc;

}
