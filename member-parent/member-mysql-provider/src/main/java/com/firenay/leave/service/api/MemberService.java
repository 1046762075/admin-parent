package com.firenay.leave.service.api;

import com.firenay.leave.entity.po.MemberPO;

/**
 * <p>Title: MemberService</p>
 * Description：
 * date：2020/5/18 13:40
 */
public interface MemberService {

	MemberPO getMemberPoByLoginAcct(String loginAcct);

	void saveMember(MemberPO memberPO);
}
