package com.jyusun.origin.base.translate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 值翻译
 * <p>
 * 作用描述：数据值翻译
 * </p>
 *
 * @author jyusun at 2022/6/9 18:05
 * @since 1.0.0
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Dicts {

    /**
     * 字典值
     * @return
     */
    Dict[] value();

}
