package com.firenay.security.config;

import com.firenay.security.entity.Admin;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: MyUserDetailsService</p>
 * Description：从数据库查询然后进行权限分配
 * date：2020/5/14 16:39
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

	@Resource
	private JdbcTemplate jdbcTemplate;
	/**
	 * 根据表单提交的用户名查询User对象 并装配角色、权限的信息
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		String sql = "SELECT id, loginacct ,userpswd, username, email FROM s_admin WHERE loginacct = ?";
		// 从数据库查询角色
		Admin admin = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Admin.class), username).get(0);
		// 给admin设置角色权限信息
		List<GrantedAuthority> authorities = new ArrayList<>();
		// 添加角色
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		authorities.add(new SimpleGrantedAuthority("ROLE_小学生"));
		// 权限
		authorities.add(new SimpleGrantedAuthority("UPDATE"));
		authorities.add(new SimpleGrantedAuthority("初中生"));
		// 最后封装到 UserDetails 中
		return new User(username, admin.getUserpswd(), authorities);
	}
}
