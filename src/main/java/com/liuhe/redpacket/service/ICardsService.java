/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.domain.Cards;
import com.liuhe.redpacket.query.CardsQuery;
import com.liuhe.redpacket.query.PageResult;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

public interface ICardsService{
	/**
	 * 保存账户
	 */
	void save(Cards entity);
	/**
	 * 更新账户信息
	 */
	void update(Cards entity);
	/**
	 * 删除账户
	 */
	void delete(Long id);
	/**
	 * 获取单个
	 */
	Cards get(Long id);
	/**
	 * 查询所有
	 */
	List<Cards> getAll();
	/**
	 * 查询所有有卡片概率的
	 * @param cardId 
	 */
	List<JSONObject> getOfRatio(Long cardId);
	/**
	 * 高级查询
	 */
	PageResult<Cards> query(CardsQuery equ);
	/**
	 * 停用
	 * @param id
	 */
	void disable(Long id);
	/**
	 * 启用
	 * @param id
	 */
	void enable(Long id);
	/**
	 * 手机端集卡列表
	 * @param openid
	 * @return
	 */
	Map<String, Object> mobileCardsList(String openid);
	/**
	 * 集卡说明
	 * @param cardsId
	 * @return
	 */
	Map<String, Object> cardsInfo(Long cardsId);
	/**
	 * 卡片领取
	 * @param cardsId
	 * @param openid
	 * @param nickname 
	 * @return
	 */
	Map<String, Object> cardsComplete(Long cardsId, String openid, String nickname);
}
