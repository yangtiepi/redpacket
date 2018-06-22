/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service;


import com.liuhe.redpacket.domain.QrCode;
import com.liuhe.redpacket.query.QrCodeQuery;
import com.liuhe.redpacket.query.PageResult;

import java.util.List;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

public interface IQrCodeService {
	/**
	 * 保存账户
	 */
	QrCode save(QrCode entity);
	/**
	 * 更新账户信息
	 */
	void update(QrCode entity);
	/**
	 * 删除账户
	 */
	void delete(Long id);
	/**
	 * 获取单个
	 */
	QrCode get(Long id);
	/**
	 * 查询所有
	 */
	List<QrCode> getAll();
	/**
	 * 高级查询
	 */
	PageResult<QrCode> query(QrCodeQuery equ);

	/**
	 * 批量生成二维码
	 * @param num 数量
	 */
    List<QrCode> save(int num) throws InterruptedException;
}
