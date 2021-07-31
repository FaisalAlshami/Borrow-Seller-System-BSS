package com.neusoft.springboottest.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class WalletFundOrder {
    private static final long serialVersionUID = 1L;

    @TableField(exist=false)
    WalletCommission walletCommission;

    @TableField(exist=false)
    CommissionType commissionType;

    @TableId(value = "Buyer_Id")
    private Integer Buyer_Id;

    @TableField("depositing_money")
    private float depositing_money;

    @TableField("withdrawing_money")
    private float withdrawing_money;

    @TableField("commission")
    private float commission;


    @TableField("transaction_type_id")
    private Integer transaction_type_id;

    @TableField("create_by")
    private String create_by;

    @TableField("create_time")
    private String create_time;


}
