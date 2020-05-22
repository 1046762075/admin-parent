package com.firenay.leave.test;

import com.aliyun.api.gateway.demo.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: AuthMain</p>
 * Description：
 * date：2020/5/18 15:31
 */
@SpringBootTest
public class AuthMainTest {

	private Logger logger = LoggerFactory.getLogger(AuthMainTest.class);

	@Test
	public void sendMessage(){
		// 短信接口调用的URL地址
		String host = "https://feginesms.market.alicloudapi.com";
		String path = "/codeNotice";
		String method = "GET";
		// 阿里云控制台云市场里面找
		String appcode = "541707ddc9c8463eb9336c3bfc0624b3";
		Map<String, String> headers = new HashMap<>();
		//最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);

		Map<String, String> querys = new HashMap<>();
		// 要发送的验证码
		querys.put("param", "5201314");
		// 收短信的手机
		querys.put("phone", "18173516309");
		// 签名编号
		querys.put("sign", "1");
		// 模板编号
		querys.put("skin", "1");
		try {
			HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
			//System.out.println(response.toString());如不输出json, 请打开这行代码，打印调试头部状态码。
			//状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
			StatusLine statusLine = response.getStatusLine();
			// 获取状态码
			int statusCode = statusLine.getStatusCode();
			String reasonPhrase = statusLine.getReasonPhrase();
			logger.info("\ncode = " + statusCode + "\treasonPhrase = " + reasonPhrase + "\n响应体: " + EntityUtils.toString(response.getEntity()));
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
