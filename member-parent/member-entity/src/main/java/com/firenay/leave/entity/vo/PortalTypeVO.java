package com.firenay.leave.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>Title: PortalTypeVO</p>
 * Description：页面展示的众筹图片 每面4项
 * date：2020/5/20 13:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortalTypeVO {

	private Integer id;

	private String name;

	private String remark;

	private List<PortalProjectVO> portalProjectVOList;
}
