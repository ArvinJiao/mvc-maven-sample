package com.xedaojia.crowdsource.mapper;

import com.xedaojia.crowdsource.domain.OrderStatusCallback;

public interface OrderStatusCallbackMapper {

    int insertSelective(OrderStatusCallback record);

    OrderStatusCallback selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderStatusCallback record);

    
}