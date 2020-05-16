package com.firenay.spring;

import com.firenay.spring.entity.Employee;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>Title: SpringTest</p>
 * Description：
 * date：2020/5/13 20:08
 */
public class SpringTest {

	@Test
	public void springTest(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyAnnotationConfiguration.class);
		Employee emp = applicationContext.getBean(Employee.class);
		System.out.println(emp);
	}
}
