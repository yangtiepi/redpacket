package com.liuhe.redpacket.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.domain.User;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.UserQuery;
import com.liuhe.redpacket.utils.result.AjaxResult;

public interface IUserService {
	/**
	 * 分页获取app端用户
	 * @param UserQuery
	 * @return
	 */
	PageResult<User> query(UserQuery UserQuery);
	/**
	 * 登录校验
	 * @param username
	 * @param password
	 * @param openid 
	 * @param wechat 
	 * @return  包含店铺对象
	 */
	User findcheckLogin(User user);
	/**
	 * 注销
	 */
	void logout();
	/**
	 * 注册
	 * @param username
	 * @param password
	 * @param invitationCode
	 * @param openid 
	 * @param wechat 
	 */
	User register(User user);
	/**
	 * 修改密码
	 * @param username
	 * @param password
	 * @param newPassWord
	 * @return  包含店铺对象
	 */
	User updatePassword(String username, String password, String newPassWord);
	/**
	 * 重置密码
	 * @param username
	 * @param password
	 * @return  包含店铺对象
	 */
	User forgetPassword(String username, String password);
	/**
	 * 停用账户
	 */
	void leave(Long id);
	/**
	 * 启用账户
	 * @param id
	 */
	void up(Long id);
	/**
	 * 删除账户
	 * @param id
	 */
	void delete(Long id);
	/**
	 * 微信登录
	 * @param wechat
	 * @param openid
	 * @return  包含店铺对象
	 */
	User checkWechatLogin(User user);
	/**
	 * 获取单个
	 * @param id
	 * @return  包含店铺对象
	 */
	User get(Long id);
	/**
	 * 更新
	 * @param user
	 */
	void update(User user);
	/**
	 * 账户
	 * @param openid
	 * @return
	 */
	Map<String, Object> userInfo(String openid);
	void saveOrUpdateUser(JSONObject jsonObject);
	AjaxResult withdraw(String openid);

    User getByWechat(String openid);
}
