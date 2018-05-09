package com.liuhe.redpacket.web.oms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuhe.redpacket.domain.Employee;
import com.liuhe.redpacket.exception.LogicException;
import com.liuhe.redpacket.query.EmployeeQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.service.IEmployeeService;
import com.liuhe.redpacket.service.IMenuService;
import com.liuhe.redpacket.system.MethodAnnotation;
import com.liuhe.redpacket.system.MethodAnnotation.ResourceType;
import com.liuhe.redpacket.utils.result.AjaxResult;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	IEmployeeService employeeService;

	@Autowired
	IMenuService menuService;

	/**
	 * 在菜单栏中点击用户管理的之后,进入到这个页面的跳转
	 * 
	 * @return
	 */
	@MethodAnnotation(name = "主页", type = ResourceType.系统账号)
	@RequestMapping("/index")
	private String employee() {
		return "employee/employee";
	}

	/**
	 * 高级查询+分页
	 * 
	 * @param req
	 * @return
	 */
	@MethodAnnotation(name = "查询", type = ResourceType.系统账号)
	@RequestMapping("/query")
	@ResponseBody
	private PageResult<Employee> employeeQuery(EmployeeQuery equ) {
		PageResult<Employee> list = employeeService.query(equ);
		return list;
	}

	/**
	 * 编辑后台账户
	 * 
	 * @param User
	 * @param id
	 * @return
	 */
	@MethodAnnotation(name = "编辑", type = ResourceType.系统账号)
	@RequestMapping("/save")
	@ResponseBody
	private AjaxResult save(Employee employee) {
		// 准备返回对象
		AjaxResult ar;
		try {
			if (employee.getId() == null) {
				employeeService.save(employee);
			} else {
				employeeService.update(employee);
			}
			ar = new AjaxResult();

		} catch (LogicException e) {
			// 封装返回结果
			ar = new AjaxResult("操作失败:" + e.getMessage(), e.getErrorCode());
		}
		// 返回
		return ar;
	}
	/**
	 * 编辑后台账户
	 * 
	 * @param User
	 * @param id
	 * @return
	 */
	@MethodAnnotation(name = "修改密码", type = ResourceType.系统账号)
	@RequestMapping("/updatePassword")
	@ResponseBody
	private AjaxResult updatePassword(@RequestParam("id")Long id,@RequestParam("oldPassword")String oldPassword,@RequestParam("password")String password) {
		// 准备返回对象
		AjaxResult ar;
		try {
			employeeService.updatePassword(id,oldPassword,password);
			ar = new AjaxResult();
			
		} catch (LogicException e) {
			// 封装返回结果
			ar = new AjaxResult("操作失败:" + e.getMessage(), e.getErrorCode());
		}
		// 返回
		return ar;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@MethodAnnotation(name = "删除", type = ResourceType.系统账号)
	@RequestMapping("/delete")
	@ResponseBody
	private AjaxResult delete(@RequestParam("id")Long id) {
		AjaxResult ar;
		try {
			employeeService.delete(id);

			ar = new AjaxResult();
		} catch (LogicException e) {

			ar = new AjaxResult("删除失败:" + e.getMessage(), e.getErrorCode());
		}
		return ar;
	}

	/**
	 * 
	 * 停用操作
	 */
	@MethodAnnotation(name = "停用", type = ResourceType.系统账号)
	@RequestMapping("/leave")
	@ResponseBody
	private AjaxResult leave(Long id) {
		AjaxResult ar;
		try {
			employeeService.leave(id);
			ar = new AjaxResult();
		} catch (LogicException e) {
			ar = new AjaxResult("停用失败:" + e.getMessage(), e.getErrorCode());
		}
		return ar;
	}

	/**
	 * 
	 * 启用操作
	 */
	@MethodAnnotation(name = "启用", type = ResourceType.系统账号)
	@RequestMapping("/up")
	@ResponseBody
	private AjaxResult up(Long id) {
		AjaxResult ar;
		try {
			employeeService.up(id);

			ar = new AjaxResult();
		} catch (LogicException e) {

			ar = new AjaxResult("启用失败:" + e.getMessage(), e.getErrorCode());
		}
		return ar;
	}

}
