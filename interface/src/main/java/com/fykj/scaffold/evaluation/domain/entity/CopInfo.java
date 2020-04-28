package com.fykj.scaffold.evaluation.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.fykj.scaffold.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 * 
 * @author gcc
 * @email ${email}
 * @date 2020-04-23 15:04:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cop_info")
public class CopInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 企业统一社会信用
	 */
	@TableField("cop_gb_code")
    @ApiModelProperty(value = "企业统一社会信用")
	private String copGbCode;

	/**
	 * 企业机构名称
	 */
	@TableField("cop_name")
    @ApiModelProperty(value = "企业机构名称")
	private String copName;

	/**
	 * 企业地址
	 */
	@TableField("cop_addr")
    @ApiModelProperty(value = "企业地址")
	private String copAddr;

	/**
	 * 营业场所
	 */
	@TableField("cop_palce")
    @ApiModelProperty(value = "营业场所")
	private String copPalce;

	/**
	 * 法定代表人
	 */
	@TableField("legal_person")
    @ApiModelProperty(value = "法定代表人")
	private String legalPerson;

	/**
	 * 法定代表人身份证号码
	 */
	@TableField("legal_person_id")
    @ApiModelProperty(value = "法定代表人身份证号码")
	private String legalPersonId;

	/**
	 * 注册资本（万元）
	 */
	@TableField("reg_capital")
    @ApiModelProperty(value = "注册资本（万元）")
	private String regCapital;

	/**
	 * 经济类型
	 */
	@TableField("economy")
    @ApiModelProperty(value = "经济类型")
	private String economy;

	/**
	 * 经营范围
	 */
	@TableField("scope")
    @ApiModelProperty(value = "经营范围")
	private String scope;

	/**
	 * 行业类型
	 */
	@TableField("industry")
    @ApiModelProperty(value = "行业类型")
	private String industry;

	/**
	 * 联系人
	 */
	@TableField("link_man")
    @ApiModelProperty(value = "联系人")
	private String linkMan;

	/**
	 * 联系人电话
	 */
	@TableField("link_phone")
    @ApiModelProperty(value = "联系人电话")
	private String linkPhone;

	/**
	 * 联系人EMAIL
	 */
	@TableField("link_email")
    @ApiModelProperty(value = "联系人EMAIL")
	private String linkEmail;

	/**
	 * 邮政编码
	 */
	@TableField("post_code")
    @ApiModelProperty(value = "邮政编码")
	private String postCode;

	/**
	 * 企业状态
	 */
	@TableField("cop_state")
    @ApiModelProperty(value = "企业状态")
	private String copState;

	/**
	 * 企业类型
	 */
	@TableField("cop_type")
    @ApiModelProperty(value = "企业类型")
	private String copType;

	/**
	 * 企业分类
	 */
	@TableField("cop_sort")
    @ApiModelProperty(value = "企业分类")
	private String copSort;

	/**
	 * 登记日期
	 */
	@TableField("reg_date")
    @ApiModelProperty(value = "登记日期")
	private LocalDate regDate;


}
