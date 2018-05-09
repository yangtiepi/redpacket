package com.liuhe.redpacket.web.mobile;

/**
 * 客户相关的接口
 */
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.exception.LogicException;
import com.liuhe.redpacket.service.IAdService;
import com.liuhe.redpacket.service.ICardService;
import com.liuhe.redpacket.service.ICardsService;
import com.liuhe.redpacket.service.IHomepageService;
import com.liuhe.redpacket.service.IRedpacketService;
import com.liuhe.redpacket.service.IUserService;
import com.liuhe.redpacket.utils.result.AjaxResult;

@Controller
@RequestMapping("mobile")
public class MobileController {
	@Autowired
	private IHomepageService homepageService;
	@Autowired
	private IAdService adService;
	@Autowired
	private ICardsService cardsService;
	@Autowired
	private ICardService cardService;

	@Autowired
	private IRedpacketService redpacketService;
	@Autowired
	private IUserService userService;

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping("/homepage")
	public ModelAndView homepage(@RequestParam("homepageId") Long homepageId,
			@RequestParam("page") Integer page, HttpServletRequest request) {
		String openid = (String) request.getSession().getAttribute("openid");
		Map<String, Object> model = homepageService.mobileIndex(homepageId,
				page, openid);
		return new ModelAndView("mobile/homepage", model);
	}

	/**
	 * 广告首页
	 * 
	 * @return
	 */
	@RequestMapping("/ad")
	public ModelAndView ad(@RequestParam("adId") Long adId,@RequestParam("homepageId") Long homepageId,
			HttpServletRequest request) {
		String openid = (String) request.getSession().getAttribute("openid");
		String nickname = (String) request.getSession()
				.getAttribute("nickname");
		Map<String, Object> model = adService.mobileIndex(adId,homepageId, openid,
				nickname);
		return new ModelAndView("mobile/ad", model);
	}

	/**
	 * 集卡列表
	 * 
	 * @return
	 */
	@RequestMapping("/cardsList")
	public ModelAndView cardsList(HttpServletRequest request) {
		String openid = (String) request.getSession().getAttribute("openid");
		Map<String, Object> model = cardsService.mobileCardsList(openid);
		return new ModelAndView("mobile/cardsList", model);
	}

	/**
	 * 我的卡片
	 * 
	 * @return
	 */
	@RequestMapping("/userCard")
	public ModelAndView userCard(@RequestParam("cardsId") Long cardsId,
			HttpServletRequest request) {
		String openid = (String) request.getSession().getAttribute("openid");
		Map<String, Object> model = cardService.userCard(cardsId, openid);
		return new ModelAndView("mobile/userCard", model);
	}

	/**
	 * 集卡说明
	 * 
	 * @return
	 */
	@RequestMapping("/cardsInfo")
	public ModelAndView cardsInfo(@RequestParam("cardsId") Long cardsId,
			HttpServletRequest request) {
		Map<String, Object> model = cardsService.cardsInfo(cardsId);
		return new ModelAndView("mobile/cardsInfo", model);
	}

	/**
	 * 领取卡片
	 * 
	 * @return
	 */
	@RequestMapping("/cardsComplete")
	public ModelAndView cardsComplete(@RequestParam("cardsId") Long cardsId,
			HttpServletRequest request) {
		String openid = (String) request.getSession().getAttribute("openid");
		String nickname = (String) request.getSession()
				.getAttribute("nickname");
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			model = cardsService.cardsComplete(cardsId, openid, nickname);
		} catch (LogicException e) {
			model.put("msg", e.getMessage());
		}
		return new ModelAndView("mobile/cardsComplete", model);
	}

	/**
	 * 领取红包
	 * 
	 * @return
	 */
	@RequestMapping("/getRedpacket")
	public ModelAndView getRedpacket(
			@RequestParam("redpacketId") Long redpacketId,
			HttpServletRequest request) {
		String openid = (String) request.getSession().getAttribute("openid");
		String nickname = (String) request.getSession()
				.getAttribute("nickname");
		Map<String, Object> model = redpacketService.receiveRedpacket(redpacketId,
				openid, nickname);
		System.out.println(model);
		return new ModelAndView("mobile/getRedpacket", model);
	}

	/**
	 * 领取红包
	 * 
	 * @return
	 */
	@RequestMapping("/confirmRedpacket")
	@ResponseBody
	public AjaxResult confirmRedpacket(
			@RequestParam("redpacketLogId") Long redpacketLogId,
			@RequestParam("userCardId") Long userCardId,
			HttpServletRequest request) {
		String openid = (String) request.getSession().getAttribute("openid");
		AjaxResult ar;
		JSONObject confirmRedpacket = redpacketService.confirmRedpacket(
				userCardId, redpacketLogId, openid);
		ar = new AjaxResult(confirmRedpacket);
		return ar;
	}

	/**
	 * 账户余额
	 * 
	 * @return
	 */
	@RequestMapping("/userInfo")
	public ModelAndView userInfo(HttpServletRequest request) {
		String openid = (String) request.getSession().getAttribute("openid");
		Map<String, Object> model = userService.userInfo(openid);
		return new ModelAndView("mobile/userInfo", model);
	}
	/**
	 * 体现
	 * 
	 * @return
	 */
	@RequestMapping("/withdraw")
	@ResponseBody
	public AjaxResult withdraw(HttpServletRequest request) {
		String openid = (String) request.getSession().getAttribute("openid");
		return userService.withdraw(openid);
	}
}
