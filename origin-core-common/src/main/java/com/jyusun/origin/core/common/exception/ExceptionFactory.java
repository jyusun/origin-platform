package com.jyusun.origin.core.common.exception;

import com.jyusun.origin.core.common.model.BaseKvEnum;
import lombok.experimental.UtilityClass;

/**
 * 异常信息工厂类
 *
 * @author jyusun at 22:31:09
 */
@UtilityClass
public class ExceptionFactory {

    /**
     * 异常信息构造函数
     * @param code {@code String} 消息编码
     * @param message {@code String} 消息描述
     */
    public static BusinessException createBiz(String code, String message) {
        return new BusinessException(code, message);
    }

    /**
     * 异常信息构造函数
     * @param baseKvEnum {@code BaseKvEnum} 错误消息
     */
    public static BusinessException createBiz(BaseKvEnum baseKvEnum) {
        return createBiz(baseKvEnum.code(), baseKvEnum.desc());
    }

    /**
     * 异常信息构造函数
     * @param code {@code Integer} 消息编码
     * @param message {@code String} 消息描述
     * @param throwable {@link Throwable} 抛出异常
     */
    public static BusinessException createBiz(String code, String message, Throwable throwable) {
        return new BusinessException(code, message, throwable);
    }

    /**
     * 异常信息构造函数
     * @param baseKvEnum {@code BaseKvEnum} 错误消息
     * @param throwable {@link Throwable} 抛出异常
     */
    public static BusinessException createBiz(BaseKvEnum baseKvEnum, Throwable throwable) {
        return new BusinessException(baseKvEnum.code(), baseKvEnum.desc(), throwable);
    }

    /**
     * 异常信息构造函数
     * @param code {@code String} 消息编码
     * @param message {@code String} 消息描述
     */
    public static ValidateException createValidate(String code, String message) {
        return new ValidateException(code, message);
    }

    /**
     * 异常信息构造函数
     * @param baseKvEnum {@code BaseKvEnum} 错误消息
     */
    public static ValidateException createValidate(BaseKvEnum baseKvEnum) {
        return createValidate(baseKvEnum.code(), baseKvEnum.desc());
    }

    /**
     * 异常信息构造函数
     * @param code {@code String} 消息编码
     * @param message {@code String} 消息描述
     * @param throwable {@link Throwable} 抛出异常
     */
    public static ValidateException createValidate(String code, String message, Throwable throwable) {
        return new ValidateException(code, message, throwable);
    }

    /**
     * 异常信息构造函数
     * @param baseKvEnum {@code BaseKvEnum} 错误消息
     * @param throwable {@link Throwable} 抛出异常
     */
    public static ValidateException createValidate(BaseKvEnum baseKvEnum, Throwable throwable) {
        return createValidate(baseKvEnum.code(), baseKvEnum.desc(), throwable);
    }

    /**
     * 异常信息构造函数
     * @param code {@code String} 消息编码
     * @param message {@code String} 消息描述
     */
    public static ServiceException createService(String code, String message) {
        return new ServiceException(code, message);
    }

    /**
     * 异常信息构造函数
     * @param baseKvEnum {@code BaseKvEnum} 错误消息
     */
    public static ServiceException createService(BaseKvEnum baseKvEnum) {
        return createService(baseKvEnum.code(), baseKvEnum.desc());
    }

    /**
     * 异常信息构造函数
     * @param code {@code String} 消息编码
     * @param message {@code String} 消息描述
     */
    public static SecureException createSecure(String code, String message) {
        return new SecureException(code, message);
    }

    /**
     * 异常信息构造函数
     * @param baseKvEnum {@code BaseKvEnum} 错误消息
     */
    public static SecureException createSecure(BaseKvEnum baseKvEnum) {
        return createSecure(baseKvEnum.code(), baseKvEnum.desc());
    }

    /**
     * 异常信息构造函数
     * @param baseKvEnum {@code BaseKvEnum} 错误消息
     * @param message {@code String} 消息描述
     */
    public static SecureException createSecure(BaseKvEnum baseKvEnum, String message) {
        return createSecure(baseKvEnum.code(), message);
    }

    /**
     * 异常信息构造函数
     * @param code {@code String} 消息编码
     * @param message {@code String} 消息描述
     * @param throwable {@link Throwable} 抛出异常
     */
    public static ServiceException createService(String code, String message, Throwable throwable) {
        return new ServiceException(code, message, throwable);
    }

    /**
     * 异常信息构造函数
     * @param baseKvEnum {@code BaseKvEnum} 错误消息
     * @param throwable {@link Throwable} 抛出异常
     */
    public static ServiceException createService(BaseKvEnum baseKvEnum, Throwable throwable) {
        return createService(baseKvEnum.code(), baseKvEnum.desc(), throwable);
    }

}
