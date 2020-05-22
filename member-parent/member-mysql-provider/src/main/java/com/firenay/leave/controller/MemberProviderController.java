package com.firenay.leave.controller;

import com.firenay.leave.constant.LeaveConstant;
import com.firenay.leave.entity.po.MemberPO;
import com.firenay.leave.service.api.MemberService;
import com.firenay.leave.util.ResultEntity;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>Title: MemberProviderController</p>
 * Description：会员登录
 * date：2020/5/18 13:37
 */
@RestController
public class MemberProviderController {

	@Resource
	private MemberService memberService;

	@RequestMapping("/save/member/remote")
	public ResultEntity<String> saveMember(@RequestBody MemberPO memberPO){
		try {
			memberService.saveMember(memberPO);
			return ResultEntity.successWithoutData();
		} catch (Exception e) {
			if(e instanceof DuplicateKeyException){
				return ResultEntity.failed(LeaveConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
			}
			e.printStackTrace();
			return ResultEntity.failed(e.getMessage());
		}
	}

	@RequestMapping("/get/get/member/po/by/login/acct/remote")
	public ResultEntity<MemberPO> getMemberPoByLoginAcctRemote(@RequestParam("loginAcct") String loginAcct){
		try {
			MemberPO memberPO = memberService.getMemberPoByLoginAcct(loginAcct);
			return ResultEntity.successWithData(memberPO);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultEntity.failed(e.getMessage());
		}
	}
}
