package com.jyusun.origin.core.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 集合工具类
 *
 * @author jyusun at 2022-04-14 11:09:14
 */
@UtilityClass
public class CollectionUtil extends CollectionUtils {

    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isNotEmpty(@Nullable Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 将集合转换为数组
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> T[] toArray(@Nullable Collection<T> collection) {
        return (T[]) collection.toArray(new Object[collection.size()]);
    }

}
