package com.firenay.leave.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * <p>Title: HelloController</p>
 * Description：
 * date：2020/5/19 10:33
 */
@RestController
public class HelloController {

	@RequestMapping("/test/spring/session/retrieve")
	public String testSession(HttpSession session){
		String king = (String) session.getAttribute("king");
		return "去出的数据：" + king;
	}
}
