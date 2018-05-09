/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.web.oms;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.liuhe.redpacket.domain.Homepage;
import com.liuhe.redpacket.exception.LogicException;
import com.liuhe.redpacket.query.HomepageQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.service.IHomepageService;
import com.liuhe.redpacket.system.MethodAnnotation;
import com.liuhe.redpacket.system.MethodAnnotation.ResourceType;
import com.liuhe.redpacket.utils.result.AjaxResult;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@Controller
@RequestMapping("/homepage")
public class HomepageController{

	@Autowired
	IHomepageService homepageService;

	/**
	 * 主页
	 * @return
	 */
	@MethodAnnotation(name = "主页", type = ResourceType.首页)
	@RequestMapping("/index")
	private String Homepage() {
		return "homepage/homepage";
	}
	/**
	 * 分类列表
	 * @return
	 */
	@MethodAnnotation(name = "列表", type = ResourceType.首页)
	@RequestMapping("/list")
	@ResponseBody
	private List<Homepage> showHomepage() {
		List<Homepage> list = homepageService.getAll();
		return list;
	}

	/**
	 * 高级查询+分页
	 * 
	 * @param req
	 * @return
	 */
	@MethodAnnotation(name = "查询", type = ResourceType.首页)
	@RequestMapping("/query")
	@ResponseBody
	private PageResult<Homepage> query(HomepageQuery qu) {
		PageResult<Homepage> list = homepageService.query(qu);
		return list;
	}

	/**
	 * 添加
	 * 
	 * @param id
	 * @return
	 * @throws IOException 
	 */
	@MethodAnnotation(name = "添加", type = ResourceType.首页)
	@RequestMapping("/save")
	@ResponseBody
	private AjaxResult save(@RequestParam("img") MultipartFile img,Homepage homepage) throws IOException {
		AjaxResult ar;
		try {
			if (homepage!=null && homepage.getId()!=null) {
				homepageService.update(img,homepage);
			}else {
				homepageService.save(img,homepage);
			}
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
	@MethodAnnotation(name = "删除", type = ResourceType.首页)
	@RequestMapping("/delete")
	@ResponseBody
	private AjaxResult delete(@RequestParam("id")Long id) {
		AjaxResult ar;
		try {
			homepageService.delete(id);
			ar = new AjaxResult();
		} catch (LogicException e) {
			ar = new AjaxResult(e.getMessage(), e.getErrorCode());
		}
		return ar;
	}

	/**
	 * 停用
	 * @return
	 */
	@MethodAnnotation(name = "停用", type = ResourceType.卡集)
	@RequestMapping("/disable")
	@ResponseBody
	private AjaxResult disable(@RequestParam("id")Long id) {
		AjaxResult ar;
		try {
			homepageService.disable(id);
			ar = new AjaxResult();
		} catch (LogicException e) {
			ar = new AjaxResult(e.getMessage(), e.getErrorCode());
		}
		return ar;
	}
	/**
	 * 启用
	 * @return
	 */
	@MethodAnnotation(name = "启用", type = ResourceType.卡集)
	@RequestMapping("/enable")
	@ResponseBody
	private AjaxResult enable(@RequestParam("id")Long id) {
		AjaxResult ar;
		try {
			homepageService.enable(id);
			ar = new AjaxResult();
		} catch (LogicException e) {
			ar = new AjaxResult(e.getMessage(), e.getErrorCode());
		}
		return ar;
	}
}
