package com.liuhe.redpacket.web.oms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuhe.redpacket.domain.Permission;
import com.liuhe.redpacket.query.BaseQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.service.IPermissionService;
import com.liuhe.redpacket.system.MethodAnnotation;
import com.liuhe.redpacket.system.MethodAnnotation.ResourceType;
import com.liuhe.redpacket.utils.result.AjaxResult;

@Controller
@RequestMapping("permission")
public class PermissionController {

	//注入service接口
	@Autowired
	IPermissionService permissionService;
	
	/*
	 * 显示页面的方法
	 */
	@MethodAnnotation(name="主页",type=ResourceType.系统权限)
	@RequestMapping("/index")
	private String index(){
		
		return "employee/permission";
	}
	
	/*
	 * 权限查询
	 */
	@MethodAnnotation(name="查询",type=ResourceType.系统权限)
	@RequestMapping("/query")
	@ResponseBody
	private PageResult<Permission> quert(BaseQuery qu){
		PageResult<Permission> result = permissionService.query(qu);
		return result;
	}
	
	/*
	 * 在角色部分需要得到的所有的权限的列表.这个地方没有关联查询
	 */
	@RequestMapping("/list")
	@ResponseBody
	private List<Permission> permissionAll(){
		List<Permission> list = permissionService.getAllPermission();
		return list;
	}
	
	/*
	 * 保存操作的持久化操作
	 * 	有id的就更新
	 * 	没有id的就添加
	 */
	@MethodAnnotation(name="编辑",type=ResourceType.系统权限)
	@RequestMapping("/save")
	@ResponseBody
	private AjaxResult save(Permission permission){
		if (permission.getId()==null) {
			permissionService.save(permission);
		}else {
			permissionService.update(permission);
		}
		return new AjaxResult();
	}
	
	/*
	 * 删除用户传入后台的行
	 */
	@MethodAnnotation(name="删除",type=ResourceType.系统权限)
	@RequestMapping("/delete")
	@ResponseBody
	private AjaxResult delete(@RequestParam("id")Long id){
		permissionService.delete(id);
		return new AjaxResult();
	}
	
}
