package com.liuhe.redpacket.service;

import java.util.List;

import com.liuhe.redpacket.domain.Menu;
import com.liuhe.redpacket.domain.Role_Menu;

public interface IMenuService {
	List<Menu> findByParentList();
	
	List<Menu> findByChildList(Long id);
	
	/*
	 * 向前台返回菜单的数据对象
	 */
	Menu[] getMenuResult();

	/*
	 * 查询出菜单中的所有的数据信息
	 */
	List<Menu> getAll();
	
	/*
	 * 根据菜单的id来
	 * 	查询出有多少角色可以来访问这个菜单
	 */
	Menu findRoles(Long id);
	
	/*
	 * 保存菜单和角色之间的关系
	 */
	void saveRelativity(Role_Menu rm);
}
