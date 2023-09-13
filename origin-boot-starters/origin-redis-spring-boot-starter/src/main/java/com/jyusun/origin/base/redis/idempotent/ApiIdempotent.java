package com.jyusun.origin.base.redis.idempotent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 幂等接口处理
 * <p>
 * 作用描述: 基于redis token 处理 Api幂等
 *
 * @author jyusun
 * @date 2021-04-16
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiIdempotent {

    /**
     * 提示描述
     * @return {@code String} 提示描述
     */
    String errorMsg() default "请求已经过期，请重试";

}
