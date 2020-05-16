package com.firenay.leave.test;

import com.firenay.leave.Admin;
import com.firenay.leave.RoleExample;
import com.firenay.leave.mapper.AdminMapper;
import com.firenay.leave.mapper.RoleMapper;
import com.firenay.leave.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>Title: CrowdTest</p>
 * Description：
 * date：2020/5/7 19:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class LeaveTest {

	@Resource
	private DataSource dataSource;

	@Resource
	private AdminMapper adminMapper;

	@Resource
	private AdminService adminService;

	@Resource
	private RoleMapper roleMapper;

	@Test
	public void saveRole(){
//		String roleName;
//		for(int i = 0;i < 288;i++){
//			roleName = getString().toString();
//			Role role = new Role(null,roleName);
//			if(roleMapper.insert(role) > 0){
//				System.out.println("保存成功");
//			}
//		}
		roleMapper.selectByExample(new RoleExample()).forEach(System.out::println);
	}

	@Test
	public void testTx(){
//		String loginName;
//		for(int i = 0;i < 288;i++){
//			loginName = getString().toString();
//			Admin admin = new Admin(null,loginName,"202CB962AC59075B964B07152D234B70",loginName,loginName + "@qq.com",null);
//			if(adminService.saveAdmin(admin) > 0){
//				System.out.println("保存成功");
//			}
//		}
		List<Admin> admins = adminService.getAll();
		admins.forEach(System.out::println);
	}

	@Test
	public void testInsertAdmin(){
//		if(adminMapper.insert(new Admin(null,"firenay","123456","lsl","firenay@qq.com",null)) > 0){
//			System.out.println("保存成功");
//		}
		Admin admin = adminMapper.selectByPrimaryKey(1);
		System.out.println(admin);
	}

	@Test
	public void testConnection(){
		try {
			Connection conn = dataSource.getConnection();
			if(conn != null){
				System.out.println("获取连接成功" + conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static volatile StringBuilder array = getArr();
	/**
	 * @Title: getString
	 * @Description: 获得一个随机字符串 小写 + 数字 + 大写
	 * @return StringBuilder
	 */
	@SuppressWarnings("unused")
	private StringBuilder getString() {
		StringBuilder result = new StringBuilder();
		for(int i = 0;i < 8;i++) {
			result.append(array.charAt((int)(Math.random()*62)));
		}
		return result;
	}

	private static StringBuilder getArr() {
		StringBuilder array = new StringBuilder();
		for (int i = 65; i < 91; i++) {
			array.append((char)i);
		}
		for(int i = 48;i < 58;i++) {
			array.append((char)i);
		}
		for(int i = 97;i < 123;i++) {
			array.append((char)i);
		}
		return array;
	}
}
