package com.xedaojia.crowdsource.mapper;

import com.xedaojia.crowdsource.domain.Ordertracks;

public interface OrdertracksMapper {

    int insertSelective(Ordertracks record);

    Ordertracks selectByPrimaryKey(Integer id);

}