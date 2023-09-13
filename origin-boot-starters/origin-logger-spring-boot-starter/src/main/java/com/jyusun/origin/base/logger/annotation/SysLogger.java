package com.jyusun.origin.base.logger.annotation;

import com.jyusun.origin.base.logger.common.enums.OperTypeEnum;
import com.jyusun.origin.core.common.util.StringUtil;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 通过API方式声明切点
 *
 * @author jyusun at 2019-08-07
 * @since 1.0.0
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogger {

    /**
     * Alias for {@link #title}
     * @return {@code String}
     */
    @AliasFor("title")
    String value() default StringUtil.EMPTY;

    /**
     * Alias for {@link #value}
     * @return {@code String}
     */
    @AliasFor("value")
    String title() default StringUtil.EMPTY;

    /**
     * 1-数据新增，2-数据更新，3-数据删除，4-用户登录，5-数据查询，6-数据列表，7-分页查询，9-未知类型
     * @return OperType
     */
    OperTypeEnum operType() default OperTypeEnum.DEFAULT;

    /**
     * 是否打印响应结果：默认是打印的，一般分页查询中可以 将此项 set = false,避免过多的日志不方便问题排查
     * @return Boolean
     */
    boolean isOutResult() default true;

}