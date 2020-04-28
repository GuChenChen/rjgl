/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.fykj.scaffold.security.business.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fykj.scaffold.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * 系统日志
 *
 * @author zhangzhi
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_log")
public class SysLog extends BaseEntity {
    private static final long serialVersionUID = 7632293446546657509L;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    /**
     * 用户操作
     */
    @ApiModelProperty(value = "操作", required = true)
    private String operation;

    /**
     * 请求方法
     */
    @ApiModelProperty(value = "请求方法", required = true)
    private String method;

    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数")
    private String params;

    /**
     * 执行时长(毫秒)
     */
    @ApiModelProperty(value = "执行时长(毫秒)")
    private Long time;

    /**
     * IP地址
     */
    @ApiModelProperty(value = "IP地址")
    private String ip;

}
