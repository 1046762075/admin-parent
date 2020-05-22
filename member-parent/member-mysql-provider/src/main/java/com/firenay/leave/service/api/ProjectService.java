package com.firenay.leave.service.api;

import com.firenay.leave.entity.vo.DetailProjectVO;
import com.firenay.leave.entity.vo.PortalTypeVO;
import com.firenay.leave.entity.vo.ProjectVO;

import java.util.List;

/**
 * <p>Title: ProjectService</p>
 * Description：
 * date：2020/5/19 20:52
 */
public interface ProjectService {
	/**
	 * 发起众筹所有数据的保存业务
	 */
	int saveProject(ProjectVO projectVO, Integer memberId);

	/**
	 * 查询所有已发布众筹信息
	 */
	List<PortalTypeVO> getPortalTypeVO();

	/**
	 * 用户点击项目然后展示的内容
	 */
	DetailProjectVO getDetailProjectVO(Integer projectId);
}
