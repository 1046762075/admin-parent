package com.firenay.cloud.api;

import com.firenay.cloud.entity.Employee;
import com.firenay.cloud.factory.MyFallBackFactory;
import com.firenay.cloud.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>Title: EmployeeRemoteService</p>
 * Description：为 Consumer 提供服务降级
 * date：2020/5/17 17:51
 */
@FeignClient(value = "firenay-provider", fallbackFactory = MyFallBackFactory.class)
public interface EmployeeRemoteService {

	/**
	 * 远程调用的进程
	 */
	@GetMapping("/provider/get/employee/remote")
	Employee getEmployeeRemote();

	@GetMapping("/provider/consumer/search")
	List<Employee> getEmpListRemote(@RequestParam("keyword") String keyword);

	@GetMapping("/provider/get/employee/circuit/breaker")
	ResultEntity<Employee> getEmpWithCircuitBreaker(@RequestParam("signal") String signal);
}
