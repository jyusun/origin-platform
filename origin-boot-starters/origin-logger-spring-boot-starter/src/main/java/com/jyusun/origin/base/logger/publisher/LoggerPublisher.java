package com.jyusun.origin.base.logger.publisher;

import com.jyusun.origin.base.logger.event.ErrorLoggerEvent;
import com.jyusun.origin.base.logger.event.ExeSqlLoggerEvent;
import com.jyusun.origin.base.logger.event.LoginLoggerEvent;
import com.jyusun.origin.base.logger.event.RequestLoggerEvent;
import com.jyusun.origin.base.logger.event.UsualLoggerEvent;
import com.jyusun.origin.base.logger.model.dto.ErrorLoggerDTO;
import com.jyusun.origin.base.logger.model.dto.ExeSqlLoggerDTO;
import com.jyusun.origin.base.logger.model.dto.LoginLoggerDTO;
import com.jyusun.origin.base.logger.model.dto.RequestLoggerDTO;
import com.jyusun.origin.base.logger.model.dto.UsualLoggerDTO;
import com.jyusun.origin.core.common.util.SpringUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * API日志信息事件发送
 *
 * @author jyusun at 2019/8/12 20:07
 * @since 1.0.0
 */
@Slf4j
@UtilityClass
public final class LoggerPublisher {

    public static void requestEvent(RequestLoggerDTO requestLoggerDTO) {
        SpringUtil.publishEvent(new RequestLoggerEvent(requestLoggerDTO));
    }

    public static void errorEvent(ErrorLoggerDTO errorLogger) {
        SpringUtil.publishEvent(new ErrorLoggerEvent(errorLogger));
    }

    public static void loginEvent(LoginLoggerDTO loginLogger) {
        SpringUtil.publishEvent(new LoginLoggerEvent(loginLogger));
    }

    public static void usualEvent(UsualLoggerDTO logUsual) {
        SpringUtil.publishEvent(new UsualLoggerEvent(logUsual));
    }

    public static void exeSqlEvent() {
        SpringUtil.publishEvent(new ExeSqlLoggerEvent(new ExeSqlLoggerDTO()));
    }

}
