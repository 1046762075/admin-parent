package com.firenay.leave.service.Impl;

import com.firenay.leave.service.api.MemberService;
import com.firenay.leave.entity.po.MemberPO;
import com.firenay.leave.entity.po.MemberPOExample;
import com.firenay.leave.mapper.MemberPOMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: MemberServiceImpl</p>
 * Description：加上事务
 * date：2020/5/18 13:41
 */

/**
 * 针对查询查询操作设置只读 增删改还需要另外写
 */
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {

	@Resource
	private MemberPOMapper memberPOMapper;

	@Override
	public MemberPO getMemberPoByLoginAcct(String loginAcct) {
		MemberPOExample memberPOExample = new MemberPOExample();
		MemberPOExample.Criteria criteria = memberPOExample.createCriteria();
		// 查询登录名为 loginAcct 的对象
		criteria.andLoginacctEqualTo(loginAcct);
		// 可能会查不到 就会抛空指针异常
		List<MemberPO> list = memberPOMapper.selectByExample(memberPOExample);
		return list.size() == 0? null : list.get(0);
	}

	/**
	 * 设置事务的传播性
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	@Override
	public void saveMember(MemberPO memberPO) {
		memberPOMapper.insertSelective(memberPO);
	}
}
