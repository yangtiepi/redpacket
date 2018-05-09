/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.liuhe.redpacket.domain.AdHomepageLink;
import com.liuhe.redpacket.domain.Homepage;
import com.liuhe.redpacket.query.HomepageQuery;
/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@Repository
public interface HomepageMapper{
	
	/**
	 * 保存
	 */
	void save(Homepage emp);
	/**
	 * 更新
	 */
	void update(Homepage emp);
	/**
	 * 删除
	 */
	void delete(Long id);
	/**
	 * 获取单个
	 */
	Homepage get(Long id);
	/**
	 * 查询所有
	 */
	List<Homepage> getAll();
	/**
	 * 高级查询
	 */
	List<Homepage> query(HomepageQuery equ);
	/**
	 * 高级查询
	 */
	Long queryTotal(HomepageQuery equ);
	void deleteAdHomepageLink(@Param("homepageId")Long homepageId);
	void saveAdHomepageLink(List<AdHomepageLink> list);
	/**
	 * 获取有该广告的所有首页
	 * @param adId
	 * @return
	 */
	List<Homepage> getByAd(@Param("adId")Long adId);
	/**
	 * 获取总点击次数
	 * @param homepageId
	 * @return
	 */
	int getTotalClicked(@Param("homepageId")Long homepageId);

}
