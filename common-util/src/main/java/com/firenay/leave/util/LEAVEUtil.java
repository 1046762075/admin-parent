package com.firenay.leave.util;

import com.aliyun.api.gateway.demo.util.HttpUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import com.firenay.leave.constant.LeaveConstant;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>Title: CrowdUtil</p>
 * Description：
 * date：2020/5/8 20:11
 */
public class LEAVEUtil {

	/**
	 * 专门负责上传文件到OSS服务器的工具方法
	 * @param endpoint			阿里云
	 * @param accessKeyId		阿里云
	 * @param accessKeySecret	阿里云
	 * @param inputStream		要上传的流
	 * @param bucketName		阿里云
	 * @param bucketDomain		阿里云
	 * @param originalName  	原始的文件名
	 */
	public static ResultEntity<String> uploadFileToOss(String endpoint, String accessKeyId, String accessKeySecret, InputStream inputStream, String bucketName, String bucketDomain, String originalName) {
		// 创建OSSClient实例。
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

		// 生成上传文件的目录
		String folderName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		// 生成上传文件在OSS服务器上保存时的文件名
		// 使用UUID生成文件主体名称
		String fileMainName = UUID.randomUUID().toString().replace("-", "");

		// 从原始文件名中获取文件扩展名
		String extensionName = originalName.substring(originalName.lastIndexOf("."));

		// 使用目录、文件主体名称、文件扩展名称拼接得到对象名称
		String objectName = folderName + "/" + fileMainName + extensionName;

		try {
			// 调用OSS客户端对象的方法上传文件并获取响应结果数据
			PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, inputStream);

			// 从响应结果中获取具体响应消息
			ResponseMessage responseMessage = putObjectResult.getResponse();

			// 根据响应状态码判断请求是否成功
			if (responseMessage == null) {

				// 拼接访问刚刚上传的文件的路径
				String ossFileAccessPath = bucketDomain + "/" + objectName;

				// 当前方法返回成功
				return ResultEntity.successWithData(ossFileAccessPath);
			} else {
				// 获取响应状态码
				int statusCode = responseMessage.getStatusCode();

				// 如果请求没有成功，获取错误消息
				String errorMessage = responseMessage.getErrorResponseAsString();

				// 当前方法返回失败
				return ResultEntity.failed("当前响应状态码 = " + statusCode + " 错误消息=" + errorMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 当前方法返回失败
			return ResultEntity.failed(e.getMessage());
		} finally {
			if (ossClient != null) {
				// 关闭OSSClient。
				ossClient.shutdown();
			}
		}
	}

	/**
	 * @param host     短信接口调用的URL地址
	 * @param path     具体发送短信功能的地址
	 * @param method   请求方法
	 * @param phoneNum 接收验证码的手机
	 * @param appCode  阿里云云市场里面的AppCode
	 * @param sign     测试使用 1
	 * @param skin     测试使用 1
	 *                 返回结果 ：200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
	 */
	public static ResultEntity<String> sendShortMessage(String host, String path, String method, String phoneNum, String appCode, String sign, String skin) {
		Map<String, String> headers = new HashMap<>();
		// 541707ddc9c8463eb9336c3bfc0624b3
		headers.put("Authorization", "APPCODE " + appCode);

		Map<String, String> querys = new HashMap<>();
		// 生成要发送的验证码
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			int random = (int) (Math.random() * 10);
			builder.append(random);
		}
		String code = builder.toString();
		querys.put("param", code);
		// 收短信的手机
		querys.put("phone", phoneNum);
		// 签名编号
		querys.put("sign", sign);
		// 模板编号
		querys.put("skin", skin);
		try {
			HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
			//System.out.println(response.toString());如不输出json, 请打开这行代码，打印调试头部状态码。
			StatusLine statusLine = response.getStatusLine();
			// 获取状态码
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				return ResultEntity.successWithoutData(code);
			}
			return ResultEntity.failed(statusLine.getReasonPhrase());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultEntity.failed(e.getMessage());
		}
	}

	public static String md5(String source) {
		if (source == null || source.length() == 0) {
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
	 * true：当前请求是Ajax请求
	 * false：当前请求不是Ajax请求
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
