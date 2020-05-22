package com.firenay.leave.service.Impl;

import com.firenay.leave.entity.po.MemberConfirmInfoPO;
import com.firenay.leave.entity.po.MemberLaunchInfoPO;
import com.firenay.leave.entity.po.ProjectPO;
import com.firenay.leave.entity.po.ReturnPO;
import com.firenay.leave.entity.vo.DetailProjectVO;
import com.firenay.leave.entity.vo.DetailReturnVO;
import com.firenay.leave.entity.vo.MemberConfirmInfoVO;
import com.firenay.leave.entity.vo.MemberLauchInfoVO;
import com.firenay.leave.entity.vo.PortalTypeVO;
import com.firenay.leave.entity.vo.ProjectVO;
import com.firenay.leave.entity.vo.ReturnVO;
import com.firenay.leave.mapper.MemberConfirmInfoPOMapper;
import com.firenay.leave.mapper.MemberLaunchInfoPOMapper;
import com.firenay.leave.mapper.ProjectItemPicPOMapper;
import com.firenay.leave.mapper.ProjectPOMapper;
import com.firenay.leave.mapper.ReturnPOMapper;
import com.firenay.leave.service.api.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: ProjectServiceImpl</p>
 * Description：发起众筹最终落地写入数据库
 * date：2020/5/19 20:52
 */
@Transactional(readOnly = true)
@Service
public class ProjectServiceImpl implements ProjectService {

	@Resource
	private ProjectPOMapper projectPOMapper;

	@Resource
	private ProjectItemPicPOMapper projectItemPicPOMapper;

	@Resource
	private MemberLaunchInfoPOMapper memberLaunchInfoPOMapper;

	@Resource
	private ReturnPOMapper returnPOMapper;

	@Resource
	private MemberConfirmInfoPOMapper memberConfirmInfoPOMapper;

	/**
	 * @param projectVO
	 * @param memberId 发起人的id
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	@Override
	public int saveProject(ProjectVO projectVO, Integer memberId) {
		// 1.保存 projectPO 对象
		ProjectPO projectPO = new ProjectPO();
		// 2.把projectVO中的属性复制到projectPO中
		BeanUtils.copyProperties(projectVO, projectPO);
		// 生成创建时间
		String createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		projectPO.setCreatedate(createDate);
		projectPO.setDeploydate(createDate);
		projectPO.setSupporter((int) (Math.random() * 9999));
		projectPO.setSupportmoney((long) (Math.random()* projectPO.getMoney()));
		projectPO.setFollower((int) (Math.random() * 9999));
		// status :0  表示即将开始
		projectPO.setStatus(0);
		projectPO.setMemberid(memberId);
		projectPOMapper.insertSelective(projectPO);
		// 拿到自增主键
		Integer projectId = projectPO.getId();
		// 3.保存分类的关联信息
		List<Integer> typeIdList = projectVO.getTypeIdList();
		projectPOMapper.insertTypeRelationship(typeIdList, projectId);
		// 4.保存标签的关联关系信息
		List<Integer> tagIdList = projectVO.getTagIdList();
		projectPOMapper.insertTagRelationship(tagIdList, projectId);
		// 5.保存详情图片路径信息
		List<String> detailPicturePaths = projectVO.getDetailPicturePathList();
		projectItemPicPOMapper.insertPaths(detailPicturePaths, projectId);
		// 6.保存项目发起人信息
		MemberLauchInfoVO memberLauchInfoVO = projectVO.getMemberLauchInfoVO();
		MemberLaunchInfoPO memberLaunchInfoPO = new MemberLaunchInfoPO();
		BeanUtils.copyProperties(memberLauchInfoVO, memberLaunchInfoPO);
		memberLaunchInfoPO.setMemberid(memberId);
		memberLaunchInfoPOMapper.insert(memberLaunchInfoPO);
		// 7.保存回报信息 这里可能报空指针异常
		List<ReturnVO> returnVOList = projectVO.getReturnVOList();
		List<ReturnPO> returnPOs = new ArrayList<>();
		for (ReturnVO returnVO : returnVOList) {
			ReturnPO returnPO = new ReturnPO();
			BeanUtils.copyProperties(returnVO, returnPO);
			returnPOs.add(returnPO);
		}
		returnPOMapper.insertReturnPOBatch(returnPOs, projectId);
		// 8.保存项目确认信息
		MemberConfirmInfoVO memberConfirmInfoVO = projectVO.getMemberConfirmInfoVO();
		MemberConfirmInfoPO memberConfirmInfoPO = new MemberConfirmInfoPO();
		BeanUtils.copyProperties(memberConfirmInfoVO, memberConfirmInfoPO);
		memberConfirmInfoPO.setMemberid(memberId);
		memberConfirmInfoPOMapper.insert(memberConfirmInfoPO);
		return 1;
	}

	@Override
	public List<PortalTypeVO> getPortalTypeVO() {
		return projectPOMapper.selectPortalTypeVOs();
	}

	@Override
	public DetailProjectVO getDetailProjectVO(Integer projectId) {
		DetailProjectVO detailProjectVO = projectPOMapper.selectDetailProjectVO(projectId);
		// 1.判断当前属于那种情况
		Integer status = detailProjectVO.getStatus();
		switch (status){
			case 0: detailProjectVO.setStatusText("审核中");break;
			case 1: detailProjectVO.setStatusText("众筹中");break;
			case 2: detailProjectVO.setStatusText("众筹成功");break;
			case 3: detailProjectVO.setStatusText("已关闭");break;
			default:break;
		}
		// 2.计算还剩多久关闭众筹
		String deployDate = detailProjectVO.getDeployDate();
		Date currentDay = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date deployDay = format.parse(deployDate);
			// 获取两个时间的时间戳
			long currentTimeStamp = currentDay.getTime();
			long deployTimeStamp = deployDay.getTime();
			// 算出相差的时间戳
			long pastDays = (currentTimeStamp - deployTimeStamp) / 1000 / 60 / 60/ 24;
			// 获取众筹的天数
			Integer totalDays = detailProjectVO.getDay();
			Integer lastDay = (int)(totalDays - pastDays);
			detailProjectVO.setLastDay(lastDay);
			detailProjectVO.setSupporterCount((int) (Math.random() * 6666));
			List<DetailReturnVO> detailReturnVOList = detailProjectVO.getDetailReturnVOList();
			for (DetailReturnVO detailReturnVO : detailReturnVOList) {
				detailReturnVO.setSupproterCount((int) (Math.random() * detailReturnVO.getPurchase()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return  detailProjectVO;
	}
}
