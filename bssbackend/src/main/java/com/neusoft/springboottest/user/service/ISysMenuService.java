package com.neusoft.springboottest.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.springboottest.user.dto.MenuList;
import com.neusoft.springboottest.user.dto.MenuRouters;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author phil
 * @since 2021-01-20
 */
public interface ISysMenuService extends IService<SysMenu> {


    public List<MenuList> getAllMenuList();

    public List<MenuList> getMenuListByUserName(String name);

    List<MenuRouters> getAllMenuRouters();
    public IPage<SysMenu> getMenuListByConditions(PageCondition pc);
}
