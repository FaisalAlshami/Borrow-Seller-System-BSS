package com.neusoft.springboottest.user.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageCondition implements Serializable {

    private Integer pageSize;

    private Integer currentPage;

    private Integer userId;
    private String username;

}
