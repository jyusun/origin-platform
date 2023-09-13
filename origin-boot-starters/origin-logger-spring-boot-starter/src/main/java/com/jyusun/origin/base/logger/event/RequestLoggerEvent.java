package com.jyusun.origin.base.logger.event;

import com.jyusun.origin.base.logger.model.dto.RequestLoggerDTO;
import com.jyusun.origin.core.common.model.event.CoreEvent;

public class RequestLoggerEvent extends CoreEvent<RequestLoggerDTO> {

    public RequestLoggerEvent(RequestLoggerDTO source) {
        super(source);
    }

}
