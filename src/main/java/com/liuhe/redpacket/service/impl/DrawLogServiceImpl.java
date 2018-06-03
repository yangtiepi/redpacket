/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service.impl;

import java.util.List;

import com.liuhe.redpacket.domain.DrawLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.mapper.DrawLogMapper;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.DrawLogQuery;
import com.liuhe.redpacket.service.IDrawLogService;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class DrawLogServiceImpl implements IDrawLogService{

	@Autowired
	private DrawLogMapper drawLogMapper;
	
	
	@Override
	public void save(DrawLog DrawLog) {
		drawLogMapper.save(DrawLog);
	}


	
	@Override
	public DrawLog get(Long redpacketId,String openid) {
		DrawLogQuery drawLogQuery = new DrawLogQuery();
		drawLogQuery.setRedpacketId(redpacketId);
		drawLogQuery.setOpenid(openid);
		List<DrawLog> drawLogList = drawLogMapper.query(drawLogQuery);
		if (drawLogList.isEmpty())return null;
		else return drawLogList.get(0);
	}
	@Override
	public PageResult<DrawLog> query(DrawLogQuery qu) {
		// 统计查询
		Long total = drawLogMapper.queryTotal(qu);
		// 分页查询
		List<DrawLog> rows = drawLogMapper.query(qu);
		return new PageResult<DrawLog>(rows, qu.getPageSize(),
				qu.getCurrentPage(), total.intValue());
	}

	@Override
	public List<DrawLog> findByUser(String openid) {
		return drawLogMapper.findByUser(openid);
	}

	@Override
	public JSONObject getSum(String openid) {
		return drawLogMapper.getSum(openid) ;
	}

}
