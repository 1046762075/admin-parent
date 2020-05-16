package com.firenay.leave.test;

import com.firenay.leave.util.CrowdUtil;
import org.junit.Test;

/**
 * <p>Title: StringTest</p>
 * Description：
 * date：2020/5/8 21:29
 */
public class StringTest {

	@Test
	public void testMd5(){
		String source = "123";
		String md5 = CrowdUtil.md5(source);
		System.out.println(md5);
	}
}
