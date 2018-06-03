/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service;

import java.util.List;

import com.liuhe.redpacket.domain.CardsComplete;
import com.liuhe.redpacket.query.CardsCompleteQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.utils.result.AjaxResult;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

public interface ICardsCompleteService{
	/**
	 * 保存账户
	 */
	void save(CardsComplete entity);
	/**
	 * 更新账户信息
	 */
	void update(CardsComplete entity);
	/**
	 * 删除账户
	 */
	void delete(Long id);
	/**
	 * 获取单个
	 */
	CardsComplete get(Long id);
	/**
	 * 查询所有
	 */
	List<CardsComplete> getAll();
	/**
	 * 高级查询
	 */
	PageResult<CardsComplete> query(CardsCompleteQuery equ);
	/**
	 * 完成领取
	 * @param id
	 */
	void receice(Long id);

	/**
	 * 兑换
	 * @param openid
	 * @param num
	 * @return
	 */
	AjaxResult exchange(String openid, Integer num);
}
