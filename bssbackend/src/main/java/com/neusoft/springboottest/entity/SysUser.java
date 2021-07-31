package com.neusoft.springboottest.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUser implements Serializable {

    private Integer userId;

    private String username;

    private String password;

    private String name;

    private String rights;

    private String roleId;

    private String lastLogin;

    private String ip;

    private String status;

    private String bz;

    private String skin;

    private String email;

    private String number;

    private String phone;

    private Integer manBuyerId;

    private static final long serialVersionUID = 1L;





}
