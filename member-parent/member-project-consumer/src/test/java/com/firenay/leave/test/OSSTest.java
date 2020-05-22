package com.firenay.leave.test;

import com.firenay.leave.util.ResultEntity;
import org.junit.Test;

import java.io.InputStream;

import static com.firenay.leave.util.LEAVEUtil.uploadFileToOss;

/**
 * <p>Title: OSSTest</p>
 * Description：OSS上传测试
 * date：2020/5/19 19:26
 */
public class OSSTest {

	@Test
	public void ossTest(){
		InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("1.jpg");
		ResultEntity<String> resultEntity = uploadFileToOss("http://oss-cn-shenzhen.aliyuncs.com", "LTAI4FyTWirWtoVVivJ3jRYA", "ZYDWqEOByTvOBGQ8zfucRARIfHhhdq", inputStream, "firenay", "http://firenay.oss-cn-shenzhen.aliyuncs.com", "1.jpg");
		System.out.println(resultEntity);
	}
}
