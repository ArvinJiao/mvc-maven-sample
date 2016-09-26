package com.xedaojia.crowdsource.mapper;

import com.xedaojia.crowdsource.domain.MainOrderInfoExtend;

public interface MainOrderInfoExtendMapper {

    int insertSelective(MainOrderInfoExtend record);

    MainOrderInfoExtend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MainOrderInfoExtend record);

    MainOrderInfoExtend selectByOrderId(String orderId);
}