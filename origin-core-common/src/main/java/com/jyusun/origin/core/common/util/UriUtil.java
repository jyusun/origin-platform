package com.jyusun.origin.core.common.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.web.util.UriUtils;

import java.net.URI;
import java.nio.charset.Charset;

/**
 * uri处理工具类
 *
 * @author L.cm
 */
@UtilityClass
public class UriUtil extends UriUtils {

    /**
     * url 编码，同js decodeURIComponent
     * @param source url
     * @param charset 字符集
     * @return 编码后的url
     */
    public static String encodeUrl(String source, Charset charset) {
        return UriUtil.encode(source, charset.name());
    }

    /**
     * url 解码
     * @param source url
     * @param charset 字符集
     * @return 解码url
     */
    public static String decodeUrl(String source, Charset charset) {
        return UriUtil.decode(source, charset.name());
    }

    /**
     * 获取url路径
     * @param uriStr 路径
     * @return url路径
     */
    @SneakyThrows
    public static String getPath(String uriStr) {
        return new URI(uriStr).getPath();
    }

}
