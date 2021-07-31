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
public class SysCompany {
    private static final long serialVersionUID = 1L;
    @TableField(exist=false)
    private SysUser sysUser;

    @TableId("user_id")
    private Integer userId ;

    @TableField("company_name")
    private String companyName ;

    @TableField("company_phone")
    private String companyPhone ;

    @TableField("company_address")
    private String companyAddress ;

    @TableField("company_description")
    private String companyDescription;
}
