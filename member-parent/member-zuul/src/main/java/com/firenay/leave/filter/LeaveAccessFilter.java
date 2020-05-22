package com.firenay.leave.filter;

import com.firenay.leave.constant.AccessPassResources;
import com.firenay.leave.constant.LeaveConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * <p>Title: LeaveAccessFilter</p>
 * Description：线程本地化传入request
 * date：2020/5/19 12:05
 */
@Component
public class LeaveAccessFilter extends ZuulFilter {

	@Override
	public String filterType() {
		// 这里返回 'pre' 意思是在目标微服务前执行过滤
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext requestContext = RequestContext.getCurrentContext();

		// 1.通过RequestContext对象获取当前请求对象（框架底层是借助ThreadLocal从当前线程上获取事先绑定的Request对象）
		HttpServletRequest request = requestContext.getRequest();

		String servletPath = request.getServletPath();

		// 2.根据servletPath判断当前请求是否对应可以直接放行的特定功能
		boolean containsResult = AccessPassResources.PASS_RES_SET.contains(servletPath);

		if (containsResult) {
			// 3.如果当前请求是可以直接放行的特定功能请求则返回false放行
			return false;
		}
		// 4.判断当前请求是否为静态资源
		return !AccessPassResources.judgeCurrentServletPathWetherStaticResource(servletPath);
	}

	@Override
	public Object run() throws ZuulException {
		// 1.获取当前请求对象
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();

		// 2.获取当前Session对象判断session中是否有用户信息
		HttpSession session = request.getSession();
		Object loginMember = session.getAttribute(LeaveConstant.ATTR_NAME_LOGIN_MEMBER);

		if(loginMember == null) {

			// 3.从requestContext对象中获取Response对象
			HttpServletResponse response = requestContext.getResponse();

			// 4.将提示消息存入Session域
			session.setAttribute(LeaveConstant.ATTR_NAME_MESSAGE, LeaveConstant.MESSAGE_ACCESS_FORBIDEN);

			// 7.重定向到auth-consumer工程中的登录页面
			try {
				response.sendRedirect("/auth/member/to/login/page");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
