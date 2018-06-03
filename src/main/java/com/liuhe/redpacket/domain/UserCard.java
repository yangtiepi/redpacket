/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */


public class UserCard implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    //可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
    //columns START
    private Long id;
    private Long cardId;
    private String openid;
    private Long userId;
    private String wechat;
    private Long DrawLogId;
    private Date receiveTime;
    private Date exchangeTime;
    //columns END

    public void setId(Long value) {
        this.id = value;
    }

    public Long getId() {
        return this.id;
    }

    public void setCardId(Long value) {
        this.cardId = value;
    }

    public Long getCardId() {
        return this.cardId;
    }

    public void setOpenid(String value) {
        this.openid = value;
    }

    public String getOpenid() {
        return this.openid;
    }

    public void setUserId(Long value) {
        this.userId = value;
    }

    public Long getUserId() {
        return this.userId;
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

    public Date getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(Date exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public Long getDrawLogId() {
        return DrawLogId;
    }

    public void setDrawLogId(Long DrawLogId) {
        this.DrawLogId = DrawLogId;
    }

    @Override
    public String toString() {
        return "UserCard [id=" + id + ", cardId=" + cardId + ", openid="
                + openid + ", userId=" + userId + ", wechat=" + wechat
                + ", DrawLogId=" + DrawLogId + ", receiveTime="
                + receiveTime + "]";
    }


}

