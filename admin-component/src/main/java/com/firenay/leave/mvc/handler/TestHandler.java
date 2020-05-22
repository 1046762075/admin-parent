package com.firenay.leave.mvc.handler;

import com.firenay.leave.Admin;
import com.firenay.leave.test.ParamData;
import com.firenay.leave.test.Student;
import com.firenay.leave.service.api.AdminService;
import com.firenay.leave.util.LEAVEUtil;
import com.firenay.leave.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class TestHandler {

	@Resource
	private AdminService adminService;

	private Logger logger = LoggerFactory.getLogger(TestHandler.class);

	@ResponseBody
	@RequestMapping("/send/compose/object.json")
	public ResultEntity<Student> testReceiveComposeObject(@RequestBody Student student, HttpServletRequest request) {
//		System.out.println(10 / 0);
		boolean flag = LEAVEUtil.judgeRequestType(request);

		logger.info("\n是否为JSON数据：" + flag + "\n" + student.toString());

		// 将“查询”到的Student对象封装到ResultEntity中返回
		ResultEntity<Student> resultEntity = ResultEntity.successWithData(student,"加油！！！");
		return resultEntity;
	}

	@ResponseBody
	@RequestMapping("/send/array/three.html")
	public String testReceiveArrayThree(@RequestBody List<Integer> array) {

		for (Integer number : array) {
			logger.info("number = " + number);
		}
		return "success";
	}

	@ResponseBody
	@RequestMapping("/send/array/two.html")
	public String testReceiveArrayTwo(ParamData paramData) {
		List<Integer> array = paramData.getArray();
		for (Integer number : array) {
			System.out.println("number = " + number);
		}
		return "success";
	}

	@ResponseBody
	@RequestMapping("/send/array/one.html")
	public String testAjax(@RequestParam("array[]") List<Integer> array) {
		for (Integer number : array) {
			System.out.println("number = " + number);
		}
		return "success";
	}

	@RequestMapping("/testSSM.html")
	public String testSSM(ModelMap modelMap, HttpServletRequest request, HttpSession session) {
//		if(session.getAttribute(LeaveConstant.ATTR_NAME_LOGIN_ADMIN) == null){
//			throw new NotloggedException(LeaveConstant.MESSAGE_NOT_LOGGED);
//		}
		boolean flag = LEAVEUtil.judgeRequestType(request);

		logger.info("\n是否为JSON请求 ：" + flag);

		List<Admin> admins = adminService.getAll();
		for (Admin admin : admins) {
			admin.setUserPswd("<font color='red'>密码不可见</font>");
		}
		modelMap.addAttribute("admins", admins);
//		System.out.println(10 / 0);
		return "success";
	}
}
