package com.fykj.scaffold.evaluation.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fykj.scaffold.base.BaseEntity;
import com.fykj.scaffold.support.conns.Cons;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author wangf
 * @since 2020-02-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_evaluation_user")
public class SysEvaluationUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 公司名称
     */
    @TableField("company_name")
    private String companyName;

    /**
     * 社会信用代码
     */
    @TableField("social_credit_code")
    private String socialCreditCode;

    /**
     * 组织机构代码证
     */
    @TableField("organization_code_url")
    private String organizationCodeUrl;

    /**
     * 单位地址
     */
    @TableField("company_addr")
    private String companyAddr;

    /**
     * 发票抬头
     */
    @TableField("invoice_title")
    private String invoiceTitle;

    /**
     * 税号
     */
    @TableField("tax_number")
    private String taxNumber;

    /**
     * 开户行
     */
    @TableField("account_open_bank")
    private String accountOpenBank;

    /**
     * 开户账户
     */
    @TableField("account_open_account")
    private String accountOpenAccount;

    /**
     * 开票地址
     */
    @TableField("write_invoice_address")
    private String writeInvoiceAddress;

    /**
     * 开票电话
     */
    @TableField("write_invoice_phone")
    private String writeInvoicePhone;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 最后登录时间
     */
    @TableField("last_login_date")
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime lastLoginDate;
}
