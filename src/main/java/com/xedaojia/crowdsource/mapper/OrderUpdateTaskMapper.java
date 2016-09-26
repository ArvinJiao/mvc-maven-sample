package com.xedaojia.crowdsource.mapper;

import com.xedaojia.crowdsource.domain.OrderUpdateTask;

public interface OrderUpdateTaskMapper {

    int insertSelective(OrderUpdateTask record);

    OrderUpdateTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderUpdateTask record);

}