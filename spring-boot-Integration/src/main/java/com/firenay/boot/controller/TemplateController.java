package com.firenay.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * <p>Title: TemplateController</p>
 * Description：
 * date：2020/5/17 13:05
 */
@Controller
public class TemplateController {

	@Resource
	private ServletContext servletContext;

	@GetMapping("/test/thymeleaf")
	public String testThymeleaf(ModelMap modelMap, HttpSession session){

		modelMap.addAttribute("modelMapVal","modelMap中的数据");

		session.setAttribute("sessionVal","session中的数据");

		servletContext.setAttribute("AppContextVal","AppContext中的数据");

		modelMap.addAttribute("list", Arrays.asList(5, 2, 0, 1, 3, 1, 4));
		return "index";
	}
}
