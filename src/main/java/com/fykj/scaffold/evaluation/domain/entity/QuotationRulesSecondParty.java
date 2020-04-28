package com.fykj.scaffold.evaluation.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fykj.scaffold.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 *  报价规则乙方
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("quotation_rules_second_party")
public class QuotationRulesSecondParty extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 测试分类
     */
    @TableField("type")
    @ApiModelProperty(value = "测试分类")
    private String type;

    /**
     * 起
     */
    @TableField("start_num")
    @ApiModelProperty(value = "起")
    private Integer startNum;

    /**
     * 止
     */
    @TableField("stop_num")
    @ApiModelProperty(value = "止")
    private Integer stopNum;

    /**
     * 单价
     */
    @TableField("unit_prise")
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrise;

    /**
     * 固定价格（性能测试）
     */
    @TableField("fixed_prise")
    @ApiModelProperty(value = "固定价格")
    private BigDecimal fixedPrise;

    /**
     * 浮动百分比（性能测试）
     */
    @TableField("float_percent")
    @ApiModelProperty(value = "浮动百分比")
    private BigDecimal floatPercent;


}
