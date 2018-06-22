/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.mapper;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.domain.QrCode;
import com.liuhe.redpacket.query.QrCodeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@Repository
public interface QrCodeMapper {
	
	/**
	 * 保存
	 */
	void save(QrCode emp);
	/**
	 * 更新
	 */
	void update(QrCode emp);
	/**
	 * 删除
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
	List<QrCode> query(QrCodeQuery equ);
	/**
	 * 高级查询
	 */
	Long queryTotal(QrCodeQuery equ);

    QrCode getByCode(String code);
}
