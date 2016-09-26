package com.xedaojia.crowdsource.service;

import com.xedaojia.crowdsource.domain.MainOrderInfo;

public interface MainOrderInfoService {
	
	MainOrderInfo getById(Integer id);
	
	int update(MainOrderInfo mainOrderInfo);
	
	MainOrderInfo getByOrderId(String orderId);

}
