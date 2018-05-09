/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.query;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

public class AdClickLogQuery extends BaseQuery implements Serializable {
	private static final long serialVersionUID = 3148176768559230877L;

	private java.lang.Long adId;
	private java.lang.Long homepageId;
	private java.lang.String openid;
	private String adTitle;
	private String beginTime;
	private String endTime;

	public java.lang.Long getAdId() {
		return adId;
	}

	public void setAdId(java.lang.Long adId) {
		this.adId = adId;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = StringUtils.isBlank(beginTime) ? null : beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = StringUtils.isBlank(endTime) ? null : endTime;
	}

	public String getAdTitle() {
		return adTitle;
	}

	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}

	public java.lang.String getOpenid() {
		return openid;
	}

	public void setOpenid(java.lang.String openid) {
		this.openid = openid;
	}

	public java.lang.Long getHomepageId() {
		return homepageId;
	}

	public void setHomepageId(java.lang.Long homepageId) {
		this.homepageId = homepageId;
	}

	@Override
	public String toString() {
		return "AdClickLogQuery [adId=" + adId + ", homepageId=" + homepageId
				+ ", openid=" + openid + ", adTitle=" + adTitle
				+ ", beginTime=" + beginTime + ", endTime=" + endTime + "]";
	}

}
