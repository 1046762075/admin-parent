package com.firenay.leave.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>Title: LeaveWebMvcConfig</p>
 * Description：注解视图映射
 * date：2020/5/18 19:51
 */
@Configuration
public class LeaveWebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		// 									浏览器访问的地址					目标视图名称 拼接前后缀
		registry.addViewController("/auth/member/to/reg/page").setViewName("member-reg");
		registry.addViewController("/auth/member/to/login/page").setViewName("member-login");
		registry.addViewController("/auth/member/to/center/page").setViewName("member-center");
		registry.addViewController("/member/my/crowd").setViewName("member-crowd");
	}
}
