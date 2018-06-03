/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service.impl;

import java.util.*;

import com.liuhe.redpacket.domain.*;
import com.liuhe.redpacket.utils.result.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.domain.DrawLog;
import com.liuhe.redpacket.mapper.AdClickLogMapper;
import com.liuhe.redpacket.mapper.AdMapper;
import com.liuhe.redpacket.mapper.CardMapper;
import com.liuhe.redpacket.mapper.CardsMapper;
import com.liuhe.redpacket.mapper.HomepageMapper;
import com.liuhe.redpacket.mapper.DrawLogMapper;
import com.liuhe.redpacket.mapper.RedpacketMapper;
import com.liuhe.redpacket.mapper.SystemDictionaryItemMapper;
import com.liuhe.redpacket.mapper.UserCardMapper;
import com.liuhe.redpacket.mapper.UserMapper;
import com.liuhe.redpacket.query.AdClickLogQuery;
import com.liuhe.redpacket.query.CardQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.DrawLogQuery;
import com.liuhe.redpacket.query.RedpacketQuery;
import com.liuhe.redpacket.service.IRedpacketService;
import com.liuhe.redpacket.utils.ConstUtil;
import com.liuhe.redpacket.utils.DateUtil;
import com.liuhe.redpacket.utils.DoubleUtils;
import com.liuhe.redpacket.utils.MatrixToImageWriter;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class RedpacketServiceImpl implements IRedpacketService {
//	// 本地异常日志记录对象
//	private static final Logger log = LoggerFactory
//			.getLogger(RedpacketServiceImpl.class);

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
    private CardsMapper cardsMapper;
    @Autowired
    private AdMapper adMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdClickLogMapper adClickLogMapper;
    @Autowired
    private SystemDictionaryItemMapper systemDictionaryItemMapper;
    @Autowired
    private HomepageMapper homepageMapper;

    @Override
    public void save(Redpacket redpacket) {
        redpacket.setStatus(ConstUtil.REDPACKET_ENABLED);
        redpacket.setIsDel(0);
        redpacketMapper.save(redpacket);

        String url = baseUrl + "mobile/drawCenter?redpacketId="
                + redpacket.getId();
        String qrcode = MatrixToImageWriter.create(url, "qrcode/redpacket",
                400, 400, "jpg", redpacket.getId() + "");
        redpacket.setQrcode(qrcode);
        redpacketMapper.update(redpacket);
    }

    @Override
    public void update(Redpacket redpacket) {
        redpacketMapper.update(redpacket);
    }

    @Override
    public void delete(Long id) {
        Redpacket redpacket = redpacketMapper.get(id);
        redpacket.setIsDel(1);
        redpacket.setDelTime(new Date());
        redpacketMapper.update(redpacket);
    }

    @Override
    public Redpacket get(Long id) {
        return redpacketMapper.get(id);
    }

    @Override
    public List<Redpacket> getAll() {
        return redpacketMapper.getAll();
    }

    @Override
    public PageResult<Redpacket> query(RedpacketQuery qu) {
        // 统计查询
        Long total = redpacketMapper.queryTotal(qu);
        // 分页查询
        List<Redpacket> rows = redpacketMapper.query(qu);
        return new PageResult<Redpacket>(rows, qu.getPageSize(),
                qu.getCurrentPage(), total.intValue());
    }

    @Override
    public void disable(Long id) {
        Redpacket redpacket = redpacketMapper.get(id);
        if (redpacket != null
                && redpacket.getStatus() != ConstUtil.REDPACKET_DISABLED) {
            redpacket.setStatus(ConstUtil.REDPACKET_DISABLED);
            redpacketMapper.update(redpacket);
        }
    }

    @Override
    public void enable(Long id) {
        Redpacket redpacket = redpacketMapper.get(id);
        if (redpacket != null
                && redpacket.getStatus() != ConstUtil.REDPACKET_ENABLED) {
            redpacket.setStatus(ConstUtil.REDPACKET_ENABLED);
            redpacketMapper.update(redpacket);
        }
    }


    @Override
    public Map<String, Object> drawInfo(Long redpacketId, String openid) {


        Map<String, Object> model = new HashMap<String, Object>();
        model.put("redpacketId",redpacketId);

        // 判断红包是否可用
        Redpacket redpacket = redpacketMapper.get(redpacketId);
        if (redpacket == null || redpacket.getIsDel() == 1
                || redpacket.getStatus() == ConstUtil.REDPACKET_DISABLED) {
            model.put("msg", "红包不存在");
            return model;
        }


        // 卡片
        List<Card> cards = cardMapper.getAll();
        if (cards == null || cards.isEmpty()) {
            model.put("msg", "没有可以抽奖的卡片");
            return model;
        }

        List<Card> cardList = new ArrayList<>();
        while (true) {
            cardList.addAll(cards);
            if (cardList.size() >= 7) break;
        }
        cardList = cardList.subList(0, 7);
        for (int i = 0; i < cardList.size(); i++) {
            model.put("card" + i, cardList.get(i));

        }
        Double max = redpacket.getMax();
        Double min = redpacket.getMin();
        Double amount = DoubleUtils.getRandomInDouble(min, max, 2);
        model.put("amount", amount);

        //中奖列表
        DrawLogQuery drawLogQuery = new DrawLogQuery();
        drawLogQuery.setRedpacketId(redpacketId);
        List<DrawLog> drawLogs = drawLogMapper.query(drawLogQuery);


        Card redpacketCard = new Card();
        redpacketCard.setId(-1L);
        redpacketCard.setRatio(redpacket.getRatio());
        cardList.add(redpacketCard);
        Card winner = randomCard(cardList);

        DrawLog drawLog = null;
        DrawLogQuery q = new DrawLogQuery();
        q.setRedpacketId(redpacketId);
        q.setOpenid(openid);
        List<DrawLog> drawLogList = drawLogMapper.query(q);
        if (drawLogList != null && !drawLogList.isEmpty()) {
            drawLog = drawLogList.get(0);
            if (drawLog.getReceiveTime() != null){
                model.put("amount", drawLog.getAmount());
                model.put("winner", drawLog.getCardId());
                model.put("drawLogs", drawLogs);
                return model;
            }
        } else {
            User user = userMapper.getByWechat(openid);
            drawLog = new DrawLog();
            drawLog.setUserId(user.getId());
            drawLog.setOpenid(openid);
            drawLog.setUserName(user.getUsername());
            drawLog.setType(0);
            drawLog.setAmount(0D);
            drawLogMapper.save(drawLog);
        }
        drawLog.setAmount(amount);
        drawLog.setRedpacketId(redpacketId);
        drawLog.setRedpacketName(redpacket.getName());
        drawLog.setCardId(winner.getId());
        drawLog.setCardName(winner.getName());
        if (winner.getId() == -1) {
            drawLog.setType(1);
            drawLog.setRemark(amount.toString() + "元");
        } else {
            drawLog.setRemark(winner.getName());
            drawLog.setType(2);
        }
        drawLogMapper.update(drawLog);



        model.put("winner", winner.getId());
        model.put("drawLogs", drawLogs);

        return model;
    }

    @Override
    public AjaxResult draw(Long redpacketId,
                                  String openid) {
        Map<String, Object> info = new HashMap<>();

        DrawLogQuery drawLogQuery = new DrawLogQuery();
        drawLogQuery.setRedpacketId(redpacketId);
        drawLogQuery.setOpenid(openid);
        List<DrawLog> drawLogList = drawLogMapper.query(drawLogQuery);
        DrawLog drawLog = drawLogList.get(0);
        info.put("drawLog", drawLog);


        if (drawLog.getReceiveTime() != null) {
            return new AjaxResult(info);
        }
        drawLog.setReceiveTime(new Date());
        drawLogMapper.update(drawLog);

        User user = userMapper.getByWechat(openid);

        if (drawLog.getType() == 2) {
            UserCard userCard = new UserCard();
            userCard.setCardId(drawLog.getCardId());
            userCard.setDrawLogId(drawLog.getId());
            userCard.setReceiveTime(new Date());
            userCard.setOpenid(openid);
            userCard.setUserId(user.getId());
            userCardMapper.save(userCard);
        } else {

            user.setAmount(DoubleUtils.add(user.getAmount(),
                    drawLog.getAmount()));
            userMapper.update(user);
        }

        return new AjaxResult(info);
    }

    private Card randomCard(List<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            return null;
        }
        int random = -1;

        Double totalRatio = 0D;
        //计算总权重
        for (Card card : cards) {
            totalRatio = DoubleUtils.add(totalRatio, card.getRatio());
        }
        //产生随机数
        double randomNumber;
        randomNumber = Math.random();

        //根据随机数在所有奖品分布的区域并确定所抽奖品
        double d1 = 0;
        double d2 = 0;
        for (int i = 0; i < cards.size(); i++) {
            d2 += Double.parseDouble(String.valueOf(cards.get(i).getRatio())) / totalRatio;
            if (i == 0) {
                d1 = 0;
            } else {
                d1 += Double.parseDouble(String.valueOf(cards.get(i - 1).getRatio())) / totalRatio;
            }
            if (randomNumber >= d1 && randomNumber < d2) {
                random = i;
                break;
            }
        }

        return cards.get(random);
    }

    ;
}
