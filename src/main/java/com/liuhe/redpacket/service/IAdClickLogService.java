/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.domain.AdClickLog;
import com.liuhe.redpacket.query.AdClickLogQuery;
import com.liuhe.redpacket.query.PageResult;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

public interface IAdClickLogService{
	/**
	 * 保存账户
	 */
	void save(AdClickLog entity);
	/**
	 * 更新账户信息
	 */
	void update(AdClickLog entity);
	/**
	 * 删除账户
	 */
	void delete(Long id);
	/**
	 * 获取单个
	 */
	AdClickLog get(Long id);
	/**
	 * 查询所有
	 */
	List<AdClickLog> getAll();
	/**
	 * 高级查询
	 */
	PageResult<AdClickLog> query(AdClickLogQuery equ);
	PageResult<JSONObject> queryGroupAd(AdClickLogQuery qu);
}
