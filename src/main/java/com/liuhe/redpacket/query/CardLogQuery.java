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

public class CardLogQuery extends BaseQuery implements Serializable {
	private static final long serialVersionUID = 3148176768559230877L;

	private Long qrCodeId;
	private Long cardId;
	private String openid;
	private String beginTime;
	private String endTime;
	private Integer type;

	public Long getQrCodeId() {
		return qrCodeId;
	}

	public void setQrCodeId(Long qrCodeId) {
		this.qrCodeId = qrCodeId;
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

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
