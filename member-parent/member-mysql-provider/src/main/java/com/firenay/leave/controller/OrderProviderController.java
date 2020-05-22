package com.firenay.leave.controller;

import com.firenay.leave.entity.vo.AddressVO;
import com.firenay.leave.entity.vo.OrderProjectVO;
import com.firenay.leave.entity.vo.OrderVO;
import com.firenay.leave.service.api.OrderService;
import com.firenay.leave.util.ResultEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: OrderProviderController</p>
 * Description：
 * date：2020/5/21 21:33
 */
@RestController
public class OrderProviderController {

	@Resource
	private OrderService orderService;

	@RequestMapping("/save/order/remote")
	public ResultEntity<String> saveOrderRemote(@RequestBody OrderVO orderVO){
		try {
			orderService.saveOrder(orderVO);
			return ResultEntity.successWithoutData();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultEntity.failed(e.getMessage());
		}
	}

	@RequestMapping("/save/address/remote")
	public ResultEntity<String> saveAddressRemote(@RequestBody AddressVO addressVO){
		try {
			orderService.saveAddress(addressVO);
			return ResultEntity.successWithoutData();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultEntity.failed(e.getMessage());
		}
	}

	@RequestMapping("/get/address/vo/remote")
	public ResultEntity<List<AddressVO>> getAddressVORemote(Integer memberId){
		try {
			List<AddressVO> addressVOS = orderService.getAddressVOList(memberId);
			return ResultEntity.successWithData(addressVOS);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultEntity.failed(e.getMessage());
		}
	}

	@RequestMapping("/get/order/project/vo/remote")
	public ResultEntity<OrderProjectVO> getOrderProjectVORemote(@RequestParam("projectId") Integer projectId,@RequestParam("returnId") Integer returnId){
		try {
			OrderProjectVO orderProjectVO = orderService.getOrderProjectVO(projectId , returnId);
			return ResultEntity.successWithData(orderProjectVO);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultEntity.failed(e.getMessage());
		}
	}
}
