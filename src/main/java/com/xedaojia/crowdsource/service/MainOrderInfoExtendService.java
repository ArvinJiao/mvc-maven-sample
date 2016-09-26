package com.xedaojia.crowdsource.service;

import com.xedaojia.crowdsource.domain.MainOrderInfoExtend;

public interface MainOrderInfoExtendService {

	 MainOrderInfoExtend getByOrderId(String orderId);
	 int update(MainOrderInfoExtend  mainOrderInfoExtend);
}