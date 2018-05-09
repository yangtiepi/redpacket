package com.liuhe.redpacket.domain;

import java.io.Serializable;

/**
 * 角色与用户的关联对象
 * @author ozil
 *
 */
public class AdHomepageLink implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5637708216542607174L;
	private Long adId;
	private Long homepageId;
	public Long getAdId() {
		return adId;
	}
	public void setAdId(Long adId) {
		this.adId = adId;
	}
	public Long getHomepageId() {
		return homepageId;
	}
	public void setHomepageId(Long homepageId) {
		this.homepageId = homepageId;
	}
	@Override
	public String toString() {
		return "AdHomepageLink [adId=" + adId + ", homepageId="
				+ homepageId + "]";
	}
	public AdHomepageLink(Long adId, Long homepageId) {
		this.adId = adId;
		this.homepageId = homepageId;
	}
	public AdHomepageLink() {
	}
	
}
