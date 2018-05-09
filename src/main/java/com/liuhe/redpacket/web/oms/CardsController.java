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

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.domain.Cards;
import com.liuhe.redpacket.exception.LogicException;
import com.liuhe.redpacket.query.CardsQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.service.ICardsService;
import com.liuhe.redpacket.system.MethodAnnotation;
import com.liuhe.redpacket.system.MethodAnnotation.ResourceType;
import com.liuhe.redpacket.utils.result.AjaxResult;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("/cards")
public class CardsController {

	@Autowired
	ICardsService cardsService;

	/**
	 * 主页
	 * 
	 * @return
	 */
	@MethodAnnotation(name = "主页", type = ResourceType.卡集)
	@RequestMapping("/index")
	private String Cards() {
		return "card/cards";
	}

	/**
	 * 卡集列表
	 * 
	 * @return
	 */
	@MethodAnnotation(name = "列表", type = ResourceType.卡集)
	@RequestMapping("/list")
	@ResponseBody
	private List<Cards> showCards() {
		List<Cards> list = cardsService.getAll();
		return list;
	}
	/**
	 * 卡集列表(有卡片概率的)
	 * 
	 * @return
	 */
	@MethodAnnotation(name = "列表", type = ResourceType.卡集)
	@RequestMapping("/getOfRatio")
	@ResponseBody
	private List<JSONObject> getOfRatio(@RequestParam("cardId")Long cardId) {
		List<JSONObject> list = cardsService.getOfRatio(cardId);
		return list;
	}

	/**
	 * 高级查询+分页
	 * 
	 * @param req
	 * @return
	 */
	@MethodAnnotation(name = "查询", type = ResourceType.卡集)
	@RequestMapping("/query")
	@ResponseBody
	private PageResult<Cards> query(CardsQuery qu) {
		PageResult<Cards> list = cardsService.query(qu);
		return list;
	}

	/**
	 * 添加
	 * 
	 * @param id
	 * @return
	 */
	@MethodAnnotation(name = "添加", type = ResourceType.卡集)
	@RequestMapping("/save")
	@ResponseBody
	private AjaxResult save(Cards cards) {
		AjaxResult ar;
		try {
			// 判断是新建还是更新
			if (cards != null && cards.getId() != null) {
				cardsService.update(cards);
			} else {
				cardsService.save(cards);
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
	@MethodAnnotation(name = "删除", type = ResourceType.卡集)
	@RequestMapping("/delete")
	@ResponseBody
	private AjaxResult delete(@RequestParam("id")Long id) {
		AjaxResult ar;
		try {
			cardsService.delete(id);
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
			cardsService.disable(id);
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
			cardsService.enable(id);
			ar = new AjaxResult();
		} catch (LogicException e) {
			ar = new AjaxResult(e.getMessage(), e.getErrorCode());
		}
		return ar;
	}

}
