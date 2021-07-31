package com.neusoft.springboottest.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2021-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ParParameter implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(exist=false)
    private SysUser sysUser;

    @TableId(value = "PAR_ID", type = IdType.AUTO)
    private Integer parId;

    @TableField("PARAM_CD")
    private String paramCd;

    @TableField("PARAM_VALUE")
    private String paramValue;

    @TableField("DESCRIPTION")
    private String description;

    @TableField("CREATED_BY")
    private String createdBy;

    @TableField("CREATION_DATE")
    private LocalDateTime creationDate;

    @TableField("LAST_UPDATE_BY")
    private String lastUpdateBy;

    @TableField("LAST_UPDATE_DATE")
    private LocalDateTime lastUpdateDate;

    @TableField("CALL_CNT")
    private Integer callCnt;

    @TableField("REMARK")
    private String remark;

    @TableField("STS_CD")
    private String stsCd;


}
