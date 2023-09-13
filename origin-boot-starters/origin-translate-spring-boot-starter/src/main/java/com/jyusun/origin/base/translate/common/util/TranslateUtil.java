package com.jyusun.origin.base.translate.common.util;

import com.jyusun.origin.base.translate.annotation.Dict;
import com.jyusun.origin.core.common.model.page.PageObject;
import com.jyusun.origin.core.common.util.AssertUtil;
import com.jyusun.origin.core.common.util.BeanUtil;
import com.jyusun.origin.core.common.util.CollectionUtil;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 翻译处理工具
 *
 * <p>
 * 作用描述：
 * </p>
 *
 * @author jyusun at 2022/6/9 18:41
 * @since 1.0.0
 */
@UtilityClass
public final class TranslateUtil {

    /**
     * 获取需要翻译的注解集合
     * @param clazz 翻译类
     * @return {@link Map <String, TranslateParam>} 翻译属性集合
     */
    public static Map<String, Dict> getTranslateAnnotation(Class<?> clazz) {
        Field[] fields = FieldUtils.getAllFields(clazz);
        return Arrays.stream(fields)
            .filter(field -> field.isAnnotationPresent(Dict.class))
            .collect(Collectors.toMap(Field::getName, filed -> AnnotationUtils.getAnnotation(filed, Dict.class)));
    }

    /**
     * 获取翻译目标类
     * <p>
     * 作用描述：仅支持
     * </p>
     * @param result {@link List} or {@link PageObject} or {@code Object} 单个对象
     * @return {@code Class<?>}
     */
    public static Class<?> getTranslateClass(Object result) {
        Object obj;
        if (result instanceof List<?>) {
            List<?> results = ((List<?>) result);
            if (CollectionUtil.isEmpty(results)) {
                return null;
            }
            obj = results.get(0);
        }
        else if (result instanceof PageObject<?>) {
            List<?> rows = ((PageObject<?>) result).getRows();
            if (CollectionUtil.isEmpty(rows)) {
                return null;
            }
            obj = rows.get(0);
        }
        else {
            obj = result;
        }
        return obj.getClass();
    }

    public Object readValue(Object result, Dict dict) {
        Class<?> resultClass = result.getClass();
        PropertyDescriptor propertyDescriptor = BeanUtil.getPropertyDescriptor(resultClass, dict.sourceField());
        AssertUtil.notNull(propertyDescriptor, "翻译属性不存在");
        // 获取翻译属性值
        return BeanUtil.readPropertyValue(result, propertyDescriptor);
    }

}
