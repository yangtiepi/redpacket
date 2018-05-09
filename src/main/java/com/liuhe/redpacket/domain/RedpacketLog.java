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


public class RedpacketLog implements Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	private Long id;
	private Long userId;
	private Long adId;
	private Long redpacketId;
	private String redpacketName;
	private String userName;
	private String openid;
	private String wechat;
	private Date receiveTime;
	private Double amount;
	//columns END
	
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
		public void setAdId(Long value) {
			this.adId = value;
		}
		
		public Long getAdId() {
			return this.adId;
		}
		public void setRedpacketId(Long value) {
			this.redpacketId = value;
		}
		
		public Long getRedpacketId() {
			return this.redpacketId;
		}
		public void setRedpacketName(String value) {
			this.redpacketName = value;
		}
		
		public String getRedpacketName() {
			return this.redpacketName;
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
		public void setReceiveTime(Date value) {
			this.receiveTime = value;
		}
		
		public Date getReceiveTime() {
			return this.receiveTime;
		}
		public void setAmount(Double amount2) {
			this.amount = amount2;
		}
		
		public Double getAmount() {
			return this.amount;
		}


	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserId",getUserId())
			.append("AdId",getAdId())
			.append("RedpacketId",getRedpacketId())
			.append("RedpacketName",getRedpacketName())
			.append("UserName",getUserName())
			.append("Openid",getOpenid())
			.append("Wechat",getWechat())
			.append("ReceiveTime",getReceiveTime())
			.append("Amount",getAmount())
			.toString();
	}
	

	
	
}

