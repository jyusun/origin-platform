package com.jyusun.origin.base.redis.idempotent;

import com.jyusun.origin.core.common.util.AssertUtil;
import com.jyusun.origin.core.common.util.WebUtil;
import com.jyusun.origin.base.redis.common.constant.RedisExtConstant;
import com.jyusun.origin.base.redis.helper.RedisHelper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 接口幂等
 *
 * @author jyusun at 2022-12-15 20:25:20
 */
@Aspect
@Component
@RequiredArgsConstructor
public class AspectIdempotent {

    private final RedisHelper redisHelper;

    /**
     * 定义切点
     * @param apiIdempotent {@link ApiIdempotent} 幂等注解
     */
    @Pointcut("@annotation(apiIdempotent)")
    public void doIdempotent(ApiIdempotent apiIdempotent) {
    }

    /**
     * 环绕通知
     */
    @SneakyThrows
    @Around("doIdempotent(apiIdempotent)")
    public Object doBefore(ProceedingJoinPoint proceedingJoinPoint, ApiIdempotent apiIdempotent) {
        String token = Objects.requireNonNull(WebUtil.getRequest()).getHeader(RedisExtConstant.API_IDEMPOTENT_HEADER);
        boolean del = redisHelper.delete(RedisExtConstant.API_IDEMPOTENT_KEY + token);
        // 如果成功删除，证明token存在，否则证明没有获取到token或已过期 不要先查在删会影响性能
        AssertUtil.isTrue(del, apiIdempotent.errorMsg());
        return proceedingJoinPoint.proceed();
    }

}
