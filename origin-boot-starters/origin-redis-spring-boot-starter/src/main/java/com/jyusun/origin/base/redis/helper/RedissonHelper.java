package com.jyusun.origin.base.redis.helper;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RBinaryStream;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;

import java.io.InputStream;
import java.io.OutputStream;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class RedissonHelper {

    /**
     * 数据缓存时间，默认 30 分钟
     */
    private static final Long DATA_VALID_TIME = 1000 * 60 * 30L;

    private final RedissonClient redissonClient;

    /**
     * 获取对象值
     * @param name
     * @param <T>
     * @return
     */
    public <T> T getValue(String name) {
        RBucket<T> bucket = redissonClient.getBucket(name);
        return bucket.get();
    }

    /**
     * 获取对象空间
     * @param name
     * @param <T>
     * @return
     */
    public <T> RBucket<T> getBucket(String name) {
        return redissonClient.getBucket(name);
    }

    /**
     * 设置对象的值
     * @param name 键
     * @param value 值
     * @return
     */
    public <T> void setValue(String name, T value) {
        setValue(name, value, DATA_VALID_TIME);
    }

    /**
     * 设置对象的值
     * @param name 键
     * @param value 值
     * @param time 缓存时间 单位毫秒 -1 永久缓存
     * @return
     */
    public <T> void setValue(String name, T value, Long time) {
        RBucket<Object> bucket = redissonClient.getBucket(name);
        if (time == -1) {
            bucket.set(value);
        }
        else {
            bucket.set(value, time, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * 如果值已经存在则则不设置
     * @param name 键
     * @param value 值
     * @param time 缓存时间 单位毫秒
     * @return true 设置成功,false 值存在,不设置
     */
    public <T> Boolean trySetValue(String name, T value, Long time) {
        RBucket<Object> bucket = redissonClient.getBucket(name);
        boolean b;
        if (time == -1) {
            b = bucket.setIfAbsent(value);
        }
        else {

            b = bucket.setIfAbsent(value, Duration.ofMillis(time));
        }
        return b;
    }

    /**
     * 如果值已经存在则则不设置
     * @param name 键
     * @param value 值
     * @return true 设置成功,false 值存在,不设置
     */
    public <T> Boolean trySetValue(String name, T value) {
        return trySetValue(name, value, DATA_VALID_TIME);
    }

    /**
     * 删除对象
     * @param name 键
     * @return true 删除成功,false 不成功
     */
    public Boolean delete(String name) {
        return redissonClient.getBucket(name).delete();
    }

    /*
     * ------------------------------------- Stream 相关操作
     * -------------------------------------------
     */

    /**
     * 获取map集合
     * @param name
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> RMap<K, V> getMap(String name) {
        return redissonClient.getMap(name);
    }

    /**
     * 设置map集合
     * @param name
     * @param data
     * @param time 缓存时间,单位毫秒 -1永久缓存
     * @return
     */
    public void setMapValues(String name, Map data, Long time) {
        RMap map = redissonClient.getMap(name);
        Long dataValidTime = DATA_VALID_TIME;
        if (time != -1) {
            map.expire(dataValidTime, TimeUnit.MILLISECONDS);
        }
        map.putAll(data);
    }

    /**
     * 设置map集合
     * @param name
     * @param data
     * @return
     */
    public void setMapValues(String name, Map data) {
        setMapValues(name, data, DATA_VALID_TIME);
    }

    /**
     * 获取List集合
     * @param name
     * @return
     */
    public <T> RList<T> getList(String name) {
        return redissonClient.getList(name);
    }

    /**
     * 设置List集合
     * @param name
     * @param data
     * @param time 缓存时间,单位毫秒 -1永久缓存
     * @return
     */
    public <T> void setListValues(String name, List<T> data, long time) {
        RList<T> list = redissonClient.getList(name);
        long dataValidTime = time != -1L ? time : DATA_VALID_TIME;
        if (time != -1) {
            list.expire(Duration.ofMillis(dataValidTime));
        }
        list.addAll(data);
    }

    /**
     * 设置List集合
     * @param name
     * @param data
     * @return
     */
    public void setListValues(String name, List<?> data) {
        setListValues(name, data, DATA_VALID_TIME);
    }

    /**
     * 获取set集合
     * @param name
     * @return
     */
    public <T> RSet<T> getSet(String name) {
        return redissonClient.getSet(name);
    }

    /**
     * 设置set集合
     * @param name
     * @param data
     * @param time 缓存时间,单位毫秒 -1永久缓存
     * @return
     */
    public void setSetValues(String name, Set data, Long time) {
        RSet set = redissonClient.getSet(name);
        Long dataValidTime = DATA_VALID_TIME;
        if (time != -1) {
            set.expire(dataValidTime, TimeUnit.MILLISECONDS);
        }
        set.addAll(data);
    }

    /**
     * 设置set集合
     * @param name
     * @param data
     * @return
     */
    public void setSetValues(String name, Set data) {
        setSetValues(name, data, DATA_VALID_TIME);
    }

    /*
     * ------------------------------------- Stream 相关操作
     * -------------------------------------------
     */

    /**
     * 获取输出流
     * @param name
     * @return
     */
    public OutputStream getOutputStream(String name) {
        RBinaryStream binaryStream = redissonClient.getBinaryStream(name);
        return binaryStream.getOutputStream();
    }

    /**
     * 获取输入流
     * @param name
     * @return
     */
    public InputStream getInputStream(String name) {
        RBinaryStream binaryStream = redissonClient.getBinaryStream(name);
        return binaryStream.getInputStream();
    }

    /**
     * 获取输入流
     * @param name
     * @return
     */
    public InputStream getValueStream(String name, OutputStream stream) {
        try {
            RBinaryStream binaryStream = redissonClient.getBinaryStream(name);
            InputStream inputStream = binaryStream.getInputStream();
            byte[] buff = new byte[1024];
            int len;
            while ((len = inputStream.read(buff)) != -1) {
                stream.write(buff, 0, len);
            }
            return binaryStream.getInputStream();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取对象空间
     * @param name
     * @return
     */
    public RBinaryStream getBucketStream(String name) {
        return redissonClient.getBinaryStream(name);
    }

    /**
     * 设置对象的值
     * @param name 键
     * @param value 值
     * @return
     */
    public void setValueStream(String name, InputStream value) {
        try {
            RBinaryStream binaryStream = redissonClient.getBinaryStream(name);
            binaryStream.delete();
            OutputStream outputStream = binaryStream.getOutputStream();
            byte[] buff = new byte[1024];
            int len;
            while ((len = value.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除对象
     * @param name 键
     * @return true 删除成功,false 不成功
     */
    public Boolean delStream(String name) {
        RBinaryStream binaryStream = redissonClient.getBinaryStream(name);
        return binaryStream.delete();
    }

}
