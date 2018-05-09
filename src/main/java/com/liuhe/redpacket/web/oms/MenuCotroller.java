package com.liuhe.redpacket.web.oms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuhe.redpacket.domain.Menu;
import com.liuhe.redpacket.service.IMenuService;

@Controller
public class MenuCotroller {
	@Autowired
	IMenuService menuService;
	
	/**
	 * 在页面加载的时候,需要的菜单的数据
	 */
	@RequestMapping("/menu")
	@ResponseBody
	private Menu[] menu(HttpServletRequest req) {
		Menu[] menus = menuService.getMenuResult();
		return menus;
	}
}
