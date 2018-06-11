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

import com.liuhe.redpacket.domain.Card;
import com.liuhe.redpacket.exception.LogicException;
import com.liuhe.redpacket.query.CardQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.service.ICardService;
import com.liuhe.redpacket.system.MethodAnnotation;
import com.liuhe.redpacket.system.MethodAnnotation.ResourceType;
import com.liuhe.redpacket.utils.result.AjaxResult;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("/card")
public class CardController {

	@Autowired
	ICardService cardService;

	/**
	 * 主页
	 * 
	 * @return
	 */
	@MethodAnnotation(name = "主页", type = ResourceType.卡片)
	@RequestMapping("/index")
	private String Card() {
		return "card/card";
	}

	/**
	 * 卡片列表
	 * 
	 * @return
	 */
	@MethodAnnotation(name = "列表", type = ResourceType.卡片)
	@RequestMapping("/list")
	@ResponseBody
	private List<Card> showCard() {
		List<Card> list = cardService.getAll();
		return list;
	}

	/**
	 * 高级查询+分页
	 * 
	 * @param qu
	 * @return
	 */
	@MethodAnnotation(name = "查询", type = ResourceType.卡片)
	@RequestMapping("/query")
	@ResponseBody
	private PageResult<Card> query(CardQuery qu) {
		PageResult<Card> list = cardService.query(qu);
		return list;
	}

	/**
	 * 添加
	 * 
	 * @param img
	 * @return
	 * @throws IOException 
	 */
	@MethodAnnotation(name = "添加", type = ResourceType.卡片)
	@RequestMapping("/save")
	@ResponseBody
	private AjaxResult save(@RequestParam("img") MultipartFile img,Card card) throws IOException {
		AjaxResult ar;
		try {
			// 判断是新建还是更新
			if (card != null && card.getId() != null) {
				cardService.update(img,card);
			} else {
				cardService.save(img,card);
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
	@MethodAnnotation(name = "删除", type = ResourceType.卡片)
	@RequestMapping("/delete")
	@ResponseBody
	private AjaxResult delete(@RequestParam("id")Long id) {
		AjaxResult ar;
		try {
			cardService.delete(id);
			ar = new AjaxResult();
		} catch (LogicException e) {
			ar = new AjaxResult(e.getMessage(), e.getErrorCode());
		}
		return ar;
	}

}
