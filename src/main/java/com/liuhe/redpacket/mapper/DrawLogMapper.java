/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.mapper;

import java.util.List;

import com.liuhe.redpacket.domain.DrawLog;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.query.DrawLogQuery;
/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@Repository
public interface DrawLogMapper {
	
	/**
	 * 保存
	 */
	void save(DrawLog emp);
	/**
	 * 保存
	 */
	void update(DrawLog emp);
	/**
	 * 高级查询
	 */
	List<DrawLog> query(DrawLogQuery equ);
	/**
	 * 高级查询
	 */
	Long queryTotal(DrawLogQuery equ);

    List<DrawLog> findByUser(String openid);

	/**
	 * 抽奖统计
	 * @param openid
	 * @return
	 */
	JSONObject getSum(String openid);
}
