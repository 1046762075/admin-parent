package com.firenay.leave.controller;

import com.firenay.leave.api.MySQLRemoteService;
import com.firenay.leave.config.OSSProperties;
import com.firenay.leave.constant.LeaveConstant;
import com.firenay.leave.entity.vo.DetailProjectVO;
import com.firenay.leave.entity.vo.MemberConfirmInfoVO;
import com.firenay.leave.entity.vo.MemberLoginVO;
import com.firenay.leave.entity.vo.ProjectVO;
import com.firenay.leave.entity.vo.ReturnVO;
import com.firenay.leave.util.LEAVEUtil;
import com.firenay.leave.util.ResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: ProjectConsumerController</p>
 * Description：信息收集图片上传
 * date：2020/5/19 22:31
 */
@Controller
public class ProjectConsumerController {

	@Resource
	private OSSProperties OSS;

	@Resource
	private MySQLRemoteService mySQLRemoteService;

	@RequestMapping("get/project/derail/{projectId}")
	public String getProjectDetail(@PathVariable("projectId") Integer projectId, ModelMap modelMap){
		ResultEntity<DetailProjectVO> resultEntity = mySQLRemoteService.getDetailProjectVORemote(projectId);
		String result = resultEntity.getResult();
		if(ResultEntity.SUCCESS.equals(result)){
			DetailProjectVO detailProjectVO = resultEntity.getData();
			modelMap.addAttribute("detailProjectVO", detailProjectVO);
			return "project-show-detail";
		}
		return "";
	}

	/**
	 * 最终提交表单
	 */
	@RequestMapping("create/confirm")
	public String saveConfirm(HttpSession session, MemberConfirmInfoVO memberConfirmInfoVO, ModelMap modelMap){

		ProjectVO projectVO = (ProjectVO) session.getAttribute(LeaveConstant.ATTR_NAME_TEMPLE_PROJECT);
		if(projectVO == null){
			throw new RuntimeException(LeaveConstant.MESSAGE_TEMPLE_PROJECT_MISSING);
		}
		// projectVO 继续收集回报表单的数据
		projectVO.setMemberConfirmInfoVO(memberConfirmInfoVO);
		// 获取登录成功那个对象
		MemberLoginVO memberLoginVO = (MemberLoginVO) session.getAttribute(LeaveConstant.ATTR_NAME_LOGIN_MEMBER);
		ResultEntity<String> saveResultEntity = mySQLRemoteService.saveProjectVORemote(projectVO, memberLoginVO.getId());
		String result = saveResultEntity.getResult();
		if(ResultEntity.FAILED.equals(result)){
			modelMap.addAttribute(LeaveConstant.ATTR_NAME_MESSAGE, saveResultEntity.getMessage());
			return "project-confirm";
		}
		// 提交成功 移除session中一路保存的数据
		session.removeAttribute(LeaveConstant.ATTR_NAME_TEMPLE_PROJECT);
		return "redirect:http://www.fireflynay.top:520/project/create/success";
	}

	@ResponseBody
	@RequestMapping("create/save/return.json")
	public ResultEntity<String> saveReturn(ReturnVO returnVO, HttpSession session){
		try {
			ProjectVO projectVO = (ProjectVO) session.getAttribute(LeaveConstant.ATTR_NAME_TEMPLE_PROJECT);
			if(projectVO == null){
				return ResultEntity.failed(LeaveConstant.MESSAGE_TEMPLE_PROJECT_MISSING);
			}
			// 1.从projectVO对象获取存储回报信息的集合
			List<ReturnVO> returnVOs = projectVO.getReturnVOList();
			if(returnVOs == null || returnVOs.size() == 0){
				returnVOs = new ArrayList<>();
				projectVO.setReturnVOList(returnVOs);
			}
			// 2.收集回报信息并写入session
			returnVOs.add(returnVO);
			session.setAttribute(LeaveConstant.ATTR_NAME_TEMPLE_PROJECT, projectVO);
			return ResultEntity.successWithoutData();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultEntity.failed(e.getMessage());
		}
	}

