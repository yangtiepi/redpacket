/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

public class Homepage implements Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	private Long id;
	private String title;
	private String image;
	private String info;
	private Integer adMaxNum;
	private Date createTime;
	private Integer status;
	private Integer isDel;
	private Date delTime;
	private String qrcode;
	// columns END

	private List<Ad> ads = new ArrayList<>();

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}

	public void setTitle(String value) {
		this.title = value;
	}

	public String getTitle() {
		return this.title;
	}

	public void setImage(String value) {
		this.image = value;
	}

	public String getImage() {
		return this.image;
	}

	public void setInfo(String value) {
		this.info = value;
	}

	public String getInfo() {
		return this.info;
	}

	public void setAdMaxNum(Integer value) {
		this.adMaxNum = value;
	}

	public Integer getAdMaxNum() {
		return this.adMaxNum;
	}

	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setStatus(Integer value) {
		this.status = value;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setIsDel(Integer value) {
		this.isDel = value;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setDelTime(Date value) {
		this.delTime = value;
	}

	public Date getDelTime() {
		return this.delTime;
	}

	public void setQrcode(String value) {
		this.qrcode = value;
	}

	public String getQrcode() {
		return this.qrcode;
	}

	public List<Ad> getAds() {
		return ads;
	}

	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("Id", getId()).append("Title", getTitle())
				.append("Image", getImage()).append("Info", getInfo())
				.append("AdMaxNum", getAdMaxNum())
				.append("CreateTime", getCreateTime())
				.append("Status", getStatus()).append("IsDel", getIsDel())
				.append("DelTime", getDelTime()).append("Qrcode", getQrcode())
				.toString();
	}

}
