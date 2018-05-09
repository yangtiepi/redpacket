package com.liuhe.redpacket.service;

import com.liuhe.redpacket.domain.SystemLog;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.SystemLogQuery;

public interface ISystemLogService {
	
	void save(SystemLog systemLog);
	SystemLog get(Long id);
	
	
	PageResult<SystemLog> query(SystemLogQuery q);
	
}
