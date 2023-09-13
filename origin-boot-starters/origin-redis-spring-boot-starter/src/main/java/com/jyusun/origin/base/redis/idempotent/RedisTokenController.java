package com.jyusun.origin.base.redis.idempotent;

import com.jyusun.origin.core.common.util.UuidUtil;
import com.jyusun.origin.base.redis.common.constant.RedisExtConstant;
import com.jyusun.origin.base.redis.helper.RedisHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * 获取token
 *
 * @author jyusun
 * @date 202完-12-09
 */
@Tag(name = "接口幂等")
@RestController
@RequestMapping("idempotent/tokens")
@RequiredArgsConstructor
public class RedisTokenController {

    private final RedisHelper redisHelper;

    /**
     * 失效时间
     */
    private static final long TOKEN_TIME_OUT = 60L * 30L;

    @GetMapping
    public String getToken(@RequestParam(value = "tokenTimeOut", required = false) Long tokenTimeOut) {
        String token = UuidUtil.generateUuidStr36();
        redisHelper.psetxx(RedisExtConstant.API_IDEMPOTENT_KEY, token,
                Optional.ofNullable(tokenTimeOut).orElse(TOKEN_TIME_OUT));
        return token;
    }

}
