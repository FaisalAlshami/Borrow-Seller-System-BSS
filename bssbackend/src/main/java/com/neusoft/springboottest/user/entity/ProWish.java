package com.neusoft.springboottest.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProWish {

    private static final long serialVersionUID = 1L;

    @TableField(exist=false)
    private SysUser sysUser;


    @TableField(exist=false)
    private ProProduct proProduct;

    @TableId(value = "wish_id", type = IdType.AUTO)
    private Integer wishId;

    @TableField("pro_id")
    private Integer proId;

    @TableField("user_id")
    private Integer userId;

    @TableField("createDate")
    private String createDate;

    public  String toString(){
        String msg="";
        msg="wishId:"+wishId+", proId="+proId+", userId="+userId+", createDate="+createDate;
        if(sysUser!=null)
           msg +=", username="+sysUser.getUsername()+", NAME="+sysUser.getName();
        if(proProduct!=null)
           msg +=", Title="+proProduct.getTitle()+", remark="+proProduct.getRemark();
        return msg;
    }
}
