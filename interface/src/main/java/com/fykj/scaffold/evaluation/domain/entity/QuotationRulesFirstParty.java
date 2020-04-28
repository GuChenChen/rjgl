package com.fykj.scaffold.evaluation.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fykj.scaffold.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 *  报价规则甲方
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class QuotationRulesFirstParty extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 项目金额百分比
     */
    @TableField("amount_percent")
    @ApiModelProperty(value = "项目金额百分比")
    private BigDecimal amountPercent;

    /**
     * 浮动百分比
     */
    @TableField("float_percent")
    @ApiModelProperty(value = "浮动百分比")
    private BigDecimal floatPercent;


}
