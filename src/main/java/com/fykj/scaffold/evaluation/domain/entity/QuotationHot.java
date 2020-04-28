package com.fykj.scaffold.evaluation.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fykj.scaffold.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * 
 * 
 * @author wangming
 * @email ${email}
 * @date 2020-04-16 13:39:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("quotation_hot")
public class QuotationHot extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 查看时间
	 */
	@TableField("view_time")
	@ApiModelProperty(value = "查看时间")
	private LocalDate viewTime;

	/**
	 * 甲方
	 */
	@TableField("first_party")
    @ApiModelProperty(value = "甲方")
	private Integer firstParty;

	/**
	 * 乙方
	 */
	@TableField("second_party")
	@ApiModelProperty(value = "乙方")
	private Integer secondParty;

	/**
	 * 功能测试
	 */
	@TableField("function_test")
    @ApiModelProperty(value = "功能测试")
	private Integer functionTest;

	/**
	 * 性能测试
	 */
	@TableField("performance_test")
    @ApiModelProperty(value = "性能测试")
	private Integer performanceTest;

	/**
	 * 安全测试
	 */
	@TableField("security_test_code")
    @ApiModelProperty(value = "安全测试")
	private Integer securityTestCode;


}
