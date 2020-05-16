package com.firenay.leave.service.api;

import com.firenay.leave.Role;

import java.util.List;

/**
 * <p>Title: RoleService</p>
 * Description：
 * date：2020/5/10 10:32
 */
public interface RoleService {

	List<Role> getPageInfo(String keyword);

	int saveRole(Role role);

	int updateRole(Role role);

	/**
	 * RoleExample执行批量删除
	 */
	int removeRoles(List<Integer> roles);
	
	/**
	 * 删除角色的时候带走权限
	 */
	int delinnerRoleAuth(List<Integer> roles);

	List<Role> getAssignedRole(Integer adminId);

	List<Role> getUnAssignedRole(Integer adminId);

	/**
	 * 添加角色的时候带上基本权限
	 */
	int saveinnerRoleAuth(Integer roleId);
}
