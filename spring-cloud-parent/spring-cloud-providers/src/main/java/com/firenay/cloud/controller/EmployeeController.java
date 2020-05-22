package com.firenay.cloud.controller;

import com.firenay.cloud.entity.Employee;
import com.firenay.cloud.util.ResultEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: EmployeeController</p>
 * Description：
 * date：2020/5/17 15:27
 */
@RestController
public class EmployeeController {

	@Value("${server.port}")
	private Integer port;

	private Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@GetMapping("/provider/get/employee/remote")
	public Employee getEmployeeRemote(){
		return new Employee(520, "fireNay 当前端口：" + port, 520.0);
	}

	/**
	 * 指定出问题备份的方法
	 */
	@HystrixCommand(fallbackMethod = "getEmpWithCircuitBreakerUp")
	@GetMapping("/provider/get/employee/circuit/breaker")
	public ResultEntity<Employee> getEmpWithCircuitBreaker(@RequestParam("signal") String signal) throws InterruptedException {
		if("quick-bang".equals(signal)){
			throw new RuntimeException("服务熔断.");
		}
		if("quick-s".equals(signal)){
			Thread.sleep(999);
		}
		return ResultEntity.successWithData(new Employee(520, "fly", 284.0));
	}

	public ResultEntity<Employee> getEmpWithCircuitBreakerUp(@RequestParam("signal") String signal){
		return ResultEntity.failed("方法出现问题, 执行断路器" + signal);
	}

	@GetMapping("/provider/consumer/search")
	public List<Employee> getEmpListRemote(@RequestParam("keyword") String keyword){
		ArrayList<Employee> emps = new ArrayList<>();
		emps.add(new Employee(1,"mq keyword：" + keyword,100.0));
		emps.add(new Employee(2,"nay keyword：" + keyword,200.0));
		emps.add(new Employee(3,"fly keyword：" + keyword,300.0));
		logger.info("\n" + emps.toString());
		return emps;
	}
}
