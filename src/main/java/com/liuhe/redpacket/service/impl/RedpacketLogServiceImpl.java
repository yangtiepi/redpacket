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
import com.liuhe.redpacket.domain.RedpacketLog;
import com.liuhe.redpacket.mapper.RedpacketLogMapper;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.RedpacketLogQuery;
import com.liuhe.redpacket.service.IRedpacketLogService;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class RedpacketLogServiceImpl implements IRedpacketLogService{

	@Autowired
	private RedpacketLogMapper RedpacketLogMapper;
	
	
	@Override
	public void save(RedpacketLog RedpacketLog) {
		RedpacketLogMapper.save(RedpacketLog);
	}

	
	@Override
	public void update(RedpacketLog RedpacketLog) {
		RedpacketLogMapper.update(RedpacketLog);
	}

	
	@Override
	public void delete(Long id) {
		RedpacketLogMapper.delete(id);
	}
	
	@Override
	public RedpacketLog get(Long id) {
		return RedpacketLogMapper.get(id);
	}

	
	@Override
	public List<RedpacketLog> getAll() {
		return RedpacketLogMapper.getAll();
	}

	
	@Override
	public PageResult<RedpacketLog> query(RedpacketLogQuery qu) {
		// 统计查询
		Long total = RedpacketLogMapper.queryTotal(qu);
		// 分页查询
		List<RedpacketLog> rows = RedpacketLogMapper.query(qu);
		return new PageResult<RedpacketLog>(rows, qu.getPageSize(),
				qu.getCurrentPage(), total.intValue());
	}


	@Override
	public PageResult<JSONObject> queryGroupAd(RedpacketLogQuery qu) {
		// 统计查询
				Long total = RedpacketLogMapper.queryGroupAdTotal(qu);
				// 分页查询
				List<JSONObject> rows = RedpacketLogMapper.queryGroupAd(qu);
				return new PageResult<JSONObject>(rows, qu.getPageSize(),
						qu.getCurrentPage(), total.intValue());
	}
	@Override
	public PageResult<JSONObject> queryInfo(RedpacketLogQuery qu) {
		// 统计查询
		Long total = RedpacketLogMapper.queryInfoTotal(qu);
		// 分页查询
		List<JSONObject> rows = RedpacketLogMapper.queryInfo(qu);
		return new PageResult<JSONObject>(rows, qu.getPageSize(),
				qu.getCurrentPage(), total.intValue());
	}
}
