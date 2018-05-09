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


public class HomepageQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
		private java.lang.Long id;
	/** title */
		private java.lang.String title;
	/** image */
		private java.lang.String image;
	/** info */
		private java.lang.String info;
	/** adMaxNum */
		private java.lang.Integer adMaxNum;
	/** createTime */
		private Date createTimeBegin;
		private Date createTimeEnd;
	/** status */
		private java.lang.Integer status;
	/** isDel */
		private java.lang.Integer isDel;
	/** delTime */
		private Date delTimeBegin;
		private Date delTimeEnd;
	/** qrcode */
		private java.lang.String qrcode;

		public java.lang.Long getId() {
			return this.id;
		}
		
		public void setId(java.lang.Long value) {
			this.id = value;
		}
	
		public java.lang.String getTitle() {
			return this.title;
		}
		
		public void setTitle(java.lang.String value) {
			this.title = value;
		}
	
		public java.lang.String getImage() {
			return this.image;
		}
		
		public void setImage(java.lang.String value) {
			this.image = value;
		}
	
		public java.lang.String getInfo() {
			return this.info;
		}
		
		public void setInfo(java.lang.String value) {
			this.info = value;
		}
	
		public java.lang.Integer getAdMaxNum() {
			return this.adMaxNum;
		}
		
		public void setAdMaxNum(java.lang.Integer value) {
			this.adMaxNum = value;
		}
	
		public Date getCreateTimeBegin() {
			return this.createTimeBegin;
		}
		
		public void setCreateTimeBegin(Date value) {
			this.createTimeBegin = value;
		}	
		
		
		public Date getCreateTimeEnd() {
			return this.createTimeEnd;
		}
		
		public void setCreateTimeEnd(Date value) {
			this.createTimeEnd = value;
		}
		
	
		public java.lang.Integer getStatus() {
			return this.status;
		}
		
		public void setStatus(java.lang.Integer value) {
			this.status = value;
		}
	
		public java.lang.Integer getIsDel() {
			return this.isDel;
		}
		
		public void setIsDel(java.lang.Integer value) {
			this.isDel = value;
		}
	
		public Date getDelTimeBegin() {
			return this.delTimeBegin;
		}
		
		public void setDelTimeBegin(Date value) {
			this.delTimeBegin = value;
		}	
		
		
		public Date getDelTimeEnd() {
			return this.delTimeEnd;
		}
		
		public void setDelTimeEnd(Date value) {
			this.delTimeEnd = value;
		}
		
	
		public java.lang.String getQrcode() {
			return this.qrcode;
		}
		
		public void setQrcode(java.lang.String value) {
			this.qrcode = value;
		}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

