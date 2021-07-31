package com.neusoft.springboottest.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class WalletCommission {
    private static final long serialVersionUID = 1L;

    @TableField(exist=false)
    CommissionType commissionType;

    @TableId(value = "commission_id", type = IdType.AUTO)
    private Integer commission_id;

    @TableField(value = "percent")
    private float percent;

    @TableField("transaction_type_id")
    private Integer transaction_type_id;

    @TableField("status")
    private Integer status;



}
