package com.xedaojia.crowdsource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xedaojia.crowdsource.domain.MainOrderInfo;
import com.xedaojia.crowdsource.mapper.MainOrderInfoMapper;
import com.xedaojia.crowdsource.service.MainOrderInfoService;

@Service
@Transactional
public class MainOrderInfoServiceImpl implements MainOrderInfoService{

	@Autowired
	private MainOrderInfoMapper mainOrderInfoMapper;

	@Override
	public MainOrderInfo getById(Integer id) {
		return mainOrderInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int update(MainOrderInfo mainOrderInfo) {
		return mainOrderInfoMapper.updateByPrimaryKeySelective(mainOrderInfo);
	}

	@Override
	public MainOrderInfo getByOrderId(String orderId) {
		return mainOrderInfoMapper.selectByOrderId(orderId);
	}
	
}
