package com.liuhe.redpacket.mapper;

import java.util.List;

import com.liuhe.redpacket.domain.SystemException;
import com.liuhe.redpacket.query.SystemExceptionQuery;

public interface SystemExceptionMapper {
	void save(SystemException systemLog);
	SystemException get(Long id);
	
	
	List<SystemException> query(SystemExceptionQuery q);
	
	Long queryTotal(SystemExceptionQuery q);
}
