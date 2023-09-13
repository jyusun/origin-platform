package com.jyusun.origin.base.logger.aspect;

import com.jyusun.origin.base.logger.annotation.SysLogger;
import com.jyusun.origin.base.logger.common.enums.OperTypeEnum;
import com.jyusun.origin.base.logger.common.util.PointUtil;
import com.jyusun.origin.base.logger.model.LoggerDataFactory;
import com.jyusun.origin.base.logger.model.dto.ErrorLoggerDTO;
import com.jyusun.origin.base.logger.model.dto.LoginLoggerDTO;
import com.jyusun.origin.base.logger.model.dto.RequestLoggerDTO;
import com.jyusun.origin.base.logger.publisher.LoggerPublisher;
import com.jyusun.origin.core.common.model.UserDTO;
import com.jyusun.origin.core.common.util.SecureUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import com.jyusun.origin.core.common.util.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Map;

/**
 * 操作日志打印（如果需要可以通过异步处理落库）
 *
 * @author jyusun at 2019-08-07
 * @since 1.0.0
 */
@Slf4j
@Aspect
public class SystemLoggerAspect {

    /**
     * 切点前后环绕通知：输出请求信息 和 响应信息
     * @param point {@link ProceedingJoinPoint} 切点信息
     * @param sysLogger {@link SysLogger } 切点注解
     * @return {@code Object} 请求处理结果
     */
    @Around("@annotation(sysLogger)")
    @SneakyThrows
    public Object doAround(ProceedingJoinPoint point, SysLogger sysLogger) {

        // 获取类名
        String className = point.getTarget().getClass().getName();
        // 获取方法
        String method = point.getSignature().getName();
        // 开始时间
        long startTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long timeCost = System.currentTimeMillis() - startTime;
        HttpServletRequest request = WebUtil.getRequest();
        UserDTO user = SecureUtil.getUser(request);
        if (StringUtil.pathEquals(sysLogger.operType().code(), OperTypeEnum.LOGIN.code())) {
            LoginLoggerDTO loginLogger = LoggerDataFactory.buildLogin(request, className, method,
                    PointUtil.getParamsByPoint(point), startTime, timeCost);
            LoggerPublisher.loginEvent(loginLogger);
        }
        else {
            RequestLoggerDTO requestLogger = LoggerDataFactory.buildRequest(request, sysLogger, className, method,
                    PointUtil.getParamsByPoint(point), startTime, timeCost, user.getUserId());
            LoggerPublisher.requestEvent(requestLogger);
        }
        return result;
    }

    @Pointcut("execution(* com.jyusun.origin..*.web..*.*(..))")
    public void doWeb() {

    }

    @Pointcut("execution(* com.jyusun.origin..*.remote..*.*(..))")
    public void doRemote() {

    }

    /**
     * 切点抛出异常后：输出请求信息
     * @param joinPoint
     * @param throwable {@link Throwable } 异常信息 通过统一异常处理拦截 不在此处处理
     */
    @AfterThrowing(pointcut = "doWeb() || doRemote()", throwing = "throwable")
    public void doAfterThrow(JoinPoint joinPoint, Throwable throwable) {
        HttpServletRequest request = WebUtil.getRequest();
        // 获取类名
        String className = joinPoint.getTarget().getClass().getName();
        // 获取方法
        String method = joinPoint.getSignature().getName();
        // 开始时间
        long startTime = System.currentTimeMillis();
        // 记录日志
        Map<String, Object> params = PointUtil.getParamsByJoinPoint(joinPoint);
        ErrorLoggerDTO errorLogger = LoggerDataFactory.buildError(request, throwable, className, method, params,
                startTime);
        LoggerPublisher.errorEvent(errorLogger);
    }

}
