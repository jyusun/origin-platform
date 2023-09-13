package com.jyusun.origin.base.launch.web.common.util;

import com.jyusun.origin.core.common.model.BaseKvEnum;
import com.jyusun.origin.core.common.util.StringUtil;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.StringJoiner;

/**
 * 工具
 * <p>
 * 作用描述：日志输出格式化工具
 *
 * @author jyusun
 * @date 2021/7/3 16:21
 * @since 1.0.0
 */
@UtilityClass
public class LoggerFormatUtil {

    /**
     * 异常信息格式化字符串
     */
    private static final String STR_FORMAT_ERROR = new StringJoiner(StringUtil.LF).add(StringUtil.LF)
        .add("############################## [ 系统错误 - 开始 ] ##############################")
        .add(" 消息代码: %s | 消息描述: %s")
        .add(" 消息标题: %s")
        .add(" 消息详情: %s")
        .add("############################## [ 系统错误 - 结束 ] ##############################")
        .toString();

    /**
     * 警告信息格式化字符串
     */
    private static final String STR_FORMAT_WARN = new StringJoiner(StringUtil.LF).add(StringUtil.LF)
        .add("****************************** [ 系统警告 - 开始 ] ******************************")
        .add(" 消息代码: %s")
        .add(" 消息描述: %s")
        .add("****************************** [ 系统警告 - 结束 ] ******************************")
        .toString();

    /**
     * 请求信息格式化字符串
     */
    public static final String STR_FORMAT_REQUEST = new StringJoiner(StringUtil.LF).add(StringUtil.LF)
        .add("============================== [ 请求日志 - 开始 ] ==============================")
        .add(" 日志主题 : %s | 操 作 人 : %s | 请求时间 : %s | 运行耗时: %s")
        .add(" 请求响应: %s")
        .add("============================== [ 请求日志 - 结束 ] ==============================")
        .toString();

    /**
     * 请求信息格式化字符串 报错错误
     */
    private static final String STR_FORMAT_REQUEST_ERROR = new StringJoiner(StringUtil.LF).add(StringUtil.LF)
        .add("#=#=#=#=#=#=#=#=#=#=#=#=#=#=#= [ 错误请求 - 开始 ] =#=#=#=#=#=#=#=#=#=#=#=#=#=#=#")
        .add(" 日志主题 : %s | 操 作 人 : %s | 请求时间 : %s")
        .add(" 请求信息 : %s")
        .add("#=#=#=#=#=#=#=#=#=#=#=#=#=#=#= [ 错误请求 - 结束 ] =#=#=#=#=#=#=#=#=#=#=#=#=#=#=#")
        .toString();

    public static String buildErrorMessage(String code, String message, String title, String detail) {
        return String.format(STR_FORMAT_ERROR, code, message, title, detail);
    }

    public static String buildErrorMessage(BaseKvEnum baseKvEnum, String title, String detail) {
        return buildErrorMessage(baseKvEnum.code(), baseKvEnum.desc(), title, detail);
    }

    public static String buildWarnMessage(String code, String message) {
        return String.format(STR_FORMAT_WARN, code, message);
    }

    public static String buildWarnMessage(BaseKvEnum baseKvEnum) {
        return buildWarnMessage(baseKvEnum.code(), baseKvEnum.desc());
    }

    public static String buildWarnMessage(BaseKvEnum baseKvEnum, String message) {
        return buildWarnMessage(baseKvEnum.code(), baseKvEnum.desc() + "，" + message);

    }

    /**
     * 请求消息组装
     * @param title
     * @param userId
     * @param requestTime
     * @param timeCost
     * @param detail
     * @return
     */
    public static String buildRequestMessage(String title, String userId, LocalDateTime requestTime, long timeCost,
            String detail) {
        return String.format(LoggerFormatUtil.STR_FORMAT_REQUEST, title, userId, requestTime, timeCost, detail);
    }

    /**
     * 异常请求消息组装
     * @param title
     * @param operator
     * @param requestTime
     * @param detail
     * @return
     */
    public static String buildErrorReqMessage(String title, String operator, LocalDateTime requestTime, String detail) {
        return String.format(LoggerFormatUtil.STR_FORMAT_REQUEST_ERROR, title, operator, requestTime, detail);
    }

}
