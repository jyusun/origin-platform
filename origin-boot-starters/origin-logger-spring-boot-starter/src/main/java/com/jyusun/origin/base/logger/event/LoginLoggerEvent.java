package com.jyusun.origin.base.logger.event;

import com.jyusun.origin.base.logger.model.dto.LoginLoggerDTO;
import com.jyusun.origin.core.common.model.event.CoreEvent;

public class LoginLoggerEvent extends CoreEvent<LoginLoggerDTO> {

    public LoginLoggerEvent(LoginLoggerDTO source) {
        super(source);
    }

}
