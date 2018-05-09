package com.liuhe.redpacket.service;

import java.util.List;

import com.liuhe.redpacket.domain.SystemDictionary;

public interface ISystemDictionaryService {
	/**
	 * 获取单个（包含details）
	 */
	SystemDictionary getMap(Long id);

	void save(SystemDictionary systemDictionary);

	void update(SystemDictionary systemDictionary);

	void delete(Long id);
	/**
	 * 获取所有（包含details）
	 */
	List<SystemDictionary> getAll();
}
