package com.neusoft.springboottest.mapper;

import com.neusoft.springboottest.entity.Salegrade;

public interface SalegradeMapper {
    int insert(Salegrade record);

    int insertSelective(Salegrade record);
}