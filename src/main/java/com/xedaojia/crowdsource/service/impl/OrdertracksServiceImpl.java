package com.xedaojia.crowdsource.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xedaojia.crowdsource.domain.Ordertracks;
import com.xedaojia.crowdsource.mapper.OrdertracksMapper;
import com.xedaojia.crowdsource.service.OrdertracksService;

@Service
@Transactional
public class OrdertracksServiceImpl implements OrdertracksService{

	private static final Logger logger = LoggerFactory.getLogger(OrdertracksServiceImpl.class);
	
	@Autowired
	private OrdertracksMapper ordertracksMapper;
	
	@Override
	public int save(Ordertracks ordertracks) {
		int r = 0;
		try {
			r = ordertracksMapper.insertSelective(ordertracks);
		} catch (Exception e) {
			logger.error("保存订单轨迹异常", e);
		}
		return r;
	}

}
