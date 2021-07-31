package com.neusoft.springboottest.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommissionType {
    private static final long serialVersionUID = 1L;

    @TableId(value = "transaction_type_id", type = IdType.AUTO)
    private Integer transaction_type_id;

    @TableField("transaction_type_name")
    private String transaction_type_name;

}
