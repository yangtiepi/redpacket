/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service;

import java.util.List;

import com.liuhe.redpacket.domain.UserCard;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.UserCardQuery;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

public interface IUserCardService{
	/**
	 * 保存账户
	 */
	void save(UserCard entity);
	/**
	 * 更新账户信息
	 */
	void update(UserCard entity);
	/**
	 * 删除账户
	 */
	void delete(Long id);
	/**
	 * 获取单个
	 */
	UserCard get(Long id);
	/**
	 * 查询所有
	 */
	List<UserCard> getAll();
	/**
	 * 高级查询
	 */
	PageResult<UserCard> query(UserCardQuery equ);

    List<UserCard> queryList(UserCardQuery userCardQuery);
}
