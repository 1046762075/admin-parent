package com.firenay.leave.mvc.handler;

import com.firenay.leave.Role;
import com.firenay.leave.constant.LeaveConstant;
import com.firenay.leave.service.api.RoleService;
import com.firenay.leave.util.ResultEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>Title: RoleHandler</p>
 * Description：
 * date：2020/5/10 10:35
 */
@RestController
public class RoleHandler {

	@Resource
	private RoleService roleService;

	private Logger log = LoggerFactory.getLogger(RoleHandler.class);

	private String role;

	@PreAuthorize("hasRole('DBA') or hasAuthority('role:delete')")
	@RequestMapping("role/remove/role/by/id/array.json")
	public ResultEntity<String> removeRoles(@RequestBody List<Integer> roles){
		if(roleService.removeRoles(roles) > 0){
			// 删除这些所对应的权限
			int success = roleService.delinnerRoleAuth(roles);
			role = roles.stream().map(role -> role.toString()).reduce((r1, r2) -> r1 + "、" + r2).get();
			log.warn("\n删除 [" + role + "] 号角色成功\t共删除" + success + "项权限");
		}
		return ResultEntity.successWithoutData("删除 [" + role + "] 号角色成功");
	}

	@PreAuthorize("hasRole('DBA') or hasAuthority('role:update')")
	@RequestMapping("role/update.json")
	public ResultEntity<String> updateRole(Role role){
		if(roleService.updateRole(role) > 0){
			log.info("\n更新成功" + role);
		}
		return ResultEntity.successWithoutData(role.getName() + "更新成功");
	}

	@PreAuthorize("hasRole('DBA') or hasAuthority('role:add')")
	@RequestMapping("role/save.json")
	public ResultEntity<String> saveRole(Role role){
		if(roleService.saveRole(role) > 0){
			roleService.saveinnerRoleAuth(role.getId());
			log.info("\n保存成功" + role);
		}
		return ResultEntity.successWithoutData(role.getName() + "保存成功");
	}

	/**
	 * @param pageNum 页码
	 * @param pageSize 每页显示数量
	 */
	@PreAuthorize("hasRole('DBA') or hasAuthority('role:get')")
	@RequestMapping("role/get/page/info.json")
	public ResultEntity<PageInfo<Role>> getPageInfo(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize, @RequestParam(value = "keyword",defaultValue = "")String keyword){
		PageHelper.startPage(pageNum, pageSize);
		List<Role> roles = roleService.getPageInfo(keyword);
		// 将查询到的admins封装到PageInfo中 连续显示5页
		PageInfo pageInfo = new PageInfo(roles, pageSize);
		// 如果上面处理的过程中抛出异常 将由 LeaveExceptionResolver.class 来处理
		return ResultEntity.successWithData(pageInfo, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss")) + "  " + LeaveConstant.MESSAGE_FIRENAY);
	}
}
