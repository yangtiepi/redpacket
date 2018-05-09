/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.domain.Redpacket;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.RedpacketQuery;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

public interface IRedpacketService{
	/**
	 * 保存账户
	 */
	void save(Redpacket entity);
	/**
	 * 更新账户信息
	 */
	void update(Redpacket entity);
	/**
	 * 删除账户
	 */
	void delete(Long id);
	/**
	 * 获取单个
	 */
	Redpacket get(Long id);
	/**
	 * 查询所有
	 */
	List<Redpacket> getAll();
	/**
	 * 高级查询
	 */
	PageResult<Redpacket> query(RedpacketQuery equ);
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
	 * 领取红包页面
	 * @param adId
	 * @param openid
	 * @param wechat 
	 * @return
	 */
	Map<String, Object> receiveRedpacket(Long redpacketId, String openid, String wechat);
	/**
	 * 确认领取
	 * @param userCardId
	 * @param redpacketLogId
	 * @param openid 
	 */
	JSONObject confirmRedpacket(Long userCardId, Long redpacketLogId, String openid);
}
