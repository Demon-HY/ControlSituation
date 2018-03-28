package com.control.situation.utils.conversion;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * Created by yhe on 2018/3/12 0012.
 */
public class MapUtils {

	/**
	 * 对象转字典
	 */
	public static Map<String, Object> objectToMap(Object obj){
		return (Map<String, Object>) JSON.toJSON(obj);
	}

	/**
	 * 字典转对象
	 */
	public static <T> T mapToObject(Map<String, Object> map, Class<T> T) {
		return JSON.parseObject(JSON.toJSONString(map), T);
	}

}
