package com.firenay.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

/**
 * <p>Title: FireNayProvidersMainType</p>
 * Description：
 * date：2020/5/17 15:25
 */
/**
 * 熔断服务
 */
@EnableCircuitBreaker
@SpringBootApplication
public class FireNayProvidersMainType {
	//	http://127.0.0.1:2000/provider/get/employee/remote
	//	http://127.0.0.1:2000/provider/consumer/search?keyword=520
// 	服务熔断  http://127.0.0.1:2000/provider/get/employee/circuit/breaker?signal=quick-bang
//	服务延迟  http://127.0.0.1:2000/provider/get/employee/circuit/breaker?signal=quick-s
//	正常访问  http://127.0.0.1:2000/provider/get/employee/circuit/breaker?signal=text
	public static void main(String[] args) {
		SpringApplication.run(FireNayProvidersMainType.class,args);
	}
}
