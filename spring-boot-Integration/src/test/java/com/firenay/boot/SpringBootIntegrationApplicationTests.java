package com.firenay.boot;

import com.firenay.boot.entity.Emp;
import com.firenay.boot.mapper.EmpMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringBootIntegrationApplicationTests {

	@Resource
	private EmpMapper empMapper;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	private Logger logger = LoggerFactory.getLogger(SpringBootIntegrationApplicationTests.class);

	@Test
	public void testSelectAll() {
		List<Emp> selectAll = empMapper.selectAll();
		for (Emp emp : selectAll) {
			logger.info("\n" + emp.toString());
		}
	}

	@Test
	public void testRedisTemplate() {
		redisTemplate.opsForValue().set("SpringBoot-redis", "FireNay");
	}

	@Test
	public void testStringRedisTemplate() {
		String value = stringRedisTemplate.opsForValue().get("SpringBoot-redis");
		logger.info("\n从redis查询到：" +  value);
	}
}
