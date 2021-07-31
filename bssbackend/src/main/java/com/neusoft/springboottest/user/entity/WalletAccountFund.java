package com.neusoft.springboottest.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class WalletAccountFund {
    private static final long serialVersionUID = 1L;

    @TableId(value = "BUYER_ID")
    private Integer BUYER_ID;

    @TableField("AVAILABLE_MONEY")
    private BigDecimal AVAILABLE_MONEY;

    @TableField("DEPOSITING_MONEY")
    private float DEPOSITING_MONEY;

    @TableField("WITHDRAWING_MONEY")
    private float WITHDRAWING_MONEY;

    @TableField("COMMISSION")
    private float COMMISSION;




    @TableField("CREATE_BY")
    private String CREATE_BY;

    @TableField("CREATE_TIME")
    private String CREATE_TIME;

    @TableField("LAST_UPDATE_BY")
    private String LAST_UPDATE_BY;

    @TableField("LAST_UPDATE_TIME")
    private String LAST_UPDATE_TIME;

    @TableField("CURRENCY")
    private String CURRENCY;

}
