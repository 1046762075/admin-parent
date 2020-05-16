package com.firenay.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * <p>Title: GongFuController</p>
 * Description：
 * date：2020/5/13 20:34
 */
@Controller
public class GongFuController {
	
	@GetMapping("/level1/{path}")
	public String level1Page(@PathVariable("path")String path){
		return "/level1/"+path;
	}
	
	@GetMapping("/level2/{path}")
	public String level2Page(@PathVariable("path")String path){
		return "/level2/"+path;
	}
	
	@GetMapping("/level3/{path}")
	public String level3Page(@PathVariable("path")String path){
		return "/level3/"+path;
	}

}
