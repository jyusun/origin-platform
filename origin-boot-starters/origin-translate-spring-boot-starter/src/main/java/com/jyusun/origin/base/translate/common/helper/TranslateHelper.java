package com.jyusun.origin.base.translate.common.helper;

import com.jyusun.origin.base.translate.annotation.Dict;
import com.jyusun.origin.base.translate.desc.TranslateHandler;
import com.jyusun.origin.core.common.util.SpringUtil;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;

/**
 * 作用描述：翻译处理
 *
 * @author jyusun at 2023/6/5 17:45
 * @since 1.0.0
 */
@UtilityClass
public class TranslateHelper {

    public static TranslateHandler getTranslateHandler() {
        return SpringUtil.getBean(TranslateHandler.class);
    }

    /**
     * 数据翻译
     * @param source 翻译源
     * @param targetClass 目标类
     * @param <S> 翻译源泛型
     * @param <T> 目标类泛型
     * @return object 目标对象
     */
    @SneakyThrows
    public static <S, T> T translate(S source, Class<T> targetClass) {
        return getTranslateHandler().translate(source, targetClass);
    }

    public static String findDescs(Object sourceObj, Field field) {
        return getTranslateHandler().findDescs(sourceObj, field);
    }

    /**
     * 查找描述
     * @param source
     * @param field
     * @return
     */
    public static String findDesc(Object source, Field field, Dict dictAnn) {
        return getTranslateHandler().findDesc(source, field, dictAnn);
    }

    /**
     * 数据翻译
     * @param source 翻译源
     * @param <T> 目标类泛型
     * @return object 目标对象
     */
    @SneakyThrows
    public static <T> T translate(T source) {
        return getTranslateHandler().translate(source);
    }

    public static String readDesc(Field field, Object value, Dict dict) {
        return getTranslateHandler().readDesc(field, value, dict);
    }

    /**
     * 数据翻译
     * @param target {@code Object} 目标对象
     */
    public static void resultHandle(Object target) {
        getTranslateHandler().resultHandle(target);
    }

}
