/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.query;

import java.io.Serializable;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

public class AdQuery extends BaseQuery implements Serializable {
	private static final long serialVersionUID = 3148176768559230877L;

	private String title;
	private Integer status;
	private Integer isDel;
	private Long homepageId;
	private Integer isValid;
	private String openid;

	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	public java.lang.Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(java.lang.Integer isDel) {
		this.isDel = isDel;
	}

	public Long getHomepageId() {
		return homepageId;
	}

	public void setHomepageId(Long homepageId) {
		this.homepageId = homepageId;
	}

	public java.lang.Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(java.lang.Integer isValid) {
		this.isValid = isValid;
	}
	

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "AdQuery [title=" + title + ", status=" + status + ", isDel="
				+ isDel + ", homepageId=" + homepageId + ", isValid=" + isValid
				+ ", openid=" + openid + "]";
	}

}
