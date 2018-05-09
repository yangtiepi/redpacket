package com.liuhe.redpacket.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典
 * 
 * @author ozil
 */
public class SystemDictionary implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6837926530896827776L;
	private Long id;
	private String sn;// 数据字典编号
	private String key;// 数据字典key，用于获取数据字典明细，不可更改
	private String name;// 数据字典名
	private String intro;// 字典目录简介
	private Integer status;// 状态 1正常 0停用

	// 字典明细 一对多
	private List<SystemDictionaryItem> details = new ArrayList<SystemDictionaryItem>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<SystemDictionaryItem> getDetails() {
		return details;
	}

	public void setDetails(List<SystemDictionaryItem> details) {
		this.details = details;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return id + ", " + sn + ", " + key + ", " + name + ", " + intro + ", "
				+ status + ", " + details;
	}

}
