package com.control.situation.api.impl;

import com.control.situation.api.RedisApi;
import com.control.situation.utils.classes.SerializeUtils;
import com.control.situation.utils.conversion.JsonUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service  
public class RedisApiImpl implements RedisApi,Serializable {
  
    @Autowired  
    private RedisTemplate<String, ?> redisTemplate;
      
    @Override  
    public boolean set(final String key, final String value) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            connection.set(serializer.serialize(key), serializer.serialize(value));
            return true;
        });
    }

    @Override
    public boolean set(final String key, final String value, long expire) {
        set(key, value);
        return expire(key, expire);
    }

    @Override
    public String get(final String key){
        try {
            return redisTemplate.execute((RedisCallback<String>) connection -> {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value =  connection.get(serializer.serialize(key));
                return serializer.deserialize(value);
            });
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Object getBean(final String key){
        try {
            return redisTemplate.execute((RedisConnection connection) -> {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value =  connection.get(serializer.serialize(key));
                return SerializeUtils.unserialize(value);
            });
        } catch (Exception e) {
            return null;
        }
    }
  
    @Override  
    public boolean expire(final String key, long expire) {  
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);  
    }  
  
    @Override  
    public <T> boolean setList(String key, List<T> list) {  
        String value = JsonUtil.toJson(list);
        return set(key,value);  
    }  
  
    @Override  
    public <T> List<T> getList(String key,Class<T> clz) {  
        String json = get(key);  
        if(json!=null){
            return JsonUtil.toList(json, clz);
        }  
        return null;  
    }

    @Override
    public boolean setBean(String key, Object obj, long expire) {
        String value = JsonUtil.toJson(obj);
        set(key,value);
        return expire(key, expire);
    }

    @Override
    public <T> T getBean(String key, Class<T> clz) {
        String json = get(key);
        if(json!=null){
            return JsonUtil.toBean(json, clz);
        }
        return null;
    }

    @Override  
    public long lpush(final String key, Object obj) {  
        final String value = JsonUtil.toJson(obj);
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            return connection.lPush(serializer.serialize(key), serializer.serialize(value));
        });
    }  
  
    @Override  
    public long rpush(final String key, Object obj) {  
        final String value = JsonUtil.toJson(obj);
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            return (long) connection.rPush(serializer.serialize(key), serializer.serialize(value));
        });
    }  
  
    @Override  
    public String lpop(final String key) {
        return redisTemplate.execute((RedisCallback<String>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            byte[] res =  connection.lPop(serializer.serialize(key));
            return serializer.deserialize(res);
        });
    }
    
   public Long incr(String key, long growthLength) {
       return  redisTemplate.opsForValue().increment(key, growthLength);
   }

    @Override
    public boolean del(String key) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            connection.del(serializer.serialize(key));
            return true;
        });
    }
}
