/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.web.oms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuhe.redpacket.domain.UserCard;
import com.liuhe.redpacket.exception.LogicException;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.UserCardQuery;
import com.liuhe.redpacket.service.IUserCardService;
import com.liuhe.redpacket.system.MethodAnnotation;
import com.liuhe.redpacket.system.MethodAnnotation.ResourceType;
import com.liuhe.redpacket.utils.result.AjaxResult;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@Controller
@RequestMapping("/userCard")
public class UserCardController{

	@Autowired
	IUserCardService UserCardService;

	/**
	 * 主页
	 * @return
	 */
	@MethodAnnotation(name = "主页", type = ResourceType.用户卡片)
	@RequestMapping("/index")
	private String UserCard() {
		return "card/userCard";
	}
	/**
	 * 分类列表
	 * @return
	 */
	@MethodAnnotation(name = "列表", type = ResourceType.用户卡片)
	@RequestMapping("/list")
	@ResponseBody
	private List<UserCard> showUserCard(Long id) {
		List<UserCard> list = UserCardService.getAll();
		return list;
	}

	/**
	 * 高级查询+分页
	 * 
	 * @param req
	 * @return
	 */
	@MethodAnnotation(name = "查询", type = ResourceType.用户卡片)
	@RequestMapping("/query")
	@ResponseBody
	private PageResult<UserCard> query(UserCardQuery qu) {
		PageResult<UserCard> list = UserCardService.query(qu);
		return list;
	}

	/**
	 * 添加
	 * 
	 * @param id
	 * @return
	 */
	@MethodAnnotation(name = "添加", type = ResourceType.用户卡片)
	@RequestMapping("/save")
	@ResponseBody
	private AjaxResult save(UserCard UserCard) {
		AjaxResult ar;
		try {
			UserCardService.save(UserCard);
			ar = new AjaxResult();
		} catch (LogicException e) {
			ar = new AjaxResult(e.getMessage(), e.getErrorCode());
		}
		return ar;
	}
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@MethodAnnotation(name = "删除", type = ResourceType.用户卡片)
	@RequestMapping("/delete")
	@ResponseBody
	private AjaxResult delete(@RequestParam("id")Long id) {
		AjaxResult ar;
		try {
			UserCardService.delete(id);
			ar = new AjaxResult();
		} catch (LogicException e) {
			ar = new AjaxResult(e.getMessage(), e.getErrorCode());
		}
		return ar;
	}

}
