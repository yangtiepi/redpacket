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


public class WithdrawLog implements Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	private Long id;
	private String userName;
	private String openid;
	private String orderNo;
	private Date receiveTime;
	private Double amount;
	//columns END
	
		public void setId(Long value) {
			this.id = value;
		}
		
		public Long getId() {
			return this.id;
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
		public void setOrderNo(String value) {
			this.orderNo = value;
		}
		
		public String getOrderNo() {
			return this.orderNo;
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
			.append("UserName",getUserName())
			.append("Openid",getOpenid())
			.append("OrderNo",getOrderNo())
			.append("ReceiveTime",getReceiveTime())
			.append("Amount",getAmount())
			.toString();
	}

	public WithdrawLog(String userName, String openid, String orderNo,
			Date receiveTime, Double amount) {
		super();
		this.userName = userName;
		this.openid = openid;
		this.orderNo = orderNo;
		this.receiveTime = receiveTime;
		this.amount = amount;
	}
	

	
	
}

