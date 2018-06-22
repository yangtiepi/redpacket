/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service.impl;

import com.liuhe.redpacket.domain.CardLog;
import com.liuhe.redpacket.mapper.CardLogMapper;
import com.liuhe.redpacket.query.CardLogQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.service.ICardLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class CardLogServiceImpl implements ICardLogService {

	@Autowired
	private CardLogMapper cardLogMapper;
	
	
	@Override
	public void save(CardLog CardLog) {
		cardLogMapper.save(CardLog);
	}

	
	@Override
	public void update(CardLog CardLog) {
		cardLogMapper.update(CardLog);
	}

	
	@Override
	public void delete(Long id) {
		cardLogMapper.delete(id);
	}
	
	@Override
	public CardLog get(Long id) {
		return cardLogMapper.get(id);
	}

	
	@Override
	public List<CardLog> getAll() {
		return cardLogMapper.getAll();
	}

	
	@Override
	public PageResult<CardLog> query(CardLogQuery qu) {
		// 统计查询
		Long total = cardLogMapper.queryTotal(qu);
		// 分页查询
		List<CardLog> rows = cardLogMapper.query(qu);
		return new PageResult<CardLog>(rows, qu.getPageSize(),
				qu.getCurrentPage(), total.intValue());
	}

}
