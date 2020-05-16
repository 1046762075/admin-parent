package com.firenay.leave.mvc.config;

import com.firenay.leave.Admin;
import com.firenay.leave.Role;
import com.firenay.leave.service.api.AdminService;
import com.firenay.leave.service.api.AuthService;
import com.firenay.leave.service.api.RoleService;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：admin-component
 * 类名称：LeaveUserDetailsService 
 * 类描述：用来存储权限与角色
 * 创建时间：2020年5月15日 下午4:54:57
 * 修改时间：2020年5月15日 下午4:54:57
 */
@Component
public class LeaveUserDetailsService implements UserDetailsService {
	
	@Resource
	private AdminService adminService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private AuthService authService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// 1.根据账号名称查询Admin对象
		Admin admin = adminService.getAdminByLoginAcct(username);
		
		// 2.获取adminId
		Integer adminId = admin.getId();
		
		// 3.根据adminId查询角色信息
		List<Role> assignedRoleList = roleService.getAssignedRole(adminId);
		
		// 4.根据adminId查询权限信息
		List<String> authNameList = authService.getAssignedAuthNameByAdminId(adminId);
		
		// 5.创建集合对象用来存储GrantedAuthority
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		// 6.遍历assignedRoleList存入角色信息
		for (Role role : assignedRoleList) {
			
			// 注意：不要忘了加前缀！
			String roleName = "ROLE_" + role.getName();
			
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
			
			authorities.add(simpleGrantedAuthority);
		}
		
		// 7.遍历authNameList存入权限信息
		for (String authName : authNameList) {
			
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authName);
			
			authorities.add(simpleGrantedAuthority);
		}
		// 8.封装SecurityAdmin对象
		SecurityAdmin securityAdmin = new SecurityAdmin(admin, authorities);
		
		return securityAdmin;
	}
}
