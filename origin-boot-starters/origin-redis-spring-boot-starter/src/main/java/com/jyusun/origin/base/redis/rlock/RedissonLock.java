package com.jyusun.origin.base.redis.rlock;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedissonLock {

    /**
     * 锁的模式：如果不设置，自动模式，当参数只有一个使用 REENTRANT 参数多个 MULTIPLE
     * @return
     */
    RedissonLockEnum lockModel() default RedissonLockEnum.AUTO;

    /**
     * 如果 keys 有多个，如果不设置则使用联锁
     * @return
     */
    String[] keys() default {};

    /**
     * key 的静态常量：当 key 的 spel 的值是 List，数组时使用 + 号连接将会被 spel 认为这个变量是个字符串，只能产生一把锁，达不到我们的目的
     * 而我们如果又需要一个常量的话这个参数将会在拼接在每个元素的后面
     * @return
     */
    String keyConstant() default "";

    /**
     * 等待加锁超时时间，默认 10000 毫秒 -1 则表示一直等待
     * @return
     */
    long attemptTimeout() default 10000L;

    /**
     * 锁超时时间，默认 30000 毫秒
     * <p>
     * 在一个分布式环境下，多个服务实例请求获取锁，其中服务实例 A 成功获取到了锁，在执行业务逻辑的过程中，服务实例突然挂掉了可以采用锁超时机制解决 如果服务实例 A
     * 没有宕机但是业务执行还没有结束，锁释放掉了就会导致线程问题（误删锁）。此时就一定要实现自动延长锁有效期的机制
     * 作用：只要这台服务实例没有挂掉，并且没有主动释放锁，看门狗都会每隔十秒给你续约一下，保证锁一直在你手中
     * @return
     */
    long lockWatchdogTimeout() default 30000L;

}