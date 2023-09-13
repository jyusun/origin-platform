package com.jyusun.origin.base.redis.common.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RedisExtConstant {

    /**
     * 幂等接口KEY
     */
    public static final String API_IDEMPOTENT_HEADER = "api-idempotent-token";

    public static final String API_IDEMPOTENT_KEY = "apiIdempotent:token:";

}
