package com.liuhe.redpacket.web.oms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuhe.redpacket.domain.Permission;
import com.liuhe.redpacket.domain.Role;
import com.liuhe.redpacket.service.IPermissionService;
import com.liuhe.redpacket.service.IRoleService;
import com.liuhe.redpacket.service.IRole_PermissionService;
import com.liuhe.redpacket.utils.result.AjaxResult;

@Controller
@RequestMapping("role")
public class RoleController {

	//注入service接口
	@Autowired
	IRoleService roleService;
	@Autowired
	IPermissionService permissionService;
	IRole_PermissionService role_PermissionService;
//	private static List<Permission> permissions = new ArrayList<>();
	/*
	 * 显示页面的方法
	 */
	@RequestMapping("/index")
	private String roleList(){
		
		return "employee/role";
	}
	
	/*
	 * 角色列表
	 */
	@RequestMapping("/list")
	@ResponseBody
	private List<Role> roleAll(){
		List<Role> list = roleService.getAll();
		return list;
	}
	
	/*
	 * 在编辑角色的时候用来回显数据
	 */
	@RequestMapping("/findPermissions")
	@ResponseBody
	private List<Permission> echo(Long id) {
		Role role = roleService.findPermissions(id);
		List<Permission> list = role.getPermissions();
		return list;
	}
	
	/*
	 * 保存操作的持久化操作
	 * 	有id的就更新
	 * 	没有id的就添加
	 */
//	@RequestMapping("/permissionSave")
//	@ResponseBody
//	private AjaxResult save(Role role){
//		if (role.getId()!=null) {
//			roleService.update(role);
//		}else {
//			roleService.save(role);
//		}
//		return new AjaxResult();
//	private AjaxResult save(Permission permission){
//		permissions.add(permission);
//		return null;
//	}
	
	@RequestMapping("/save")
	@ResponseBody
	private AjaxResult save(Role role){
//		for (Permission permission : permissions) {
//			Role_Permission rp = new Role_Permission();
//			rp.setPermission(permission);
//			rp.setRole(role);
//			//保存关系
//			role_PermissionService.save(rp);
//		}
		if (role.getId()!=null) {
			roleService.update(role);
		}else {
			roleService.save(role);
		}
		return new AjaxResult();
	}
	
	/*
	 * 删除用户传入后台的行
	 */
	@RequestMapping("/delete")
	@ResponseBody
	private String delete(@RequestParam("id")Long id){
		roleService.delete(id);
		return "success";
	}
	
}
