package com.neusoft.springboottest.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProStatus {

    private static final long serialVersionUID = 1L;


    @TableId(value = "status_code")
    private Integer statusCode;

    @TableField("status_name")
    private String  statusName;
}
