package com.liuhe.redpacket.service;

import java.util.List;

import com.liuhe.redpacket.domain.Employee;
import com.liuhe.redpacket.query.EmployeeQuery;
import com.liuhe.redpacket.query.PageResult;

public interface IEmployeeService {
	/**
	 * 保存账户
	 */
	void save(Employee emp);
	/**
	 * 更新账户信息
	 */
	void update(Employee emp);
	/**
	 * 停用账户
	 */
	void leave(Long id);
	/**
	 * 启用账户
	 * @param id
	 */
	void up(Long id);
	/**
	 * 删除账户
	 */
	void delete(Long id);
	/**
	 * 获取单个账户
	 */
	Employee get(Long id);
	/**
	 * 查询所有账户
	 */
	List<Employee> getAll();
	/**
	 * 高级查询
	 */
	PageResult<Employee> query(EmployeeQuery equ);
	/**
	 * 登录检查
	 * @param name
	 * @param password
	 * @return
	 */
	Employee findcheckLogin(String username, String password,Boolean rememberName,Boolean rememberPassword,String randomCode);
	/**
	 * 注销
	 */
	void logout();
	
	String findNameByCookie();
	Employee findByCookie(String name, String password);
	/**
	 * 修改密码
	 * @param id 
	 * @param oldPassword
	 * @param password
	 */
	void updatePassword(Long id, String oldPassword, String password);
}
