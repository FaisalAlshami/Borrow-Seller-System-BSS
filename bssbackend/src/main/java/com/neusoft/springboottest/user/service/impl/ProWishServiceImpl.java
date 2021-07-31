package com.neusoft.springboottest.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.ProWish;
import com.neusoft.springboottest.user.mapper.ProWishMapper;
import com.neusoft.springboottest.user.service.IProWishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProWishServiceImpl extends ServiceImpl<ProWishMapper, ProWish> implements IProWishService {

    @Autowired
    ProWishMapper proWishMapper;


    public IPage<ProWish> getWishByBorrowUserId(PageCondition pc){
        //return proCartMapper.selectByBorrowUserId(id);
        //  Conditional constructor
       QueryWrapper<ProWish> wishQuery = Wrappers.query();
        wishQuery.eq("user_id",pc.getUserId());
        IPage<ProWish> page = new Page<ProWish>(pc.getCurrentPage(), pc.getPageSize());
        IPage<ProWish> wishPage = proWishMapper.selectPage(page,wishQuery);
        System.out.println("total pages num：" + wishPage.getPages());
        System.out.println("total：" + wishPage.getTotal());
        return wishPage;
    }

    public List<ProWish> getListWishByBorrowUserId(PageCondition pc){
        return proWishMapper.selectByBorrowUserId(pc.getUserId());
    }
    public List<ProWish> verfiyWishByProIdUserId(Integer pid, Integer uid){
        return proWishMapper.verfiyWishByProIdUserId(pid,uid);
    }
}
