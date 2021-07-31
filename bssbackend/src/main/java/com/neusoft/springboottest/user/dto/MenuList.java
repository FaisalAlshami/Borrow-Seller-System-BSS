package com.neusoft.springboottest.user.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.neusoft.springboottest.user.entity.SysMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MenuList implements Serializable {


    private Integer menuId;


    private String menuName;


    private String menuUrl;


    private String parentId;


    private String menuOrder;


    private String menuIcon;


    private String menuType;

    private List<SysMenu> subMenuList;
}
