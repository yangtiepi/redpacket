package com.liuhe.redpacket.vo.weixin.reply;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.liuhe.redpacket.vo.weixin.adapter.AdapterCDATA;

@XmlRootElement(name = "xml")
public class ReplyTextMessage extends BaseReplyMessage{
	private String content;

	@XmlElement(name = "Content")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
