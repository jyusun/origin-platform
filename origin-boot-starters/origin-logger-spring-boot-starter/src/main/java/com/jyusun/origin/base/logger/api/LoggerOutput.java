package com.jyusun.origin.base.logger.api;

import com.jyusun.origin.base.logger.model.dto.ErrorLoggerDTO;
import com.jyusun.origin.base.logger.model.dto.LoginLoggerDTO;
import com.jyusun.origin.base.logger.model.dto.RequestLoggerDTO;
import com.jyusun.origin.base.logger.model.dto.UsualLoggerDTO;

/**
 * 作用描述：
 *
 * @author jyusun at 2023/3/1 14:14
 * @since 1.0.0
 */
public interface LoggerOutput {

    void login(LoginLoggerDTO loggerDTO);

    void request(RequestLoggerDTO loggerDTO);

    void usual(UsualLoggerDTO loggerDTO);

    void error(ErrorLoggerDTO loggerDTO);

}
