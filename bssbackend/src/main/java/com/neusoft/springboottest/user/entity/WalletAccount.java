package com.neusoft.springboottest.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class WalletAccount {
    private static final long serialVersionUID = 1L;

    @TableField(exist=false)
    WalletAccountFund walletAccountFund;

    @TableField(exist=false)
    WalletAccountPerm walletAccountPerm;


    @TableId(value = "BUYER_ID")
    private Integer BUYER_ID;

    @TableField("ACCOUNT_NAME")
    private String ACCOUNT_NAME;

    @TableField("EMAIL")
    private String EMAIL;

    @TableField("PASSWORD")
    private String PASSWORD;

    @TableField("ACTIVE_TIME")
    private String ACTIVE_TIME;

    @TableField("IS_ACTIVE")
    private String IS_ACTIVE;

    @TableField("STATUS")
    private Integer STATUS;

    @TableField("CREATE_BY")
    private String CREATE_BY;

    @TableField("CREATE_TIME")
    private String CREATE_TIME;

    @TableField("LAST_UPDATE_BY")
    private String LAST_UPDATE_BY;

    @TableField("LAST_UPDATE_TIME")
    private String LAST_UPDATE_TIME;

    @TableField("ACCOUNT_TYPE")
    private Integer ACCOUNT_TYPE;

    @TableField("HOLD_ORDER_TIME")
    private String HOLD_ORDER_TIME;

    @TableField("AUTO_PAY_STATUS")
    private String AUTO_PAY_STATUS;
}
