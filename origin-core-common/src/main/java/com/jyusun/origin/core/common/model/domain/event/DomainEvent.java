package com.jyusun.origin.core.common.model.domain.event;

import java.io.Serializable;

/**
 * 领域事件可能有唯一标识
 *
 * @author jyusun at 2022-04-10 00:48:16
 */
public interface DomainEvent {

    /**
     * 返回事件的唯一ID
     * @return 事件的唯一ID
     */
    Serializable sid();

    /**
     * 返回事件的产生的时间
     * @return 事件的产生的时间
     */
    long timestamp();

    /**
     * 判断领域事件是否一致
     * @param other 另外一个领域事件
     * @return <code>true</code> 如果当前比较的两个领域事件一致返回true
     */
    boolean sameEventAs(DomainEvent other);

}
