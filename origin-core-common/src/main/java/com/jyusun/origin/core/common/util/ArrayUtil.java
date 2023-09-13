package com.jyusun.origin.core.common.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;

/**
 * 作用描述： - 数组操作工具类
 *
 * @author jyusun at 2020/1/5 16:39
 * @since 1.0.0
 */
@UtilityClass
public class ArrayUtil extends ArrayUtils {

    /**
     * 新建一个空数组
     * @param <T> 数组元素类型
     * @param componentType 元素类型
     * @param newSize 大小
     * @return 空数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] newArray(Class<T> componentType, int newSize) {
        return (T[]) Array.newInstance(componentType, newSize);
    }

}
