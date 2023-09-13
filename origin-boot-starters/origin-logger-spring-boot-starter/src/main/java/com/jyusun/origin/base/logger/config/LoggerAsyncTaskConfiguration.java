package com.jyusun.origin.base.logger.config;

import com.jyusun.origin.base.logger.config.props.LoggerThreadPool;
import com.jyusun.origin.core.thread.config.props.AbstractThreadPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步任务配置
 *
 * @author jyusun at 2022-1-30 11:24:43
 */
@Slf4j
@EnableAsync
@Configuration
@EnableConfigurationProperties(LoggerThreadPool.class)
@RequiredArgsConstructor
public class LoggerAsyncTaskConfiguration {

    private final LoggerThreadPool loggerThreadPool;

    @Bean("loggerExecutor")
    public Executor loggerExecutor() {
        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        return initExcutor(loggerThreadPool, loggerThreadPool.getThreadNamePrefix(), callerRunsPolicy);
    }

    private Executor initExcutor(AbstractThreadPool abstractThreadPool, String threadName,
            RejectedExecutionHandler rejectedExecutionHandler) {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(abstractThreadPool.getCorePoolSize());
        threadPool.setMaxPoolSize(abstractThreadPool.getMaxPoolSize());
        threadPool.setKeepAliveSeconds(abstractThreadPool.getKeepAliveSeconds());
        threadPool.setQueueCapacity(abstractThreadPool.getQueueCapacity());
        threadPool.setThreadNamePrefix(threadName);
        threadPool.setRejectedExecutionHandler(rejectedExecutionHandler);
        return threadPool;
    }

}
