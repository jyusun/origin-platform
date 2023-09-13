package com.jyusun.origin.base.logger.event;

import com.jyusun.origin.base.logger.model.dto.ErrorLoggerDTO;
import com.jyusun.origin.core.common.model.event.CoreEvent;

public class ErrorLoggerEvent extends CoreEvent<ErrorLoggerDTO> {

    public ErrorLoggerEvent(ErrorLoggerDTO source) {
        super(source);
    }

}
