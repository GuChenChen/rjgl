package com.fykj.scaffold.security.business.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fykj.scaffold.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangm
 * @since 2019-10-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ScheduleJob extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    /**
     * spring bean名称
     */
    @TableField("bean_name")
    @NotBlank(message="bean名称不能为空")
    private String beanName;

    /**
     * 参数
     */
    @TableField("params")
    private String params;

    /**
     * cron表达式
     */
    @TableField("cron_expression")
    @NotBlank(message="cron表达式不能为空")
    private String cronExpression;

    /**
     * 状态（启用，未启用）
     */
    @TableField("status")
    private Boolean status;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


}
