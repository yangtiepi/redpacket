package com.liuhe.redpacket.service;

import java.util.List;

import com.liuhe.redpacket.domain.SystemDictionaryItem;

public interface ISystemDictionaryItemService {

	/**
	 * 查询单个（包含parent）
	 * @param id
	 * @return
	 */
	SystemDictionaryItem getMap(Long id);

	void save(SystemDictionaryItem systemDictionaryItem);

	void update(SystemDictionaryItem systemDictionaryItem);

	void delete(Long id);

	List<SystemDictionaryItem> getAll();

	/**
	 * 获取数据字典目录id对应的所有（包含parent）
	 * 
	 * @param id
	 * @return
	 */
	List<SystemDictionaryItem> getByParent(Long id);

	/**
	 * 获取数据字典目录key对应的所有（包含parent）
	 * 
	 * @param key
	 * @return
	 */
	List<SystemDictionaryItem> getByParentKey(String key);
}
