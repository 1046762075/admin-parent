package com.firenay.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * <p>Title: FireNayDashboard</p>
 * Description：启用仪表盘监控功能
 * date：2020/5/17 21:16
 */
@EnableHystrixDashboard
@SpringBootApplication
public class FireNayDashboard {
//		http://localhost:1000/actuator/hystrix.stream
//		http://localhost:2000/actuator/hystrix.stream
	public static void main(String[] args) {
		SpringApplication.run(FireNayDashboard.class,args);
	}
}
