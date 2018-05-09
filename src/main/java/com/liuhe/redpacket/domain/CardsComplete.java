/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

public class CardsComplete implements Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	private Long id;
	private Long userId;
	private Long cardsId;
	private String userName;
	private String openid;
	private String wechat;
	private String code;
	private Integer status;
	private Date completeTime;
	private Date receiveTime;

	private Cards cards;

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

	public void setCardsId(Long value) {
		this.cardsId = value;
	}

	public Long getCardsId() {
		return this.cardsId;
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

	public void setCode(String value) {
		this.code = value;
	}

	public String getCode() {
		return this.code;
	}

	public void setStatus(Integer value) {
		this.status = value;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setCompleteTime(Date value) {
		this.completeTime = value;
	}

	public Date getCompleteTime() {
		return this.completeTime;
	}

	public void setReceiveTime(Date value) {
		this.receiveTime = value;
	}

	public Date getReceiveTime() {
		return this.receiveTime;
	}

	public Cards getCards() {
		return cards;
	}

	public void setCards(Cards cards) {
		this.cards = cards;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("Id", getId()).append("UserId", getUserId())
				.append("CardsId", getCardsId())
				.append("UserName", getUserName())
				.append("Openid", getOpenid()).append("Wechat", getWechat())
				.append("Code", getCode()).append("Status", getStatus())
				.append("CompleteTime", getCompleteTime())
				.append("ReceiveTime", getReceiveTime()).toString();
	}

}
