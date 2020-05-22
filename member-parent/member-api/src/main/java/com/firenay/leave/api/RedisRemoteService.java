package com.firenay.leave.api;

import com.firenay.leave.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: RedisRemoteService</p>
 * Description：
 * date：2020/5/18 14:24
 */
@FeignClient("firenay-leave-redis")
public interface RedisRemoteService {

	@RequestMapping("/set/redis/key/value/remote")
	ResultEntity<String> setRedisKeyValueRemote(@RequestParam("key") String key, @RequestParam("value") String value);

	/**
	 * 带超时时间
	 */
	@RequestMapping("/set/redis/key/value/remote/with/timeout")
	ResultEntity<String> setRedisKeyValueRemoteWithTimeOut(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("time") long time, @RequestParam("timeUnit")TimeUnit timeUnit);

	@RequestMapping("/get/redis/string/value/by/key")
	ResultEntity<String> getRedisStringValueByKey(@RequestParam("key") String key);

	@RequestMapping("/remove/redis/key/remote")
	ResultEntity<String> removeRedisKeyRemote(@RequestParam("key") String key);

}
