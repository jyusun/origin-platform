package com.jyusun.origin.core.common.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.CharUtils;

/**
 * 字符工具类<br>
 * 部分工具来自于Apache Commons系列
 *
 * @author jyusun
 * @since 1.0.0
 */
@UtilityClass
public class CharUtil extends CharUtils {

    public static final char SPACE = ' ';

    public static final char TAB = '	';

    public static final char DOT = '.';

    public static final char SLASH = '/';

    public static final char BACKSLASH = '\\';

    public static final char CR = '\r';

    public static final char LF = '\n';

    public static final char UNDERLINE = '_';

    public static final char DASHED = '-';

    public static final char COMMA = ',';

    public static final char DELIM_START = '{';

    public static final char DELIM_END = '}';

    public static final char BRACKET_START = '[';

    public static final char BRACKET_END = ']';

    public static final char COLON = ':';

    public static final char DOUBLE_QUOTES = '"';

    public static final char SINGLE_QUOTE = '\'';

    public static final char AMP = '&';

    /**
     * 是否为ASCII字符，ASCII字符位于0~127之间
     *
     * <pre>
     *   CharUtil.isAscii('a')  = true
     *   CharUtil.isAscii('A')  = true
     *   CharUtil.isAscii('3')  = true
     *   CharUtil.isAscii('-')  = true
     *   CharUtil.isAscii('\n') = true
     *   CharUtil.isAscii('&copy;') = false
     * </pre>
     * @param ch 被检查的字符处
     * @return true表示为ASCII字符，ASCII字符位于0~127之间
     */
    public static boolean isAscii(char ch) {
        return ch < 128;
    }

}
