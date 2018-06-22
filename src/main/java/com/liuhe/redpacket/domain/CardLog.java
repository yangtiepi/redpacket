/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */


public class CardLog implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    //可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
    //columns START
    private Long id;
    private Long userId;
    private Long qrCodeId;
    private Long cardId;
    private String cardName;
    private String userName;
    private String openid;
    private String wechat;
    private Date receiveTime;

    private Integer type;//0获取，1兑换
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

    public void setQrCodeId(Long value) {
        this.qrCodeId = value;
    }

    public Long getQrCodeId() {
        return this.qrCodeId;
    }

    public void setCardId(Long value) {
        this.cardId = value;
    }

    public Long getCardId() {
        return this.cardId;
    }

    public void setCardName(String value) {
        this.cardName = value;
    }

    public String getCardName() {
        return this.cardName;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

