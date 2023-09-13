package com.jyusun.origin.core.thread.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 默认的线程属性配置
 *
 * @author jyusun at 2021-7-7 13:33:21
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "origin-system.default-thread-pool")
public class DefaultThreadPool extends AbstractThreadPool {

    /**
     * 线程前缀
     */
    private String threadNamePrefix = "Def-Thread-";

}
