package indi.study.system.common.utils;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Component
public class RedisClient {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Resource
    private RedissonClient redissonClient;

    /**
     * setex
     * @param key  key
     * @param value  value
     * @param time 过期时间
     */
    public void setex(String key,Object value,long time){
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * set
     * String类型的set,无过期时间
     * @param key key
     * @param value value
     */
    public void set(String key, Object value){
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 批量设置key和value
     * @param map key和value的集合
     */
    public void mset(Map<String,Object> map){
        redisTemplate.opsForValue().multiSet(map);
    }

    /**
     * 如果key不存在，则设置
     * @param key  key
     * @param value value
     * @return 返回是否成功
     */
    public Boolean setnx(String key,Object value){
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 如果key不存在，则设置并设置时间
     * @param key key
     * @param value value(uuid)
     * @time time time(毫秒数)
     * @return 返回是否成功
     */
    public Boolean setnx(String key, Object value, long time) { return  redisTemplate.opsForValue().setIfAbsent(key, value, time, TimeUnit.MILLISECONDS);}

    /**
     * 执行Lua脚本
     * @param javaScript javaScript
     * @param keys keys
     * @param value value
     * */
    public Object exec(DefaultRedisScript javaScript, List keys, Object... value) {return redisTemplate.execute(javaScript, keys, value);}

    /**
     * 批量插入key，如果key不存在的话
     * @param map key和value的集合
     * @return 是否成功
     */
    public Boolean msetnx(Map<String,Object> map){
        return redisTemplate.opsForValue().multiSetIfAbsent(map);
    }

    /**
     * String类型的get
     * @param key key
     * @return 返回value对应的对象
     */
    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除对应key
     * @param key key
     * @return 返回是否删除成功
     */
    public Boolean del(String key){
        return redisTemplate.delete(key);
    }

    /**
     * 批量删除key
     * @param keys key的集合
     * @return 返回删除成功的个数
     */
    public Long del(List<String> keys){
        return redisTemplate.delete(keys);
    }

    /**
     * 给某个key设置过期时间
     * @param key key
     * @param time 过期时间
     * @return 返回是否设置成功
     */
    public Boolean expire(String key, long time){
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 返回某个key的过期时间
     * @param key key
     * @return 返回key剩余的过期时间
     */
    public Long ttl(String key){
        return redisTemplate.getExpire(key);
    }

    /**
     * 返回是否存在该key
     * @param key key
     * @return 是否存在该key
     */
    public Boolean exists(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 给key的值加上delta值
     * @param key key
     * @param delta 参数
     * @return 返回key+delta的值
     */
    public Long incrby(String key, long delta){
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 给key的值减去delta
     * @param key key
     * @param delta 参数
     * @return 返回key - delta的值
     */
    public Long decrby(String key, long delta){
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    //hash类型

    /**
     * set hash类型
     * @param key key
     * @param hashKey  hashKey
     * @param value value
     */
    public void hset(String key,String hashKey, Object value){
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * set hash类型,并设置过期时间
     * @param key  key
     * @param hashKey hashKey
     * @param value value
     * @param time 过期时间
     * @return 返回是否成功
     */
    public Boolean hset(String key, String hashKey,Object value, long time){
        hset(key, hashKey, value);
        return expire(key, time);
    }

    /**
     * 批量设置hash
     * @param key key
     * @param map hashKey和value的集合
     * @param time 过期时间
     * @return 是否成功
     */
    public Boolean hmset(String key, Map<String,Object> map, long time){
        redisTemplate.opsForHash().putAll(key, map);
        return expire(key, time);
    }

    /**
     * 获取hash类型的值
     * @param key key
     * @param hashKey hashKey
     * @return 返回对应的value
     */
    public Object hget(String key, String hashKey){
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取key下所有的hash值以及hashKey
     * @param key key
     * @return 返回数据
     */
    public Map<Object,Object> hgetall(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 批量删除
     * @param key  key
     * @param hashKey hashKey数组集合
     */
    public void hdel(String key, Object... hashKey){
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    /**
     * 判断是否存在hashKey
     * @param key  key
     * @param hashKey hashKey
     * @return 是否存在
     */
    public Boolean hexists(String key, String hashKey){
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 获取redis分布式锁
     * @param key 锁的key
     * @return RedisLock
     * */
    public RLock getLock(String key) {
        return redissonClient.getLock(key);
    }

    /**
     * 管道
     * @param consumer
     * @param <T>
     * @return
     * */
    public <T> List<T> pipelined(Consumer<RedisOperations<String, Object>> consumer) {
        return (List<T>) redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                consumer.accept((RedisOperations<String, Object>) operations);
                return null;
            }
        });
    }
}
