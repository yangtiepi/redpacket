package com.liuhe.redpacket.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liuhe.redpacket.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.domain.SystemDictionaryItem;
import com.liuhe.redpacket.domain.User;
import com.liuhe.redpacket.domain.WithdrawLog;
import com.liuhe.redpacket.exception.LogicException;
import com.liuhe.redpacket.mapper.UserMapper;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.UserQuery;
import com.liuhe.redpacket.utils.ConstUtil;
import com.liuhe.redpacket.utils.DoubleUtils;
import com.liuhe.redpacket.utils.IPUtil;
import com.liuhe.redpacket.utils.MD5Util;
import com.liuhe.redpacket.utils.OrderNumUtil;
import com.liuhe.redpacket.utils.StatusCode;
import com.liuhe.redpacket.utils.UserContext;
import com.liuhe.redpacket.utils.result.AjaxResult;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private IWxService wxService;
	@Autowired
	private ISystemDictionaryItemService systemDictionaryItemService;
	@Autowired
	private IWithdrawLogService withdrawLogService;
	@Autowired
	private IDrawLogService drawLogService;

	@Override
	public PageResult<User> query(UserQuery qu) {
		// 统计查询
		Long total = userMapper.queryTotal(qu);
		// 分页查询
		List<User> rows = userMapper.query(qu);
		return new PageResult<User>(rows, qu.getPageSize(),
				qu.getCurrentPage(), total.intValue());
	}

	@Override
	public User findcheckLogin(User user) {
		// 加密密码
		String password = MD5Util.MD5(user.getPassword());
		User u = userMapper.checkLogin(user.getUsername());
		// 检查用户名
		if (u == null) {
			throw new LogicException("用户名错误！！", StatusCode.USERNAME_ERROR);
		}
		// 检查密码
		if (!u.getPassword().equals(password)) {
			throw new LogicException("密码错误！！", StatusCode.PASSWORD_ERROR);
		}
		// 检查状态
		if (user.getStatus() != 1) {
			throw new LogicException("账号状态异常,已停用！！", StatusCode.STATE_ERROR);
		}
//		if (openid != null) {
//			u.setWechat(wechat);
//			u.setOpenid(openid);
//			userMapper.updateWechat(u);
//		}
		return u;
	}
	
	@Override
	public void logout() {
		UserContext.removeUser();
	}

	@Override
	public User checkWechatLogin(User user) {
		// 加密密码
		String wechat = user.getWechat();
		String openid = user.getOpenid();
		User u = userMapper.getByWechat(openid);
		// 检查用户名
		if (u == null) {
			throw new LogicException("该微信号还未绑定任何账户！！", StatusCode.USER_NOWECHAT);
		}
		// 检查状态
		if (u.getStatus() != 1) {
			throw new LogicException("账号状态异常,已停用！！", StatusCode.STATE_ERROR);
		}
		// 判断微信昵称是否更新
		if (!wechat.equals(u.getWechat())) {
			u.setWechat(wechat);
			userMapper.updateWechat(u);
		}
		return u;
	}

	@Override
	public User register(User user) {
		// 加密密码
		String wechat = user.getWechat();
		String openid = user.getOpenid();
		String username = user.getUsername();
		String password = MD5Util.MD5(user.getPassword());
		// 检查用户名
		User u = userMapper.checkLogin(username);
		if (u != null) {
			throw new LogicException("用户名已存在！！", StatusCode.USERNAME_EXISTING);
		}

		u = new User();
		u.setUsername(username);
		u.setPassword(password);
		if (openid != null) {
			u.setWechat(wechat);
			u.setOpenid(openid);
		}
			userMapper.save(u);
		return u;
	}

	@Override
	public User updatePassword(String username, String password,
			String newPassword) {
		// 加密密码
		newPassword = MD5Util.MD5(newPassword);
		User user = userMapper.checkLogin(username);
		// 检查用户名
		if (user == null) {
			throw new LogicException("用户名错误！！", StatusCode.USERNAME_ERROR);
		}
		// 检查密码
		if (!user.getPassword().equals(password)) {
			throw new LogicException("密码错误！！", StatusCode.PASSWORD_ERROR);
		}
		user.setPassword(newPassword);
		userMapper.update(user);
		return user;
	}

	@Override
	public User forgetPassword(String username, String password) {
		// 加密密码
		password = MD5Util.MD5(password);
		// 检查用户名
		User user = userMapper.checkLogin(username);
		if (user == null) {
			throw new LogicException("用户名不存在！！", StatusCode.USERNAME_ERROR);
		}
		user.setPassword(password);
		userMapper.update(user);
		return user;
	}



	// 停用账户
	@Override
	public void leave(Long id) {
		User user = userMapper.get(id);
		user.setStatus(0);
		userMapper.update(user);

	}

	@Override
	public void up(Long id) {
		User user = userMapper.get(id);
		user.setStatus(1);
		userMapper.update(user);
		;
	}

	@Override
	public void delete(Long id) {
//		User user = userMapper.get(id);
//		if (user.getStatus() != 0) {
//			throw new LogicException("不能删除未停用的账户", StatusCode.USER_NOTDELETE);
//		}
		userMapper.delete(id);
	}



	@Override
	public User get(Long id) {
		return userMapper.get(id);
	}


	@Override
	public void update(User user) {
		userMapper.update(user);
	}



	@Override
	public Map<String, Object> userInfo(String openid) {
		User user = userMapper.getByWechat(openid);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", user);

		JSONObject json = drawLogService.getSum(openid);
		model.put("drawLogSum", json);
		return model;
	}

	@Override
	public void saveOrUpdateUser(JSONObject jsonObject) {
		String openid = jsonObject.getString(
				"openid");
		String nickname = jsonObject.getString(
				"nickname");
//		String sex = jsonObject.getString(
//				"sex");
//		String province = jsonObject.getString(
//				"province");
//		String city = jsonObject.getString(
//				"city");
//		String country = jsonObject.getString(
//				"country");
		String headimgurl = jsonObject.getString(
				"headimgurl");
		User user = userMapper.getByWechat(openid);
		if (user == null) {
			user = new User();
			user.setAmount(0D);
			user.setHeadImage(headimgurl);
			user.setOpenid(openid);
			user.setWechat(nickname);
			user.setStatus(ConstUtil.USER_NORMAL);
			userMapper.save(user);
		}else {
			user.setHeadImage(headimgurl);
			user.setWechat(nickname);
			userMapper.update(user);
		}
		
	}

	@Override
	public AjaxResult withdraw(String openid) {
		if (StringUtils.isBlank(openid)) {
			return new AjaxResult("未登录！", 400);
		}
		User user = userMapper.getByWechat(openid);
		if (user == null) {
			return new AjaxResult("用户不存在！", 400);
		}
		
		Double amount = user.getAmount();
		SystemDictionaryItem dictionaryItem = systemDictionaryItemService.getMap(4L);
		if (dictionaryItem != null) {
			String value = dictionaryItem.getValue();
			Double max = Double.valueOf(value);
			if (DoubleUtils.compare(max, amount) < 0) {
				return new AjaxResult("提现金额超过最大金额！", 400);
			}
		}
		
		String orderNum = OrderNumUtil.getOrderNum();
		Map<String, Object> map = wxService.weixinMmpaymkttransfers(orderNum, openid, "NO_CHECK", null, DoubleUtils.mul(amount, 100D).intValue(), "用户红包提现", IPUtil.getLocalIp());
		if ("0".equals(map.get("code"))) {
			user.setAmount(0D);
			userMapper.update(user);
			
			WithdrawLog withdrawLog = new WithdrawLog(user.getUsername(), openid, orderNum, new Date(), amount);
			withdrawLogService.save(withdrawLog);
			return new AjaxResult("提现成功！");
		}else {
			return new AjaxResult(map.get("msg").toString(), 400);
		}
	}

	@Override
	public User getByWechat(String openid) {
		return userMapper.getByWechat(openid);
	}


}
