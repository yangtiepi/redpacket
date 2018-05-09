package com.liuhe.redpacket.web.oms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuhe.redpacket.domain.SystemLog;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.SystemLogQuery;
import com.liuhe.redpacket.service.ISystemLogService;
import com.liuhe.redpacket.system.MethodAnnotation;
import com.liuhe.redpacket.system.MethodAnnotation.ResourceType;

@Controller
@RequestMapping("/systemLog")
public class SystemLogController {
	@Autowired
	private ISystemLogService systemLogService;

	// 系统日志页面
	@MethodAnnotation(name = "主页", type = ResourceType.系统日志)
	@RequestMapping("/index")
	private String index() {
		return "systemLog/systemLog";
	}

	// 返回列表数据(高级查询)
	@MethodAnnotation(name = "查询", type = ResourceType.系统日志)
	@RequestMapping("/query")
	@ResponseBody
	private PageResult<SystemLog> query(SystemLogQuery q) {
		return systemLogService.query(q);
	}
}
