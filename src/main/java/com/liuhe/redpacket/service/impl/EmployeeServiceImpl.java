package com.liuhe.redpacket.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.liuhe.redpacket.domain.Employee;
import com.liuhe.redpacket.domain.EmployeeRoleLink;
import com.liuhe.redpacket.domain.Role;
import com.liuhe.redpacket.exception.LogicException;
import com.liuhe.redpacket.mapper.EmployeeMapper;
import com.liuhe.redpacket.query.EmployeeQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.service.IEmployeeService;
import com.liuhe.redpacket.utils.ConstUtil;
import com.liuhe.redpacket.utils.MD5Util;
import com.liuhe.redpacket.utils.StatusCode;
import com.liuhe.redpacket.utils.UserContext;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;

	/**
	 * 保存账户的同时， 还要保存中间表数据
	 */
	@CacheEvict
	@Override
	public void save(Employee emp) {
		// 检查用户名
		List<Employee> list = employeeMapper.checkLogin(emp.getUsername());
		if (list != null && list.size() != 0 && list.get(0) != null) {
			throw new LogicException("账户名已存在!", StatusCode.USERNAME_EXISTING);
		}

		// 加密密码
		emp.setPassword(MD5Util.MD5(emp.getPassword()));
		// 设置状态
		emp.setStatus(ConstUtil.EMPLOYEE_NORMAL);
		// 先保存账户，并且拿到ID
		employeeMapper.save(emp);

		// 维护中间表信息
		this.updateEmployeeRoleLink(emp);
	}

	@CacheEvict
	@Override
	public void update(Employee emp) {
		if (emp.getId().equals(ConstUtil.ADMIN_ID)) {
			throw new LogicException("不能修改的管理员账户", StatusCode.USER_NOTEDIT_ADMIN);
		}
		employeeMapper.update(emp);
		this.updateEmployeeRoleLink(emp);
	}

	/**
	 * 更新中间表数据
	 * 
	 * @param emp
	 */
	@CacheEvict
	private void updateEmployeeRoleLink(Employee emp) {
		// 判断账户ID有效
		Long empId = emp.getId();
		if (empId != null) {
			employeeMapper.deleteEmployeeRoleLink(empId);
			// 获取账户相关的角色集合
			List<Role> roles = emp.getRoles();
			if (roles != null && roles.size() > 0) {
				List<EmployeeRoleLink> list = new ArrayList<>();
				for (Role role : roles) {
					Long roleId = role.getId();
					if (roleId != null) {
						list.add(new EmployeeRoleLink(empId, roleId));
					}
				}
				// 保存中间表数据
				employeeMapper.saveEmployeeRoleLink(list);
			}
		}
	}

	// 停用账户
	@CacheEvict
	@Override
	public void leave(Long id) {
		Employee emp = employeeMapper.get(id);
		if (emp.getId().equals(ConstUtil.ADMIN_ID)) {
			throw new LogicException("不能修改的管理员账户", StatusCode.USER_NOTEDIT_ADMIN);
		}
		emp.setStatus(0);
		employeeMapper.update(emp);

	}

	@CacheEvict
	@Override
	public void up(Long id) {
		Employee emp = employeeMapper.get(id);
		if (emp.getId().equals(ConstUtil.ADMIN_ID)) {
			throw new LogicException("不能修改的管理员账户", StatusCode.USER_NOTEDIT_ADMIN);
		}
		emp.setStatus(1);
		employeeMapper.update(emp);

	}

	@CacheEvict
	@Override
	public void delete(Long id) {
		Employee emp = employeeMapper.get(id);
		if (emp.getStatus() != 0) {
			throw new LogicException("不能删除未停用的账户", StatusCode.USER_NOTDELETE);
		}
		if (emp.getId().equals(ConstUtil.ADMIN_ID)) {
			throw new LogicException("不能修改的管理员账户", StatusCode.USER_NOTEDIT_ADMIN);
		}
		employeeMapper.deleteEmployeeRoleLink(id);
		employeeMapper.delete(id);

	}

	@Cacheable
	@Override
	public Employee get(Long id) {
		return employeeMapper.get(id);
	}

	@Cacheable
	@Override
	public List<Employee> getAll() {
		return employeeMapper.getAll();
	}

	@Override
	public Employee findcheckLogin(String username, String password,
			Boolean rememberName, Boolean rememberPassword, String randomCode) {
		// 加密密码
		password = MD5Util.MD5(password);
		// 用验证码进行验证
		String oldRandomCode = (String) UserContext.getRequest().getSession()
				.getAttribute("randomcode_in_session");
		if (!oldRandomCode.equals(randomCode)) {
			throw new LogicException("验证码输入错误！！", StatusCode.RANDOM_ERROR);
		}
		List<Employee> list = employeeMapper.checkLogin(username);
		// 检查用户名
		if (list == null || list.size() == 0 || list.get(0) == null) {
			throw new LogicException("用户名错误！！", StatusCode.USERNAME_ERROR);
		}
		Employee employee = list.get(0);
		// 检查密码
		if (!employee.getPassword().equals(password)) {
			throw new LogicException("密码错误！！", StatusCode.PASSWORD_ERROR);
		}
		// 检查状态
		if (employee.getStatus() != 1) {
			throw new LogicException("账号状态异常,已停用！！", StatusCode.STATE_ERROR);
		}
		// 将用户信息存入cookie中
		System.out.println("rememberName"+rememberName);
		if (rememberName) {
			// 设置编码,防止中文乱码
			try {
				username = URLEncoder.encode(username, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			// 创建带有用户信息的cookie
			Cookie nameCookie = new Cookie("name", username);
			nameCookie.setPath("/");// 设置在整个应用中在请求的时候都会携带Cookie.
			nameCookie.setMaxAge(60 * 60 * 24 * 30);
			// 把cookie对象放入响应中
			UserContext.getResponse().addCookie(nameCookie);
		} else {
			// 清空cookie
			clearCookies();
		}
		System.out.println("rememberPassword"+rememberPassword);
		if (rememberPassword) {
			// 创建带有用户信息的cookie
			Cookie passwordCookie = new Cookie("password", password);
			passwordCookie.setPath("/");// 设置在整个应用中在请求的时候都会携带Cookie.
			passwordCookie.setMaxAge(60 * 60 * 24 * 30);
			// 把cookie对象放入响应中
			UserContext.getResponse().addCookie(passwordCookie);
		}
		// 将employee放入session
		UserContext.setEmployee(employee);
		return employee;

	}

	@Cacheable
	@Override
	public PageResult<Employee> query(EmployeeQuery qu) {
		// 统计查询
		Long total = employeeMapper.queryTotal(qu);
		// 分页查询
		List<Employee> rows = employeeMapper.query(qu);
		return new PageResult<Employee>(rows, qu.getPageSize(),
				qu.getCurrentPage(), total.intValue());
	}

	// 根据用户名和密码查询employee对象
	@Override
	public Employee findByCookie(String name, String password) {
		// 根据用户名到数据库查询有没有这个账户
		Employee employee = new Employee();
		employee.setUsername(name);
		employee.setPassword(password);
		List<Employee> employees = employeeMapper.findByHql(employee);
		if (employees.size() > 0) {
			return employees.get(0);
		}
		return null;
	}

	@Override
	public String findNameByCookie() {
		String name = null;
		// 获取cookie中的保存的用户名
		Cookie[] cookies = UserContext.getRequest().getCookies();
		// 在cookie中查询是否保存账号
		// 获取cookie中的保存的用户名
		if (cookies != null) {
			for (Cookie c : cookies) {
				if ("name".equals(c.getName())) {
					name = c.getValue();
				}
			}
		}
		return name;
	}

	// 清除cookie(创建相同的cookie覆盖原来的)
	private void clearCookies() {
		System.out.println("=======================清除cookies===========================");
		Cookie name = new Cookie("name", "");
		Cookie pass = new Cookie("password", "");
		name.setPath("/");// 设置在整个应用中在请求的时候都会携带Cookie.
		pass.setPath("/");
		name.setMaxAge(0);// 让这个cookie失效
		pass.setMaxAge(0);// 让这个cookie失效
		// 把cookie对象放入响应中
		UserContext.getResponse().addCookie(name);
		UserContext.getResponse().addCookie(pass);
	}

	@Override
	public void logout() {
		System.out.println("=======================注销=============================");
		UserContext.removeEmployee();
		System.out.println("=======================注销2=============================");
		clearCookies();
	}

	@Override
	public void updatePassword(Long id, String oldPassword, String password) {
		Employee employee = employeeMapper.get(id);
		if (employee == null) {
			throw new LogicException("密码错误！！", StatusCode.PASSWORD_ERROR);
		}
		// 加密密码
		oldPassword = MD5Util.MD5(oldPassword);
		// 检查密码
		if (!employee.getPassword().equals(oldPassword)) {
			throw new LogicException("密码错误！！", StatusCode.PASSWORD_ERROR);
		}
		// 加密密码
		password = MD5Util.MD5(password);
		
		employee.setPassword(password);
		employeeMapper.updatePassword(employee);
	}

}
