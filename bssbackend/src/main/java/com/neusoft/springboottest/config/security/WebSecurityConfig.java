package com.neusoft.springboottest.config.security;

import com.neusoft.springboottest.user.mapper.SysUserMapper;
import com.neusoft.springboottest.user.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
//                        测试用
                        "/*.jpg",
                        "/image/*.jpg",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
//                        ,"/**/*.jpg"
                ).permitAll()
                .antMatchers("/user/sys-user/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .and().logout().logoutUrl("/logout")
                .and()
                .csrf().disable();
                ;




        http.addFilterAt(customJSONLoginFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Autowired
    ISysUserService iSysUserService;



    public CustomJSONLoginFilter customJSONLoginFilter(){

        CustomJSONLoginFilter customJSONLoginFilter= new CustomJSONLoginFilter("/user/sys-user/login",iSysUserService);
        // verify username password right  will return userinfo to front-end
        customJSONLoginFilter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessfulHandler(iSysUserService));
        customJSONLoginFilter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
        return customJSONLoginFilter;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        // don't use encryption algorithm
//        return NoOpPasswordEncoder.getInstance();
        String encoderType= EncryptionAlgorithm.ENCODER_TYPE.get(1);
        PasswordEncoder passwordEncoder=  EncryptionAlgorithm.ENCODER_MAP.get(encoderType);

        return passwordEncoder;
    }



}
