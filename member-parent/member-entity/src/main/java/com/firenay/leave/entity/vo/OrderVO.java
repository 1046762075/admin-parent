package com.firenay.leave.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * <p>Title: OrderVO</p>
 * Description：用于收集页面提交支付
 * date：2020/5/22 10:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

//    主键
	private Integer id;
	
//    订单号
	private String orderNum;
	
//    支付宝流水单号
	private String payOrderNum;
	
//    订单金额
	private Double orderAmount;
	
//    是否开发票
	private Integer invoice;
	
//    发票抬头
	private String invoiceTitle;
	
//    备注
	private String orderRemark;
	
	private String addressId;
	
	private OrderProjectVO orderProjectVO;
}
