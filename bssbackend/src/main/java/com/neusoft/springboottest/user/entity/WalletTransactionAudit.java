package com.neusoft.springboottest.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class WalletTransactionAudit {
    private static final long serialVersionUID = 1L;

    @TableField(exist=false)
    CommissionType commissionType;

    @TableId(value = "TRANSACTION_AUDIT_ID", type = IdType.AUTO)
    private Integer TRANSACTION_AUDIT_ID;

    @TableField(value = "BUYER_ID")
    private Integer BUYER_ID;

    @TableField("TRANSACTION_ID")
    private Integer TRANSACTION_ID;

    @TableField("AVAILABLE_MONEY_BEFORE")
    private float AVAILABLE_MONEY_BEFORE;

    @TableField("DEPOSITING_MONEY_BEFORE")
    private float DEPOSITING_MONEY_BEFORE;

    @TableField("WITHDRAWING_MONEY_BEFORE")
    private float WITHDRAWING_MONEY_BEFORE;

    @TableField("COMMISSION_BEFORE")
    private float COMMISSION_BEFORE;
    ////////////
    @TableField("OPERATE_MONEY")
    private float OPERATE_MONEY;

    @TableField("OPERATE_TYPE")
    private Integer OPERATE_TYPE;

    ///////////
    @TableField("AVAILABLE_MONEY_AFTER")
    private float AVAILABLE_MONEY_AFTER;

    @TableField("DEPOSITING_MONEY_AFTER")
    private float DEPOSITING_MONEY_AFTER;

    @TableField("WITHDRAWING_MONEY_AFTER")
    private float WITHDRAWING_MONEY_AFTER;

    @TableField("COMMISSION_AFTER")
    private float COMMISSION_AFTER;

    /////////////////////

    @TableField("DEPOSITING_MONEY_ACTUAL")
    private float DEPOSITING_MONEY_ACTUAL;

    @TableField("WITHDRAWING_MONEY_ACTUAL")
    private float WITHDRAWING_MONEY_ACTUAL;

    @TableField("COMMISSION_MONEY_ACTUAL")
    private float COMMISSION_MONEY_ACTUAL;

    @TableField("TRANSACTION_TYPE_ID")
    private Integer TRANSACTION_TYPE_ID;

    @TableField("IS_CANCEL")
    private Integer IS_CANCEL;

    @TableField("REASON_CANCEL")
    private String REASON_CANCEL;

    ///////////////
    @TableField("OPERATE_BY")
    private String OPERATE_BY;

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

    @TableField("order_id")
    private Integer order_id;




}

