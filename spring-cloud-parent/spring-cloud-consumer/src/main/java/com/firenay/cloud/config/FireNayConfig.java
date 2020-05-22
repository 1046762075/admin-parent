package com.firenay.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Title: FireNayConfig</p>
 * Description：
 * date：2020/5/17 15:40
 */
@Configuration
public class FireNayConfig {

	/**
	 * @LoadBalanced :让RestTemplate 具备负载均衡的功能 通过调用Ribbon访问Provider
	 */
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
