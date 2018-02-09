package com.control.situation.api;

import java.util.List;


/** 
 *  
 * @author vic 
 * @desc redis service 
 */  
public interface RedisApi {
      
    boolean set(String key, String value);

    String get(String key);

    /**
     * 设置 kye 的存活时间，单位秒
     */
    boolean expire(String key, long expire);

    <T> boolean setList(String key, List<T> list);

    <T> List<T> getList(String key, Class<T> clz);

    long lpush(String key, Object obj);

    long rpush(String key, Object obj);
      
    String lpop(String key);

    boolean set(final String key, final String value, long expire);
      
    
    Long incr(String key, long growthLength);

    boolean del(String key);
}  
