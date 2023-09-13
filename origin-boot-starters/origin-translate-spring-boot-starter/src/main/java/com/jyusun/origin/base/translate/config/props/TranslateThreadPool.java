package com.jyusun.origin.base.translate.config.props;

import com.jyusun.origin.core.thread.config.props.AbstractThreadPool;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 文档库属性配置
 *
 * @author jyusun at 2021-7-7 13:33:21
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "origin-system.translate-thread-pool")
public class TranslateThreadPool extends AbstractThreadPool {

    /**
     * 线程前缀
     */
    private String threadNamePrefix = "Trans-Thread-";

}
