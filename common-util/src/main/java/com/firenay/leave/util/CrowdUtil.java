package com.firenay.leave.util;

import com.firenay.leave.constant.LeaveConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Title: CrowdUtil</p>
 * Description：
 * date：2020/5/8 20:11
 */
public class CrowdUtil {

	public static String md5(String source){
		if(source  == null || source.length() == 0){
			throw new RuntimeException(LeaveConstant.MESSAGE_STRING_INVALIDATE);
		}
		String algorithm = "md5";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			byte[] bytes = source.getBytes();
			// 加密
			byte[] output = messageDigest.digest(bytes);
			int bigNum = 1;
			BigInteger bigInteger = new BigInteger(bigNum, output);
			int radix = 16;
			// 将加密之后的数组转换成字符串
			return bigInteger.toString(radix).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 判断当前请求是否为Ajax请求
	 * 		true：当前请求是Ajax请求
	 * 		false：当前请求不是Ajax请求
	 */
	public static boolean judgeRequestType(HttpServletRequest request) {
		
		// 1.获取请求消息头
		String acceptHeader = request.getHeader("Accept");
		String xRequestHeader = request.getHeader("X-Requested-With");
		
		// 2.判断
		return (acceptHeader != null && acceptHeader.contains("application/json"))
				||
				(xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));
	}
	
}
