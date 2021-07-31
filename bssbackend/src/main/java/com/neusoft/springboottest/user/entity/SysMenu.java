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
 * @since 2021-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "MENU_ID", type = IdType.AUTO)
    private Integer menuId;

    @TableField("MENU_NAME")
    private String menuName;

    @TableField("MENU_URL")
    private String menuUrl;

    @TableField("PARENT_ID")
    private String parentId;

    @TableField("MENU_ORDER")
    private String menuOrder;

    @TableField("MENU_ICON")
    private String menuIcon;

    @TableField("MENU_TYPE")
    private String menuType;


}
