package com.liuhe.redpacket.query;

import org.apache.commons.lang3.StringUtils;

/**
 * 系统异常高级查询条件
 * @author ozil
 *
 */
public class SystemExceptionQuery extends BaseQuery{
	private String startTime; 
	private String endTime; //时间范围
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = StringUtils.isBlank(startTime)?null:startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = StringUtils.isBlank(endTime)?null:endTime;;
	}

	@Override
	public String toString() {
		return super.toString() + startTime + "," + endTime;
	}

}
