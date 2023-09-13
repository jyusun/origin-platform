package com.jyusun.origin.core.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 实现对SpringContext管理
 *
 * @author jyusun
 */
@Slf4j
@Lazy(false)
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringUtil implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getContext() {
        if (ObjectUtil.isEmpty(applicationContext)) {
            throw new IllegalStateException("applicationContext属性为null,请检查是否注入了SpringUtil");
        }
        return applicationContext;
    }

    public static <T> T getBean(String beanName) {
        return (T) getContext().getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getContext().getBean(clazz);
    }

    public static void publishEvent(ApplicationEvent event) {
        log.info("事件发送：{}", event.getTimestamp());
        getContext().publishEvent(event);
    }

    /**
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * 清除SpringContextHolder中的ApplicationContext为Null.
     */
    private static void clearHolder() {
        applicationContext = null;
    }

    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    @SneakyThrows
    public void destroy() {
        SpringUtil.clearHolder();
    }

}
