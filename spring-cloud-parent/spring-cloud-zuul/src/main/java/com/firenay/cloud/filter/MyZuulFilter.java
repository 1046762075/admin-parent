package com.firenay.cloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: MyZuulFilter</p>
 * Description：Zuul 过滤器
 * date：2020/5/17 22:25
 */
@Component
public class MyZuulFilter extends ZuulFilter {

	Logger logger = LoggerFactory.getLogger(MyZuulFilter.class);

	@Override
	public String filterType() {
		// 返回当前过滤器的类型，决定当前过滤器在什么时候执行
		// pre表示在目标微服务前执行
		String filterType = "pre";

		return filterType;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	/**
	 * 判断当前请求要不要过滤
	 * 过滤：继续执行 run 方法  [true]
	 * 不过滤：直接放行			[false]
 	 */
	@Override
	public boolean shouldFilter() {

		RequestContext requestContext = RequestContext.getCurrentContext();

		// 获取Request对象
		HttpServletRequest request = requestContext.getRequest();

		// 判断当前请求参数是否为 signal = text
		String parameter = request.getParameter("signal");
		// 请求参数为 text 就执行 run 方法进行过滤
		return "text".equals(parameter);
	}

	@Override
	public Object run() throws ZuulException {
		logger.info("当前请求要进行过滤，run()方法执行了");
		// 当前实现会忽略这个方法的返回值
		return null;
	}
}
