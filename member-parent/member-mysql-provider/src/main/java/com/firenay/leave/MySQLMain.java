package com.firenay.leave;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>Title: MySQLMain</p>
 * Description：
 * date：2020/5/18 11:15
 */
@MapperScan("com.firenay.leave.mapper")
@SpringBootApplication
public class MySQLMain {

//	http://127.0.0.1:2000/get/get/member/po/by/login/acct/remote?loginAcct=leave
////	获取所有在页面展示的已发起众筹项目
////	http://127.0.0.1:2000/get/portal/type/project/data
////	查询单个众筹项目
////	http://127.0.0.1:2000/get/project/derail/remote/1
	public static void main(String[] args) {
		SpringApplication.run(MySQLMain.class,args);
	}
}
