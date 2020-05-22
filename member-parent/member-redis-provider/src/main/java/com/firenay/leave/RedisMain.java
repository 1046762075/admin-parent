package com.firenay.leave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>Title: RedisMain</p>
 * Description：
 * date：2020/5/18 14:04
 */
@SpringBootApplication
public class RedisMain {

//	http://127.0.0.1:3000/set/redis/key/value/remote?key=test&value=test
//	带超时时间：http://127.0.0.1:3000/set/redis/key/value/remote/with/timeout?key=test&value=test&time=666&timeUnit=SECONDS
//	http://127.0.0.1:3000/get/redis/string/value/by/key?key=test
//	http://127.0.0.1:3000/remove/redis/key/remote?key=test
	public static void main(String[] args) {
		SpringApplication.run(RedisMain.class,args);
	}
}
