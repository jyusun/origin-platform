package com.jyusun.origin.core.common.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyusun.origin.core.common.util.SpringUtil;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

/**
 * 作用描述：JACKSON 操作
 *
 * @author jyusun at 2023/5/25 9:33
 * @since 1.0.0
 */
@UtilityClass
public class JacksonHelper {

    public static ObjectMapper objectMapper() {
        return SpringUtil.getBean(ObjectMapper.class);
    }

    /**
     * 对象转JSON 字符串
     * @param obj 对象
     * @return
     */
    @SneakyThrows
    public static String toJsonStr(Object obj) {
        return objectMapper().writeValueAsString(obj);
    }

    /**
     * 对象转JSON 字符串
     * @param obj 对象
     * @return
     */
    @SneakyThrows
    public static <T> T parse(String obj, Class<T> clzz) {
        return objectMapper().readValue(obj, clzz);
    }

    /**
     * 对象转JSON 字符串
     * @param obj 对象
     * @return
     */
    @SneakyThrows
    public static <T> T parseType(String obj, Class<T> clzz) {
        return objectMapper().readValue(obj, new TypeReference<T>() {
        });
    }

}
