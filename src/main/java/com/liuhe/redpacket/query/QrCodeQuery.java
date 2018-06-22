/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.query;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

public class QrCodeQuery extends BaseQuery implements Serializable {
	private static final long serialVersionUID = 3148176768559230877L;
	private String beginTime;
	private String endTime;
	private Integer isUsed;

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}
}
