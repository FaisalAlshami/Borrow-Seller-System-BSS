package com.neusoft.springboottest.user.dto;

import java.math.BigDecimal;

public class WithdrawItem {

    int user_id;
    float withdraw_money;
    String password;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public float getWithdraw_money() {
        return withdraw_money;
    }

    public void setWithdraw_money(float withdraw_money) {
        this.withdraw_money = withdraw_money;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "WithdrawItem{" +
                "user_id=" + user_id +
                ", withdraw_money=" + withdraw_money +
                ", password='" + password + '\'' +
                '}';
    }
}
