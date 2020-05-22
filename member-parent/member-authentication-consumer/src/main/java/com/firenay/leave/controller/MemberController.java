package com.firenay.leave.controller;

import com.firenay.leave.api.MySQLRemoteService;
import com.firenay.leave.api.RedisRemoteService;
import com.firenay.leave.config.ShortMessageProperties;
import com.firenay.leave.constant.LeaveConstant;
import com.firenay.leave.entity.po.MemberPO;
import com.firenay.leave.entity.vo.MemberLoginVO;
import com.firenay.leave.entity.vo.MemberVO;
import com.firenay.leave.util.LEAVEUtil;
import com.firenay.leave.util.ResultEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * <p>Title: MemberController</p>
 * Description：用户发码、注册、登录
 * date：2020/5/18 20:19
 */
@Controller
public class MemberController {

	@Resource
	private ShortMessageProperties MPS;

	@Resource
	private RedisRemoteService remoteService;

	@Resource
	private MySQLRemoteService mySQLRemoteService;

	@Resource
	private JavaMailSenderImpl mailSender;

	@RequestMapping("/auth/member/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:http://www.fireflynay.top:520/";
	}

	@RequestMapping("auth/member/do/login")
	public String doLogin(@RequestParam("loginAcct") String loginAcct, @RequestParam("userpswd") String userpswd, ModelMap modelMap, HttpSession session){
		// 1.用户没登录上
		ResultEntity<MemberPO> resultEntity = mySQLRemoteService.getMemberPoByLoginAcctRemote(loginAcct);
		if(ResultEntity.FAILED.equals(resultEntity.getResult())){
			modelMap.addAttribute(LeaveConstant.ATTR_NAME_MESSAGE, resultEntity.getMessage());
			return "member-login";
		}
		MemberPO memberPO = resultEntity.getData();
		if(memberPO == null){
			modelMap.addAttribute(LeaveConstant.ATTR_NAME_MESSAGE, LeaveConstant.MESSAGE_LOGIN_FAILED);
			return "member-login";
		}
		// 2.比较密码
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		if(bCryptPasswordEncoder.matches(userpswd, memberPO.getUserpswd())){
			MemberLoginVO loginVO = new MemberLoginVO(memberPO.getId(), memberPO.getUsername(), memberPO.getEmail());
			session.setAttribute(LeaveConstant.ATTR_NAME_LOGIN_MEMBER, loginVO);
			return "redirect:http://www.fireflynay.top:520/auth/member/to/center/page";
		}
		modelMap.addAttribute(LeaveConstant.ATTR_NAME_MESSAGE, LeaveConstant.MESSAGE_LOGIN_FAILED);
		return "member-login";
	}

	@RequestMapping("auth/do/member/register")
	public String register(MemberVO memberVO, ModelMap modelMap){

		// 1.获取手机号
		String phoneNum = memberVO.getPhoneNum();
		if(isPhoneNum(phoneNum) && isEmail(phoneNum)){
			modelMap.addAttribute(LeaveConstant.ATTR_NAME_MESSAGE, LeaveConstant.ATTR_PHONE_INVALID);
			return "member-reg";
		}
		if(null == memberVO.getLoginacct() || "".equals(memberVO.getLoginacct().trim())){
			modelMap.addAttribute(LeaveConstant.ATTR_NAME_MESSAGE, LeaveConstant.ATTR_USER_NAME_INVALID);
			return "member-reg";
		}
		// 2.拼Redis中存储的key
		String key = LeaveConstant.REDIS_CODE_PREFIX + phoneNum;
		// 3.从redis读取key对应的value
		ResultEntity<String> resultEntity = remoteService.getRedisStringValueByKey(key);
		// 4.检查查询操作是否有效
		String result = resultEntity.getResult();
		if(ResultEntity.FAILED.equals(result)){
			modelMap.addAttribute(LeaveConstant.ATTR_NAME_MESSAGE, resultEntity.getMessage());
			return "member-reg";
		}
		// 5.如果从Redis能够查询到value则比较表单验证码和Redis验证码
		String redisCode = resultEntity.getMessage();
		if(redisCode == null){
			modelMap.addAttribute(LeaveConstant.ATTR_NAME_MESSAGE, LeaveConstant.MESSAGE_CODE_NOT_EXISTS);
			return "member-reg";
		}
		if(!Objects.equals(redisCode, memberVO.getCode())){
			modelMap.addAttribute(LeaveConstant.ATTR_NAME_MESSAGE, LeaveConstant.MESSAGE_CODE_INVALID);
			return "member-reg";
		}
		// 6.如果与验证码一致，则从redis删除
		remoteService.removeRedisKeyRemote(key);
		// 7.执行密码加密
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encode = bCryptPasswordEncoder.encode(memberVO.getUserpswd());
		memberVO.setUserpswd(encode);

		// 8.由视图对象转换到数据库对象 准备保存到数据库
		MemberPO memberPO = new MemberPO();
		BeanUtils.copyProperties(memberVO, memberPO);
		ResultEntity<String> saveMemberResultEntity = mySQLRemoteService.saveMember(memberPO);
		if(ResultEntity.FAILED.equals(saveMemberResultEntity.getResult())){
			modelMap.addAttribute(LeaveConstant.ATTR_NAME_MESSAGE, saveMemberResultEntity.getMessage());
			return "member-reg";
		}
		// 9.最后重定向登录页面
		return "redirect:http://www.fireflynay.top:520/auth/member/to/login/page";
	}

