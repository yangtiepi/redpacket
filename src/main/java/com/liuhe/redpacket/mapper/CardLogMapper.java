/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.mapper;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.domain.CardLog;
import com.liuhe.redpacket.query.CardLogQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@Repository
public interface CardLogMapper {
	
	/**
	 * 保存
	 */
	void save(CardLog emp);
	/**
	 * 更新
	 */
	void update(CardLog emp);
	/**
	 * 删除
	 */
	void delete(Long id);
	/**
	 * 获取单个
	 */
	CardLog get(Long id);
	/**
	 * 查询所有
	 */
	List<CardLog> getAll();
	/**
	 * 高级查询
	 */
	List<CardLog> query(CardLogQuery equ);
	/**
	 * 高级查询
	 */
	Long queryTotal(CardLogQuery equ);
}
