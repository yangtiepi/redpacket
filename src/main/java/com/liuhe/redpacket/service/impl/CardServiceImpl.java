/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.domain.Card;
import com.liuhe.redpacket.mapper.CardMapper;
import com.liuhe.redpacket.query.CardQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.service.ICardService;
import com.liuhe.redpacket.utils.FileUtil;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class CardServiceImpl implements ICardService{

	@Autowired
	private CardMapper cardMapper;
	
	
	@Override
	public void save(MultipartFile img, Card card) throws IOException {
		if (img != null && !img.isEmpty()) {
			String src = FileUtil.saveImage(img, "cards");
			card.setImage(src);
		}
		card.setIsDel(0);
		cardMapper.save(card);
	}

	
	@Override
	public void update(MultipartFile img, Card card) throws IOException {
		if (img != null && !img.isEmpty()) {
			String src = FileUtil.saveImage(img, "cards");
			FileUtil.deleteFile(card.getImage());
			card.setImage(src);
		}
		cardMapper.update(card);
	}

	
	@Override
	public void delete(Long id) {
		Card card = cardMapper.get(id);
		card.setIsDel(1);
		card.setDelTime(new Date());
		cardMapper.update(card);
	}
	
	@Override
	public Card get(Long id) {
		return cardMapper.get(id);
	}

	
	@Override
	public List<Card> getAll() {
		return cardMapper.getAll();
	}

	
	@Override
	public PageResult<Card> query(CardQuery qu) {
		// 统计查询
		Long total = cardMapper.queryTotal(qu);
		// 分页查询
		List<Card> rows = cardMapper.query(qu);
		return new PageResult<Card>(rows, qu.getPageSize(),
				qu.getCurrentPage(), total.intValue());
	}


	@Override
	public Map<String, Object> userCard(Long cardsId,String openid) {
		List<JSONObject> cards= cardMapper.getUserCard(cardsId, openid);
		Map<String, Object> model = new HashMap<String, Object>();
		
		CardQuery qu = new CardQuery();
		qu.setCardsId(cardsId);
		List<Card> list = cardMapper.query(qu);
		
		boolean isComplete = true;
		//加入未获取的卡片
		outer:
		for (Card card : list) {
			for (JSONObject json : cards) {
				if (card.getId().equals(json.getLong("id"))) {
					break outer;
				}
				
			}
			isComplete = false;
			JSONObject json = (JSONObject) JSON.toJSON(card);
			json.put("cardNum", 0);
			cards.add(json);
		}
		model.put("cards", cards);
		model.put("cardsId", cardsId);
		
		
		if (isComplete) {
			JSONObject jsonObject = cards.get(0);
			model.put("completeNum", jsonObject.get("cardNum"));
		}else {
			model.put("completeNum", 0);
		}
		return model;
	}
}
