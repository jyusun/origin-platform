package com.jyusun.origin.core.common.util;

import lombok.experimental.UtilityClass;

import java.util.UUID;

/**
 * UUID工具
 * <p>
 * 作用描述：UUID操作
 * </p>
 *
 * @author jyusun at 2020/7/2 14:49
 * @since 1.0.0
 */
@UtilityClass
public final class UuidUtil {

    /**
     * UUID 32位 字符串 去横线
     * @return {@code String }
     */
    public static String generateUuidStr32() {
        return generateUuidStr36().replace(StringUtil.DASHED, StringUtil.EMPTY);
    }

    /**
     * UUID 36位 字符串
     * @return {@code String }
     */
    public static String generateUuidStr36() {
        return UUID.randomUUID().toString();
    }

    /**
     * <p>
     * 功能描述：
     * </p>
     * <p>
     * - 返回8位计算后的UUID,可能有概率会重复，需要配合其他的数据使用
     * </p>
     * @return a
     */
    public static String generateShortUuid() {
        StringBuilder shortBuffer = new StringBuilder();
        String uuid = generateUuidStr32();
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8",
            "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
            "SnowFlake", "U", "V", "W", "X", "Y", "Z" };

}
