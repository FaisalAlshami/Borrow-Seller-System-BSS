package com.neusoft.springboottest.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ManManufacturer {

    private static final long serialVersionUID = 1L;


    @TableId(value = "MAN_ID")
    private Integer MAN_ID ;

    @TableField("NAME_EN")
    private String NAME_EN ;

    @TableField("NAME_CN")
    private String NAME_CN ;

    @TableField("GMC_REPORT_TYPE")
    private String GMC_REPORT_TYPE ;

    @TableField("GMC_REPORT_URL")
    private String GMC_REPORT_URL;

    @TableField("DESCRIPTION")
    private String DESCRIPTION ;

    @TableField("CREATED_BY")
    private String CREATED_BY ;

    @TableField("CREATION_DATE")
    private String CREATION_DATE ;

    @TableField("LAST_UPDATE_BY")
    private String LAST_UPDATE_BY;

    @TableField("LAST_UPDATE_DATE")
    private String LAST_UPDATE_DATE;

    @TableField("CALL_CNT")
    private Integer CALL_CNT;

    @TableField("REMARK")
    private String REMARK;

    @Override
    public String toString() {
        return "ManManufacturer{" +
                " MAN_ID=" + MAN_ID +
                ", NAME_EN='" + NAME_EN + '\'' +
                ", NAME_CN='" + NAME_CN + '\'' +
                ", GMC_REPORT_TYPE='" + GMC_REPORT_TYPE + '\'' +
                ", GMC_REPORT_URL='" + GMC_REPORT_URL + '\'' +
                ", DESCRIPTION='" + DESCRIPTION + '\'' +
                ", CREATED_BY='" + CREATED_BY + '\'' +
                ", CREATION_DATE='" + CREATION_DATE + '\'' +
                ", LAST_UPDATE_BY='" + LAST_UPDATE_BY + '\'' +
                ", LAST_UPDATE_DATE='" + LAST_UPDATE_DATE + '\'' +
                ", CALL_CNT=" + CALL_CNT +
                ", REMARK='" + REMARK + '\'' +
                ", STS_CD='" + STS_CD + '\'' +
                '}';
    }

    @TableField("STS_CD")
    private String STS_CD;
}
