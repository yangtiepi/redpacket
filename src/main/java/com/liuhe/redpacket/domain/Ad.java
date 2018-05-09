/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

public class Ad implements Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	private Long id;
	private String title;
	private String smallImg;
	private String image;
	private String content;
	private String info;
	private String url;
	private Integer sort;
	private Integer clickedNum;
	private Integer clickableNum;
	private Integer periodDay;
	private Date beginTime;
	private Date endTime;
	private Date createTime;
	private Integer status;
	private Integer isDel;
	private Date delTime;
	private String qrcode;
	private Long homepageId;
	private Long redpacketId;

	private Redpacket redpacket;
	private List<Homepage> homepages;

	// columns END

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

	public void setSmallImg(String value) {
		this.smallImg = value;
	}

	public String getSmallImg() {
		return this.smallImg;
	}

	public void setImage(String value) {
		this.image = value;
	}

	public String getImage() {
		return this.image;
	}

	public void setContent(String value) {
		this.content = value;
	}

	public String getContent() {
		return this.content;
	}

	public void setInfo(String value) {
		this.info = value;
	}

	public String getInfo() {
		return this.info;
	}

	public void setUrl(String value) {
		this.url = value;
	}

	public String getUrl() {
		return this.url;
	}

	public void setSort(Integer value) {
		this.sort = value;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setClickedNum(Integer value) {
		this.clickedNum = value;
	}

	public Integer getClickedNum() {
		return this.clickedNum;
	}

	public void setClickableNum(Integer value) {
		this.clickableNum = value;
	}

	public Integer getClickableNum() {
		return this.clickableNum;
	}

	public void setPeriodDay(Integer value) {
		this.periodDay = value;
	}

	public Integer getPeriodDay() {
		return this.periodDay;
	}

	public void setBeginTime(Date value) {
		this.beginTime = value;
	}

	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setEndTime(Date value) {
		this.endTime = value;
	}

	public Date getEndTime() {
		return this.endTime;
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

	public Long getHomepageId() {
		return homepageId;
	}

	public void setHomepageId(Long homepageId) {
		this.homepageId = homepageId;
	}

	public Long getRedpacketId() {
		return redpacketId;
	}

	public void setRedpacketId(Long redpacketId) {
		this.redpacketId = redpacketId;
	}

	public Redpacket getRedpacket() {
		return redpacket;
	}

	public void setRedpacket(Redpacket redpacket) {
		this.redpacket = redpacket;
	}

	public List<Homepage> getHomepages() {
		return homepages;
	}

	public void setHomepages(List<Homepage> homepages) {
		this.homepages = homepages;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("Id", getId()).append("Title", getTitle())
				.append("SmallImg", getSmallImg()).append("Image", getImage())
				.append("Content", getContent()).append("Info", getInfo())
				.append("Url", getUrl()).append("Sort", getSort())
				.append("ClickedNum", getClickedNum())
				.append("ClickableNum", getClickableNum())
				.append("PeriodDay", getPeriodDay())
				.append("BeginTime", getBeginTime())
				.append("EndTime", getEndTime())
				.append("CreateTime", getCreateTime())
				.append("Status", getStatus()).append("IsDel", getIsDel())
				.append("DelTime", getDelTime()).append("Qrcode", getQrcode())
				.append("HomepageId", getHomepageId()).toString();
	}

}
