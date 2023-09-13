package com.jyusun.origin.core.common.model.domain.event;

import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.context.ApplicationEvent;

import java.io.Serializable;

/**
 * 作用描述： - 领域事件 基础扩展
 *
 * @author jyusun at 2020/1/7 13:23
 * @since 1.0.0
 */
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractEvent<T> extends ApplicationEvent implements DomainEvent, Serializable {

    private final Serializable sid;

    protected transient T source;

    protected AbstractEvent(Serializable sid, T source) {
        super(source);
        this.source = source;
        this.sid = sid;
    }

    /**
     * 返回事件的唯一ID
     * @return 事件的唯一ID
     */
    @Override
    public Serializable sid() {
        return this.sid;
    }

    @Override
    public long timestamp() {
        return super.getTimestamp();
    }

    /**
     * 判断领域事件是否一致
     * @param other 另外一个领域事件
     * @return <code>true</code> 如果当前比较的两个领域事件一致返回true
     */
    @Override
    public boolean sameEventAs(DomainEvent other) {
        return false;
    }

}
