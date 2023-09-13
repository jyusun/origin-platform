package com.jyusun.origin.base.launch.web.core.handle;

import com.jyusun.origin.base.launch.web.common.util.LoggerFormatUtil;
import com.jyusun.origin.core.common.constant.SystemConstant;
import com.jyusun.origin.core.common.enums.SystemResultEnum;
import com.jyusun.origin.core.common.exception.BusinessException;
import com.jyusun.origin.core.common.exception.SecureException;
import com.jyusun.origin.core.common.exception.ServiceException;
import com.jyusun.origin.core.common.model.result.AbstractResult;
import com.jyusun.origin.core.common.model.result.ResultFactory;
import com.jyusun.origin.core.common.util.StringUtil;
import com.jyusun.origin.core.common.util.ThrowableUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Objects;
import java.util.Set;

/**
 * 全局的的异常拦截器
 * <p>
 * 作用描述：处理可预见的异常问题，只有在Web环境下拦截生效 {@link ConditionalOnWebApplication}
 *
 * @author jyusun at 2021-7-3 12:40:19
 * @since 1.0.0
 */
@Slf4j
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@RestControllerAdvice(basePackages = SystemConstant.BASE_PACKAGE)
public class GlobalExceptionHandler {

    private static final String ENV_PROD = "prod";

    /**
     * 生产环境不输出印错误日志到前台接口
     */
    @Value("${spring.profiles.active:prod}")
    public String env;

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AbstractResult<Object> handleError(MissingServletRequestParameterException e) {
        String message = String.format("缺少必要的请求参数: %s", e.getParameterName());

        String code = SystemResultEnum.BAD_REQUEST_PARAM_MISS.code();
        log.warn(LoggerFormatUtil.buildWarnMessage(code, message), e);
        return ResultFactory.warn(code, message);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AbstractResult<Object> handleError(MethodArgumentTypeMismatchException e) {
        String message = String.format("请求参数格式错误: %s", e.getName());

        String code = SystemResultEnum.BAD_REQUEST_PARAM_TYPE_ERROR.code();
        log.warn(LoggerFormatUtil.buildWarnMessage(code, message), e);
        return ResultFactory.warn(code, message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AbstractResult<Object> handleError(MethodArgumentNotValidException e) {
        return handleError(e.getBindingResult());
    }

    /**
     * 请求参数绑定错误
     * @param e {@link BindException }
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AbstractResult<Object> handleError(BindException e) {
        return handleError(e.getBindingResult());
    }

    private AbstractResult<Object> handleError(BindingResult result) {
        FieldError error = result.getFieldError();
        String message = String.format("%s:%s", Objects.requireNonNull(error).getField(), error.getDefaultMessage());

        String code = SystemResultEnum.BAD_REQUEST_PARAM_BIND_ERROR.code();
        log.warn(LoggerFormatUtil.buildWarnMessage(code, message), error);
        return ResultFactory.warn(code, message);
    }

    /**
     * 参数校验失败
     * @param e {@link ConstraintViolationException }
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AbstractResult<Object> handleError(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
        String message = String.format("%s:%s", path, violation.getMessage());

        String code = SystemResultEnum.BAD_REQUEST_PARAM_VALID_ERROR.code();
        log.warn(LoggerFormatUtil.buildWarnMessage(code, message), e);
        return ResultFactory.warn(code, message);
    }

    /**
     * 404未找到请求
     * @param e {@link NoHandlerFoundException }
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AbstractResult<Object> handleError(NoHandlerFoundException e) {
        String code = SystemResultEnum.NOT_FOUND.code();
        log.warn(LoggerFormatUtil.buildWarnMessage(code, e.getMessage()), e);
        return ResultFactory.warn(code, ThrowableUtil.getSimpleMessage(e));
    }

    /**
     * 不支持当前请求方法
     * @param e {@link HttpRequestMethodNotSupportedException}
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public AbstractResult<Object> handleError(HttpRequestMethodNotSupportedException e) {
        String code = SystemResultEnum.METHOD_NOT_SUPPORTED.code();
        log.warn(LoggerFormatUtil.buildWarnMessage(code, e.getMessage()), e);
        return ResultFactory.warn(code, ThrowableUtil.getSimpleMessage(e));
    }

    /**
     * 数据类型错误
     * @param e {@link HttpMessageNotReadableException } 消息无法正常读取
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AbstractResult<Object> handleError(HttpMessageNotReadableException e) {
        String code = SystemResultEnum.BAD_REQUEST_MSG_NOT_READABLE.code();
        log.warn(LoggerFormatUtil.buildWarnMessage(code, e.getMessage()), e);
        return ResultFactory.warn(code, ThrowableUtil.getSimpleMessage(e));
    }

    /**
     * 不支持当前媒体类型
     * @param e {@link HttpMediaTypeNotSupportedException }
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public AbstractResult<Object> handleError(HttpMediaTypeNotSupportedException e) {
        String code = SystemResultEnum.MEDIA_TYPE_NOT_SUPPORTED.code();
        log.warn(LoggerFormatUtil.buildWarnMessage(code, e.getMessage()), e);
        return ResultFactory.warn(code, ThrowableUtil.getSimpleMessage(e));
    }

    /**
     * 业务异常
     * @param e {@link BusinessException}业务异常
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AbstractResult<Object> handleError(BusinessException e) {
        log.warn(LoggerFormatUtil.buildWarnMessage(e.getCode(), e.getMessage()));
        return ResultFactory.warn(e.getCode(), e.getMessage());
    }

    /**
     * 安全认证异常
     * @param e {@link SecureException} 安全认证
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(SecureException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AbstractResult<Object> handleError(SecureException e) {
        log.warn(LoggerFormatUtil.buildWarnMessage(e.getCode(), e.getMessage()));
        return ResultFactory.warn(e.getCode(), e.getMessage());
    }

    /**
     * 服务异常信息
     * @param e {@link ServiceException}
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AbstractResult<Object> handleError(ServiceException e) {
        Throwable cause = e.getCause();
        String simpleName = cause.getClass().getSimpleName();
        String message = cause.getMessage();
        log.error(LoggerFormatUtil.buildErrorMessage(e.getCode(), e.getMessage(), simpleName, message), e);
        if (!StringUtil.pathEquals(env, ENV_PROD)) {
            return ResultFactory.error(SystemResultEnum.INTERNAL_SERVER_ERROR, simpleName, message);
        }
        else {
            return ResultFactory.error(SystemResultEnum.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 拦截所有未知的异常信息，避免暴露给用户
     * @param e {@link Throwable} 异常超类
     * @return {@link AbstractResult}
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AbstractResult<Object> handleError(Throwable e) {
        String simpleName = e.getClass().getSimpleName();
        String message = e.getMessage();
        SystemResultEnum serverError = SystemResultEnum.INTERNAL_SERVER_ERROR;
        log.error(LoggerFormatUtil.buildErrorMessage(serverError.code(), serverError.desc(), simpleName, message), e);
        if (!StringUtil.pathEquals(env, ENV_PROD)) {
            return ResultFactory.error(serverError, simpleName, message);
        }
        else {
            return ResultFactory.error(serverError);
        }
    }

}