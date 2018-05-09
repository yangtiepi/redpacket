package com.liuhe.redpacket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuhe.redpacket.domain.SystemLog;
import com.liuhe.redpacket.mapper.SystemLogMapper;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.SystemLogQuery;
import com.liuhe.redpacket.service.ISystemLogService;

@Service
public class SystemLogServiceImpl implements ISystemLogService {
	@Autowired
	private SystemLogMapper systemLogMapper;

	@Override
	public void save(SystemLog systemLog) {
		systemLogMapper.save(systemLog);
	}

	@Override
	public SystemLog get(Long id) {
		return systemLogMapper.get(id);
	}

	@Override
	public PageResult<SystemLog> query(SystemLogQuery qu) {
		// 统计查询
		Long total = systemLogMapper.queryTotal(qu);
		// 分页查询
		List<SystemLog> rows = systemLogMapper.query(qu);
		return new PageResult<SystemLog>(rows, qu.getPageSize(),
				qu.getCurrentPage(), total.intValue());
	}
}
