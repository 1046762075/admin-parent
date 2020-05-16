package com.firenay.leave.service.api;

import com.firenay.leave.Menu;

import java.util.List;

/**
 * <p>Title: MenuService</p>
 * Description：
 * date：2020/5/11 19:09
 */
public interface MenuService {

	/**
	 * 获取所有节点
	 */
	List<Menu> getAll();

	void saveMenu(Menu menu);

	void updateMenu(Menu menu);

	void removeMenu(Integer id);

}
