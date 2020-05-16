package com.firenay.leave.service.impl;

import com.firenay.leave.Role;
import com.firenay.leave.RoleExample;
import com.firenay.leave.mapper.RoleMapper;
import com.firenay.leave.service.api.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: RoleServiceImpl</p>
 * Description：
 * date：2020/5/10 10:32
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleMapper roleMapper;

	@Override
	public List<Role> getPageInfo(String keyword) {
		return roleMapper.selectRoleByKeyword(keyword);
	}

	@Override
	public int saveRole(Role role) {
		return roleMapper.insert(role);
	}

	@Override
	public int updateRole(Role role) {
		return roleMapper.updateByPrimaryKeySelective(role);
	}

	/**
	 * RoleExample执行批量删除
	 */
	@Override
	public int removeRoles(List<Integer> roles) {
		RoleExample roleExample = new RoleExample();
		RoleExample.Criteria criteria = roleExample.createCriteria();
		criteria.andIdIn(roles);
		return roleMapper.deleteByExample(roleExample);
	}

	/**
	 * 自定义mapper
	 */
	@Override
	public List<Role> getAssignedRole(Integer adminId) {
		return roleMapper.selectAssignedRole(adminId);
	}

	@Override
	public List<Role> getUnAssignedRole(Integer adminId) {
		return roleMapper.selectUnAssignedRole(adminId);
	}

	@Override
	public int saveinnerRoleAuth(Integer roleId) {
		// 3是用户查询 6是角色查询
		roleMapper.saveinnerRoleAuth(roleId, 3);
		return roleMapper.saveinnerRoleAuth(roleId, 6);
	}
	
	@Override
	public int delinnerRoleAuth(List<Integer> roles) {
		// 删除所有id锁对应的权限
		int delSUCCESS = 0;
		for (Integer roleId : roles) {
			delSUCCESS += roleMapper.deleteByPrimaryKey(roleId);
		}
		return delSUCCESS;
	}
}
