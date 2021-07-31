package com.neusoft.springboottest.user.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Reem
 * @since 2021-02-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("code_type")
    private String codeType;

    @TableField("type_cd")
    private String typeCd;

    @TableField("code_value")
    private String codeValue;

    @TableField("description")
    private String description;

}
