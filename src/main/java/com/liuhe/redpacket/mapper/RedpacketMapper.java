/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.liuhe.redpacket.domain.Redpacket;
import com.liuhe.redpacket.query.RedpacketQuery;
/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@Repository
public interface RedpacketMapper{
	
	/**
	 * 保存
	 */
	void save(Redpacket emp);
	/**
	 * 更新
	 */
	void update(Redpacket emp);
	/**
	 * 删除
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
	List<Redpacket> query(RedpacketQuery equ);
	/**
	 * 高级查询
	 */
	Long queryTotal(RedpacketQuery equ);

}
