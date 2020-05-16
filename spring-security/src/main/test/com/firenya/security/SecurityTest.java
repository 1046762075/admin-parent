package com.firenya.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p>Title: SecurityTest</p>
 * Description：盐值加密测试
 * date：2020/5/14 20:09
 */
public class SecurityTest {

	private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	public static void main(String[] args) {
		String str = "123";
		System.out.println(str + "加密后：" + encode(str));

		// 判断是否相等 	$2a$10$rbDAEOiFZ/Mv.b8q3rVSMOYEdza0GcEHmL48ranauOUP5RFjTZCUy
		System.out.println(str + "加密后是否相等? " + matches(str, "$2a$10$rbDAEOiFZ/Mv.b8q3rVSMOYEdza0GcEHmL48ranauOUP5RFjTZCUy"));
	}

	private static String encode(String encode){
		// 创建BCryptPasswordEncoder对象
		return bCryptPasswordEncoder.encode(encode);
	}

	private static boolean matches(CharSequence rawPassword, String encodedPassword){
		return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
	}
}
