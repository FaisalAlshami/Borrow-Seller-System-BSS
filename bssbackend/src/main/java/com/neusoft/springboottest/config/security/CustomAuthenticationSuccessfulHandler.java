package com.neusoft.springboottest.config.security;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.user.entity.SysUser;
import com.neusoft.springboottest.user.mapper.SysUserMapper;
import com.neusoft.springboottest.user.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class CustomAuthenticationSuccessfulHandler implements AuthenticationSuccessHandler {

    public CustomAuthenticationSuccessfulHandler(ISysUserService iSysUserService){
        this.iSysUserService=iSysUserService;
    }

    ISysUserService iSysUserService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {


//        QueryWrapper qw=new QueryWrapper();
//        qw.eq("username",authentication.getPrincipal());
//        SysUser user=sysUserMapper.selectOne(qw);

        SysUser user=iSysUserService.getUserInfoByUsername(authentication.getPrincipal().toString());

        System.out.println("**************login verify ok authentication:"+authentication);
        System.out.println(authentication.getCredentials());

        Message msg=new Message();
        msg.setCode(200);
        msg.setMsg("login successful");
        msg.setObj(user);
//        WebResponse response = WebResponse.success("登录成功");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        httpServletResponse.getWriter().write(JSON.toJSONString(msg));


    }
}
