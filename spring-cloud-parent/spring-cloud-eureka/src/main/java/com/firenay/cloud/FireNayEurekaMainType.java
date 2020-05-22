package com.firenay.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <p>Title: FireNayEurekaMainType</p>
 * Description：
 * date：2020/5/17 15:59
 */
@EnableEurekaServer
@SpringBootApplication
public class FireNayEurekaMainType {
//	http://127.0.0.1:5000/
	public static void main(String[] args) {
		SpringApplication.run(FireNayEurekaMainType.class,args);
	}
}
