package com.neusoft.springboottest.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.springboottest.user.dto.MenuList;
import com.neusoft.springboottest.user.dto.MenuRouters;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.Emp;
import com.neusoft.springboottest.user.entity.SysMenu;
import com.neusoft.springboottest.user.mapper.SysMenuMapper;
import com.neusoft.springboottest.user.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author phil
 * @since 2021-01-20
 */
@Service
@CacheConfig(cacheNames="test1cache")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;

    public List<MenuList> getAllMenuList(){
        return sysMenuMapper.getAllMenuList();
    }


    @Cacheable(key="#name")
    public List<MenuList> getMenuListByUserName(String name){

        List<MenuList> list= sysMenuMapper.getMenuListByUserName2(name);
        return list;
    }

    public List<MenuRouters> getAllMenuRouters(){
        List<MenuRouters> list= sysMenuMapper.getAllMenuRouters();


        for(MenuRouters mr:list){

            System.out.println(mr);
        }

        return list;
    }

    public IPage<SysMenu> getMenuListByConditions(PageCondition pc) {

        //  Conditional constructor
        QueryWrapper<SysMenu> dataQuery = Wrappers.query();

        IPage<SysMenu> page = new Page<SysMenu>(pc.getCurrentPage(), pc.getPageSize());
        IPage<SysMenu> dataPage = sysMenuMapper.selectPage(page,dataQuery);
        System.out.println("total pages num：" + dataPage.getPages());
        System.out.println("total：" + dataPage.getTotal());


        return dataPage;
//

    }



}
