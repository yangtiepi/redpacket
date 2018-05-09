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

public class Card implements Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	private Long id;
	private String name;
	private String image;
	private Long cardsId;
	private Double ratio;
	private Integer isDel;
	private Date delTime;

	private Cards cards;

	// columns END

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}

	public void setImage(String value) {
		this.image = value;
	}

	public String getImage() {
		return this.image;
	}

	public void setCardsId(Long value) {
		this.cardsId = value;
	}

	public Long getCardsId() {
		return this.cardsId;
	}

	public void setRatio(Double value) {
		this.ratio = value;
	}

	public Double getRatio() {
		return this.ratio;
	}

	public Cards getCards() {
		return cards;
	}

	public void setCards(Cards cards) {
		this.cards = cards;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Date getDelTime() {
		return delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", name=" + name + ", image=" + image
				+ ", cardsId=" + cardsId + ", ratio=" + ratio + ", isDel="
				+ isDel + ", delTime=" + delTime + ", cards=" + cards + "]";
	}

}
