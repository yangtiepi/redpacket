package com.liuhe.redpacket.service;

import java.util.List;

import com.liuhe.redpacket.domain.Employee;
import com.liuhe.redpacket.domain.Permission;
import com.liuhe.redpacket.query.BaseQuery;
import com.liuhe.redpacket.query.PageResult;

public interface IPermissionService {

	/**
	 * 新增一个权限
	 */
	void save(Permission permission);
	/**
	 * 修改一个一个权限
	 */
	void update(Permission permission);
	/**
	 * 删除一个权限
	 */
	void delete(Long id);
	/**
	 * 获得所有的权限列表
	 */
	List<Permission> getAll();
	
	/**
	 * 权限高级查询
	 * @param qu
	 * @return
	 */
	PageResult<Permission> query(BaseQuery qu);
	/**
	 * 根据id的值获得单个权限信息
	 */
	Permission get(Long id);
	/**
	 * 返回所有的权限列表
	 */
	List<Permission> getAllPermission();
	
	Permission getPermissionByResources(String resourceName);
	
	List<Permission> getEmployeePermission(Employee emp);
}
