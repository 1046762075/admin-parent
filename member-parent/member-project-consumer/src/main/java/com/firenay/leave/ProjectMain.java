package com.firenay.leave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
 * <p>Title: ProjectMain</p>
 * Description：图片上传在这
 * date：2020/5/19 18:15
 */
// 启用Feign客户端功能
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ProjectMain {
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectMain.class, args);
	}
}
