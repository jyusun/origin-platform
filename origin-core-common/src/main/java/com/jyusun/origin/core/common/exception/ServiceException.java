package com.jyusun.origin.core.common.exception;

/**
 * 作用描述： - 服务错误 自定义异常
 *
 * @author jyusun
 * @date 2020/1/2 17:08
 * @since 1.0.0
 */
public class ServiceException extends BaseException {

    /**
     * 异常信息构造函数
     * @param code {@code String} 消息编码
     * @param message {@code String} 消息描述
     */
    public ServiceException(String code, String message) {
        super(code, message);
    }

    /**
     * 异常信息构造函数
     * @param code {@code String} 消息编码
     * @param message {@code String} 消息描述
     * @param throwable {@link Throwable} 异常信息
     */
    public ServiceException(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

}
