package com.jyusun.origin.base.logger.api;

import com.alibaba.fastjson.JSON;
import com.jyusun.origin.base.logger.model.dto.ErrorLoggerDTO;
import com.jyusun.origin.base.logger.model.dto.LoginLoggerDTO;
import com.jyusun.origin.base.logger.model.dto.RequestLoggerDTO;
import com.jyusun.origin.base.logger.model.dto.UsualLoggerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 作用描述：
 *
 * @author jyusun at 2023/3/1 14:14
 * @since 1.0.0
 */
@Component
@Slf4j
public class DefaultLoggerOutputImpl implements LoggerOutput {

    /**
     * @param loggerDTO
     */
    @Override
    public void login(LoginLoggerDTO loggerDTO) {
        log.info(JSON.toJSONString(loggerDTO));
    }

    /**
     * @param loggerDTO
     */
    @Override
    public void request(RequestLoggerDTO loggerDTO) {
        log.info(JSON.toJSONString(loggerDTO));
    }

    /**
     * @param loggerDTO
     */
    @Override
    public void usual(UsualLoggerDTO loggerDTO) {
        log.info(JSON.toJSONString(loggerDTO));
    }

    /**
     * @param loggerDTO
     */
    @Override
    public void error(ErrorLoggerDTO loggerDTO) {
        log.error(JSON.toJSONString(loggerDTO));
    }

}
