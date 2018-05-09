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
import com.liuhe.redpacket.domain.AdClickLog;
import com.liuhe.redpacket.query.AdClickLogQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.service.IAdClickLogService;
import com.liuhe.redpacket.system.MethodAnnotation;
import com.liuhe.redpacket.system.MethodAnnotation.ResourceType;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@Controller
@RequestMapping("/adClickLog")
public class AdClickLogController{

	@Autowired
	IAdClickLogService adClickLogService;

	/**
	 * 主页
	 * @return
	 */
	@MethodAnnotation(name = "主页", type = ResourceType.广告点击记录)
	@RequestMapping("/index")
	private String adClickLog() {
		return "ad/adClickLog";
	}

	/**
	 * 高级查询+分页
	 * 
	 * @param req
	 * @return
	 */
	@MethodAnnotation(name = "明细查询", type = ResourceType.广告点击记录)
	@RequestMapping("/query")
	@ResponseBody
	private PageResult<AdClickLog> query(AdClickLogQuery qu) {
		PageResult<AdClickLog> list = adClickLogService.query(qu);
		return list;
	}
	/**
	 * 高级查询+分页
	 * 
	 * @param req
	 * @return
	 */
	@MethodAnnotation(name = "统计查询", type = ResourceType.广告点击记录)
	@RequestMapping("/queryGroupAd")
	@ResponseBody
	private PageResult<JSONObject> queryGroupAd(AdClickLogQuery qu) {
		PageResult<JSONObject> list = adClickLogService.queryGroupAd(qu);
		return list;
	}


}
