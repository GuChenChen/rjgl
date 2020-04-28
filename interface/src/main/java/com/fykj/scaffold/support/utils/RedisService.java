package com.fykj.scaffold.support.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import utils.ClassUtil;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * redis服务实现
 *
 * @author zhangzhi
 */
@Slf4j
@Component
public class RedisService {

    @Autowired
    private RedisTemplate<Serializable, Object> redisTemplate;

    @Autowired
    private HashOperations<Serializable, Object, Object> hashOperations;

    @Autowired
    private ListOperations<Serializable, Object> listOperations;

    @Autowired
    private ValueOperations<Serializable, Object> valueOperations;

    @Autowired
    private SetOperations<Serializable, Object> setOperations;

    /**
     * 写入缓存
     *
     * @param key   键
     * @param value 值
     * @return 是否成功
     */
    public boolean set(final Serializable key, Object value) {
        return set(key, value, 0);
    }

    /**
     * 写入缓存设置时效时间
     *
     * @param key        键
     * @param value      值
     * @param expireTime 有效时间（秒）
     * @return 是否成功
     */
    public boolean set(final Serializable key, Object value, long expireTime) {
        try {
            valueOperations.set(key, value);
            if (expireTime != 0) {
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("redis set:", e);
        }
        return false;
    }

    /**
     * 批量删除对应的value
     *
     * @param keys 键数组
     */
    public void remove(final Serializable... keys) {
        remove(Arrays.asList(keys));
    }

    /**
     * 批量删除对应的value
     *
     * @param keys 键数组
     */
    public Long remove(Collection<Serializable> keys) {
        return redisTemplate.delete(keys);
    }

    /**
     * 批量删除key
     *
     * @param pattern 模板
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (!CollectionUtils.isEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key 键
     * @return 是否存在
     */
    public boolean exists(final Serializable key) {
        Boolean exists = redisTemplate.hasKey(key);
        return exists != null && exists;
    }

    /**
     * 读取缓存
     *
     * @param key 键
     * @return 对应值
     */
    public <T> T get(final Serializable key) {
        return ClassUtil.cast(valueOperations.get(key));
    }

    /**
     * 哈希 添加
     *
     * @param key     键
     * @param hashKey hash键
     * @param value   对应值
     */
    public boolean hmSet(Serializable key, Object hashKey, Object value) {
        try {
            hashOperations.put(key, hashKey, value);
            return true;
        } catch (Exception e) {
            log.error("hmSet:", e);
        }
        return false;
    }

    /**
     * 哈希 添加
     *
     * @param key     键
     * @param hashKey hash键
     * @param value   对应值
     */
    public boolean hmSet(Serializable key, Object hashKey, Object value, long expireTime) {
        boolean result = hmSet(key, hashKey, value);
        if (result && expireTime != 0) {
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
        return result;
    }

    /**
     * 批量哈希添加-带过期时间
     *
     * @param key        键
     * @param map        hashMap
     * @param expireTime 过期时间（秒）
     */
    public void hmSetBatch(Serializable key, Map<? extends Serializable, ? extends Object> map, long expireTime) {
        hashOperations.putAll(key, map);
        if (expireTime != 0) {
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
    }


    /**
     * 哈希获取数据
     *
     * @param key     键
     * @param hashKey hash键
     * @return 对应值
     */
    @SuppressWarnings("unchecked")
    public <T> T hmGet(Serializable key, Object hashKey) {
        return (T) hashOperations.get(key, hashKey);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> hmGetValues(Serializable key) {
        return (List<T>) hashOperations.values(key);
    }

    @SuppressWarnings("unchecked")
    public <K,V> Map<K,V> hmGetEntries(Serializable key) {
        return (Map<K,V>) hashOperations.entries(key);
    }

    /**
     * 删除hash表中的值
     *
     * @param key      键 不能为null
     * @param hashKeys 哈希键 可以使多个 不能为null
     */
    public void hmDel(Serializable key, Object... hashKeys) {
        hashOperations.delete(key, hashKeys);
    }


    /**
     * 列表添加
     *
     * @param key   键 不能为null
     * @param value 项 可以使多个 不能为null
     */
    public void listPush(Serializable key, Object value) {
        listOperations.rightPush(key, value);
    }

    /**
     * 列表获取
     *
     * @param key   key
     * @param start 开始索引
     * @param end   结束索引
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> listRange(Serializable key, long start, long end) {
        return (List<T>) listOperations.range(key, start, end);
    }

    /**
     * 集合添加
     *
     * @param key   键
     * @param value 值
     */
    public void add(Serializable key, Object value) {
        setOperations.add(key, value);
    }

    /**
     * 集合获取
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> Set<T> members(Serializable key) {
        return (Set<T>) setOperations.members(key);
    }

    /**
     * 有序集合添加
     *
     * @param key   键
     * @param value 值
     * @param score the score.
     */
    public void zAdd(Serializable key, Object value, double score) {
        ZSetOperations<Serializable, Object> zSet = redisTemplate.opsForZSet();
        zSet.add(key, value, score);
    }

    /**
     * 有序集合获取
     *
     * @param key 键
     * @param min 开始索引
     * @param max 最大索引
     * @return 结果集
     */
    @SuppressWarnings("unchecked")
    public <T> Set<T> rangeByScore(Serializable key, double min, double max) {
        ZSetOperations<Serializable, Object> zSet = redisTemplate.opsForZSet();
        return (Set<T>) zSet.rangeByScore(key, min, max);
    }

    /**
     * redis原子自增
     *
     * @param key 键
     * @return 结果
     */
    public int increment(String key) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();
        return increment.intValue();
    }

    /**
     * 得到原子自增的数字(不增加)
     *
     * @param key 键
     * @return 结果
     */
    public int getIncrement(String key) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.get();
        return increment.intValue();
    }

    /**
     * 设置原子自增的数字
     *
     * @param key 键
     */
    public void setIncrement(String key, int num) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        entityIdCounter.getAndSet(num);
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return 是否成功
     */
    public boolean expire(Serializable key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("expire:", e);
        }
        return false;
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public Long getExpire(Serializable key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

}
