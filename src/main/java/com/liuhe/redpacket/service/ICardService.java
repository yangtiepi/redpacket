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

import com.liuhe.redpacket.domain.Card;
import com.liuhe.redpacket.query.CardQuery;
import com.liuhe.redpacket.query.PageResult;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

public interface ICardService{
	/**
	 * 保存卡片
	 * @param img 
	 */
	void save(MultipartFile img, Card entity) throws IOException;
	/**
	 * 更新卡片信息
	 * @param img 
	 */
	void update(MultipartFile img, Card entity) throws IOException;
	/**
	 * 删除卡片
	 */
	void delete(Long id);
	/**
	 * 获取单个
	 */
	Card get(Long id);
	/**
	 * 查询所有
	 */
	List<Card> getAll();
	/**
	 * 高级查询
	 */
	PageResult<Card> query(CardQuery equ);
	/**
	 * 用户卡片
	 * @param openid
	 * @return
	 */
	Map<String, Object> userCard(String openid);
}
