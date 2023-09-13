package com.jyusun.origin.core.common.util;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

/**
 * 作用描述： JavaBean 操作工具
 *
 * @author jyusun at 2020/5/18 13:52
 * @since 1.0.0
 */
@UtilityClass
public class BeanUtil extends BeanUtils {

    /**
     * 实例化对象
     * @param clazz {@code Class<T>} 类
     * @param <T> {@link <T>} 泛型标记
     * @return 对象
     */
    public static <T> T newInstance(Class<T> clazz) {
        return BeanUtils.instantiateClass(clazz);
    }

    /**
     * 复制对象中的值
     * @param source 源
     * @param targetClass 目标类
     * @param <T> 目标类泛型
     * @return 目标对象
     */
    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        Assert.notNull(targetClass, "Target must not be null");
        T bean = BeanUtil.newInstance(targetClass);
        BeanUtils.copyProperties(source, bean);
        return bean;
    }

    /**
     * 复制对象中的值
     * @param targetClass 目标类
     * @return 目标对象
     */
    public static <T, R> List<R> copyList(Collection<T> sources, Class<R> targetClass) {
        Assert.notNull(targetClass, "Target must not be null");
        List<R> list = Lists.newArrayListWithCapacity(sources.size());
        sources.forEach(data -> {
            R bean = BeanUtil.newInstance(targetClass);
            BeanUtils.copyProperties(data, bean);
            list.add(bean);
        });
        return list;
    }

    @SneakyThrows
    public static Object readPropertyValue(Object obj, PropertyDescriptor propertyDescriptor) {
        Method readMethod = propertyDescriptor.getReadMethod();
        return readMethod.invoke(obj);
    }

    @SneakyThrows
    public static void writePropertyValue(Object obj, String fieldName, String desc) {
        PropertyDescriptor propertyDescriptor = BeanUtil.getPropertyDescriptor(obj.getClass(), fieldName);
        AssertUtil.notNull(propertyDescriptor, "写入属性不存在");
        Method writeMethod = propertyDescriptor.getWriteMethod();
        writeMethod.invoke(obj, desc);
    }

}
