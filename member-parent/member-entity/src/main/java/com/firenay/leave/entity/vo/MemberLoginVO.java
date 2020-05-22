package com.firenay.leave.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>Title: MemberLoginVO</p>
 * Description：用户登录成功之后放入session
 * date：2020/5/18 22:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String username;

	private String email;
}
