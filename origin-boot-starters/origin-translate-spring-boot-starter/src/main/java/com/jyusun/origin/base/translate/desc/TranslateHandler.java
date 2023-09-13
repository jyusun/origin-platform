package com.jyusun.origin.base.translate.desc;

import com.jyusun.origin.base.translate.annotation.Dict;
import com.jyusun.origin.base.translate.annotation.Dicts;
import com.jyusun.origin.base.translate.common.util.TranslateUtil;
import com.jyusun.origin.base.translate.desc.handler.DescBooleanStrategy;
import com.jyusun.origin.base.translate.desc.handler.DescCustomStrategy;
import com.jyusun.origin.base.translate.desc.handler.DescExtendStrategy;
import com.jyusun.origin.core.common.model.page.PageObject;
import com.jyusun.origin.core.common.util.AssertUtil;
import com.jyusun.origin.core.common.util.BeanUtil;
import com.jyusun.origin.core.common.util.ObjectUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据翻译工具类
 *
 * @author jyusun at 2022-06-17 16:23:38
 */
@RequiredArgsConstructor
public class TranslateHandler {

    private final DescBooleanStrategy descBooleanStrategy;

    private final DescCustomStrategy descCustomStrategy;

    private final DescExtendStrategy descExtendStrategy;

    /**
     * 数据翻译
     * @param source 翻译源
     * @param targetClass 目标类
     * @param <S> 翻译源泛型
     * @param <T> 目标类泛型
     * @return object 目标对象
     */
    @SneakyThrows
    public <S, T> T translate(S source, Class<T> targetClass) {
        // 获取目标对象
        T target = BeanUtil.copyProperties(source, targetClass);
        Field[] fields = FieldUtils.getAllFields(targetClass);
        Arrays.stream(fields).filter(field -> field.isAnnotationPresent(Dict.class)).forEach(field -> {
            if (field.isAnnotationPresent(Dicts.class)) {
                String descs = findDescs(source, field);
                BeanUtil.writePropertyValue(target, field.getName(), descs);
            }
            else {
                Dict dictAnn = AnnotationUtils.getAnnotation(field, Dict.class);
                String desc = this.findDesc(source, field, dictAnn);
                BeanUtil.writePropertyValue(target, field.getName(), desc);
            }
        });
        return target;
    }

    public String findDescs(Object sourceObj, Field field) {
        Dicts dictAnns = AnnotationUtils.getAnnotation(field, Dicts.class);
        AssertUtil.notNull(dictAnns, "注解不存在");
        return Arrays.stream(dictAnns.value())
            .map(dictAnn -> this.findDesc(sourceObj, field, dictAnn))
            .collect(Collectors.joining(StringUtil.COMMA));

    }

    /**
     * 查找描述
     * @param source
     * @param field
     * @return
     */
    public String findDesc(Object source, Field field, Dict dictAnn) {
        Object value = TranslateUtil.readValue(source, dictAnn);
        return this.readDesc(field, value, dictAnn);
    }

    /**
     * 数据翻译
     * @param source 翻译源
     * @param <T> 目标类泛型
     * @return object 目标对象
     */
    @SneakyThrows
    public <T> T translate(T source) {
        Class<?> target = source.getClass();
        // 获取目标对象
        Field[] fields = FieldUtils.getAllFields(target);
        Arrays.stream(fields)
            .filter(field -> field.isAnnotationPresent(Dicts.class))
            .filter(field -> field.isAnnotationPresent(Dict.class))
            .forEach(field -> {
                if (field.isAnnotationPresent(Dicts.class)) {
                    String descs = findDescs(source, field);
                    BeanUtil.writePropertyValue(target, field.getName(), descs);
                }
                else {
                    Dict dictAnn = AnnotationUtils.getAnnotation(field, Dict.class);
                    String desc = this.findDesc(source, field, dictAnn);
                    BeanUtil.writePropertyValue(target, field.getName(), desc);
                }
            });
        return source;
    }

    public String readDesc(Field field, Object value, Dict dict) {
        if (ObjectUtil.isEmpty(value)) {
            return dict.defValue();
        }
        DescContext descContext;
        if (dict.customKv().length > 0) {
            if (field.getType() == Boolean.class || field.getType() == boolean.class) {
                descContext = new DescContext(descBooleanStrategy);
            }
            else {
                descContext = new DescContext(descCustomStrategy);
            }
        }
        else {
            descContext = new DescContext(descExtendStrategy);
        }
        return descContext.getDesc(dict, value);
    }

    /**
     * 数据翻译
     * @param target {@code Object} 目标对象
     */
    public void resultHandle(Object target) {
        Class<?> clazz = TranslateUtil.getTranslateClass(target);
        AssertUtil.notNull(clazz, "数据值翻译：目标类不存在");
        // 获取翻译字段配置信息
        if (target instanceof List<?>) {
            ((List<?>) target).parallelStream().forEach(this::translate);
        }
        else if (target instanceof PageObject<?>) {
            ((PageObject<?>) target).getRows().parallelStream().forEach(this::translate);
        }
        else {
            this.translate(target);
        }
    }

}
