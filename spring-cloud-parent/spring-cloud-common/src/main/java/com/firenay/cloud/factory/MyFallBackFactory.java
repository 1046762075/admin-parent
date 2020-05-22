package com.firenay.cloud.factory;

import com.firenay.cloud.api.EmployeeRemoteService;
import com.firenay.cloud.entity.Employee;
import com.firenay.cloud.util.ResultEntity;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>Title: MyFallBackFactory</p>
 * Description：实现 consumer 这端服务降级的功能
 * date：2020/5/17 20:29
 */
@Component
public class MyFallBackFactory implements FallbackFactory<EmployeeRemoteService> {

	@Override
	public EmployeeRemoteService create(Throwable cause) {
		return new EmployeeRemoteService() {
			@Override
			public Employee getEmployeeRemote() {
				return null;
			}

			@Override
			public List<Employee> getEmpListRemote(String keyword) {
				return null;
			}

			@Override
			public ResultEntity<Employee> getEmpWithCircuitBreaker(String signal) {
				return ResultEntity.failed("服务降级生效" + cause.getMessage());
			}
		};
	}
}
