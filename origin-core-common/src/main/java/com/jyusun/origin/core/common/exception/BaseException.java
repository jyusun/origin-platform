package com.jyusun.origin.core.common.exception;

import lombok.Getter;

/**
 * 作用描述： - 基础异常 自定义异常
 *
 * @author jyusun
 * @date 2020/1/2 17:08
 * @since 1.0.0
 */
public abstract class BaseException extends RuntimeException {

    @Getter
    private final String code;

    /**
     * 异常信息构造函数
     * @param code {@code String} 消息编码
     * @param message {@code String} 消息描述
     */
    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 异常信息构造函数
     * @param code {@code String} 消息编码
     * @param message {@code String} 消息描述
     * @param cause {@link Throwable} 异常信息
     */
    public BaseException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}
