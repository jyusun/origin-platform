package com.jyusun.origin.core.common.util;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

/**
 * 用户代理信息
 *
 * @author jyusun
 */
@UtilityClass
public final class UserAgentUtil {

    /**
     * 根据http获取userAgent信息
     * @param request {@link HttpServletRequest} HTTP请求
     * @return {@code String}
     */
    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    /**
     * 根据request获取userAgent，然后解析出osVersion
     * @param request {@link HttpServletRequest} HTTP请求
     * @return {@code String}
     */
    public static String getOsVersion(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getOsVersion(userAgent);
    }

    /**
     * 根据userAgent解析出osVersion
     * @param userAgent {@code String} 用户代理
     * @return {@code String}
     */
    public static String getOsVersion(String userAgent) {
        String osVersion = "";
        if (StringUtils.isBlank(userAgent)) {
            return osVersion;
        }
        String[] strArr = userAgent.substring(userAgent.indexOf("(") + 1, userAgent.indexOf(")")).split(";");
        if (ArrayUtil.isEmpty(strArr)) {
            return osVersion;
        }
        return strArr[1];
    }

    /**
     * 获取操作系统对象
     * @param userAgent {@code String} 用户代理
     * @return {@code String}
     */
    private static OperatingSystem getOperatingSystem(String userAgent) {
        UserAgent agent = UserAgent.parseUserAgentString(userAgent);
        return agent.getOperatingSystem();
    }

    /**
     * 获取os：Windows/ios/Android
     * @param request {@link HttpServletRequest} HTTP请求
     * @return {@code String}
     */
    public static String getOs(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getOs(userAgent);
    }

    /**
     * 获取os：Windows/ios/Android
     * @param userAgent {@code String} 用户代理
     * @return {@code String}
     */
    public static String getOs(String userAgent) {
        OperatingSystem operatingSystem = getOperatingSystem(userAgent);
        return operatingSystem.getGroup().getName();
    }

    /**
     * 获取deviceType
     * @param request {@link HttpServletRequest} HTTP请求
     * @return {@code String}
     */
    public static String getDevicetype(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getDevicetype(userAgent);
    }

    /**
     * 获取deviceType
     * @param userAgent {@code String} 用户代理
     * @return {@code String}
     */
    public static String getDevicetype(String userAgent) {
        OperatingSystem operatingSystem = getOperatingSystem(userAgent);
        return operatingSystem.getDeviceType().toString();
    }

    /**
     * 获取操作系统的名字
     * @param request {@link HttpServletRequest} HTTP请求
     * @return {@code String}
     */
    public static String getOsName(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getOsName(userAgent);
    }

    /**
     * 获取操作系统的名字
     * @param userAgent {@code String} 用户代理
     * @return {@code String}
     */
    public static String getOsName(String userAgent) {
        OperatingSystem operatingSystem = getOperatingSystem(userAgent);
        return operatingSystem.getName();
    }

    /**
     * 获取device的生产厂家
     * @param request {@link HttpServletRequest} HTTP请求
     * @return {@code String}
     */
    public static String getDeviceManufacturer(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getDeviceManufacturer(userAgent);
    }

    /**
     * 获取device的生产厂家
     * @param userAgent {@code String} 用户代理
     * @return {@code String}
     */
    public static String getDeviceManufacturer(String userAgent) {
        OperatingSystem operatingSystem = getOperatingSystem(userAgent);
        return operatingSystem.getManufacturer().toString();
    }

    /**
     * 获取浏览器对象
     * @param userAgent {@code String} 用户代理
     * @return {@code String}
     */
    public static Browser getBrowser(String userAgent) {
        UserAgent agent = UserAgent.parseUserAgentString(userAgent);
        return agent.getBrowser();
    }

    /**
     * 获取browser name
     * @param request {@link HttpServletRequest} HTTP请求
     * @return {@code String}
     */
    public static String getBrowserName(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getBrowserName(userAgent);
    }

