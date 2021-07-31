package com.neusoft.springboottest.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.ParParameter;
import com.neusoft.springboottest.user.mapper.ParParameterMapper;
import com.neusoft.springboottest.user.service.IParParameterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author phil
 * @since 2021-03-12
 */
@Service
public class ParParameterServiceImpl extends ServiceImpl<ParParameterMapper, ParParameter> implements IParParameterService {


    @Autowired
    ParParameterMapper parParameterMapper;


    /*** Get list of parameters and handling pagination ***/
    public IPage<ParParameter> getParameterList(PageCondition pageCondition) {
        QueryWrapper<ParParameter> paramQuery = Wrappers.query();
        IPage<ParParameter> page = new Page<>(pageCondition.getCurrentPage(), pageCondition.getPageSize());
        IPage<ParParameter> paramPage = parParameterMapper.selectPage(page, paramQuery);
        System.out.println("Total Page Number：" + paramPage.getPages());
        System.out.println("Total data：" + paramPage.getTotal());
        return paramPage;
    }

    public void insert(){

    }
}
