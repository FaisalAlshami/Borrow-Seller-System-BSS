package com.neusoft.springboottest.config.security;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neusoft.springboottest.user.entity.SysRole;
import com.neusoft.springboottest.user.entity.SysUser;
import com.neusoft.springboottest.user.service.ISysUserService;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CustomJSONLoginFilter  extends AbstractAuthenticationProcessingFilter {

    private final ISysUserService iUserService;

    public CustomJSONLoginFilter(String defaultFilterProcessesUrl, ISysUserService iUserService) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl, HttpMethod.POST.name()));
        this.iUserService = iUserService;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        JSONObject requestBody=  getRequestBody(httpServletRequest);
       String username= requestBody.getString("username");
       String password= requestBody.getString("password");

        // get user info  by username
       SysUser sysUser= iUserService.getUserInfoByUsername(username);

        //verify password
        String encorderType=EncryptionAlgorithm.ENCODER_TYPE.get(1);
        PasswordEncoder passwordEncoder =EncryptionAlgorithm.ENCODER_MAP.get(encorderType);
        System.out.println(passwordEncoder);
        System.out.println(sysUser);
        System.out.println(password);
        if(sysUser==null){
            throw new UsernameNotFoundException("can't find userinfo by username:"+username);
        }else if(!passwordEncoder.matches(password,sysUser.getPassword())){
            throw new BadCredentialsException("password wrong!");
        }else{
            // find user's roles
            // find roles by username
          List<SysRole> list= iUserService.findRolesByUsername(username);
          List<SimpleGrantedAuthority> simpleGrantedAuthorities=  new ArrayList<SimpleGrantedAuthority>();


          Iterator<SysRole> i=list.iterator();
          while(i.hasNext()){
              simpleGrantedAuthorities.add(new SimpleGrantedAuthority(i.next().getRoleName()));
          }

          return new UsernamePasswordAuthenticationToken(username,password,simpleGrantedAuthorities);
        }




    }


    /**
     * 获取请求体
     */
    private JSONObject getRequestBody(HttpServletRequest request) throws AuthenticationException{
        try {
            StringBuilder stringBuilder = new StringBuilder();
            InputStream inputStream = request.getInputStream();
            byte[] bs = new byte[StreamUtils.BUFFER_SIZE];
            int len;
            while ((len = inputStream.read(bs)) != -1) {
                stringBuilder.append(new String(bs, 0, len));
            }
            return JSON.parseObject(stringBuilder.toString());
        } catch (IOException e) {
            System.out.println("get request body error.");
        }
        throw new AuthenticationServiceException("invalid request body");
    }


}
