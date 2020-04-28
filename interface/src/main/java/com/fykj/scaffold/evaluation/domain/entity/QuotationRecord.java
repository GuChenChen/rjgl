package com.fykj.scaffold.evaluation.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fykj.scaffold.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *  获取报价记录
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class QuotationRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 类型（甲方、乙方）
     */
    @TableField("type")
    @ApiModelProperty(value = "类型")
    private String type;

    /**
     * 甲方输入金额
     */
    @TableField("input_prise")
    @ApiModelProperty(value = "甲方输入金额")
    private BigDecimal inputPrise;

    /**
     * 子系统数
     */
    @TableField("subsystem_count")
    @ApiModelProperty(value = "子系统数")
    private Integer subsystemCount;

    /**
     * 子系统模块数
     */
    @TableField("subsystem_module_count")
    @ApiModelProperty(value = "子系统模块数")
    private Integer subsystemModuleCount;

    /**
     * 模块功能点数
     */
    @TableField("module_function_count")
    @ApiModelProperty(value = "模块功能点数")
    private Integer moduleFunctionCount;

    /**
     * 并发数
     */
    @TableField("concurrent_count")
    @ApiModelProperty(value = "并发数")
    private Integer concurrentCount;

    /**
     * 性能点
     */
    @TableField("performance_count")
    @ApiModelProperty(value = "性能点")
    private Integer performanceCount;

    /**
     * 代码行数
     */
    @TableField("code_lines_count")
    @ApiModelProperty(value = "代码行数")
    private Integer codeLinesCount;

    /**
     * 系统数
     */
    @TableField("system_count")
    @ApiModelProperty(value = "系统数")
    private Integer systemCount;

    /**
     * 最终报价
     */
    @TableField("quotation")
    @ApiModelProperty(value = "最终报价")
    private BigDecimal quotation;

    /**
     * 联系电话
     */
    @TableField("contact")
    @ApiModelProperty(value = "联系电话")
    private String contact;

    /**
     * 申请单位
     */
    @TableField("evaluation_user_id")
    @ApiModelProperty(value = "申请单位")
    private String evaluationUserId;

    /**
     * 上浮金额
     */
    @TableField("up_ward")
    @ApiModelProperty(value = "申请单位")
    private BigDecimal upWard;

    /**
     * 下浮金额
     */
    @TableField("down_Ward")
    @ApiModelProperty(value = "申请单位")
    private BigDecimal downWard;
}
