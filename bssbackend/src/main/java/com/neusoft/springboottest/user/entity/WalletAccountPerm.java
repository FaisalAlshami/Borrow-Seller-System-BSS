package com.neusoft.springboottest.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class WalletAccountPerm {
    @TableId(value = "buyer_id")
    private Integer buyer_id;

    @TableField("is_direct_deposit")
    private Integer is_direct_deposit;

    @TableField("is_direct_withdraw")
    private Integer is_direct_withdraw;

    @TableField("is_direct_transaction")
    private Integer is_direct_transaction;


    @TableField("add_deposit")
    private Integer add_deposit;

    @TableField("add_withdraw")
    private Integer add_withdraw;
}
