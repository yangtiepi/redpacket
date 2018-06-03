/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.domain.Card;
import com.liuhe.redpacket.query.CardQuery;
/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@Repository
public interface CardMapper{
	
	/**
	 * 保存
	 */
	void save(Card emp);
	/**
	 * 更新
	 */
	void update(Card emp);
	/**
	 * 删除
	 */
	void delete(Long id);
	/**
	 * 获取单个
	 */
	Card get(Long id);
	/**
	 * 查询所有
	 */
	List<Card> getAll();
	/**
	 * 高级查询
	 */
	List<Card> query(CardQuery equ);
	/**
	 * 高级查询
	 */
	Long queryTotal(CardQuery equ);
	/**
	 * 获取未兑换的所有卡片
	 */
	List<JSONObject> getUserCard(@Param("openid")String openid);

}
