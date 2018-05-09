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

import com.liuhe.redpacket.domain.Homepage;
import com.liuhe.redpacket.query.HomepageQuery;
import com.liuhe.redpacket.query.PageResult;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

public interface IHomepageService{
	/**
	 * 保存账户
	 * @param img 
	 * @throws IOException 
	 */
	void save(MultipartFile img, Homepage entity) throws IOException;
	/**
	 * 更新账户信息
	 * @param img 
	 */
	void update(MultipartFile img, Homepage entity) throws IOException;
	/**
	 * 删除账户
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
	PageResult<Homepage> query(HomepageQuery equ);
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
	 * 客户端首页数据
	 * @param homepageId
	 * @param page
	 * @param openid 
	 * @return
	 */
	Map<String, Object> mobileIndex(Long homepageId, Integer page, String openid);
}
