package com.liuhe.redpacket.web.oms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liuhe.redpacket.system.MethodAnnotation;
import com.liuhe.redpacket.system.MethodAnnotation.ResourceType;
/**
 * 系统监控Durid
 * @author ozil
 *
 */
@Controller
@RequestMapping("/monitoring")
public class MonitoringController {

	@MethodAnnotation(name = "主页", type = ResourceType.系统监控)
	@RequestMapping("/index")
	private String index() {
		return "monitoring";
	}
}
