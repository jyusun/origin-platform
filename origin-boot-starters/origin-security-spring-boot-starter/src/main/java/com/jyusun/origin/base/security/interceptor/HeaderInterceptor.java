package com.jyusun.origin.base.security.interceptor;

import com.jyusun.origin.base.security.config.props.JwtTokenProperties;
import com.jyusun.origin.base.security.handler.JwtInfoHandler;
import com.jyusun.origin.core.common.enums.SystemResultEnum;
import com.jyusun.origin.core.common.exception.SecureException;
import com.jyusun.origin.core.common.util.StringUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Header Interceptor
 *
 * @author jyusun at 2022-08-04 21:09:56
 */
@Slf4j
@RequiredArgsConstructor
public class HeaderInterceptor implements AsyncHandlerInterceptor {

    private final JwtTokenProperties jwtTokenProperties;

    private final JwtInfoHandler jwtInfoHandler;

    /**
     * 在业务处理请求之前处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // 判断对象是否是映射到一个方法，如果不是则直接通过
        if (!(handler instanceof HandlerMethod)) {
            // instanceof运算符是用来在运行时指出对象是否是特定类的一个实例
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Method method = handlerMethod.getMethod();
        // 检查方法是否有PassLogin注解，有则跳过认证
        // if (method.isAnnotationPresent(PassLogin.class)) {
        // return true;
        // }

        // 从HTTP请求头中获取Authorization信息，
        String authorization = request.getHeader(jwtTokenProperties.getHeader());
        // 判断Authorization是否为空
        if (StringUtil.hasText(authorization)) {
            log.info("token无效");
            SystemResultEnum resultEnum = SystemResultEnum.UNAUTHORIZED;
            throw new SecureException(resultEnum.code(), resultEnum.desc());
        }
        // 获取TOKEN,注意要清除前缀"Bearer "
        String token = authorization.replace("Bearer ", "");
        // HTTP请求头中TOKEN解析出的用户信息
        Claims claims = jwtInfoHandler.parseToken(token);
        if (claims == null) {
            log.info("token无效");
            SystemResultEnum resultEnum = SystemResultEnum.UNAUTHORIZED;
            throw new SecureException(resultEnum.code(), resultEnum.desc());
        }
        // 校验是否过期
        boolean flag = jwtInfoHandler.isExpired(claims.getExpiration());
        if (flag) {
            log.error("token过期");
            SystemResultEnum resultEnum = SystemResultEnum.UNAUTHORIZED;
            throw new SecureException(resultEnum.code(), resultEnum.desc());
        }
        // token正常，获取用户信息，比如这里的subject存的是用户id
        String subject = claims.getSubject();
        // 将用户信息存入request，以便后面处理请求使用
        request.setAttribute("subject", subject);
        return true;
    }

}
