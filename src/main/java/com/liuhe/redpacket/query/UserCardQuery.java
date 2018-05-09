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


public class UserCardQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
		private java.lang.Long id;
	/** cardId */
		private java.lang.Long cardId;
	/** openid */
		private java.lang.String openid;
	/** userId */
		private java.lang.Long userId;
	/** wechat */
		private java.lang.String wechat;
	/** receiveTime */
		private Date receiveTimeBegin;
		private Date receiveTimeEnd;

		public java.lang.Long getId() {
			return this.id;
		}
		
		public void setId(java.lang.Long value) {
			this.id = value;
		}
	
		public java.lang.Long getCardId() {
			return this.cardId;
		}
		
		public void setCardId(java.lang.Long value) {
			this.cardId = value;
		}
	
		public java.lang.String getOpenid() {
			return this.openid;
		}
		
		public void setOpenid(java.lang.String value) {
			this.openid = value;
		}
	
		public java.lang.Long getUserId() {
			return this.userId;
		}
		
		public void setUserId(java.lang.Long value) {
			this.userId = value;
		}
	
		public java.lang.String getWechat() {
			return this.wechat;
		}
		
		public void setWechat(java.lang.String value) {
			this.wechat = value;
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

