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

import com.liuhe.redpacket.domain.WithdrawLog;
import com.liuhe.redpacket.mapper.WithdrawLogMapper;
import com.liuhe.redpacket.service.IWithdrawLogService;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class WithdrawLogServiceImpl implements IWithdrawLogService{

	@Autowired
	private WithdrawLogMapper WithdrawLogMapper;
	
	
	@Override
	public void save(WithdrawLog WithdrawLog) {
		WithdrawLogMapper.save(WithdrawLog);
	}

	
	@Override
	public void update(WithdrawLog WithdrawLog) {
		WithdrawLogMapper.update(WithdrawLog);
	}

	
	@Override
	public void delete(Long id) {
		WithdrawLogMapper.delete(id);
	}
	
	@Override
	public WithdrawLog get(Long id) {
		return WithdrawLogMapper.get(id);
	}

	
	@Override
	public List<WithdrawLog> getAll() {
		return WithdrawLogMapper.getAll();
	}
}
