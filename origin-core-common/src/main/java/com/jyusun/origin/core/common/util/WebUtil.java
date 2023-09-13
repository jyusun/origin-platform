package com.jyusun.origin.core.common.util;

import com.google.common.collect.Maps;
import com.jyusun.origin.core.common.enums.SystemResultEnum;
import com.jyusun.origin.core.common.exception.ExceptionFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;
import java.util.Optional;

/**
 * 作用描述： Web 工具
 *
 * @author jyusun at 2020/4/16 15:18
 * @since 1.0.0
 */
@UtilityClass
public class WebUtil extends WebUtils {

    public static final String USER_AGENT_HEADER = "user-agent";

    private static final String UN_KNOWN = "unknown";

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 获取 HttpServletRequest
     * @return {@link HttpServletRequest}
     */
    public static HttpServletRequest getRequest() {
        return Optional.ofNullable(getRequestAttributes())
            .orElseThrow(
                    () -> ExceptionFactory.createService(SystemResultEnum.INTERNAL_SERVER_ERROR.code(), "未获得正确的请求信息"))
            .getRequest();
    }

    /**
     * 获取 HttpServletRequest
     * @return {@link HttpServletRequest} 响应信息
     */
    public static HttpServletResponse getResponse() {
        return Optional.ofNullable(getRequestAttributes())
            .map(ServletRequestAttributes::getResponse)
            .orElseThrow(
                    () -> ExceptionFactory.createService(SystemResultEnum.INTERNAL_SERVER_ERROR.code(), "未获得正确的响应信息"));
    }

    /**
     * 获取http请求 头信息
     * @param request {@link HttpServletRequest}
     * @return {@link Map}
     */
    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = Maps.newHashMap();
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 获取http请求 参数
     * @param request {@link HttpServletRequest} 请求对象
     * @return {@link Map}
     */
    public static Map<String, String> getParams(HttpServletRequest request) {
        Map<String, String> map = Maps.newHashMap();
        Enumeration<String> params = request.getParameterNames();

        while (params.hasMoreElements()) {
            String key = params.nextElement();
            String value = request.getParameter(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * Web端文件响应头设置
     * @param response {@link HttpServletResponse} httpresp
     * @param fileName {@code String} 文件名称
     */
    @SneakyThrows
    public static void setFileNameHeader(HttpServletResponse response, String fileName) {
        WebUtil.setHeader(response, MediaType.APPLICATION_OCTET_STREAM_VALUE, HttpStatus.OK);
        String encodeName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition", "attachment;filename=" + encodeName);
    }

    @SneakyThrows
    public static void setWriterHeader(HttpServletResponse response, HttpStatus httpStatus, String message) {
        WebUtil.setHeader(response, MediaType.APPLICATION_PROBLEM_JSON_VALUE, httpStatus);
        PrintWriter writer = response.getWriter();
        writer.println(message);
    }

    /**
     * 设置响应头信息
     * @param response
     * @param contentType
     * @param httpStatus
     */
    @SneakyThrows
    public static void setHeader(HttpServletResponse response, String contentType, HttpStatus httpStatus) {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(contentType);
        response.setStatus(httpStatus.value());
    }

    /**
     * 文件流响应
     * @param fileName
     * @param inputStream
     */
    @SneakyThrows
    public static void streamResponse(String fileName, InputStream inputStream) {
        HttpServletResponse response = getResponse();
        setFileNameHeader(response, fileName);
        IOUtil.copy(inputStream, response.getOutputStream());
    }

    /**
     * 获取ip
     * @return {String}
     */
    public static String getIpAddr() {
        return getIpAddr(getRequest());
    }

    /**
     * 获取ip
     * @param request HttpServletRequest
     * @return {String}
     */
    @Nullable
    public static String getIpAddr(HttpServletRequest request) {
        Assert.notNull(request, "HttpServletRequest is null");
        String ip = request.getRemoteAddr();
        if (StringUtil.notHasText(ip) || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Requested-For");
        }
        if (StringUtil.notHasText(ip) || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (StringUtil.notHasText(ip) || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtil.notHasText(ip) || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtil.notHasText(ip) || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtil.notHasText(ip) || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        return StringUtil.notHasText(ip) ? null : ip.split(",")[0];
    }

}
