package com.jyusun.origin.base.launch.server.core.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StringUtils;

/**
 * 作用描述： - 程序启动事件监听
 *
 * @author jyusun at 2020/3/16 12:44
 * @since 1.0.0
 */
@Slf4j
public class ServerListener {

    /**
     * webserver init 事件监听
     * @param event {@link WebServerInitializedEvent} 事件
     */
    @Async
    @Order
    @EventListener(WebServerInitializedEvent.class)
    public void afterStart(WebServerInitializedEvent event) {

        Environment environment = event.getApplicationContext().getEnvironment();
        String appName = environment.getProperty("spring.application.name");
        int localPort = event.getWebServer().getPort();
        String profile = StringUtils.arrayToCommaDelimitedString(environment.getActiveProfiles());
        log.info("---[{}]---启动完成，当前使用的端口:[{}]，环境变量:[{}]---", appName, localPort, profile);
    }

}
