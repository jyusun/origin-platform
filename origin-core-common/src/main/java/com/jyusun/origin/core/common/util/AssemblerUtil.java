package com.jyusun.origin.core.common.util;

import lombok.experimental.UtilityClass;

/**
 * 装配工具
 * <p>
 * 作用描述：数据类型转换
 * </p>
 *
 * @author jyusun at 2020/5/25 10:57
 * @since 1.0.0
 */
@UtilityClass
public class AssemblerUtil {

    /**
     * 数据转换
     * @param obj 数据对象
     * @param clazz 类
     * @param <T> 泛型标记
     * @return {@code <T>} 泛型类
     */
    public static <T> T convert(Object obj, Class<T> clazz) {
        T t = BeanUtil.newInstance(clazz);
        BeanUtil.copyProperties(obj, t);
        return t;
    }

}
