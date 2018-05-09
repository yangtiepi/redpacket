/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.liuhe.redpacket.domain.Ad;
import com.liuhe.redpacket.domain.AdClickLog;
import com.liuhe.redpacket.domain.Homepage;
import com.liuhe.redpacket.mapper.AdClickLogMapper;
import com.liuhe.redpacket.mapper.AdMapper;
import com.liuhe.redpacket.mapper.HomepageMapper;
import com.liuhe.redpacket.query.AdClickLogQuery;
import com.liuhe.redpacket.query.AdQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.service.IAdService;
import com.liuhe.redpacket.utils.ConstUtil;
import com.liuhe.redpacket.utils.DateUtil;
import com.liuhe.redpacket.utils.FileUtil;
import com.liuhe.redpacket.utils.MatrixToImageWriter;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class AdServiceImpl implements IAdService {

	@Autowired
	private AdMapper adMapper;
	@Autowired
	private AdClickLogMapper adClickLogMapper;
	@Autowired
	private HomepageMapper homepageMapper;
	@Autowired
	@Value("#{prop.baseUrl}")
	private String baseUrl;

	@Override
	public void save(Ad ad) {
		adMapper.save(ad);
	}

	@Override
	public void update(Ad ad) {
		adMapper.update(ad);
	}

	@Override
	public void delete(Long id) {
		Ad ad = adMapper.get(id);
		ad.setIsDel(1);
		ad.setDelTime(new Date());
		adMapper.update(ad);
	}

	@Override
	public Ad get(Long id) {
		return adMapper.get(id);
	}

	@Override
	public List<Ad> getAll() {
		return adMapper.getAll();
	}

	@Override
	public PageResult<Ad> query(AdQuery qu) {
		// 统计查询
		Long total = adMapper.queryTotal(qu);
		// 分页查询
		List<Ad> rows = adMapper.query(qu);
		return new PageResult<Ad>(rows, qu.getPageSize(), qu.getCurrentPage(),
				total.intValue());
	}

	@Override
	public void save(Ad ad, MultipartFile smallImg, MultipartFile image)
			throws IOException {
		if (smallImg != null && !smallImg.isEmpty()) {
			String src = FileUtil.saveImage(smallImg, "ad/smallImg");
			ad.setSmallImg(src);
		}
		if (image != null && !image.isEmpty()) {
			String src = FileUtil.saveImage(image, "ad/image");
			ad.setImage(src);
		}
		Date date = new Date();
		ad.setCreateTime(date);
		// 设置有效时间
		// Date endTime = DateUtil.getDayDistanceDay(date, ad.getPeriodDay() ==
		// -1?10000:ad.getPeriodDay());
		// ad.setBeginTime(new Date());
		// ad.setEndTime(endTime);
		ad.setIsDel(0);
		ad.setStatus(ConstUtil.AD_ENABLED);
		ad.setClickedNum(0);

		adMapper.save(ad);
		String url = baseUrl + "mobile/ad?adId=" + ad.getId();
		String qrcode = MatrixToImageWriter.create(url, "qrcode/ad", 400, 400,
				"jpg", ad.getId() + "");
		ad.setQrcode(qrcode);
	}

	@Override
	public void update(Ad ad, MultipartFile smallImg, MultipartFile image)
			throws IOException {
		if (smallImg != null && !smallImg.isEmpty()) {
			String src = FileUtil.saveImage(smallImg, "ad/smallImg");
			FileUtil.deleteFile(ad.getSmallImg());
			ad.setSmallImg(src);
		}
		if (image != null && !image.isEmpty()) {
			String src = FileUtil.saveImage(image, "ad/image");
			FileUtil.deleteFile(ad.getImage());
			ad.setImage(src);
		}

		// 设置有效时间
		// Date date = new Date();
		// Date endTime = DateUtil.getDayDistanceDay(date,
		// ad.getPeriodDay() == -1 ? 10000 : ad.getPeriodDay());
		// ad.setBeginTime(new Date());
		// ad.setEndTime(endTime);
		ad.setIsDel(0);
		ad.setStatus(ConstUtil.AD_ENABLED);
		adMapper.update(ad);
	}

	@Override
	public Map<String, Object> mobileIndex(Long adId, Long homepageId,
			String openid, String nickname) {
		Ad ad = adMapper.get(adId);

		Map<String, Object> model = new HashMap<String, Object>();
		if (homepageId == null) {
			model.put("msg", "必须从首页进入广告");
			return model;
		}
		if (ad.getStatus() == ConstUtil.AD_DISABLED || ad.getIsDel() == 1) {
			model.put("msg", "广告不存在");
			return model;
		}
		if (ad.getClickedNum() >= ad.getClickableNum()) {
			model.put("msg", "该广告已达到最大点击数");
			return model;
		}
		
		Homepage homepage = homepageMapper.get(homepageId);
		if (homepage == null) {
			model.put("msg", "首页不存在");
			return model;
		}
		// 判断是否达到首页最大点击数
		AdQuery q = new AdQuery();
		q.setHomepageId(homepageId);
		q.setPageSize(Integer.MAX_VALUE);
		List<Ad> list = adMapper.query(q);
		Integer clickNum = 0;
		for (Ad a : list) {
			AdClickLogQuery aqu = new AdClickLogQuery();
			aqu.setAdId(a.getId());
			aqu.setHomepageId(homepageId);
			Long total = adClickLogMapper.queryTotal(aqu);
			clickNum += total.intValue();
		}
		if (homepage.getAdMaxNum() <= clickNum) {
			model.put("msg", "该首页广告已达到最大点击数！");
			return model;
		}
		

		// 判断是否点击过
		AdClickLogQuery qu = new AdClickLogQuery();
		Date date = new Date();
		Date beginTime = DateUtil
				.getDayDistanceDay(date, 0 - ad.getPeriodDay());
		qu.setBeginTime(DateUtil.sdf.format(beginTime));
		qu.setEndTime(DateUtil.sdf.format(date));
		qu.setAdId(adId);
		qu.setHomepageId(homepageId);
		qu.setOpenid(openid);
		Long total = adClickLogMapper.queryTotal(qu);
		if (total > 0) {
			model.put("clickNum", total);
			// List<Homepage> homepages = homepageMapper.getByAd(adId);
			// if (homepages != null && !homepages.isEmpty()) {
			// model.put("homepageId", homepages.get(0).getId());
			// }
			model.put("homepageId", homepageId);
		} else {
			// 保存点击记录
			AdClickLog adClickLog = new AdClickLog();
			adClickLog.setAdId(adId);
			adClickLog.setHomepageId(homepageId);
			adClickLog.setAdTitle(ad.getTitle());
			adClickLog.setClickTime(date);
			adClickLog.setOpenid(openid);
			adClickLog.setWechat(nickname);
			adClickLogMapper.save(adClickLog);

			// 更新点击次数
			ad.setClickedNum(ad.getClickedNum() == null ? 0 : ad
					.getClickedNum() + 1);
			adMapper.update(ad);
		}

		model.put("ad", ad);
		return model;
	}

	@Override
	public PageResult<Ad> queryByUser(AdQuery qu) {
		// 统计查询
		Long total = adMapper.queryByUserTotal(qu);
		// 分页查询
		List<Ad> rows = adMapper.queryByUser(qu);
		return new PageResult<Ad>(rows, qu.getPageSize(), qu.getCurrentPage(),
				total.intValue());
	}

	@Override
	public List<Ad> listNoUse() {
		return adMapper.listNoUse();
	}
}
