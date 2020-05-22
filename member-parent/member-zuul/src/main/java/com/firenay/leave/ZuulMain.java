package com.firenay.leave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * <p>Title: ZuulMain</p>
 * Description：
 * date：2020/5/18 16:38
 */
@EnableZuulProxy
@SpringBootApplication
public class ZuulMain {

//	http://127.0.0.1:520/
	public static void main(String[] args) {
		SpringApplication.run(ZuulMain.class,args);
	}
}
