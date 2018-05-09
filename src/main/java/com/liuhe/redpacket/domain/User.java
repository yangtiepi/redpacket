package com.liuhe.redpacket.domain;

import java.io.Serializable;

import com.liuhe.redpacket.utils.ConstUtil;

/**
 * APP账户
 * */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4297188847457542054L;
	private Long id;
	private String username;
	private String password;
	private Double amount;
	private String headImage;// 头像
	private Integer status = ConstUtil.USER_NORMAL;// 状态,0异常,无法使用,1正常
	private String wechat;// 绑定的微信昵称
	private String openid;// 微信的授权标识
	private String remark;// 微信的授权标识

	public User(Long userId) {
		this.id = userId;
	}

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", amount=" + amount + ", headImage="
				+ headImage + ", status=" + status + ", wechat=" + wechat
				+ ", openid=" + openid + "]";
	}

}
