/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service.impl;

import java.util.Date;
import java.util.List;

import com.liuhe.redpacket.domain.*;
import com.liuhe.redpacket.query.UserCardQuery;
import com.liuhe.redpacket.service.*;
import com.liuhe.redpacket.utils.RandomUtil;
import com.liuhe.redpacket.utils.result.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liuhe.redpacket.mapper.CardsCompleteMapper;
import com.liuhe.redpacket.query.CardsCompleteQuery;
import com.liuhe.redpacket.query.PageResult;
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
	private CardsCompleteMapper cardsCompleteMapper;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserCardService userCardService;
	@Autowired
	private ICardLogService cardLogService;
	@Autowired
	private ICardService cardService;
	@Autowired
	private ICardsService cardsService;

	
	@Override
	public void save(CardsComplete CardsComplete) {
		cardsCompleteMapper.save(CardsComplete);
	}

	
	@Override
	public void update(CardsComplete CardsComplete) {
		cardsCompleteMapper.update(CardsComplete);
	}

	
	@Override
	public void delete(Long id) {
		cardsCompleteMapper.delete(id);
	}
	
	@Override
	public CardsComplete get(Long id) {
		return cardsCompleteMapper.get(id);
	}

	
	@Override
	public List<CardsComplete> getAll() {
		return cardsCompleteMapper.getAll();
	}

	
	@Override
	public PageResult<CardsComplete> query(CardsCompleteQuery qu) {
		// 统计查询
		Long total = cardsCompleteMapper.queryTotal(qu);
		// 分页查询
		List<CardsComplete> rows = cardsCompleteMapper.query(qu);
		return new PageResult<CardsComplete>(rows, qu.getPageSize(),
				qu.getCurrentPage(), total.intValue());
	}


	@Override
	public void receice(Long id) {
		CardsComplete cardsComplete = cardsCompleteMapper.get(id);
		if (ConstUtil.CARDS_COMPLETE == cardsComplete.getStatus()) {
			cardsComplete.setStatus(ConstUtil.CARDS_RECEIVE);
			cardsComplete.setReceiveTime(new Date());
			cardsCompleteMapper.update(cardsComplete);
		}
	}

	@Override
	public AjaxResult exchange(String openid, Long cardsId) {
		Cards cards = cardsService.get(cardsId);
		if (cards == null){
			return new AjaxResult("要兑换的奖品不存在",-100);
		}
		Integer num = cards.getCompleteNum();

		User user = userService.getByWechat(openid);

		UserCardQuery userCardQuery = new UserCardQuery();
		userCardQuery.setOpenid(openid);
		userCardQuery.setExchangeTime(null);
		List<UserCard> userCards = userCardService.queryList(userCardQuery);
		if (userCards == null || userCards.isEmpty() || userCards.size() < num){
			return new AjaxResult("卡片数量不足",-100);
		}

		Date date = new Date();
        for (int i = 0; i < num; i++) {
            UserCard userCard = userCards.get(i);
			userCard.setExchangeTime(date);
			userCardService.update(userCard);
			Card card = cardService.get(userCard.getCardId());
			System.out.println("===========更新时间================="+userCard.getReceiveTime());


			CardLog cardLog = new CardLog();
			cardLog.setCardId(userCard.getCardId());
			if(card != null){
				cardLog.setCardName(card.getName());
			}
			cardLog.setOpenid(openid);
			cardLog.setQrCodeId(userCard.getId());
			cardLog.setReceiveTime(date);
			cardLog.setType(1);
			cardLog.setUserId(user.getId());
			cardLog.setUserName(user.getUsername());
			cardLogService.save(cardLog);
        }

		String randomCode = RandomUtil.randomCode(6);
		CardsComplete cardsComplete = new CardsComplete();
		cardsComplete.setCode(randomCode);
		cardsComplete.setCompleteTime(new Date());
		cardsComplete.setOpenid(openid);
		cardsComplete.setUserId(user.getId());
		cardsComplete.setUserName(user.getUsername());
		cardsComplete.setStatus(ConstUtil.CARDS_COMPLETE);
		cardsComplete.setCardsId(cards.getId());
		cardsComplete.setCardsName(cards.getInfo());
		cardsCompleteMapper.save(cardsComplete);

		return new AjaxResult(cardsComplete);
	}

	@Override
	public List<CardsComplete> findByUser(String openid) {
		return cardsCompleteMapper.findByUser(openid);
	}
}
