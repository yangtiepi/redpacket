/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.query;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

public class RedpacketQuery extends BaseQuery implements Serializable {
	private static final long serialVersionUID = 3148176768559230877L;

	/** id */
	private java.lang.Long id;
	/** name */
	private java.lang.String name;
	/** info */
	private java.lang.String info;
	/** cardsId */
	private java.lang.Long cardsId;
	/** min */
	private java.lang.Long min;
	/** max */
	private java.lang.Long max;
	/** qrcode */
	private java.lang.String qrcode;

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

	public java.lang.String getInfo() {
		return this.info;
	}

	public void setInfo(java.lang.String value) {
		this.info = value;
	}

	public java.lang.Long getCardsId() {
		return this.cardsId;
	}

	public void setCardsId(java.lang.Long value) {
		this.cardsId = value;
	}

	public java.lang.Long getMin() {
		return this.min;
	}

	public void setMin(java.lang.Long value) {
		this.min = value;
	}

	public java.lang.Long getMax() {
		return this.max;
	}

	public void setMax(java.lang.Long value) {
		this.max = value;
	}

	public java.lang.String getQrcode() {
		return this.qrcode;
	}

	public void setQrcode(java.lang.String value) {
		this.qrcode = value;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
