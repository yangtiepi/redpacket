/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service.impl;

import java.io.IOException;
import java.util.ArrayList;
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
import com.liuhe.redpacket.domain.AdHomepageLink;
import com.liuhe.redpacket.domain.Homepage;
import com.liuhe.redpacket.mapper.AdClickLogMapper;
import com.liuhe.redpacket.mapper.HomepageMapper;
import com.liuhe.redpacket.query.AdQuery;
import com.liuhe.redpacket.query.HomepageQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.service.IAdService;
import com.liuhe.redpacket.service.IHomepageService;
import com.liuhe.redpacket.utils.ConstUtil;
import com.liuhe.redpacket.utils.FileUtil;
import com.liuhe.redpacket.utils.MatrixToImageWriter;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class HomepageServiceImpl implements IHomepageService {

	@Autowired
	private HomepageMapper homepageMapper;
	@Autowired
	private IAdService adService;
	@Autowired
	private AdClickLogMapper adClickLogMapper;
	@Value("#{prop.baseUrl}")
	private String baseUrl;

	@Override
	public void save(MultipartFile img, Homepage homepage) throws IOException {
		if (img != null && !img.isEmpty()) {
			String src = FileUtil.saveImage(img, "homepage");
			FileUtil.deleteFile(homepage.getImage());
			homepage.setImage(src);
		}
		homepage.setCreateTime(new Date());
		homepage.setStatus(ConstUtil.HOMEPAGE_ENABLED);
		homepage.setIsDel(0);
		homepageMapper.save(homepage);

		String url = baseUrl + "mobile/homepage?homepageId=" + homepage.getId()
				+ "&page=1";
		String qrcode = MatrixToImageWriter.create(url, "qrcode/homepage", 400,
				400, "jpg", homepage.getId() + "");
		homepage.setQrcode(qrcode);
		homepageMapper.update(homepage);

		// 维护中间表信息
		this.updateAdHomepageLink(homepage);
	}

	@Override
	public void update(MultipartFile img, Homepage homepage) throws IOException {
		if (img != null && !img.isEmpty()) {
			String src = FileUtil.saveImage(img, "homepage");
			FileUtil.deleteFile(homepage.getImage());
			homepage.setImage(src);
		}
		homepageMapper.update(homepage);
		this.updateAdHomepageLink(homepage);
	}

	@Override
	public void delete(Long id) {
		Homepage homepage = homepageMapper.get(id);
		homepage.setIsDel(1);
		homepage.setDelTime(new Date());
		homepageMapper.update(homepage);
	}

	@Override
	public Homepage get(Long id) {
		return homepageMapper.get(id);
	}

	@Override
	public List<Homepage> getAll() {
		return homepageMapper.getAll();
	}

	@Override
	public PageResult<Homepage> query(HomepageQuery qu) {
		// 统计查询
		Long total = homepageMapper.queryTotal(qu);
		// 分页查询
		List<Homepage> rows = homepageMapper.query(qu);
		return new PageResult<Homepage>(rows, qu.getPageSize(),
				qu.getCurrentPage(), total.intValue());
	}

	private void updateAdHomepageLink(Homepage homepage) {
		// 判断账户ID有效
		Long homepageId = homepage.getId();
		if (homepageId != null) {
			homepageMapper.deleteAdHomepageLink(homepageId);
			// 获取广告集合
			List<Ad> ads = homepage.getAds();
			if (ads != null && ads.size() > 0) {
				List<AdHomepageLink> list = new ArrayList<>();
				for (Ad ad : ads) {
					Long adId = ad.getId();
					if (homepageId != null) {
						list.add(new AdHomepageLink(adId, homepageId));
					}
				}
				// 保存中间表数据
				homepageMapper.saveAdHomepageLink(list);
			}
		}
	}

	@Override
	public void disable(Long id) {
		Homepage homepage = homepageMapper.get(id);
		if (homepage != null
				&& homepage.getStatus() != ConstUtil.HOMEPAGE_DISABLED) {
			homepage.setStatus(ConstUtil.HOMEPAGE_DISABLED);
			homepageMapper.update(homepage);
		}

	}

	@Override
	public void enable(Long id) {
		Homepage homepage = homepageMapper.get(id);
		if (homepage != null
				&& homepage.getStatus() != ConstUtil.HOMEPAGE_ENABLED) {
			homepage.setStatus(ConstUtil.HOMEPAGE_ENABLED);
			homepageMapper.update(homepage);
		}

	}

	@Override
	public Map<String, Object> mobileIndex(Long homepageId, Integer page,
			String openid) {
		Homepage homepage = homepageMapper.get(homepageId);
		if (ConstUtil.HOMEPAGE_DISABLED == homepage.getStatus()) {
			return null;
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("title", homepage.getTitle());
		model.put("image", homepage.getImage());
		model.put("info", homepage.getInfo());
		model.put("id", homepage.getId());
		model.put("adMaxNum", homepage.getAdMaxNum());

		AdQuery qu = new AdQuery();
		qu.setPageSize(9);
		qu.setCurrentPage(page);
		qu.setHomepageId(homepageId);
		qu.setIsValid(1);
		// qu.setOpenid(openid);
		PageResult<Ad> result = adService.query(qu);
		if (result.getRows().size() == 0) {
			qu.setCurrentPage(1);
			result = adService.query(qu);
		}

		//计算总点击数
//		qu.setPageSize(Integer.MAX_VALUE);
//		PageResult<Ad> rs = adService.query(qu);
//		Integer clickNum = 0;
//		for (Ad ad : rs.getRows()) {
//			System.out.println("============ad==============="+ad);
//			AdClickLogQuery aqu = new AdClickLogQuery();
//			aqu.setAdId(ad.getId());
//			aqu.setHomepageId(homepageId);
//			Long total = adClickLogMapper.queryTotal(aqu);
//			clickNum += total.intValue();
//		}
		int clickNum = homepageMapper.getTotalClicked(homepageId);
		
		model.put("page", result.getPage());
		model.put("ads", result.getRows());
		model.put("clickNum", clickNum);
		return model;
	}
}
