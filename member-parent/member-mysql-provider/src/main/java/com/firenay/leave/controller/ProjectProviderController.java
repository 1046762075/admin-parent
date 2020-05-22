package com.firenay.leave.controller;

import com.firenay.leave.constant.LeaveConstant;
import com.firenay.leave.entity.vo.DetailProjectVO;
import com.firenay.leave.entity.vo.PortalTypeVO;
import com.firenay.leave.entity.vo.ProjectVO;
import com.firenay.leave.service.api.ProjectService;
import com.firenay.leave.util.ResultEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>Title: ProjectProviderController</p>
 * Description：发起众筹提交表单
 * date：2020/5/19 20:52
 */
@RestController
public class ProjectProviderController {

	@Resource
	private ProjectService projectService;

	/**
	 * 在 portal 中 326行 <a th:href="@{/portal/show/project/detail} + ${project.projectId}" th:text="${project.projectName}">众筹项目名称</a>
	 *  所以取路径上的值
	 */
	@RequestMapping("get/project/derail/remote/{projectId}")
	public ResultEntity<DetailProjectVO> getDetailProjectVORemote(@PathVariable("projectId") Integer projectId){
		try {
			DetailProjectVO detailProjectVO = projectService.getDetailProjectVO(projectId);
			return ResultEntity.successWithData(detailProjectVO);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultEntity.failed(e.getMessage());
		}
	}

	@RequestMapping("get/portal/type/project/data")
	public ResultEntity<List<PortalTypeVO>> getPortalTypeProjectDataRemote(){
		try {
			List<PortalTypeVO> portalTypeVO = projectService.getPortalTypeVO();
			return ResultEntity.successWithData(portalTypeVO, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss")) + "  " + LeaveConstant.MESSAGE_FIRENAY);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultEntity.failed(e.getMessage());
		}
	}

	@RequestMapping("/save/project/vo/remote")
	ResultEntity<String> saveProjectVORemote(@RequestBody ProjectVO projectVO, @RequestParam("memberId") Integer memberId){
		try {
			projectService.saveProject(projectVO, memberId);
			return ResultEntity.successWithoutData();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultEntity.failed(e.getMessage());
		}
	}
}
