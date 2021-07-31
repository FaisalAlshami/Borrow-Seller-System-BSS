package com.neusoft.springboottest.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

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
public class Dept implements Serializable {

    @TableField(exist = false)
    private List<Emp> empList;

    private static final long serialVersionUID = 1L;

    @TableId(value = "DEPTNO", type = IdType.AUTO)
    private Integer deptno;

    @TableField("DNAME")
    private String dname;

    @TableField("LOC")
    private String loc;


}
