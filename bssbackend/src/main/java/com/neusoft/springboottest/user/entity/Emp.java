package com.neusoft.springboottest.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;
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
public class Emp extends Model<Emp> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(exist=false)
    private Dept dept;


    @TableId(value = "EMPNO", type = IdType.AUTO)
//    @TableId(value = "EMPNO", type = IdType.INPUT)
    private Integer empno;

//    @TableField(value="ENAME",condition = SqlCondition.LIKE)
    @TableField(value="ENAME")
    private String ename;

    @TableField("JOB")
    private String job;

    @TableField("MGR")
    private Integer mgr;

    @TableField("HIREDATE")
    private LocalDate hiredate;

//    @TableField(value="SAL",condition = "%s &lt;#{%s}")
    @TableField(value="SAL")
    private Double sal;

    @TableField("COMM")
    private Double comm;

    @TableField("DEPTNO")
    private Integer deptno;


}
