package com.firenay.leave.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * <p>Title: HelloController</p>
 * Description：
 * date：2020/5/19 10:32
 */
@RestController
public class HelloController {

	@RequestMapping("/test/spring/session")
	public String testSession(HttpSession session){
		session.setAttribute("king","firenay");
		return "数据已存入";
	}
}
