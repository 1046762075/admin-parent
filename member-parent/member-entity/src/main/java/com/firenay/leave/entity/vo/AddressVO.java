package com.firenay.leave.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * <p>Title: AddressVO</p>
 * Description：
 * date：2020/5/21 21:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
    private String receiveName;

    private String phoneNum;

    private String address;

    private Integer memberId;

}
