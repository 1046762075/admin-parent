package com.firenay.leave.mvc.interceptor;

import com.firenay.leave.exception.AccessForbiddenException;
import com.firenay.leave.constant.LeaveConstant;
import com.firenay.leave.Admin;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>Title: LoginInterceptor</p>
 * Description：对需要登录的请求进行拦截
 * date：2020/5/9 10:05
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute(LeaveConstant.ATTR_NAME_LOGIN_ADMIN);
		if(admin == null){
			throw new AccessForbiddenException(LeaveConstant.MESSAGE_ACCESS_FORBIDEN);
		}
		return true;
	}
}
