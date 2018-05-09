/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.liuhe.redpacket.domain.UserCard;
import com.liuhe.redpacket.query.UserCardQuery;
/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@Repository
public interface UserCardMapper{
	
	/**
	 * 保存
	 */
	void save(UserCard emp);
	/**
	 * 更新
	 */
	void update(UserCard emp);
	/**
	 * 删除
	 */
	int delete(Long id);
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
	List<UserCard> query(UserCardQuery equ);
	/**
	 * 高级查询
	 */
	Long queryTotal(UserCardQuery equ);
	/**
	 * 用户在时间段内领取红包数量
	 * @param openid
	 * @param dayStart
	 * @param dayEnd
	 * @return
	 */
	int getTotal(@Param("openid")String openid, @Param("start")Date start, @Param("end")Date end);
	UserCard getByRedPacketLog(Long id);

}
