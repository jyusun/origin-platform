package com.jyusun.origin.base.logger.model;

import com.jyusun.origin.base.logger.annotation.SysLogger;
import com.jyusun.origin.base.logger.common.util.OutForUtil;
import com.jyusun.origin.base.logger.event.LoginLoggerEvent;
import com.jyusun.origin.base.logger.model.dto.ErrorLoggerDTO;
import com.jyusun.origin.base.logger.model.dto.LoginLoggerDTO;
import com.jyusun.origin.base.logger.model.dto.RequestLoggerDTO;
import com.jyusun.origin.base.logger.model.dto.UsualLoggerDTO;
import com.jyusun.origin.base.logger.model.value.ServerValue;
import com.jyusun.origin.core.common.util.DateUtil;
import com.jyusun.origin.core.common.util.SecureUtil;
import com.jyusun.origin.core.common.util.SpringUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import com.jyusun.origin.core.common.util.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

/**
 * 作用描述：
 *
 * @author jyusun at 2023/3/1 15:48
 * @since 1.0.0
 */
@Builder
@UtilityClass
public class LoggerDataFactory {

    /**
     * 构建请求日志参数
     * @param request
     * @param loginLogger
     * @param className
     * @param method
     * @param params
     * @param startTime
     * @param timeCost
     * @param userId
     * @return
     */

    public static RequestLoggerDTO buildRequest(HttpServletRequest request, SysLogger loginLogger, String className,
            String method, Map<String, Object> params, long startTime, long timeCost, long userId) {

        RequestLoggerDTO requestLoggerDTO = new RequestLoggerDTO();

        String title = StringUtil.hasText(loginLogger.value()) ? loginLogger.value() : loginLogger.operType().desc();
        requestLoggerDTO.setRequestTime(DateUtil.toDateTime(startTime, ZoneId.systemDefault()))
            .setTimeCost(timeCost)
            .setRequestInfo(OutForUtil.buildRequest(request, params))
            .setServerInfo(new ServerValue())
            .setOperType(loginLogger.operType().code())
            .setTitle(title)
            .setClassName(className)
            .setMethodName(method)
            .setOperator(userId);
        return requestLoggerDTO;
    }

    /**
     * 构建登录参数
     * @param request
     * @param className
     * @param method
     * @param paramsByPoint
     * @param startTime
     * @param timeCost
     * @return
     */
    public static LoginLoggerDTO buildLogin(HttpServletRequest request, String className, String method,
            Map<String, Object> paramsByPoint, long startTime, long timeCost) {
        String title = "登录";
        LoginLoggerDTO loginLoggerDTO = new LoginLoggerDTO();
        loginLoggerDTO.setUserAgent(OutForUtil.buildUserAgent(request))
            .setRemoteAddress(WebUtil.getIpAddr(request))
            .setLoginTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), ZoneId.systemDefault()))
            .setTimeCost(timeCost)
            .setClassName(className)
            .setMethodName(method)
            .setTitle(title);
        SpringUtil.publishEvent(new LoginLoggerEvent(loginLoggerDTO));
        return loginLoggerDTO;
    }

    /**
     * 构建异常参数
     * @param request
     * @param throwable
     * @param className
     * @param method
     * @param params
     * @param startTime
     * @return
     */
    public static ErrorLoggerDTO buildError(HttpServletRequest request, Throwable throwable, String className,
            String method, Map<String, Object> params, long startTime) {
        String title = throwable.getLocalizedMessage();

        ErrorLoggerDTO errDto = new ErrorLoggerDTO();
        errDto.setRequestTime(DateUtil.toDateTime(startTime, ZoneId.systemDefault()))
            .setErrorInfo(OutForUtil.buildError(throwable))
            .setRequestInfo(OutForUtil.buildRequest(request, params))
            .setServerInfo(new ServerValue())
            .setClassName(className)
            .setMethodName(method)

            .setTitle(title)
            .setOperator(SecureUtil.getUser().getUserId());
        return errDto;
    }

    /**
     * 构建常规日志
     * @param className
     * @param method
     * @param title
     * @param bizId
     * @param tableName
     * @param description
     * @return
     */
    public static UsualLoggerDTO buildUsual(String className, String method, String title, Long bizId, String tableName,
            String description, long userId) {
        UsualLoggerDTO logUsual = new UsualLoggerDTO();
        logUsual.setBizId(bizId)
            .setTableName(tableName)
            .setDescription(description)
            .setTitle(title)
            .setClassName(className)
            .setMethodName(method)
            .setOperator(userId);
        return logUsual;
    }

}
