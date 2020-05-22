package com.firenay.leave.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * <p>Title: DetailProjectVO</p>
 * Description：
 * date：2020/5/20 17:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailProjectVO {

	private Integer projectId;

	private String projectName;

	private String projectDesc;

	private Integer followerCount;

	private Integer status;

	private Integer day;

	private String statusText;

	private Integer money;

	private Integer supportMoney;

	private Integer percentage;

	private String deployDate;

	private Integer lastDay;

	private Integer supporterCount;

	private String headerPicturePath;

	private List<String> detailPicturePathList;

	private List<DetailReturnVO> detailReturnVOList;
}
