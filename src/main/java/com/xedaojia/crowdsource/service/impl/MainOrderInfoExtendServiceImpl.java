package com.xedaojia.crowdsource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xedaojia.crowdsource.domain.MainOrderInfoExtend;
import com.xedaojia.crowdsource.mapper.MainOrderInfoExtendMapper;
import com.xedaojia.crowdsource.service.MainOrderInfoExtendService;

@Service
@Transactional
public class MainOrderInfoExtendServiceImpl implements MainOrderInfoExtendService{

	@Autowired
	private MainOrderInfoExtendMapper mainOrderInfoExtendMapper;
	
	@Override
	public MainOrderInfoExtend getByOrderId(String orderId) {
		return mainOrderInfoExtendMapper.selectByOrderId(orderId);
	}

	@Override
	public int update(MainOrderInfoExtend mainOrderInfoExtend) {
		return mainOrderInfoExtendMapper.updateByPrimaryKeySelective(mainOrderInfoExtend);
	}
}
