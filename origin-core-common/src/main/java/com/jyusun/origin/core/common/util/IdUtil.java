package com.jyusun.origin.core.common.util;

import com.jyusun.origin.core.common.util.support.SnowFlake;
import lombok.experimental.UtilityClass;

/**
 * Id工具
 *
 * @author jyusun at 2022-04-01 11:22:15
 */
@UtilityClass
public class IdUtil {

    /**
     * 雪花算法ID
     * @return
     */
    public static Long genSnowFlake() {
        return new SnowFlake(1L, 1L).nextId();
    }

    /**
     * UUID32
     * @return
     */
    public static String genUuid32() {
        return UuidUtil.generateUuidStr32();
    }

    /**
     * UUID36
     * @return
     */
    public static String genUuid36() {
        return UuidUtil.generateUuidStr36();
    }

}
