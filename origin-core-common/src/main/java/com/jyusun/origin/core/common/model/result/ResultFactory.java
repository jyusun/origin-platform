package com.jyusun.origin.core.common.model.result;

import com.jyusun.origin.core.common.enums.ResultTypeEnum;
import com.jyusun.origin.core.common.enums.SystemResultEnum;
import com.jyusun.origin.core.common.exception.BusinessException;
import com.jyusun.origin.core.common.model.BaseKvEnum;
import com.jyusun.origin.core.common.util.AssemblerUtil;
import com.jyusun.origin.core.common.util.AssertUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.experimental.UtilityClass;

import java.io.Serializable;
import java.util.Optional;

/**
 * 作用描述： 响应结果静态工厂
 *
 * @author jyusun at 2020/3/9 21:10
 * @since 1.0.0
 */
@UtilityClass
public class ResultFactory {

    /**
     * 数据传输结果响应
     * @param code {@code String} 消息编码
     * @param message {@code String} 消息描述
     * @param data {@code Object} 承载数据
     * @param <T> {@code T} 泛型标记
     * @return {@link AbstractResult} 响应结果
     */
    public static <T> AbstractResult<T> data(String code, String message, T data) {
        return Optional.ofNullable(data)
            .map(obj -> new AbstractResult<>(code, message, ResultTypeEnum.SUCCESS.code(), obj))
            .orElseThrow(() -> {
                SystemResultEnum resultEnum = SystemResultEnum.SUCCESS_DATA_WARN;
                return new BusinessException(resultEnum.code(), resultEnum.desc());
            });
    }

    /**
     * 数据传输结果响应
     * @param baseKvEnum {@link BaseKvEnum} 基础响应结果
     * @param data {@code Object} 承载数据
     * @param <T> {@code T} 泛型标记
     * @return {@link AbstractResult}响应结果
     */
    @Operation(description = "响应结果")
    public static <T> AbstractResult<T> data(BaseKvEnum baseKvEnum, T data) {
        return data(baseKvEnum.code(), baseKvEnum.desc(), data);

    }

    /**
     * 数据传输结果响应
     * @param data {@code Object} 承载数据
     * @param <T> {@code T} 泛型标记
     * @return {@link AbstractResult}响应结果
     */
    @Operation(description = "响应结果")
    public static <T> AbstractResult<T> data(T data) {
        return data(SystemResultEnum.SUCCESS, data);
    }

    /**
     * 数据传输结果响应 数据转换
     * @param data {@code Object} 承载数据
     * @param clazz {@link Class<E>} 转换类型
     * @param <E> {@code E } 泛型标记
     * @return {@link AbstractResult}响应结果
     */
    public static <E> AbstractResult<E> dataConvert(Object data, Class<E> clazz) {
        return data(AssemblerUtil.convert(data, clazz));
    }

    /**
     * 数据操作状态
     * @param code {@code String} 消息编码
     * @param message {@code String} 消息描述
     * @param type {@code ResultTypeEnum} 操作标记
     * @return {@link AbstractResult<Boolean>}响应结果
     */
    public static <T> AbstractResult<Boolean> status(String code, String message, String type, boolean flag) {
        return new AbstractResult<>(code, message, type, flag);
    }

    /**
     * 数据操作状态
     * @param baseKvEnum {@link BaseKvEnum} 操作标记（true-成功,false-失败）
     * @param resultType {@code ResultTypeEnum} 操作类型
     * @return {@link AbstractResult<Boolean>}响应结果
     */
    private static <T> AbstractResult<Boolean> status(BaseKvEnum baseKvEnum, ResultTypeEnum resultType, boolean flag) {
        return status(baseKvEnum.code(), baseKvEnum.desc(), resultType.code(), flag);
    }

    /**
     * 数据操作状态-操作成功
     * @return {@link AbstractResult<Boolean>} 响应结果
     */
    public static <T extends Serializable> AbstractResult<Boolean> ok() {
        return status(SystemResultEnum.SUCCESS, ResultTypeEnum.SUCCESS, true);
    }

    /**
     * 数据操作状态
     * @param flag 操作标记（true-成功|false-无内容）
     * @return {@link AbstractResult<Boolean>}响应结果
     */
    public static AbstractResult<Boolean> status(boolean flag) {
        if (flag) {
            return status(SystemResultEnum.SUCCESS, ResultTypeEnum.SUCCESS, true);
        }
        else {
            return status(SystemResultEnum.SUCCESS_NO_CONTENT, ResultTypeEnum.WARN, false);
        }
    }

    /**
     * 错误信息
     * @param title {@code String} 消息标题
     * @param detail {@code String} 消息明细
     * @return {@link AbstractResult}响应结果
     */
    public static AbstractResult<Object> error(String code, String message, String title, String detail) {
        return new ErrorResult(code, message, title, detail);
    }

    /**
     * 错误信息
     * @param baseKvEnum {@code BaseResultCode} 枚举消息
     * @param title {@code String} 消息标题
     * @param detail {@code String} 消息明细
     * @return {@link AbstractResult}响应结果
     */
    public static AbstractResult<Object> error(BaseKvEnum baseKvEnum, String title, String detail) {
        return error(baseKvEnum.code(), baseKvEnum.desc(), title, detail);
    }

    /**
     * 错误信息
     * @param baseKvEnum {@code BaseResultCode} 枚举消息
     * @return {@link AbstractResult}响应结果
     */
    public static AbstractResult<Object> error(BaseKvEnum baseKvEnum) {
        return error(baseKvEnum.code(), baseKvEnum.desc(), null, null);
    }

    /**
     * 警告信息
     * @param message {@code String} 消息描述
     * @return {@link AbstractResult} 响应结果
     */
    @Operation(description = "响应结果")
    public static AbstractResult<Object> warn(String code, String message, String self) {
        return new AbstractResult<>(code, message, ResultTypeEnum.WARN.code(), false, self);
    }

    /**
     * 警告信息
     * @param message {@code String} 消息描述
     * @return {@link AbstractResult} 响应结果
     */
    @Operation(description = "响应结果")
    public static AbstractResult<Object> warn(String code, String message) {
        return warn(code, message, null);
    }

    /**
     * 警告信息
     * @param baseKvEnum {@link BaseKvEnum} 枚举消息
     * @return {@link AbstractResult} 响应结果
     */
    public static AbstractResult<Object> warn(BaseKvEnum baseKvEnum) {
        return warn(baseKvEnum.code(), baseKvEnum.desc(), null);
    }

    /**
     * 警告信息
     * @param baseKvEnum {@link BaseKvEnum} 枚举消息
     * @return {@link AbstractResult} 响应结果
     */
    public static AbstractResult<Object> warn(BaseKvEnum baseKvEnum, String self) {
        return warn(baseKvEnum.code(), baseKvEnum.desc(), self);
    }

    /**
     * 接口数据处理，一般情况下如果feign接口统一包装后，获取数据使用
     * @param rpcResult {@link AbstractResult<T>}
     * @return {@link <T>} 泛型标记数据
     */
    public static <T> T readHandle(AbstractResult<T> rpcResult) {
        AssertUtil.isTrue(StringUtil.pathEquals(rpcResult.getType(), ResultTypeEnum.SUCCESS.code()),
                rpcResult.getMessage());
        return rpcResult.getData();
    }

    public static Object readHandle(Object body) {
        AbstractResult<Object> result = (AbstractResult<Object>) body;
        AssertUtil.isTrue(StringUtil.pathEquals(result.getType(), ResultTypeEnum.SUCCESS.code()), result.getMessage());
        return result.getData();
    }

}
