package com.firenay.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * <p>Title: AdminController</p>
 * Description：
 * date：2020/5/13 20:34
 */
@Controller
public class AdminController {
	
	@GetMapping("/main.html")
	public String main(){
		return "main";
	}
	
	@RequestMapping("/to/no/auth/page.html")
	public String toNoAuthPage() {
		return "no_auth";
	}

}
