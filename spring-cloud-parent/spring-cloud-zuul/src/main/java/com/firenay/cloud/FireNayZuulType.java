package com.firenay.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * <p>Title: FireNayZuulType</p>
 * Description：开启 zuul 网关		[还可以开启让所有的服务只能通过 zuul 别名访问]
 * date：2020/5/17 21:43
 */
@EnableZuulProxy
@SpringBootApplication
public class FireNayZuulType {

//				http://localhost:9000/firenay-feign-consumer/feign/consumer/test/fallback?signal=text
//	别名访问：	http://localhost:9000/zuul-emp/feign/consumer/test/fallback?signal=text
//	加上前缀：	http://localhost:9000/maomi/zuul-emp/feign/consumer/test/fallback?signal=text
	public static void main(String[] args) {
		SpringApplication.run(FireNayZuulType.class,args);
	}
}
