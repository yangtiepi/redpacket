package com.liuhe.redpacket.query;



public class UserQuery extends BaseQuery{
	private String username;
	private Integer status = -99;
	private Integer userType = -1;
	private String useCode;

	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	

	public String getUseCode() {
		return useCode;
	}

	public void setUseCode(String useCode) {
		this.useCode = useCode;
	}

	@Override
	public String toString() {
		return super.toString() + username + "," + status + "," + userType + "," + useCode;
	}

}
