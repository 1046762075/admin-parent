package com.firenay.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p>Title: FireNayFeignConsumerMainType</p>
 * Description：把 FireNayProviderMainType 关掉实现服务降级
 * date：2020/5/17 18:05
 */
@EnableFeignClients
@SpringBootApplication
public class FireNayFeignConsumerMainType {

//				http://127.0.0.1:7000/feign/consumer/get/employee
//				http://127.0.0.1:7000/provider/consumer/search?keyword=520
//	服务降级	http://127.0.0.1:7000/feign/consumer/test/fallback?signal=quick-bang
//	延时降级	http://127.0.0.1:7000/feign/consumer/test/fallback?signal=quick-s
//	正常访问	http://127.0.0.1:7000/feign/consumer/test/fallback?signal=text
	public static void main(String[] args) {
		SpringApplication.run(FireNayFeignConsumerMainType.class,args);
	}
}
