package com.firenay.leave.service.api;

import com.firenay.leave.Admin;

import java.util.List;


public interface AdminService {

	int saveAdmin(Admin admin);

	List<Admin> getAll();
	
	/**
	 * 时候添加权限
	 */
	int saveInnerAdminRole(Integer adminId);

	/**
	 * 登录的方法
	 */
	Admin getAdminByLoginAcct(Admin admin);

	Admin getAdminByLoginAcct(String username);

	/**
	 * 字段、页码、每页显示数量
	 */
	List<Admin> getPageInfo(String keyword);

	/**
	 * 删除
	 */
	int remove(Integer adminId);

	Admin getAdminById(Integer adminId);

	/**
	 * 有选择的更新
	 */
	int updateAdmin(Admin admin);

	/**
	 * 删除权限
	 */
	int saveAdminRoleRelationship(Integer adminId, List<Integer> roleIds);

	/**
	 * 删除用户的时候吧权限也删了
	 */
	int removeInnerAdminRole(Integer adminId);
}
