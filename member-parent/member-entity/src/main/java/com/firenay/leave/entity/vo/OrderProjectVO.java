package com.firenay.leave.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * <p>Title: OrderProjectVO</p>
 * Description：
 * date：2020/5/21 21:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProjectVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String projectName;

    private String launchName;

    private String returnContent;

    private Integer returnCount;

    private Integer supportPrice;

    private Integer freight;

    private Integer orderId;
    
    private Integer signalPurchase;
    
    private Integer purchase;
}
