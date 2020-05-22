package com.firenay.leave.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: RedisMainTest</p>
 * Description：
 * date：2020/5/18 14:15
 */
@SpringBootTest
public class RedisMainTest {

	private Logger logger = LoggerFactory.getLogger(RedisMainTest.class);

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void redisTest(){
		ValueOperations<String, String> operations = redisTemplate.opsForValue();
		operations.set("apple","orange");
	}

	@Test
	public void testExSet() {
		ValueOperations<String, String> operations = redisTemplate.opsForValue();
		// 设置超时时间
		operations.set("banana", "yellow", 5000, TimeUnit.SECONDS);
	}

	@Test
	public void testStringRedisTemplate() {
		String value = stringRedisTemplate.opsForValue().get("apple");
		logger.info("\n从redis查询到：" +  value);
	}
}
