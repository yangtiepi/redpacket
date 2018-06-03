/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liuhe.redpacket.domain.UserCard;
import com.liuhe.redpacket.mapper.UserCardMapper;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.UserCardQuery;
import com.liuhe.redpacket.service.IUserCardService;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class UserCardServiceImpl implements IUserCardService{

	@Autowired
	private UserCardMapper UserCardMapper;
	
	
	@Override
	public void save(UserCard UserCard) {
		UserCardMapper.save(UserCard);
	}

	
	@Override
	public void update(UserCard UserCard) {
		UserCardMapper.update(UserCard);
	}

	
	@Override
	public void delete(Long id) {
		UserCardMapper.delete(id);
	}
	
	@Override
	public UserCard get(Long id) {
		return UserCardMapper.get(id);
	}

	
	@Override
	public List<UserCard> getAll() {
		return UserCardMapper.getAll();
	}

	
	@Override
	public PageResult<UserCard> query(UserCardQuery qu) {
		// 统计查询
		Long total = UserCardMapper.queryTotal(qu);
		// 分页查询
		List<UserCard> rows = UserCardMapper.query(qu);
		return new PageResult<UserCard>(rows, qu.getPageSize(),
				qu.getCurrentPage(), total.intValue());
	}

	@Override
	public List<UserCard> queryList(UserCardQuery userCardQuery) {
		return UserCardMapper.query(userCardQuery);
	}
}