	//	project-return.html  455行的上传参数
	@ResponseBody
	@RequestMapping("create/upload/return/picture.json")
	public ResultEntity<String> uploadReturnPicture(@RequestParam("returnPicture") MultipartFile returnPicture) throws IOException {
		return LEAVEUtil.uploadFileToOss(OSS.getEndPoint(),
				OSS.getAccessKeyId(),
				OSS.getAccessKeySecret(),
				returnPicture.getInputStream(),
				OSS.getBucketName(),
				OSS.getBucketDomain(),
				returnPicture.getOriginalFilename());
	}

	/**
	 * @param projectVO   除了上传图片意外的普通数据
	 * @param headerPicture  头图
	 * @param detailPictureList	详情的图片
	 * @param session	最后收集一部分 VO 对象存入 session
	 */
	@RequestMapping("create/project/information")
	public String saveProjectBasicInfo(ProjectVO projectVO, MultipartFile headerPicture, List<MultipartFile> detailPictureList, HttpSession session, ModelMap modelMap) throws IOException {
		String result;
		if(headerPicture.isEmpty()){
			modelMap.addAttribute(LeaveConstant.ATTR_NAME_MESSAGE, LeaveConstant.MESSAGE_HEADER_PIC_EMPTY);
			return "project-launch";
		}
		// 1. 头图不为空 完成头图的上传
		ResultEntity<String> uploadFileResultEntity = LEAVEUtil.uploadFileToOss(OSS.getEndPoint(),
				OSS.getAccessKeyId(),
				OSS.getAccessKeySecret(),
				headerPicture.getInputStream(),
				OSS.getBucketName(),
				OSS.getBucketDomain(),
				headerPicture.getOriginalFilename());
		result = uploadFileResultEntity.getResult();
		// 2.判断是否上传成功
		if(ResultEntity.SUCCESS.equals(result)){
			// 获取图片访问路径
			String headerPicturePath = uploadFileResultEntity.getData();
			projectVO.setHeaderPicturePath(headerPicturePath);
		}else{
			modelMap.addAttribute(LeaveConstant.ATTR_NAME_MESSAGE, LeaveConstant.MESSAGE_HEADER_PIC_UPLOAD_FAILED);
			return "project-launch";
		}
		// 3.用于收集详情图片
		ArrayList<String> detailUploadResults = new ArrayList<>();
		// 详情图片不可为空
		if(detailPictureList == null || detailPictureList.size() == 0){
			modelMap.addAttribute(LeaveConstant.ATTR_NAME_MESSAGE, LeaveConstant.MESSAGE_DETAIL_PIC_EMPTY);
			return "project-launch";
		}
		for (MultipartFile multipartPic : detailPictureList) {
			if(multipartPic.isEmpty()){
				modelMap.addAttribute(LeaveConstant.ATTR_NAME_MESSAGE, LeaveConstant.MESSAGE_DETAIL_PIC_EMPTY);
				return "project-launch";
			}
			ResultEntity<String> detailUploadResultEntity = LEAVEUtil.uploadFileToOss(OSS.getEndPoint(),
					OSS.getAccessKeyId(),
					OSS.getAccessKeySecret(),
					multipartPic.getInputStream(),
					OSS.getBucketName(),
					OSS.getBucketDomain(),
					multipartPic.getOriginalFilename());
			result = detailUploadResultEntity.getResult();
			if(ResultEntity.SUCCESS.equals(result)){
				// 将单个上传成功的图片路径存入集合
				detailUploadResults.add(detailUploadResultEntity.getData());
			}else{
				modelMap.addAttribute(LeaveConstant.ATTR_NAME_MESSAGE, LeaveConstant.MESSAGE_DETAIL_PIC_UPLOAD_FAILED);
				return "project-launch";
			}
		}
		// 4.将刚刚收集的图片集合存入VO对象
		projectVO.setDetailPicturePathList(detailUploadResults);
		// 将VO对象存入session
		session.setAttribute(LeaveConstant.ATTR_NAME_TEMPLE_PROJECT, projectVO);
		// 5.重定向到下一个收集回报信息页面
		return "redirect:http://www.fireflynay.top:520/project/return/info/page";
	}
}
