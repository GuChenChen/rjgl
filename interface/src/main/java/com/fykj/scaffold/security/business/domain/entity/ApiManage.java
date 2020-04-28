package com.fykj.scaffold.security.business.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fykj.scaffold.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author yangx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("api_manage")
public class ApiManage extends BaseEntity {
    private static final long serialVersionUID = -8847288988390021787L;

    /**
     * 第三方接口所对应的code
     */
    @TableField("code")
    private String code;

    /**
     * 第三方接口url
     */
    @TableField("url")
    private String url;

    /**
     * 第三方接口调用方法
     */
    @TableField("method")
    private String method;

    /**
     * 第三方接口参数类型
     */
    @TableField("param_type")
    private String paramType;

    /**
     * 第三方接口返回类型
     */
    @TableField("response_type")
    private String responseType;

    /**
     * 第三方接口描述
     */
    @TableField("mark")
    private String mark;

    /**
     * 第三方接口状态（启用/禁用）
     */
    @TableField("status")
    private Boolean status;

}
