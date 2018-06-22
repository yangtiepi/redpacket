/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service;


import com.liuhe.redpacket.domain.CardLog;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.CardLogQuery;

import java.util.List;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

public interface ICardLogService {
	/**
	 * 保存账户
	 */
	void save(CardLog entity);
	/**
	 * 更新账户信息
	 */
	void update(CardLog entity);
	/**
	 * 删除账户
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
	PageResult<CardLog> query(CardLogQuery equ);
}
