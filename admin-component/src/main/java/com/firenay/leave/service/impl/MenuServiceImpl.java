package com.firenay.leave.service.impl;

import com.firenay.leave.Menu;
import com.firenay.leave.MenuExample;
import com.firenay.leave.mapper.MenuMapper;
import com.firenay.leave.service.api.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>Title: MenuServiceImpl</p>
 * Description：
 * date：2020/5/11 19:09
 */
@Service
public class MenuServiceImpl implements MenuService {

	@Resource
	private MenuMapper menuMapper;

	@Override
	public List<Menu> getAll() {
		return menuMapper.selectByExample(new MenuExample());
	}

	@Override
	public void saveMenu(Menu menu) {
		menuMapper.insert(menu);
	}

	@Override
	public void updateMenu(Menu menu) {
		// 由于pid没有传入，保证 pid 字段不会被置空
		menuMapper.updateByPrimaryKeySelective(menu);
	}

	@Override
	public void removeMenu(Integer id) {
		menuMapper.deleteByPrimaryKey(id);
	}

}
