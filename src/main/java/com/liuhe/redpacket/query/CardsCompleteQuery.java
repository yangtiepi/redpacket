/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.query;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class CardsCompleteQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
		private java.lang.Long id;
	/** userId */
		private java.lang.Long userId;
	/** cardsId */
		private java.lang.Long cardsId;
	/** userName */
		private java.lang.String userName;
	/** openid */
		private java.lang.String openid;
	/** wechat */
		private java.lang.String wechat;
	/** code */
		private java.lang.String code;
	/** status */
		private java.lang.Integer status;
	/** completeTime */
		private Date completeTimeBegin;
		private Date completeTimeEnd;
	/** receiveTime */
		private Date receiveTimeBegin;
		private Date receiveTimeEnd;

		public java.lang.Long getId() {
			return this.id;
		}
		
		public void setId(java.lang.Long value) {
			this.id = value;
		}
	
		public java.lang.Long getUserId() {
			return this.userId;
		}
		
		public void setUserId(java.lang.Long value) {
			this.userId = value;
		}
	
		public java.lang.Long getCardsId() {
			return this.cardsId;
		}
		
		public void setCardsId(java.lang.Long value) {
			this.cardsId = value;
		}
	
		public java.lang.String getUserName() {
			return this.userName;
		}
		
		public void setUserName(java.lang.String value) {
			this.userName = value;
		}
	
		public java.lang.String getOpenid() {
			return this.openid;
		}
		
		public void setOpenid(java.lang.String value) {
			this.openid = value;
		}
	
		public java.lang.String getWechat() {
			return this.wechat;
		}
		
		public void setWechat(java.lang.String value) {
			this.wechat = value;
		}
	
		public java.lang.String getCode() {
			return this.code;
		}
		
		public void setCode(java.lang.String value) {
			this.code = value;
		}
	
		public java.lang.Integer getStatus() {
			return this.status;
		}
		
		public void setStatus(java.lang.Integer value) {
			this.status = value;
		}
	
		public Date getCompleteTimeBegin() {
			return this.completeTimeBegin;
		}
		
		public void setCompleteTimeBegin(Date value) {
			this.completeTimeBegin = value;
		}	
		
		
		public Date getCompleteTimeEnd() {
			return this.completeTimeEnd;
		}
		
		public void setCompleteTimeEnd(Date value) {
			this.completeTimeEnd = value;
		}
		
	
		public Date getReceiveTimeBegin() {
			return this.receiveTimeBegin;
		}
		
		public void setReceiveTimeBegin(Date value) {
			this.receiveTimeBegin = value;
		}	
		
		
		public Date getReceiveTimeEnd() {
			return this.receiveTimeEnd;
		}
		
		public void setReceiveTimeEnd(Date value) {
			this.receiveTimeEnd = value;
		}
		
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

