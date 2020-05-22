package com.firenay.leave.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Title: PortalProjectVO</p>
 * Description：页面展示
 * date：2020/5/20 13:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortalProjectVO {

	private Integer projectId;

	private String projectName;

	private String headerPicturePath;

	private Integer money;

	private String deployDate;

	private Integer percentage;

	private Integer supporter;
}
