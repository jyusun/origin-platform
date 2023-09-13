package com.jyusun.origin.core.common.clone;

/**
 * 对象浅复制，克隆接口
 *
 * @param <T> 实现克隆接口的类型
 * @author jyusun
 */
public interface Cloneable<T> extends java.lang.Cloneable {

    /**
     * 浅复制
     * @return 克隆后的对象
     * @throws CloneNotSupportedException 克隆异常
     */
    T clone() throws CloneNotSupportedException;

}