	@ResponseBody
	@RequestMapping("auth/member/send/short/message.json")
	public ResultEntity<String> sendMessage(@RequestParam("phoneNum") String phoneNum){
		ResultEntity<String> sendMessageResultEntity = ResultEntity.failed();
		String code = MPS.getCode();
		if("test".equals(MPS.getMode())){
			sendMessageResultEntity = ResultEntity.successWithoutData();
		}else if("email".equals(MPS.getMode())){
			if(isEmail(phoneNum)){
				return ResultEntity.failed(LeaveConstant.ATTR_PHONE_INVALID);
			}
			sendMessageResultEntity = sendEmailMessage(MPS.getEmailAccess(), phoneNum, MPS.getTopic(), MPS.getContent());
			code = sendMessageResultEntity.getMessage();
		}else if("PhoneNum".equals(MPS.getMode())){
			if(isPhoneNum(phoneNum)){
				return ResultEntity.failed(LeaveConstant.ATTR_PHONE_INVALID);
			}
			sendMessageResultEntity = LEAVEUtil.sendShortMessage(MPS.getHost(), MPS.getPath(), MPS.getMethod(), phoneNum, MPS.getAppCode(), MPS.getSign(), MPS.getSkin());
			code = sendMessageResultEntity.getData();
		}
		// 短信发送成功
		if(ResultEntity.SUCCESS.equals(sendMessageResultEntity.getResult())){
			// 拼接手机号当作key
			String key = LeaveConstant.REDIS_CODE_PREFIX + phoneNum;
			ResultEntity<String> saveCodeResultEntity = remoteService.setRedisKeyValueRemoteWithTimeOut(key, code, 10, TimeUnit.MINUTES);
			if(ResultEntity.SUCCESS.equals(saveCodeResultEntity.getResult())) {
				return ResultEntity.successWithoutData();
			}else {
				return saveCodeResultEntity;
			}
		}else{
			// 发送失败返回具体信息
			return sendMessageResultEntity;
		}
	}

	public ResultEntity<String> sendEmailMessage(String FromAddr, String toAddr, String topic, String content){
		MimeMessage message = mailSender.createMimeMessage();
		try {
			// multipart : 开启文件上传
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			helper.setFrom(FromAddr);
			helper.setTo(toAddr);
			helper.setSubject(topic);
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < 6; i++) {
				int random = (int) (Math.random() * 10);
				builder.append(random);
			}
			String code = builder.toString();
			helper.setText("<b style='color:orange'>" + content + code + " </br>有效时间10分钟.</b>",true);
			mailSender.send(message);
			return ResultEntity.successWithoutData(code);
		} catch (MessagingException e) {
			e.printStackTrace();
			return ResultEntity.failed(e.getMessage());
		}
	}

	private boolean isPhoneNum(String AccessNum){
		String PHONE_PATTERN = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17([0,1,6,7,]))|(18[0-2,5-9]))\\d{8}$";
		return !Pattern.compile(PHONE_PATTERN).matcher(AccessNum).matches();
	}

	private boolean isEmail(String AccessNum){
		String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		return !Pattern.compile(EMAIL_PATTERN).matcher(AccessNum).matches();
	}
}
