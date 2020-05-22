package com.firenay.leave.controller;

import com.firenay.leave.api.MySQLRemoteService;
import com.firenay.leave.constant.LeaveConstant;
import com.firenay.leave.entity.vo.PortalTypeVO;
import com.firenay.leave.util.ResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: PortalController</p>
 * Description：去首页
 * date：2020/5/18 15:37
 */
@Controller
public class PortalController {

	@Resource
	private MySQLRemoteService mySQLRemoteService;

//	@RequestMapping("/")
//	public String showPortalPage(){
//		return "portal";
//	}

	@RequestMapping("/")
	public String showPortalPage(ModelMap modelMap){
		ResultEntity<List<PortalTypeVO>> resultEntity = mySQLRemoteService.getPortalTypeProjectDataRemote();
		String result = resultEntity.getResult();
		if(ResultEntity.SUCCESS.equals(result)){
			// 获取查询结果数据
			List<PortalTypeVO> list = resultEntity.getData();
			modelMap.addAttribute(LeaveConstant.ATTR_NAME_PORTAL_DATA, list);
		}
		return "portal";
	}
}
