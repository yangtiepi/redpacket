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

public class CardQuery extends BaseQuery implements Serializable {
	private static final long serialVersionUID = 3148176768559230877L;

	/** id */
	private java.lang.Long id;
	/** name */
	private java.lang.String name;
	/** name */
	private java.lang.String cardsName;
	/** image */
	private java.lang.String image;
	/** cardsId */
	private java.lang.Long cardsId;
	/** ratio */
	private java.lang.Integer ratio;

	private Integer isDel;

	public java.lang.Long getId() {
		return this.id;
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	public java.lang.String getImage() {
		return this.image;
	}

	public void setImage(java.lang.String value) {
		this.image = value;
	}

	public java.lang.Long getCardsId() {
		return this.cardsId;
	}

	public void setCardsId(java.lang.Long value) {
		this.cardsId = value;
	}

	public java.lang.Integer getRatio() {
		return this.ratio;
	}

	public void setRatio(java.lang.Integer value) {
		this.ratio = value;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	

	public java.lang.String getCardsName() {
		return cardsName;
	}

	public void setCardsName(java.lang.String cardsName) {
		this.cardsName = cardsName;
	}

	@Override
	public String toString() {
		return "CardQuery [id=" + id + ", name=" + name + ", cardsName="
				+ cardsName + ", image=" + image + ", cardsId=" + cardsId
				+ ", ratio=" + ratio + ", isDel=" + isDel + "]";
	}

}
