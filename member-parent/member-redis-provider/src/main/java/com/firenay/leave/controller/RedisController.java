package com.firenay.leave.controller;

import com.firenay.leave.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: RedisController</p>
 * Description：
 * date：2020/5/18 14:34
 */
@RestController
public class RedisController {

//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@RequestMapping("/set/redis/key/value/remote")
	ResultEntity<String> setRedisKeyValueRemote(@RequestParam("key") String key, @RequestParam("value") String value){
		try {
			ValueOperations<String, String> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			return ResultEntity.successWithoutData();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultEntity.failed();
		}
	}

	/**
	 * 带超时时间
	 */
	@RequestMapping("/set/redis/key/value/remote/with/timeout")
	ResultEntity<String> setRedisKeyValueRemoteWithTimeOut(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("time") long time, @RequestParam("timeUnit")TimeUnit timeUnit){
		try {
			ValueOperations<String, String> operations = redisTemplate.opsForValue();
			// 设置超时时间
			operations.set(key, value, time, timeUnit);
			return ResultEntity.successWithoutData();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultEntity.failed();
		}
	}

	@RequestMapping("/get/redis/string/value/by/key")
	ResultEntity<String> getRedisStringValueByKey(@RequestParam("key") String key){
		try {
			ValueOperations<String, String> operations = redisTemplate.opsForValue();
			String value = operations.get(key);
			return ResultEntity.successWithoutData(value);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultEntity.failed(e.getMessage());
		}
	}

	@RequestMapping("/remove/redis/key/remote")
	ResultEntity<String> removeRedisKeyRemote(@RequestParam("key") String key){
		try {
			redisTemplate.delete(key);
			return ResultEntity.successWithoutData();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultEntity.failed(e.getMessage());
		}
	}
}
