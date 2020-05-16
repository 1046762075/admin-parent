package com.firenay.leave.service.api;

import com.firenay.leave.Auth;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: AuthService</p>
 * Description：
 * date：2020/5/12 21:44
 */
public interface AuthService {

	List<Auth> getAll();

	List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

	int saveRoleAuth(Map<String, List<Integer>> map);

	List<String> getAssignedAuthNameByAdminId(Integer adminId);
}
