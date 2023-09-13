package com.jyusun.origin.base.launch.feign.core.interceptor;

import com.jyusun.origin.core.common.util.ObjectUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;

/**
 * feign 请求拦截器(请求头传递给Feign接口)
 *
 * @author jyusun at 2022-08-09 19:03:06
 */
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtil.isNotEmpty(attributes)) {
            HttpServletRequest httpServletRequest = attributes.getRequest();
            Enumeration<String> params = httpServletRequest.getParameterNames();

            while (params.hasMoreElements()) {
                String key = params.nextElement();
                String value = httpServletRequest.getHeader(key);
                requestTemplate.header(key, value);
            }
            // Map<String, String> headers = WebUtil.getHeaders(httpServletRequest);
            // // 传递用户信息请求头，防止丢失
            // String userId = headers.get(SecurityConstant.DETAILS_USER_ID);
            // if (StringUtils.isNotEmpty(userId)) {
            // requestTemplate.header(SecurityConstant.DETAILS_USER_ID, userId);
            // }
            // String userCode = headers.get(SecurityConstant.DETAILS_USER_CODE);
            // if (StringUtils.isNotEmpty(userCode)) {
            // requestTemplate.header(SecurityConstant.DETAILS_USER_CODE, userCode);
            // }
            // String username = headers.get(SecurityConstant.DETAILS_USERNAME);
            // if (StringUtils.isNotEmpty(username)) {
            // requestTemplate.header(SecurityConstant.DETAILS_USERNAME, username);
            // }
            // String authentication = headers.get(SecurityConstant.AUTHORIZATION_HEADER);
            // if (StringUtils.isNotEmpty(authentication)) {
            // requestTemplate.header(SecurityConstant.AUTHORIZATION_HEADER,
            // authentication);
            // }
            // // 配置客户端IP
            // requestTemplate.header("X-Forwarded-For", WebUtil.getIpAddr());
        }
    }

}