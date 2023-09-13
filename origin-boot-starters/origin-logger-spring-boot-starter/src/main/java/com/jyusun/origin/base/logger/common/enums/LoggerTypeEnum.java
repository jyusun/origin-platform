package com.jyusun.origin.base.logger.common.enums;

import com.jyusun.origin.core.common.model.BaseKvEnum;
import lombok.AllArgsConstructor;

/**
 * 枚举类型
 * <p>
 * 作用描述：日志类型
 *
 * @author jyusun
 * @date 2021/3/24 09:41
 * @since 1.0.0
 */
@AllArgsConstructor
public enum LoggerTypeEnum implements BaseKvEnum {

    /**
     * 请求日志
     */
    LOGIN("1", "登录日志"),
    /**
     * 请求日志
     */
    REQ("2", "请求日志"),
    /**
     * 常规日志 业务日志
     */
    USUAL("3", "常规日志"),
    /**
     * 错误日志
     */
    ERROR("4", "错误日志"),
    /**
     * SQL执行日志
     */
    EXESQL("5", "SQL日志");

    private final String code;

    private final String desc;

    /**
     * 值
     * @return str
     */
    @Override
    public String code() {
        return this.code;
    }

    /**
     * 标签
     * @return str
     */
    @Override
    public String desc() {
        return this.desc;
    }

}
