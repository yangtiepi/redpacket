package com.liuhe.redpacket.web.oms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuhe.redpacket.domain.Resource;
import com.liuhe.redpacket.service.IResourceService;

@Controller
@RequestMapping("resource")
public class ResourceController {

	//注入service接口
	@Autowired
	IResourceService resourceService;
	
	/*
	 * 查询所有
	 */
	@RequestMapping("/list")
	@ResponseBody
	private List<Resource> roleAll(){
		List<Resource> list = resourceService.getAll();
		return list;
	}
}
