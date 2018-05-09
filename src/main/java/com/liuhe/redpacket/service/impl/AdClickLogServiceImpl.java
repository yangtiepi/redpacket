/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.domain.AdClickLog;
import com.liuhe.redpacket.mapper.AdClickLogMapper;
import com.liuhe.redpacket.query.AdClickLogQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.service.IAdClickLogService;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class AdClickLogServiceImpl implements IAdClickLogService{

	@Autowired
	private AdClickLogMapper AdClickLogMapper;
	
	
	@Override
	public void save(AdClickLog AdClickLog) {
		AdClickLogMapper.save(AdClickLog);
	}

	
	@Override
	public void update(AdClickLog AdClickLog) {
		AdClickLogMapper.update(AdClickLog);
	}

	
	@Override
	public void delete(Long id) {
		AdClickLogMapper.delete(id);
	}
	
	@Override
	public AdClickLog get(Long id) {
		return AdClickLogMapper.get(id);
	}

	
	@Override
	public List<AdClickLog> getAll() {
		return AdClickLogMapper.getAll();
	}

	
	@Override
	public PageResult<AdClickLog> query(AdClickLogQuery qu) {
		// 统计查询
		Long total = AdClickLogMapper.queryTotal(qu);
		// 分页查询
		List<AdClickLog> rows = AdClickLogMapper.query(qu);
		return new PageResult<AdClickLog>(rows, qu.getPageSize(),
				qu.getCurrentPage(), total.intValue());
	}


	@Override
	public PageResult<JSONObject> queryGroupAd(AdClickLogQuery qu) {
		// 统计查询
		Long total = AdClickLogMapper.queryGroupAdTotal(qu);
		// 分页查询
		List<JSONObject> rows = AdClickLogMapper.queryGroupAd(qu);
		return new PageResult<JSONObject>(rows, qu.getPageSize(),
				qu.getCurrentPage(), total.intValue());
	}
}
