package com.neusoft.springboottest.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.ProCart;
import com.neusoft.springboottest.user.entity.ProProduct;
import com.neusoft.springboottest.user.mapper.ProCartMapper;
import com.neusoft.springboottest.user.mapper.ProProductMapper;
import com.neusoft.springboottest.user.service.IProCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProCartServiceImpl extends ServiceImpl<ProCartMapper, ProCart> implements IProCartService {

    @Autowired
    ProCartMapper proCartMapper;

    public IPage<ProCart> getCartConditions(PageCondition pc){

        //  Conditional constructor
        QueryWrapper<ProCart> userQuery = Wrappers.query();
        IPage<ProCart> page = new Page<ProCart>(pc.getCurrentPage(), pc.getPageSize());
        IPage<ProCart> cartPage = proCartMapper.selectPage(page,userQuery);
        System.out.println("total pages num：" + cartPage.getPages());
        System.out.println("total：" + cartPage.getTotal());
        return cartPage;
    }
    public List<ProCart> getCartByBorrowUserId(Integer id){
        return proCartMapper.selectByBorrowUserId(id);
        //  Conditional constructor
     /*   QueryWrapper<ProCart> cartQuery = Wrappers.query();
        //cartQuery.eq("user_id",id);
        IPage<ProCart> page = new Page<ProCart>(pc.getCurrentPage(), pc.getPageSize());
        IPage<ProCart> cartPage = proCartMapper.selectPage(page,cartQuery);
        System.out.println("total pages num：" + cartPage.getPages());
        System.out.println("total：" + cartPage.getTotal());
        return cartPage;*/
    }
    public List<ProCart> getCartByBrandUserId(Integer branduserid){
        return proCartMapper.selectByBrandUserId(branduserid);
    }
}
