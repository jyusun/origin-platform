package com.jyusun.origin.core.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

/**
 * 作用描述： - 字符串操作工具类
 *
 * @author jyusun at 2020/1/5 16:07
 * @since 1.0.0
 */
@UtilityClass
public final class StringUtil extends StringUtils {

    public static final String SPACE = " ";

    public static final String TAB = "	";

    public static final String DOT = ".";

    public static final String DOUBLE_DOT = "src/main";

    public static final String SLASH = "/";

    public static final String BACKSLASH = "\\";

    public static final String EMPTY = "";

    public static final String NULL = "null";

    public static final String CR = "\r";

    public static final String LF = "\n";

    public static final String CRLF = "\r\n";

    public static final String UNDERLINE = "_";

    public static final String DASHED = "-";

    public static final String COMMA = ",";

    public static final String DELIM_START = "{";

    public static final String DELIM_END = "}";

    public static final String BRACKET_START = "[";

    public static final String BRACKET_END = "]";

    public static final String COLON = ":";

    public static final String HTML_NBSP = "&nbsp;";

    public static final String HTML_AMP = "&amp;";

    public static final String HTML_QUOTE = "&quot;";

    public static final String HTML_APOS = "&apos;";

    public static final String HTML_LT = "&lt;";

    public static final String HTML_GT = "&gt;";

    public static final String EMPTY_JSON = "{}";

    /**
     * 下划线转驼峰
     * @param para 字符串
     * @return String
     */
    public static String underlineToHump(String para) {
        StringBuilder result = new StringBuilder();
        String[] a = para.split(UNDERLINE);
        for (String s : a) {
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            }
            else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * 驼峰转下划线
     * @param para 字符串
     * @return String
     */
    public static String humpToUnderline(String para) {
        StringBuilder sb = new StringBuilder(para);
        int temp = 0;
        for (int i = 0; i < para.length(); i++) {
            if (Character.isUpperCase(para.charAt(i))) {
                sb.insert(i + temp, UNDERLINE);
                temp += 1;
            }
        }
        return sb.toString().toLowerCase();
    }

    public boolean notHasLength(@Nullable CharSequence str) {
        return !hasLength(str);
    }

    public boolean notHasLength(@Nullable String str) {
        return !hasLength(str);
    }

    public boolean notHasText(@Nullable CharSequence str) {
        return !hasText(str);
    }

    public boolean notHasText(@Nullable String str) {
        return !hasText(str);
    }

}
