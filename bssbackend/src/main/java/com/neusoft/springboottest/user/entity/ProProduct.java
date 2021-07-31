package com.neusoft.springboottest.user.entity;

import java.math.BigDecimal;
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
 * @since 2021-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "PRO_ID", type = IdType.AUTO)
    private Integer proId;

    @TableField("USER_ID")
    private Integer userId;

    @TableField("SKU_CD")
    private String skuCd;

    @TableField("BRD_ID")
    private Integer brdId;

    @TableField("MAN_ID")
    private Integer manId;

    @TableField("TITLE")
    private String title;

    @TableField("BRD_NAME")
    private String brdName;

    @TableField("UPC")
    private String upc;

    @TableField("EAN")
    private String ean;

    @TableField("MODEL")
    private String model;

    @TableField("WARRANTY_DAY")
    private String warrantyDay;

    @TableField("RETAIL_PRICE")
    private BigDecimal retailPrice;

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

    @TableField("MININUM_RETAIL_PRICE")
    private BigDecimal mininumRetailPrice;

    @TableField("REPLENISHMENT_PERIOD")
    private Integer replenishmentPeriod;

    @TableField("KEY_WORDS")
    private String keyWords;

    @TableField("WARRANTY")
    private String warranty;

    @TableField("TIME_UNIT")
    private String timeUnit;

    @TableField("STOCKSETING")
    private Integer stockseting;
    @TableField("HEIGHT")
    private  String height;

    @TableField("WIDTH")
    private String width;

    @TableField("WEIGHT")
    private String weight;

    @TableField("LENGTH")
    private  String length;

    @TableField("EBAY_DESCRIPTION")
    private  String  ebayDescription;

    @TableField("AMAZON_DESCRIPTION")
    private String amazonDescription;


}
