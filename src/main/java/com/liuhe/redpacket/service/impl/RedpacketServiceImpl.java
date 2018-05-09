/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.domain.Ad;
import com.liuhe.redpacket.domain.AdClickLog;
import com.liuhe.redpacket.domain.Card;
import com.liuhe.redpacket.domain.Cards;
import com.liuhe.redpacket.domain.Homepage;
import com.liuhe.redpacket.domain.Redpacket;
import com.liuhe.redpacket.domain.RedpacketLog;
import com.liuhe.redpacket.domain.SystemDictionaryItem;
import com.liuhe.redpacket.domain.User;
import com.liuhe.redpacket.domain.UserCard;
import com.liuhe.redpacket.mapper.AdClickLogMapper;
import com.liuhe.redpacket.mapper.AdMapper;
import com.liuhe.redpacket.mapper.CardMapper;
import com.liuhe.redpacket.mapper.CardsMapper;
import com.liuhe.redpacket.mapper.HomepageMapper;
import com.liuhe.redpacket.mapper.RedpacketLogMapper;
import com.liuhe.redpacket.mapper.RedpacketMapper;
import com.liuhe.redpacket.mapper.SystemDictionaryItemMapper;
import com.liuhe.redpacket.mapper.UserCardMapper;
import com.liuhe.redpacket.mapper.UserMapper;
import com.liuhe.redpacket.query.AdClickLogQuery;
import com.liuhe.redpacket.query.CardQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.RedpacketLogQuery;
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
	private RedpacketLogMapper redpacketLogMapper;
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

		String url = baseUrl + "mobile/getRedpacket?redpacketId="
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
	public Map<String, Object> receiveRedpacket(Long redpacketId, String openid,String wechat) {
		Date date = new Date();
		Date monthStart = DateUtil.getMonthStart(date);
		Date monthEnd = DateUtil.getMonthEnd(date);
		Date weekStart = DateUtil.getWeekMonday(date);
		Date weekEnd = DateUtil.getWeekSunday(date);

		Date dayStart = DateUtil.getDayOfHour(0);
		Date dayEnd = DateUtil.getDayOfHour(24);

		int dayNum = userCardMapper.getTotal(openid, dayStart, dayEnd);
		int weekNum = userCardMapper.getTotal(openid, weekStart, weekEnd);
		int monthNum = userCardMapper.getTotal(openid, monthStart, monthEnd);

		int ableDayNum = 0;
		int ableWeekNum = 0;
		int ableMonthNum = 0;
		List<SystemDictionaryItem> list = systemDictionaryItemMapper
				.findByParentKey(ConstUtil.USER_REDPACKET_DAY);
		if (list != null && !list.isEmpty()) {
			SystemDictionaryItem systemDictionaryItem = list.get(0);
			String value = systemDictionaryItem.getValue();
			try {
				Integer num = Integer.valueOf(value);
				ableDayNum = (num - dayNum) < 0 ? 0 : (num - dayNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		List<SystemDictionaryItem> list2 = systemDictionaryItemMapper
				.findByParentKey(ConstUtil.USER_REDPACKET_WEEK);
		if (list2 != null && !list2.isEmpty()) {
			SystemDictionaryItem systemDictionaryItem = list2.get(0);
			String value = systemDictionaryItem.getValue();
			try {
				Integer num = Integer.valueOf(value);
				ableWeekNum = (num - weekNum) < 0 ? 0 : (num - weekNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		List<SystemDictionaryItem> list3 = systemDictionaryItemMapper
				.findByParentKey(ConstUtil.USER_REDPACKET_MONTH);
		if (list3 != null && !list3.isEmpty()) {
			SystemDictionaryItem systemDictionaryItem = list3.get(0);
			String value = systemDictionaryItem.getValue();
			try {
				Integer num = Integer.valueOf(value);
				ableMonthNum = (num - monthNum) < 0 ? 0 : (num - monthNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ableDayNum", ableDayNum);
		model.put("ableWeekNum", ableWeekNum);
		model.put("ableMonthNum", ableMonthNum);

		if (ableDayNum == 0 || ableWeekNum == 0 || ableMonthNum == 0) {
			model.put("msg", "当前领取机会已经用完");
			return model;
		}
		// 判断红包是否可用
		Redpacket redpacket = redpacketMapper.get(redpacketId);
		if (redpacket == null || redpacket.getIsDel() == 1
				|| redpacket.getStatus() == ConstUtil.REDPACKET_DISABLED) {
			model.put("msg", "红包不存在");
			return model;
		}
		// 判断该用户是否点击广告
		List<Ad> ads = adMapper.getByRedpacket(redpacketId);
		if (ads == null || ads.isEmpty()) {
			model.put("msg", "广告主不存在");
			return model;
		}
		boolean flag = true;
		boolean isRevice = false;
		Long adId = null;
		Long homepageId = null;
		for (Ad ad : ads) {
			RedpacketLogQuery qu = new RedpacketLogQuery();
			qu.setAdId(ad.getId());
			qu.setOpenid(openid);
			qu.setRedpacketId(redpacketId);
			List<RedpacketLog> rpls = redpacketLogMapper.query(qu);
			//没有领过红包
			if (rpls == null || rpls.isEmpty()) {
				AdClickLogQuery equ = new AdClickLogQuery();
				equ.setAdId(ad.getId());
				equ.setOpenid(openid);
				List<AdClickLog> acls = adClickLogMapper.query(equ);
				//无点击记录(进入对应广告主)
				if (acls == null || acls.isEmpty()) {
					adId = ad.getId();
					List<Homepage> byAd = homepageMapper.getByAd(adId);
					//获取第一个未点满的首页
					for (Homepage homepage : byAd) {
						int totalClicked = homepageMapper.getTotalClicked(homepage.getId());
						if (totalClicked < homepage.getAdMaxNum()) {
							homepageId = homepage.getId();
							break;
						}
					}
					break;
				}
				//有点击记录，获取红包
				Double max = redpacket.getMax();
				Double min = redpacket.getMin();
				Double amount = DoubleUtils.getRandomInDouble(min, max, 2);

				RedpacketLog redpacketLog = new RedpacketLog();
				redpacketLog.setAdId(ad.getId());
				redpacketLog.setAmount(amount);
				redpacketLog.setOpenid(openid);
				redpacketLog.setWechat(wechat);
				redpacketLog.setRedpacketId(redpacketId);
				redpacketLog.setRedpacketName(redpacket.getName());

				redpacketLogMapper.save(redpacketLog);
				model.put("redpacketLog", redpacketLog);
				// 获取卡片
				Long cardsId = redpacket.getCardsId();
				Cards cards = cardsMapper.get(cardsId);
				//没有对应卡集
				if (cards == null || cards.getIsDel() == 1
						|| cards.getStatus() == ConstUtil.CARDS_DISABLED) {
					return model;
				}
				//有卡集，领取卡片
				CardQuery query = new CardQuery();
				query.setCardsId(cardsId);
				List<Card> list4 = cardMapper.query(query);
//				Map<Integer, Long> ratioMap = new HashMap<Integer, Long>();
//				int totalRatio = 0;
//				int index = 0;
//				int add = 0;// 前面累计的
//				for (Card card : list4) {
//					totalRatio += card.getRatio();
//				}
//				for (Card card : list4) {
//					for (; index < totalRatio; index++) {
//						if (card.getRatio() + add >= index) {
//							ratioMap.put(index, card.getId());
//						} else {
//							add += card.getRatio();
//							break;
//						}
//					}
//
//				}
//				int random = new Random().nextInt(totalRatio);
//				Long cardId = ratioMap.get(random);
//				Card card = cardMapper.get(cardId);
				if (list4 == null || list4.isEmpty()) {
					return model;
				}
				Card card = randomCard(list4);
				model.put("card", card);

				UserCard userCard = new UserCard();
				userCard.setCardId(card.getId());
				userCard.setOpenid(openid);
				userCard.setRedPacketLogId(redpacketLog.getId());
				userCardMapper.save(userCard);
				model.put("userCard", userCard);
				return model;
			} else {
				//领取过红包，返回红包信息
				for (RedpacketLog redpacketLog : rpls) {
					model.put("redpacketLog", redpacketLog);
					UserCard userCard = userCardMapper
							.getByRedPacketLog(redpacketLog.getId());
					if (userCard != null) {
						model.put("userCard", userCard);
						Card card = cardMapper.get(userCard.getCardId());
						model.put("card", card);
					}
					if (redpacketLog.getReceiveTime() == null) {
						return model;
					}else {
						isRevice = true;
						
					}
				}

			}
		}
		//所有红包已经领取
		if (isRevice) {
			return model;
		}
		//有未点击广告，跳转到广告
		if (flag) {
			if (adId ==null || homepageId == null) {
				model.put("msg", "寻宝已结束！");
			}else{
				model.put("msg", "我要去寻宝！");
				model.put("adId", adId);
				model.put("homepageId", homepageId);
			}
			return model;
		}

		return model;
	}

	@Override
	public JSONObject confirmRedpacket(Long userCardId, Long redpacketLogId,
			String openid) {
		JSONObject jsonObject = new JSONObject();
		RedpacketLog redpacketLog = redpacketLogMapper.get(redpacketLogId);
		if (redpacketLog == null) {
			jsonObject.put("msg", "红包不存在！");
			return jsonObject;
		}
		// 判断该用户是否点击广告
		Long adId = redpacketLog.getAdId();
		Ad ad = adMapper.get(adId);
		jsonObject.put("adId", adId);
		if (ad == null) {
			jsonObject.put("msg", "没有资格领取该红包，该红包没有对应广告主！");
			return jsonObject;
		}
		AdClickLogQuery equ = new AdClickLogQuery();
		equ.setAdId(ad.getId());
		equ.setOpenid(openid);
		List<AdClickLog> list = adClickLogMapper.query(equ);
		if (list == null || list.isEmpty()) {
			jsonObject.put("msg", "我要去寻宝！");
			List<Homepage> list2 = homepageMapper.getByAd(ad.getId());
			if (list2!= null && !list2.isEmpty()) {
				jsonObject.put("homepageId", list2.get(0).getId());
			}
			return jsonObject;
		}
		if (redpacketLog.getReceiveTime() != null) {
			jsonObject.put("msg", "您已领取红包！");
			return jsonObject;
		}
		Date date = new Date();
		redpacketLog.setReceiveTime(date);
		User user = userMapper.getByWechat(openid);
		if (user == null) {
			user = new User();
			user.setAmount(0D);
			user.setOpenid(openid);
			user.setStatus(ConstUtil.USER_NORMAL);
			userMapper.save(user);
		}

		user.setAmount(DoubleUtils.add(user.getAmount(),
				redpacketLog.getAmount()));
		userMapper.update(user);
		redpacketLogMapper.update(redpacketLog);

		UserCard userCard = userCardMapper.get(userCardId);
		if (userCard != null && userCard.getReceiveTime() == null) {
			userCard.setReceiveTime(date);
			userCardMapper.update(userCard);
		}
		jsonObject.put("msg", "领取成功！");
		jsonObject.put("success", "true");
		return jsonObject;
	}
	
	private Card randomCard(List<Card> cards){
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
        for(int i=0;i<cards.size();i++){
            d2 += Double.parseDouble(String.valueOf(cards.get(i).getRatio()))/totalRatio;
            if(i==0){
                d1 = 0;
            }else{
                d1 +=Double.parseDouble(String.valueOf(cards.get(i-1).getRatio()))/totalRatio;
            }
            if(randomNumber >= d1 && randomNumber <d2){
                random = i;
                break;
            }
        }
		
		return cards.get(random);
	};
}
