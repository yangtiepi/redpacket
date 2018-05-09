//package com.liuhe.redpacket.web.oms;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.liuhe.redpacket.domain.User;
//import com.liuhe.redpacket.exception.LogicException;
//import com.liuhe.redpacket.query.PageResult;
//import com.liuhe.redpacket.query.UserQuery;
//import com.liuhe.redpacket.service.IUserService;
//import com.liuhe.redpacket.system.MethodAnnotation;
//import com.liuhe.redpacket.system.MethodAnnotation.ResourceType;
//import com.liuhe.redpacket.utils.result.AjaxResult;
//
//@Controller
//@RequestMapping("/user")
//public class UserController {
//
//	@Autowired
//	IUserService userService;
//
//	@MethodAnnotation(name = "主页", type = ResourceType.用户账号)
//	@RequestMapping("/index")
//	private String index() {
//		return "user/user";
//	}
//
//	@MethodAnnotation(name = "查询", type = ResourceType.用户账号)
//	@RequestMapping("/query")
//	@ResponseBody
//	private PageResult<User> query(UserQuery qu) {
//		PageResult<User> result = userService.query(qu);
//		return result;
//	}
//
//	/**
//	 * 
//	 * @param id
//	 * @return
//	 */
//	@MethodAnnotation(name = "删除", type = ResourceType.用户账号)
//	@RequestMapping("/delete")
//	@ResponseBody
//	private AjaxResult delete(@RequestParam("id")Long id) {
//		AjaxResult ar;
//		try {
//			userService.delete(id);
//			ar = new AjaxResult();
//		} catch (LogicException e) {
//
//			ar = new AjaxResult("删除失败:" + e.getMessage(), e.getErrorCode());
//		}
//		return ar;
//	}
//
//	/**
//	 * 
//	 * 停用操作
//	 */
//	@MethodAnnotation(name = "停用", type = ResourceType.用户账号)
//	@RequestMapping("/leave")
//	@ResponseBody
//	private AjaxResult leave(Long id) {
//		AjaxResult ar;
//		try {
//			userService.leave(id);
//			ar = new AjaxResult();
//		} catch (LogicException e) {
//			ar = new AjaxResult("停用失败:" + e.getMessage(), e.getErrorCode());
//		}
//		return ar;
//	}
//
//	/**
//	 * 
//	 * 启用操作
//	 */
//	@MethodAnnotation(name = "启用", type = ResourceType.用户账号)
//	@RequestMapping("/up")
//	@ResponseBody
//	private AjaxResult up(Long id) {
//		AjaxResult ar;
//		try {
//			userService.up(id);
//
//			ar = new AjaxResult();
//		} catch (LogicException e) {
//
//			ar = new AjaxResult("启用失败:" + e.getMessage(), e.getErrorCode());
//		}
//		return ar;
//	}
//}
