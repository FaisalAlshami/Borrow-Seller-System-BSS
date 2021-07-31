package com.neusoft.springboottest.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProCart {

    private static final long serialVersionUID = 1L;

    @TableField(exist=false)
    private SysUser sysUser;

    @TableField(exist=false)
    private ProStatus proStatus;

    @TableField(exist=false)
    private ProProduct proProduct;

    @TableId(value = "Cart_id", type = IdType.AUTO)
    private Integer cartId;

    @TableField("pro_id")
    private Integer proId;

    @TableField("user_id")
    private Integer borrowUserId;

    @TableField("quantity")
    private Integer quantity;

    @TableField("createDate")
    private String createDate;

    public  String toString(){
        String msg="";
        msg="cartId:"+cartId+", proId="+proId+", userId="+borrowUserId+",quantity="+quantity+", createDate="+createDate;
        msg +=", username="+sysUser.getUsername()+", NAME="+sysUser.getName();
        msg +=", statusCode="+proStatus.getStatusCode()+", statusName="+proStatus.getStatusName();
        msg +=", Title="+proProduct.getTitle()+", remark="+proProduct.getRemark();
        return msg;
    }
}
