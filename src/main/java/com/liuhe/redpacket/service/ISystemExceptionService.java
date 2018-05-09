package com.liuhe.redpacket.service;

import com.liuhe.redpacket.domain.SystemException;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.SystemExceptionQuery;

public interface ISystemExceptionService {
	
	void saveException(SystemException systemLog);
	SystemException get(Long id);
	
	
	PageResult<SystemException> query(SystemExceptionQuery q);
	
}
