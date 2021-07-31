package com.neusoft.springboottest.user.controller;


import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.user.dto.UserRole;
import com.neusoft.springboottest.user.service.ISysRoleService;
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
 * @since 2021-01-28
 */
@RestController
@RequestMapping("/user/sys-role")
public class SysRoleController {

    @Autowired
    ISysRoleService iSysRoleService;

    @PostMapping("verifyTargetUrl")
    public Message verifyUserRoleWithUserIdAndTargetUrl(@RequestBody UserRole userRole){
        System.out.println(userRole);

        Message msg=   new Message();
        int num=iSysRoleService.verifyUserRoleWithUserIdAndTargetUrl(userRole);
        if(num>0){
            msg.setCode(200);
            msg.setMsg("verifyTargetUrl ok");
        }else{
            msg.setCode(504);
            msg.setMsg("verifyTargetUrl can't visit");
        }
        return msg;
    }


}
