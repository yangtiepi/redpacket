/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.domain.Card;
import com.liuhe.redpacket.domain.Cards;
import com.liuhe.redpacket.domain.CardsComplete;
import com.liuhe.redpacket.domain.UserCard;
import com.liuhe.redpacket.exception.LogicException;
import com.liuhe.redpacket.mapper.CardMapper;
import com.liuhe.redpacket.mapper.CardsCompleteMapper;
import com.liuhe.redpacket.mapper.CardsMapper;
import com.liuhe.redpacket.mapper.UserCardMapper;
import com.liuhe.redpacket.query.CardQuery;
import com.liuhe.redpacket.query.CardsQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.UserCardQuery;
import com.liuhe.redpacket.service.ICardsService;
import com.liuhe.redpacket.utils.ConstUtil;
import com.liuhe.redpacket.utils.RandomUtil;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class CardsServiceImpl implements ICardsService{

	@Autowired
	private CardsMapper cardsMapper;
	@Autowired
	private CardMapper cardMapper;
	@Autowired
	private UserCardMapper userCardMapper;
	@Autowired
	private CardsCompleteMapper cardsCompleteMapper;
	
	
	@Override
	public void save(Cards cards) {
		Date date = new Date();
		cards.setCreateTime(date);
		cards.setBeginTime(date);
		cards.setStatus(ConstUtil.CARDS_ENABLED);
		cards.setIsDel(0);
		cardsMapper.save(cards);
	}

	
	@Override
	public void update(Cards cards) {
		cardsMapper.update(cards);
	}

	
	@Override
	public void delete(Long id) {
		Cards cards = cardsMapper.get(id);
		cards.setIsDel(1);
		cards.setDelTime(new Date());
		cardsMapper.update(cards);
	}
	
	@Override
	public Cards get(Long id) {
		return cardsMapper.get(id);
	}

	
	@Override
	public List<Cards> getAll() {
		return cardsMapper.getAll();
	}
	@Override
	public List<JSONObject> getOfRatio(Long cardId) {
		return cardsMapper.getOfRatio(cardId);
	}
	
	

	
	@Override
	public PageResult<Cards> query(CardsQuery qu) {
		// 统计查询
		Long total = cardsMapper.queryTotal(qu);
		// 分页查询
		List<Cards> rows = cardsMapper.query(qu);
		return new PageResult<Cards>(rows, qu.getPageSize(),
				qu.getCurrentPage(), total.intValue());
	}


	@Override
	public void disable(Long id) {
		Cards cards = cardsMapper.get(id);
		if (cards!= null && cards.getStatus() != ConstUtil.CARDS_DISABLED) {
			cards.setStatus(ConstUtil.CARDS_DISABLED);
			cardsMapper.update(cards);
		}
		
	}
	@Override
	public void enable(Long id) {
		Cards cards = cardsMapper.get(id);
		if (cards!= null && cards.getStatus() != ConstUtil.CARDS_ENABLED) {
			cards.setStatus(ConstUtil.CARDS_ENABLED);
			cardsMapper.update(cards);
		}
		
	}


	@Override
	public Map<String, Object> mobileCardsList(String openid) {
		CardsQuery qu = new CardsQuery();
		qu.setPageSize(20);
		qu.setCurrentPage(1);
		qu.setStatus(ConstUtil.CARDS_ENABLED);
		qu.setOpenid(openid);
		List<Cards> cardss = cardsMapper.query(qu);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("cardss", cardss);
		return model;
	}


	@Override
	public Map<String, Object> cardsInfo(Long cardsId) {
		Cards cards = cardsMapper.get(cardsId);
		Map<String, Object> model = new HashMap<String, Object>();
		if (cards != null && cards.getIsDel() == 0 && cards.getStatus() == ConstUtil.CARDS_ENABLED) {
			model.put("info", cards.getInfo());
		}
		return model;
	}
}
