package com.jyusun.origin.core.common.exception;

/**
 * 验证异常
 *
 * @author jyusun at 2021-10-8 09:48:55
 * @since 1.0.0
 */
public class ValidateException extends BusinessException {

    /**
     * 异常信息构造函数
     * @param code {@code String} 消息编码
     * @param message {@code String} 消息描述
     */
    public ValidateException(String code, String message) {
        super(code, message);
    }

    /**
     * 业务异常信息构造函数
     * @param code {@code String} 消息编码
     * @param message {@code String} 消息描述
     * @param throwable {@link Throwable} 异常信息
     */
    public ValidateException(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

}
