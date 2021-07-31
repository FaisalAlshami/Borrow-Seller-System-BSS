package com.neusoft.springboottest.user.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentInfoByBVO {
    int o_id;
    int user_id;
    String receive_name;
    String receive_address;
    String receive_phone;
    BigDecimal price;
}
