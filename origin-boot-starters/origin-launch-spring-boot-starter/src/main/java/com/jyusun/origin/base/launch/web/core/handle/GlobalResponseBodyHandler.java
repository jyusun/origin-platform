package com.jyusun.origin.base.launch.web.core.handle;

import com.jyusun.origin.core.common.constant.SystemConstant;
import com.jyusun.origin.core.common.model.result.AbstractResult;
import com.jyusun.origin.core.common.model.result.ResultFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局的拦截器
 * <p>
 * 作用描述：响应数据统一包装处理
 *
 * @author jyusun at 2021-7-3 12:40:19
 * @since 1.0.0
 */

@RestControllerAdvice(basePackages = SystemConstant.BASE_PACKAGE)
public class GlobalResponseBodyHandler implements ResponseBodyAdvice<Object> {

    /**
     * Whether this component supports the given controller method return type and the
     * selected {@code HttpMessageConverter} type.
     * @param returnType the return type
     * @param converterType the selected converter type
     * @return {@code true} if {@link #beforeBodyWrite} should be invoked; {@code false}
     * otherwise
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * Invoked after an {@code HttpMessageConverter} is selected and just before its write
     * method is invoked.
     * @param body the body to be written
     * @param returnType the return type of the controller method
     * @param selectedContentType the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request the current request
     * @param response the current response
     * @return the body that was passed in or a modified (possibly new) instance
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
        if (body instanceof AbstractResult) {
            return body;
        }
        else if (body instanceof Boolean) {
            return ResultFactory.status(Boolean.parseBoolean(String.valueOf(body)));
        }
        else {
            return ResultFactory.data(body);
        }
    }

}