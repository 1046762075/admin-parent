package com.firenay.leave.mvc.config;

import com.firenay.leave.constant.LeaveConstant;
import com.firenay.leave.exception.LoginAcctAlreadyInUserException;
import com.firenay.leave.exception.LoginFailedException;
import com.firenay.leave.util.CrowdUtil;
import com.firenay.leave.util.ResultEntity;
import com.google.gson.Gson;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Title: LeaveExceptionResolver</p>
 * Description：基于注解的异常处理器类
 * date：2020/5/8 18:26
 */
@ControllerAdvice
public class LeaveExceptionResolver {

	@ExceptionHandler(value = LoginFailedException.class)
	public ModelAndView LoginFailedException(
			LoginFailedException exception,
			HttpServletRequest request,
			HttpServletResponse response
	) throws IOException {
		String viewName = "admin-login";
		return commonResolve(viewName, exception, request, response);
	}
	
	@ExceptionHandler(value = LoginAcctAlreadyInUserException.class)
	public ModelAndView LoginAcctAlreadyInUserException(
			LoginAcctAlreadyInUserException exception,
			HttpServletRequest request,
			HttpServletResponse response
	) throws IOException {
		String viewName = "admin-add";
		return commonResolve(viewName, exception, request, response);
	}

	@ExceptionHandler(value = Exception.class)
	public ModelAndView UserUpdateOrResolveMathOrNotLoggedOrAssignException(
			Exception exception,
			HttpServletRequest request,
			HttpServletResponse response
	) throws IOException {
		String viewName = "system-error";
		return commonResolve(viewName, exception, request, response);
	}

	/**
	 * @ExceptionHandler 将一个具体的异常类型和一个方法关联起来
	 * @param viewName 异常处理完成后要去的页面
	 * @param exception 实际捕获到的异常类型
	 */
	private ModelAndView commonResolve(String viewName, Exception exception, HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 1.如果是Ajax请求
		if (CrowdUtil.judgeRequestType(request)) {
			// 2.创建ResultEntity对象
			ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());

			// 3.创建Gson对象
			Gson gson = new Gson();

			// 4.将ResultEntity对象转换为JSON字符串作为响应体返回给浏览器
			response.getWriter().write(gson.toJson(resultEntity));

			// 5.由于上面已经通过原生的 response 对象返回了响应，所以不提供ModelAndView对象
			return null;
		}

		// 6.如果不是Ajax请求则创建ModelAndView对象
		ModelAndView modelAndView = new ModelAndView();

		// 7.将Exception对象存入模型
		modelAndView.addObject(LeaveConstant.ATTR_NAME_EXCEPTION, exception.toString().split(":")[1]);

		// 8.设置对应的视图名称
		modelAndView.setViewName(viewName);

		// 9.返回modelAndView对象
		return modelAndView;
	}
}
