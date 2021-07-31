package com.neusoft.springboottest.user.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserRole implements Serializable {

    private  Integer userId;

    private  String targetUrl;

}
