package com.firenay.leave.service.impl;

import com.firenay.leave.Auth;
import com.firenay.leave.AuthExample;
import com.firenay.leave.mapper.AuthMapper;
import com.firenay.leave.service.api.AuthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: AuthServiceImpl</p>
 * Description：
 * date：2020/5/12 21:44
 */
@Service
public class AuthServiceImpl implements AuthService {

	@Resource
	private AuthMapper authMapper;

	@Override
	public List<Auth> getAll() {
		return authMapper.selectByExample(new AuthExample());
	}

	@Override
	public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {
		return authMapper.selectAssignedAuthIdByRoleId(roleId);
	}

	@Override
	public int saveRoleAuth(Map<String, List<Integer>> map) {
		// 我们当初存储的时候就是 把当前用户的id存在 roleId 中
		Integer roleId = map.get("roleId").get(0);
		// 删除以前的权限
		authMapper.deleteOldRelationship(roleId);
		// 获取需要修改后的权限进行保存 role-page.jsp 中 213行
		List<Integer> authIds = map.get("authIds");
		if(null != authIds && authIds.size() > 0){
			return authMapper.insertNewRelationship(roleId, authIds);
		}
		return 1;
	}

	@Override
	public List<String> getAssignedAuthNameByAdminId(Integer adminId) {
		return authMapper.selectAssignedAuthNameByAdminId(adminId);
	}
}
