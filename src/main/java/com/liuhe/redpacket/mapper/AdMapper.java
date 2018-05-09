/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.liuhe.redpacket.domain.Ad;
import com.liuhe.redpacket.query.AdQuery;
/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@Repository
public interface AdMapper{
	
	/**
	 * 保存
	 */
	void save(Ad emp);
	/**
	 * 更新
	 */
	void update(Ad emp);
	/**
	 * 删除
	 */
	void delete(Long id);
	/**
	 * 获取单个
	 */
	Ad get(Long id);
	/**
	 * 查询所有
	 */
	List<Ad> getAll();
	/**
	 * 高级查询
	 */
	List<Ad> query(AdQuery equ);
	/**
	 * 高级查询
	 */
	Long queryTotal(AdQuery equ);
	/**
	 * 获取红包对应广告
	 * @return
	 */
	List<Ad> getByRedpacket(@Param("redpacketId")Long redpacketId);
	Long queryByUserTotal(AdQuery qu);
	List<Ad> queryByUser(AdQuery qu);
	List<Ad> listNoUse();

}
