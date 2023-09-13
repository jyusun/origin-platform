package com.jyusun.origin.base.logger.common.util;

import lombok.experimental.UtilityClass;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Point 工具
 * <p>
 * 作用描述：Point 处理
 *
 * @author jyusun
 * @date 2021/7/8 16:30
 * @since 1.0.0
 */
@UtilityClass
public class PointUtil {

    /**
     * 获取入参
     * @param proceedingJoinPoint
     * @return {@link Map<String, Object>}
     */
    public Map<String, Object> getParamsByPoint(ProceedingJoinPoint proceedingJoinPoint) {
        // 参数名
        String[] paramNames = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        // 参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();
        return buildParam(paramNames, paramValues);
    }

    /**
     * 获取入参
     * @param joinPoint
     * @return {@link Map<String, Object>}
     */
    public Map<String, Object> getParamsByJoinPoint(JoinPoint joinPoint) {
        // 参数名
        String[] paramNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        // 参数值
        Object[] paramValues = joinPoint.getArgs();
        return buildParam(paramNames, paramValues);
    }

    /**
     * 构建参数
     * @param paramNames 参数名称
     * @param paramValues 参数值
     * @return {@link Map<String, Object>}
     */
    private Map<String, Object> buildParam(String[] paramNames, Object[] paramValues) {
        Map<String, Object> requestParams = new HashMap<>(16);
        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];

            // 如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                // 获取文件名
                value = file.getOriginalFilename();
            }
            requestParams.put(paramNames[i], value);
        }
        return requestParams;
    }

}
