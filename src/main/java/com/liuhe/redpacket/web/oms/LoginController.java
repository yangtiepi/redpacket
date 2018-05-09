package com.liuhe.redpacket.web.oms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuhe.redpacket.domain.Employee;
import com.liuhe.redpacket.domain.Permission;
import com.liuhe.redpacket.exception.LogicException;
import com.liuhe.redpacket.service.IEmployeeService;
import com.liuhe.redpacket.service.IPermissionService;
import com.liuhe.redpacket.utils.UserContext;
import com.liuhe.redpacket.utils.result.AjaxResult;

@Controller
public class LoginController {
	@Autowired
	private IEmployeeService service;
	@Autowired
	private IPermissionService permissionService;

	@RequestMapping("/checkLogin")
	@ResponseBody
	private AjaxResult checkLogin(@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("rememberName") Boolean rememberName,
			@RequestParam("rememberPassword") Boolean rememberPassword,
			@RequestParam("randomCode") String randomCode) {

		AjaxResult ajaxResult = null;
		try {
			// 检查登陆
			Employee emp = service.findcheckLogin(username, password,
					rememberName, rememberPassword, randomCode);
			// 将权限放入session中
			List<Permission> permission = permissionService
					.getEmployeePermission(emp);
			UserContext.setEmployeePermission(permission);
			// 注册session
			UserContext.setEmployee(emp);
			// 封装响应信息
			ajaxResult = new AjaxResult();
		} catch (LogicException e) {
			// 封装响应信息
			ajaxResult = new AjaxResult(e.getMessage(), e.getErrorCode());
		}
		return ajaxResult;
	}

	@RequestMapping("/logout")
	private String logout() {
		service.logout();
		return "redirect:/index.jsp";
	}

	/**
	 * 进入首页
	 */
	@RequestMapping("/main")
	private String mian() {
		return "forward:/WEB-INF/pages/main.jsp";
	}
}
