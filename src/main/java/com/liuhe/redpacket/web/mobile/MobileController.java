package com.liuhe.redpacket.web.mobile;

/**
 * 客户相关的接口
 */

import com.liuhe.redpacket.domain.CardsComplete;
import com.liuhe.redpacket.domain.DrawLog;
import com.liuhe.redpacket.service.*;
import com.liuhe.redpacket.utils.result.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("mobile")
public class MobileController {
    @Autowired
    private ICardService cardService;

    @Autowired
    private IRedpacketService redpacketService;
    @Autowired
    private IDrawLogService drawLogService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICardsCompleteService cardsCompleteService;

    /**
     * 非微信打开页面
     *
     * @param
     */
    @RequestMapping("wxFllow")
    public String doWxFllow() {
        return "mobile/wxFllow";
    }

    /**
     * 个人中心
     *
     * @return
     */
    @RequestMapping("/userInfo")
    public ModelAndView userInfo(HttpServletRequest request,String qrCode) {
        String openid = (String) request.getSession().getAttribute("openid");
        Map<String, Object> model = userService.userInfo(openid);
        model.put("qrCode",qrCode);
        return new ModelAndView("mobile/userInfo", model);
    }

    /**
     * 余额
     *
     * @return
     */
    @RequestMapping("/balance")
    public ModelAndView balance(HttpServletRequest request) {
        String openid = (String) request.getSession().getAttribute("openid");
        Map<String, Object> model = userService.userInfo(openid);
        return new ModelAndView("mobile/balance", model);
    }

    /**
     * 我的卡片
     *
     * @return
     */
    @RequestMapping("/userCards")
    public ModelAndView userCards(HttpServletRequest request) {
        String openid = (String) request.getSession().getAttribute("openid");
        Map<String, Object> model = cardService.userCard(openid);
        return new ModelAndView("mobile/userCards", model);
    }

    /**
     * 抽奖记录
     *
     * @return
     */
    @RequestMapping("/drawRecord")
    public ModelAndView drawRecord(
            HttpServletRequest request) {
        String openid = (String) request.getSession().getAttribute("openid");
        List<DrawLog> drawLogList =  drawLogService.findByUser(openid);
        Map<String, Object> model = userService.userInfo(openid);
        model.put("drawLogList",drawLogList);
        return new ModelAndView("mobile/drawRecord", model);
    }


    /**
     * 兑奖记录
     *
     * @return
     */
    @RequestMapping("/exchangeRecord")
    public ModelAndView exchangeRecord(
            HttpServletRequest request) {
        String openid = (String) request.getSession().getAttribute("openid");
        List<CardsComplete> cardsCompleteList =  cardsCompleteService.findByUser(openid);
        Map<String, Object> model = userService.userInfo(openid);
        model.put("cardsCompleteList",cardsCompleteList);
        return new ModelAndView("mobile/drawRecord", model);
    }

    /**
     * 抽奖中心
     *
     * @return
     */
    @RequestMapping("/drawCenter")
    public ModelAndView drawCenter(HttpServletRequest request) {
        String openid = (String) request.getSession().getAttribute("openid");
        Map<String, Object> model = drawLogService.drawInfo(openid);
        return new ModelAndView("mobile/drawCenter", model);
    }

    /**
     * 抽奖
     *
     * @return
     */
    @RequestMapping("/draw")
    @ResponseBody
    public AjaxResult draw(
            HttpServletRequest request, String code) {
        String openid = (String) request.getSession().getAttribute("openid");
        return drawLogService.draw(code, openid);
    }

    /**
     * 抽中结果
     *
     * @return
     */
    @RequestMapping("/drawResult")
    public ModelAndView drawResult(
            HttpServletRequest request, Long redpacketId) {
        String openid = (String) request.getSession().getAttribute("openid");
        DrawLog drawLog = drawLogService.get(redpacketId, openid);
        Map<String, Object> model = new HashMap<>();
        model.put("drawLog", drawLog);
        if (drawLog.getType() == 1) {
            return new ModelAndView("mobile/drawMoney", model);
        } else if(drawLog.getType() == 2){
            return new ModelAndView("mobile/drawCard", model);
        }
        return new ModelAndView("mobile/drawCard", model);
    }

    /**
     * 兑换
     *
     * @return
     */
    @RequestMapping("/exchange")
    @ResponseBody
    public AjaxResult exchange(
            HttpServletRequest request, Long cardsId) {
        String openid = (String) request.getSession().getAttribute("openid");
        return cardsCompleteService.exchange(openid,cardsId);
    }


    /**
     * 提现
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
