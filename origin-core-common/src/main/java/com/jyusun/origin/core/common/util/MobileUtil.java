package com.jyusun.origin.core.common.util;

import lombok.experimental.UtilityClass;

/**
 * 手机号工具类
 *
 * @author jyusun at 2022-1-5 22:57:58
 */
@UtilityClass
public class MobileUtil {

    /**
     * 手机号 四位替换为*号
     * @param mobile 手机号码
     * @return 替换后的号码
     */
    public String formatAsterisk(String mobile) {
        if (!StringUtil.hasText(mobile)) {
            return StringUtil.EMPTY;
        }
        return mobile.replace("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

}
