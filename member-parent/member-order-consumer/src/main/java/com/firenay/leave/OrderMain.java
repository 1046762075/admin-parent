package com.firenay.leave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p>Title: OrderMain</p>
 * Description：订单
 * date：2020/5/21 19:48
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class OrderMain {
	public static void main(String[] args) {
		SpringApplication.run(OrderMain.class, args);
	}
}
