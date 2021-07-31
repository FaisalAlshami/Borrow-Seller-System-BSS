package com.neusoft.springboottest.user.entity;

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
public class Salgrade implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("GRADE")
    private Integer grade;

    @TableField("LOSAL")
    private Integer losal;

    @TableField("HISAL")
    private Integer hisal;


}
