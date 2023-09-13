package com.jyusun.origin.base.translate.aspect;

import com.jyusun.origin.base.translate.annotation.ResultTranslate;
import com.jyusun.origin.base.translate.common.helper.TranslateHelper;
import com.jyusun.origin.core.common.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * 数据字典切面
 *
 * @author jyusun at 2022-06-09 18:33:39
 */
@Aspect
@RequiredArgsConstructor
public class TranslateAspect {

    @Around("@annotation(resultTranslate)")
    public Object translation(ProceedingJoinPoint proceedingJoinPoint, ResultTranslate resultTranslate)
            throws Throwable {

        Object result = proceedingJoinPoint.proceed();
        // 获取方法返回值
        if (ObjectUtil.isNotEmpty(result)) {
            TranslateHelper.resultHandle(result);
        }
        return result;
    }

}
