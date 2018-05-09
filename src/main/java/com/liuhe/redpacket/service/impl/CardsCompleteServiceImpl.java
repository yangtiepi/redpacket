/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liuhe.redpacket.domain.CardsComplete;
import com.liuhe.redpacket.mapper.CardsCompleteMapper;
import com.liuhe.redpacket.query.CardsCompleteQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.service.ICardsCompleteService;
import com.liuhe.redpacket.utils.ConstUtil;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class CardsCompleteServiceImpl implements ICardsCompleteService{

	@Autowired
	private CardsCompleteMapper CardsCompleteMapper;
	
	
	@Override
	public void save(CardsComplete CardsComplete) {
		CardsCompleteMapper.save(CardsComplete);
	}

	
	@Override
	public void update(CardsComplete CardsComplete) {
		CardsCompleteMapper.update(CardsComplete);
	}

	
	@Override
	public void delete(Long id) {
		CardsCompleteMapper.delete(id);
	}
	
	@Override
	public CardsComplete get(Long id) {
		return CardsCompleteMapper.get(id);
	}

	
	@Override
	public List<CardsComplete> getAll() {
		return CardsCompleteMapper.getAll();
	}

	
	@Override
	public PageResult<CardsComplete> query(CardsCompleteQuery qu) {
		// 统计查询
		Long total = CardsCompleteMapper.queryTotal(qu);
		// 分页查询
		List<CardsComplete> rows = CardsCompleteMapper.query(qu);
		return new PageResult<CardsComplete>(rows, qu.getPageSize(),
				qu.getCurrentPage(), total.intValue());
	}


	@Override
	public void receice(Long id) {
		CardsComplete cardsComplete = CardsCompleteMapper.get(id);
		if (ConstUtil.CARDS_COMPLETE == cardsComplete.getStatus()) {
			cardsComplete.setStatus(ConstUtil.CARDS_RECEIVE);
			cardsComplete.setReceiveTime(new Date());
			CardsCompleteMapper.update(cardsComplete);
		}
	}
}
