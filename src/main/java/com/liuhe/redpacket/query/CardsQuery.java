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

public class CardsQuery extends BaseQuery implements Serializable {
	private static final long serialVersionUID = 3148176768559230877L;
	private String openid;
	private Integer status;
	private Integer isDel;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	@Override
	public String toString() {
		return "CardsQuery [openid=" + openid + ", status=" + status
				+ ", isDel=" + isDel + "]";
	}
}
