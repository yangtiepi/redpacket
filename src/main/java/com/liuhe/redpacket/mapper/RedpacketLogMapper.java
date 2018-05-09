/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.domain.RedpacketLog;
import com.liuhe.redpacket.query.RedpacketLogQuery;
/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@Repository
public interface RedpacketLogMapper{
	
	/**
	 * 保存
	 */
	void save(RedpacketLog emp);
	/**
	 * 更新
	 */
	void update(RedpacketLog emp);
	/**
	 * 删除
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
	List<RedpacketLog> query(RedpacketLogQuery equ);
	/**
	 * 高级查询
	 */
	Long queryTotal(RedpacketLogQuery equ);
	/**
	 * 红包统计
	 * @param qu
	 * @return
	 */
	Long queryGroupAdTotal(RedpacketLogQuery qu);
	List<JSONObject> queryGroupAd(RedpacketLogQuery qu);
	/**
	 * 红包明细
	 * @param qu
	 * @return
	 */
	Long queryInfoTotal(RedpacketLogQuery qu);
	List<JSONObject> queryInfo(RedpacketLogQuery qu);

}
