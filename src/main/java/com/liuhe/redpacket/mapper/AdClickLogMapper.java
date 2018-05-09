/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.domain.AdClickLog;
import com.liuhe.redpacket.query.AdClickLogQuery;
/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@Repository
public interface AdClickLogMapper{
	
	/**
	 * 保存
	 */
	void save(AdClickLog emp);
	/**
	 * 更新
	 */
	void update(AdClickLog emp);
	/**
	 * 删除
	 */
	void delete(Long id);
	/**
	 * 获取单个
	 */
	AdClickLog get(Long id);
	/**
	 * 获取单个
	 */
	AdClickLog getByOpenid(String openid);
	/**
	 * 查询所有
	 */
	List<AdClickLog> getAll();
	/**
	 * 高级查询
	 */
	List<AdClickLog> query(AdClickLogQuery equ);
	/**
	 * 高级查询
	 */
	Long queryTotal(AdClickLogQuery equ);
	/**
	 * 高级查询
	 */
	List<JSONObject> queryGroupAd(AdClickLogQuery equ);
	/**
	 * 高级查询
	 */
	Long queryGroupAdTotal(AdClickLogQuery equ);
	AdClickLog getByAdOpenid(@RequestParam("adId")Long id, @RequestParam("openid")String openid);

}
