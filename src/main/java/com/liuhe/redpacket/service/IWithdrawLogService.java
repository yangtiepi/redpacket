/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service;

import java.util.List;

import com.liuhe.redpacket.domain.WithdrawLog;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

public interface IWithdrawLogService{
	/**
	 * 保存账户
	 */
	void save(WithdrawLog entity);
	/**
	 * 更新账户信息
	 */
	void update(WithdrawLog entity);
	/**
	 * 删除账户
	 */
	void delete(Long id);
	/**
	 * 获取单个
	 */
	WithdrawLog get(Long id);
	/**
	 * 查询所有
	 */
	List<WithdrawLog> getAll();
}
