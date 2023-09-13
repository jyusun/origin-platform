package com.jyusun.origin.base.logger.event;

import com.jyusun.origin.base.logger.model.dto.UsualLoggerDTO;
import com.jyusun.origin.core.common.model.event.CoreEvent;

public class UsualLoggerEvent extends CoreEvent<UsualLoggerDTO> {

    public UsualLoggerEvent(UsualLoggerDTO source) {
        super(source);
    }

}
