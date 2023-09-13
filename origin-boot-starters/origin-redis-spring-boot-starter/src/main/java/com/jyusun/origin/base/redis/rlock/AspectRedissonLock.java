package com.jyusun.origin.base.redis.rlock;

import com.jyusun.origin.core.common.constant.ResultConstant;
import com.jyusun.origin.core.common.exception.ExceptionFactory;
import com.jyusun.origin.core.common.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.annotation.Order;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁切面
 *
 * @author jyusun at 2023-04-26 16:32:16
 */
@Slf4j
@Aspect
@Order(-10)
@RequiredArgsConstructor
public class AspectRedissonLock {

    /**
     * 分布式锁前缀
     */
    private static final String REDISSON_LOCK_PREFIX_KEY = "opf:redisson:lock:";

    private final RedissonClient redissonClient;

    /**
     * 切点
     * @param redissonLock
     */
    @Pointcut("@annotation(redissonLock)")
    public void rlockAspect(RedissonLock redissonLock) {
    }

    /**
     * 通过 Spring SpEL 获取参数
     * @param key 定义的 key值以 # 开头 例如：#user
     * @param parameterNames 形参
     * @param values 形参值
     * @param keyConstant key的常亮
     * @return
     */
    public List<String> getVauleBySpel(String key, String[] parameterNames, Object[] values, String keyConstant) {
        List<String> keys = new ArrayList<>();
        if (!key.contains("#")) {
            String s = REDISSON_LOCK_PREFIX_KEY + key + keyConstant;
            log.info("==== 没有使用 SpEL 表达式 value -> {}", s);
            keys.add(s);
            return keys;
        }
        // SpEL 解析器
        ExpressionParser parser = new SpelExpressionParser();
        // SpEL 上下文
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], values[i]);
        }
        Expression expression = parser.parseExpression(key);
        Object value = expression.getValue(context);
        if (ObjectUtil.isNotEmpty(value)) {
            if (value instanceof List) {
                for (Object o : (List<Object>) value) {
                    keys.add(REDISSON_LOCK_PREFIX_KEY + o.toString() + keyConstant);
                }
            }
            else if (value.getClass().isArray()) {
                Object[] arrObj = (Object[]) value;
                for (Object o : arrObj) {
                    keys.add(REDISSON_LOCK_PREFIX_KEY + o + keyConstant);
                }
            }
            else {
                keys.add(REDISSON_LOCK_PREFIX_KEY + value + keyConstant);
            }
        }
        log.info("==== SpEL 表达式 key={}, value={}", key, keys);
        return keys;
    }

    @Around(value = "rlockAspect(redissonLock)", argNames = "proceedingJoinPoint,redissonLock")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint, RedissonLock redissonLock) throws Throwable {
        String[] keys = redissonLock.keys();
        if (keys.length == 0) {
            throw new RuntimeException("keys 不能为空");
        }
        String[] parameterNames = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        // new
        // StandardReflectionParameterNameDiscoverer().getParameterNames(((MethodSignature)
        // proceedingJoinPoint.getSignature()).getMethod());
        Object[] args = proceedingJoinPoint.getArgs();

        long attemptTimeout = redissonLock.attemptTimeout();
        long lockWatchdogTimeout = redissonLock.lockWatchdogTimeout();

        RedissonLockEnum lockModel = redissonLock.lockModel();
        if (lockModel.equals(RedissonLockEnum.AUTO)) {
            if (keys.length > 1) {
                lockModel = RedissonLockEnum.REDLOCK;
            }
            else {
                lockModel = RedissonLockEnum.REENTRANT;
            }
        }
        if (!lockModel.equals(RedissonLockEnum.MULTIPLE) && !lockModel.equals(RedissonLockEnum.REDLOCK)
                && keys.length > 1) {
            throw new RuntimeException("参数有多个, 锁模式为 -> " + lockModel.name() + ".无法锁定");
        }
        log.info("==== 锁模式 -> {}, 等待锁定时间 -> {} 秒.锁定最长时间 -> {} 秒", lockModel.name(), attemptTimeout / 1000,
                lockWatchdogTimeout / 1000);
        boolean res = false;
        RLock rLock = null;
        // 一直等待加锁
        switch (lockModel) {
            case FAIR:
                rLock = redissonClient
                    .getFairLock(getVauleBySpel(keys[0], parameterNames, args, redissonLock.keyConstant()).get(0));
                break;
            case REDLOCK:
                List<RLock> rLocks = new ArrayList<>();
                for (String key : keys) {
                    List<String> vauleBySpel = getVauleBySpel(key, parameterNames, args, redissonLock.keyConstant());
                    for (String s : vauleBySpel) {
                        rLocks.add(redissonClient.getLock(s));
                    }
                }
                RLock[] locks = new RLock[rLocks.size()];
                int index = 0;
                for (RLock r : rLocks) {
                    locks[index++] = r;
                }
                rLock = new RedissonRedLock(locks);
                break;
            case MULTIPLE:
                rLocks = new ArrayList<>();

                for (String key : keys) {
                    List<String> vauleBySpel = getVauleBySpel(key, parameterNames, args, redissonLock.keyConstant());
                    for (String s : vauleBySpel) {
                        rLocks.add(redissonClient.getLock(s));
                    }
                }
                locks = new RLock[rLocks.size()];
                index = 0;
                for (RLock r : rLocks) {
                    locks[index++] = r;
                }
                rLock = new RedissonMultiLock(locks);
                break;
            case REENTRANT:
                List<String> vauleBySpel = getVauleBySpel(keys[0], parameterNames, args, redissonLock.keyConstant());
                // 如果spel表达式是数组或者LIST 则使用红锁
                if (vauleBySpel.size() == 1) {
                    rLock = redissonClient.getLock(vauleBySpel.get(0));
                }
                else {
                    locks = new RLock[vauleBySpel.size()];
                    index = 0;
                    for (String s : vauleBySpel) {
                        locks[index++] = redissonClient.getLock(s);
                    }
                    rLock = new RedissonRedLock(locks);
                }
                break;
            case READ:
                RReadWriteLock readLock = redissonClient
                    .getReadWriteLock(getVauleBySpel(keys[0], parameterNames, args, redissonLock.keyConstant()).get(0));
                rLock = readLock.readLock();
                break;
            case WRITE:
                RReadWriteLock writeLock = redissonClient
                    .getReadWriteLock(getVauleBySpel(keys[0], parameterNames, args, redissonLock.keyConstant()).get(0));
                rLock = writeLock.writeLock();
                break;
        }

        // 执行 AOP
        if (ObjectUtil.isNotEmpty(rLock)) {
            try {
                if (attemptTimeout == -1) {
                    res = true;
                    // 一直等待加锁
                    rLock.lock(lockWatchdogTimeout, TimeUnit.MILLISECONDS);
                }
                else {
                    res = rLock.tryLock(attemptTimeout, lockWatchdogTimeout, TimeUnit.MILLISECONDS);
                }
                if (!res) {
                    throw ExceptionFactory.createBiz(ResultConstant.MESSAGE_FORBIDDEN, "请求过于频繁，请稍后再试");
                }
                return proceedingJoinPoint.proceed();
            }
            finally {
                if (res) {
                    String name = rLock.getName();
                    rLock.unlock();
                    log.info("==== Key：{} 解锁成功", name);
                }
            }
        }
        throw ExceptionFactory.createBiz(ResultConstant.MESSAGE_FORBIDDEN, "请求过于频繁，请稍后再试");
    }

}