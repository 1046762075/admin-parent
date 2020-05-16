package com.firenay.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * <p>Title: MyPasswordEncoder</p>
 * Description：
 * date：2020/5/14 19:09
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return pEncode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return Objects.equals(pEncode(rawPassword),encodedPassword);
	}

	/**
	 * 加密
	 */
	private String pEncode(CharSequence rawPassword){
			String algorithm = "MD5";
			String encoded = null;
			try {
				MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
				// 获取输入的字节数组
				byte[] input = ((String) rawPassword).getBytes();
				// 加密
				byte[] digest = messageDigest.digest(input);
				// 将加密后的字节数组转成 16 进制
				encoded = new BigInteger(1, digest).toString(16).toUpperCase();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			return encoded;
		}
}
