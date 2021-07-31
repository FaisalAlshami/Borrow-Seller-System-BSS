package com.neusoft.springboottest.user.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CommissionItem {
    Integer user_id;
    Integer commission_id;
    Integer commission_type;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getCommission_id() {
        return commission_id;
    }

    public void setCommission_id(Integer commission_id) {
        this.commission_id = commission_id;
    }

    public Integer getCommission_type() {
        return commission_type;
    }

    public void setCommission_type(Integer commission_type) {
        this.commission_type = commission_type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    float amount;

}
