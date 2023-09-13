package com.jyusun.origin.base.translate.annotation;

import com.jyusun.origin.base.translate.TranslateProvider;
import com.jyusun.origin.core.common.util.StringUtil;
import org.springframework.core.annotation.AliasFor;

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
public @interface Dict {

    /**
     * 翻译字段
     * @return String
     */
    @AliasFor("sourceField")
    String value() default StringUtil.EMPTY;

    /**
     * 翻译字段
     * @return String
     */
    @AliasFor("value")
    String sourceField() default StringUtil.EMPTY;

    /**
     * 翻译指定的翻译字典类型编码
     * @return String
     */
    String dictCode() default StringUtil.EMPTY;

    /**
     * 自定义翻译标签 如：{true-真,false-假} or {0-女,1-男,2-未知} or {zhangsan-张三,lisi-李四}
     */
    String[] customKv() default {};

    /**
     * 分隔符
     * <p>
     * 作用描述：分割值单独翻译 test,test1
     * </p>
     * @return String
     */
    String split() default StringUtil.EMPTY;

    /**
     * 默认值：如果获取不到字典值，设置默认值
     */
    String defValue() default StringUtil.EMPTY;

    /**
     * 翻译数据来源：默认来源于数据字典
     */
    Class<? extends TranslateProvider> customSource() default TranslateProvider.class;

}
