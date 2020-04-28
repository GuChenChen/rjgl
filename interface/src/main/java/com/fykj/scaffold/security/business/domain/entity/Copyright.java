package com.fykj.scaffold.security.business.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fykj.scaffold.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 版权信息
 *
 * @author feihj.
 * @date: 2019/11/6 14:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("copyright")
public class Copyright extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 服务热线
     */
    @TableField("code")
    private String code;

    /**
     * 服务热线
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 传真
     */
    @TableField("fax")
    private String fax;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 版权公司
     */
    @TableField("copyright_company")
    private String copyrightCompany;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;


}
