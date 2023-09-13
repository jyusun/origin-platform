package com.jyusun.origin.core.common.enums;

import com.jyusun.origin.core.common.constant.ResultConstant;
import com.jyusun.origin.core.common.model.BaseKvEnum;
import lombok.AllArgsConstructor;

/**
 * 作用描述：系统响应消息枚举类
 *
 * @author jyusun at 2019-07-01
 * @since 1.0.0
 */
@AllArgsConstructor
public enum SystemResultEnum implements BaseKvEnum {

    /**
     * 未知异常：-1
     */
    UNKNOWN_ANOMALY(ResultConstant.MESSAGE_UNKNOWN_ANOMALY, "系统出错,请联系管理员"),
    /**
     * 操作成功：S00200
     */
    SUCCESS(ResultConstant.MESSAGE_OK, "操作成功"),
    /**
     * 操作成功：S00200 警告
     */
    SUCCESS_DATA_WARN(ResultConstant.MESSAGE_OK, "暂无承载数据"),
    /**
     * 创建成功：S00201
     */
    SUCCESS_CREATE(ResultConstant.MESSAGE_CREATE, "创建成功"),
    /**
     * 操作成功：S00204 警告 删除和修改找不到对应数据会触发
     */
    SUCCESS_NO_CONTENT(ResultConstant.MESSAGE_NO_CONTENT, "请求成功，无响应内容"),
    /**
     * 请求错误：S00400
     */
    BAD_REQUEST(ResultConstant.MESSAGE_BAD_REQUEST, "请求错误"),
    /**
     * 请求未授权：S00401
     */
    UNAUTHORIZED(ResultConstant.MESSAGE_UNAUTHORIZED, "请求未授权"),
    /**
     * 请求被拒绝: S00403
     */
    REQ_REJECT(ResultConstant.MESSAGE_FORBIDDEN, "请求被拒绝"),
    /**
     * 未找到请求：S00404
     */
    NOT_FOUND(ResultConstant.MESSAGE_NOT_FOUND, "未找到请求"),
    /**
     * 不支持当前请求方法: S00405
     */
    METHOD_NOT_SUPPORTED(ResultConstant.MESSAGE_METHOD_NOT_ALLOWED, "不支持当前请求方法"),
    /**
     * 请求被拒绝: S00406 黑名单不允许访问 或 只允许白名单访问
     */
    MESSAGE_METHOD_NOT_ACCEPTABLE(ResultConstant.MESSAGE_METHOD_NOT_ACCEPTABLE, "请求被拒绝"),
    /**
     * 不支持当前媒体类型: S00415
     */
    MEDIA_TYPE_NOT_SUPPORTED(ResultConstant.MESSAGE_UNSUPPORTED_MEDIA_TYPE, "不支持当前媒体类型"),
    /**
     * 服务器异常: S00500
     */
    INTERNAL_SERVER_ERROR(ResultConstant.MESSAGE_INTERNAL_SERVER_ERROR, "系统繁忙,请联系管理员"),

    /**
     * 缺少必要的请求参数
     */
    BAD_REQUEST_PARAM_MISS(ResultConstant.MESSAGE_BAD_REQUEST, "缺少必要的请求参数"),
    /**
     * 参数类型错误
     */
    BAD_REQUEST_PARAM_TYPE_ERROR(ResultConstant.MESSAGE_BAD_REQUEST, "参数类型错误"),
    /**
     * 参数绑定错误
     */
    BAD_REQUEST_PARAM_BIND_ERROR(ResultConstant.MESSAGE_BAD_REQUEST, "参数绑定失败"),
    /**
     * 参数校验失败
     */
    BAD_REQUEST_PARAM_VALID_ERROR(ResultConstant.MESSAGE_BAD_REQUEST, "参数校验失败"),
    /**
     * 消息不能读取
     */
    BAD_REQUEST_MSG_NOT_READABLE(ResultConstant.MESSAGE_BAD_REQUEST, "消息不能读取"),

    /**
     * 业务错误
     */
    MESSAGE_BUSINESS_ERROR(ResultConstant.MESSAGE_BUSINESS_ERROR, "输入信息错误，请注意调整");

    /**
     * 状态码
     */
    private final String code;

    /**
     * 消息描述
     */
    private final String desc;

    /**
     * 返回代码
     * @return int
     */
    @Override
    public String code() {
        return this.code;
    }

    /**
     * 返回消息
     * @return str
     */
    @Override
    public String desc() {
        return this.desc;
    }

}
