package com.jyusun.origin.base.logger.listener;

import com.jyusun.origin.base.logger.api.LoggerOutput;
import com.jyusun.origin.base.logger.event.ErrorLoggerEvent;
import com.jyusun.origin.base.logger.event.LoginLoggerEvent;
import com.jyusun.origin.base.logger.event.RequestLoggerEvent;
import com.jyusun.origin.base.logger.event.UsualLoggerEvent;
import com.jyusun.origin.base.logger.model.dto.ErrorLoggerDTO;
import com.jyusun.origin.base.logger.model.dto.LoginLoggerDTO;
import com.jyusun.origin.base.logger.model.dto.RequestLoggerDTO;
import com.jyusun.origin.base.logger.model.dto.UsualLoggerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * 接口日志 异步监听事件
 *
 * @author jyusun at 2019-08-08
 */
@Slf4j
@RequiredArgsConstructor
public class LoggerListener {

    private final LoggerOutput loggerOutput;

    @Async("loggerExecutor")
    @EventListener(RequestLoggerEvent.class)
    public void onRequestAppEvent(RequestLoggerEvent event) {
        RequestLoggerDTO source = (RequestLoggerDTO) event.getSource();
        loggerOutput.request(source);
    }

    @Async("loggerExecutor")
    @EventListener(ErrorLoggerEvent.class)
    public void onErrorAppEvent(ErrorLoggerEvent event) {
        ErrorLoggerDTO loggerDTO = (ErrorLoggerDTO) event.getSource();
        loggerOutput.error(loggerDTO);
    }

    @Async("loggerExecutor")
    @EventListener(LoginLoggerEvent.class)
    public void onLoginAppEvent(LoginLoggerEvent event) {
        LoginLoggerDTO source = (LoginLoggerDTO) event.getSource();
        loggerOutput.login(source);
    }

    @Async("loggerExecutor")
    @EventListener(UsualLoggerEvent.class)
    public void onUsualAppEvent(UsualLoggerEvent event) {
        UsualLoggerDTO source = (UsualLoggerDTO) event.getSource();
        loggerOutput.usual(source);
    }

}
