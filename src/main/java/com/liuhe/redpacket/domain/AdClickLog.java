/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

public class AdClickLog implements Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	private Long id;
	private Long userId;
	private Date clickTime;
	private Long adId;
	private String adTitle;
	private String userName;
	private String openid;
	private String wechat;
	private Long homepageId;

	// columns END

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}

	public void setUserId(Long value) {
		this.userId = value;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setClickTime(Date value) {
		this.clickTime = value;
	}

	public Date getClickTime() {
		return this.clickTime;
	}

	public void setAdTitle(String value) {
		this.adTitle = value;
	}

	public String getAdTitle() {
		return this.adTitle;
	}

	public void setUserName(String value) {
		this.userName = value;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setOpenid(String value) {
		this.openid = value;
	}

	public String getOpenid() {
		return this.openid;
	}

	public void setWechat(String value) {
		this.wechat = value;
	}

	public String getWechat() {
		return this.wechat;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public Long getHomepageId() {
		return homepageId;
	}

	public void setHomepageId(Long homepageId) {
		this.homepageId = homepageId;
	}

	@Override
	public String toString() {
		return "AdClickLog [id=" + id + ", userId=" + userId + ", clickTime="
				+ clickTime + ", adId=" + adId + ", adTitle=" + adTitle
				+ ", userName=" + userName + ", openid=" + openid + ", wechat="
				+ wechat + "]";
	}

}
