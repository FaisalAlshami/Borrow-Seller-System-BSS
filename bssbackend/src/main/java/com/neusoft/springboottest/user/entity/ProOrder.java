package com.neusoft.springboottest.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProOrder {

    private static final long serialVersionUID = 1L;

    @TableField(exist=false)
    private ProStatus proStatus;

    @TableField(exist=false)
    private ProProduct proProduct;

    @TableId(value = "or_id", type = IdType.AUTO)
    private Integer or_id ;

    @TableField(value = "bvo_user_id")
    private Integer bvo_user_id ;

    @TableField(value = "man_id")
    private Integer man_id ;

    @TableField(value = "STR_ID")
    private Integer STR_ID ;

    @TableField(value = "pro_id")
    private Integer pro_id ;

    @TableField(value = "sts_cd")
    private Integer sts_cd ;

    @TableField("ORDER_ID")
    private String ORDER_ID ;

    @TableField("SALES_PRICE")
    private BigDecimal SALES_PRICE ;

    @TableField("QTY")
    private Integer QTY;

    @TableField("SKU_NO")
    private String SKU_NO ;

    @TableField("receive_name")
    private String receive_name;

    @TableField("receive_address")
    private String receive_address ;

    @TableField("receive_phone")
    private String receive_phone;

    @TableField("tracking_company")
    private String tracking_company;

    @TableField("tracking_number")
    private String tracking_number;

    @TableField("ORDER_CREATED_TIME")
    private String ORDER_CREATED_TIME;

    @TableField("PAID_TIME")
    private String PAID_TIME;

    @TableField("paid_by")
    private String paid_by;

    @TableField("ship_time")
    private String ship_time;

    @TableField("ship_by")
    private String ship_by;

    @TableField("completed_time")
    private String completed_time;

    @TableField("completed_by")
    private String completed_by;

    @TableField("canceled_by")
    private String canceled_by;

    @TableField("cancelled_time")
    private String cancelled_time;

    @TableField("canceled_reason")
    private String canceled_reason;

    @TableField("transaction_withdraw_id")
    private Integer transaction_withdraw_id;

    @TableField("transaction_deposit_id")
    private Integer transaction_deposit_id;

    @Override
    public String toString() {
        return "ProOrder{" +
                "or_id=" + or_id +
                ", bvo_user_id=" + bvo_user_id +
                ", man_id=" + man_id +
                ", STR_ID=" + STR_ID +
                ", pro_id=" + pro_id +
                ", ORDER_ID=" + ORDER_ID +
                ", sts_cd=" + sts_cd +
                ", SALES_PRICE='" + SALES_PRICE + '\'' +
                ", QTY=" + QTY +
                ", SKU_NO='" + SKU_NO + '\'' +
                ", receive_name='" + receive_name + '\'' +
                ", receive_address='" + receive_address + '\'' +
                ", receive_phone='" + receive_phone + '\'' +
                ", tracking_company='" + tracking_company + '\'' +
                ", tracking_number=" + tracking_number +
                ", ORDER_CREATED_TIME='" + ORDER_CREATED_TIME + '\'' +
                ", PAID_TIME='" + PAID_TIME + '\'' +
                ", paid_by='" + paid_by + '\'' +
                ", ship_time='" + ship_time + '\'' +
                ", ship_by='" + ship_by + '\'' +
                ", completed_time='" + completed_time + '\'' +
                ", completed_by='" + completed_by + '\'' +
                ", canceled_by='" + canceled_by + '\'' +
                ", cancelled_time='" + cancelled_time + '\'' +
                ", canceled_reason='" + canceled_reason + '\'' +
                ", transaction_withdraw_id='" + transaction_withdraw_id + '\'' +
                ", transaction_deposit_id='" + transaction_deposit_id + '\'' +
                '}';
    }
}
