package com.xedaojia.crowdsource.service;

import java.util.Map;

import com.xedaojia.crowdsource.domain.OrderStatusCallback;

public interface OrderStatusCallbackService {
	
	Map<String,Object> process(OrderStatusCallback orderStatusCallback) throws Exception;
	
	int save(OrderStatusCallback orderStatusCallback);

}
