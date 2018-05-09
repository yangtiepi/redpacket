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
import com.liuhe.redpacket.domain.Cards;
import com.liuhe.redpacket.query.CardsQuery;
/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@Repository
public interface CardsMapper{
	
	/**
	 * 保存
	 */
	void save(Cards emp);
	/**
	 * 更新
	 */
	void update(Cards emp);
	/**
	 * 删除
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
	 * 高级查询
	 */
	List<Cards> query(CardsQuery equ);
	/**
	 * 高级查询
	 */
	Long queryTotal(CardsQuery equ);
	/**
	 * 查询所有有卡片概率的
	 * @param cardId 
	 * 
	 * @return
	 */
	List<JSONObject> getOfRatio(@Param("cardId")Long cardId);

}
