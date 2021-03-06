package com.liuhe.redpacket.domain;

import java.awt.Menu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理角色的类
 * 
 * @author tian
 * 
 */
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1489929252090250414L;
	private Long id;
	private String name;
	private String sn;
	private List<Permission> permissions = new ArrayList<Permission>();
	// 定义一个角色能够访问的菜单
	private List<Menu> menus = new ArrayList<Menu>();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", sn=" + sn
				+ ", permissions=" + permissions + ", menus=" + menus + "]";
	}
}
