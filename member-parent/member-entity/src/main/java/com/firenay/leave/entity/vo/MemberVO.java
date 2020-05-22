package com.firenay.leave.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Title: MemberVO</p>
 * Description：
 * date：2020/5/18 22:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {

	private String loginacct;

	private String userpswd;

	private String username;

	private String email;

	private String phoneNum;

	private String code;


}
