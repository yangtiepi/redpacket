/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.web.oms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.RedpacketLogQuery;
import com.liuhe.redpacket.service.IRedpacketLogService;
import com.liuhe.redpacket.system.MethodAnnotation;
import com.liuhe.redpacket.system.MethodAnnotation.ResourceType;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@Controller
@RequestMapping("/redpacketLog")
public class RedpacketLogController{

	@Autowired
	IRedpacketLogService redpacketLogService;

	/**
	 * 主页
	 * @return
	 */
	@MethodAnnotation(name = "主页", type = ResourceType.红包统计)
	@RequestMapping("/index")
	private String RedpacketLog() {
		return "redpacket/redpacketLog";
	}
	/**
	 * 高级查询+分页
	 * 
	 * @param req
	 * @return
	 */
	@MethodAnnotation(name = "统计查询", type = ResourceType.红包统计)
	@RequestMapping("/queryGroupAd")
	@ResponseBody
	private PageResult<JSONObject> queryGroupAd(RedpacketLogQuery qu) {
		PageResult<JSONObject> list = redpacketLogService.queryGroupAd(qu);
		return list;
	}
	/**
	 * 领取详情
	 * 
	 * @param req
	 * @return
	 */
	@MethodAnnotation(name = "统计查询", type = ResourceType.红包统计)
	@RequestMapping("/queryInfo")
	@ResponseBody
	private PageResult<JSONObject> queryInfo(RedpacketLogQuery qu) {
		PageResult<JSONObject> list = redpacketLogService.queryInfo(qu);
		return list;
	}

}
