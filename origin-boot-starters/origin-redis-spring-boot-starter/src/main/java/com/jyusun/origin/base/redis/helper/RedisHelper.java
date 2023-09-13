package com.jyusun.origin.base.redis.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Redis 操作
 * <p>
 * String: 字符串 Hash: 散列 List: 列表 Set: 集合 Sorted Set: 有序集合
 *
 * @author jyusun at 22:02:02
 */
@RequiredArgsConstructor
public class RedisHelper {

    private final RedisTemplate<String, Object> redisTemplate;

    private final StringRedisTemplate stringRedisTemplate;

    /*
     * ------------------------------------- key相关操作
     * -------------------------------------------
     */

    /**
     * 用于删除已存在的键,不存在的 key 会被忽略
     * @param key KEY
     */
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 用于序列化给定 key ,并返回被序列化的值
     * @param key KEY
     */
    public byte[] dump(String key) {
        return redisTemplate.dump(key);
    }

    /**
     * 用于检查给定 key 是否存在
     * @param key KEY
     */
    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 用于设置 key 的过期时间，key 过期后将不再可用。单位以秒计
     * @param key KEY
     * @param seconds 过期时间-秒
     */
    public Boolean expire(String key, long seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * 用于以 UNIX 时间戳(unix timestamp)格式设置 key 的过期时间。key 过期后将不再可用
     * @param key KEY
     * @param unixTime 秒时间戳：1611567518
     */
    public Boolean expireAt(String key, long unixTime) {
        Instant ins = Instant.ofEpochSecond(unixTime);
        return redisTemplate.expireAt(key, ins);
    }

    /**
     * 以毫秒为单位设置 key 的生效时间
     * @param key KEY
     * @param milliseconds 毫秒
     */
    public Boolean pexpire(String key, long milliseconds) {
        return redisTemplate.expire(key, milliseconds, TimeUnit.MILLISECONDS);
    }

    /**
     * 以毫秒为单位设置 key 的生效时间
     * @param key KEY
     * @param milliseconds 毫秒时间戳：1611567518000
     */
    public Boolean pexpireAt(String key, Long milliseconds) {
        Instant ins = Instant.ofEpochMilli(milliseconds);
        return redisTemplate.expireAt(key, ins);
    }

    /**
     * 用于查找所有符合给定模式 pattern 的 key
     * @param pattern *
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 移除 key 的过期时间，key 将持久保持
     * @param key KEY
     */
    public Boolean persist(String key) {
        return redisTemplate.persist(key);
    }

    /**
     * 获取过期时间
     * @param key KEY
     */
    public Long extGetExpire(String key, final TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    /**
     * Pttl 命令以毫秒为单位返回 key 的剩余过期时间
     * @param key KEY
     */
    public Long pttl(String key) {
        return this.extGetExpire(key, TimeUnit.MILLISECONDS);
    }

    /**
     * Pttl 命令以秒为单位返回 key 的剩余过期时间
     * @param key KEY
     */
    public Long ttl(String key) {
        return this.extGetExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 从当前数据库中随机返回一个 key
     */
    public String randomKey() {
        return redisTemplate.randomKey();
    }

    /**
     * 修改 key 的名称
     * @param oldKey 原KEY
     * @param newKey 新KEY
     */
    public void rename(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * 用于在新的 key 不存在时修改 key 的名称
     * @param oldKey 原KEY
     * @param newKey 新KEY
     * @return 修改成功时，返回 true 。 如果 NEW_KEY_NAME 已经存在，返回 false
     */
    public Boolean renameIfAbsent(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 用于返回 key 所储存的值的类型
     * @return 返回 key 的数据类型，数据类型有: none (key不存在)、string (字符串)、list (列表)、set (集合)、zset
     * (有序集)、hash (哈希表)
     */
    public String type(String key) {
        return Objects.requireNonNull(redisTemplate.type(key)).code();
    }

    /*
     * ------------------------------------- String 相关操作
     * -------------------------------------------
     */

    /**
     * Redis 字符串数据类型的相关命令用于管理 redis 字符串值
     * @return ValueOperations
     */
    private ValueOperations<String, String> valueOperations() {
        return this.stringRedisTemplate.opsForValue();
    }

    /**
     * 用于设置给定 key 的值。如果 key 已经存储其他值， SET 就覆写旧值，且无视类型
     * @param key KEY
     * @param value VALUE
     */
    public String set(String key, String value) {
        this.valueOperations().set(key, value);
        return value;
    }

    /**
     * 用于获取指定 key 的值。如果 key 不存在，返回 nil 。如果key 储存的值不是字符串类型，返回一个错误
     * @param key KEY
     */
    public String get(String key) {
        return this.valueOperations().get(key);
    }

    /**
     * 返回所有(一个或多个)给定 key 的值。 如果给定的 key 里面，有某个 key 不存在，那么这个 key 返回特殊值 nil
     * @param keys KEY数组
     */
    public List<String> mget(String... keys) {
        return this.valueOperations().multiGet(Arrays.asList(keys));
    }

    /**
     * 用于同时设置一个或多个 key-value 对
     * @param kvs {@link Map<String, String> kvs}
     */
    public Map<String, String> mset(Map<String, String> kvs) {
        this.valueOperations().multiSet(kvs);
        return kvs;
    }

    /**
     * 用于所有给定 key 都不存在时，同时设置一个或多个 key-value
     * <p>
     * Redis语法: redis 127.0.0.1:6379> MSETNX key1 value1 key2 value2 .. keyN valueN
     * @param kvs <code>Map<String,String></code>
     * @return {@link Boolean} true-成功|false-失败
     */
    public Boolean msetnx(Map<String, String> kvs) {
        return this.valueOperations().multiSetIfAbsent(kvs);
    }

    /**
     * 用于获取存储在指定 key 中字符串的子字符串。字符串的截取范围由 start 和 end 两个偏移量决定(包括 start 和 end 在内!)
     */
    public String getRange(String key, Long start, Long end) {
        return this.valueOperations().get(key, start, end);
    }

    /**
     * 为指定的 key 设置值及其过期时间。如果 key 已经存在， SETEX 命令将会替换旧的值和过期时间
     * @param key 指定 key
     * @param value 值
     * @param seconds 过期时间-秒
     */
    public Long setex(String key, String value, Long seconds) {
        this.valueOperations().set(key, value, seconds, TimeUnit.SECONDS);
        return seconds;
    }

    /**
     * 命令以毫秒 milliseconds 为单位设置 key 的生存时间
     */
    public String psetxx(String key, String value, Long milliseconds) {
        this.valueOperations().set(key, value, milliseconds, TimeUnit.MILLISECONDS);
        return value;
    }

    /**
     * 在指定的 key 不存在时，为 key 设置指定的值.否则设置无效
     */
    public Boolean setnx(String key, String value) {
        return this.valueOperations().setIfAbsent(key, value);
    }

    /**
     * 用指定的字符串覆盖给定 key 所储存的字符串值，覆盖的位置从偏移量 offset 开始
     */
    public void setRange(String key, String value, Long offset) {
        this.valueOperations().set(key, value, offset);
    }

    /**
     * 用于获取指定 key 所储存的字符串值的长度。当 key 储存的不是字符串值时，返回一个错误
     */
    public Long size(String key) {
        return this.valueOperations().size(key);
    }

    /**
     * Redis Incr 命令将 key 中储存的数字值增一。 !:如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
     * !:如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。 !:本操作的值限制在 64 位(bit)有符号数字表示之内。
     */
    public Long incr(String key) {
        return this.valueOperations().increment(key);
    }

    /**
     * Redis Incr 命令将 key 中储存的数字值减一。 !:如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
     * !:如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。 !:本操作的值限制在 64 位(bit)有符号数字表示之内。
     */
    public Long decr(String key) {
        return this.valueOperations().decrement(key);
    }

    /**
     * 用于为指定的 key 追加值。 :如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。 :如果 key
     * 不存在， APPEND 就简单地将给定 key 设为 value ，就像执行 SET key value 一样
     */
    public Object append(String key, String value) {
        return this.valueOperations().append(key, value);
    }

    /*
     * ------------------------------------- hash 相关操作
     * -------------------------------------------
     */

    /**
     * Redis hash 是一个 string 类型的 field（字段） 和 value（值） 的映射表，hash 特别适合用于存储对象。 Redis 中每个 hash
     * 可以存储 232 - 1 键值对（40多亿）。
     * @return HashOperations
     */
    private HashOperations<String, Object, Object> hashOperations() {
        return this.stringRedisTemplate.opsForHash();
    }

    /**
     * 用于删除哈希表 key 中的一个或多个指定字段，不存在的字段将被忽略
     */
    public Long hdel(String key, String... fields) {
        return this.hashOperations().delete(key, (Object) fields);
    }

    /**
     * 用于返回哈希表中指定字段的值
     */
    public Object hget(String key, String field) {
        return this.hashOperations().get(key, field);
    }

    /**
     * 用于返回哈希表中，所有的字段和值 基本语法：redis 127.0.0.1:6379> HGETALL KEY_NAME
     */
    public Map<Object, Object> hgetAll(String key) {
        return this.hashOperations().entries(key);
    }

    /**
     * 用于返回哈希表中，一个或多个给定字段的值 :如果指定的字段不存在于哈希表，那么返回一个 nil 值
     */
    public Object hmget(String key, String... fields) {
        return this.hashOperations().multiGet(key, Arrays.asList(fields));
    }

    /**
     * 用于查看哈希表的指定字段是否存在
     */
    public Boolean hexists(String key, String field) {
        return this.hashOperations().hasKey(key, field);
    }

    /**
     * 用于获取哈希表中的所有域（field）
     */
    public Set<String> hkeys(String pattern) {
        return this.hashOperations().getOperations().keys(pattern);
    }

    /**
     * 返回哈希表所有域(field)的值
     */
    public List<Object> hvalues(String key) {
        return this.hashOperations().values(key);
    }

    /**
     * 用于获取哈希表中字段的数量
     */
    public Long hlen(String key) {
        return this.hashOperations().size(key);
    }

    /**
     * 用于为哈希表中的字段赋值
     * <p>
     * 基本语法：redis 127.0.0.1:6379> HSET KEY_NAME FIELD VALUE
     */
    public void hset(String key, String field, String value) {
        this.hashOperations().put(key, field, value);
    }

    /**
     * 用于同时将多个 field-value (字段-值)对设置到哈希表中
     * <p>
     * 基础语法：redis 127.0.0.1:6379> HMSET KEY_NAME FIELD1 VALUE1 ...FIELDN VALUEN
     */
    public Map<String, String> hmset(String key, Map<String, String> hash) {
        this.hashOperations().putAll(key, hash);
        return hash;
    }

    /**
     * 用于为哈希表中不存在的的字段赋值
     */
    public Boolean hsetnx(String key, String field, String value) {
        return this.hashOperations().putIfAbsent(key, field, value);
    }

    /*
     * ------------------------------------- List 相关操作
     * -------------------------------------------
     */

    /**
     * Redis hash 是一个 string 类型的 field（字段） 和 value（值） 的映射表，hash 特别适合用于存储对象。 Redis 中每个 hash
     * 可以存储 232 - 1 键值对（40多亿）。
     * @return HashOperations
     */
    private ListOperations<String, String> listOperations() {
        return this.stringRedisTemplate.opsForList();
    }

    /**
     * 命令移出并获取多个指定 key 列表第一个元素，如果元素为空返回nil
     * <p>
     * 基础语法：redis 127.0.0.1:6379> BLPOP LIST1 LIST2 .. LISTN TIMEOUT
     */
    public String blpop(String key) {
        return this.listOperations().leftPop(key);
    }

    /**
     * 命令移出并获取指定 key 列表第一个元素，如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     */
    public String blpop(String key, Long timeout) {
        return this.listOperations().leftPop(key, Duration.ofMillis(timeout));
    }

    /**
     * 命令移出并获取多个指定 key 列表最后一个元素，如果元素为空返回nil
     */
    public String brpop(String key) {
        return this.listOperations().rightPop(key);
    }

    /**
     * 命令移出并获取指定 key 列表最后一个元素，如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     */
    public String brpop(String key, long timeout) {
        return this.listOperations().rightPop(key, Duration.ofMillis(timeout));
    }

    /**
     * 用于通过索引获取列表中的元素
     * @param index 0 or -1
     */
    public String lindex(String key, Long index) {
        return this.listOperations().index(key, index);
    }

    /**
     * 用于在列表的元素前或者后插入元素。当指定元素不存在于列表中时，不执行任何操作
     * @param pivot 以那个元素为中心点
     */
    public Long linsert(String key, String pivot, String value) {
        return this.listOperations().leftPush(key, pivot, value);
    }

    /**
     * 用于返回列表的长度
     */
    public Long llen(String key) {
        return this.listOperations().size(key);
    }

    /**
     * 移出并获取列表的第一个元素
     */
    public String lpop(String key) {
        return this.listOperations().leftPop(key);
    }

    /**
     * 将一个或多个值插入到列表头部。 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。 当 key 存在但不是列表类型时，返回一个错误
     */
    public Long lpush(String key, String... values) {
        return this.listOperations().leftPushAll(key, values);
    }

    /**
     * 将一个值插入到已存在的列表头部，列表不存在时操作无效
     */
    public Long lpushx(String key, String value) {
        return this.listOperations().leftPushIfPresent(key, value);
    }

    /**
     * 返回列表中指定区间内的元素，区间以偏移量 START 和 END 指定
     */
    public List<String> lrange(String key, Long start, Long end) {
        return this.listOperations().range(key, start, end);
    }

    /**
     * 根据参数 COUNT 的值，移除列表中与参数 VALUE 相等的元素 count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT
     * 。 count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。 count = 0 : 移除表中所有与 VALUE
     * 相等的值
     */
    public Long lrem(String key, Long count, String value) {
        return this.listOperations().remove(key, count, value);
    }

    /**
     * 通过索引来设置元素的值
     */
    public void lset(String key, Long index, String value) {
        this.listOperations().set(key, index, value);
    }

    /**
     * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除
     */
    public void ltrim(String key, Long start, Long end) {
        this.listOperations().trim(key, start, end);
    }

    /**
     * 用于移除列表的最后一个元素，返回值为移除的元素
     */
    public String rpop(String key) {
        return this.listOperations().rightPop(key);
    }

    /**
     * 用于将一个或多个值插入到列表的尾部(最右边)
     */
    public Long rpush(String key, String... values) {
        return this.listOperations().rightPushAll(key, values);
    }

    /**
     * 用于将一个值插入到已存在的列表尾部(最右边)。如果列表不存在，操作无效
     */
    public Long rpushx(String key, String value) {
        return this.listOperations().rightPushIfPresent(key, value);
    }

    /*
     * ------------------------------------- Set 相关操作
     * -------------------------------------------
     */

    /**
     * 将一个或多个成员元素加入到集合中，已经存在于集合or重复的成员元素将被忽略。 假如集合 key 不存在，则创建一个只包含添加的元素作成员的集合。 当集合 key
     * 不是集合类型时，返回一个错误。
     */
    public Long sadd(String key, String... members) {
        return stringRedisTemplate.opsForSet().add(key, members);
    }

    /**
     * 返回集合中元素的数量
     */
    public Long scard(String key) {
        return stringRedisTemplate.opsForSet().size(key);
    }

    /**
     * 返回给定所有给定集合的交集。 不存在的集合 key 被视为空集。 当给定集合当中有一个空集时，结果也为空集(根据集合运算定律)
     */
    public Set<String> sinter(String... keys) {
        return stringRedisTemplate.opsForSet().intersect(Arrays.asList(keys));
    }

    /**
     * 判断成员元素是否是集合的成员
     */
    public Boolean sismember(String key, String member) {
        return stringRedisTemplate.opsForSet().isMember(key, member);
    }

    /**
     * 返回集合中的所有的成员。 不存在的集合 key 被视为空集合
     */
    public Set<String> smembers(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    /**
     * 命令将指定成员 member 元素从 source 集合移动到 destination 集合 当 destination 集合已经包含 member 元素时，
     * SMOVE 命令只是简单地将 source 集合中的 member 元素删除。 当 source 或 destination 不是集合类型时，返回一个错误
     * @param srckey set1
     * @param dstkey set2
     * @param member 移动的值
     */
    public Boolean smove(String srckey, String dstkey, String member) {
        return stringRedisTemplate.opsForSet().move(srckey, member, dstkey);
    }

    /**
     * 用于移除集合中的指定 key 的一个随机元素，移除后会返回移除的元素
     */
    public String spop(String key) {
        return stringRedisTemplate.opsForSet().pop(key);
    }

    /**
     * 用于移除集合中的指定 key 的多个随机元素，移除后会返回移除的元素
     */
    public List<String> spop(String key, Long count) {
        return stringRedisTemplate.opsForSet().pop(key, count);
    }

    /**
     * 用于返回集合中的一个随机元素。
     */
    public String srandmember(String key) {
        return stringRedisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 用于返回集合中指定count数量的随机元素 如果 count 为正数，且小于集合基数，那么命令返回一个包含 count 个元素的数组，数组中的元素各不相同。如果
     * count 大于等于集合基数，那么返回整个集合。 如果 count 为负数，那么命令返回一个数组，数组中的元素可能会重复出现多次，而数组的长度为 count 的绝对值
     */
    public List<String> srandmember(String key, Integer count) {
        return stringRedisTemplate.opsForSet().randomMembers(key, count);
    }

    /**
     * 用于移除集合中的一个或多个成员元素，不存在的成员元素会被忽略
     */
    public Long srem(String key, String... members) {
        return stringRedisTemplate.opsForSet().remove(key, members);
    }

    /**
     * 返回给定集合的并集。不存在的集合 key 被视为空集
     */
    public Set<String> sunion(String... keys) {
        return stringRedisTemplate.opsForSet().union(Arrays.asList(keys));
    }

    /*
     * ------------------------------------- sortedset 相关操作
     * -------------------------------------------
     */

    /**
     * ZSET
     * @return ZSetOperations
     */
    private ZSetOperations<String, String> zset() {
        return stringRedisTemplate.opsForZSet();
    }

    /**
     * Redis Zadd 命令用于将一个或多个成员元素及其分数值加入到有序集当中。
     * 如果某个成员已经是有序集的成员，那么更新这个成员的分数值，并通过重新插入这个成员元素，来保证该成员在正确的位置上。 分数值可以是整数值或双精度浮点数。 如果有序集合
     * key 不存在，则创建一个空的有序集并执行 ZADD 操作。 当 key 存在但不是有序集类型时，返回一个错误。
     */
    public Boolean zadd(String key, Double score, String member) {
        return this.zset().add(key, member, score);
    }

    /**
     * Redis Zadd 命令用于将一个或多个成员元素及其分数值加入到有序集当中。
     * 如果某个成员已经是有序集的成员，那么更新这个成员的分数值，并通过重新插入这个成员元素，来保证该成员在正确的位置上。 分数值可以是整数值或双精度浮点数。 如果有序集合
     * key 不存在，则创建一个空的有序集并执行 ZADD 操作。 当 key 存在但不是有序集类型时，返回一个错误。 注意： 在 Redis 2.4 版本以前， ZADD
     * 每次只能添加一个元素。
     */
    public Long zadd(String key, Map<String, Double> params) {
        Set<ZSetOperations.TypedTuple<String>> typedTuples = params.entrySet()
            .stream()
            .map(obj -> new DefaultTypedTuple<>(obj.getKey(), obj.getValue()))
            .collect(Collectors.toSet());
        return this.zset().add(key, typedTuples);
    }

    /**
     * 用于计算集合中元素的数量
     */
    public Long zcard(String key) {
        return this.zset().size(key);
    }

    /**
     * 用于计算有序集合中指定分数区间的成员数量
     * <p>
     * 基础语法：redis 127.0.0.1:6379> ZLEXCOUNT KEY MIN MAX
     */
    public Long zcount(String key, Double min, Double max) {
        return this.zset().count(key, min, max);
    }

    /**
     * Redis Zrange 返回有序集中，指定区间内的成员。 其中成员的位置按分数值递增(从小到大)来排序。
     * 具有相同分数值的成员按字典序(lexicographical order )来排列
     */
    public Set<String> zrange(String key, long start, long end) {
        return this.zset().range(key, start, end);
    }

    /**
     * Redis Zrange 返回有序集中，指定区间内的成员。 其中成员的位置按分数值递增(从小到大)来排序。
     * 具有相同分数值的成员按字典序(lexicographical order )来排列
     */
    public Set<ZSetOperations.TypedTuple<String>> zrangeWithScores(String key, long start, long end) {
        return this.zset().rangeWithScores(key, start, end);
    }

    /**
     * 返回有序集中指定成员的排名。其中有序集成员按分数值递增(从小到大)顺序排列
     */
    public Long zrank(String key, String member) {
        return this.zset().rank(key, member);
    }

    /**
     * 用于移除有序集中的一个或多个成员，不存在的成员将被忽略
     */
    public Long zrem(String key, String... members) {
        return this.zset().remove(key, members);
    }

    /**
     * 返回有序集中，成员的分数值。 如果成员元素不是有序集 key 的成员，或 key 不存在，返回 nil
     */
    public Double zsocre(String key, String member) {
        return this.zset().score(key, member);
    }

    /*
     * ------------------------------------- Stream 相关操作
     * -------------------------------------------
     */

    /**
     * Redis Stream 是 Redis 5.0 版本新增加的数据结构。
     * <p>
     * Redis Stream 主要用于消息队列（MQ，Message Queue），Redis 本身是有一个 Redis 发布订阅 (pub/sub)
     * 来实现消息队列的功能，但它有个缺点就是消息无法持久化，如果出现网络断开、Redis 宕机等，消息就会被丢弃。
     * <p>
     * 简单来说发布订阅 (pub/sub) 可以分发消息，但无法记录历史消息。
     * <p>
     * 而 Redis Stream 提供了消息的持久化和主备复制功能，可以让任何客户端访问任何时刻的数据，并且能记住每一个客户端的访问位置，还能保证消息不丢失。
     * @return
     */
    private StreamOperations<String, Object, Object> streamOperations() {
        return this.stringRedisTemplate.opsForStream();
    }

    /*
     * ------------------------------------- HyperLogLog 相关操作
     * -------------------------------------------
     */

    /**
     * 将所有元素参数添加到 HyperLogLog 数据结构中
     */
    public Long pfadd(String key, String... elements) {
        return stringRedisTemplate.opsForHyperLogLog().add(key, elements);
    }

    /**
     * 返回给定 HyperLogLog 的基数估算值,已存在的值将会被忽略
     */
    public long pfcount(String keys) {
        return stringRedisTemplate.opsForHyperLogLog().size(keys);
    }

    /**
     * 将多个 HyperLogLog 合并为一个 HyperLogLog ，合并后的 HyperLogLog 的基数估算值是通过对所有 给定 HyperLogLog
     * 进行并集计算得出的
     */
    public Long pfmerge(String destkey, String... sourcekeys) {
        return stringRedisTemplate.opsForHyperLogLog().union(destkey, sourcekeys);
    }

}
