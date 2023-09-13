package com.jyusun.origin.core.common.clone;

import com.jyusun.origin.core.common.enums.SystemResultEnum;

/**
 * 克隆支持类，提供默认的克隆方法
 *
 * @param <T> 继承类的类型
 * @author jyusun
 */
public class CloneSupport<T> implements Cloneable<T> {

    @SuppressWarnings("unchecked")
    @Override
    public T clone() {
        try {
            return (T) super.clone();
        }
        catch (CloneNotSupportedException throwable) {
            SystemResultEnum resultEnum = SystemResultEnum.INTERNAL_SERVER_ERROR;
            throw new CloneRuntimeException(resultEnum.code(), resultEnum.desc(), throwable);
        }
    }

}
