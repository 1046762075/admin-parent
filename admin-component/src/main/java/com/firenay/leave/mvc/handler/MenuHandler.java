package com.firenay.leave.mvc.handler;

import com.firenay.leave.Menu;
import com.firenay.leave.constant.LeaveConstant;
import com.firenay.leave.service.api.MenuService;
import com.firenay.leave.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>Title: MenuHandler</p>
 * Description：
 * date：2020/5/11 19:11
 */
@RestController
public class MenuHandler {

	@Resource
	private MenuService menuService;

	private Logger log = LoggerFactory.getLogger(RoleHandler.class);

	private String menus;
	
	private boolean Accs(Integer id) {
		if(id > 19) {
			return true;
		}
		throw new RuntimeException("对不起！您无法对这个菜单进行更改！");
	}

	@RequestMapping("menu/remove.json")
	public ResultEntity<String> removeMenu(@RequestParam("id") Integer id) {
		Accs(id);
		menuService.removeMenu(id);
		menus = "删除" + id + "号菜单成功";
		log.warn("\n" + menus);
		return ResultEntity.successWithoutData(menus);
	}

	@RequestMapping("menu/update.json")
	public ResultEntity<String> updateMenu(Menu menu) {
		Accs(menu.getId());
		menuService.updateMenu(menu);
		menus = "修改" + menu + "菜单成功";
		log.warn("\n" + menus);
		return ResultEntity.successWithoutData("修改" + menu.getName() + "菜单成功");
	}

	@RequestMapping("menu/save.json")
	public ResultEntity<String> saveMenu(Menu menu) {
		if(null == menu || "".equals(menu.getName().trim()) || "".equals(menu.getIcon().trim()) || "".equals(menu.getUrl().trim())) {
			throw new RuntimeException("请填写完整的菜单信息！");
		}
		menuService.saveMenu(menu);
		menus = "添加" + menu + "菜单成功";
		log.info("\n" + menus);
		return ResultEntity.successWithoutData("添加" + menu.getName() + "菜单成功");
	}

	@RequestMapping("menu/get/whole/tree.json")
	public ResultEntity<Menu> getWholeTreeNew() {
		// 1.查询全部的Menu对象
		List<Menu> menus = menuService.getAll();
		Menu root = null;
		// 2.创建Map对象用来存储id和Menu对象的对应关系便于查找父节点
		Map<Integer, Menu> menuMap = new HashMap<>();
		// 3.遍历 menus 填充menuMap
		for (Menu menu : menus) {
			menuMap.put(menu.getId(), menu);
		}
		// 4.再次遍历 menus 查找根节点、组装父子节点
		for (Menu menu : menus) {
			// pid 即父节点的id
			Integer pid = menu.getPid();
			if (pid == null) {
				root = menu;
				// 5.如果当前节点是根节点，那么肯定没有父节点，不必继续执行
				continue;
			}
			// 6.如果pid不为null，说明当前节点有父节点，直接map获取
			Menu father = menuMap.get(pid);
			// 7.将当前节点存入父节点的children集合
			father.getChildren().add(menu);
		}
		// 8.经过上面的运算，根节点包含了整个树形结构，返回根节点就是返回整个树
		return ResultEntity.successWithData(root, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss")) + "  " + LeaveConstant.MESSAGE_FIRENAY);
	}

	public ResultEntity<Menu> getWholeTreeOld() {
		// 1.查询全部的Menu对象
		List<Menu> menus = menuService.getAll();
		Menu root = null;
		for (Menu menu : menus) {
			// 2.获取当前menu对象的pid属性值
			Integer pid = menu.getPid();
			if (pid == null) {
				// 3.这个pid等于 null 的这个menu就是root节点
				root = menu;
				// 4.停止本次循环，继续执行下一次循环
				continue;
			}
			// 5.开始建立关系, 如果pid不为null，说明当前节点有父节点，找到父节点就可以进行组装，建立父子关系
			for (Menu maybeFather : menus) {
				// 6.获取maybeFather的id属性
				Integer id = maybeFather.getId();
				// 7.将子节点的pid和疑似父节点的id进行比较
				if (Objects.equals(pid, id)) {
					// 8.将子节点存入父节点的children集合
					maybeFather.getChildren().add(menu);
					// 9.找到即可停止运行循环
					break;
				}
			}
		}
		// 10.将组装的树形结构（也就是根节点对象）返回给浏览器
		return ResultEntity.successWithData(root);
	}
}
