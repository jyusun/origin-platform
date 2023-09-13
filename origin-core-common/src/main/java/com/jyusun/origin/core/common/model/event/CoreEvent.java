package com.jyusun.origin.core.common.model.event;

import org.springframework.context.ApplicationEvent;

/**
 * 应用事件
 *
 * @author jyusun at 2019-08-07
 */
public class CoreEvent<T> extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    public CoreEvent(T source) {
        super(source);
    }

}
