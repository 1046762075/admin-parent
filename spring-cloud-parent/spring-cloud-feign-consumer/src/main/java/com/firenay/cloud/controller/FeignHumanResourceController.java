package com.firenay.cloud.controller;

import com.firenay.cloud.api.EmployeeRemoteService;
import com.firenay.cloud.entity.Employee;
import com.firenay.cloud.util.ResultEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: FeignHumanResourceController</p>
 * Description：通过Feign来调用
 * date：2020/5/17 18:07
 */
@RestController
public class FeignHumanResourceController {

	@Resource
	private EmployeeRemoteService employeeRemoteService;

	@GetMapping("/feign/consumer/test/fallback")
	public ResultEntity<Employee> getFallBack(@RequestParam("signal") String signal) {
		return employeeRemoteService.getEmpWithCircuitBreaker(signal);
	}

	@GetMapping("/feign/consumer/get/employee")
	public Employee getEmployeeRemote(){
		return employeeRemoteService.getEmployeeRemote();
	}

	@GetMapping("/provider/consumer/search")
	public List<Employee> getEmpListRemote(@RequestParam("keyword") String keyword){
		return employeeRemoteService.getEmpListRemote(keyword);
	}
}
