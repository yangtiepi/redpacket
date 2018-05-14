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


public class Cards implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    //可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
    //columns START
    private Long id;
    private String name;
    private String info;
    private String image;
    private Date createTime;
    private Integer status;
    private Integer isDel;
    private Date delTime;
    private Date beginTime;
    private Date endTime;

    private Integer completeNum;
    //columns END

    public void setId(Long value) {
        this.id = value;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    public void setInfo(String value) {
        this.info = value;
    }

    public String getInfo() {
        return this.info;
    }

    public void setImage(String value) {
        this.image = value;
    }

    public String getImage() {
        return this.image;
    }

    public void setCreateTime(Date value) {
        this.createTime = value;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setStatus(Integer value) {
        this.status = value;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setIsDel(Integer value) {
        this.isDel = value;
    }

    public Integer getIsDel() {
        return this.isDel;
    }

    public void setDelTime(Date value) {
        this.delTime = value;
    }

    public Date getDelTime() {
        return this.delTime;
    }

    public void setBeginTime(Date value) {
        this.beginTime = value;
    }

    public Date getBeginTime() {
        return this.beginTime;
    }

    public void setEndTime(Date value) {
        this.endTime = value;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public Integer getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(Integer completeNum) {
        this.completeNum = completeNum;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Id", getId())
                .append("Name", getName())
                .append("Info", getInfo())
                .append("Image", getImage())
                .append("CreateTime", getCreateTime())
                .append("Status", getStatus())
                .append("IsDel", getIsDel())
                .append("DelTime", getDelTime())
                .append("BeginTime", getBeginTime())
                .append("EndTime", getEndTime())
                .append("completeNum", getCompleteNum())
                .toString();
    }


}

