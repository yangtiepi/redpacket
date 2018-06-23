/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.domain.RedpacketLog;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.RedpacketLogQuery;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

public interface IRedpacketLogService{
	/**
	 * 保存账户
	 */
	void save(RedpacketLog entity);
	/**
	 * 更新账户信息
	 */
	void update(RedpacketLog entity);
	/**
	 * 删除账户
	 */
	void delete(Long id);
	/**
	 * 获取单个
	 */
	RedpacketLog get(Long id);
	/**
	 * 查询所有
	 */
	List<RedpacketLog> getAll();
	/**
	 * 高级查询
	 */
	PageResult<RedpacketLog> query(RedpacketLogQuery equ);

	/**
	 * 查询总金额
	 * @param qu
	 * @return
	 */
    Double queryTotalAmount(RedpacketLogQuery qu);
}
