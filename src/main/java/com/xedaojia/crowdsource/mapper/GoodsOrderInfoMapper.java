package com.xedaojia.crowdsource.mapper;

import com.xedaojia.crowdsource.domain.GoodsOrderInfo;

public interface GoodsOrderInfoMapper {

    int insertSelective(GoodsOrderInfo record);

    GoodsOrderInfo selectByPrimaryKey(String goodsOrderId);

    int updateByPrimaryKeySelective(GoodsOrderInfo record);

}