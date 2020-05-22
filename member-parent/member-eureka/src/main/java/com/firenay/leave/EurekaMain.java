package com.firenay.leave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <p>Title: EurekaMain</p>
 * Description：
 * date：2020/5/18 10:38
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaMain {

	public static void main(String[] args) {
		SpringApplication.run(EurekaMain.class,args);
	}
}
