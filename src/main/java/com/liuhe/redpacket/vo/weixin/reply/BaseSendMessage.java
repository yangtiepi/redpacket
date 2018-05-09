package com.liuhe.redpacket.vo.weixin.reply;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.liuhe.redpacket.vo.weixin.adapter.AdapterCDATA;

public class BaseSendMessage {
	private String ToUserName;
	private String MsgType;
	
	@XmlElement(name = "touser")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	
	@XmlElement(name = "msgtype")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getMsgType() {
		return MsgType;
	}
	
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
}
