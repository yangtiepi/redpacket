package com.liuhe.redpacket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liuhe.redpacket.domain.User;
import com.liuhe.redpacket.query.UserQuery;

public interface UserMapper {

	void save(User user);

	void update(User user);

	/**
	 * 更新绑定微信
	 * 
	 * @param user
	 */
	void updateWechat(User user);

	void delete(Long id);
	/**
	 * 查询单个
	 * @param id
	 * @return
	 */
	User get(Long id);


	/**
	 * 根据微信信息查询
	 * 
	 * @param storeId
	 * @return
	 */
	User getByWechat(@Param("openid") String openid);

	List<User> getAll();

	/**
	 * 登录检查用户是否存在
	 * 
	 * @param name
	 * @param password
	 *            @ return 包含店铺对象
	 */
	User checkLogin(String username);

	List<User> query(UserQuery qu);

	Long queryTotal(UserQuery qu);

}
