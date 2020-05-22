package com.firenay.leave.service.impl;

import com.firenay.leave.AdminExample;
import com.firenay.leave.constant.LeaveConstant;
import com.firenay.leave.exception.LoginAcctAlreadyInUserException;
import com.firenay.leave.exception.LoginAcctAlreadyInUserUpdateException;
import com.firenay.leave.exception.LoginFailedException;
import com.firenay.leave.mapper.AdminMapper;
import com.firenay.leave.Admin;
import com.firenay.leave.service.api.AdminService;
import com.firenay.leave.util.LEAVEUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Resource
	private AdminMapper adminMapper;

	@Resource
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Resource
	private HttpServletRequest request;

	private Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

	/**
	 * 有选择的更新
	 */
	@Override
	public int updateAdmin(Admin admin) {
		int flag = 0;
		try {
			flag = adminMapper.updateByPrimaryKeySelective(admin);
		} catch (Exception e) {
			log.warn("\n异常全类名：" + e.getClass().getName());
			if(e instanceof DuplicateKeyException){
				throw new LoginAcctAlreadyInUserUpdateException(LeaveConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
			}
		}
		return flag;
	}

	/**
	 * 根据情况进行清除权限
	 */
	@Override
	public int saveAdminRoleRelationship(Integer adminId, List<Integer> roleIds) {
		// 先删除所有权限
		int flag = adminMapper.deleteRelationship(adminId);
		// 不为空并且有权限角色才保存
		if(null != roleIds && roleIds.size() > 0){
			return adminMapper.saveRelationship(adminId, roleIds);
		}
		return flag;
	}

	@Override
	public int saveAdmin(Admin admin) {
		int flag = 0;
		admin.setUserPswd(bCryptPasswordEncoder.encode(admin.getUserPswd()));
		admin.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		try {
			flag = adminMapper.insert(admin);
		} catch (Exception e) {
			log.warn("\n异常全类名：" + e.getClass().getName());
			if(e instanceof DuplicateKeyException){
				throw new LoginAcctAlreadyInUserException(LeaveConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
			}
		}
		return flag;
	}

	@Override
	public List<Admin> getAll() {
		return adminMapper.selectByExample(new AdminExample());
	}

	@Override
	public Admin getAdminByLoginAcct(Admin admin) {
		AdminExample adminExample = new AdminExample();
		AdminExample.Criteria criteria = adminExample.createCriteria();
		// 查 UserName
		criteria.andLoginAcctEqualTo(admin.getUserName());
		List<Admin> admins = adminMapper.selectByExample(adminExample);
		if(admins == null || admins.size() == 0 || admins.get(0) == null){
			throw new LoginFailedException(LeaveConstant.MESSAGE_LOGIN_FAILED);
		}
		if(admins.size() > 1){
			throw new RuntimeException(LeaveConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
		}
		Admin administrator = admins.get(0);
		if(!Objects.equals(administrator.getUserPswd(), LEAVEUtil.md5(admin.getUserPswd()))){
			throw new LoginFailedException(LeaveConstant.MESSAGE_LOGIN_FAILED);
		}
		admin.setId(administrator.getId());
		return admin;
	}

	@Override
	public Admin getAdminByLoginAcct(String username) {
		AdminExample example = new AdminExample();
		Admin admin = new Admin();
		AdminExample.Criteria criteria = example.createCriteria();
		criteria.andLoginAcctEqualTo(username);
		List<Admin> admins = adminMapper.selectByExample(example);
		if(admins.size() == 0) {
			throw new LoginFailedException("账号或密码错误！");
		}
		System.out.println(admins.size());
		if(admins.size() > 0) {
			admin = admins.get(0);
			request.getSession().setAttribute(LeaveConstant.ATTR_ADMIN_ID, admin.getId());
		}
		return admin;
	}

	@Override
	public List<Admin> getPageInfo(String keyword) {
		return adminMapper.selectAdminByKeyword(keyword);
	}

	@Override
	public int remove(Integer adminId) {
		return adminMapper.deleteByPrimaryKey(adminId);
	}

	@Override
	public Admin getAdminById(Integer adminId) {
		return adminMapper.selectByPrimaryKey(adminId);
	}

	@Override
	public int saveInnerAdminRole(Integer adminId) {
		return adminMapper.saveInnerAdminRole(adminId);
	}

	@Override
	public int removeInnerAdminRole(Integer adminId) {
		return adminMapper.removeInnerAdminRole(adminId);
	}
}
