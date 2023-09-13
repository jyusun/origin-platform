package com.jyusun.origin.core.common.model.domain.valueobject;

import java.io.Serializable;

/**
 * 作用描述： - 基础值对象
 *
 * @author jyusun at 2020/1/7 15:11
 * @since 1.0.0
 */
public interface BaseValue<T> extends Serializable {

    /**
     * 值对象通过属性比较，没有唯一ID
     * @param other 另外的值对象
     * @return <code>true</code> 属性比较一致时返回true
     */
    boolean sameValueAs(T other);

}
