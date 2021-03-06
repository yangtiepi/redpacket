package com.liuhe.redpacket.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.liuhe.redpacket.utils.ConstUtil;

/**
 * 后台用户
 *
 */
public class Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8381786482889855880L;
	private Long id;
	private String username;
	private String realName;
	private String password;
	private Date loginTime;//最后登录时间
	private Integer status = ConstUtil.EMPLOYEE_NORMAL;//状态 0停用,1正常

	/**
	 * 账户 VS 角色 1 多 多 1
	 * 
	 * 多 VS 多
	 * 
	 * @return
	 */
	private List<Role> roles = new ArrayList<>();

	private List<Permission> permissions = new ArrayList<>();
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", username=" + username + ", realName="
				+ realName + ", password=" + password + ", loginTime="
				+ loginTime + ", status=" + status + ", roles=" + roles
				+ ", permissions=" + permissions + "]";
	}
}
