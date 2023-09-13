package com.jyusun.origin.core.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * 作用描述： -
 *
 * @author jyusun at 2020/1/5 17:08
 * @since 1.0.0
 */
@UtilityClass
public class ClassUtil extends ClassUtils {

    /**
     * 获得对象数组的类数组
     * @param objects 对象数组，如果数组中存在{@code null}元素，则此元素被认为是Object类型
     * @return 类数组
     */
    public static Class<?>[] getClasses(Object... objects) {
        Class<?>[] classes = new Class<?>[objects.length];
        Object obj;
        for (int i = 0; i < objects.length; i++) {
            obj = objects[i];
            classes[i] = (ObjectUtil.isEmpty(obj)) ? Object.class : obj.getClass();
        }
        return classes;
    }

    /**
     * 获得默认值列表
     * @param classes 值类型
     * @return 默认值列表
     */
    public static Object[] getDefaultValues(Class<?>... classes) {
        return Arrays.stream(classes).map(ClassUtil::getDefaultValue).toArray();
    }

    /**
     * 是否为静态方法
     * @param method 方法
     * @return 是否为静态方法
     */
    public static boolean isStatic(Method method) {
        AssertUtil.notNull(method, "Method to provided is null.");
        return Modifier.isStatic(method.getModifiers());
    }

    /**
     * 获取指定类型分的默认值<br>
     * 默认值规则为：
     *
     * <pre>
     * 1、如果为原始类型，返回0
     * 2、非原始类型返回{@code null}
     * </pre>
     * @param clazz 类
     * @return 默认值
     */
    public static Object getDefaultValue(Class<?> clazz) {

        if (clazz.isPrimitive()) {
            if (long.class == clazz) {
                return 0L;
            }
            else if (int.class == clazz) {
                return 0;
            }
            else if (short.class == clazz) {
                return (short) 0;
            }
            else if (char.class == clazz) {
                return (char) 0;
            }
            else if (byte.class == clazz) {
                return (byte) 0;
            }
            else if (double.class == clazz) {
                return 0.0D;
            }
            else if (float.class == clazz) {
                return 0.0f;
            }
            else if (boolean.class == clazz) {
                return false;
            }
        }
        return null;
    }

}
