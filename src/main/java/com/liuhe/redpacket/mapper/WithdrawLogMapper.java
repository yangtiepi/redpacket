package com.liuhe.redpacket.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.liuhe.redpacket.domain.WithdrawLog;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@Repository
public interface WithdrawLogMapper {

	/**
	 * 保存
	 */
	void save(WithdrawLog emp);

	/**
	 * 更新
	 */
	void update(WithdrawLog emp);

	/**
	 * 删除
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
