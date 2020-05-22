package com.firenay.leave.controller;

import com.firenay.leave.api.MySQLRemoteService;
import com.firenay.leave.constant.LeaveConstant;
import com.firenay.leave.entity.vo.AddressVO;
import com.firenay.leave.entity.vo.MemberLoginVO;
import com.firenay.leave.entity.vo.OrderProjectVO;
import com.firenay.leave.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>Title: OrderController</p>
 * Description：
 * date：2020/5/21 21:08
 */
@Controller
public class OrderController {

	private Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Resource
	private MySQLRemoteService mySQLRemoteService;

	@RequestMapping("save/address")
	public String saveAddress(AddressVO addressVO, HttpSession session){
		// 1.执行地址信息的保存
		ResultEntity<String> resultEntity = mySQLRemoteService.saveAddressRemote(addressVO);
		logger.info("地址保存处理结果：" + resultEntity.getResult() + "\t[来自com.firenay.leave.controller.OrderController]");
		// 2.获取session中的 orderProjectVO 再获取用户的id然后重定向到订单页面
		OrderProjectVO orderProjectVO = (OrderProjectVO) session.getAttribute("orderProjectVO");
		Integer returnCount = orderProjectVO.getReturnCount();
		return "redirect:http://www.fireflynay.top:520/order/confirm/order/" + returnCount;
	}

	@RequestMapping("confirm/return/info/{projectId}/{returnId}")
	public String showReturnConfirmInfo(@PathVariable("projectId") Integer projectId , @PathVariable("returnId") Integer returnId, HttpSession session){

		ResultEntity<OrderProjectVO> resultEntity = mySQLRemoteService.getOrderProjectVORemote(projectId, returnId);
		if(ResultEntity.SUCCESS.equals(resultEntity.getResult())){
			OrderProjectVO orderProjectVO = resultEntity.getData();
			session.setAttribute("orderProjectVO", orderProjectVO);
		}
		return "confirm_return";
	}

	/**
	 * 查询用户的收获地址数据进行下一步支付
	 */
	@RequestMapping("/confirm/order/{returnCount}")
	public String showConfirmOrderInfo(@PathVariable("returnCount") Integer returnCount, HttpSession session){

		// 1.把接收到的回报数量合并到session
		OrderProjectVO orderProjectVO = (OrderProjectVO) session.getAttribute("orderProjectVO");
		orderProjectVO.setReturnCount(returnCount);
		// 刷新redis中的数据
		session.setAttribute("orderProjectVO", orderProjectVO);

		// 2.获取当前已登录的用户id
		MemberLoginVO memberLoginVO = (MemberLoginVO) session.getAttribute(LeaveConstant.ATTR_NAME_LOGIN_MEMBER);
		Integer memberId = memberLoginVO.getId();
		// 3.查询目前收货地址的数据
		ResultEntity<List<AddressVO>> resultEntity = mySQLRemoteService.getAddressVORemote(memberId);
		if(ResultEntity.SUCCESS.equals(resultEntity.getResult())){
			List<AddressVO> list = resultEntity.getData();
			session.setAttribute("addressVOList",list);
		}
		return "confirm_order";
	}
}
