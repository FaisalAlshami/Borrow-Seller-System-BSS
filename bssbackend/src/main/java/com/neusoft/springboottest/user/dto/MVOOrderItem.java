package com.neusoft.springboottest.user.dto;

import lombok.Data;

@Data
public class MVOOrderItem {
    int user_id;
    int status_id;
    int pro_id;
    int o_id;
    String cancelreason;
    String trackingCompany;
}
