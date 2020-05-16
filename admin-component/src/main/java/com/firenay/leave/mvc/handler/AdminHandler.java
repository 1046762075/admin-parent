package com.firenay.leave.mvc.handler;

import com.firenay.leave.constant.LeaveConstant;
import com.firenay.leave.Admin;
import com.firenay.leave.exception.AccessForbiddenException;
import com.firenay.leave.service.api.AdminService;
import com.firenay.leave.util.ResultEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>Title: AdminHandler</p>
 * Description：管理员
 * date：2020/5/8 21:48
 */
@Controller
public class AdminHandler {

	@Resource
	private AdminService adminService;

	private Logger log = LoggerFactory.getLogger(AdminHandler.class);
	
	
	public boolean Accs(String StrId, Integer id) {
		if(!StrId.equals("1") && id == 1) {
			throw new RuntimeException("您无权修改此账号！");
		}
		return true;
	}

	@PreAuthorize("hasAuthority('user:update') and (hasRole('DBA') or hasRole('管理员'))")
	@RequestMapping("admin/updateAdmin.html")
	public String updateAdmin(Admin admin, @RequestParam("pageNum") Integer pageNum, @RequestParam("keyword") String keyword, HttpServletRequest request){
		Accs(request.getSession().getAttribute(LeaveConstant.ATTR_ADMIN_ID).toString(), admin.getId());
		if(adminService.updateAdmin(admin) > 0){
			log.warn("\n" + request.getSession().getAttribute(LeaveConstant.ATTR_ADMIN_ID) + "号用户更改了" + admin.getId() + "号用户的名称为：" + admin.getUserName());
		}
		return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
	}

	/**
	 * 去 admin-edit 页面然后回显
	 */
	@PreAuthorize("hasAuthority('user:get')")
	@RequestMapping("admin/to/edit/page.html")
	public String toEditPage(@RequestParam("adminId") Integer adminId ,ModelMap modelMap){
		Admin admin = adminService.getAdminById(adminId);
		modelMap.addAttribute(LeaveConstant.ATTR_ADMIN_EDIT, admin);
		return "admin-edit";
	}

	/**
	 * 这楼里需要user的保存权限
	 */
	@PreAuthorize("hasAuthority('user:save') and (hasRole('DBA') or hasRole('管理员'))")
	@RequestMapping("admin/save.html")
	public String save(Admin admin, HttpServletRequest request){
		adminService.saveAdmin(admin);
		admin.setUserPswd(null);
		log.info("\n" + request.getSession().getAttribute(LeaveConstant.ATTR_ADMIN_ID) + "号用户添加了" + admin);
		adminService.saveInnerAdminRole(admin.getId());
		return "redirect:/admin/get/page.html?pageNum=" + 0x7fffffff;
	}

	@PreAuthorize("hasAuthority('user:delete') and (hasRole('DBA') or hasRole('管理员'))")
	@RequestMapping("admin/remove/{adminId}/{pageNum}/{keyword}.html")
	public String remove(@PathVariable("adminId") Integer adminId, @PathVariable("pageNum") Integer pageNum, @PathVariable("keyword") String keyword , HttpSession session){
		Accs(session.getAttribute(LeaveConstant.ATTR_ADMIN_ID).toString(), adminId);
		if(session.getAttribute(LeaveConstant.ATTR_ADMIN_ID).equals(adminId)){
			throw new AccessForbiddenException("不能删除自己的账户!");
		}
		if(adminService.remove(adminId) > 0){
			adminService.removeInnerAdminRole(adminId);
			log.warn("\n" + session.getAttribute(LeaveConstant.ATTR_ADMIN_ID) + "号用户删除了" + adminId + "号用户的账户");
		}
		// 转发一旦刷新页面会重复提交表单 浪费性能
//		return "forward:/admin/get/page.html";
		return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
	}

	/**
	 * @param pageNum 页码
	 * @param pageSize 每页显示数量
	 */
	@PreAuthorize("hasAuthority('user:get')")
	@RequestMapping("admin/get/page.html")
	public String getPageInfo(@RequestParam(value = "keyword",defaultValue = "")String keyword, @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum , @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize ,ModelMap modelMap){
		// 开启分页功能
		PageHelper.startPage(pageNum, pageSize);
		List<Admin> admins = adminService.getPageInfo("%" + keyword + "%");
		// 将查询到的admins封装到PageInfo中 连续显示5页
		PageInfo pageInfo = new PageInfo(admins, pageSize);
		modelMap.addAttribute(LeaveConstant.ATTR_NAME_PAGE_INFO, pageInfo);
		return "admin-page";
	}

	@RequestMapping("admin/do/logout.html")
	public String Logout(HttpSession session){
		session.invalidate();
		return "redirect:/admin/to/login.html";
	}

//	@RequestMapping("admin/do/login.html")
//	public String Login(Admin admin, HttpSession session){
//		admin = adminService.getAdminByLoginAcct(admin);
//		session.setAttribute(LeaveConstant.ATTR_NAME_LOGIN_ADMIN, admin);
//		session.setAttribute(LeaveConstant.ATTR_ADMIN_ID, admin.getId());
//		log.info("\n" + admin.getUserName() +"登录成功");
//		// 重定向防止表单重复提交
//		return "redirect:/admin/to/main/page.html";
////		return "admin-main";
//	}
	
	/**
	 * 过滤掉单数   POSTMAN发请求
	 */
	@PreFilter(value = "filterObject%2==0")
	@ResponseBody
	@RequestMapping("admin/test/pre/filter.json")
	public ResultEntity<List<Integer>> saveList(@RequestBody List<Integer> valueList){
		return ResultEntity.successWithData(valueList);
	}
}
