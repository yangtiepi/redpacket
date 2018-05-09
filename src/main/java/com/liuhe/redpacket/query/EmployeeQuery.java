package com.liuhe.redpacket.query;


/**
 * 封装查询对象
 */
public class EmployeeQuery extends BaseQuery{
	private String employeeName;
	private Long roleId;
	private Integer status=-99;
	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return super.toString() + employeeName + "," + roleId + "," + status;
	}
	
}
