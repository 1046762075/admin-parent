package com.firenay.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <p>Title: FireNayConsumerMainType</p>
 * Description：
 * date：2020/5/17 15:30
 */
@EnableEurekaClient
@SpringBootApplication
public class FireNayConsumerMainType {

	//	http://127.0.0.1:4000/consumer/get/employee/remote
	//	http://127.0.0.1:4000/consumer/search?keyword=520
	public static void main(String[] args) {
		SpringApplication.run(FireNayConsumerMainType.class,args);
	}
}
