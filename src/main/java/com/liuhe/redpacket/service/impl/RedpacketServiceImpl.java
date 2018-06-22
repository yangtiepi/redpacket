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

import com.liuhe.redpacket.domain.DrawLog;
import com.liuhe.redpacket.mapper.CardMapper;
import com.liuhe.redpacket.mapper.DrawLogMapper;
import com.liuhe.redpacket.mapper.RedpacketMapper;
import com.liuhe.redpacket.mapper.UserCardMapper;
import com.liuhe.redpacket.mapper.UserMapper;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.DrawLogQuery;
import com.liuhe.redpacket.query.RedpacketQuery;
import com.liuhe.redpacket.service.IRedpacketService;
import com.liuhe.redpacket.utils.ConstUtil;
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

}
