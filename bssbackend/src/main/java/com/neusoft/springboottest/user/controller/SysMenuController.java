package com.neusoft.springboottest.user.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.user.dto.MenuList;

import com.neusoft.springboottest.user.dto.MenuRouters;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.SysMenu;
import com.neusoft.springboottest.user.entity.SysUser;
import com.neusoft.springboottest.user.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author phil
 * @since 2021-01-20
 */
@RestController
@RequestMapping("/user/sys-menu")
public class SysMenuController {


    @Autowired
    ISysMenuService iSysMenuService;


    @GetMapping("getAllMenuList")
    public Message<List<MenuList>> getAllMenuList(){

        Message msg=new Message();
        List<MenuList> list=  iSysMenuService.getAllMenuList();

        msg.setObj(list);
        msg.setCode(200);
        msg.setMsg("get all menulist successful ");
        System.out.println("getAllMenuList :"+list);
        return msg;
    }
    @PostMapping("getMenuListByUserName")
    public Message<List<MenuList>> getMenuListByUserName(@RequestBody SysUser sysuser){


        Message msg=new Message();
        List<MenuList> list=  iSysMenuService.getMenuListByUserName(sysuser.getUsername());

        msg.setObj(list);
        msg.setCode(200);
        msg.setMsg("get all menulist successful ");

        return msg;
    }
    @PostMapping("getMenuRouters")
    public Message<List<MenuRouters>> getAllMenuRouters(){

        Message<List<MenuRouters>> message=new Message();
        message.setCode(200);
        message.setMsg("got menurouters");
        List<MenuRouters> list=   iSysMenuService.getAllMenuRouters();
        message.setObj(list);
        return message;
    }

    @PostMapping("getAllMenu")
    public Message getAllData(@RequestBody PageCondition pc) {
        Message msg = new Message();
        IPage<SysMenu> dataIPage = iSysMenuService.getMenuListByConditions(pc);
        List<SysMenu> list = dataIPage.getRecords();
        if (list != null && list.size() > 0) {
            msg.setCode(200);
            msg.setMsg("getAllUsers2 successful");
            msg.setObj(dataIPage);
        } else {
            msg.setCode(504);
            msg.setMsg("getAllUsers2 failure can't find data ");
        }
        return msg;
    }

    @PostMapping("Save")
    public Message Save(@RequestBody SysMenu sysMenu) {

        boolean ok = iSysMenuService.saveOrUpdate(sysMenu);
        Message msg = new Message();
        if (ok) {
            msg.setCode(200);
            msg.setMsg("save successful");
        } else {
            msg.setCode(504);
            msg.setMsg("save failure");
        }
        return msg;

    }

    @PostMapping("deleteById")
    public Message deleteById(@RequestBody SysMenu sysMenu) {

        boolean ok = iSysMenuService.removeById(sysMenu.getMenuId());
        Message msg = new Message();
        if (ok) {
            msg.setCode(200);
            msg.setMsg("delete successful");
        } else {
            msg.setCode(504);
            msg.setMsg("delete failure");
        }
        return msg;

    }
}
