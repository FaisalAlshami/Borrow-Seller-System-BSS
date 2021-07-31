package com.neusoft.springboottest.user.controller;


import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.user.entity.SysUser;
import com.neusoft.springboottest.user.entity.SysUserRoleRelationship;
import com.neusoft.springboottest.user.service.ISysUserRoleRelationshipService;
import com.neusoft.springboottest.user.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author phil
 * @since 2021-03-19
 */
@RestController
@RequestMapping("/user/sys-user-role-relationship")
public class SysUserRoleRelationshipController {


    @Autowired
    ISysUserRoleRelationshipService iSysUserRoleRelationshipService;
    @Autowired
    ISysUserService iSysUserService;

    @PostMapping("addRoleByUser")
    public Message addRoleByUser(@RequestBody SysUserRoleRelationship sysUserRoleRelationship) {
        boolean ok = iSysUserRoleRelationshipService.save(sysUserRoleRelationship);
        Message msg = new Message();
        if (ok) {
            msg.setCode(200);
            msg.setMsg("active successful");
        } else {
            msg.setCode(504);
            msg.setMsg("active  failure");
        }
        return msg;

    }
    @PostMapping("deleteById")
    public Message deleteById(@RequestBody SysUser sysUser) {

        int ok = iSysUserRoleRelationshipService.deleteList(sysUser.getUserId());
        Message msg = new Message();
        if (ok==1) {
            msg.setCode(200);
            msg.setMsg("delete successful");
        } else {
            msg.setCode(504);
            msg.setMsg("delete failure");
        }
        return msg;

    }

}
