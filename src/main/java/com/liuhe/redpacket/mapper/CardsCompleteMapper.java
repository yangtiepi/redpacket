/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.liuhe.redpacket.domain.CardsComplete;
import com.liuhe.redpacket.query.CardsCompleteQuery;
/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@Repository
public interface CardsCompleteMapper{
	
	/**
	 * 保存
	 */
	void save(CardsComplete emp);
	/**
	 * 更新
	 */
	void update(CardsComplete emp);
	/**
	 * 删除
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
	List<CardsComplete> query(CardsCompleteQuery equ);
	/**
	 * 高级查询
	 */
	Long queryTotal(CardsCompleteQuery equ);

    List<CardsComplete> findByUser(String openid);
}
