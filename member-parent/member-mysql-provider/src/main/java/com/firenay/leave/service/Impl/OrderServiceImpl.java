package com.firenay.leave.service.Impl;

import com.firenay.leave.entity.po.AddressPO;
import com.firenay.leave.entity.po.AddressPOExample;
import com.firenay.leave.entity.po.OrderPO;
import com.firenay.leave.entity.po.OrderProjectPO;
import com.firenay.leave.entity.vo.AddressVO;
import com.firenay.leave.entity.vo.OrderProjectVO;
import com.firenay.leave.entity.vo.OrderVO;
import com.firenay.leave.mapper.AddressPOMapper;
import com.firenay.leave.mapper.OrderPOMapper;
import com.firenay.leave.mapper.OrderProjectPOMapper;
import com.firenay.leave.service.api.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: OrderServiceImpl</p>
 * Description：
 * date：2020/5/21 21:34
 */
@Transactional(readOnly = true)
@Service
public class OrderServiceImpl implements OrderService {

	@Resource
	private OrderProjectPOMapper orderProjectPOMapper;

	@Resource
	private OrderPOMapper orderPOMapper;

	@Resource
	public AddressPOMapper addressPOMapper;

	@Override
	public OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId) {
		return orderProjectPOMapper.selectOrderProjectVO(returnId);
	}

	@Override
	public List<AddressVO> getAddressVOList(Integer memberId) {
		AddressPOExample example = new AddressPOExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		List<AddressPO> addressPOS = addressPOMapper.selectByExample(example);
		List<AddressVO> addressVOS = new ArrayList<>();
		for (AddressPO addressPO : addressPOS) {
			AddressVO addressVO = new AddressVO();
			BeanUtils.copyProperties(addressPO, addressVO);
			addressVOS.add(addressVO);
		}
		return addressVOS;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	@Override
	public void saveAddress(AddressVO addressVO) {
		AddressPO addressPO = new AddressPO();

		BeanUtils.copyProperties(addressVO, addressPO);

		addressPOMapper.insert(addressPO);
	}

	/**
	 * 保存用户支付信息
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	@Override
	public void saveOrder(OrderVO orderVO) {
		OrderPO orderPO = new OrderPO();
		BeanUtils.copyProperties(orderVO, orderPO);
		OrderProjectPO orderProjectPO = new OrderProjectPO();
		BeanUtils.copyProperties(orderVO.getOrderProjectVO(), orderProjectPO);
		orderPOMapper.insert(orderPO);
		orderProjectPO.setOrderId(orderPO.getId());
		orderProjectPOMapper.insert(orderProjectPO);
	}
}
