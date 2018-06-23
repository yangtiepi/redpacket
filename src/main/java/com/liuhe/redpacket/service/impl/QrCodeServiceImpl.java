/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service.impl;

import com.liuhe.redpacket.domain.QrCode;
import com.liuhe.redpacket.mapper.QrCodeMapper;
import com.liuhe.redpacket.query.QrCodeQuery;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.service.IQrCodeService;
import com.liuhe.redpacket.utils.IdWorker;
import com.liuhe.redpacket.utils.MatrixToImageWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class QrCodeServiceImpl implements IQrCodeService {

	@Autowired
	private QrCodeMapper qrCodeMapper;
//	@Autowired
//	private IdWorker idWorker;
	@Value("#{prop.baseUrl}")
	private String baseUrl;
	
	
	@Override
	public QrCode save(QrCode qrCode) {
		qrCode.setIsDel(0);
		qrCode.setIsUsed(0);
		qrCode.setCode(UUID.randomUUID().toString());
		qrCodeMapper.save(qrCode);

		String url = baseUrl + "mobile/drawCenter?code="
				+ qrCode.getCode();
		String qrcode = MatrixToImageWriter.create(url, "qrcode",
				400, 400, "jpg", qrCode.getCode() + "");
 		qrCode.setUrl(qrcode);
		qrCodeMapper.update(qrCode);
		return qrCode;
	}

	
	@Override
	public void update(QrCode QrCode) {
		qrCodeMapper.update(QrCode);
	}

	
	@Override
	public void delete(Long id) {
		qrCodeMapper.delete(id);
	}
	
	@Override
	public QrCode get(Long id) {
		return qrCodeMapper.get(id);
	}

	
	@Override
	public List<QrCode> getAll() {
		return qrCodeMapper.getAll();
	}

	
	@Override
	public PageResult<QrCode> query(QrCodeQuery qu) {
		// 统计查询
		Long total = qrCodeMapper.queryTotal(qu);
		// 分页查询
		List<QrCode> rows = qrCodeMapper.query(qu);
		return new PageResult<QrCode>(rows, qu.getPageSize(),
				qu.getCurrentPage(), total.intValue());
	}

	@Override
	public List<QrCode> save(int num) throws InterruptedException {
		List<Thread> workers = new ArrayList<>();
		final List<QrCode> qrCodes = new ArrayList<>();
		for(int i = 0; i < num; i++) {
//			Thread worker = new Thread(new Runnable() {
//				@Override
//				public void run() {
					QrCode qrCode = new QrCode();
					qrCode = save(qrCode);
					qrCodes.add(qrCode);
//				}
//			});
//			worker.start();
//			workers.add(worker);
		}
//		for(int i = 0; i < num; i++) {
//			workers.get(i).join();
//		}
		return qrCodes;
	}

}
