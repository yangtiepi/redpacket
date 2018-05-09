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
import org.springframework.web.multipart.MultipartFile;

import com.liuhe.redpacket.domain.Ad;
import com.liuhe.redpacket.exception.LogicException;
import com.liuhe.redpacket.query.AdQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.service.IAdService;
import com.liuhe.redpacket.system.MethodAnnotation;
import com.liuhe.redpacket.system.MethodAnnotation.ResourceType;
import com.liuhe.redpacket.utils.result.AjaxResult;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@Controller
@RequestMapping("/ad")
public class AdController{

	@Autowired
	IAdService adService;

	/**
	 * 主页
	 * @return
	 */
	@MethodAnnotation(name = "主页", type = ResourceType.广告主)
	@RequestMapping("/index")
	private String Ad() {
		return "ad/ad";
	}
	/**
	 * 广告列表
	 * @return
	 */
	@MethodAnnotation(name = "列表", type = ResourceType.广告主)
	@RequestMapping("/list")
	@ResponseBody
	private List<Ad> showAd() {
		List<Ad> list = adService.getAll();
		return list;
	}
	/**
	 * 广告列表
	 * @return
	 */
	@MethodAnnotation(name = "未使用", type = ResourceType.广告主)
	@RequestMapping("/listNoUse")
	@ResponseBody
	private List<Ad> listNoUse() {
		List<Ad> list = adService.listNoUse();
		return list;
	}

	/**
	 * 高级查询+分页
	 * 
	 * @param req
	 * @return
	 */
	@MethodAnnotation(name = "查询", type = ResourceType.广告主)
	@RequestMapping("/query")
	@ResponseBody
	private PageResult<Ad> query(AdQuery qu) {
		PageResult<Ad> list = adService.query(qu);
		return list;
	}

	/**
	 * 添加
	 * 
	 * @param id
	 * @return
	 */
	@MethodAnnotation(name = "编辑", type = ResourceType.广告主)
	@RequestMapping("/save")
	@ResponseBody
	private AjaxResult save(@RequestParam("small") MultipartFile small, @RequestParam("img") MultipartFile img, Ad ad) {
		AjaxResult ar;
		try {
			// 判断是新建还是更新
			if (ad != null && ad.getId() != null) {
				adService.update(ad,small,img);
				ar = new AjaxResult();
			} else {
				adService.save(ad,small,img);
				ar = new AjaxResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ar = new AjaxResult("广告主添加失败！！", null);
		}
		return ar;
	}
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@MethodAnnotation(name = "删除", type = ResourceType.广告主)
	@RequestMapping("/delete")
	@ResponseBody
	private AjaxResult delete(@RequestParam("id")Long id) {
		AjaxResult ar;
		try {
			adService.delete(id);
			ar = new AjaxResult();
		} catch (LogicException e) {
			ar = new AjaxResult(e.getMessage(), e.getErrorCode());
		}
		return ar;
	}

}
