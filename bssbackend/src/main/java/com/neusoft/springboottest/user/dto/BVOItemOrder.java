package com.neusoft.springboottest.user.dto;

import lombok.Data;

@Data
public class BVOItemOrder {
    int user_id;
    int cart_id;
    int pro_id;
    int o_id;
    int status_id;
    String dropshop;

}
