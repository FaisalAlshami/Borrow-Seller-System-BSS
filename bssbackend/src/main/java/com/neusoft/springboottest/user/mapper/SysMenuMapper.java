package com.neusoft.springboottest.user.mapper;

import com.neusoft.springboottest.user.dto.MenuList;
import com.neusoft.springboottest.user.dto.MenuRouters;
import com.neusoft.springboottest.user.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author phil
 * @since 2021-01-20
 */
@Mapper
@Component("SysMenuMapper")
public interface SysMenuMapper extends BaseMapper<SysMenu> {

   public List<MenuList> getAllMenuList();

   public List<MenuList> getMenuListByUserName(String name);

   public List<MenuList> getMenuListByUserName2(String name);
   List<MenuRouters> getAllMenuRouters();


}
