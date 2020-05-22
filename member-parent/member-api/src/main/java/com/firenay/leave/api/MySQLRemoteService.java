package com.firenay.leave.api;

import com.firenay.leave.entity.po.MemberPO;
import com.firenay.leave.entity.vo.AddressVO;
import com.firenay.leave.entity.vo.DetailProjectVO;
import com.firenay.leave.entity.vo.OrderProjectVO;
import com.firenay.leave.entity.vo.OrderVO;
import com.firenay.leave.entity.vo.PortalTypeVO;
import com.firenay.leave.entity.vo.ProjectVO;
import com.firenay.leave.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>Title: MySQLRemoteService</p>
 * Description：实体类要传数据一定要加 @RequestBody 单个参数要加上 @RequestParam
 * date：2020/5/18 13:14
 */
@FeignClient("firenay-leave-mysql")
public interface MySQLRemoteService {

	@RequestMapping("/get/get/member/po/by/login/acct/remote")
	ResultEntity<MemberPO> getMemberPoByLoginAcctRemote(@RequestParam("loginAcct") String loginAcct);

	/**
	 * 远程向数据库保存用户信息
	 */
	@RequestMapping("/save/member/remote")
	ResultEntity<String> saveMember(@RequestBody MemberPO memberPO);

	/**
	 * 保存众筹项目发起的所有表单中的数据到数据库
	 */
	@RequestMapping("/save/project/vo/remote")
	ResultEntity<String> saveProjectVORemote(@RequestBody ProjectVO projectVO, @RequestParam("memberId") Integer memberId);

	/**
	 * 查询所有正在众筹的项目
	 */
	@RequestMapping("/get/portal/type/project/data")
	ResultEntity<List<PortalTypeVO>> getPortalTypeProjectDataRemote();

	/**
	 * 当用户点击正在众筹的项目时
	 */
	@RequestMapping("/get/project/derail/remote/{projectId}")
	ResultEntity<DetailProjectVO> getDetailProjectVORemote(@PathVariable("projectId") Integer projectId);

	@RequestMapping("/get/order/project/vo/remote")
	ResultEntity<OrderProjectVO> getOrderProjectVORemote(@RequestParam("projectId") Integer projectId,@RequestParam("returnId") Integer returnId);

	@RequestMapping("/get/address/vo/remote")
	ResultEntity<List<AddressVO>> getAddressVORemote(@RequestParam("memberId") Integer memberId);

	@RequestMapping("/save/address/remote")
	ResultEntity<String> saveAddressRemote(@RequestBody AddressVO addressVO);

	@RequestMapping("/save/order/remote")
	ResultEntity<String> saveOrderRemote(@RequestBody OrderVO orderVO);
}
