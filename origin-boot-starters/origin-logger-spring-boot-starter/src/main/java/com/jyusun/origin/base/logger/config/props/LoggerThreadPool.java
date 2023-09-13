package com.jyusun.origin.base.logger.config.props;

import com.jyusun.origin.core.thread.config.props.AbstractThreadPool;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 日志属性线程配置
 *
 * @author jyusun at 2021-7-7 13:33:21
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "origin-system.logger.thread-pool")
public class LoggerThreadPool extends AbstractThreadPool {

    /**
     * 线程前缀
     */
    private String threadNamePrefix = "Log-Thread-";

}
