package com.xedaojia.crowdsource.mapper;

import com.xedaojia.crowdsource.domain.MainOrderInfo;

public interface MainOrderInfoMapper {
 
    int insertSelective(MainOrderInfo record);

    MainOrderInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MainOrderInfo record);
    
    MainOrderInfo selectByOrderId(String orderId);
}