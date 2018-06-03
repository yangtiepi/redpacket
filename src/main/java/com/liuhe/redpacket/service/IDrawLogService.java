/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.domain.DrawLog;
import com.liuhe.redpacket.query.DrawLogQuery;
import com.liuhe.redpacket.query.PageResult;

import java.util.List;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

public interface IDrawLogService{
	/**
	 * 保存
	 */
	void save(DrawLog entity);
	DrawLog get(Long redpacketId,String openid);

	/**
	 * 高级查询
	 */
	PageResult<DrawLog> query(DrawLogQuery equ);

	List<DrawLog> findByUser(String openid);

	JSONObject getSum(String openid);
}
