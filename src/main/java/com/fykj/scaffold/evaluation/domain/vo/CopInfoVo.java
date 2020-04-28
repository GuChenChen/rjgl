package com.fykj.scaffold.evaluation.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fykj.scaffold.support.conns.Cons;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description 企业信息
 * @Author gcc
 * @Date 2020/4/23 14:56
 **/
@Data
public class CopInfoVo {

	private static final long serialVersionUID = 1L;

	private String id;
	/**
	 * 企业统一社会信用
	 */
	private String copGbCode;

	/**
	 * 企业机构名称
	 */
	private String copName;

	/**
	 * 企业地址
	 */
	private String copAddr;

	/**
	 * 营业场所
	 */
	private String copPalce;

	/**
	 * 法定代表人
	 */
	private String legalPerson;

	/**
	 * 法定代表人身份证号码
	 */
	private String legalPersonId;

	/**
	 * 注册资本（万元）
	 */
	private String regCapital;

	/**
	 * 经济类型
	 */
	private String economy;

	/**
	 * 经营范围
	 */
	private String scope;

	/**
	 * 行业类型
	 */
	private String industry;

	/**
	 * 联系人
	 */
	private String linkMan;

	/**
	 * 联系人电话
	 */
	private String linkPhone;

	/**
	 * 联系人EMAIL
	 */
	private String linkEmail;

	/**
	 * 邮政编码
	 */
	private String postCode;

	/**
	 * 企业状态
	 */
	private String copState;

	/**
	 * 企业类型
	 */
	private String copType;

	/**
	 * 企业分类
	 */
	private String copSort;

	/**
	 * 登记日期
	 */
	@JsonFormat(pattern = Cons.DATETIME_FORMAT)
	private LocalDateTime regDate;


}
