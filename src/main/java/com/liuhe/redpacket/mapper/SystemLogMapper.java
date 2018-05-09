package com.liuhe.redpacket.mapper;

import java.util.List;

import com.liuhe.redpacket.domain.SystemLog;
import com.liuhe.redpacket.query.SystemLogQuery;

public interface SystemLogMapper {
	void save(SystemLog systemLog);
	SystemLog get(Long id);
	
	
	List<SystemLog> query(SystemLogQuery q);
	
	Long queryTotal(SystemLogQuery q);
}
