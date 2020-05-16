package com.firenay.leave.mvc.config;

import com.firenay.leave.constant.LeaveConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Title: WebAppSecurityConfig</p>
 * Description：Security配置类
 * date：2020/5/14 20:51
 */
@Configuration
@EnableWebSecurity
// 启用全局方法权限控制功能，并且设置prePostEnabled = true。保证@PreAuthority、@PostAuthority、@PreFilter、@PostFilter生效
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Resource
	private UserDetailsService userDetailsService;
	
	@Resource
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		
		// 内存判断
//		builder.inMemoryAuthentication().withUser("firenay").password("123").roles("ADMIN");
		// 数据库判断
		builder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		
		security
				// 对请求进行授权        		针对登录页进行设置
				.authorizeRequests().antMatchers("/admin/to/login.html").permitAll()
				// 无条件访问
				.antMatchers("/bootstrap/**").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/fonts/**").permitAll()
				.antMatchers("/img/**").permitAll()
				.antMatchers("/jquery/**").permitAll()
				.antMatchers("/layer/**").permitAll()
				.antMatchers("/script/**").permitAll()
				.antMatchers("/ztree/**").permitAll()
				.antMatchers("/index.jsp").permitAll()
				.antMatchers("/send/array/one.html").permitAll()
				.antMatchers("/send/array/two.html").permitAll()
				.antMatchers("/send/array/three.html").permitAll()
				.antMatchers("/send/compose/object.json").permitAll()
				// 访问这个请求需要 DBA 的角色 或者 user:save 权限  参考 MethodSecurityExpressionRoot 可以查看表达式的写法
				.antMatchers("/admin/get/page.html").access("hasRole('DBA') or hasAuthority('user:save')")
				// 除了以上的请求全部都需要认证
				.anyRequest().authenticated()
				// 没有权限跳转的页面
				.and().exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
					
					@Override
					public void handle(HttpServletRequest request, HttpServletResponse response,
							AccessDeniedException accessDeniedException) throws IOException, ServletException {
						request.setAttribute("exception", LeaveConstant.MESSAGE_ACCESS_DENIED);
						request.getRequestDispatcher("/WEB-INF/system-error.jsp").forward(request, response);
					}
				})
				.and().csrf()                            	// 防跨站请求伪造功能
				.disable()                        			// 禁用
				// 开启表单登录的功能 指定登录页面
				.formLogin().loginPage("/admin/to/login.html")
				// 指定处理登录请求的地址
				.loginProcessingUrl("/security/do/login.html")
				// 登录成功之后前往的地址
				.defaultSuccessUrl("/admin/to/main/page.html")
				// 指定表单 username 跟 password
				.usernameParameter("userName").passwordParameter("userPswd")
				// 开启退出功能 指定推出链接 退出成功去的页面
				.and().logout().logoutUrl("/security/do/logout.html").logoutSuccessUrl("/admin/to/login.html");
	}
}
