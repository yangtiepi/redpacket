package com.liuhe.redpacket.vo.weixin.receive;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.liuhe.redpacket.vo.weixin.adapter.AdapterCDATA;

@XmlRootElement(name = "item")
public class Item {
	private String PicUrl;

	@XmlElement(name = "PicUrl")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	
}