    /**
     * 获取browser name
     * @param userAgent {@code String} 用户代理
     * @return {@code String}
     */
    public static String getBrowserName(String userAgent) {
        Browser browser = getBrowser(userAgent);
        return browser.getName();
    }

    /**
     * 获取浏览器的类型
     * @param request {@link HttpServletRequest} HTTP请求
     * @return {@code String}
     */
    public static String getBrowserType(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getBrowserType(userAgent);
    }

    /**
     * 获取浏览器的类型
     * @param userAgent {@code String} 用户代理
     * @return {@code String}
     */
    public static String getBrowserType(String userAgent) {
        Browser browser = getBrowser(userAgent);
        return browser.getBrowserType().getName();
    }

    /**
     * 获取浏览器组： CHROME、IE
     * @param request {@link HttpServletRequest} HTTP请求
     * @return {@code String}
     */
    public static String getBrowserGroup(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getBrowserGroup(userAgent);
    }

    /**
     * 获取浏览器组： CHROME、IE
     * @param userAgent {@code String} 用户代理
     * @return {@code String}
     */
    public static String getBrowserGroup(String userAgent) {
        Browser browser = getBrowser(userAgent);
        return browser.getGroup().getName();
    }

    /**
     * 获取浏览器的生产厂商
     * @param request {@link HttpServletRequest} HTTP请求
     * @return {@code String}
     */
    public static String getBrowserManufacturer(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getBrowserManufacturer(userAgent);
    }

    /**
     * 获取浏览器的生产厂商
     * @param userAgent {@code String} 用户代理
     * @return {@code String}
     */
    public static String getBrowserManufacturer(String userAgent) {
        Browser browser = getBrowser(userAgent);
        return browser.getManufacturer().getName();
    }

    /**
     * 获取浏览器使用的渲染引擎
     * @param request {@link HttpServletRequest} HTTP请求
     * @return {@code String}
     */
    public static String getBorderRenderingEngine(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getBorderRenderingEngine(userAgent);
    }

    /**
     * 获取浏览器使用的渲染引擎
     * @param userAgent {@code String} 用户代理
     * @return {@code String}
     */
    public static String getBorderRenderingEngine(String userAgent) {
        Browser browser = getBrowser(userAgent);
        return browser.getRenderingEngine().name();
    }

    /**
     * 获取浏览器版本
     * @param request {@link HttpServletRequest} HTTP请求
     * @return {@code String}
     */
    public static String getBrowserVersion(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getBrowserVersion(userAgent);
    }

    /**
     * 获取浏览器版本
     * @param userAgent {@code String} 用户代理
     * @return {@code String}
     */
    public static String getBrowserVersion(String userAgent) {
        Browser browser = getBrowser(userAgent);
        return browser.getVersion(userAgent).toString();
    }

    // public static void main(String[] args) {
    //// String winUserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64)
    // AppleWebKit/537.36 (KHTML, like Gecko) " +
    //// "Chrome/68.0.3440.106 Safari/537.36";
    // String winUserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36
    // (KHTML, like Gecko) " +
    // "Chrome/90.0.4430.212 Safari/537.36";
    // System.out.println("浏览器组：" + getBrowserGroup(winUserAgent));
    // System.out.println("浏览器名字：" + getBrowserName(winUserAgent));
    // System.out.println("浏览器类型" + getBrowserType(winUserAgent));
    // System.out.println("浏览器生产商：" + getBrowserManufacturer(winUserAgent));
    // System.out.println("浏览器版本：" + getBrowserVersion(winUserAgent));
    // System.out.println("设备生产厂商:" + getDeviceManufacturer(winUserAgent));
    // System.out.println("设备类型:" + getDevicetype(winUserAgent));
    // System.out.println("设备操作系统：" + getOs(winUserAgent));
    // System.out.println("操作系统的名字：" + getOsName(winUserAgent));
    // System.out.println("操作系统的版本号：" + getOsVersion(winUserAgent));
    // System.out.println("操作系统浏览器的渲染引擎:" + getBorderRenderingEngine(winUserAgent));
    // }

}