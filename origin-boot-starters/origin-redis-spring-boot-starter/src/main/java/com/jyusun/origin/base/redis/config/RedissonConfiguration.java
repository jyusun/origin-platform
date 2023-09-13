package com.jyusun.origin.base.redis.config;

import com.jyusun.origin.base.redis.helper.RedissonHelper;
import com.jyusun.origin.base.redis.rlock.AspectRedissonLock;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RedissonConfiguration {

    private final RedissonClient redissonClient;

    @Bean
    @ConditionalOnMissingBean(RedissonHelper.class)
    public RedissonHelper redissonHelper() {
        return new RedissonHelper(redissonClient);
    }

    @Bean
    public AspectRedissonLock aspectRedissonLock() {
        return new AspectRedissonLock(redissonClient);
    }

}