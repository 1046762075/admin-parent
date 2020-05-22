package com.firenay.leave.mvc.handler;

import com.firenay.leave.Auth;
import com.firenay.leave.Role;
import com.firenay.leave.constant.LeaveConstant;
import com.firenay.leave.service.api.AuthService;
import com.firenay.leave.service.api.AdminService;
import com.firenay.leave.service.api.RoleService;
import com.firenay.leave.util.ResultEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: AssignHandler</p>
 * Description：
 * date：2020/5/12 16:35
 */
@Controller
public class AssignHandler {

	@Resource
	private AdminService adminService;

	@Resource
	private RoleService roleService;

	@Resource
	private AuthService authService;

	/**
	 * 权限保存
	 */
	@PreAuthorize("hasRole('DBA')")
	@ResponseBody
	@RequestMapping("assign/do/role/assign/auth.json")
	public ResultEntity<List<Integer>> saveRoleAuthRelationship(@RequestBody Map<String,List<Integer>> map){
		if(authService.saveRoleAuth(map) != 0){
			return ResultEntity.successWithoutData("权限修改成功");
		}
		return ResultEntity.failed("请检查角色与权限的关系! ");
	}

	/**
	 * 获取权限
	 */
	@ResponseBody
	@RequestMapping("assign/get/assigned/auth/id/by/role/id.json")
	public ResultEntity<List<Integer>> getAssignedAuthIdByRole(@RequestParam("roleId") Integer roleId){
		List<Integer> authIds = authService.getAssignedAuthIdByRoleId(roleId);
		return ResultEntity.successWithData(authIds);
	}

	@ResponseBody
	@RequestMapping("assign/get/all/auth.json")
	public ResultEntity<List<Auth>> getAllAuth(){
		List<Auth> auths = authService.getAll();
		return ResultEntity.successWithData(auths, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss")) + "  " + LeaveConstant.MESSAGE_FIRENAY);
	}

	/**
	 * @param adminId 当前操作用户的id
	 * @param pageNum 继承当前页面的页码
	 * @param keyword 继承当前页面查询的关键字
	 * @param roleIds 所有需要修改的角色
	 */
	@PreAuthorize("hasRole('DBA') or hasRole('管理员')")
	@RequestMapping("assign/do/role/assign.html")
	public String saveAdminRelationship(@RequestParam("adminId") Integer adminId, @RequestParam("pageNum") Integer pageNum, @RequestParam("keyword") String keyword, @RequestParam(value = "roleIds", required = false) List<Integer> roleIds){
		System.out.println(roleIds);
		adminService.saveAdminRoleRelationship(adminId, roleIds);
		return "redirect:/assign/to/assign/role/page.html?adminId=" + adminId + "&pageNum=" + pageNum + "&keyword=" + keyword;
	}

	/**
	 * 已分配、未分配权限的查询
	 */
	@RequestMapping("assign/to/assign/role/page.html")
	public String toAssignRolePage(@RequestParam("adminId") Integer adminId, ModelMap modelMap){
		// 1.查询已分配的橘色
		List<Role> assignedRoles = roleService.getAssignedRole(adminId);

		// 2.查询未分配的角色
		List<Role> unAssignedRoles = roleService.getUnAssignedRole(adminId);

		modelMap.addAttribute("assignedRoles", assignedRoles);
		modelMap.addAttribute("unAssignedRoles", unAssignedRoles);
		return "assign-role";
	}
}
