package com.xedaojia.crowdsource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xedaojia.crowdsource.domain.OrderUpdateTask;
import com.xedaojia.crowdsource.mapper.OrderUpdateTaskMapper;
import com.xedaojia.crowdsource.service.OrderUpdateTaskService;

@Service
@Transactional
public class OrderUpdateTaskServiceImpl implements OrderUpdateTaskService{

	@Autowired
	private OrderUpdateTaskMapper orderUpdateTaskMapper;
	
	@Override
	public int save(OrderUpdateTask orderUpdateTask) {
		
		return orderUpdateTaskMapper.insertSelective(orderUpdateTask);
	}

}
