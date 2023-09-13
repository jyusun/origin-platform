package com.jyusun.origin.core.thread.config.props;

import lombok.Getter;
import lombok.Setter;

/**
 * 文档库属性配置
 *
 * @author jyusun at 2021-7-7 13:33:21
 */
@Getter
@Setter
public abstract class AbstractThreadPool {

    /**
     * 启用
     */
    private Boolean enabled;

    /**
     * 核心线程数
     */
    private Integer corePoolSize;

    /**
     * 最大线程数
     */
    private Integer maxPoolSize;

    /**
     * 队列容量
     */
    private Integer queueCapacity;

    /**
     * 队列容量
     */
    private Integer keepAliveSeconds;

    public AbstractThreadPool() {
        this.enabled = true;
        this.corePoolSize = 2;
        this.maxPoolSize = 2;
        this.queueCapacity = 10;
        this.keepAliveSeconds = 60;
    }

}
