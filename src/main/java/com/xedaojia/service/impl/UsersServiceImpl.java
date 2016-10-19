package com.xedaojia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xedaojia.domain.Users;
import com.xedaojia.mapper.UsersMapper;
import com.xedaojia.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	UsersMapper userMapper;
	
	@Override
	@Transactional
	public Users getById(Integer id) {
		Users users = userMapper.selectWithLock(id);
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return users;
	}

	

}
