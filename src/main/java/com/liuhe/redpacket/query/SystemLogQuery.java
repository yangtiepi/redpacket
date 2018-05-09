package com.liuhe.redpacket.query;

import org.apache.commons.lang3.StringUtils;

/**
 * 系统日志高级查询条件
 * @author ozil
 *
 */
public class SystemLogQuery extends BaseQuery{
	private String startTime; 
	private String endTime; //时间范围
	
	private String opName;//操作信息
	
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
		this.endTime = StringUtils.isBlank(endTime)?null:endTime;
	}
	
	

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	@Override
	public String toString() {
		return super.toString() + startTime + ", " + endTime + ", " + opName;
	}

}
