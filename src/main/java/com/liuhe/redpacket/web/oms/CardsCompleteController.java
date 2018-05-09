/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.web.oms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuhe.redpacket.domain.CardsComplete;
import com.liuhe.redpacket.exception.LogicException;
import com.liuhe.redpacket.query.CardsCompleteQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.service.ICardsCompleteService;
import com.liuhe.redpacket.system.MethodAnnotation;
import com.liuhe.redpacket.system.MethodAnnotation.ResourceType;
import com.liuhe.redpacket.utils.result.AjaxResult;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@Controller
@RequestMapping("/cardsComplete")
public class CardsCompleteController{

	@Autowired
	ICardsCompleteService cardsCompleteService;

	/**
	 * 主页
	 * @return
	 */
	@MethodAnnotation(name = "主页", type = ResourceType.集卡兑换)
	@RequestMapping("/index")
	private String CardsComplete() {
		return "card/cardsComplete";
	}
	/**
	 * 完成领取
	 * @return
	 */
	@MethodAnnotation(name = "完成领取", type = ResourceType.集卡兑换)
	@RequestMapping("/receice")
	@ResponseBody
	private AjaxResult receice(@RequestParam("id")Long id) {
		AjaxResult ar;
		try {
			cardsCompleteService.receice(id);
			ar = new AjaxResult();
		} catch (LogicException e) {
			ar = new AjaxResult(e.getMessage(), e.getErrorCode());
		}
		return ar;
	}

	/**
	 * 高级查询+分页
	 * 
	 * @param req
	 * @return
	 */
	@MethodAnnotation(name = "查询", type = ResourceType.集卡兑换)
	@RequestMapping("/query")
	@ResponseBody
	private PageResult<CardsComplete> query(CardsCompleteQuery qu) {
		PageResult<CardsComplete> list = cardsCompleteService.query(qu);
		return list;
	}

	/**
	 * 添加
	 * 
	 * @param id
	 * @return
	 */
	@MethodAnnotation(name = "添加", type = ResourceType.集卡兑换)
	@RequestMapping("/save")
	@ResponseBody
	private AjaxResult save(CardsComplete CardsComplete) {
		AjaxResult ar;
		try {
			cardsCompleteService.save(CardsComplete);
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
	@MethodAnnotation(name = "删除", type = ResourceType.集卡兑换)
	@RequestMapping("/delete")
	@ResponseBody
	private AjaxResult delete(@RequestParam("id")Long id) {
		AjaxResult ar;
		try {
			cardsCompleteService.delete(id);
			ar = new AjaxResult();
		} catch (LogicException e) {
			ar = new AjaxResult(e.getMessage(), e.getErrorCode());
		}
		return ar;
	}

}
