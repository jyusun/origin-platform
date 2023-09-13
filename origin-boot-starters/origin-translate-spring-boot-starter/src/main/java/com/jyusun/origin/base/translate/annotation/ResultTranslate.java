package com.jyusun.origin.base.translate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 切点
 * <p>
 * 作用描述：结果翻译service层
 * </p>
 *
 * @author jyusun at 2022/6/9 18:55
 * @since 1.0.0
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ResultTranslate {

}
