package com.jyusun.origin.core.common.clone;

import com.jyusun.origin.core.common.exception.ServiceException;

/**
 * 克隆异常
 *
 * @author jyusun
 * @since 1.0.0
 */
public class CloneRuntimeException extends ServiceException {

    /**
     * 异常信息构造函数
     * @param code {@code Integer} 消息编码
     * @param message {@code String} 消息描述
     * @param throwable {@link Throwable} 异常信息
     */
    public CloneRuntimeException(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

    /**
     * 异常信息构造函数
     * @param code {@code Integer} 消息编码
     * @param message {@code String} 消息描述
     */
    public CloneRuntimeException(String code, String message) {
        super(code, message);
    }

}
