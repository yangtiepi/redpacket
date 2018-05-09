package com.liuhe.redpacket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.liuhe.redpacket.domain.Employee;
import com.liuhe.redpacket.domain.Permission;
import com.liuhe.redpacket.mapper.PermissionMapper;
import com.liuhe.redpacket.query.BaseQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.service.IPermissionService;

@Service
public class PermissionServiceImpl implements IPermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public void save(Permission permission) {
		permissionMapper.save(permission);
	}

	@Override
	public void update(Permission permission) {
		permissionMapper.update(permission);
	}

	@Override
	public void delete(Long id) {
		permissionMapper.delete(id);
	}

	@Cacheable
	@Override
	public List<Permission> getAll() {
		return permissionMapper.getAll();
	}

	@Cacheable
	@Override
	public List<Permission> getAllPermission() {
		return permissionMapper.getAllPermission();
	}

	@Cacheable
	@Override
	public Permission get(Long id) {
		return permissionMapper.get(id);
	}

	@Cacheable
	@Override
	public Permission getPermissionByResources(String resourceName) {
		return permissionMapper.getPermissionByResources(resourceName);
	}

	@Cacheable
	@Override
	public List<Permission> getEmployeePermission(Employee emp) {
		return permissionMapper.getEmployeePermission(emp.getId());
	}

	@Cacheable
	@Override
	public PageResult<Permission> query(BaseQuery qu) {
		// 统计查询
		Long total = permissionMapper.queryTotal(qu);
		// 分页查询
		List<Permission> rows = permissionMapper.query(qu);
		return new PageResult<Permission>(rows, qu.getPageSize(),
				qu.getCurrentPage(), total.intValue());
	}

}
