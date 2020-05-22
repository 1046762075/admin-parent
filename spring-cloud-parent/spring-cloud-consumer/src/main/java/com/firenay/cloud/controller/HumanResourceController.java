package com.firenay.cloud.controller;

import com.firenay.cloud.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: HumanResourceController</p>
 * Description：
 * date：2020/5/17 15:42
 */
@RestController
public class HumanResourceController {

	@Resource
	private RestTemplate restTemplate;

	/**
	 * 通过Ribbon来调用
	 */
//	private String host = "http://127.0.0.1:1000";
	private String host = "http://firenay-provider";

	private String url = "/provider/get/employee/remote";

	@GetMapping("/consumer/get/employee/remote")
	public Employee getEmployeeRemote(){
		return restTemplate.getForObject(host + url, Employee.class);
	}

	@GetMapping("/consumer/search")
	public List<Employee> getEmployeeSearch(@RequestParam("keyword") String keyword){
		return restTemplate.getForObject(host + "/provider/consumer/search?keyword=" + keyword, List.class);
	}
}
