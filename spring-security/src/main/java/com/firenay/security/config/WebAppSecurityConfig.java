package com.firenay.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * <p>Title: WebAppSecurityConfig</p>
 * Description：
 * date：2020/5/13 21:40
 */
@EnableWebSecurity
@Configuration
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private DataSource dataSource;

	/**
	 * 根据这里面的设置进行分配
	 */
	@Resource
	private MyUserDetailsService myUserDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	/**
	 * 自己的加密方式 [不带盐值]
	 */
//	@Resource
//	private MyPasswordEncoder myPasswordEncoder;

	@Resource
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {

		// 在内存完成用户名密码的校验 指定当前用户拥有什么样的角色
//		builder.inMemoryAuthentication()
//				.withUser("firenay")
//				.password("123")
//				.roles("ADMIN", "小学生")
//				.and()
//				.withUser("nay")
//				.password("123")
//				.roles("UPDATE", "初中生");

		// 使用数据库的
		builder.userDetailsService(myUserDetailsService)
				// 进行加密判断
				.passwordEncoder(bCryptPasswordEncoder);
	}

	/**
	 * 注意！！！  大范围的拦截要放在后面 小范围的放在前面  否则会失效
	 */
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		// 将记住我的信息存入
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		// 给请求授权
		security.authorizeRequests()
				// 针对index.jsp进行授权 [permitAll] : 无条件访问
				.antMatchers("/index.jsp").permitAll()
				// /layui目录下面的请求无条件访问
				.antMatchers("/layui/**").permitAll()
				// level1 需要学徒的角色才能访问
				.antMatchers("/level1/**").hasRole("小学生")
				.antMatchers("/level2/**").hasAuthority("初中生")
				// 对请求进行授权
				.and().authorizeRequests()
				// 任意的请求需要登录才能访问
				.anyRequest().authenticated()
				// 指定用自己的页面登录
				.and().formLogin().loginPage("/index.jsp")
				// 指定登录地址[登录表单的地址] 就覆盖 loginPage 方法中设置的默认值
				.loginProcessingUrl("/do/login.html")
				// 设置登录用户名和密码
				.usernameParameter("loginAcct").passwordParameter("userPw")
				// 指定登录成功之后去的页面
				.defaultSuccessUrl("/main.html")
				// 禁用csrf  禁用之后可以发 GET请求进行注销 [否则必须发POST请求进行注销等等]
				// 并且页面form表单还必须包含类似这样一条  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				.and().csrf().disable()
				// 指定退出的请求 退出成功去的页面
				.logout().logoutUrl("/do/logout.html").logoutSuccessUrl("/index.jsp")
				// 访问被拒绝以后前忘的页面  accessDeniedHandler：不仅去no_auth.jsp这个页面还可以带消息过去
				.and().exceptionHandling().accessDeniedPage("/to/no/auth/page.html").accessDeniedHandler(new AccessDeniedHandler() {
			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
				request.setAttribute("message", "抱歉您无法访问这个资源");
				request.getRequestDispatcher("/WEB-INF/views/no_auth.jsp").forward(request, response);
			}
		}).
				// 开启记住我的功能  在表单中 提供名 remember-me 的请求参数
				// <input type="checkbox" name="remember-me" lay-skin="primary" title="记住我">
				and().rememberMe()
				// 将登录信息保存到数据库
				.tokenRepository(tokenRepository);
	}
}
