/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.liuhe.redpacket.domain.Ad;
import com.liuhe.redpacket.query.AdQuery;
import com.liuhe.redpacket.query.PageResult;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

public interface IAdService{
	/**
	 * 保存账户
	 */
	void save(Ad entity);
	/**
	 * 更新账户信息
	 */
	void update(Ad entity);
	/**
	 * 删除账户
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
	PageResult<Ad> query(AdQuery equ);
	/**
	 * 保存广告
	 * @param ad
	 * @param smallImg
	 * @param image
	 */
	void save(Ad ad, MultipartFile smallImg,
			MultipartFile image) throws IOException;
	/**
	 * 更新
	 * @param ad
	 * @param smallImg
	 * @param image
	 * @throws IOException 
	 */
	void update(Ad ad, MultipartFile smallImg,
			MultipartFile image) throws IOException;
	/**
	 * 手机端广告页面
	 * @param adId
	 * @param homepageId 
	 * @param nickname 
	 * @return
	 */
	Map<String, Object> mobileIndex(Long adId, Long homepageId, String openid, String nickname);
	/**
	 * 手机端广告列表
	 * @param qu
	 * @return
	 */
	PageResult<Ad> queryByUser(AdQuery qu);
	/**
	 * 未使用广告列表
	 * @return
	 */
	List<Ad> listNoUse();
}
