package com.liuhe.redpacket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.liuhe.redpacket.domain.SystemDictionary;
import com.liuhe.redpacket.mapper.SystemDictionaryMapper;
import com.liuhe.redpacket.service.ISystemDictionaryService;

@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService {
	@Autowired
	private SystemDictionaryMapper systemDictionaryMapper;

	@Cacheable
	@Override
	public SystemDictionary getMap(Long id) {
		return systemDictionaryMapper.getMap(id);
	}

	@CacheEvict
	@Override
	public void save(SystemDictionary systemDictionary) {
		systemDictionaryMapper.save(systemDictionary);
	}

	@CacheEvict
	@Override
	public void update(SystemDictionary systemDictionary) {
		systemDictionaryMapper.update(systemDictionary);
	}

	@CacheEvict
	@Override
	public void delete(Long id) {
		systemDictionaryMapper.delete(id);
	}

	@Cacheable
	@Override
	public List<SystemDictionary> getAll() {
		return systemDictionaryMapper.getAll();
	}

}
