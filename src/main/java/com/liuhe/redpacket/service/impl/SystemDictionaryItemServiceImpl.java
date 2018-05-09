package com.liuhe.redpacket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.liuhe.redpacket.domain.SystemDictionaryItem;
import com.liuhe.redpacket.mapper.SystemDictionaryItemMapper;
import com.liuhe.redpacket.service.ISystemDictionaryItemService;

@Service
public class SystemDictionaryItemServiceImpl implements
		ISystemDictionaryItemService {
	@Autowired
	private SystemDictionaryItemMapper systemDictionaryItemMapper;

	@Cacheable
	@Override
	public SystemDictionaryItem getMap(Long id) {
		return systemDictionaryItemMapper.getMap(id);
	}

	@CacheEvict
	@Override
	public void save(SystemDictionaryItem systemDictionaryItem) {
		systemDictionaryItemMapper.save(systemDictionaryItem);

	}

	@CacheEvict
	@Override
	public void update(SystemDictionaryItem systemDictionaryItem) {
		systemDictionaryItemMapper.update(systemDictionaryItem);

	}

	@CacheEvict
	@Override
	public void delete(Long id) {
		systemDictionaryItemMapper.delete(id);

	}

	@Cacheable
	@Override
	public List<SystemDictionaryItem> getAll() {
		return systemDictionaryItemMapper.getAll();
	}

	@Cacheable
	@Override
	public List<SystemDictionaryItem> getByParent(Long id) {
		return systemDictionaryItemMapper.findByParent(id);
	}

	@Override
	public List<SystemDictionaryItem> getByParentKey(String key) {
		return systemDictionaryItemMapper.findByParentKey(key);
	}

}
