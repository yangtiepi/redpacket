package com.liuhe.redpacket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuhe.redpacket.domain.SystemException;
import com.liuhe.redpacket.mapper.SystemExceptionMapper;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.SystemExceptionQuery;
import com.liuhe.redpacket.service.ISystemExceptionService;

@Service
public class SystemExceptionServiceImpl implements ISystemExceptionService {
	@Autowired
	private SystemExceptionMapper systemExceptionMapper;

	@Override
	public void saveException(SystemException systemException) {
		systemExceptionMapper.save(systemException);
	}

	@Override
	public SystemException get(Long id) {
		return systemExceptionMapper.get(id);
	}

	@Override
	public PageResult<SystemException> query(SystemExceptionQuery qu) {
		// 统计查询
		Long total = systemExceptionMapper.queryTotal(qu);
		// 分页查询
		List<SystemException> rows = systemExceptionMapper.query(qu);
		return new PageResult<SystemException>(rows, qu.getPageSize(),
				qu.getCurrentPage(), total.intValue());
	}
}
