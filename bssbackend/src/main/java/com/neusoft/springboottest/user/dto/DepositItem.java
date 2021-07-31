package com.neusoft.springboottest.user.dto;

import java.math.BigDecimal;

public class DepositItem {

    int user_id;
    float depositing_money;
    String password;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public float getDepositing_money() {
        return depositing_money;
    }

    public void setDepositing_money(float depositing_money) {
        this.depositing_money = depositing_money;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DepositItem{" +
                "user_id=" + user_id +
                ", depositing_money=" + depositing_money +
                ", password='" + password + '\'' +
                '}';
    }
}
