/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.web.oms;

import com.liuhe.redpacket.domain.DrawLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.DrawLogQuery;
import com.liuhe.redpacket.service.IDrawLogService;
import com.liuhe.redpacket.system.MethodAnnotation;
import com.liuhe.redpacket.system.MethodAnnotation.ResourceType;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */


@Controller
@RequestMapping("/drawLog")
public class DrawLogController{

	@Autowired
	IDrawLogService DrawLogService;

	/**
	 * 主页
	 * @return
	 */
	@MethodAnnotation(name = "主页", type = ResourceType.红包统计)
	@RequestMapping("/index")
	private String DrawLog() {
		return "draw/drawLog";
	}
	/**
	 * 高级查询+分页
	 *
	 * @param qu
	 * @return
	 */
	@MethodAnnotation(name = "统计查询", type = ResourceType.红包统计)
	@RequestMapping("/query")
	@ResponseBody
	private PageResult<DrawLog> query(DrawLogQuery qu) {
		qu.setType(1);
		PageResult<DrawLog> list = DrawLogService.query(qu);
		return list;
	}


}
