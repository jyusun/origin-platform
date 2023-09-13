package com.jyusun.origin.core.thread.common.util;

import com.jyusun.origin.core.thread.config.props.AbstractThreadPool;
import lombok.experimental.UtilityClass;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;

/**
 * 线程初始化工具
 *
 * @author jyusun at 2022-06-09 23:25:03
 */
@UtilityClass
public class ExecutorUtil {

    public static Executor initExcutor(AbstractThreadPool abstractThreadPool, String threadName,
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
