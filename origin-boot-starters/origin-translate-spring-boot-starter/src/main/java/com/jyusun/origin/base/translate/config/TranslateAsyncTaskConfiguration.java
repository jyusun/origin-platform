package com.jyusun.origin.base.translate.config;

import com.jyusun.origin.base.translate.config.props.TranslateThreadPool;
import com.jyusun.origin.core.thread.common.util.ExecutorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步任务配置
 *
 * @author jyusun at 2022-1-30 11:24:43
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(TranslateThreadPool.class)
@RequiredArgsConstructor
@ConditionalOnProperty(value = "origin-system.translate-thread-pool.enabled", matchIfMissing = true)
public class TranslateAsyncTaskConfiguration {

    private final TranslateThreadPool translateThreadPool;

    @Bean("translateExecutor")
    public Executor translateExecutor() {
        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        return ExecutorUtil.initExcutor(translateThreadPool, translateThreadPool.getThreadNamePrefix(),
                callerRunsPolicy);
    }

}
