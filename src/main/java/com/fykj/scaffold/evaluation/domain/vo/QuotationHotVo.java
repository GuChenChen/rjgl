package com.fykj.scaffold.evaluation.domain.vo;

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
public class QuotationHotVo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 查看时间
	 */
	private String viewTime;

	/**
	 * 甲方
	 */
	private Integer firstParty;

	/**
	 * 乙方
	 */
	private Integer secondParty;

	/**
	 * 功能测试
	 */
	private Integer functionTest;

	/**
	 * 性能测试
	 */
	private Integer performanceTest;

	/**
	 * 安全测试
	 */
	private Integer securityTestCode;


}
