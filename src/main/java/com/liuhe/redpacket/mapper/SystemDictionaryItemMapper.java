package com.liuhe.redpacket.mapper;

import java.util.List;

import com.liuhe.redpacket.domain.SystemDictionaryItem;

public interface SystemDictionaryItemMapper {
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
	 *  获取对应数据字典目录id的所有
	 * @param id
	 * @return
	 */
	List<SystemDictionaryItem> findByParent(Long id);
	/**
	 *  获取对应数据字典目录key的所有
	 * @param key
	 * @return
	 */
	List<SystemDictionaryItem> findByParentKey(String key);
}
