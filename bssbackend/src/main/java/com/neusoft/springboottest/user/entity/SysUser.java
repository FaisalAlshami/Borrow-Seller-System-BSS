package com.neusoft.springboottest.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author phil
 * @since 2021-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "USER_ID", type = IdType.INPUT)
    private Integer userId;

    @TableField("USERNAME")
    private String username;

    @TableField("PASSWORD")
    private String password;

    @TableField("NAME")
    private String name;

    @TableField("RIGHTS")
    private String rights;

    @TableField("ROLE_ID")
    private String roleId;

    @TableField("LAST_LOGIN")
    private String lastLogin;

    @TableField("IP")
    private String ip;

    @TableField("STATUS")
    private String status;

    @TableField("BZ")
    private String bz;

    @TableField("SKIN")
    private String skin;

    @TableField("EMAIL")
    private String email;

    @TableField("NUMBER")
    private String number;

    @TableField("PHONE")
    private String phone;

    @TableField("MAN_BUYER_ID")
    private Integer manBuyerId;

    @TableField("img_url")
    private  String imgUrl;


    @TableField("ROLE_NAME")
    private String role_Name;
}
