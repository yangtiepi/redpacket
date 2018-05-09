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

public class Redpacket implements Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	private Long id;
	private String name;
	private String info;
	private Long cardsId;
	private Double min;
	private Double max;
	private String qrcode;
	private Integer status;
	private Integer isDel;
	private Date delTime;

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

	public void setInfo(String value) {
		this.info = value;
	}

	public String getInfo() {
		return this.info;
	}

	public void setCardsId(Long value) {
		this.cardsId = value;
	}

	public Long getCardsId() {
		return this.cardsId;
	}

	public void setMin(Double value) {
		this.min = value;
	}

	public Double getMin() {
		return this.min;
	}

	public void setMax(Double value) {
		this.max = value;
	}

	public Double getMax() {
		return this.max;
	}

	public void setQrcode(String value) {
		this.qrcode = value;
	}

	public String getQrcode() {
		return this.qrcode;
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

	public Date getDelTime() {
		return delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}

	@Override
	public String toString() {
		return "Redpacket [id=" + id + ", name=" + name + ", info=" + info
				+ ", cardsId=" + cardsId + ", min=" + min + ", max=" + max
				+ ", qrcode=" + qrcode + ", status=" + status + ", isDel="
				+ isDel + ", delTime=" + delTime + "]";
	}

}
