/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service.impl;

import java.util.*;

import com.liuhe.redpacket.domain.*;
import com.liuhe.redpacket.mapper.*;
import com.liuhe.redpacket.utils.DoubleUtils;
import com.liuhe.redpacket.utils.DrawModel;
import com.liuhe.redpacket.utils.result.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.DrawLogQuery;
import com.liuhe.redpacket.service.IDrawLogService;

/**
 * @author yang
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class DrawLogServiceImpl implements IDrawLogService {
    @Value("#{prop.baseUrl}")
    private String baseUrl;
    @Autowired
    private RedpacketMapper redpacketMapper;
    @Autowired
    private DrawLogMapper drawLogMapper;
    @Autowired
    private UserCardMapper userCardMapper;
    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QrCodeMapper qrCodeMapper;
    @Autowired
    private CardLogMapper cardLogMapper;
    @Autowired
    private RedpacketLogMapper redpacketLogMapper;


    @Override
    public void save(DrawLog DrawLog) {
        drawLogMapper.save(DrawLog);
    }


    @Override
    public DrawLog get(Long redpacketId, String openid) {
        DrawLogQuery drawLogQuery = new DrawLogQuery();
        drawLogQuery.setRedpacketId(redpacketId);
        drawLogQuery.setOpenid(openid);
        List<DrawLog> drawLogList = drawLogMapper.query(drawLogQuery);
        if (drawLogList.isEmpty()) return null;
        else return drawLogList.get(0);
    }

    @Override
    public PageResult<DrawLog> query(DrawLogQuery qu) {
        // 统计查询
        Long total = drawLogMapper.queryTotal(qu);
        // 分页查询
        List<DrawLog> rows = drawLogMapper.query(qu);
        return new PageResult<>(rows, qu.getPageSize(),
                qu.getCurrentPage(), total.intValue());
    }

    @Override
    public List<DrawLog> findByUser(String openid) {
        return drawLogMapper.findByUser(openid);
    }

    @Override
    public JSONObject getSum(String openid) {
        return drawLogMapper.getSum(openid);
    }


    @Override
    public Map<String, Object> drawInfo(String openid) {
        Map<String, Object> model = new HashMap<>();
        //中奖列表
        DrawLogQuery drawLogQuery = new DrawLogQuery();
        List<DrawLog> drawLogs = drawLogMapper.query(drawLogQuery);
        model.put("drawLogs", drawLogs);

        return model;
    }

    @Override
    public AjaxResult draw(String code,
                           String openid) {
        Map<String, Object> info = new HashMap<>();
        // 判断二维码是否可用
        QrCode qrCode = qrCodeMapper.getByCode(code);
        if (qrCode == null || qrCode.getIsDel() == 1
                || qrCode.getIsUsed() == 1) {
            return new AjaxResult("抽奖次数已用完！", 0);
        }
        List<DrawModel> drawList = new ArrayList<>();
        // 卡片
        List<Card> cards = cardMapper.getAll();
        for (Card card : cards) {
            DrawModel drawModel = new DrawModel();
            drawModel.setId(card.getId());
            drawModel.setName(card.getName());
            drawModel.setRatio(card.getRatio());
            drawModel.setType(1);
            drawList.add(drawModel);
        }
        List<Redpacket> redpacketList = redpacketMapper.getAll();
        for (Redpacket redpacket : redpacketList) {
            DrawModel drawModel = new DrawModel();
            drawModel.setId(0 - redpacket.getId());
            drawModel.setName(redpacket.getName());
            drawModel.setRatio(redpacket.getRatio());
            drawModel.setAmount(redpacket.getMin());
            drawModel.setType(0);
            drawList.add(drawModel);
        }

        DrawModel winner = randomCard(drawList);
        Date date = new Date();

        User user = userMapper.getByWechat(openid);
        DrawLog drawLog = new DrawLog();
        drawLog.setUserId(user.getId());
        drawLog.setOpenid(openid);
        drawLog.setUserName(user.getUsername());
        drawLog.setReceiveTime(date);
        drawLog.setQrCodeId(qrCode.getId());
        if (winner.getType() != null && winner.getType() == 0) {
            drawLog.setRedpacketId(0 - winner.getId());
            drawLog.setRedpacketName(winner.getName());
            drawLog.setAmount(winner.getAmount());
            drawLog.setRemark(winner.getAmount().toString() + "元");
            drawLog.setType(1);
            info.put("type", 1);
            info.put("amount", winner.getAmount().toString());
        } else if (winner.getType() != null && winner.getType() == 1) {
            drawLog.setCardId(winner.getId());
            drawLog.setCardName(winner.getName());
            drawLog.setRemark(winner.getName());
            drawLog.setType(2);
            info.put("type", 2);
            info.put("name", winner.getName());
        } else {
            drawLog.setType(3);
            info.put("type", 3);
        }
        drawLogMapper.save(drawLog);




        if (drawLog.getType() == 2) {
            UserCard userCard = new UserCard();
            userCard.setCardId(drawLog.getCardId());
            userCard.setDrawLogId(drawLog.getId());
            userCard.setReceiveTime(date);
            userCard.setOpenid(openid);
            userCard.setUserId(user.getId());
            userCardMapper.save(userCard);

            CardLog cardLog = new CardLog();
            cardLog.setCardId(winner.getId());
            cardLog.setCardName(winner.getName());
            cardLog.setOpenid(openid);
            cardLog.setQrCodeId(qrCode.getId());
            cardLog.setReceiveTime(date);
            cardLog.setType(0);
            cardLog.setUserId(user.getId());
            cardLog.setUserName(user.getUsername());
            cardLogMapper.save(cardLog);
        } else {
            user.setAmount(DoubleUtils.add(user.getAmount(),
                    drawLog.getAmount()));
            userMapper.update(user);

            RedpacketLog redpacketLog = new RedpacketLog();
            redpacketLog.setOpenid(openid);
            redpacketLog.setQrCodeId(qrCode.getId());
            redpacketLog.setReceiveTime(date);
            redpacketLog.setUserId(user.getId());
            redpacketLog.setUserName(user.getUsername());
            redpacketLog.setAmount(winner.getAmount());
            redpacketLog.setRedpacketId(0-winner.getId());
            redpacketLog.setRedpacketName(winner.getName());
            redpacketLogMapper.save(redpacketLog);
        }

        qrCode.setIsUsed(1);
        qrCode.setUsedTime(date);
        qrCodeMapper.update(qrCode);

        return new AjaxResult(info);
    }


    private DrawModel randomCard(List<DrawModel> drawModels) {
        if (drawModels == null || drawModels.isEmpty()) {
            return null;
        }
        int random = -1;

        Double totalRatio = 0D;
        //计算总权重
        for (DrawModel drawModel : drawModels) {
            totalRatio = DoubleUtils.add(totalRatio, drawModel.getRatio());
        }

        //谢谢惠顾
//        if (DoubleUtils.compare(totalRatio, 100D) != 1) {
//            DrawModel drawModel = new DrawModel();
//            drawModel.setRatio(DoubleUtils.sub(100D, totalRatio));
//            drawModels.add(drawModel);
//            totalRatio = 100D;
//        }

        //产生随机数
        double randomNumber;
        randomNumber = Math.random();

        //根据随机数在所有奖品分布的区域并确定所抽奖品
        double d1 = 0;
        double d2 = 0;
        for (int i = 0; i < drawModels.size(); i++) {
            d2 += Double.parseDouble(String.valueOf(drawModels.get(i).getRatio())) / totalRatio;
            if (i == 0) {
                d1 = 0;
            } else {
                d1 += Double.parseDouble(String.valueOf(drawModels.get(i - 1).getRatio())) / totalRatio;
            }
            if (randomNumber >= d1 && randomNumber < d2) {
                random = i;
                break;
            }
        }

        return drawModels.get(random);
    }

}
